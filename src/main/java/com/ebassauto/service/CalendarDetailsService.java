package com.ebassauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebassauto.dao.ICalendarDao;
import com.ebassauto.pojo.CalendarDetails;

@Service
public class CalendarDetailsService implements ICalendarDetailsService{
	
	@Autowired
	private ICalendarDao iCalDao;

	@Override
	public List<CalendarDetails> getCalendarDetails() {
		List<CalendarDetails> calDetails=iCalDao.getCalendarDetails();
		return calDetails;
	}

}
