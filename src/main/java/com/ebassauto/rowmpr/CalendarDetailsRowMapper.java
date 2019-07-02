package com.ebassauto.rowmpr;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ebassauto.pojo.CalendarDetails;

public class CalendarDetailsRowMapper implements RowMapper<CalendarDetails>{

	@Override
	public CalendarDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		CalendarDetails calDetails=new CalendarDetails();
		calDetails.setId(rs.getInt("id"));
		calDetails.setlDate(rs.getDate("date"));
		calDetails.setDay(rs.getString("day"));
		calDetails.setHolidayDesc(rs.getString("holidaydesc"));
		calDetails.setHolidayFlag(rs.getInt("holidayflag"));
		return calDetails;
	}

}
