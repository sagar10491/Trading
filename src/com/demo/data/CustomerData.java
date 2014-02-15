package com.demo.data;

import java.io.Serializable;


public class CustomerData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5360497233352405637L;
	private int id;
	private String firstName = null;
	private String lastName = null;
	private String gender = null;
	private String email = null;
	private String password = null;
	private String cellPhone = null;
	private String phone = null;
	private String fax = null;
	private String specialAccountNumber;
	private String personalId ;
	private String firstDepositDate ;
	private String counrty = "india";
	private String registeredCountry = "Any";
	private String city ;
	private String state ;
	private String newCustomer = "1";
	private String street ;
	private String houseNumber ;
	private String apartmentNumber ;
	private String risk = "low";
	private String verification ="None";
	private String referenceLink ;
	private String siteLanguage ;
	private String timezone            = null ; 
	private String promotionalEmails 	= null ;	
	private String tradingEmails       = null ; 
	private String employeeInChargeId  = null ; 
	private String employeeInChargeName= null ; 
	private String type                = null ; 
	private String approvesEmailAds    = null ; 
	private String campaignId          = null ; 
	private String campaignName        = null ; 
	private String birthday            = null ; 
	private String subCampaignId       = null ; 
	private String subCampaignParam    = null ; 
	private String regTime             = null ; 
	private String regTimeFormatted    = null ; 
	private String lastTimeActive      = null ; 
	private String lastUpdate          = null ; 
	private String regStatus           = null ; 
	private String postCode           = null ; 
	private String accountBalance      = null ; 
	private String saleStatus          = null ; 
	private String leadStatus          = null ; 
	private String regulateStatus     = null ; 
	private String isSuspended         = null ; 
	private String isBlackList         = null ; 
	private String pnl                 = null ; 
	private String currencySign        = null ; 
	private String currency            = null ; 
	private String isLead              = null ; 
	private String a_aid      			= null ; 
	private String isDemo              = null ; 
	private String group               = null ; 
	private String potential           = null ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getSpecialAccountNumber() {
		return specialAccountNumber;
	}
	public void setSpecialAccountNumber(String specialAccountNumber) {
		this.specialAccountNumber = specialAccountNumber;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	public String getFirstDepositDate() {
		return firstDepositDate;
	}
	public void setFirstDepositDate(String firstDepositDate) {
		this.firstDepositDate = firstDepositDate;
	}
	public String getCounrty() {
		return counrty;
	}
	public void setCounrty(String counrty) {
		this.counrty = counrty;
	}
	public String getRegisteredCountry() {
		return registeredCountry;
	}
	public void setRegisteredCountry(String registeredCountry) {
		this.registeredCountry = registeredCountry;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(String newCustomer) {
		this.newCustomer = newCustomer;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public String getReferenceLink() {
		return referenceLink;
	}
	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}
	public String getSiteLanguage() {
		return siteLanguage;
	}
	public void setSiteLanguage(String siteLanguage) {
		this.siteLanguage = siteLanguage;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getPromotionalEmails() {
		return promotionalEmails;
	}
	public void setPromotionalEmails(String promotionalEmails) {
		this.promotionalEmails = promotionalEmails;
	}
	public String getTradingEmails() {
		return tradingEmails;
	}
	public void setTradingEmails(String tradingEmails) {
		this.tradingEmails = tradingEmails;
	}
	public String getEmployeeInChargeId() {
		return employeeInChargeId;
	}
	public void setEmployeeInChargeId(String employeeInChargeId) {
		this.employeeInChargeId = employeeInChargeId;
	}
	public String getEmployeeInChargeName() {
		return employeeInChargeName;
	}
	public void setEmployeeInChargeName(String employeeInChargeName) {
		this.employeeInChargeName = employeeInChargeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApprovesEmailAds() {
		return approvesEmailAds;
	}
	public void setApprovesEmailAds(String approvesEmailAds) {
		this.approvesEmailAds = approvesEmailAds;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSubCampaignId() {
		return subCampaignId;
	}
	public void setSubCampaignId(String subCampaignId) {
		this.subCampaignId = subCampaignId;
	}
	public String getSubCampaignParam() {
		return subCampaignParam;
	}
	public void setSubCampaignParam(String subCampaignParam) {
		this.subCampaignParam = subCampaignParam;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getRegTimeFormatted() {
		return regTimeFormatted;
	}
	public void setRegTimeFormatted(String regTimeFormatted) {
		this.regTimeFormatted = regTimeFormatted;
	}
	public String getLastTimeActive() {
		return lastTimeActive;
	}
	public void setLastTimeActive(String lastTimeActive) {
		this.lastTimeActive = lastTimeActive;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getLeadStatus() {
		return leadStatus;
	}
	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}
	public String getRegulateStatus() {
		return regulateStatus;
	}
	public void setRegulateStatus(String regulateStatus) {
		this.regulateStatus = regulateStatus;
	}
	public String getIsSuspended() {
		return isSuspended;
	}
	public void setIsSuspended(String isSuspended) {
		this.isSuspended = isSuspended;
	}
	public String getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}
	public String getPnl() {
		return pnl;
	}
	public void setPnl(String pnl) {
		this.pnl = pnl;
	}
	public String getCurrencySign() {
		return currencySign;
	}
	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIsLead() {
		return isLead;
	}
	public void setIsLead(String isLead) {
		this.isLead = isLead;
	}
	public String getA_aid() {
		return a_aid;
	}
	public void setA_aid(String a_aid) {
		this.a_aid = a_aid;
	}
	public String getIsDemo() {
		return isDemo;
	}
	public void setIsDemo(String isDemo) {
		this.isDemo = isDemo;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPotential() {
		return potential;
	}
	public void setPotential(String potential) {
		this.potential = potential;
	} 
	
}
