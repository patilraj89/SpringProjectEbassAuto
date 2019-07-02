package com.ebassauto.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ebassauto.pojo.CalendarDetails;
import com.ebassauto.pojo.CorridorDetails;
import com.ebassauto.pojo.Tenor;


/**

@Author Pradip Patil

*
*/
public interface ICorridorService {
	
	List<CorridorDetails> executeCorridor(List<CalendarDetails> calendardateList,List<Tenor> tenorList,Date inputDate) throws ParseException;

}

