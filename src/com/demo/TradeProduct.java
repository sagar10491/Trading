package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.data.BrokerApiAccess;
import com.demo.data.CountSignals;
import com.demo.data.CustomerData;
import com.demo.data.PositionsData;
import com.demo.data.ProductData;
import com.demo.datamanager.DataManager;
import com.demo.parsermanaager.ProductFetcher;

/**
 * Servlet implementation class TradeProduct
 */
public class TradeProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeProduct() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String amountValue = request.getParameter("tardeAmount");
		CountSignals cs = new CountSignals();
		ProductData productData =  cs.getProdcutMap().get(request.getParameter("product"));
		CustomerData customerData =(CustomerData)request.getSession().getAttribute(AttributeConstants.LOGGED_IN);
		BrokerApiAccess brokerApiAccess = (BrokerApiAccess)request.getSession().getAttribute(AttributeConstants.BROKER_API);
		ProductFetcher productFetcher = new ProductFetcher();
		List<PositionsData> positionsDatas  = null;
		try{
			System.out.println(customerData.getId());
			positionsDatas = productFetcher.tradeProduct(brokerApiAccess,amountValue, productData, String.valueOf(customerData.getId()));
			if(positionsDatas != null && positionsDatas.size() > 0){
				DataManager dataManager = new DataManager();
				dataManager.insertPositions(positionsDatas);
				//response.sendRedirect("profile.jsp?execute=true");
				out.print("Trade performed Successfully");
			}else
				out.print("Error occured in Trading system, Reason : fetching of data from api error.");
				//throw new ServletException("Fetching of data from api error");
		}catch(Exception e){
			out.print("Error occured in Trading system, Reason :<b> "+e.getMessage()+"</b>");
		}
		
	}

}
