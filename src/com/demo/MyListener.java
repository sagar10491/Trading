package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.demo.datamanager.ScheduledTask;

public class MyListener implements ServletContextListener{

	Connection conn = null;
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		new ScheduledTask();
		String url = (String)context.getInitParameter("url");
		String userName = (String)context.getInitParameter("username");
		String password = (String) context.getInitParameter("password");
		String driver = (String) context.getInitParameter("driver");
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,userName,password);
	
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:demo","trading","trading");*/
			context.setAttribute("connection", conn);
			System.out.println("Connection established..");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("project undeployed");
		
	}
}
