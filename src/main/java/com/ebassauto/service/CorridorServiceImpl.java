package com.ebassauto.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebassauto.dao.ICorridorDAO;
import com.ebassauto.pojo.CalendarDetails;
import com.ebassauto.pojo.CorridorDetails;
import com.ebassauto.pojo.Tenor;
import com.ebassauto.util.CorridorUtil;




/**

@Author Pradip Patil

*
*/
@Service
/* @ComponentScan({ "com.ebass.corridor" }) */
public class CorridorServiceImpl implements ICorridorService{
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/* @Autowired(required=true) */
	@Autowired
	ICorridorDAO corridorDao;
	
	
	public void setCorridorDao(ICorridorDAO corridorDao) {
		this.corridorDao = corridorDao;
	}

	static SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public List<CorridorDetails> executeCorridor(List<CalendarDetails> calendardateList, List<Tenor> tenorList, Date inputDate)throws ParseException{
		//Map<String,CalendarDetails> holidayCheck=CorridorUtil.getCalendarDate(calendardateList);
		/*
		 * if(holidayCheck.containsKey(localDateFormat.format(inputDate)) &&
		 * holidayCheck.get(localDateFormat.format(inputDate)).getHolidayFlag()==0) {
		 * System.out.println("Todays Date is holiday...so we cant process corridor");
		 * return "Todays Date is holiday...so we cant process corridor"; }
		 */
		List<CorridorDetails> corridorList= new ArrayList<CorridorDetails>();
		for(Tenor tnr:tenorList) {
			for(int i=tnr.getSettelementDateRangeStart();i<=tnr.getSettelementDateRangeEnd();i++) {
				//System.out.println("Corridor List="+i);
				CorridorDetails corridorDetails=calculateCorridorDetails(tnr,calendardateList,i,inputDate);
				corridorList.add(corridorDetails);
				
			}
		}
		//corridorDao.insertCorridor(corridorList);
		
		return corridorList;
	}
	
	private static CorridorDetails calculateCorridorDetails(Tenor tenor,List<CalendarDetails> calendardateList,int settelementCtr,Date inputDate)throws ParseException{
		//System.out.println("Corridor Called");
		
		CorridorDetails corridorDetails=new CorridorDetails();
		List<String> tradeDateList = CorridorUtil.getPreviousWorkingDateFromToday(calendardateList, 1,inputDate);
		List<String> settelmentDate=getSettelementDate(localDateFormat.parse(tradeDateList.get(tradeDateList.size()-1)), settelementCtr, calendardateList);
		List<String> maturityDt=getMaturityDate(localDateFormat.parse(settelmentDate.get(settelmentDate.size()-1)),tenor.getTenorDuration(),calendardateList);
		List<String> corridorStrt=getCorridorStartDt(calendardateList,tenor.getCorridorLowerLimit(),localDateFormat.parse(maturityDt.get(maturityDt.size()-1)));
		List<String> corridorEnd=getCorridorEndDt(calendardateList,tenor.getCorridorUpperLimit(),localDateFormat.parse(maturityDt.get(maturityDt.size()-1)));
		
		corridorDetails.setTradeDate(localDateFormat.parse(tradeDateList.get(tradeDateList.size()-1)));
		corridorDetails.setTenor(tenor.getTenor());
		corridorDetails.setPublicationDate(inputDate);
		corridorDetails.setSettlementDate(localDateFormat.parse(settelmentDate.get(settelmentDate.size()-1)));
		corridorDetails.setTheoriticalMaturityDate(localDateFormat.parse(maturityDt.get(maturityDt.size()-1)));
		corridorDetails.setCorridorStartDate(localDateFormat.parse(corridorStrt.get(corridorStrt.size()-1)));
		corridorDetails.setCorridorEndDate(localDateFormat.parse(corridorEnd.get(corridorEnd.size()-1)));
		
		return corridorDetails;
	}
	
