package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.data.CustomerData;

/**
 * Servlet implementation class AutoTradeSetting
 */
public class AutoTradeSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoTradeSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tradeValue = request.getParameter("enable");
		if(tradeValue != null ){
			
			System.out.println(tradeValue);
			tradeValue = tradeValue.trim();
			if(tradeValue.equalsIgnoreCase("true") || tradeValue.equalsIgnoreCase("false")){
				Connection connect = null;
				PreparedStatement preparedStatement = null;
				try {
					connect = FireQuery.getconnection();
					if( connect != null){
						CustomerData customerData =(CustomerData)request.getSession().getAttribute(AttributeConstants.LOGGED_IN);
						preparedStatement = connect.prepareStatement("update login set autoTrade = ? where id = ?");
						preparedStatement.setString(1, tradeValue);
						preparedStatement.setString(2, String.valueOf(customerData.getId()));
						preparedStatement.executeUpdate();
						System.out.println("Auto Trade updated to "+tradeValue+".");
						request.getSession().setAttribute(AttributeConstants.AUTO_TRADE, Boolean.valueOf(tradeValue));

					}
				} catch (SQLException e) {
				//	e.printStackTrace();
				}finally{
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						//e.printStackTrace();
					}
					try {
						connect.close();
					} catch (SQLException e) {
						//e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
