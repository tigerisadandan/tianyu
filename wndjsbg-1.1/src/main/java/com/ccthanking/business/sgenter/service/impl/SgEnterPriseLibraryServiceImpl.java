/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgEnterPriseLibraryService.java
 * 创建日期： 2014-04-09 上午 10:51:19
 * 功能：    接口：业务页面模版表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-09 上午 10:51:19  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.x500.DirectoryString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.common.vo.SgdwUserVO;
import com.ccthanking.business.project.vo.JsCompanyVO;
import com.ccthanking.business.sgenter.dao.SgECreditCommendRewardDao;
import com.ccthanking.business.sgenter.dao.SgECreditProjectsDao;
import com.ccthanking.business.sgenter.dao.SgEnterPriseLibraryDao;
import com.ccthanking.business.sgenter.dao.SgEnterpriseZenZizhiDao;
import com.ccthanking.business.sgenter.service.SgEnterPriseLibraryService;
import com.ccthanking.business.sgenter.vo.SgEnterpriseLibraryVO;
import com.ccthanking.business.sgsx.dao.BuSpywSggdsjrgwsbDao;
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
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;



/**
 * <p> SgEnterPriseLibraryService.java </p>
 * <p> 功能：业务页面模版表 </p>
 *
 * <p><a href="SgEnterPriseLibraryService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-09
 * 
 */

@Service
public class SgEnterPriseLibraryServiceImpl extends Base1ServiceImpl<SgEnterpriseLibraryVO, String> implements SgEnterPriseLibraryService {

