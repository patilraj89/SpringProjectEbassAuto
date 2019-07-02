package com.ebassauto.pojo;

/**

@Author Pradip Patil

*
*/
public class Tenor {
	
	private String benchmark;
	private String tenor;
	private String prev;
	private String next;
	private int settelementDateRangeStart;
	private int settelementDateRangeEnd;
	private int corridorLowerLimit;
	private int corridorUpperLimit;
	private String corridorUnits;
	private String tenorDuration;
	private int ruleType;
	public String getBenchmark() {
		return benchmark;
	}
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public int getSettelementDateRangeStart() {
		return settelementDateRangeStart;
	}
	public void setSettelementDateRangeStart(int settelementDateRangeStart) {
		this.settelementDateRangeStart = settelementDateRangeStart;
	}
	public int getSettelementDateRangeEnd() {
		return settelementDateRangeEnd;
	}
	public void setSettelementDateRangeEnd(int settelementDateRangeEnd) {
		this.settelementDateRangeEnd = settelementDateRangeEnd;
	}
	public int getCorridorLowerLimit() {
		return corridorLowerLimit;
	}
	public void setCorridorLowerLimit(int corridorLowerLimit) {
		this.corridorLowerLimit = corridorLowerLimit;
	}
	public int getCorridorUpperLimit() {
		return corridorUpperLimit;
	}
	public void setCorridorUpperLimit(int corridorUpperLimit) {
		this.corridorUpperLimit = corridorUpperLimit;
	}
	public String getCorridorUnits() {
		return corridorUnits;
	}
	public void setCorridorUnits(String corridorUnits) {
		this.corridorUnits = corridorUnits;
	}
	public String getTenorDuration() {
		return tenorDuration;
	}
	public void setTenorDuration(String tenorDuration) {
		this.tenorDuration = tenorDuration;
	}
	public int getRuleType() {
		return ruleType;
	}
	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}
	@Override
	public String toString() {
		return "Tenor [benchmark=" + benchmark + ", tenor=" + tenor + ", prev=" + prev + ", next=" + next
				+ ", settelementDateRangeStart=" + settelementDateRangeStart + ", settelementDateRangeEnd="
				+ settelementDateRangeEnd + ", corridorLowerLimit=" + corridorLowerLimit + ", corridorUpperLimit="
				+ corridorUpperLimit + ", corridorUnits=" + corridorUnits + ", tenorDuration=" + tenorDuration
				+ ", ruleType=" + ruleType + "]";
	}
	
	

}

