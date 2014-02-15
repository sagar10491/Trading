package com.demo;

import com.demo.parsermanaager.ProductFetcher;

public class Constants {

	public  static String API_URL ="http://www.api.trade.spotoption.com/Api";
	public  static String USERNAME ="demo@demosite.com";
	public  static String PASSWORD ="123456";
	public  static String API_WHITELABEL ="DemoSite";
	public final static String CUSTOMER_MODULE ="Customer";
	public final static String ASSETS_MODULE ="Assets";
	public final static String VIEW_COMMAND = "view";
	public final static String ADD_COMMAND = "add";
	public final static String RULE_MODULE = "Rules";
	public final static String RULE_OPTIONSBUILDER = "optionsBuilder";
	public final static String RULE_SIXTYSECONDS = "sixtySeconds";
	public final static String POSITION_MODULE = "Positions";
	
	private static ProductFetcher productFetcher = null;
	public static void setBrokerConfig(String apiUrl, String userName, String pwd, String apilabel){
		if(productFetcher == null){
			
		}
	}

}