	private static Logger logger = LoggerFactory.getLogger(SgEnterPriseLibraryServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.SG_ENTERPRISE_LIBRARY;
	@Autowired
	private SgEnterPriseLibraryDao sgEnterPriseLibraryDao;
	@Autowired
	private SgEnterpriseZenZizhiDao sgEnterpriseZenZizhiDao;
	@Autowired
	private SgECreditCommendRewardDao sgECreditCommendRewardDao;
	@Autowired
	private SgECreditProjectsDao sgECreditProjectsDao;
	@Autowired
	private FsMessageInfoService fsMessageInfoService;
    // @Override
    public String queryCondition(String json,Map map) throws Exception {
    
        String domresult = sgEnterPriseLibraryDao.queryCondition(json, map);
        
        return domresult;

    }
    
    @Autowired
	@Qualifier("sgEnterPriseLibraryDaoImpl")
	public void setBuSpywSggdsjrgwsbDao(SgEnterPriseLibraryDao sgEnterPriseLibraryDao) {
		this.sgEnterPriseLibraryDao= sgEnterPriseLibraryDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) sgEnterPriseLibraryDao);
	}
    
    
    // @Override
    public String insert(String json,Map files) throws Exception {

        String resultVO = sgEnterPriseLibraryDao.insert(json,files);
        
        return resultVO;

    }

    // @Override
    public String update(String json,Map files,String id) throws Exception {
    	
        SgEnterpriseLibraryVO vo = new SgEnterpriseLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        
    	 JSONArray list = vo.doInitJson(json);
         JSONObject object=  (JSONObject) list.get(0);
         String Status=object.getString("SHENHE_JIEGUO");
         String resultVO = sgEnterPriseLibraryDao.update(json,files);
         
        if(id!=null){
 		SgEnterpriseLibraryVO sgpvo=this.findById(id);//添加SGDW_USER 默认用户
        String sql = "select * from Sgdw_User where Sg_Company_Uid="+sgpvo.getSg_company_uid()+" and admin_y= 1 ";
        String [][]res = DBUtil.query(sql);
           if(res==null){ //如有管理员 无需再默认添加
        	   SgdwUserVO sgvo=new SgdwUserVO();
        		sgvo.setSgdw_user_uid(DBUtil.getSequenceValue("SGDW_USER_UID"));
        		sgvo.setSg_company_uid(sgpvo.getSg_company_uid());
        		sgvo.setUser_code(sgpvo.getDenglu_code());
        		sgvo.setUser_name("管理员");
        		sgvo.setPwd(sgpvo.getPwd());
        		sgvo.setMima(sgpvo.getMima());
        		sgvo.setAdmin_y("1");
        		sgvo.setCreated_date(new Date());
        		sgEnterPriseLibraryDao.save(sgvo);
           }
        }

        if("10".equals(Status)){
        	
        	 //****************************消息添加
   	         Map messageMap= new HashMap();
   	         messageMap.put("TITLE", "施工企业审核");
   	         messageMap.put("CONTENT", "企业审核通过");
   	         messageMap.put("USERTO", object.getString("DENGLU_CODE"));//企业的登录code
   	         messageMap.put("USERTONAME",object.getString("COMPANY_NAME"));//企业的名称
   	
   	         fsMessageInfoService.insertVo(messageMap);
        }
        return resultVO;

    }
     public String updatetuihui(String ids,String liyou) throws Exception {
    	
    	 User user = ActionContext.getCurrentUserInThread();
    	 Connection conn = DBUtil.getConnection();
    	 
 			try {
 				String [] sgid=ids.split(",");
 				for (int i = 0; i < sgid.length; i++) {
 					String id=sgid[i];
 					if("".equals(id)){
 						break;
 					}
 					String qusql="select * from SG_ENTERPRISE_LIBRARY where SG_ENTERPRISE_LIBRARY_uid='"+id+"'"; //新增 未通过20 记录
 	 				List ls=DBUtil.queryReturnList(conn, qusql);
 	 				SgEnterpriseLibraryVO savevo=new SgEnterpriseLibraryVO();
 	 				JSONObject aa= JSONObject.fromObject(ls.get(0));
 	 				savevo.setValueFromJson(JSONObject.fromObject(ls.get(0)));
 	 				savevo.setSg_enterprise_library_uid(DBUtil.getSequenceValue("SG_ENTERPRISE_LIBRARY_UID"));
 	 				savevo.setStatus("20");
 	 				savevo.setShenhe_ren(user.getUserSN());
 	 				savevo.setShenhe_date(new Date());
 	 				savevo.setShenhe_jieguo("20");
 	 				savevo.setShenhe_yijian(liyou);
 	 				sgEnterPriseLibraryDao.save(savevo);
 	 				
 	 				SgEnterpriseLibraryVO updatevo=new SgEnterpriseLibraryVO(); //修改30状态 审核结果为未通过
 	 				updatevo.setSg_enterprise_library_uid(id);
 	 				updatevo.setShenhe_jieguo("20");
 	 				updatevo.setShenhe_ren(user.getUserSN());
 	 				updatevo.setShenhe_date(new Date());
 	 				updatevo.setShenhe_yijian(liyou);
 	 				sgEnterPriseLibraryDao.update(updatevo);
				}
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} finally {
 				DBUtil.closeConnetion(conn);
 			}
 		return null;

    }

    // @Override
    public String delete(String json) throws Exception {

        String resultVO = sgEnterPriseLibraryDao.delete(json);
        
        return resultVO;

    }

	public String queryAll() throws Exception {
		String resultList = sgEnterPriseLibraryDao.queryAll();
		return resultList;
	}

	public SgEnterpriseLibraryVO getUserByUsernameAndPassword(String dengluCode,
			String pwd) throws Exception {
		SgEnterpriseLibraryVO vo = sgEnterPriseLibraryDao.getUserByUsernameAndPassword(dengluCode);
		if(vo==null){
			//没有该用户
			return null;	
		}
		if(!pwd.equals(vo.getPwd())){
			//密码不匹配
			return null;
		}
		return vo;
	}

	public String queryCodeIsEmpty(String json) throws Exception {
		return sgEnterPriseLibraryDao.queryCodeIsEmpty(json);
	}

	public String queryApproval(String json) throws Exception {
		return sgEnterPriseLibraryDao.queryApproval(json);
	}

	public String loadFiles(Map map) throws Exception {
		return sgEnterPriseLibraryDao.loadFiles(map);
	}

	public void updateCopySGXX() throws Exception{
		boolean hasEdit = sgEnterPriseLibraryDao.queryCompanyForEdit();
		//如果不存在状态为40的信息,才执行copy操作
		if(hasEdit){
			//复制企业信息
			String sg_uid = sgEnterPriseLibraryDao.updateCopyCompany();
			//复制增项资质信息
			sgEnterpriseZenZizhiDao.updateCopyZizhi(sg_uid);
			//复制奖项信息
			sgECreditCommendRewardDao.updateCopyJxxx(sg_uid);
			//复制项目信息
			sgECreditProjectsDao.updateCopyXmxx(sg_uid);
		}
	}

	public String insertToOld(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		if(StringUtils.isNotBlank(json)){
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对老系统<企业信息>数据同步", user, "", "");
			return sgEnterPriseLibraryDao.insertToOld(json);
		}
		LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对老系统<企业信息>数据同步失败", user, "", "");
		return null;
	}

	public String queryAppList(String json, Map map) throws Exception {
		return sgEnterPriseLibraryDao.queryAppList(json, map);
	}

	public String queryOnSp(String json) throws Exception {
		return sgEnterPriseLibraryDao.queryOnSp(json);
	}

	public String updateCopyByStatus(Map map) throws Exception {
		
		return sgEnterPriseLibraryDao.updateCopyByStatus(map);
			
	}

	public void updateDshxx(Map map) throws Exception {
	 	String jg = (String) map.get("jg");
	 	String companycode = (String) map.get("denglu_code");//企业登陆code
	 	String companyname = (String)map.get("company_name");//企业名称
	 	String companyuid = (String)map.get("companyUid");//企业编号
		if("10".equals(jg)){//人员审核通过
	    	 //****************************消息添加
		         Map messageMap= new HashMap();
		         messageMap.put("TITLE", "企业信息审核");
		         messageMap.put("CONTENT", "<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"qyglView()\">企业信息</a><span&nbsp;style='color:blue;'>审核通过</span>");
		         messageMap.put("USERTO", companycode);//企业的登录Code
		         messageMap.put("USERTONAME", companyname);//企业的名称
		         messageMap.put("SYSTEM_TYPE","SG");//系统类型
		         messageMap.put("COMPANY_UID", companyuid);//对应的施工企业编号
		         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_SGQYXX);//消息类型
		         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_SG_QYGL);//涉及的权限点
		         
		         fsMessageInfoService.insertVo(messageMap);
		}else if("20".equals(jg)){//人员审核不通过
			 //****************************消息添加
			Map messageMap= new HashMap();
	         messageMap.put("TITLE", "企业信息审核");
	         messageMap.put("CONTENT", "<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"qyglView()\">企业信息</a><span&nbsp;style='color:red;'>审核不通过</span>");
	         messageMap.put("USERTO", companycode);//企业的登录Code
	         messageMap.put("USERTONAME", companyname);//企业的名称
	         messageMap.put("SYSTEM_TYPE","SG");//系统类型
	         messageMap.put("COMPANY_UID", companyuid);//对应的施工企业编号
	         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_SGQYXX);//消息类型
	         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_SG_QYGL);//涉及的权限点
	         
	         fsMessageInfoService.insertVo(messageMap);
		}
		sgEnterPriseLibraryDao.updateDshxx(map);
	}
	
	public String updateCopyToRk(String json) throws Exception{
		if (StringUtils.isBlank(json)) {
			return null;
		}
		JSONObject data = JSONObject.fromObject(json);
		JSONObject data2 = JSONObject.fromObject(JSONObject.fromObject(data.getString("response")).getJSONArray("data").getString(0));
		if("10".equals(data2.getString("STATUS"))){
			Map map = new HashMap();
			map.put("uid", data2.getString("SG_ENTERPRISE_LIBRARY_UID"));
			map.put("status", "1");
			
			//不需要再加角色了  jiang
			//sgEnterPriseLibraryDao.insertRole(data2.getString("SG_ENTERPRISE_LIBRARY_UID"));
			
			return sgEnterPriseLibraryDao.updateCopyByStatus(map);
		}
		return null;
	}

	public void updateQueren(String uid) throws Exception {
		sgEnterPriseLibraryDao.updateQueren(uid);
	}
	
	
}
