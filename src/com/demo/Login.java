package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.data.BrokerApiAccess;
import com.demo.data.CustomerData;
import com.demo.parsermanaager.LoginChecker;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String emailId = request.getParameter("username");
		String pwd = request.getParameter("pwd");
	
		RequestDispatcher loginFailed = request.getRequestDispatcher("index.jsp?invalid=true");  
		if(emailId != null && emailId.trim().length() > 1 && pwd != null && pwd.trim().length() > 1){

	        CustomerData customerData = new CustomerData();
	        customerData.setEmail(emailId);
	        customerData.setPassword(pwd);
	        Connection connect = null;
	        PreparedStatement preparedStatement = null;
	        try {
	        	connect = FireQuery.getconnection();
	        	if( connect != null){
	        		
	        		preparedStatement = connect.prepareStatement("select autotrade,brokerid from login where emailid = ? and password = ?");
	        		preparedStatement.setString(1, customerData.getEmail());
	        		preparedStatement.setString(2, customerData.getPassword());
	        		ResultSet resultSet = preparedStatement.executeQuery();
	        		HttpSession session = request.getSession();
	        		if(resultSet.next()){
	        			LoginChecker loginChecker = new LoginChecker();
	        			String brokerId = resultSet.getString("brokerid");
	        			String autoTradeStatus = resultSet.getString("autotrade");
	        			
	        			if(autoTradeStatus != null){
	        				System.out.println("Auto Trade : "+autoTradeStatus+" ::: "+Boolean.valueOf(autoTradeStatus));
	        				session.setAttribute(AttributeConstants.AUTO_TRADE, Boolean.valueOf(autoTradeStatus));
	        				
	        			}else {
	        				System.out.println("autoTradeStatus is null");
	        			}
	        			BrokerApiAccess brokerApiAccess = new BrokerApiAccess();
	        			if(brokerId != null && brokerApiAccess.preloginActivity(brokerId)){
	        				
	        				List<CustomerData> customerDatas = loginChecker.checkLogin(brokerApiAccess,customerData);
	        				if(customerDatas == null || customerDatas.size() < 1){
	        					System.out.println("cust datas is null ot < 0 "+customerDatas);
	        					loginFailed.forward(request, response); 
	        				}else{
	        					session.setAttribute("login", emailId);
	        					session.setAttribute("website",brokerApiAccess.getBrokerwebsite());
	        					session.setAttribute(AttributeConstants.LOGGED_IN, customerDatas.get(0));
	        					session.setAttribute(AttributeConstants.BROKER_API, brokerApiAccess);
	        					RequestDispatcher loginSuccess = request.getRequestDispatcher("homepage.jsp");  
	        					loginSuccess.forward(request, response); 
	        				}
	        			}else{
	        				System.out.println("login else ");
	        				loginFailed.forward(request, response); 
	        			}
	        		}else{
	        			System.out.println("login last else");
	        			loginFailed.forward(request, response); 
	        		}
	        	}else{
	        		
	        		loginFailed.forward(request, response); 
	        	}
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	loginFailed.forward(request, response); 
	        }finally{
	        	try {
	        		if(preparedStatement != null)
	        			preparedStatement.close();
	        	} catch (SQLException e) {
	        		e.printStackTrace();

	        	}
	        	try {
	        		if(connect != null)
	        			connect.close();
	        	} catch (SQLException e) {
	        		e.printStackTrace();
	        	}
	        }
		}else{
			
			loginFailed.forward(request, response); 
		}

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
