package com.ebassauto.rowmpr;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ebassauto.pojo.Tenor;

/**

@Author Pradip Patil

*
*/
public class TenorDataMapper implements RowMapper<Tenor>{
	
	public Tenor mapRow(ResultSet resultSet,int i) throws SQLException{
		Tenor tenor=new Tenor();
		tenor.setBenchmark(resultSet.getString("Benchmark"));
		tenor.setTenor(resultSet.getString("Tenor"));
		tenor.setPrev(resultSet.getString("prev"));
		tenor.setNext(resultSet.getString("next"));
		tenor.setSettelementDateRangeStart(resultSet.getInt("settlementDateRangeStart"));
		tenor.setSettelementDateRangeEnd(resultSet.getInt("settlementDateRangeEnd"));
		tenor.setCorridorLowerLimit(resultSet.getInt("corridorLowerLimit"));
		tenor.setCorridorUpperLimit(resultSet.getInt("corridorUpperLimit"));
		tenor.setCorridorUnits(resultSet.getString("corridorUnits"));
		tenor.setTenorDuration(resultSet.getString("tenorDuration"));
		tenor.setRuleType(resultSet.getInt("ruleType"));
		return tenor;
	}

}

