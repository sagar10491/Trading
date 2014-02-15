package com.demo.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.FireQuery;

public class BrokerApiAccess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7350421668154258926L;
	private String apiUrl ;
	private String userName;
	private String password;
	private String apiLabel;
	/**
	 * 
	 */
	private String brokerwebsite;
	
	public String getBrokerwebsite() {
		return brokerwebsite;
	}
	public void setBrokerwebsite(String brokerwebsite) {
		this.brokerwebsite = brokerwebsite;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApiLabel() {
		return apiLabel;
	}
	public void setApiLabel(String apiLabel) {
		this.apiLabel = apiLabel;
	}

	
	public boolean preloginActivity(String brokerId) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		try {
			connect = FireQuery.getconnection();
			if (connect != null) {

				preparedStatement = null;
				preparedStatement = connect
						.prepareStatement("select * from broker where brokerid = ?");
				preparedStatement.setString(1, brokerId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					setApiUrl(resultSet.getString("apiurl"));
					setUserName(resultSet.getString("username"));
					setPassword(resultSet.getString("password"));
					setApiLabel(resultSet.getString("apilabel"));
					setBrokerwebsite(resultSet.getString("site"));
				}
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}finally{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
			try {
				connect.close();
			} catch (SQLException e) {
			//	e.printStackTrace();
			}
		}
		return true;
	}

}
