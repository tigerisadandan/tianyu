package com.ccthanking.business.yhzg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.yhzg.dao.ZgXmjdDao;
import com.ccthanking.business.yhzg.service.ZgXmjdService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;

@Service
public class ZgXmjdServiceImpl extends Base1ServiceImpl implements ZgXmjdService{
	private ZgXmjdDao zgXmjdDao;

	@Autowired
	@Qualifier("zgXmjdDaoImpl")
	public void setZgXmjdDao(ZgXmjdDao zgXmjdDao) {
		this.zgXmjdDao = zgXmjdDao;
	}

	public String queryCondition(String name) {
		// TODO Auto-generated method stub
		return this.zgXmjdDao.queryCondition(name);
	}


	
}
