package com.ebassauto.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ebassauto.pojo.CalendarDetails;
import com.ebassauto.rowmpr.CalendarDetailsRowMapper;

/*@Transactional
@Repository*/
@Component
public class CalendarDao implements ICalendarDao{
	
	/*
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 */

	JdbcTemplate jdbcTemplate;
	
	private final String LIST_ALL_CALENDARDATA="select * from calendardate";
	
	@Autowired
	public CalendarDao(DataSource dataSOurce) {
		jdbcTemplate=new JdbcTemplate(dataSOurce);
	}
	
	@Override
	public List<CalendarDetails> getCalendarDetails() {
		/*
		 * String
		 * calQuery="select id,date,day,holidaydesc,holidayflag from calendardate";
		 * RowMapper<CalendarDetails> rowMp = new CalendarDetailsRowMapper();
		 */
		return jdbcTemplate.query(LIST_ALL_CALENDARDATA, new CalendarDetailsRowMapper());
	}

}
