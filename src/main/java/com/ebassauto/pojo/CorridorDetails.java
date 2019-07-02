package com.ebassauto.pojo;

import java.util.Date;

/**

@Author Pradip Patil

*
*/
public class CorridorDetails {
	
	private Date publicationDate;
	private Date tradeDate;
	private String tenor;
	private Date settlementDate;
	private Date theoriticalMaturityDate;
	private Date corridorStartDate;
	private Date corridorEndDate;
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public Date getTheoriticalMaturityDate() {
		return theoriticalMaturityDate;
	}
	public void setTheoriticalMaturityDate(Date theoriticalMaturityDate) {
		this.theoriticalMaturityDate = theoriticalMaturityDate;
	}
	public Date getCorridorStartDate() {
		return corridorStartDate;
	}
	public void setCorridorStartDate(Date corridorStartDate) {
		this.corridorStartDate = corridorStartDate;
	}
	public Date getCorridorEndDate() {
		return corridorEndDate;
	}
	public void setCorridorEndDate(Date corridorEndDate) {
		this.corridorEndDate = corridorEndDate;
	}
	@Override
	public String toString() {
		return "CorridorDetails [publicationDate=" + publicationDate + ", tradeDate=" + tradeDate + ", tenor=" + tenor
				+ ", settlementDate=" + settlementDate + ", theoriticalMaturityDate=" + theoriticalMaturityDate
				+ ", corridorStartDate=" + corridorStartDate + ", corridorEndDate=" + corridorEndDate + "]";
	}
	
	

}