	private static List<String> getMaturityDate(Date settlementDt,String tenorDuration,List<CalendarDetails> calendardateList)throws ParseException{
		List<String> maturityDt=new ArrayList<String>();
		if("7D".equals(tenorDuration)) {
			maturityDt=getMaturityByTenorDate(settlementDt,7,calendardateList);
		}
		if("1M".equals(tenorDuration)) {
			maturityDt=getMaturityByTenorDateRuleTwo(settlementDt,1,calendardateList);
		}
		if("3M".equals(tenorDuration)) {
			maturityDt=getMaturityByTenorDateRuleTwo(settlementDt,3,calendardateList);
		}
		
		if("6M".equals(tenorDuration)) {
			maturityDt=getMaturityByTenorDateRuleTwo(settlementDt,6,calendardateList);
		}
		
		if("12M".equals(tenorDuration)) {
			maturityDt=getMaturityByTenorDateRuleTwo(settlementDt,12,calendardateList);
		}
		
		return maturityDt;
		
	}
	
private static List<String> getMaturityByTenorDateRuleTwo(Date settlementDt,int tenorDurationInMonth,List<CalendarDetails> calendarData) throws ParseException{
		
		List<String> maturityDate = new ArrayList<String>();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		Map<String,CalendarDetails> dateMap = getCalendarDate(calendarData);
		
		Date lastDateOfMonth=getLastDateOfMonthByInputDate(settlementDt);
		if(settlementDt.equals(lastDateOfMonth)) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(settlementDt);
			cal.add(Calendar.MONTH, tenorDurationInMonth);
			Date tDate=cal.getTime();
			
			Calendar calFirst = Calendar.getInstance();
	        calFirst.setTime(tDate);
	        calFirst.set(Calendar.DAY_OF_MONTH, 1);
	        Date firstDt=calFirst.getTime();
	        
	        if(tDate.equals(firstDt)) {
	        	Calendar previous = Calendar.getInstance();
	        	previous.setTime(tDate);
	        	previous.set(Calendar.DATE, -1);
		        Date previousDate=previous.getTime();
		        List<String> previousMonthWkgDt=getPreviousWorkingDateFromToday(calendarData,1,previousDate);
				maturityDate.add(previousMonthWkgDt.get(previousMonthWkgDt.size()-1));
	        }else {
	        	Date lastDateOfNextMonth=getLastDateOfMonthByInputDate(tDate);
				List<String> nextMonthWkgDt=getPreviousWorkingDateFromToday(calendarData,1,lastDateOfNextMonth);
				maturityDate.add(nextMonthWkgDt.get(nextMonthWkgDt.size()-1));
	        }
			
			
			
		}else {
			
			Calendar calMonth  = Calendar.getInstance();
			calMonth.setTime(settlementDt);
			calMonth.add(Calendar.MONTH, tenorDurationInMonth);
			Date calMonthDt=calMonth.getTime();
			
			
			if(tenorDurationInMonth==1 && dateMap.containsKey(sdf.format(calMonthDt)) && dateMap.get(sdf.format(calMonthDt)).getHolidayFlag()==1) {
				maturityDate.add(sdf.format(calMonthDt));
				return maturityDate;
			}
			if(tenorDurationInMonth==3 && dateMap.containsKey(sdf.format(calMonthDt)) && dateMap.get(sdf.format(calMonthDt)).getHolidayFlag()==1) {
				maturityDate.add(sdf.format(calMonthDt));
				return maturityDate;
			}
			
			if(tenorDurationInMonth==6 && dateMap.containsKey(sdf.format(calMonthDt)) && dateMap.get(sdf.format(calMonthDt)).getHolidayFlag()==1) {
				maturityDate.add(sdf.format(calMonthDt));
				return maturityDate;
			}
			if(tenorDurationInMonth==12 && dateMap.containsKey(sdf.format(calMonthDt)) && dateMap.get(sdf.format(calMonthDt)).getHolidayFlag()==1) {
				maturityDate.add(sdf.format(calMonthDt));
				return maturityDate;
			}
			
			int index=1;			
			while(maturityDate.size()<1) {
				Calendar cal  = Calendar.getInstance();
				cal.setTime(calMonthDt);
				cal.add(Calendar.DATE, index);
				Date tDate=cal.getTime();
				
				if(dateMap.containsKey(sdf.format(tDate)) && dateMap.get(sdf.format(tDate)).getHolidayFlag()==1) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
					String sDate = dateFormat.format(tDate); 
					maturityDate.add(sDate);
					
				}
				index++;
			}
			
