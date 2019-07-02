package com.ebassauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebassauto.dao.ITenorDao;
import com.ebassauto.pojo.Tenor;

@Service
public class TenorService implements ITenorService{

	/* @Autowired(required=true) */
	@Autowired
	private ITenorDao tenorDao;
	
	@Override
	public List<Tenor> getAllTenorData() {
		List<Tenor> tnorLst=tenorDao.getAllTenorData();
		return tnorLst;
	}

}
