package com.ebassauto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorridorDetailsPojo {
	
	private String targetDate;
	private String tradeDate;
	private String tenor;
	private String settlementDate;
	private String theoriticalMaturityDate;
	private String corridorStartDate;
	private String corridorEndDate;
	
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getTheoriticalMaturityDate() {
		return theoriticalMaturityDate;
	}
	public void setTheoriticalMaturityDate(String theoriticalMaturityDate) {
		this.theoriticalMaturityDate = theoriticalMaturityDate;
	}
	public String getCorridorStartDate() {
		return corridorStartDate;
	}
	public void setCorridorStartDate(String corridorStartDate) {
		this.corridorStartDate = corridorStartDate;
	}
	public String getCorridorEndDate() {
		return corridorEndDate;
	}
	public void setCorridorEndDate(String corridorEndDate) {
		this.corridorEndDate = corridorEndDate;
	}
	
	

}
