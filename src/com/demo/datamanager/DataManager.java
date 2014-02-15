package com.demo.datamanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.demo.FireQuery;
import com.demo.data.PositionsData;

public class DataManager {

	public boolean insertPositions(List<PositionsData> positionsDatas) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		try{
			connection = FireQuery.getconnection(); 
				//DriverManager.getConnection("jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com:3306/sagar_db","sagar123","sagar");
		
			preparedStatement = connection.prepareStatement("insert into positions (optionId ,amount " +
					",sourcePlatform ,batStrategyId ,position ,customerId ,currency ,rate ,originalRate " +
					",status ,leveratePositionId ,dateData ,rateUSD ,amountUSD ,id ,isAbuseCancel ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			Iterator<PositionsData> iterator = positionsDatas.iterator();
			while(iterator.hasNext()){
				PositionsData positionsData = iterator.next();
				preparedStatement.setString(1, positionsData.getOptionId());
				preparedStatement.setString(2, positionsData.getAmount());
				preparedStatement.setString(3, positionsData.getSourcePlatform());
				preparedStatement.setString(4, positionsData.getBatStrategyId());
				preparedStatement.setString(5, positionsData.getPosition());
				preparedStatement.setString(6, positionsData.getCustomerId());
				preparedStatement.setString(7, positionsData.getCurrency());
				preparedStatement.setString(8, positionsData.getRate());
				preparedStatement.setString(9, positionsData.getOriginalRate());
				preparedStatement.setString(10, positionsData.getStatus());
				preparedStatement.setString(11, positionsData.getLeveratePositionId());
				preparedStatement.setString(12, positionsData.getDate());
				preparedStatement.setString(13, positionsData.getRateUSD());
				preparedStatement.setString(14, positionsData.getAmountUSD());
				preparedStatement.setString(15, positionsData.getId());
				preparedStatement.setString(16, positionsData.getIsAbuseCancel());
				
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		
		}catch(SQLException sqe){
			//sqe.printStackTrace();
			throw sqe;
			
		}finally{

			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				//	e.printStackTrace();
				}
			}
		}
	}
	public boolean updatePositions(List<PositionsData> positionsDatas) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		try{
			connection = FireQuery.getconnection();
			//DriverManager.getConnection("jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com:3306/sagar_db","sagar123","sagar");
			preparedStatement = connection.prepareStatement("update positions set rate = ? ,set status = ? , set rateUSD = ? ,set amountUSD = ? where optionid = ?");
			Iterator<PositionsData> iterator = positionsDatas.iterator();
			while(iterator.hasNext()){
				PositionsData positionsData = iterator.next();
				preparedStatement.setString(1, positionsData.getRate());
				preparedStatement.setString(2, positionsData.getStatus());
				preparedStatement.setString(3, positionsData.getRateUSD());
				preparedStatement.setString(4, positionsData.getAmountUSD());
				preparedStatement.setString(5, positionsData.getOptionId());
				
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		
		}catch(SQLException sqe){
			sqe.printStackTrace();
			throw sqe;
			
		}finally{

			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					//e.printStackTrace();
				}
			}
		}
	}
	public Set<String> getPositionIdList(String customerid) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{
			connection =FireQuery.getconnection();
			//DriverManager.getConnection("jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com:3306/sagar_db","sagar123","sagar");
			preparedStatement = connection.prepareStatement("select id from positions where customerid = ?");
			preparedStatement.setString(1, customerid);
			ResultSet rs = preparedStatement.executeQuery();
			Set<String> positionIdList = new HashSet<String>();
			while(rs.next())
				positionIdList.add(rs.getString(1));
			
			
			return positionIdList;
		
		}catch(SQLException sqe){
			sqe.printStackTrace();
			throw sqe;
			
		}finally{

			if(preparedStatement != null){
				try {
					preparedStatement.close();
					
				} catch (SQLException e) {
					//e.printStackTrace();
				}
			}
			if(connection != null)  {
				connection.close();
			}
		}
	}
	
	public void initDatabase(){
		
	}

}
