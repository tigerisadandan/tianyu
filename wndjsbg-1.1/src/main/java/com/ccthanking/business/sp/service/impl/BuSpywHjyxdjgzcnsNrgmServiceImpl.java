/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsNrgmService.java
 * 创建日期： 2014-06-13 下午 05:53:33
 * 功能：    接口：环境影响登记告知承诺内容规模
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 05:53:33  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcnsNrgmDao;
import com.ccthanking.business.sp.service.BuSpywHjyxdjgzcnsNrgmService;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcns3SpVO;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsNrgmVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywHjyxdjgzcnsNrgmService.java </p>
 * <p> 功能：环境影响登记告知承诺内容规模 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsNrgmService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Service
public class BuSpywHjyxdjgzcnsNrgmServiceImpl extends Base1ServiceImpl<BuSpywHjyxdjgzcnsNrgmVO, String> implements BuSpywHjyxdjgzcnsNrgmService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsNrgmServiceImpl.class);

	@Autowired
	@Qualifier("buSpywHjyxdjgzcnsNrgmDaoImpl")
	public void setBuSpywHjyxdjgzcnsNrgmDao(BuSpywHjyxdjgzcnsNrgmDao buSpywHjyxdjgzcnsNrgmDao) {
		this.buSpywHjyxdjgzcnsNrgmDao = buSpywHjyxdjgzcnsNrgmDao;
	}
    
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_HJYXDJGZCNS_NRGM;
    
    private BuSpywHjyxdjgzcnsNrgmDao buSpywHjyxdjgzcnsNrgmDao;

    // @Override
    
    public List<Map<String, String>> find(String id) throws Exception {
		
		List<Map<String, String>> list=buSpywHjyxdjgzcnsNrgmDao.find(id);
        
        return list;

    }
    public String queryCondition(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
   		BuSpywHjyxdjgzcnsNrgmVO vo=new BuSpywHjyxdjgzcnsNrgmVO();
 		String domresult = null;
 		try {
 			domresult = buSpywHjyxdjgzcnsNrgmDao.queryCondition(json);
 			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据查询成功", user, "", "");

 		} catch (DaoException e) {
			logger.error("表单信息{}", e.getMessage());
			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据查询失败", user, "", "");
			SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据查询失败,请联系相关人员处理");
		} finally {
		}
     	  return domresult;
    	
    }
    public String queryNrgmUpdate(String id) throws Exception {
     
    	User user = ActionContext.getCurrentUserInThread();
   		BuSpywHjyxdjgzcnsNrgmVO vo=new BuSpywHjyxdjgzcnsNrgmVO();
 		String domresult = null;
 		try {
 			domresult = buSpywHjyxdjgzcnsNrgmDao.queryNrgmUpdate(id);
 			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-更改操作-子表内容规模-数据查询成功", user, "", "");

 		} catch (DaoException e) {
			logger.error("表单信息{}", e.getMessage());
			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-更改操作-子表内容规模-数据查询失败", user, "", "");
			SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-更改操作-子表内容规模-数据查询失败,请联系相关人员处理");
		} finally {
		}
     	  return domresult;
 	

 }

    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywHjyxdjgzcnsNrgmVO vo=new BuSpywHjyxdjgzcnsNrgmVO();
 		String domresult = null;
 		try {
 			domresult = buSpywHjyxdjgzcnsNrgmDao.insert(json);
 			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据新增成功", user, "", "");

 		} catch (DaoException e) {
			logger.error("表单信息{}", e.getMessage());
			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据新增失败", user, "", "");
			SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据新增失败,请联系相关人员处理");
		} finally {
		}
     	  return domresult;
       

    }

    public String update(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywHjyxdjgzcnsNrgmVO vo=new BuSpywHjyxdjgzcnsNrgmVO();
 		String domresult = null;
 		try {
 			domresult = buSpywHjyxdjgzcnsNrgmDao.update(json);
 			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据更改成功", user, "", "");

 		} catch (DaoException e) {
			logger.error("表单信息{}", e.getMessage());
			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-附件信息-数据更改失败", user, "", "");
			SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据更改失败,请联系相关人员处理");
		} finally {
		}
     	  return domresult;
    	

    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywHjyxdjgzcnsNrgmVO vo=new BuSpywHjyxdjgzcnsNrgmVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据删除成功", user, "", "");

 		} catch (DaoException e) {
			logger.error("表单信息{}", e.getMessage());
			LogManager.writeUserLog(vo.getHjyxdjgzcnsnrgm_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设项目环境影响登记表告知承诺备案--工业类-附件信息-数据删除失败", user, "", "");
			SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--工业类-子表内容规模-数据删除失败,请联系相关人员处理");
		} finally {
		}
     	  return domresult;
		

	}
}
