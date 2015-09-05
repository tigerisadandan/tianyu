/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.ProjectsService.java
 * 创建日期： 2014-07-02 下午 12:00:58
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:00:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.project.vo.JsCompanyVO;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.business.project.dao.ProjectsDao;
import com.ccthanking.business.project.service.JsCompanyService;
import com.ccthanking.business.project.service.ProjectsService;
import com.ccthanking.business.resources.service.JsComClkService;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> ProjectsService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="ProjectsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */

@Service
public class ProjectsServiceImpl extends Base1ServiceImpl<ProjectsVO, String> implements ProjectsService {

	private static Logger logger = LoggerFactory.getLogger(ProjectsServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.PROJECTS;
    
    private ProjectsDao projectsDao;
    @Autowired
    private JsComClkService jsComClkService;
    
    @Autowired
    private FsMessageInfoService fsMessageInfoService;
    
    @Autowired
    private JsCompanyService companyService;
    // @Override
    //项目分期信息查询
    public String queryCondition(String json) throws Exception {
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsDao.queryCondition(json, null, null);

                 
        }catch (DaoException e) {
        	logger.error("项目分期信息查询{}", e.getMessage());
			SystemException.handleMessageException("项目分期查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
  //环保项目信息查询
    public String queryHb(String json) throws Exception {
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = projectsDao.queryHb(json, null, null);

                 
        }catch (DaoException e) {
        	logger.error("环保项目信息查询{}", e.getMessage());
			SystemException.handleMessageException("环保项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    public String insert(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ProjectsVO vo = new ProjectsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
          
            // 插入
			projectsDao.save(vo);
            resultVO = vo.getRowJson();

           	//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("项目信息新增{}", e.getMessage());
             SystemException.handleMessageException("项目新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String resultVO = null;
        ProjectsVO  projectsVO = new ProjectsVO();

        try {
           JSONArray list = projectsVO.doInitJson(json);
           JSONObject object= (JSONObject) list.get(0);
           projectsVO.setValueFromJson((JSONObject) list.get(0));
           
//           String projectid=object.getString("PROJECTS_UID");
           String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");//   1通过   2退回
           String SHENPI_YIJIAN=object.getString("SHENPI_YIJIAN");
           String jscompany=object.getString("JS_COMPANY_UID");
           String code=object.getString("PROJECTS_CODE");
           String type=object.getString("PROJECTS_TYPE");
         
           projectsVO.setShenhe_jieguo(SHENHE_JIEGUO);
           projectsVO.setShenpi_date(new Date());
           projectsVO.setShenpi_ren(user.getUserSN());
           projectsVO.setShenpi_yijian(SHENPI_YIJIAN); 
           String projectscode="";
           /**
            * 审核通过，添加项目编号
            * 规则：年份+项目类型（projects_type）+后四位，后四位不够用0补齐
            * **/
           if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
        	   projectscode = projectsDao.getCode(code,jscompany,projectsVO.getProjects_type(),3);
        	  projectsVO.setProjects_code(projectscode); 
        	  projectsVO.setStatus("10");
        	  
        	  //****************************消息添加
         	  JsCompanyVO jsCompanyVO=companyService.findById(jscompany);
         	  
              Map messageMap= new HashMap();
              if("H".equals(type)){
            	  messageMap.put("TITLE", "建设企业环保项目审核");
            	  messageMap.put("CONTENT", "环保项目"+projectsVO.getProjects_name()+"审核通过");
                  messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_HBSXSB);
              }else{
              messageMap.put("TITLE", "建设企业建设项目分期审核");
              messageMap.put("CONTENT", "项目分期"+projectsVO.getProjects_name()+"审核通过");
              messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_JSSXSB);
              }
              messageMap.put("USERTO", jsCompanyVO.getUser_name());
              messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
              messageMap.put("SYSTEM_TYPE", "JS");
              messageMap.put("COMPANY_UID", jscompany);
              messageMap.put("MSG_TYPE", Constants.FS_MESSAGE_INFO_MSG_TYPE_JSXMFQ);
              messageMap.put("MSG_VIEW_TYPE", "1");
              messageMap.put("PRM1", projectsVO.getProjects_uid());
              
              fsMessageInfoService.insertVo(messageMap);
           }else{
        	  projectsVO.setStatus("20");
        	  
        	  //****************************消息添加
         	    JsCompanyVO jsCompanyVO=companyService.findById(jscompany);
         	  
              Map messageMap= new HashMap();
              if("H".equals(type)){
            	  messageMap.put("TITLE", "建设企业环保项目审核");
            	  messageMap.put("CONTENT", "环保项目"+projectsVO.getProjects_name()+"审核未通过");
            	  messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_HBSXSB);
              }else{
              messageMap.put("TITLE", "建设企业建设项目分期审核");
              messageMap.put("CONTENT",  "项目分期"+projectsVO.getProjects_name()+"审核未通过");
              messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_JSSXSB);
              }
              messageMap.put("USERTO", jsCompanyVO.getUser_name());
              messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
              messageMap.put("SYSTEM_TYPE", "JS");
              messageMap.put("COMPANY_UID", jscompany);
              messageMap.put("MSG_TYPE", "建设项目分期");
              messageMap.put("MSG_VIEW_TYPE", "2");
              messageMap.put("PRM1", projectsVO.getProjects_uid());
              
              fsMessageInfoService.insertVo(messageMap);
           }
           
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            projectsDao.update(projectsVO);
            resultVO = projectsVO.getRowJson();
            if("J".equals(type)){
            if(projectsVO.getStatus().equals("10")){
           
         	   String UNTISS= object.getString("UNTISS");
         	   String[] ary = UNTISS.split(",");  //units id 数组
         	//审核 后添加  Units_code代码 
               String UnitsCode=projectscode;     //分期 code
               for (int i = 1; i <= ary.length; i++) {   
               	    // 小于 10 十位补零
               	String index=null;
               	    if(i<10)
               	    	index="0"+i;
               	    else
               	        index=i+"";
   		            String codesql = "update  projects_units u set u.units_code='"+UnitsCode+"D"+index+"' where u.units_uid='"+ary[i-1]+"'";
   		            String cc="";
   		            String cc1="";
   		            String cc2="";
   		            projectsDao.updateUnitsCode(codesql);
   			
   			}
//            ProjectsVO projectsVO=this.findById(projectid);  
            }
             }
            /**
             * 由于通过projectsVO.setValueFromJson((JSONObject) list.get(0))
             * 转换得到的都是更新的字段信息
             * 需要通过项目ID从新获取项目的VO信息来进行企业材料库的节点新建
             * **/
            String projectid=object.getString("PROJECTS_UID");
            projectsVO=this.findById(projectid);  
            //项目审核通过后 初始化项目的材料节点
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	jsComClkService.saveCompanyClk("XM", projectsVO.getJs_company_uid(), projectsVO.getProjects_name(), projectsVO.getProjects_uid());
            }
        } catch (DaoException e) {
            logger.error("项目审批{}", e.getMessage());
            SystemException.handleMessageException("项目审批失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ProjectsVO vo = new ProjectsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			projectsDao.delete(ProjectsVO.class, vo.getProjects_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("项目删除{}", e.getMessage());
            SystemException.handleMessageException("项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("projectsDaoImpl")
	public void setProjectsDao(ProjectsDao projectsDao) {
		this.projectsDao = projectsDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) projectsDao);
	}

	public String getProjectCount(String type) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList= projectsDao.getProjectCount(type);
			if(tempList!=null&&tempList.size()>0){				
					Map temMap=(Map)tempList.get(0);		
					obj.put("DSH", temMap.get("DSH"));
					obj.put("WTG", temMap.get("WTG"));
					obj.put("YTG", temMap.get("YTG"));
	
			}
			
		} catch (DaoException e) {
            logger.error("项目分期审批结果统计{}", e.getMessage());
            SystemException.handleMessageException("项目分期审批结果统计查询失败,请联系相关人员处理");
		} finally {
		}
		
		return obj.toString();
	}
	
}
