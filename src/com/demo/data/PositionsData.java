package com.demo.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PositionsData implements Comparable<PositionsData>{

	private String optionId;
	private String amount;
	private String sourcePlatform;
	private String batStrategyId;
	private String position;
	private String customerId;
	private String currency;
	private String rate;
	private String originalRate;
	private String status;
	private String leveratePositionId;
	private String date;
	private String rateUSD;
	private String amountUSD;
	private String id;
	private String isAbuseCancel;
	private String assetId;
	private String assetName;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSourcePlatform() {
		return sourcePlatform;
	}
	public void setSourcePlatform(String sourcePlatform) {
		this.sourcePlatform = sourcePlatform;
	}
	public String getBatStrategyId() {
		return batStrategyId;
	}
	public void setBatStrategyId(String batStrategyId) {
		this.batStrategyId = batStrategyId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getOriginalRate() {
		return originalRate;
	}
	public void setOriginalRate(String originalRate) {
		this.originalRate = originalRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLeveratePositionId() {
		return leveratePositionId;
	}
	public void setLeveratePositionId(String leveratePositionId) {
		this.leveratePositionId = leveratePositionId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRateUSD() {
		return rateUSD;
	}
	public void setRateUSD(String rateUSD) {
		this.rateUSD = rateUSD;
	}
	public String getAmountUSD() {
		return amountUSD;
	}
	public void setAmountUSD(String amountUSD) {
		this.amountUSD = amountUSD;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsAbuseCancel() {
		return isAbuseCancel;
	}
	public void setIsAbuseCancel(String isAbuseCancel) {
		this.isAbuseCancel = isAbuseCancel;
	}
	@Override
	public int compareTo(PositionsData positionsData) {

		try{
			Date currentDate = simpleDateFormat.parse(positionsData.getDate());
			Date defaultData = simpleDateFormat.parse(this.getDate());
			return currentDate.compareTo(defaultData);
		}catch(ParseException pe){
			
		}
		return 0;
	}
	
/*	public static Comparator<PositionsData> FruitNameComparator = new Comparator<PositionsData>() {

		public int compare(PositionsData positionsData, PositionsData positionsData1) {

			try{
				Date currentDate = simpleDateFormat.parse(positionsData.getDate());
				Date defaultData = simpleDateFormat.parse(this.getDate());
				return currentDate.compareTo(defaultData);
			}catch(ParseException pe){
				
			}
			return 0;
		}

	};
*/}
