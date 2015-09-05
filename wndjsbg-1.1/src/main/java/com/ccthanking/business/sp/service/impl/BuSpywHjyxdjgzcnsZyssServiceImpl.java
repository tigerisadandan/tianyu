/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsZyssService.java
 * 创建日期： 2014-06-16 上午 09:33:02
 * 功能：    接口：环境影响登记告知承诺主要设施
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-16 上午 09:33:02  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcnsZyssDao;
import com.ccthanking.business.sp.service.BuSpywHjyxdjgzcnsZyssService;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsNrgmVO;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsZyssVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywHjyxdjgzcnsZyssService.java </p>
 * <p> 功能：环境影响登记告知承诺主要设施 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsZyssService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-16
 * 
 */

@Service
public class BuSpywHjyxdjgzcnsZyssServiceImpl extends Base1ServiceImpl<BuSpywHjyxdjgzcnsZyssVO, String> implements BuSpywHjyxdjgzcnsZyssService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsZyssServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_HJYXDJGZCNS_ZYSS;
    
    private BuSpywHjyxdjgzcnsZyssDao buSpywHjyxdjgzcnsZyssDao;


	@Autowired
	@Qualifier("buSpywHjyxdjgzcnsZyssDaoImpl")
	public void setBuSpywHjyxdjgzcnsZyssDao(BuSpywHjyxdjgzcnsZyssDao buSpywHjyxdjgzcnsZyssDao) {
		this.buSpywHjyxdjgzcnsZyssDao = buSpywHjyxdjgzcnsZyssDao;
	}
    // @Override
	 public List<Map<String, String>> find(String id) throws Exception {
			
			List<Map<String, String>> list=buSpywHjyxdjgzcnsZyssDao.find(id);
	        
	        return list;

	    }
	 public String queryCondition(String json) throws Exception {
		 
		 	User user = ActionContext.getCurrentUserInThread();
	   		BuSpywHjyxdjgzcnsZyssVO vo=new BuSpywHjyxdjgzcnsZyssVO();
	 		String domresult = null;
	 		try {
	 			domresult = buSpywHjyxdjgzcnsZyssDao.queryCondition(json);
	 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据查询成功", user, "", "");

	 		} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据查询失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据查询失败,请联系相关人员处理");
			} finally {
			}
	     	  return domresult;
	    	

	    }
	 
	 public String queryZyssUpdate(String id) throws Exception {
	     
		 	User user = ActionContext.getCurrentUserInThread();
	   		BuSpywHjyxdjgzcnsZyssVO vo=new BuSpywHjyxdjgzcnsZyssVO();
	 		String domresult = null;
	 		try {
	 			domresult = buSpywHjyxdjgzcnsZyssDao.queryZyssUpdate(id);
	 			LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-更改操作-子表主要设施-数据查询成功", user, "", "");

	 		} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-更改操作-子表主要设施-数据查询失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-更改操作-子表主要设施-数据查询失败,请联系相关人员处理");
			} finally {
			}
	     	  return domresult;
		 	

		 }
	 public String insert(String json) throws Exception {

		 	User user = ActionContext.getCurrentUserInThread();
	   		BuSpywHjyxdjgzcnsZyssVO vo=new BuSpywHjyxdjgzcnsZyssVO();
	 		String domresult = null;
	 		try {
	 			domresult = buSpywHjyxdjgzcnsZyssDao.insert(json);
	 			LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据新增成功", user, "", "");

	 		} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据新增失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据新增失败,请联系相关人员处理");
			} finally {
			}
	     	  return domresult;
	    	

	    }

	 public String update(String json) throws Exception {

		 	User user = ActionContext.getCurrentUserInThread();
	   		BuSpywHjyxdjgzcnsZyssVO vo=new BuSpywHjyxdjgzcnsZyssVO();
	 		String domresult = null;
	 		try {
	 			domresult = buSpywHjyxdjgzcnsZyssDao.update(json);
	 			LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据更改成功", user, "", "");

	 		} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据更改失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据更改失败,请联系相关人员处理");
			} finally {
			}
	     	  return domresult;
	    
	    }

	    public String delete(String json) throws Exception {
	    	
	    	User user = ActionContext.getCurrentUserInThread();
	   		BuSpywHjyxdjgzcnsZyssVO vo=new BuSpywHjyxdjgzcnsZyssVO();
	 		String domresult = null;
	 		try {
	 			domresult = null;
	 			LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据删除成功", user, "", "");

	 		} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcnszyss_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
					user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据删除失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表主要设施-数据删除失败,请联系相关人员处理");
			} finally {
			}
	     	  return domresult;

		}
    
}
