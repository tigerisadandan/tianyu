package com.ccthanking.business.jl.service.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.jl.vo.PersonLibraryVO;
import com.ccthanking.business.jl.dao.JlPersonLibraryDao;
import com.ccthanking.business.jl.service.JlPersonLibraryService;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.FjlbManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

@Service
public class JlPersonLibraryServiceImpl implements JlPersonLibraryService {
	
	
	private JlPersonLibraryDao jlPersonLibraryDao;
	
	@Autowired
    private FsMessageInfoService fsMessageInfoService;

	@Autowired
	@Qualifier("jlPersonLibraryDaoImpl")
	public void setJlPersonLibraryDao(JlPersonLibraryDao jlPersonLibraryDao) {
		this.jlPersonLibraryDao = jlPersonLibraryDao;
	}

	public String queryCondition(String json) throws Exception {
		
		return this.jlPersonLibraryDao.queryCondition(json);
	}

	public String queryNotNull(String json) throws Exception {
		
		return this.jlPersonLibraryDao.queryNotNull(json);
	}
	
	public String updateCopyPerson(String t_id,String u_id) throws Exception {
		String new_id=this.jlPersonLibraryDao.updateCopyPerson(t_id,u_id);
		
		//t_id为30状态该数据的ID，u_id为该数据的person_uid
		//this.jlPersonLibraryDao.updateCopyZhengshu(t_id,u_id,new_id);

		return new_id;
	}
	
	public String updateShenhe(String json,String u_id,String status,String t_id) throws Exception {
		
		//User user = ActionContext.getCurrentUserInThread();
   		PersonLibraryVO vo=new PersonLibraryVO();
 		String domresult = null;
 		try {
 			 JSONArray list = vo.doInitJson(json);
 	         JSONObject object=  (JSONObject) list.get(0);
 	        // String companyname=object.getString("COMPANY_NAME");
 	        // String companycode=object.getString("COMPANY_DENGLU_CODE");
 	         domresult=this.jlPersonLibraryDao.updateShenhe(json,u_id,status,t_id);
    	 
    	 if("10".equals(status)){
    	 //****************************消息添加
	         Map<String ,String> messageMap= new HashMap<String ,String> ();
	         messageMap.put("TITLE", "监理人员审核");
	         messageMap.put("CONTENT", "人员审核通过");
	         //messageMap.put("USERTO", companycode);//人员的登录Code
	        /// messageMap.put("USERTONAME", companyname);//企业的名称
	
	         //fsMessageInfoService.insertVo(messageMap);
    	 }

  		} catch (DaoException e) {
 			SystemException.handleMessageException("监理人员信息-提交-数据更改成功,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
		
	}
	
	
	public String updateStatusOne(String u_id,String js) throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
   		PersonLibraryVO vo=new PersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=this.jlPersonLibraryDao.updatePass(u_id,js);

  		} catch (DaoException e) {
 			SystemException.handleMessageException("施工人员信息-入库-数据更改成功,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
		
	}
	
	
	public String queryCodeIsEmpty(String json) throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
   		PersonLibraryVO vo=new PersonLibraryVO();
 		String domresult = null;
 		try {
    	 domresult=this.jlPersonLibraryDao.queryCodeIsEmpty(json);

  		} catch (DaoException e) {
 			SystemException.handleMessageException("施工人员信息-根据条件查询是否为空-数据查询失败,请联系相关人员处理");
 		} finally {
 		}
    	return domresult;
		
	}
	
	public String queryStatusIsEmpty(String json,String bz,String personUID) throws Exception {
		
		String domresult=this.jlPersonLibraryDao.queryStatusIsEmpty(json,bz,personUID);
    	return domresult;
		
	}

	public String tuihui(String ids, String yijian) throws Exception {
		String domresult = this.jlPersonLibraryDao.tuihui(ids,yijian);
    	return domresult;
	}

	public String update(String msg, String jg) throws Exception {
		
		return this.jlPersonLibraryDao.update(msg,jg);
	}

	public void updateDshxx(Map<String, String> map) throws Exception {
		 
		
		 this.jlPersonLibraryDao.updateDshxx(map);
		 
		 String jg = map.get("jg");
		 String jlqyid =map.get("jlqyid");
		 String uid = map.get("uid");
		 String pname = map.get("pname");
		 if("10".equals(jg)){//人员审核通过
	    	 //****************************消息添加
	    		 
		         Map messageMap= new HashMap();
		         messageMap.put("TITLE", "监理人员审核");
		         messageMap.put("CONTENT", "人员<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"zxdtView('"+uid+"','jlrysh')\">"+pname+"</a>信息<span&nbsp;style='color:blue;'>审核通过</span>");
		         //messageMap.put("USERTO", companycode);//企业的登录Code
		         //messageMap.put("USERTONAME", companyname);//企业的名称
		         messageMap.put("SYSTEM_TYPE","JL");//系统类型
		         messageMap.put("COMPANY_UID", jlqyid);//对应的监理企业编号
		         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_JLQYRY);//消息类型
		         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_JL_RYGL);//涉及的权限点
		         
		         fsMessageInfoService.insertVo(messageMap);
	    	 }else if("20".equals(jg)){//人员审核未通过
	    	 //****************************消息添加
	    		 
		         Map messageMap= new HashMap();
		         messageMap.put("TITLE", "监理人员审核");
		         messageMap.put("CONTENT", "人员<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"zxdtView('"+uid+"','jlrysh')\">"+pname+"</a>信息<span&nbsp;style='color:red;'>审核未通过</span>");
		         //messageMap.put("USERTO", companycode);//人员的登录Code
		         //messageMap.put("USERTONAME", companyname);//企业的名称
		         messageMap.put("SYSTEM_TYPE","JL");//系统类型
		         messageMap.put("COMPANY_UID", jlqyid);//对应的施工企业编号
		         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_JLQYRY);//消息类型
		         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_JL_RYGL);//涉及的权限点
		         
		         fsMessageInfoService.insertVo(messageMap);
	    	 }
	}

	public String updatePerson(String msg) {
		PersonLibraryVO vo = new PersonLibraryVO();
		String resultVO = null;
		try {
			JSONArray list = vo.doInitJson(msg);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            String ywid=(String)obj.get("ywid");
            this.jlPersonLibraryDao.update(vo);
            if(vo!=null&&vo.getPerson_library_uid()!=null&&!"".equals(vo.getPerson_library_uid())){
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getPerson_library_uid(),FjlbManager.JL_SFZ_FJLB);
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getPerson_library_uid(),FjlbManager.JL_ZCZS_FJLB);
            	 
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getPerson_library_uid(),FjlbManager.JL_CYZS_FJLB);
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getPerson_library_uid(),FjlbManager.JL_LDHT_FJLB);
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getPerson_library_uid(),FjlbManager.JL_SBZM_FJLB);
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getPerson_library_uid(),FjlbManager.JL_XXWJ_FJLB);
            	 
            	 
            }
            resultVO = vo.getRowJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	public String jiesuo(String id) throws Exception{
		// TODO Auto-generated method stub
		return this.jlPersonLibraryDao.jiesuo(id);
	}

	public String suoding(String id, String yijian, String sjfw, String jzY) throws Exception{
		// TODO Auto-generated method stub
		return this.jlPersonLibraryDao.suoding( id,  yijian,  sjfw,  jzY);
	}
	
	

}
