/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywJsgcsgxkzsqMxService.java
 * 创建日期： 2014-05-27 下午 03:05:51
 * 功能：    接口：施工许可申请表-明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 03:05:51  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywJsgcsgxkzsqMxDao;
import com.ccthanking.business.sp.service.BuSpywJsgcsgxkzsqMxService;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqMxVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywJsgcsgxkzsqMxService.java </p>
 * <p> 功能：施工许可申请表-明细 </p>
 *
 * <p><a href="BuSpywJsgcsgxkzsqMxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

@Service
public class BuSpywJsgcsgxkzsqMxServiceImpl extends Base1ServiceImpl<BuSpywJsgcsgxkzsqMxVO, String> implements BuSpywJsgcsgxkzsqMxService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcsgxkzsqMxServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_JSGCSGXKZSQ_MX;
    
    private BuSpywJsgcsgxkzsqMxDao buSpywJsgcsgxkzsqMxDao;
    
	@Autowired
	@Qualifier("buSpywJsgcsgxkzsqMxDaoImpl")
	public void setBuSpywJsgcsgxkzsqMxDao(BuSpywJsgcsgxkzsqMxDao buSpywJsgcsgxkzsqMxDao) {
		this.buSpywJsgcsgxkzsqMxDao = buSpywJsgcsgxkzsqMxDao;
	}
	
    // @Override
	public List<Map<String, String>> find(String id) throws Exception {
		
		List<Map<String, String>> list=buSpywJsgcsgxkzsqMxDao.find(id);
        
        return list;

    }
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqMxVO vo=new BuSpywJsgcsgxkzsqMxVO();
 		String domresult = null;
 		try {
 			domresult = buSpywJsgcsgxkzsqMxDao.queryCondition(json);
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请-子表—数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请-子表—数据查询失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请-子表—数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	
       

    }
    public String queryUpdate(String id) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqMxVO vo=new BuSpywJsgcsgxkzsqMxVO();
 		String domresult = null;
 		try {
 			domresult = buSpywJsgcsgxkzsqMxDao.queryUpdate(id);
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请--子表—更改操作-数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请--子表—更改操作-数据查询失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请--子表—更改操作-数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	

    }
    public String insert(String json) throws Exception {

		
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqMxVO vo=new BuSpywJsgcsgxkzsqMxVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请-子表—数据新增成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请-子表—数据新增失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请-子表—数据新增失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	

    }

    public String update(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqMxVO vo=new BuSpywJsgcsgxkzsqMxVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请-子表—数据更改成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请-子表—数据更改失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请-子表—数据更改失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	

    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqMxVO vo=new BuSpywJsgcsgxkzsqMxVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请-子表—数据删除成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsqmx_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请-子表—数据删除失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请-子表—数据删除失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

	}


    
}
