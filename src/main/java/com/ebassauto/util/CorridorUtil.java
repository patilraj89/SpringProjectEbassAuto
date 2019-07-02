package com.ebassauto.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebassauto.pojo.CalendarDetails;




/**

@Author Pradip Patil

*
*/
public class CorridorUtil {
	
	public static List<String> getPreviousWorkingDateFromToday(List<CalendarDetails> calendarDetailListDate,int count,Date todayDate) {

		List<String> previousDates = new ArrayList<String>();
		
		Map<String,CalendarDetails> dateMap = getCalendarDate( calendarDetailListDate);
		int index=1;
		while(previousDates.size()<count) {
			Calendar cal  = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -index);
			Date preDate=cal.getTime();
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
			if(dateMap.containsKey(sdf.format(preDate)) && dateMap.get(sdf.format(preDate)).getHolidayFlag()==1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate = dateFormat.format(preDate); 
				previousDates.add(strDate);
				
			}
			index++;
		}

		return previousDates;
	}
	
	public static Map<String,CalendarDetails> getCalendarDate(List<CalendarDetails> calendarDetailListDate){
		Map<String,CalendarDetails> dateMap = new HashMap<String, CalendarDetails>();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		
		for(CalendarDetails calDate:calendarDetailListDate ) {
			dateMap.put(sdf.format(calDate.getlDate()), calDate);
		}	
		return dateMap;
	}
	
	
	  public static String getStringDt(Date dt){		 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate =dateFormat.format(dt);
		return strDate;
		}
	 
	
	/*
	 * public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	 * return dateToConvert.toInstant() .atZone(ZoneId.systemDefault())
	 * .toLocalDate(); }
	 */
}