			Date maturityDateRes=new SimpleDateFormat("yyyy-MM-dd").parse(maturityDate.get(0));		
			int matDateMonth=getmonthOfDate(maturityDateRes);
			int calDtM=getmonthOfDate(calMonthDt);
			if(matDateMonth==calDtM) {
				return maturityDate;
			}else {	
				List<String> mtDt=getMaturityInSameMonth(maturityDate,calMonthDt,dateMap,sdf);
				maturityDate.addAll(mtDt);
			}
			
		}		
		return maturityDate;
	}

	private static List<String> getMaturityInSameMonth(List<String> maturityDate,Date calMonthDt,Map<String,CalendarDetails> dateMap,SimpleDateFormat sdf){
		maturityDate.clear();
		int id=0;		
		while(maturityDate.size()<1) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(calMonthDt);
			cal.add(Calendar.DATE, -id);
			Date tDate=cal.getTime();
			
			if(dateMap.containsKey(sdf.format(tDate)) && dateMap.get(sdf.format(tDate)).getHolidayFlag()==1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String sDate = dateFormat.format(tDate); 
				maturityDate.add(sDate);
				
			}
			id++;
		}
		
		return maturityDate;
	}

	private static int getmonthOfDate(Date dt) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return Integer.parseInt(dateFormat.format(dt));
	}

	private static List<String> getPreviousWorkingDateFromToday(List<CalendarDetails> calendarDetailListDate,int count,Date todayDate) {

	List<String> previousDates = new ArrayList<String>();
	
	Map<String,CalendarDetails> dateMap = getCalendarDate( calendarDetailListDate);
	
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	if(dateMap.containsKey(sdf.format(todayDate)) && dateMap.get(sdf.format(todayDate)).getHolidayFlag()==1) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(todayDate); 
		previousDates.add(strDate);
		
	}
	
	int index=1;
	while(previousDates.size()<count) {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.DATE, -index);
		Date preDate=cal.getTime();
		
		if(dateMap.containsKey(sdf.format(preDate)) && dateMap.get(sdf.format(preDate)).getHolidayFlag()==1) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(preDate); 
			previousDates.add(strDate);
			
		}
		index++;
	}

	return previousDates;
}

	private static Date getLastDateOfMonthByInputDate(Date settlementDt) throws ParseException{
		Date lastDateOfMonth;
		
		/*Calendar calFirst = Calendar.getInstance();
        calFirst.setTime(settlementDt);
        calFirst.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDt=calFirst.getTime();*/	
		
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(settlementDt);
		calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        lastDateOfMonth=calendar.getTime();
		return lastDateOfMonth;
		
	}
	
	public static List<String> getCorridorEndDt(List<CalendarDetails> calendarDetailListDate,int corridorUpperLimit,Date maturityDt) {

		List<String> corridorEnd = new ArrayList<String>();
		
		if(corridorUpperLimit==0) {
			SimpleDateFormat sdf =  new SimpleDateFormat(DATE_FORMAT);
			corridorEnd.add(sdf.format(maturityDt));
			return corridorEnd;
		}
		
		Map<String,CalendarDetails> dateMap = getCalendarDate(calendarDetailListDate);
		
		
		
		int index=1;
		while(corridorEnd.size()<corridorUpperLimit) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(maturityDt);
			cal.add(Calendar.DATE, index);
			Date preDate=cal.getTime();
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
			if(dateMap.containsKey(sdf.format(preDate)) && dateMap.get(sdf.format(preDate)).getHolidayFlag()==1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate = dateFormat.format(preDate); 
				corridorEnd.add(strDate);
				
			}
			index++;
			
		}
		return corridorEnd;
	}
	
	
	private static List<String> getCorridorStartDt(List<CalendarDetails> calendarDetailListDate,int corridorLowerLimit,Date maturityDt) {

		List<String> corridorSt = new ArrayList<String>();
		
		if(corridorLowerLimit==0) {
			SimpleDateFormat sdf =  new SimpleDateFormat(DATE_FORMAT);
			corridorSt.add(sdf.format(maturityDt));
			return corridorSt;
		}
		
		Map<String,CalendarDetails> dateMap = getCalendarDate(calendarDetailListDate);
		int index=1;
		while(corridorSt.size()<Math.abs(corridorLowerLimit)) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(maturityDt);
			cal.add(Calendar.DATE, -index);
			Date preDate=cal.getTime();
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
			if(dateMap.containsKey(sdf.format(preDate)) && dateMap.get(sdf.format(preDate)).getHolidayFlag()==1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate = dateFormat.format(preDate); 
				corridorSt.add(strDate);
				
			}
			index++;
			
		}
		return corridorSt;
	}	
	
	private static List<String> getMaturityByTenorDate(Date settlementDt,int tenorDuration,List<CalendarDetails> calendarData) throws ParseException{
		
		List<String> maturityDate = new ArrayList<String>();
		
		Map<String,CalendarDetails> dateMap = getCalendarDate(calendarData);
		int index=tenorDuration;
		int count=1;
		while(maturityDate.size()<count) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(settlementDt);
			cal.add(Calendar.DATE, index);
			Date tDate=cal.getTime();
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
			if(dateMap.containsKey(sdf.format(tDate)) && dateMap.get(sdf.format(tDate)).getHolidayFlag()==1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String sDate = dateFormat.format(tDate); 
				maturityDate.add(sDate);
				
			}
			index++;
		}
		return maturityDate;
	}
	
	
	private static List<String> getSettelementDate(Date tradeDate,int settlementRange,List<CalendarDetails> calendarData) throws ParseException{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		List<String> settlementDate = new ArrayList<String>();
		if(settlementRange==0) {
			settlementDate.add(sdf.format(tradeDate));
			return settlementDate;
		}
		Map<String,CalendarDetails> dateMap = getCalendarDate(calendarData);
		int index=1;
		int count=settlementRange;
		while(settlementDate.size()<Math.abs(count)) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(tradeDate);
			cal.add(Calendar.DATE, index);
			Date tDate=cal.getTime();
			
			if(dateMap.containsKey(sdf.format(tDate)) && dateMap.get(sdf.format(tDate)).getHolidayFlag()==1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String sDate = dateFormat.format(tDate); 
				settlementDate.add(sDate);
				
			}
			index++;
		}
		return settlementDate;
	}

public static Map<String,CalendarDetails> getCalendarDate(List<CalendarDetails> calendarDetailListDate){
	Map<String,CalendarDetails> dateMap = new HashMap<String,CalendarDetails>();
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	for(CalendarDetails calDate:calendarDetailListDate ) {
		dateMap.put(sdf.format(calDate.getlDate()), calDate);
	}	
	return dateMap;
}


}

