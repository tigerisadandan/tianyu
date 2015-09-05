package com.ccthanking.business.dtgl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.dao.QueryZGDDao;
import com.ccthanking.business.dtgl.service.QueryZGDService;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

@Service
public class QueryZGDServiceImpl implements QueryZGDService {
	private static Logger logger = LoggerFactory
			.getLogger(QueryZGDServiceImpl.class);
	
	private QueryZGDDao queryZGDDao;

	public String queryZGD() {
		String domresult = "";
		try {
			domresult = queryZGDDao.queryZGD();
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error("整改单查询{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
		}		
		return domresult;
	}

	public QueryZGDDao getQueryZGDDao() {
		return queryZGDDao;
	}

	@Autowired
	public void setQueryZGDDao(QueryZGDDao queryZGDDao) {
		this.queryZGDDao = queryZGDDao;
	}
	

}
