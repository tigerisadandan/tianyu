package com.ccthanking.business.rygl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.rygl.dao.XcryglDao;
import com.ccthanking.business.rygl.service.XcryglService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;

@Service
public class XcryglServiceImpl extends Base1ServiceImpl implements XcryglService{
	private XcryglDao xcryglDao;

	@Autowired
	@Qualifier("xcryglDaoImpl")
	public void setXcryglDao(XcryglDao xcryglDao) {
		this.xcryglDao = xcryglDao;
	}

	public String queryCondition(String msg) {
		// TODO Auto-generated method stub
		return this.xcryglDao.queryCondition(msg);
	}
	
}
