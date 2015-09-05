package com.ccthanking.business.dtgl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.dao.GetIndexInformationDao;
import com.ccthanking.business.dtgl.service.GetIndexInformationService;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;


@Service
public class GetIndexInformationServiceImpl implements GetIndexInformationService {
	private static Logger logger = LoggerFactory
			.getLogger(GetIndexInformationServiceImpl.class);	
	
	private GetIndexInformationDao getIndexInformationDao;
	
	public String getQuanxianService(String userId) {
		String domresult = "";
		try {
			domresult = getIndexInformationDao.getQuanxian(userId);
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error("权限查询{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
		}		
		return domresult;
	}
	
	public String getChartData(String before,String after) {
		String domresult = "";
		try {
			domresult = getIndexInformationDao.getChartData(before,after);
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error("图形数据查询{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
		}		
		return domresult;
	}
	
	public String queryZGD(){
		String domresult = "";
		try {
			domresult = getIndexInformationDao.queryZGD();
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error("整改单信息查询{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
		}		
		return domresult;
	}
	
	
	
	public String queryKaoqingInfo(){
		String domresult = "";
		try {
			domresult = getIndexInformationDao.queryKaoqingInfo();
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error("考勤信息查询{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
		}		
		return domresult;
	}

	public GetIndexInformationDao getGetIndexInformationDao() {
		return getIndexInformationDao;
	}

	@Autowired
	public void setGetIndexInformationDao(GetIndexInformationDao getIndexInformationDao) {
		this.getIndexInformationDao = getIndexInformationDao;
	}


	
}
