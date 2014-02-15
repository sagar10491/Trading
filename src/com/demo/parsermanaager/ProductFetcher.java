package com.demo.parsermanaager;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.demo.Constants;
import com.demo.data.BrokerApiAccess;
import com.demo.data.CountSignals;
import com.demo.data.PositionsData;
import com.demo.data.ProductData;
import com.demo.data.Rules;
import com.demo.datamanager.DataManager;

@SuppressWarnings("deprecation")
public class ProductFetcher {
	static boolean dataAvailable = false;
	private String API_URL ="http://www.api.trade.spotoption.com/Api";
	private  String USERNAME ="demo@demosite.com";
	private String PASSWORD ="123456";
	private String API_WHITELABEL ="DemoSite";
	static{
		
			//dataAvailable = true;
			TimerTask t = new TimerTask() {
				public void run(){
					//System.out.println("called--------------");
					ProductFetcher productFetcher = new ProductFetcher();
					List<ProductData> proDataList = null;
					List<Rules> rulesList = null;
					try {
						proDataList = productFetcher.getProductDataList();
						rulesList = productFetcher.fetchRules();
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<ProductData> productDataList = new ArrayList<ProductData>();
					if (rulesList != null) {
						if (rulesList.size() > 0) {
							Iterator<Rules> ruleItreator = rulesList.iterator();
							while (ruleItreator.hasNext()) {

								Iterator<ProductData> productIterator = proDataList.iterator();
								Rules ruleData = ruleItreator.next();
								String startTime = ruleData.getStartTime();
								String endTime = ruleData.getEndTime();
								int flagStarttime = 0;
								int flagEndtime = 0;
								if (startTime.toLowerCase().contains("pm")) {
									flagStarttime = 1;
								}
								if (endTime.toLowerCase().contains("pm")) {
									flagEndtime = 1;
								}
								startTime = startTime.replaceAll("am", "").trim();
								startTime = startTime.replaceAll("pm", "").trim();
								endTime = endTime.replaceAll("am", "").trim();
								endTime = endTime.replaceAll("pm", "").trim();
								String sTimes[] = startTime.split(":");
								String eTimes[] = endTime.split(":");

								SimpleDateFormat f = new SimpleDateFormat("HH:mm");
								f.setTimeZone(TimeZone.getTimeZone("Europe/London"));
								String d = f.format(GregorianCalendar.getInstance().getTime());
								String europeTime[] = d.split(":");
								int finalflag = 0;
								try {
									if(flagStarttime == 1)  {
										int i = Integer.parseInt(sTimes[0]) + 12;
										sTimes[0] = i+"";
									}
									if (Integer.parseInt(sTimes[0].trim()) <= Integer.parseInt(europeTime[0])) {

									}else {
										finalflag = 1;
									}
									if (Integer.parseInt(europeTime[0].trim()) <= Integer.parseInt(sTimes[0]) ) {

									}else {
										finalflag = 1;
									}
									if (Integer.parseInt(sTimes[0].trim()) == Integer.parseInt(europeTime[0])) {
										if (Integer.parseInt(sTimes[1].trim()) <= Integer.parseInt(europeTime[1])) {

										}else {
											finalflag = 1;
										}

									}
									if (Integer.parseInt(europeTime[0].trim()) == Integer.parseInt(sTimes[0]) ) {
										if (Integer.parseInt(europeTime[1].trim()) <= Integer.parseInt(sTimes[1]) ) {

										}else {
											finalflag = 1;
										}

									}
								} catch (Exception e) {

								}
								if(finalflag == 0 ) {
								while (productIterator.hasNext()) {
									ProductData productData = productIterator.next();
									if (productData.getId().equals(ruleData.getAssetId())) {
										productData.setRules(ruleData);
										productDataList.add(productData);
										continue;
									}
								}
}
							}
						}
					}
					CountSignals.getCountSingal().readData(productDataList);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
			scheduledThreadPoolExecutor.prestartAllCoreThreads();
			scheduledThreadPoolExecutor.scheduleWithFixedDelay(t, 10, 10, TimeUnit.SECONDS);
		
	}
	public List<ProductData> getProductDataList() throws Exception {
		try {

			HttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost(API_URL);
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("api_username", USERNAME));
			nameValuePairs.add(new BasicNameValuePair("api_password", PASSWORD));
			nameValuePairs.add(new BasicNameValuePair("api_whiteLabel", API_WHITELABEL));
			nameValuePairs.add(new BasicNameValuePair("MODULE", Constants.ASSETS_MODULE));
			nameValuePairs.add(new BasicNameValuePair("COMMAND", Constants.VIEW_COMMAND));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			/*
			 * BufferedReader br = new BufferedReader(new
			 * InputStreamReader(response.getEntity().getContent())); String
			 * line =""; while((line = br.readLine())!=null){
			 * System.out.println(line); }
			 */
			return generateData(response.getEntity().getContent());

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

	public ProductFetcher(){
		
	}
	public ProductFetcher(String aPI_URL, String uSERNAME, String pASSWORD,
			String aPI_WHITELABEL) {
		super();
		API_URL = aPI_URL;
		USERNAME = uSERNAME;
		PASSWORD = pASSWORD;
		API_WHITELABEL = aPI_WHITELABEL;
	}

	private List<Rules> fetchRules() throws Exception {
		try {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(API_URL);
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("api_username", USERNAME));
			nameValuePairs.add(new BasicNameValuePair("api_password", PASSWORD));
			nameValuePairs.add(new BasicNameValuePair("api_whiteLabel", API_WHITELABEL));
			nameValuePairs.add(new BasicNameValuePair("MODULE", Constants.RULE_MODULE));

			nameValuePairs.add(new BasicNameValuePair("COMMAND", Constants.VIEW_COMMAND));
			nameValuePairs.add(new BasicNameValuePair("FILTER[product]", Constants.RULE_OPTIONSBUILDER));
			nameValuePairs.add(new BasicNameValuePair("FILTER[rules]", "live"));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			List<Rules> ruleList = null;
			try {
				ruleList = generateRulesList(response.getEntity().getContent(), Constants.RULE_OPTIONSBUILDER);
				if (ruleList != null) {
					for (int i = 0; i < ruleList.size(); i++) {
						ruleList.get(i).setTradeType(Constants.RULE_OPTIONSBUILDER);
					}
				}
			} catch (Exception e) {

			}
			nameValuePairs.remove(nameValuePairs.size() - 1);
			nameValuePairs.add(new BasicNameValuePair("FILTER[product]", Constants.RULE_SIXTYSECONDS));
			nameValuePairs.add(new BasicNameValuePair("FILTER[rules]", "live"));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = client.execute(post);
			if (ruleList == null) {
				try {

					ruleList = generateRulesList(response.getEntity().getContent(), Constants.RULE_SIXTYSECONDS);
					if (ruleList != null) {
						for (int i = 0; i < ruleList.size(); i++) {
							ruleList.get(i).setTradeType(Constants.RULE_SIXTYSECONDS);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				try {
					List<Rules> rules = generateRulesList(response.getEntity().getContent(), Constants.RULE_SIXTYSECONDS);
					if (rules != null) {

						for (int i = 0; i < rules.size(); i++) {
							rules.get(i).setTradeType(Constants.RULE_SIXTYSECONDS);
						}
						ruleList.addAll(rules);
					}
				} catch (Exception e) {
					// to handel no rule found exception
					// TODO: handle exception
				}
			}
			//System.out.println("final" + ruleList.size());
			return ruleList;
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

	private List<Rules> generateRulesList(InputStream inputStream, String type) throws DOMException, Exception {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			NodeList serviceList = document.getElementsByTagName("status");
			List<Rules> ruleDataList = new ArrayList<Rules>();

			for (int i = 0; i < serviceList.getLength(); i++) {
				NodeList nodeList = serviceList.item(0).getChildNodes();
				String strNodeName = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					strNodeName = node.getNodeName();
					if (strNodeName.equals("connection_status")) {

					} else if (strNodeName.equals("operation_status")) {
						if (node.getTextContent().trim().equalsIgnoreCase("failed")) {
						}
					} else if (strNodeName.equals("errors")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("error"))
								throw new Exception(childNode.item(k).getTextContent());
						}
						return null;
					} else if (strNodeName.equals("Rules")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("data")) {
								Rules ruleData = new Rules();
								ruleData.setType(type);
								NodeList actualNodes = childNode.item(k).getChildNodes();
								for (int l = 0; l < actualNodes.getLength(); l++) {
									String tagName = actualNodes.item(l).getNodeName();
									String smallNode = actualNodes.item(l).getTextContent().trim();
									if (tagName.equals("id")) {
										ruleData.setId(smallNode);
									}
									if (tagName.equals("weekDays")) {
										ruleData.setWeekDays(smallNode);

									}
									if (tagName.equals("startTime")) {
										ruleData.setStartTime(smallNode);
									}
									if (tagName.equals("endTime")) {
										ruleData.setEndTime(smallNode);
									}
									if (tagName.equals("endTimeFormatted")) {
										ruleData.setEndTimeFormatted(smallNode);
									}
									if (tagName.equals("startTimeFormatted")) {
										ruleData.setStartTimeFormatted(smallNode);
									}
									if (tagName.equals("startTimeTimeStamp")) {
										ruleData.setStartTimeTimeStamp(smallNode);
									}
									if (tagName.equals("profit")) {
										ruleData.setProfit(smallNode);
									}
									if (tagName.equals("loss")) {
										ruleData.setLoss(smallNode);
									}
									if (tagName.equals("maxInvestment")) {
										ruleData.setMaxInvestment(smallNode);
									}
									if (tagName.equals("minInvestment")) {
										ruleData.setMinInvestment(smallNode);
									}
									if (tagName.equals("amountRange")) {
										ruleData.setAmountRange(smallNode);
									}
									if (tagName.equals("assetId")) {
										ruleData.setAssetId(smallNode);
									}

								}
								/*
								 * if(type.equals(Constants.RULE_OPTIONSBUILDER))
								 * { int min =
								 * (int)Double.parseDouble(ruleData.getMinInvestment
								 * ()); int max =
								 * (int)Double.parseDouble(ruleData
								 * .getMaxInvestment()); String amountRange =
								 * String.valueOf(min); min = min * 2; while
								 * (min <= max){ amountRange += ","+min; min =
								 * min * 2; }
								 * ruleData.setAmountRange(amountRange); }
								 */
								ruleDataList.add(ruleData);
							}
						}
						return ruleDataList;
					}
				}

			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException io) {
			io.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private List<PositionsData> generatePositionsData(InputStream inputStream, String customerId) throws Exception {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			NodeList serviceList = document.getElementsByTagName("status");
			List<PositionsData> positionList = new ArrayList<PositionsData>();

			for (int i = 0; i < serviceList.getLength(); i++) {
				NodeList nodeList = serviceList.item(0).getChildNodes();
				String strNodeName = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					strNodeName = node.getNodeName();
					if (strNodeName.equals("connection_status")) {

					} else if (strNodeName.equals("operation_status")) {
						if (node.getTextContent().trim().equalsIgnoreCase("failed")) {

						}
					} else if (strNodeName.equals("errors")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("error"))
								throw new Exception(childNode.item(k).getTextContent());
						}
						return null;
					} else if (strNodeName.equals("Positions")) {
						NodeList actualNodes = node.getChildNodes();
						PositionsData positionsData = new PositionsData();
						for (int l = 0; l < actualNodes.getLength(); l++) {
							String tagName = actualNodes.item(l).getNodeName();
							String smallNode = actualNodes.item(l).getTextContent().trim();
							if (tagName.equals("id")) {
								positionsData.setId(smallNode);
							}
							if (tagName.equals("optionId")) {
								positionsData.setOptionId(smallNode);

							}
							if (tagName.equals("amount")) {
								positionsData.setAmount(smallNode);
							}
							if (tagName.equals("sourcePlatform")) {
								positionsData.setSourcePlatform(smallNode);
							}
							if (tagName.equals("batStrategyId")) {
								positionsData.setBatStrategyId(smallNode);
							}
							if (tagName.equals("position")) {
								positionsData.setPosition(smallNode);
							}
							if (tagName.equals("customerId")) {
								if (smallNode == null || smallNode.equals("0") || smallNode.trim().length() < 1)
									positionsData.setCustomerId(customerId);
								else
									positionsData.setCustomerId(smallNode);
							}
							if (tagName.equals("currency")) {
								positionsData.setCurrency(smallNode);
							}
							if (tagName.equals("rate")) {
								positionsData.setRate(smallNode);
							}
							if (tagName.equals("amountUSD")) {
								positionsData.setAmountUSD(smallNode);
							}
							if (tagName.equals("rateUSD")) {
								positionsData.setRateUSD(smallNode);
							}
							if (tagName.equals("date")) {
								positionsData.setDate(smallNode);
							}
							if (tagName.equals("leveratePositionId")) {
								positionsData.setLeveratePositionId(smallNode);
							}
							if (tagName.equals("status")) {
								positionsData.setStatus(smallNode);
							}
							if (tagName.equals("originalRate")) {
								positionsData.setOriginalRate(smallNode);
							}
							if (tagName.equals("isAbuseCancel")) {
								positionsData.setIsAbuseCancel(smallNode);
							}
						}
						positionsData.setCustomerId(customerId);
						positionList.add(positionsData);
						return positionList;

					}

				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException io) {
			io.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	private List<PositionsData> updatedPositionsData(InputStream inputStream, String customerId) throws Exception {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			NodeList serviceList = document.getElementsByTagName("status");
			List<PositionsData> positionList = new ArrayList<PositionsData>();

			for (int i = 0; i < serviceList.getLength(); i++) {
				NodeList nodeList = serviceList.item(0).getChildNodes();
				String strNodeName = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					strNodeName = node.getNodeName();
					if (strNodeName.equals("connection_status")) {

					} else if (strNodeName.equals("operation_status")) {
						if (node.getTextContent().trim().equalsIgnoreCase("failed")) {

						}
					} else if (strNodeName.equals("errors")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("error"))
								throw new Exception(childNode.item(k).getTextContent());
						}
						return null;
					} else if (strNodeName.equals("Positions")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("data")) {

								PositionsData positionsData = new PositionsData();
								NodeList actualNodes = childNode.item(k).getChildNodes();
								for (int l = 0; l < actualNodes.getLength(); l++) {
									String tagName = actualNodes.item(l).getNodeName();
									String smallNode = actualNodes.item(l).getTextContent().trim();
									if (tagName.equals("id")) {
										positionsData.setId(smallNode);
									}
									if (tagName.equals("optionId")) {
										positionsData.setOptionId(smallNode);

									}
									if (tagName.equals("amount")) {
										positionsData.setAmount(smallNode);
									}
									if (tagName.equals("sourcePlatform")) {
										positionsData.setSourcePlatform(smallNode);
									}
									if (tagName.equals("batStrategyId")) {
										positionsData.setBatStrategyId(smallNode);
									}
									if (tagName.equals("position")) {
										positionsData.setPosition(smallNode);
									}
									if (tagName.equals("customerId")) {
										if (smallNode == null || smallNode.equals("0") || smallNode.trim().length() < 1)
											positionsData.setCustomerId(customerId);
										else
											positionsData.setCustomerId(smallNode);
									}
									if (tagName.equals("currency")) {
										positionsData.setCurrency(smallNode);
									}
									if (tagName.equals("endRate")) {
										positionsData.setRate(smallNode);
									}
									if (tagName.equals("amountUSD")) {
										positionsData.setAmountUSD(smallNode);
									}
									if (tagName.equals("rateUSD")) {
										positionsData.setRateUSD(smallNode);
									}
									if (tagName.equals("date")) {
										positionsData.setDate(smallNode);
									}
									if (tagName.equals("leveratePositionId")) {
										positionsData.setLeveratePositionId(smallNode);
									}
									if (tagName.equals("status")) {
										positionsData.setStatus(smallNode);
									}
									if (tagName.equals("originalRate")) {
										positionsData.setOriginalRate(smallNode);
									}
									if (tagName.equals("isAbuseCancel")) {
										positionsData.setIsAbuseCancel(smallNode);
									}

									if (tagName.equals("assetId")) {
										positionsData.setAssetId("Asset Id : " + smallNode);
									}
									if (tagName.equals("name")) {
										positionsData.setAssetName(smallNode);
									}

								}
								positionsData.setCustomerId(customerId);
								positionList.add(positionsData);
							}

						}
						return positionList;
					}
				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException io) {
			io.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private List<ProductData> generateData(InputStream inputStream) throws DOMException, Exception {

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			NodeList serviceList = document.getElementsByTagName("status");
			List<ProductData> productDataList = new ArrayList<ProductData>();

			for (int i = 0; i < serviceList.getLength(); i++) {
				NodeList nodeList = serviceList.item(0).getChildNodes();
				String strNodeName = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					strNodeName = node.getNodeName();
					if (strNodeName.equals("connection_status")) {

					} else if (strNodeName.equals("operation_status")) {
						if (node.getTextContent().trim().equalsIgnoreCase("failed")) {
						}
					} else if (strNodeName.equals("errors")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("error"))
								throw new Exception(childNode.item(k).getTextContent());
						}
						return null;
					} else if (strNodeName.equals("Assets")) {
						NodeList childNode = node.getChildNodes();
						for (int k = 0; k < childNode.getLength(); k++) {
							if (childNode.item(k).getNodeName().contains("data")) {
								ProductData productData = new ProductData();
								NodeList actualNodes = childNode.item(k).getChildNodes();
								for (int l = 0; l < actualNodes.getLength(); l++) {
									String tagName = actualNodes.item(l).getNodeName();
									String smallNode = actualNodes.item(l).getTextContent().trim();
									if (tagName.equals("id")) {
										productData.setId(smallNode);
									}
									if (tagName.equals("name")) {
										productData.setName(smallNode);

									}
									if (tagName.equals("isTradeable")) {
										productData.setIsTradeable(smallNode);
									}
									if (tagName.equals("symbol")) {
										productData.setSymbol(smallNode);
									}
									if (tagName.equals("rate")) {
										productData.setRate(smallNode);
									}
									if (tagName.equals("tailDigits")) {
										productData.setTailDigits(smallNode);
									}
									if (tagName.equals("lastUpdated")) {
										productData.setLastUpdated(smallNode);
									}
									if (tagName.equals("tradePrice")) {
										productData.setTradePrice(smallNode);
									}
									if (tagName.equals("type")) {
										productData.setType(smallNode);
									}
									if (tagName.equals("showInHome")) {
										productData.setShowInHome(smallNode);
									}
									if (tagName.equals("priceTick")) {
										productData.setPriceTick(smallNode);
									}
									if (tagName.equals("color")) {
										productData.setColor(smallNode);
									}
									if (tagName.equals("correlation")) {
										productData.setCorrelation(smallNode);
									}
									if (tagName.equals("trend")) {
										productData.setTrend(smallNode);
									}
									if (tagName.equals("trendType")) {
										productData.setTrendType(smallNode);
									}

								}
								productDataList.add(productData);
							}
						}
						return productDataList;
					}
				}

			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException io) {
			io.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<ProductData> filterWeekDaysOffProducts(List<ProductData> productDataList) {
		List<ProductData> productDatas = new ArrayList<ProductData>();
		Iterator<ProductData> iterator = productDataList.iterator();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a hh:mm");
		Calendar calendar = Calendar.getInstance();
		String dayOfTheWeek = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
		Date currentDate = new Date();
		while (iterator.hasNext()) {
			ProductData productData = iterator.next();
			Rules rules = productData.getRules();
			try {

				Date startDate = simpleDateFormat.parse(rules.getStartTime());
				Date endDate = simpleDateFormat.parse(rules.getEndTime());
				String numberOfWeek = rules.getWeekDays();
				if (numberOfWeek.contains(dayOfTheWeek) && ((startDate.before(currentDate) && endDate.after(currentDate)) || startDate.compareTo(currentDate) == 0 || endDate.compareTo(currentDate) == 0)) {
					productDatas.add(productData);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return productDatas;
	}

	public static boolean isTradableCheck(ProductData productData) {
		String tradable = productData.getIsTradeable();
		if (tradable.equals("-1")) {
			return false;
		}
		/*
		 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a hh:mm");
		 * Calendar calendar = Calendar.getInstance(); String dayOfTheWeek =
		 * String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)); Date currentDate
		 * = new Date(); Rules rules = productData.getRules(); try {
		 * 
		 * Date startDate = simpleDateFormat.parse(rules.getStartTime());
		 * currentDate.setYear(startDate.getYear());
		 * currentDate.setMonth(startDate.getMonth());
		 * currentDate.setDate(startDate.getDate()); Date endDate =
		 * simpleDateFormat.parse(rules.getEndTime()); String numberOfWeek =
		 * rules.getWeekDays(); if (numberOfWeek.contains(dayOfTheWeek) &&
		 * ((startDate.before(currentDate) && endDate.after(currentDate)) ||
		 * startDate.compareTo(currentDate) == 0 ||
		 * endDate.compareTo(currentDate) == 0)) { return true; } } catch
		 * (ParseException e) { e.printStackTrace(); }
		 */return true;
	}

	public List<PositionsData> updateTradeProductInfo(BrokerApiAccess brokerApiAccess ,String customerId) throws Exception {
		try {

			List<PositionsData> productDatas = new ArrayList<PositionsData>();
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(brokerApiAccess.getApiUrl());
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("api_username", brokerApiAccess.getUserName()));
			nameValuePairs.add(new BasicNameValuePair("api_password", brokerApiAccess.getPassword()));
			//nameValuePairs.add(new BasicNameValuePair("api_whiteLabel", brokerApiAccess.getApiLabel()));
			nameValuePairs.add(new BasicNameValuePair("MODULE", Constants.POSITION_MODULE));
			nameValuePairs.add(new BasicNameValuePair("COMMAND", Constants.VIEW_COMMAND));
			// nameValuePairs.add(new BasicNameValuePair("page","1"));
			nameValuePairs.add(new BasicNameValuePair("FILTER[customerId]", customerId));
			Date today = Calendar.getInstance().getTime();
			today.setMonth(today.getMonth() - 1);
			DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
			nameValuePairs.add(new BasicNameValuePair("FILTER[date][min]", df.format(today)));
			// FILTER[date][min]=2013_06_01
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			try {
				productDatas = updatedPositionsData(response.getEntity().getContent(), customerId);

				Set<String> optionIdSet = new DataManager().getPositionIdList(customerId);
				Iterator<PositionsData> iterator = productDatas.iterator();
				List<PositionsData> positionsDatas2 = new ArrayList<PositionsData>();
				while (iterator.hasNext()) {
					PositionsData positionData = iterator.next();
					if (optionIdSet.contains(positionData.getId())) {
						positionsDatas2.add(positionData);
					}
				}
				Collections.sort(positionsDatas2);
				return positionsDatas2;
			} catch (Exception ex) {
				throw ex;
			}

		} catch (MalformedURLException e) {

			throw e;

		} catch (IOException e) {

			throw e;

		}
	}

	public List<PositionsData> tradeProduct(BrokerApiAccess brokerApiAccess ,String amount, ProductData productData, String customerId) throws Exception {
		try {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(brokerApiAccess.getApiUrl());
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("api_username", brokerApiAccess.getUserName()));
			nameValuePairs.add(new BasicNameValuePair("api_password", brokerApiAccess.getPassword()));
			//nameValuePairs.add(new BasicNameValuePair("api_whiteLabel", brokerApiAccess.getApiLabel()));
			nameValuePairs.add(new BasicNameValuePair("MODULE", Constants.POSITION_MODULE));
			nameValuePairs.add(new BasicNameValuePair("COMMAND", Constants.ADD_COMMAND));
			nameValuePairs.add(new BasicNameValuePair("product", productData.getRules().getType()));
			nameValuePairs.add(new BasicNameValuePair("ruleId", productData.getRules().getId()));
			nameValuePairs.add(new BasicNameValuePair("amount", amount));

			nameValuePairs.add(new BasicNameValuePair("position", "call"));
			if (productData.getRules().getTradeType().equals(Constants.RULE_OPTIONSBUILDER)) {
				// System.out.println(productData.getRules().getStartTime());
				System.out.println(productData.getRules().getEndTime());
				Date d = new Date();
				// am 10:00
				try {
					String eTime = productData.getRules().getEndTime();
					if (eTime.toLowerCase().contains("am"))
						eTime = eTime.toLowerCase().replaceAll("am", "");
					if (eTime.toLowerCase().contains("pm"))
						eTime = eTime.toLowerCase().replaceAll("pm", "");

					eTime = eTime.trim();
					// d.setHours(Integer.parseInt(arr[0].trim()) + 10);
					// d.setMinutes(Integer.parseInt(arr[1].trim()) + 10);
					d.setHours(d.getHours() + 1);
					
					Timestamp timestamp = new Timestamp(d.getTime() / 1000);
					nameValuePairs.add(new BasicNameValuePair("expireTime", String.valueOf(timestamp.getTime())));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			nameValuePairs.add(new BasicNameValuePair("customerId", customerId));
			nameValuePairs.add(new BasicNameValuePair("assetId", productData.getRules().getAssetId()));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			List<PositionsData> positionsDatas = null;
			try {
				positionsDatas = generatePositionsData(response.getEntity().getContent(), customerId);

			} catch (Exception ex) {
				throw ex;
			}

			return positionsDatas;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

	public static void refereshData() throws Exception {/*

		if (!dataAvailable) {
			dataAvailable = true;
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					ProductFetcher productFetcher = new ProductFetcher();
					List<ProductData> proDataList = null;
					List<Rules> rulesList = null;
					try {
						proDataList = productFetcher.getProductDataList();
						rulesList = productFetcher.fetchRules();
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<ProductData> productDataList = new ArrayList<ProductData>();
					if (rulesList != null) {
						if (rulesList.size() > 0) {
							Iterator<Rules> ruleItreator = rulesList.iterator();
							while (ruleItreator.hasNext()) {

								Iterator<ProductData> productIterator = proDataList.iterator();
								Rules ruleData = ruleItreator.next();
								String startTime = ruleData.getStartTime();
								String endTime = ruleData.getEndTime();
								int flagStarttime = 0;
								int flagEndtime = 0;
								if (startTime.toLowerCase().contains("pm")) {
									flagStarttime = 1;
								}
								if (endTime.toLowerCase().contains("pm")) {
									flagEndtime = 1;
								}
								startTime = startTime.replaceAll("am", "").trim();
								startTime = startTime.replaceAll("pm", "").trim();
								endTime = endTime.replaceAll("am", "").trim();
								endTime = endTime.replaceAll("pm", "").trim();
								String sTimes[] = startTime.split(":");
								String eTimes[] = endTime.split(":");

								SimpleDateFormat f = new SimpleDateFormat("HH:mm");
								f.setTimeZone(TimeZone.getTimeZone("Europe/London"));
								String d = f.format(GregorianCalendar.getInstance().getTime());
								String europeTime[] = d.split(":");
								int finalflag = 0;
								try {
									if(flagStarttime == 1)  {
										int i = Integer.parseInt(sTimes[0]) + 12;
										sTimes[0] = i+"";
									}
									if (Integer.parseInt(sTimes[0].trim()) <= Integer.parseInt(europeTime[0])) {

									}else {
										finalflag = 1;
									}
									if (Integer.parseInt(europeTime[0].trim()) <= Integer.parseInt(sTimes[0]) ) {

									}else {
										finalflag = 1;
									}
									if (Integer.parseInt(sTimes[0].trim()) == Integer.parseInt(europeTime[0])) {
										if (Integer.parseInt(sTimes[1].trim()) <= Integer.parseInt(europeTime[1])) {

										}else {
											finalflag = 1;
										}

									}
									if (Integer.parseInt(europeTime[0].trim()) == Integer.parseInt(sTimes[0]) ) {
										if (Integer.parseInt(europeTime[1].trim()) <= Integer.parseInt(sTimes[1]) ) {

										}else {
											finalflag = 1;
										}

									}
								} catch (Exception e) {

								}
								if(finalflag == 0 ) {
								while (productIterator.hasNext()) {
									ProductData productData = productIterator.next();
									if (productData.getId().equals(ruleData.getAssetId())) {
										productData.setRules(ruleData);
										productDataList.add(productData);
										continue;
									}
								}
}
							}
						}
					}
					CountSignals.getCountSingal().readData(productDataList);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					run();
				}
			});
			t.start();

		}
	*/}
}
