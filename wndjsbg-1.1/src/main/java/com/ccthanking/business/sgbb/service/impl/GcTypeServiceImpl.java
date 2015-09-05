/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.GcTypeService.java
 * 创建日期： 2014-04-22 下午 04:07:38
 * 功能：    接口：施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-22 下午 04:07:38  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.service.impl;


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

import com.ccthanking.business.sgbb.vo.GcTypeVO;
import com.ccthanking.business.sgbb.dao.GcTypeDao;
import com.ccthanking.business.sgbb.service.GcTypeService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> GcTypeService.java </p>
 * <p> 功能：施工报备 </p>
 *
 * <p><a href="GcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-22
 * 
 */

@Service
public class GcTypeServiceImpl extends Base1ServiceImpl<GcTypeVO, String> implements GcTypeService {

	private static Logger logger = LoggerFactory.getLogger(GcTypeServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.GC_TYPE;
    
    private GcTypeDao gcTypeDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = gcTypeDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<施工报备>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("施工报备{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "施工报备查询失败", user, "", "");
			SystemException.handleMessageException("施工报备查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        GcTypeVO vo = new GcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			gcTypeDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getGc_type_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getUserSN() + "施工报备新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("施工报备{}", e.getMessage());
            LogManager.writeUserLog(vo.getGc_type_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getUserSN() + "施工报备新增失败", user, "", "");
            SystemException.handleMessageException("施工报备新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        GcTypeVO vo = new GcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            // 修改
            gcTypeDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getGc_type_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getUserSN() + "施工报备修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("施工报备{}", e.getMessage());
            LogManager.writeUserLog(vo.getGc_type_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getUserSN() + "施工报备修改失败", user, "", "");
            SystemException.handleMessageException("施工报备修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		GcTypeVO vo = new GcTypeVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			gcTypeDao.delete(GcTypeVO.class, vo.getGc_type_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(vo.getGc_type_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "施工报备删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("施工报备{}", e.getMessage());
            LogManager.writeUserLog(vo.getGc_type_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "施工报备删除失败", user, "", "");
            SystemException.handleMessageException("施工报备删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("gcTypeDaoImpl")
	public void setGcTypeDao(GcTypeDao gcTypeDao) {
		this.gcTypeDao = gcTypeDao;
	}

	public String queryTypelist(String pType) throws Exception {
		// TODO Auto-generated method stub
		return gcTypeDao.queryTypelist(pType);
	}

	public String queryPType(String gcType) throws Exception {
		// TODO Auto-generated method stub
		return gcTypeDao.queryPType(gcType);
	}
    
}
