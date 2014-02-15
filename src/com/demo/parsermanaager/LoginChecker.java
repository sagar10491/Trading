package com.demo.parsermanaager;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.demo.Constants;
import com.demo.FireQuery;
import com.demo.data.BrokerApiAccess;
import com.demo.data.CustomerData;

public class LoginChecker {

	
	public List<CustomerData> checkLogin(BrokerApiAccess brokerApiAccess,CustomerData customerData){
		try {
			 
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(brokerApiAccess.getApiUrl());
				List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("api_username", brokerApiAccess.getUserName()));
				nameValuePairs.add(new BasicNameValuePair("api_password", brokerApiAccess.getPassword()));
				//nameValuePairs.add(new BasicNameValuePair("api_whiteLabel", brokerApiAccess.getApiLabel()));
				nameValuePairs.add(new BasicNameValuePair("MODULE", Constants.CUSTOMER_MODULE));
		        nameValuePairs.add(new BasicNameValuePair("FILTER[email]", customerData.getEmail()));
		        nameValuePairs.add(new BasicNameValuePair("FILTER[password", customerData.getPassword()));
		        // make aboove one correct
		        nameValuePairs.add(new BasicNameValuePair("COMMAND", "view"));
		        
		        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = client.execute(post);
		        /*BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        String line ="";
		        while((line = br.readLine())!=null){
		        	System.out.println(line);
		        }*/
		       return checkLogin(response.getEntity().getContent()); 
		        
		   } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		 }
		return null;
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
					
					Constants.setBrokerConfig(resultSet.getString("apiurl"),
							resultSet.getString("username"),
							resultSet.getString("password"),
							resultSet.getString("apilabel"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		return true;
	}
	public List<CustomerData> addUser(BrokerApiAccess brokerApiAccess,Map<String,String> map){
		try {
			 
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(brokerApiAccess.getApiUrl());
				List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("api_username", brokerApiAccess.getUserName()));
				nameValuePairs.add(new BasicNameValuePair("api_password", brokerApiAccess.getPassword()));
				//nameValuePairs.add(new BasicNameValuePair("api_whiteLabel", brokerApiAccess.getApiLabel()));
		        nameValuePairs.add(new BasicNameValuePair("MODULE", Constants.CUSTOMER_MODULE));
		        for (Map.Entry<String, String> entry : map.entrySet()) {
		        	nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		        	
		    	}
		        nameValuePairs.add(new BasicNameValuePair("COMMAND", "add"));
		        
		        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = client.execute(post);
		        /*BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        String line ="";
		        while((line = br.readLine())!=null){
		        	System.out.println(line);
		        }*/
		       
		        return generateData(response.getEntity().getContent());
		        
		   } catch (MalformedURLException e) {
			e.printStackTrace();
		   } catch (IOException e) {
			e.printStackTrace();
		   }
		  System.out.println("this will return null now");
		  return null;
	}
	private List<CustomerData> checkLogin( InputStream inputStream){
		
		try{
	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			NodeList serviceList = document.getElementsByTagName("status");
			List<CustomerData> customerDatas = new ArrayList<CustomerData>();
			
			for (int i = 0; i < serviceList.getLength(); i++) {
				NodeList nodeList = serviceList.item(0).getChildNodes();
				String strNodeName = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					strNodeName = node.getNodeName();
					if (strNodeName.equals("connection_status")) {
						
					} else if (strNodeName.equals("operation_status")){
						if(node.getTextContent().trim().equalsIgnoreCase("failed")){
							return null;
						}
					}else if(strNodeName.equals("errors")){
						return null;
					}else if (strNodeName.equals("Customer")){
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName()
									.contains("data")) {
								CustomerData customerData = new CustomerData();
								NodeList actualNodes = childNode.item(k)
										.getChildNodes();
								for (int l = 0; l < actualNodes.getLength(); l++) {
									String tagName = actualNodes.item(l)
											.getNodeName();
									String smallNode = actualNodes.item(l).getTextContent().trim();
									if (tagName.equals("id")) {
										System.out.println("id : "+smallNode);
										customerData.setId(Integer.parseInt(smallNode));
									}
									if (tagName.equals("FirstName")) {
										customerData.setFirstName(smallNode);
							
									}
									if (tagName.equals("LastName")) {
										customerData.setLastName(smallNode);
									}
									if (tagName.equals("gender")) {
										customerData.setGender(smallNode);
									}
									if (tagName.equals("email")) {
										customerData.setEmail(smallNode);
									}
									if (tagName.equals("password")) {
										customerData.setPassword(smallNode);
									}
									if (tagName.equals("cellphone")) {
										customerData.setCellPhone(smallNode);
									}
									if (tagName.equals("phone")) {
										customerData.setPhone(smallNode);
									}
									if (tagName.equals("fax")) {
										customerData.setFax(smallNode);
									}
									if (tagName.equals("specialAccountNumber")) {
										customerData.setSpecialAccountNumber(smallNode);
									}
									if (tagName.equals("personalId")) {
										customerData.setPersonalId(smallNode);
									}
									if (tagName.equals("firstDepositDate")) {
										customerData.setFirstDepositDate(smallNode);
									}
									if (tagName.equals("Country")) {
										customerData.setCounrty(smallNode);
									}
									if (tagName.equals("registrationCountry")) {
										customerData.setRegisteredCountry(smallNode);
									}
									if (tagName.equals("City")) {
										customerData.setCity(smallNode);
									}
									if (tagName.equals("state")) {
										customerData.setState(smallNode);
									}
									if (tagName.equals("newCustomer")) {
										customerData.setNewCustomer(smallNode);
									}
									if (tagName.equals("street")) {
										customerData.setStreet(smallNode);
									}
									if (tagName.equals("houseNumber")) {
										customerData.setHouseNumber(smallNode);
									}
									if (tagName.equals("aptNumber")) {
										customerData.setApartmentNumber(smallNode);
									}
									if (tagName.equals("risk")) {
										customerData.setRisk(smallNode);
									}
									if (tagName.equals("verification")) {
										customerData.setVerification(smallNode);
									}
									if (tagName.equals("referLink")) {
										customerData.setReferenceLink(smallNode);
									}
									if (tagName.equals("siteLanguage")) {
										customerData.setSiteLanguage(smallNode);
									}
									if (tagName.equals("timezone")) {
										customerData.setTimezone(smallNode);
									}
									if (tagName.equals("promotionalEmails")){
										customerData.setPromotionalEmails(smallNode);
									}
									if (tagName.equals("tradingEmails")) {
										customerData.setTradingEmails(smallNode);
									}
									if (tagName.equals("employeeInChargeId")) {
										customerData.setEmployeeInChargeId(smallNode);
									}
									if (tagName.equals("employeeInChargeName")) {
										customerData.setEmployeeInChargeName(smallNode);
									}
									if (tagName.equals("type")) {
										customerData.setType(smallNode);
									}
									if (tagName.equals("approvesEmailAds")) {
										customerData.setApprovesEmailAds(smallNode);
									}
									if (tagName.equals("campaignId")) {
										customerData.setCampaignId(smallNode);
									}
									if (tagName.equals("campaignName")) {
										customerData.setCampaignName(smallNode);
									}
									if (tagName.equals("birthday")) {
										customerData.setBirthday(smallNode);
									}
									if (tagName.equals("subCampaignId")) {
										customerData.setSubCampaignId(smallNode);
									}
									if (tagName.equals("subCampaignParam")) {
										customerData.setSubCampaignParam(smallNode);
									}
									if (tagName.equals("regTime")) {
										customerData.setRegTime(smallNode);
									}
									if (tagName.equals("regTimeFormatted")) {
										customerData.setRegTimeFormatted(smallNode);
									}
									if (tagName.equals("lastTimeActive")) {
									}
									if (tagName.equals("lastUpdate")) {
									}
									if (tagName.equals("regStatus")) {
									}
									if (tagName.equals("postCode")) {
									}
									if (tagName.equals("accountBalance")) {
										customerData.setAccountBalance(smallNode);
									}
									if (tagName.equals("saleStatus")) {
									}
									if (tagName.equals("leadStatus")) {
									}
									if (tagName.equals("regulateStatus")) {
									}
									if (tagName.equals("isSuspended")) {
									}
									if (tagName.equals("isBlackList")) {
									}
									if (tagName.equals("pnl")) {
									}
									if (tagName.equals("currencySign")) {
									}
									if (tagName.equals("currency")) {
									}
									if (tagName.equals("isLead")) {
									}
									if (tagName.equals("a_aid")) {
									}
									if (tagName.equals("isDemo")) {
									}
									if (tagName.equals("group")) {
									}
									if (tagName.equals("potential")) {
									}
									
								}
								customerDatas.add(customerData);
							}
						}
						return customerDatas;
					}
				}
				
			}
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(SAXException io){
			io.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return null;
	}
	
	private List<CustomerData> generateData( InputStream inputStream){
		
		try{
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			
			String xmlString =  org.apache.ws.security.util.XMLUtils.PrettyDocumentToString(document);
			System.out.println(xmlString);
			
			NodeList serviceList = document.getElementsByTagName("status");
			
			System.out.println(document.getTextContent());
			
			List<CustomerData> customerDatas = new ArrayList<CustomerData>();
			if(serviceList!=null && serviceList.getLength()==0){
				System.out.println("serviceList is null for node status" );
				return null;
			}
			
			for (int i = 0; i < serviceList.getLength(); i++) {
				NodeList nodeList = serviceList.item(0).getChildNodes();
				String strNodeName = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					strNodeName = node.getNodeName();
					
					System.out.println("node name " + strNodeName);
					if(node.getTextContent()!=null)
						System.out.println(strNodeName + " :: " + node.getTextContent().trim());
					
					if (strNodeName.equals("connection_status")) {
						
					} else if (strNodeName.equals("operation_status")){
						System.out.println(node.getTextContent().trim());
						if(node.getTextContent().trim().equalsIgnoreCase("failed")){
							return null;
						}
					}else if(strNodeName.equals("errors")){
						return null;
					}else if (strNodeName.equals("Customer")){
						NodeList actualNodes = node.getChildNodes();
						CustomerData customerData = new CustomerData();
								for (int l = 0; l < actualNodes.getLength(); l++) {
									String tagName = actualNodes.item(l)
											.getNodeName();
									String smallNode = actualNodes.item(l).getTextContent().trim();
									if (tagName.equals("id")) {
										System.out.println(smallNode);
										customerData.setId(Integer.parseInt(smallNode));
									}
									if (tagName.equals("FirstName")) {
										customerData.setFirstName(smallNode);
							
									}
									if (tagName.equals("LastName")) {
										customerData.setLastName(smallNode);
									}
									if (tagName.equals("gender")) {
										customerData.setGender(smallNode);
									}
									if (tagName.equals("email")) {
										customerData.setEmail(smallNode);
									}
									if (tagName.equals("password")) {
										customerData.setPassword(smallNode);
									}
									if (tagName.equals("cellphone")) {
										customerData.setCellPhone(smallNode);
									}
									if (tagName.equals("phone")) {
										customerData.setPhone(smallNode);
									}
									if (tagName.equals("fax")) {
										customerData.setFax(smallNode);
									}
									if (tagName.equals("specialAccountNumber")) {
										customerData.setSpecialAccountNumber(smallNode);
									}
									if (tagName.equals("personalId")) {
										customerData.setPersonalId(smallNode);
									}
									if (tagName.equals("firstDepositDate")) {
										customerData.setFirstDepositDate(smallNode);
									}
									if (tagName.equals("Country")) {
										customerData.setCounrty(smallNode);
									}
									if (tagName.equals("registrationCountry")) {
										customerData.setRegisteredCountry(smallNode);
									}
									if (tagName.equals("City")) {
										customerData.setCity(smallNode);
									}
									if (tagName.equals("state")) {
										customerData.setState(smallNode);
									}
									if (tagName.equals("newCustomer")) {
										customerData.setNewCustomer(smallNode);
									}
									if (tagName.equals("street")) {
										customerData.setStreet(smallNode);
									}
									if (tagName.equals("houseNumber")) {
										customerData.setHouseNumber(smallNode);
									}
									if (tagName.equals("aptNumber")) {
										customerData.setApartmentNumber(smallNode);
									}
									if (tagName.equals("risk")) {
										customerData.setRisk(smallNode);
									}
									if (tagName.equals("verification")) {
										customerData.setVerification(smallNode);
									}
									if (tagName.equals("referLink")) {
										customerData.setReferenceLink(smallNode);
									}
									if (tagName.equals("siteLanguage")) {
										customerData.setSiteLanguage(smallNode);
									}
									if (tagName.equals("timezone")) {
										customerData.setTimezone(smallNode);
									}
									if (tagName.equals("promotionalEmails")){
										customerData.setPromotionalEmails(smallNode);
									}
									if (tagName.equals("tradingEmails")) {
										customerData.setTradingEmails(smallNode);
									}
									if (tagName.equals("employeeInChargeId")) {
										customerData.setEmployeeInChargeId(smallNode);
									}
									if (tagName.equals("employeeInChargeName")) {
										customerData.setEmployeeInChargeName(smallNode);
									}
									if (tagName.equals("type")) {
										customerData.setType(smallNode);
									}
									if (tagName.equals("approvesEmailAds")) {
										customerData.setApprovesEmailAds(smallNode);
									}
									if (tagName.equals("campaignId")) {
										customerData.setCampaignId(smallNode);
									}
									if (tagName.equals("campaignName")) {
										customerData.setCampaignName(smallNode);
									}
									if (tagName.equals("birthday")) {
										customerData.setBirthday(smallNode);
									}
									if (tagName.equals("subCampaignId")) {
										customerData.setSubCampaignId(smallNode);
									}
									if (tagName.equals("subCampaignParam")) {
										customerData.setSubCampaignParam(smallNode);
									}
									if (tagName.equals("regTime")) {
										customerData.setRegTime(smallNode);
									}
									if (tagName.equals("regTimeFormatted")) {
										customerData.setRegTimeFormatted(smallNode);
									}
									if (tagName.equals("lastTimeActive")) {
									}
									if (tagName.equals("lastUpdate")) {
									}
									if (tagName.equals("regStatus")) {
									}
									if (tagName.equals("postCode")) {
									}
									if (tagName.equals("accountBalance")) {
										customerData.setAccountBalance(smallNode);
									}
									if (tagName.equals("saleStatus")) {
									}
									if (tagName.equals("leadStatus")) {
									}
									if (tagName.equals("regulateStatus")) {
									}
									if (tagName.equals("isSuspended")) {
									}
									if (tagName.equals("isBlackList")) {
									}
									if (tagName.equals("pnl")) {
									}
									if (tagName.equals("currencySign")) {
									}
									if (tagName.equals("currency")) {
									}
									if (tagName.equals("isLead")) {
									}
									if (tagName.equals("a_aid")) {
										customerData.setA_aid(smallNode);
									}
									if (tagName.equals("isDemo")) {
									}
									if (tagName.equals("group")) {
									}
									if (tagName.equals("potential")) {
									}
							}
							customerDatas.add(customerData);
						}
					}
				}
			return customerDatas;
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(SAXException io){
			io.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return null;
	}

}
