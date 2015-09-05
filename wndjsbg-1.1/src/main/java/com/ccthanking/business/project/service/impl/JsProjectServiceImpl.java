/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.JsProjectService.java
 * 创建日期： 2014-09-02 下午 04:35:32
 * 功能：    接口：项目管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-09-02 下午 04:35:32  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsx3.gui.Alerts;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.project.dao.JsProjectDao;
import com.ccthanking.business.project.dao.ProjectsDao;
import com.ccthanking.business.project.service.JsCompanyService;
import com.ccthanking.business.project.service.JsProjectService;
import com.ccthanking.business.project.vo.JsCompanyVO;
import com.ccthanking.business.project.vo.JsProjectVO;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.business.resources.service.JsComClkService;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> JsProjectService.java </p>
 * <p> 功能：项目管理 </p>
 *
 * <p><a href="JsProjectService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-09-02
 * 
 */

@Service
public class JsProjectServiceImpl extends Base1ServiceImpl<JsProjectVO, String> implements JsProjectService {

	private static Logger logger = LoggerFactory.getLogger(JsProjectServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JS_PROJECT;
    
    private JsProjectDao jsProjectDao;
    
    @Autowired
    private JsCompanyService companyService;
    
    @Autowired
    private ProjectsDao projectsDao;
    
    
    
    @Autowired
    private FsMessageInfoService fsMessageInfoService;

    @Autowired
	@Qualifier("jsProjectDaoImpl")
	public void setJsProjectDao(JsProjectDao jsProjectDao) {
		this.jsProjectDao = jsProjectDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) jsProjectDao);
	}
    @Autowired
    private JsComClkService jsComClkService;
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = jsProjectDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<项目>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("项目管理审批{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "项目查询失败", user, "", "");
			SystemException.handleMessageException("项目管理审批查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
    
    public String queryCondition2(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = jsProjectDao.queryCondition2(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<项目>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("项目管理审批{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "项目查询失败", user, "", "");
			SystemException.handleMessageException("项目管理审批查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
 	
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JsProjectVO vo = new JsProjectVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            
            vo.setValueFromJson(obj);
            //vo.setProject_name(obj.getJSONArray("PROJECT_NAME").getString(0));
            
            vo.setJs_company_uid(user.getIdCard());
            vo.setProject_uid(DBUtil.getSequenceValue("PROJECT_UID"));
            vo.setCreated_date(new Date());
            vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
            vo.setCreated_uid(ActionContext.getCurrentUserInThread().getAccount());
            
            
            // 插入
			jsProjectDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "项目管理新增成功", user, "", "");

            
        } catch (DaoException e) {
        	 logger.error("项目管理审批{}", e.getMessage());
             LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "项目管理新增失败", user, "", "");
             SystemException.handleMessageException("项目管理审批新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JsProjectVO vo = new JsProjectVO();

        try {
            JSONArray list = vo.doInitJson(json);
            //vo.setValueFromJson((JSONObject) list.get(0));
            JSONObject object= (JSONObject) list.get(0);
            vo.setValueFromJson(object);
           
            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");//   1通过   2退回
            String SHENHE_YIJIAN=object.getString("SHENHE_YIJIAN");
            
            String jscompany=object.getString("JS_COMPANY_UID");
            String jsproject=object.getString("PROJECT_UID");
           
            //vo.setStatus(STATUS);
            vo.setShenhe_jieguo(SHENHE_JIEGUO);
            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(user.getUserSN());
            vo.setShenhe_yijian(SHENHE_YIJIAN);
            /**
             * 审核通过，添加项目编号
             * 规则：年份+项目类型（projects_type）+后四位，后四位不够用0补齐
             * **/
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
         	  String projectscode= jsProjectDao.getProjectsCode(jscompany,3);
         	  vo.setProject_code(projectscode); //给项目编号赋值
         	  vo.setStatus("10");
         	  
         	  

              
              //生成 单位工程 code
				 String UNTISS= object.getString("UNTISS");
	         	   String[] ary = UNTISS.split(",");  //units id 数组
	               String UnitsCode = vo.getProject_code()+"01";     //分期 code
	               for (int i = 1; i <= ary.length; i++) {   
	               	String index=null; // 小于 10 十位补零
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
         	  
         	  //****************************消息添加
         	  JsCompanyVO jsCompanyVO=companyService.findById(jscompany);
         	  
              Map messageMap= new HashMap();
              messageMap.put("TITLE", "建设企业建设项目审核");
              messageMap.put("CONTENT", "建设项目"+vo.getGongcheng_name()+"审核通过");
              messageMap.put("USERTO", jsCompanyVO.getUser_name());
              messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
              messageMap.put("SYSTEM_TYPE", "JS");
              messageMap.put("COMPANY_UID", jscompany);
              messageMap.put("MSG_TYPE", Constants.FS_MESSAGE_INFO_MSG_TYPE_JSXM);
              messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_XMXXWH);
              messageMap.put("MSG_VIEW_TYPE", "1");
              messageMap.put("PRM1", jsproject);
              
              fsMessageInfoService.insertVo(messageMap);
              
            } else{
            	vo.setStatus("20");
            	//jsProjectDao.clean(jsproject);
            	//当不通过时，把项目-立项关联表中的记录删除
            	
            	  //****************************消息添加
           	    JsCompanyVO jsCompanyVO=companyService.findById(jscompany);
           	  
                Map messageMap= new HashMap();
                messageMap.put("TITLE", "建设企业建设项目审核");
                messageMap.put("CONTENT", "建设项目"+vo.getGongcheng_name()+"审核未通过");
                messageMap.put("USERTO", jsCompanyVO.getUser_name());
                messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
                messageMap.put("SYSTEM_TYPE", "JS");
                messageMap.put("COMPANY_UID", jscompany);
                messageMap.put("MSG_TYPE", Constants.FS_MESSAGE_INFO_MSG_TYPE_JSXM);
                messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_XMXXWH);
                messageMap.put("MSG_VIEW_TYPE", "2");
                messageMap.put("PRM1", jsproject);

                fsMessageInfoService.insertVo(messageMap);
            }
            
            // 修改
            jsProjectDao.update(vo);
            resultVO = vo.getRowJson();
            JsProjectVO xmVo =this.findById(vo.getProject_uid());
        	if("1".equals(xmVo.getJsrw_y())&&"0".equals(xmVo.getXmfq_y())){ //审核无分期项目时  同时处理子分期项目
        		String queryFqXm = "select projects_uid from projects where project_uid =" +vo.getProject_uid();
				String[][] fqXm = DBUtil.query(queryFqXm);
				ProjectsVO fqVo =  new ProjectsVO();
				fqVo.setValueFromJson(object);
				fqVo.setProjects_uid(fqXm[0][0]);
				fqVo.setStatus(vo.getStatus());
				fqVo.setLixiang_uid("");
				fqVo.setProjects_name(vo.getGongcheng_name());
				fqVo.setZhandi_mianji(vo.getZdmj());
				fqVo.setJianzhu_mianji(vo.getZdmj());
				fqVo.setZong_touzi(vo.getTouzi());
				fqVo.setLianxiren_mobile(vo.getFzr_mobile());
				fqVo.setLianxiren_phone(vo.getFzr_phone());
				fqVo.setLianxiren_fax(vo.getFzr_fax());
				fqVo.setEmail(vo.getFzr_email());
				fqVo.setLianxiren(vo.getFzr());
				if(vo.getStatus().equals("10")){ 
					String projectscode = projectsDao.getCode(vo.getProject_code(),jscompany,"J",3);
					fqVo.setProjects_code(vo.getProject_code()+"01"); 
				}
				jsProjectDao.update(fqVo);
			}

            /**
             * 由于通过projectsVO.setValueFromJson((JSONObject) list.get(0))
             * 转换得到的都是更新的字段信息
             * 需要通过项目ID从新获取项目的VO信息来进行企业材料库的节点新建
             * **/
            //String projectid=object.getString("PROJECT_UID");
            vo=this.findById(jsproject);  
           
            
            //项目审核通过后 初始化管理的立项的材料节点
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	//一个项目绑定多个立项时新建节点
            	List<Map<String, String>> lxList=jsProjectDao.lxList(vo.getProject_uid());
            	
            	if(lxList!=null&&lxList.size()>0){
            		for(int i=0;i<lxList.size();i++){
            			Map tempmap=lxList.get(i);
            			String lxname=(String)tempmap.get("LIXIANG_NAME");
            			String lxuid=(String)tempmap.get("LIXIANG_UID");
            			
            			jsComClkService.saveCompanyClk(Constants.YWCLKJB_LX, vo.getJs_company_uid(), lxname, lxuid);
            		}	
            	}	
            }
            
            
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "项目信息修改成功", user, "", "");


        } catch (DaoException e) {
            logger.error("项目管理审批{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "项目信息修改失败", user, "", "");

            SystemException.handleMessageException("项目管理审批修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JsProjectVO vo = new JsProjectVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			jsProjectDao.delete(ProjectsVO.class, vo.getProject_uid());


			resultVo = vo.getRowJson();

			LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "项目管理删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("项目管理审批{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "项目项目删除失败", user, "", "");
            SystemException.handleMessageException("项目管理审批删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

    public String getProjectCount() throws Exception {
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList= jsProjectDao.getProjectCount();
			if(tempList!=null&&tempList.size()>0){				
					Map temMap=(Map)tempList.get(0);		
					obj.put("DSH", temMap.get("DSH"));
					obj.put("WTG", temMap.get("WTG"));
					obj.put("YTG", temMap.get("YTG"));
	
			}
			
		} catch (DaoException e) {
            logger.error("项目管理审批结果统计{}", e.getMessage());
            SystemException.handleMessageException("项目管理审批结果统计查询失败,请联系相关人员处理");
		} finally {
		}
		
		return obj.toString();
	}
    public String queryLX(String json) throws Exception {

		String resultVo = null;
		
		resultVo=jsProjectDao.queryLX(json);
		return resultVo;
	}
    public String queryLXdetail(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = jsProjectDao.queryLXdetail(json);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<查询立项详细信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("查询立项详细信息{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "查询立项详细信息失败", user, "", "");
			SystemException.handleMessageException("查询立项详细信息失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
}
