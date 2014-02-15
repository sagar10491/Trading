package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginChecker loginChecker = new LoginChecker();
		RequestDispatcher loginFailed = request.getRequestDispatcher("index.jsp?register_invalid=true");  
		BrokerApiAccess brokerApiAccess = new BrokerApiAccess();
		
		if(request.getParameter("broker") !=null && brokerApiAccess.preloginActivity(request.getParameter("broker"))){
			Map<String, String> map = new HashMap<String, String>();
			map.put("FirstName",request.getParameter("FirstName"));
			map.put("LastName",request.getParameter("LastName"));
			map.put("email",request.getParameter("email"));
			map.put("password",request.getParameter("password"));
			map.put("debugMode",request.getParameter("debugMode"));
			map.put("Phone",request.getParameter("Phone"));
			map.put("Country",request.getParameter("Country"));
			map.put("currency",request.getParameter("currency"));
			map.put("birthday",request.getParameter("birthday"));
			map.put("gender",request.getParameter("gender"));
			map.put("campaignId",request.getParameter("campaignId"));
			map.put("a_id",request.getParameter("a_id"));
			map.put("subCampaignId",request.getParameter("subCampaignId"));
			
			List<CustomerData> customerDatas = loginChecker.addUser(brokerApiAccess,map);

			if(customerDatas == null){
				System.out.println("customer datas null");
				loginFailed.forward(request, response);
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("login",customerDatas.get(0).getEmail());
				session.setAttribute(AttributeConstants.LOGGED_IN, customerDatas.get(0));
				Connection connect = null;
				PreparedStatement preparedStatement = null;
				try {
					connect = FireQuery.getconnection();
					if( connect != null){
						
						preparedStatement = connect.prepareStatement("insert into login (id,name,surname,emailid,password,address,brokerid,autoTrade) values (?,?,?,?,?,?,?,?)");
						preparedStatement.setLong(1, customerDatas.get(0).getId());
						preparedStatement.setString(2, customerDatas.get(0).getFirstName());
						preparedStatement.setString(3, customerDatas.get(0).getLastName());
						preparedStatement.setString(4, customerDatas.get(0).getEmail());
						preparedStatement.setString(5, request.getParameter("password"));
						preparedStatement.setString(6, customerDatas.get(0).getStreet()+","+customerDatas.get(0).getCity()+","+customerDatas.get(0).getState()+","+customerDatas.get(0).getCounrty()+". PIN CODE : "+customerDatas.get(0).getPostCode());
						preparedStatement.setString(7, request.getParameter("broker"));
						preparedStatement.setString(8, "true");
						System.out.println("User Addded :"+preparedStatement.executeUpdate());
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						connect.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				System.out.println("in else loop going to login  try");
				RequestDispatcher loginSuccess = request.getRequestDispatcher("Login?username="+request.getParameter("email")+"&pwd="+request.getParameter("password"));  
				loginSuccess.forward(request, response);
			}
		}else{
			System.out.println("in last else");
			loginFailed.forward(request, response);
		}
		
		
	}

}
