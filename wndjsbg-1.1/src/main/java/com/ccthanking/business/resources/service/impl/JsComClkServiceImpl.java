/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    resources.service.JsComClkService.java
 * 创建日期： 2014-06-14 下午 05:05:25
 * 功能：    接口：企业材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-14 下午 05:05:25  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.resources.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.project.vo.JsComClkVO;
import com.ccthanking.business.resources.dao.JsComClkDao;
import com.ccthanking.business.resources.service.JsComClkService;
import com.ccthanking.business.spxx.service.BuSpClkService;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> JsComClkService.java </p>
 * <p> 功能：企业材料库 </p>
 *
 * <p><a href="JsComClkService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-14
 * 
 */

@Service
public class JsComClkServiceImpl extends Base1ServiceImpl<JsComClkVO, String> implements JsComClkService {
	private static Logger logger = LoggerFactory.getLogger(JsComClkServiceImpl.class);
	
    private JsComClkDao jsComClkDao;
    @Autowired
    private BuSpClkService buSpClkService;
    
	@Autowired
	@Qualifier("jsComClkDaoImpl")
	public void setJsComClkDao(JsComClkDao jsComClkDao) {
		this.jsComClkDao = jsComClkDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) jsComClkDao);
	}


	public JsComClkVO saveJsComClkVO(JsComClkVO vo)  throws Exception{
		User user = ActionContext.getCurrentUserInThread();

//        JsComClkVO vo1 = new JsComClkVO();

        try {
        	vo.setCreated_date(new Date());
        	vo.setCreated_name(user.getName());
        	vo.setCreated_uid(user.getUserSN());
			jsComClkDao.save(vo);
			
//			JSONArray list = vo1.doInitJson(vo.getRowJson());
//	        vo1.setValueFromJson((JSONObject) list.get(0));        
        } catch (DaoException e) {
            logger.error("企业材料库{}", e);
          
            SystemException.handleMessageException("企业材料库新增失败,请联系相关人员处理");
        } finally {
        }
        return vo;
	}
	
	
	//查询注册公司list  add by longchuxiong 20140617
	public List<?> getAllCompanyList(String json) {
		return jsComClkDao.getAllCompanyList(json);
	}


	public List<?> getAllCompanyClkList(JSONObject object) {
		
		return jsComClkDao.getAllCompanyClkList(object);
	}
	

	
	/**
	 * * *************************企业资料库初始化添加节点方法
	 *1、 用于企业注册审核通过之后调用，
	 *2、立项审核通过之后调用，
	 *3、项目审核通过之后调用 
	 * 
	 * 参数说明：
	 * type：QY\LX\XM三种类型（YW类型不在该处进行处理）
	 * companyid：企业ID 
	 * typename：节点名称（type为QY时对应公司名称;type为LX时对应立项名称;
	 * 				type为XM时对应项目名称;）
	 * typeid：节点对应实际记录的ID（type为QY时为空;type为LX时对应立项ID;
	 * 				type为XM时对应项目ID;）
	 * 返回值：true 或  false
	 * */
	public boolean saveCompanyClk(String type,String companyid,String typename,String typeid){
		boolean isResult=false;
		if(StringUtil.isNotBlankStr(type)&&Constants.YWCLKJB_QY.equals(type)){//企业数据初始化		
			try {
				JSONObject objecttemp= new JSONObject();
				objecttemp.put("NODE_TYPE", type);
				objecttemp.put("JS_COMPANY_UID", companyid);

				List<?> comclkList=jsComClkDao.getAllCompanyClkList(objecttemp);
				
				if(comclkList==null||comclkList.size()==0){
					//企业根节点
					JsComClkVO comclk= new JsComClkVO();		
					comclk.setJs_company_uid(companyid);
					comclk.setNode_name(typename);
					comclk.setNode_type(type);
					comclk.setEnabled("1");  
					JsComClkVO vo = this.saveJsComClkVO(comclk);
					
					saveCompanyClkQy(vo,type);//新增企业下的所有的材料节点，包括项目和立项根节点
				}
				
				isResult=true;
			} catch (Exception e) {
				logger.error("企业材料库节点企业类型节点初始化{}", e);	          
		        SystemException.handleMessageException("企业材料库节点初始化企业类型节点失败,请联系相关人员处理");
			}	
		}else if(StringUtil.isNotBlankStr(type)&&Constants.YWCLKJB_LX.equals(type)){//立项
			try{
				//立项名称根节点数据，节点类型：LX
				//公司ID，立项ID，立项名称				
				JSONObject objecttemp= new JSONObject();
				objecttemp.put("NODE_TYPE", "LXG");
				objecttemp.put("JS_COMPANY_UID", companyid);
				objecttemp.put("CLK_UID", "");
				List<?> comclkList=jsComClkDao.getAllCompanyClkList(objecttemp);
				
				if(comclkList!=null&&comclkList.size()>0){
					String comclkid=(String)((Map)comclkList.get(0)).get("ID");
					
					//检查是否已经添加过该项目的节点
					JSONObject obj= new JSONObject();
					obj.put("NODE_TYPE", type);
					obj.put("JS_COMPANY_UID", companyid);
					obj.put("P_COM_CJK_UID", comclkid);				
					obj.put("LIXIANG_UID", typeid);
					
					List<?> comclklx=jsComClkDao.getAllCompanyClkList(obj);
					
					if(comclklx==null||comclklx.size()==0){
						JsComClkVO comclk= new JsComClkVO();
						comclk.setP_com_cjk_uid(comclkid);		
						comclk.setJs_company_uid(companyid);
						comclk.setNode_name(typename);
						comclk.setNode_type(type);
						comclk.setLixiang_uid(typeid);
						comclk.setEnabled("1");  
						
						JsComClkVO vo = this.saveJsComClkVO(comclk);
						saveCompanyClkLx(vo, type);//新增立项下的所有的材料节点	
					}
				}
				isResult=true;
			} catch (Exception e) {
				logger.error("企业材料库立项类型节点初始化{}", e);	          
		        SystemException.handleMessageException("企业材料库新增立项类型节点初始化失败,请联系相关人员处理");
			}
		}else if(StringUtil.isNotBlankStr(type)&&Constants.YWCLKJB_XM.equals(type)){//项目
			try{
				 // 1、项目名称根节点数据，节点类型：XM
				//公司ID，项目ID，项目名称
				
				JSONObject objecttemp= new JSONObject();
				objecttemp.put("NODE_TYPE", "XMG");
				objecttemp.put("JS_COMPANY_UID", companyid);
				objecttemp.put("CLK_UID", "");
				List<?> comclkList=jsComClkDao.getAllCompanyClkList(objecttemp);
				if(comclkList!=null&&comclkList.size()>0){
					String comclkid=(String)((Map)comclkList.get(0)).get("ID");
					
					//检查是否已经添加过该项目的节点
					JSONObject obj= new JSONObject();
					obj.put("NODE_TYPE", type);
					obj.put("JS_COMPANY_UID", companyid);
					obj.put("P_COM_CJK_UID", comclkid);				
					obj.put("PROJECTS_UID", typeid);
					
					List<?> comclkxm=jsComClkDao.getAllCompanyClkList(obj);					
					
					if(comclkxm==null||comclkxm.size()==0){
						JsComClkVO comclk= new JsComClkVO();
						comclk.setP_com_cjk_uid(comclkid);		
						comclk.setJs_company_uid(companyid);
						comclk.setNode_name(typename);
						comclk.setNode_type(type);
						comclk.setProjects_uid(typeid);
						comclk.setEnabled("1");  
						
						JsComClkVO vo = this.saveJsComClkVO(comclk);
//						saveCompanyClkXm(vo, type);//新增项目下的所有的材料节点
						saveCompanyClkYw(vo, Constants.YWCLKJB_YW);//新建项目分期下面的所有业务材料节点  2014-11-11
					}
				}
				
				isResult=true;
			} catch (Exception e) {
				logger.error("企业材料库项目类型节点初始化{}", e);	          
		        SystemException.handleMessageException("企业材料库新增项目类型节点初始化失败,请联系相关人员处理");
			}
		}
		return isResult;
	}

	 /**
	 * 业务材料初始化
	 * 1、业务材料节点数据，节点类型：CL
	 * CLK_UID   JS_COMPANY_UID   PROJECTS_UID
	 * */
    public void saveCompanyClkYw(JsComClkVO vo, String type){
    	Map temmap=new HashMap();
		temmap.put("TYPE", type);
		List<?> spclkList=buSpClkService.getSpClkListByType(temmap);
	
		if(spclkList!=null&&spclkList.size()>0){
			for(int i=0;i<spclkList.size();i++){
				Map clk=(Map)spclkList.get(i);
				String ckmc =(String)clk.get("CLMC");
				String clk_uid=(String)clk.get("CLK_UID");
				String clk_clsx=(String)clk.get("CLSX");
				
				JsComClkVO comclk= new JsComClkVO();	
				
				comclk.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
				comclk.setJs_company_uid(vo.getJs_company_uid());
				comclk.setNode_name(ckmc);
				comclk.setNode_type(Constants.YWCLKJB_CL);
				comclk.setProjects_uid(vo.getProjects_uid());//项目ID
				comclk.setClk_uid(clk_uid);				
				comclk.setEnabled("1");  
				comclk.setDescribe(clk_clsx);

				try {
					JsComClkVO comclkvo= this.saveJsComClkVO(comclk);
					
					/**
					 * 更新所属项目材料上传记录
					 * 需要1、项目申请通过上传的附件，在FS_FILEUPLOAD 中的 FJLB、YWID、FJZT、GLID2不为空
					 * YWID 对应 项目ID
					 * FJLB 从Constants类里面进行获取或自行添加，作为识别码Constants.FS_FILEUPLOAD_FJLB_XMCL;
					 * FJZT 需要在项目申请成功之后把该状态改成1即可用状态
					 * GLID2 对应审批业务材料库ID
					 * 进行修正动作时根据以上条件，添加GLID1字段值   即企业材料库id
					 * */
//					String fjlb=Constants.FS_FILEUPLOAD_FJLB_XMCL;////2014-11-11附件类别统一替换成bu_sp_clk的uid
//					String fjlb=clk_uid;
//					FileUploadOldService.updateFileUploadGLID1(vo.getProjects_uid(), clk_uid, fjlb, comclkvo.getJs_com_cjk_uid());
				
				} catch (Exception e) {
					logger.error("企业材料库新增{}", e);	          
			        SystemException.handleMessageException("企业材料库新增失败,请联系相关人员处理");
				}
			}
		}
    }  
    
    /**
	 * 项目材料初始化
	 * 1、项目材料节点数据，节点类型：CL
	 * CLK_UID   JS_COMPANY_UID   PROJECTS_UID
	 * */
    public void saveCompanyClkXm(JsComClkVO vo, String type){
    	Map temmap=new HashMap();
		temmap.put("TYPE", type);
		List<?> spclkList=buSpClkService.getSpClkListByType(temmap);
	
		if(spclkList!=null&&spclkList.size()>0){
			for(int i=0;i<spclkList.size();i++){
				Map clk=(Map)spclkList.get(i);
				String ckmc =(String)clk.get("CLMC");
				String clk_uid=(String)clk.get("CLK_UID");
				
				JsComClkVO comclk= new JsComClkVO();	
				
				comclk.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
				comclk.setJs_company_uid(vo.getJs_company_uid());
				comclk.setNode_name(ckmc);
				comclk.setNode_type(Constants.YWCLKJB_CL);
				comclk.setProjects_uid(vo.getProjects_uid());//项目ID
				comclk.setClk_uid(clk_uid);				
				comclk.setEnabled("1");  
				comclk.setDescribe((String) clk.get("CLSX"));

				try {
					JsComClkVO comclkvo= this.saveJsComClkVO(comclk);
					
					/**
					 * 更新所属项目材料上传记录
					 * 需要1、项目申请通过上传的附件，在FS_FILEUPLOAD 中的 FJLB、YWID、FJZT、GLID2不为空
					 * YWID 对应 项目ID
					 * FJLB 从Constants类里面进行获取或自行添加，作为识别码Constants.FS_FILEUPLOAD_FJLB_XMCL;
					 * FJZT 需要在项目申请成功之后把该状态改成1即可用状态
					 * GLID2 对应审批业务材料库ID
					 * 进行修正动作时根据以上条件，添加GLID1字段值   即企业材料库id
					 * */
//					String fjlb=Constants.FS_FILEUPLOAD_FJLB_XMCL;////2014-11-11附件类别统一替换成bu_sp_clk的uid
					String fjlb=clk_uid;
					FileUploadOldService.updateFileUploadGLID1(vo.getProjects_uid(), clk_uid, fjlb, comclkvo.getJs_com_cjk_uid());
				
				} catch (Exception e) {
					logger.error("企业材料库新增{}", e);	          
			        SystemException.handleMessageException("企业材料库新增失败,请联系相关人员处理");
				}
			}
		}
    }  
	
	/**
	 * 立项材料初始化
	 * 1、立项材料节点数据，     节点类型：CL
	 * CLK_UID   JS_COMPANY_UID   LIXIANG_UID
	 * */
    public void saveCompanyClkLx(JsComClkVO vo,String type){
    	Map temmap=new HashMap();
		temmap.put("TYPE", type);
		List<?> spclkList=buSpClkService.getSpClkListByType(temmap);
	
		if(spclkList!=null&&spclkList.size()>0){	
			for(int i=0;i<spclkList.size();i++){
				Map clk=(Map)spclkList.get(i);
				String ckmc =(String)clk.get("CLMC");
				String clk_uid=(String)clk.get("CLK_UID");
				
				JsComClkVO comclk= new JsComClkVO();	
				
				comclk.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
				comclk.setJs_company_uid(vo.getJs_company_uid());
				comclk.setNode_name(ckmc);
				comclk.setNode_type(Constants.YWCLKJB_CL);
				comclk.setLixiang_uid(vo.getLixiang_uid());
				comclk.setClk_uid(clk_uid);				
				comclk.setEnabled("1");  

				try {
					JsComClkVO comclkvo= this.saveJsComClkVO(comclk);
					
					/**
					 * 更新所属立项材料上传记录
					 * 需要1、立项之前上传的附件，在FS_FILEUPLOAD 中的 FJLB、YWID、FJZT、GLID2不为空
					 * YWID 对应 立项ID
					 * FJLB 从Constants类里面进行获取或自行添加，作为识别码 Constants.FS_FILEUPLOAD_FJLB_LXCL
					 * FJZT 需要在立项成功之后把该状态改成1即可用状态
					 * GLID2 对应审批业务材料库ID
					 * 进行修正动作时根据以上条件，添加GLID1字段值   即企业材料库id
					 * */
//					String fjlb=Constants.FS_FILEUPLOAD_FJLB_LXCL;
					String fjlb=clk_uid;//2014-11-11附件类别统一替换成bu_sp_clk的uid
					FileUploadOldService.updateFileUploadGLID1(vo.getLixiang_uid(), clk_uid, fjlb, comclkvo.getJs_com_cjk_uid());
				
				} catch (Exception e) {
					logger.error("企业材料库{}", e);	          
			        SystemException.handleMessageException("企业材料库新增失败,请联系相关人员处理");
				}
			}
		}
    }
    
    
	/**
	 * 企业材料初始化，并修正注册过程中上传的附件的GLID1的值
	 * 1、企业根节点，节点类型：QY
	 * 2、立项的根节点，节点类型：LXG
	 * 3、项目的根节点，节点类型：XMG
	 * 4、企业材料节点数据，节点类型：CL，即审批业务材料库中级别都是QY的材料数据
	 * CLK_UID   JS_COMPANY_UID
	 * */
	public void saveCompanyClkQy(JsComClkVO vo,String type){
		Map temmap=new HashMap();
		temmap.put("TYPE", type);
		//通过类型查找审批材料数据，并添加到企业材料库
		List<?> spclkList=buSpClkService.getSpClkListByType(temmap);
		if(spclkList!=null&&spclkList.size()>0){
			for(int i=0;i<spclkList.size();i++){
				Map clk=(Map)spclkList.get(i);
				String  ckmc =(String)clk.get("CLMC");
				String clk_uid=(String)clk.get("CLK_UID");
				String cllx=(String)clk.get("CLLX");
				JsComClkVO comclk= new JsComClkVO();	
				
				comclk.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
				comclk.setJs_company_uid(vo.getJs_company_uid());
				comclk.setNode_name(ckmc);
				comclk.setNode_type(Constants.YWCLKJB_CL);
				comclk.setClk_uid(clk_uid);				
				comclk.setEnabled("1");  
				
				
				try {
					JsComClkVO comclkvo= this.saveJsComClkVO(comclk);
					
					/**
					 * 更新所属企业材料上传记录
					 * 需要1、企业注册之前上传的附件，在FS_FILEUPLOAD 中的 FJLB、YWID、FJZT、GLID2不为空
					 * YWID 对应 企业ID
					 * FJLB 从Constants类里面进行获取或自行添加，作为识别码  Constants.FS_FILEUPLOAD_FJLB_QYCL; 对应即可
					 * FJZT 需要在注册成功之后把该状态改成1即可用状态
					 * GLID2 对应审批业务材料库ID
					 * 进行修正动作时根据以上条件，添加GLID1字段值   即企业材料库id
					 * */
				/**	String fjlb=Constants.FS_FILEUPLOAD_FJLB_QYCL;
					if(!StringUtil.isBlankStr(cllx)&&cllx.equals("0")){
						fjlb=Constants.FS_FILEUPLOAD_FJLB_QYYYZZCL;//营业执照
					}else if(!StringUtil.isBlankStr(cllx)&&cllx.equals("1")){
						fjlb=Constants.FS_FILEUPLOAD_FJLB_QYZZJGDMCL;//组织机构			
					}
				**/
					String fjlb=clk_uid;//2014-11-11附件类别统一替换成bu_sp_clk的uid
					FileUploadOldService.updateFileUploadGLID1(vo.getJs_company_uid(), clk_uid, fjlb, comclkvo.getJs_com_cjk_uid());
				
				} catch (Exception e) {
					logger.error("企业材料库材料新增{}", e);	          
			        SystemException.handleMessageException("企业材料库材料新增失败,请联系相关人员处理");
				}
			}
		}	
		User user = ActionContext.getCurrentUserInThread();
		
//		保存项目和立项根节点-----暂时先这样处理
		JsComClkVO comclk= new JsComClkVO();	
		comclk.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
		comclk.setJs_company_uid(vo.getJs_company_uid());
		comclk.setNode_name("项目");
		comclk.setNode_type("XMG");			
		comclk.setEnabled("1");  
		comclk.setCreated_date(new Date());
		comclk.setCreated_name(user.getName());
		comclk.setCreated_uid(user.getUserSN());
		jsComClkDao.save(comclk);
		
		JsComClkVO comclk1= new JsComClkVO();	
		comclk1.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
		comclk1.setJs_company_uid(vo.getJs_company_uid());
		comclk1.setNode_name("立项");
		comclk1.setNode_type("LXG");			
		comclk1.setEnabled("1"); 
		comclk1.setCreated_date(new Date());
		comclk1.setCreated_name(user.getName());
		comclk1.setCreated_uid(user.getUserSN());	
		jsComClkDao.save(comclk1);
		
		
		JsComClkVO comclk2= new JsComClkVO();	
		comclk2.setP_com_cjk_uid(vo.getJs_com_cjk_uid());
		comclk2.setJs_company_uid(vo.getJs_company_uid());
		comclk2.setNode_name("事务");
		comclk2.setNode_type("SW");			
		comclk2.setEnabled("1"); 
		comclk2.setCreated_date(new Date());
		comclk2.setCreated_name(user.getName());
		comclk2.setCreated_uid(user.getUserSN());	
		jsComClkDao.save(comclk2);
		
	}
 
}
