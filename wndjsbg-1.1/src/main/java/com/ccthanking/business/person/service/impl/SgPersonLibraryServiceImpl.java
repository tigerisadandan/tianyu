/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.SgPersonLibraryService.java
 * 创建日期： 2014-04-28 上午 09:29:27
 * 功能：    接口：人员信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-28 上午 09:29:27  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.person.service.impl;


import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.jdbc.Const;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.person.dao.SgPersonLibraryDao;
import com.ccthanking.business.person.dao.SgPersonZhengshuDao;
import com.ccthanking.business.person.service.SgPersonLibraryService;
import com.ccthanking.business.person.vo.SgPersonLibraryVO;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgPersonLibraryService.java </p>
 * <p> 功能：人员信息 </p>
 *
 * <p><a href="SgPersonLibraryService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-28
 * 
 */

@Service
public  class SgPersonLibraryServiceImpl extends Base1ServiceImpl<SgPersonLibraryVO, String> implements SgPersonLibraryService {

	private static Logger logger = LoggerFactory.getLogger(SgPersonLibraryServiceImpl.class);
	// 业务类型
	private String ywlx = YwlxManager.SGRY;

    private SgPersonLibraryDao sgPersonLibraryDao;
    private SgPersonZhengshuDao sgPersonZhengshuDao;
    
