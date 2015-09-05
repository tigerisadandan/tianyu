/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gongcheng.service.ProjectsGongchengService.java
 * 创建日期： 2014-10-16 下午 04:22:49
 * 功能：    接口：施工内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-16 下午 04:22:49  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.gongcheng.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.gongcheng.dao.ProjectsGongchengDao;
import com.ccthanking.business.gongcheng.service.ProjectsGongchengService;
import com.ccthanking.business.project.vo.ProjectsGongchengVO;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ProjectsGongchengService.java </p>
 * <p> 功能：施工内容 </p>
 *
 * <p><a href="ProjectsGongchengService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-16
 * 
 */

@Service
public class ProjectsGongchengServiceImpl extends Base1ServiceImpl<ProjectsGongchengVO, String> implements ProjectsGongchengService {

	private static Logger logger = LoggerFactory.getLogger(ProjectsGongchengServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.PROJECTS_GONGCHENG;
    
    private ProjectsGongchengDao projectsGongchengDao;
    
    @Autowired
    private FsMessageInfoService fsMessageInfoService;

    // @Override
    public String queryCondition(String json,Map map) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsGongchengDao.queryCondition(json, null, map);          
        }catch (DaoException e) {
        	logger.error("施工内容息查询{}", e.getMessage());
			SystemException.handleMessageException("施工内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryCondition2(String json,Map map) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = projectsGongchengDao.queryCondition2(json,null,map);          
        }catch (DaoException e) {
        	logger.error("施工内容息查询{}", e.getMessage());
			SystemException.handleMessageException("施工内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
public String queryCondition3(String json,String condition,String AJZ_UID) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = projectsGongchengDao.queryCondition3(json,condition,AJZ_UID);          
        }catch (DaoException e) {
        	logger.error("施工内容息查询{}", e.getMessage());
			SystemException.handleMessageException("施工内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String querygc(String json,String gcid) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		
    		domresult = projectsGongchengDao.querygc(json, null, gcid);
    		
    		
    		
    	}catch (DaoException e) {
    		logger.error("施工内容息查询{}", e.getMessage());
    		SystemException.handleMessageException("施工内容查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    public String queryAllGc(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsGongchengDao.queryAllGc(json, null, null);

           
            
        }catch (DaoException e) {
        	logger.error("施工内容息查询{}", e.getMessage());
			SystemException.handleMessageException("施工内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ProjectsGongchengVO vo = new ProjectsGongchengVO();

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
			projectsGongchengDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "施工内容新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("施工内容{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "施工内容新增失败", user, "", "");
            SystemException.handleMessageException("施工内容新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user  = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ProjectsGongchengVO vo = new ProjectsGongchengVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object= (JSONObject) list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            String SHENPI_YIJIAN=object.getString("SHENHE_YIJIAN");
            String STATUS=object.getString("STATUS");
            String PROJECTS_CODE=object.getString("PROJECTS_CODE");//分期 code
            String PROJECTS_UID= object.getString("PROJECTS_UID");//分期id
            
            String sql="select count(*) from projects_gongcheng where projects_uid="+PROJECTS_UID+" and status=10";
            String[][] temp=DBUtil.querySql(conn, sql); 
            int count=Integer.parseInt(temp[0][0])+1;
            if(count<10){
            	PROJECTS_CODE+="N0"+count;
            }else{
            	PROJECTS_CODE+="N"+count;
            }
            vo.setGongcheng_code(PROJECTS_CODE);
//            ProjectsVO projectsVO=this.findById(projectid);  
            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(user.getUserSN());
            vo.setShenhe_yijian(SHENPI_YIJIAN);
            String jieguo="";
            if("10".equals(STATUS)){
            	jieguo="1";
            }else if("20".equals(STATUS)){
            	vo.setGongcheng_code("");
            	jieguo="2";
            }
            vo.setShenhe_jieguo(jieguo);
            // 修改
            projectsGongchengDao.update(vo);
            resultVO = vo.getRowJson();
           
            if("10".equals(STATUS)){
            	//****************************消息添加
                Map messageMap= new HashMap();
                messageMap.put("TITLE", "建设企业施工内容审核");
                messageMap.put("CONTENT", "施工内容"+vo.getGongcheng_name()+"审核通过");
                messageMap.put("USERTO", vo.getCreated_uid());
                messageMap.put("USERTONAME", vo.getCreated_name());

                fsMessageInfoService.insertVo(messageMap);
            }else{
            	//****************************消息添加
                Map messageMap= new HashMap();
                messageMap.put("TITLE", "建设企业施工内容审核");
                messageMap.put("CONTENT", "施工内容"+vo.getGongcheng_name()+"审核未通过");
                messageMap.put("USERTO", vo.getCreated_uid());
                messageMap.put("USERTONAME", vo.getCreated_name());

                fsMessageInfoService.insertVo(messageMap);
            	
            }
           

        } catch (DaoException e) {
        	logger.error("施工内容息修改{}", e.getMessage());
			SystemException.handleMessageException("施工内容修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ProjectsGongchengVO vo = new ProjectsGongchengVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);
//
//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			projectsGongchengDao.delete(ProjectsGongchengVO.class, vo.getGongcheng_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "施工内容删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("施工内容{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "施工内容删除失败", user, "", "");
            SystemException.handleMessageException("施工内容删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

    public String queryStatusNums(String id) throws Exception {
		String result = "";
        try {

        	result = projectsGongchengDao.queryStatusNums(id);
           
        } catch (DaoException e) {
            logger.error("审批业务流转实例{}", e.getMessage());
            SystemException.handleMessageException("审批业务流转实各个状态的数据条目数查询失败,请联系相关人员处理");
        } finally {
        }
        return result;
	}
    
    public String getAJY() throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = projectsGongchengDao.getAJY();          
        }catch (DaoException e) {
        	logger.error("安监员信息查询{}", e.getMessage());
			SystemException.handleMessageException("安监员查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    

    public String getZJY() throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = projectsGongchengDao.getZJY();          
        }catch (DaoException e) {
        	logger.error("质监员信息查询{}", e.getMessage());
			SystemException.handleMessageException("质监员查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryAjDept() throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		domresult = projectsGongchengDao.queryAjDept();          
    	}catch (DaoException e) {
    		logger.error("安监部门信息查询{}", e.getMessage());
    		SystemException.handleMessageException("安监部门信息查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    
    
	@Autowired
	@Qualifier("projectsGongchengDaoImpl")
	public void setProjectsGongchengDao(ProjectsGongchengDao projectsGongchengDao) {
		this.projectsGongchengDao = projectsGongchengDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)projectsGongchengDao);
	}

	
    
}
