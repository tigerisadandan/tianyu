/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpYwxxService.java
 * 创建日期： 2014-06-11 上午 11:51:59
 * 功能：    接口：审批业务信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-11 上午 11:51:59  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.spxx.dao.BuSpYwxxDao;
import com.ccthanking.business.spxx.service.BuSpYwxxService;
import com.ccthanking.business.spxx.vo.BuSpYwxxVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpYwxxService.java </p>
 * <p> 功能：审批业务信息 </p>
 *
 * <p><a href="BuSpYwxxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */

@Service
public class BuSpYwxxServiceImpl extends Base1ServiceImpl<BuSpYwxxVO, String> implements BuSpYwxxService {

	private static Logger logger = LoggerFactory.getLogger(BuSpYwxxServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SP_YWXX;
    
    private BuSpYwxxDao buSpYwxxDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpYwxxDao.queryCondition(json, null, null);

//            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息查询失败", user, "", "");
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryBz(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpYwxxDao.queryBz(json, null, null);

//            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息查询失败", user, "", "");
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryYWLX(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpYwxxDao.queryYWLX(json);

//            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息查询失败", user, "", "");
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpYwxxVO vo = new BuSpYwxxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           vo.setEvent_uid(new RandomGUID().toString());
           String bz = vo.getDescribe();
           bz = bz.replace("&lt;", "<").replace("&gt;", ">");
           vo.setDescribe(bz);
           
           
           vo.setCreated_date(Pub.getCurrentDate());
           vo.setCreated_name(user.getName());
           vo.setCreated_uid(user.getAccount());
           
            // 插入
			buSpYwxxDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "审批业务信息新增成功", user, "", "");

            
        } catch (DaoException e) {
            logger.error("审批业务信息{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息新增失败", user, "", "");
            SystemException.handleMessageException("审批业务信息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpYwxxVO vo = new BuSpYwxxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            String bz = vo.getDescribe();
            bz = bz.replace("&lt;", "<").replace("&gt;", ">");
            vo.setDescribe(bz);
            
            vo.setUpdate_date(Pub.getCurrentDate());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
            
            buSpYwxxDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务信息修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("审批业务信息{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息修改失败", user, "", "");
            SystemException.handleMessageException("审批业务信息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpYwxxVO vo = new BuSpYwxxVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			buSpYwxxDao.delete(BuSpYwxxVO.class, vo.getSpyw_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务信息删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("审批业务信息{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息删除失败", user, "", "");
            SystemException.handleMessageException("审批业务信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpYwxxDaoImpl")
	public void setBuSpYwxxDao(BuSpYwxxDao buSpYwxxDao) {
		this.buSpYwxxDao = buSpYwxxDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)buSpYwxxDao);
	}

	public String getAllSpyw() throws Exception {
		return buSpYwxxDao.getAllSpyw();
	}
    
}
