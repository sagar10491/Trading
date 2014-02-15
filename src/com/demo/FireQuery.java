package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class FireQuery {

	public static Connection getconnection() {

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/tradingc_inrush1",
							"tradingc_inrush1", "inrush1234");
			return conn;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	/*
	 * public static void main(String[] arg) throws SQLException{ Connection
	 * conn = null; Statement statement = null; try{ conn = getconnection();
	 * statement = conn.createStatement(); // ResultSet resultSet =
	 * statement.executeQuery
	 * ("select autotrade from login where emailid='temp@temp.com'");
	 * System.out.println("Count :"); if(resultSet.next()){
	 * System.out.println(resultSet.getString("autotrade")); }else{
	 * System.out.println("mc"); } //statement.execute(
	 * "create table login (id varchar(100),name varchar(100),surname varchar(100),emailid varchar(100),password varchar(100),address varchar(100),brokerid varchar(100))"
	 * ); //conn.commit(); //int i = statement.executeUpdate(
	 * "insert into broker ( brokerId ,brokername ,username  ,password  ,apiurl  ,site ,apilabel) values ('1','Spot Option','demo@demosite.com','123456','http://www.api.trade.spotoption.com/Api','http://www.spotoption.com','DemoSite')"
	 * );
	 * 
	 * }catch(Exception e){ e.printStackTrace(); }finally{ if (statement !=
	 * null) { try { statement.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } } if(conn != null){ try { conn.close(); } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * }
	 */

}