    @Autowired
	@Qualifier("sgPersonLibraryDaoImpl")
	public void setSgPersonLibraryDao(SgPersonLibraryDao sgPersonLibraryDao) {
		this.sgPersonLibraryDao = sgPersonLibraryDao;
	}
    @Autowired
	@Qualifier("sgPersonZhengshuDaoImpl")
	public void setSgPersonZhengshueDao(SgPersonZhengshuDao sgPersonZhengshuDao) {
		this.sgPersonZhengshuDao = sgPersonZhengshuDao;
	}
    @Autowired
    private FsMessageInfoService fsMessageInfoService;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
 			domresult = sgPersonLibraryDao.queryCondition(json);
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "施工人员信息-数据查询成功", user, "", "");

 		} catch (DaoException e) {
			logger.error("表单信息{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "施工人员信息-数据查询失败", user, "", "");
			SystemException.handleMessageException("施工人员信息-数据查询失败,请联系相关人员处理");
		} finally {
		}
     	  return domresult;
    	
    }
    public String queryConditionNotNull(String json) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=sgPersonLibraryDao.queryConditionNotNull(json);
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-根据条件查询-数据查询成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-根据条件查询-数据查询失败", user, "", "");
 			SystemException.handleMessageException("施工人员信息-根据条件查询-数据查询失败,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;

    }
    
    public String insert(String json, String status,Map fils) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=sgPersonLibraryDao.insert(json, status, fils);
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-数据新增成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-数据新增失败", user, "", "");
 			SystemException.handleMessageException("施工人员信息-数据新增失败,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;

    }

    public String update(String json,Map fils) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=sgPersonLibraryDao.update(json, fils);
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-数据更改成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-数据更改失败", user, "", "");
 			SystemException.handleMessageException("施工人员信息-数据更改失败,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
    	
    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=sgPersonLibraryDao.delete(json);
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-数据删除成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-数据删除失败", user, "", "");
 			SystemException.handleMessageException("施工人员信息-数据删除失败,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;

	}
	
	public String queryCodeIsEmpty(String json) throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=sgPersonLibraryDao.queryCodeIsEmpty(json);
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-根据条件查询是否为空-数据查询成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-根据条件查询是否为空-数据查询失败", user, "", "");
 			SystemException.handleMessageException("施工人员信息-根据条件查询是否为空-数据查询失败,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
		
	}

	public String queryList(String json) throws Exception {
		
		String domresult=sgPersonLibraryDao.queryList(json);
    	return domresult;

	}
	
	public String updateShenhe(String json,Map fils,String u_id,String status,String t_id) throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
 			 JSONArray list = vo.doInitJson(json);
 	         JSONObject object=  (JSONObject) list.get(0);
 	         String companyname=object.getString("COMPANY_NAME");
 	         String companycode=object.getString("COMPANY_DENGLU_CODE");
 	         String companyuid = object.getString("SG_COMPANY_UID");
 	         String personUid = object.getString("SG_PERSON_LIBRARY_UID");
 	         String personName = object.getString("PERSON_NAME");
 	         domresult=sgPersonLibraryDao.updateShenhe(json,fils,u_id,status,t_id);
    	 
    	 if("10".equals(status)){//人员审核通过
    	 //****************************消息添加
    		 
	         Map messageMap= new HashMap();
	         messageMap.put("TITLE", "施工人员审核");
	         messageMap.put("CONTENT", "人员<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"zxdtView('"+personUid+"','sgrysh')\">"+personName+"</a>信息<span&nbsp;style='color:blue;'>审核通过</span>");
	         messageMap.put("USERTO", companycode);//企业的登录Code
	         messageMap.put("USERTONAME", companyname);//企业的名称
	         messageMap.put("SYSTEM_TYPE","SG");//系统类型
	         messageMap.put("COMPANY_UID", companyuid);//对应的施工企业编号
	         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_SGQYRY);//消息类型
	         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_SG_RYGL);//涉及的权限点
	         
	         fsMessageInfoService.insertVo(messageMap);
    	 }else if("20".equals(status)){//人员审核未通过
    	 //****************************消息添加
    		 
	         Map messageMap= new HashMap();
	         messageMap.put("TITLE", "施工人员审核");
	         messageMap.put("CONTENT", "人员<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"zxdtView('"+personUid+"','sgrysh')\">"+personName+"</a>信息<span&nbsp;style='color:red;'>审核未通过</span>");
	         messageMap.put("USERTO", companycode);//人员的登录Code
	         messageMap.put("USERTONAME", companyname);//企业的名称
	         messageMap.put("SYSTEM_TYPE","SG");//系统类型
	         messageMap.put("COMPANY_UID", companyuid);//对应的施工企业编号
	         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_SGQYRY);//消息类型
	         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_SG_RYGL);//涉及的权限点
	         
	         fsMessageInfoService.insertVo(messageMap);
    	 }
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-提交-数据更改成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-提交-数据更改成功", user, "", "");
 			SystemException.handleMessageException("施工人员信息-提交-数据更改成功,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
		
	}
	public String updateStatusOne(String u_id,String js) throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
   		SgPersonLibraryVO vo=new SgPersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=sgPersonLibraryDao.updatePass(u_id,js);
    	 LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
 				user.getName() + "施工人员信息-入库-数据更改成功", user, "", "");

  		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
 				user.getName() + "施工人员信息-入库-数据更改成功", user, "", "");
 			SystemException.handleMessageException("施工人员信息-入库-数据更改成功,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
		
	}
	public String updateCopyPerson(String t_id,String u_id) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String new_id=sgPersonLibraryDao.updateCopyPerson(t_id,u_id);
		
		LogManager.writeUserLog(new_id, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对入库<人员信息>增加", user, "", "");
		//t_id为30状态该数据的ID，u_id为该数据的person_uid
		sgPersonZhengshuDao.updateCopyZhengshu(t_id,u_id,new_id);
		//LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对入库<证书信息>增加", user, "", "");

		//sgPersonZhengshuDao.insertToOldZhengshu(new_id);
		return new_id;
	}
    public String updateCopyPerson2(String status,String IdCard,String shenfenID) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
		String new_id=sgPersonLibraryDao.updateCopyPerson2(status,IdCard,shenfenID);
		LogManager.writeUserLog(new_id, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对入库<人员信息>更改", user, "", "");
		
		sgPersonZhengshuDao.updateCopyZhengshu2(IdCard,new_id);
		return new_id;
	}
	public String queryStatusIsEmpty(String json,String bz,String personUID) throws Exception {
		
		String domresult=sgPersonLibraryDao.queryStatusIsEmpty(json,bz,personUID);
    	return domresult;
		
	}
	public String tuihui(String ids,String yijian) throws Exception {
		
		String domresult=sgPersonLibraryDao.tuihui(ids,yijian);
    	return domresult;
		
	}
	public String suoding(String id,String yijian,String sjfw,String jz_y) throws Exception {
		
		String domresult=sgPersonLibraryDao.suoding(id,yijian,sjfw,jz_y);
    	return domresult;
		
	}
	public String jiesuo(String id) throws Exception {
		
		String domresult=sgPersonLibraryDao.jiesuo(id);
    	return domresult;
		
	}
}
