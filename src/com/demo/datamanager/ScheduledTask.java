package com.demo.datamanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

//import org.apache.axis2.databinding.types.xsd.Date;

import com.demo.FireQuery;
import com.demo.data.BrokerApiAccess;
import com.demo.data.CountSignals;
import com.demo.parsermanaager.ProductFetcher;

public class ScheduledTask {

	Calendar lastupdated = null;
	CountSignals countSignals = new CountSignals();
	ProductFetcher productFetcher = new ProductFetcher();
	int tradeAmount = 25;
	
	public ScheduledTask(){
		startSchedule();
	}
	private void startSchedule(){
		Timer timer = new Timer();
		TimerTask tt = new TimerTask(){
			public void run(){
				Calendar calendar = new GregorianCalendar();
				TimeZone timeZone = TimeZone.getTimeZone("Europe/London");
				calendar.setTimeZone(timeZone);
				if(calendar.get(Calendar.HOUR_OF_DAY) == 10){
					
					if(lastupdated == null){
						lastupdated = new GregorianCalendar();
						lastupdated.setTimeZone(timeZone);
					}else{
						
						if(lastupdated.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)){
							return;
						}
					}
					if(calendar.get(Calendar.HOUR_OF_DAY ) == 10){
						lastupdated.setTime(calendar.getTime());
						performBestTrade(generateCustomer());
					}
				}
			}
		};
		timer.schedule(tt,20000,1000*3600);
	}
	
	private Map<String, BrokerApiAccess> generateCustomer(){
		Map<String, BrokerApiAccess> autoTradeClients = new HashMap<String, BrokerApiAccess>();
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		try {
			connect = FireQuery.getconnection();
			if (connect != null) {

				preparedStatement = null;
				preparedStatement = connect
						.prepareStatement("select id,brokerid from login where autotrade =  'true'");
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					BrokerApiAccess brokerApiAccess = new BrokerApiAccess();
					if(brokerApiAccess.preloginActivity(resultSet.getString("brokerid"))){
						autoTradeClients.put(resultSet.getString("id"), brokerApiAccess);
					}
				}
			}
			return autoTradeClients;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(new Date()+" : Error Occured while getting customers from db , :"+e.getMessage());
		}finally{
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
			try {
				if(connect != null)
					connect.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return null;
	}
	
	private boolean performBestTrade(Map<String,BrokerApiAccess> brokerApiAccessMap){
		if(brokerApiAccessMap == null)
			return false;
		
		for (Map.Entry<String, BrokerApiAccess> entry : brokerApiAccessMap.entrySet()) {
			if(!performBestTrade(entry.getValue(), entry.getKey(),true)){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
				}
				performBestTrade(entry.getValue(), entry.getKey(),false);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		return true;
	}
	
	private boolean performBestTrade(BrokerApiAccess brokerApiAccess ,String customerId,boolean newTrade){
		countSignals.recommandedProdcut();
		try{
			
			productFetcher.tradeProduct(brokerApiAccess, String.valueOf(tradeAmount), CountSignals.productData, customerId);
			return true;
		}catch(Exception exp){
			if(!newTrade){
				
				System.out.println(new Date()+" : Auto Trade is Failed for customer : "+customerId+", Reason :"+exp.getMessage()+"/n Trying Again");
			}else{
				System.out.println(new Date()+"  : Auto Trade is Failed Again for customer : "+customerId+", Reason :"+exp.getMessage()+"/n Skipping this product");

			}
		}
		return false;
	}
}
