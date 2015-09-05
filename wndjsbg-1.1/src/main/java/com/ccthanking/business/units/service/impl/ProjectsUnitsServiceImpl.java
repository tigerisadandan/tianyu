/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    units.service.ProjectsUnitsService.java
 * 创建日期： 2014-10-17 下午 01:43:10
 * 功能：    接口：单位工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-17 下午 01:43:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.units.service.impl;


import java.util.Date;

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

import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.business.units.dao.ProjectsUnitsDao;
import com.ccthanking.business.units.service.ProjectsUnitsService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ProjectsUnitsService.java </p>
 * <p> 功能：单位工程 </p>
 *
 * <p><a href="ProjectsUnitsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-17
 * 
 */

@Service
public class ProjectsUnitsServiceImpl extends Base1ServiceImpl<ProjectsUnitsVO, String> implements ProjectsUnitsService {

	private static Logger logger = LoggerFactory.getLogger(ProjectsUnitsServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.PROJECTS_UNITS;
    
    private ProjectsUnitsDao projectsUnitsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsUnitsDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("单位工程查询{}", e.getMessage());
			SystemException.handleMessageException("单位工程查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String querybyIds(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsUnitsDao.querybyIds(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("单位工程查询{}", e.getMessage());
			SystemException.handleMessageException("单位工程查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }

    
    public String querybygcid(String id,String type,String cUid) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsUnitsDao.querybygcid(id,type,cUid);

          
            
        }catch (DaoException e) {
        	logger.error("单位工程查询{}", e.getMessage());
			SystemException.handleMessageException("单位工程查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ProjectsUnitsVO vo = new ProjectsUnitsVO();

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
            vo.setCreated_date(new Date());
         			vo.setEvent_uid(new RandomGUID().toString()); // 事件编号
                     vo.setCreated_name(user.getName());
                     vo.setCreated_uid(ActionContext.getCurrentUserInThread()
         					.getAccount());
            // 插入
			projectsUnitsDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "单位工程新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("单位工程{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "单位工程新增失败", user, "", "");
            SystemException.handleMessageException("单位工程新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ProjectsUnitsVO vo = new ProjectsUnitsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            // 修改
            projectsUnitsDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "单位工程修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("单位工程{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "单位工程修改失败", user, "", "");
            SystemException.handleMessageException("单位工程修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ProjectsUnitsVO vo = new ProjectsUnitsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);
//
//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			projectsUnitsDao.delete(ProjectsUnitsVO.class, vo.getUnits_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "单位工程删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("单位工程{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "单位工程删除失败", user, "", "");
            SystemException.handleMessageException("单位工程删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("projectsUnitsDaoImpl")
	public void setProjectsUnitsDao(ProjectsUnitsDao projectsUnitsDao) {
		this.projectsUnitsDao = projectsUnitsDao;
	}
    
}
