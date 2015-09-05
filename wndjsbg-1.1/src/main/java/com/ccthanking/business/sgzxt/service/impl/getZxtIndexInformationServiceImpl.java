/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgzxt.service.getZxtIndexInformationService.java
 * 创建日期： 2015-08-13 下午 03:19:20
 * 功能：    接口：施工子系统首页信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-08-13 下午 03:19:20  老虎是只耽耽   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgzxt.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.sgzxt.dao.getZxtIndexInformationDao;
import com.ccthanking.business.sgzxt.service.getZxtIndexInformationService;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> getZxtIndexInformationService.java </p>
 * <p> 功能：施工子系统首页信息 </p>
 *
 * <p><a href="getZxtIndexInformationService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">老虎是只耽耽</a>
 * @version 0.1
 * @since 2015-08-13
 * 
 */

@Service
public class getZxtIndexInformationServiceImpl  implements getZxtIndexInformationService {

	private static Logger logger = LoggerFactory.getLogger(getZxtIndexInformationServiceImpl.class);
    
    private getZxtIndexInformationDao getZxtIndexInformationDao;

    // @Override
    public String queryCondition(String json,String GongCheng_UID) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = getZxtIndexInformationDao.queryCondition(json, GongCheng_UID);

            LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("施工子系统首页信息{}", e.getMessage());
			LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
			SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String queryGC_SCORE(String json) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.queryGC_SCORE(json);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    public String queryGC_SCORE_detail(String json) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.queryGC_SCORE_detail(json);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    
    public String querySGRY(String json) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.querySGRY(json);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    public String queryJLRY(String json) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.queryJLRY(json);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    
    public String queryRYXX_detail(String json,String personType) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.queryRYXX_detail(json, personType);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    
    public String queryRYXX_Fileid(String json,String PERSON_UID,String File_type) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.queryRYXX_Fileid(json, PERSON_UID,File_type);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    public String querygc(String json) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = getZxtIndexInformationDao.querygc(json);
    		
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工子系统首页信息>", user, "", "");
    		
    	}catch (DaoException e) {
    		logger.error("施工子系统首页信息{}", e.getMessage());
    		LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工子系统首页信息查询失败", user, "", "");
    		SystemException.handleMessageException("施工子系统首页信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    

	@Autowired
	@Qualifier("getZxtIndexInformationDaoImpl")
	public void setgetZxtIndexInformationDao(getZxtIndexInformationDao getZxtIndexInformationDao) {
		this.getZxtIndexInformationDao = getZxtIndexInformationDao;
	}
    
}
