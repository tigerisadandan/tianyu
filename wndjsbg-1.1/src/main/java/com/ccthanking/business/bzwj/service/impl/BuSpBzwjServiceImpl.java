/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpBzwjService.java
 * 创建日期： 2014-10-28 上午 11:02:10
 * 功能：    接口：步骤文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 上午 11:02:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.bzwj.dao.BuSpBzwjDao;
import com.ccthanking.business.bzwj.service.BuSpBzwjService;
import com.ccthanking.business.spxx.vo.BuSpBzwjVO;
import com.ccthanking.business.spxx.vo.BuSpYwlzBzwjVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpBzwjService.java </p>
 * <p> 功能：步骤文件 </p>
 *
 * <p><a href="BuSpBzwjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */

@Service
public class BuSpBzwjServiceImpl extends Base1ServiceImpl<BuSpBzwjVO, String> implements BuSpBzwjService {

	private static Logger logger = LoggerFactory.getLogger(BuSpBzwjServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SP_BZWJ;
    
    private BuSpBzwjDao buSpBzwjDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpBzwjDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("步骤文件查询{}", e.getMessage());
			SystemException.handleMessageException("步骤文件查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String querylzbzwj(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpBzwjDao.querylzbzwj(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("步骤文件查询{}", e.getMessage());
			SystemException.handleMessageException("步骤文件查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpBzwjVO vo = new BuSpBzwjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//		    vo.setSjbh(eventVO.getSjbh());

            // 插入
			buSpBzwjDao.save(vo);
            resultVO = vo.getRowJson();

//            LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
//                    .getOrgDept().getDeptName() + " " + user.getName() + "步骤文件新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
        	logger.error("步骤文件查询{}", e.getMessage());
			SystemException.handleMessageException("步骤文件查询失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String insertlzwj(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpYwlzBzwjVO vo = new BuSpYwlzBzwjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//		    vo.setSjbh(eventVO.getSjbh());

            // 插入
			buSpBzwjDao.save(vo);
            resultVO = vo.getRowJson();

//            LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
//                    .getOrgDept().getDeptName() + " " + user.getName() + "步骤文件新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
        	logger.error("步骤文件查询{}", e.getMessage());
			SystemException.handleMessageException("步骤文件查询失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpBzwjVO vo = new BuSpBzwjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
//
//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            // 修改
            buSpBzwjDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
        	logger.error("步骤文件查询{}", e.getMessage());
			SystemException.handleMessageException("步骤文件查询失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpBzwjVO vo = new BuSpBzwjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			buSpBzwjDao.delete(BuSpBzwjVO.class, vo.getBzwj_uid());

			resultVo = vo.getRowJson();

//			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
//                    .getDeptName() + " " + user.getName() + "步骤文件删除成功", user, "", "");

		} catch (DaoException e) {
			logger.error("步骤文件查询{}", e.getMessage());
			SystemException.handleMessageException("步骤文件查询失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpBzwjDaoImpl")
	public void setBuSpBzwjDao(BuSpBzwjDao buSpBzwjDao) {
		this.buSpBzwjDao = buSpBzwjDao;
	}
    
}
