package com.demo.data;

public class ProductData {

	private String id;
	private String name;
	private String isTradeable;
	private String symbol;
	private String rate;
	private String tailDigits;
	private String lastUpdated;
	private String tradePrice;
	private String type;
	private String showInHome;
	private String priceTick;
	private String color;
	private String correlation;
	private String trend;
	private String trendType;
	private Rules rules ;
	
	
	public Rules getRules() {
		return rules;
	}
	public void setRules(Rules rules) {
		this.rules = rules;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsTradeable() {
		return isTradeable;
	}
	public void setIsTradeable(String isTradeable) {
		this.isTradeable = isTradeable;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTailDigits() {
		return tailDigits;
	}
	public void setTailDigits(String tailDigits) {
		this.tailDigits = tailDigits;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShowInHome() {
		return showInHome;
	}
	public void setShowInHome(String showInHome) {
		this.showInHome = showInHome;
	}
	public String getPriceTick() {
		return priceTick;
	}
	public void setPriceTick(String priceTick) {
		this.priceTick = priceTick;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCorrelation() {
		return correlation;
	}
	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}
	public String getTrend() {
		return trend;
	}
	public void setTrend(String trend) {
		this.trend = trend;
	}
	public String getTrendType() {
		return trendType;
	}
	public void setTrendType(String trendType) {
		this.trendType = trendType;
	}
	
}
