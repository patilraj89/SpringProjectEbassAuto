package com.ebassauto.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ebassauto.pojo.Tenor;
import com.ebassauto.rowmpr.TenorDataMapper;


/**

@Author Pradip Patil

*
*/
@Component
public class TenorDaoImpl implements ITenorDao{
	
	JdbcTemplate jdbcTemplate;
	
	private final String LIST_ALL_TENOR_DATA="select * from tenor";
	
	@Autowired
	public TenorDaoImpl(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}

	public List<Tenor> getAllTenorData() {
		return jdbcTemplate.query(LIST_ALL_TENOR_DATA, new TenorDataMapper());
	}

}

