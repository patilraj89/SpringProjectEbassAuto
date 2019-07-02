package com.ebassauto.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ebassauto.pojo.CorridorDetails;


/**

@Author Pradip Patil

*
*/
@Component
public class CorridorDAOImpl implements ICorridorDAO{

	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CorridorDAOImpl(DataSource dataSOurce) {
		jdbcTemplate=new JdbcTemplate(dataSOurce);
	}
	
	private static final String INSERT_CORRIDOR = "insert into corridor (tradedate,publicationdate,tenor,settlementdate,theoriticalmaturitydate,corridorstartdate,corridorenddate) "
			+ " VALUES(?,?,?,?,?,?,?)";

		
	public boolean insertCorridor(List<CorridorDetails> corridorList) {
		boolean sts=false;
		for (CorridorDetails corridorDetails : corridorList) {
			sts=jdbcTemplate.update(INSERT_CORRIDOR,corridorDetails.getTradeDate(),corridorDetails.getPublicationDate(),
						corridorDetails.getTenor(),corridorDetails.getSettlementDate(),corridorDetails.getTheoriticalMaturityDate(),
						corridorDetails.getCorridorStartDate(),corridorDetails.getCorridorEndDate())>0;
		}
		return sts;
	}

}

