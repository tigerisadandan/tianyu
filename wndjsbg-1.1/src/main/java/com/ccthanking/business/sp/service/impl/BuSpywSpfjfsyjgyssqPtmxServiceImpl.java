/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywSpfjfsyjgyssqPtmxService.java
 * 创建日期： 2014-06-09 下午 02:02:22
 * 功能：    接口：商品房交付使用竣工验收申请——公建配套明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 02:02:22  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywSpfjfsyjgyssqPtmxDao;
import com.ccthanking.business.sp.service.BuSpywSpfjfsyjgyssqPtmxService;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqPtmxVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqVO;
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
 * <p> BuSpywSpfjfsyjgyssqPtmxService.java </p>
 * <p> 功能：商品房交付使用竣工验收申请——公建配套明细 </p>
 *
 * <p><a href="BuSpywSpfjfsyjgyssqPtmxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Service
public class BuSpywSpfjfsyjgyssqPtmxServiceImpl extends Base1ServiceImpl<BuSpywSpfjfsyjgyssqPtmxVO, String> implements BuSpywSpfjfsyjgyssqPtmxService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSpfjfsyjgyssqPtmxServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SPFJFSYJGYSSQ_PTMX;
    
    private BuSpywSpfjfsyjgyssqPtmxDao buSpywSpfjfsyjgyssqPtmxDao;
    
    @Autowired
	@Qualifier("buSpywSpfjfsyjgyssqPtmxDaoImpl")
	public void setBuSpywSpfjfsyjgyssqPtmxDao(BuSpywSpfjfsyjgyssqPtmxDao buSpywSpfjfsyjgyssqPtmxDao) {
		this.buSpywSpfjfsyjgyssqPtmxDao = buSpywSpfjfsyjgyssqPtmxDao;
	}
    // @Override
    public List<?> find(String id) throws Exception {
		
		List<?> list=buSpywSpfjfsyjgyssqPtmxDao.find(id);
        
        return list;

    }
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqPtmxVO vo=new BuSpywSpfjfsyjgyssqPtmxVO();
 		String domresult = null;
 		try {
 			domresult = buSpywSpfjfsyjgyssqPtmxDao.queryCondition(json);
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请-子表公建配套明细—数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据查询失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—子表公建配套明细—数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	
    	

    }
    public String queryUpdate(String id) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqPtmxVO vo=new BuSpywSpfjfsyjgyssqPtmxVO();
 		String domresult = null;
 		try {
 			 domresult=buSpywSpfjfsyjgyssqPtmxDao.queryUpdate(id);
 			 LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 					user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细-更改操作—数据新增成功", user, "", "");

 	 		} catch (DaoException e) {
 	 			logger.error("表单信息{}", e.getMessage());
 	 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 					user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—更改操作—数据新增失败", user, "", "");
 	 			SystemException.handleMessageException("商品房交付使用竣工验收申请—子表公建配套明细—更改操作—数据新增失败,请联系相关人员处理");
 	 		} finally {
 	 		}
 	 			return domresult;
 	       

    }
    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqPtmxVO vo=new BuSpywSpfjfsyjgyssqPtmxVO();
 		String domresult = null;
 		try {
 			domresult = buSpywSpfjfsyjgyssqPtmxDao.insert(json);
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据新增成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据新增失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—子表公建配套明细—数据新增失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
       

    }

    public String update(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqPtmxVO vo=new BuSpywSpfjfsyjgyssqPtmxVO();
 		String domresult = null;
 		try {
 			domresult = buSpywSpfjfsyjgyssqPtmxDao.update(json);
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据更改成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据更改失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—子表公建配套明细—数据更改失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqPtmxVO vo=new BuSpywSpfjfsyjgyssqPtmxVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据删除成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—子表公建配套明细—数据删除失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—子表公建配套明细—数据删除失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;


	}
	
    
}
