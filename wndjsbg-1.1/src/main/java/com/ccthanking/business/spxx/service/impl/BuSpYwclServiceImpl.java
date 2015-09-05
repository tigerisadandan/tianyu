/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpYwclService.java
 * 创建日期： 2014-06-18 上午 10:56:57
 * 功能：    接口：审批业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-18 上午 10:56:57  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.project.vo.JsComClkVO;
import com.ccthanking.business.resources.service.JsComClkService;
import com.ccthanking.business.spxx.dao.BuSpYwclDao;
import com.ccthanking.business.spxx.service.BuSpClkService;
import com.ccthanking.business.spxx.service.BuSpYwclService;
import com.ccthanking.business.spxx.vo.BuSpClkVO;
import com.ccthanking.business.spxx.vo.BuSpYwclVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpYwclService.java </p>
 * <p> 功能：审批业务材料 </p>
 *
 * <p><a href="BuSpYwclService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-18
 * 
 */

@Service
public class BuSpYwclServiceImpl extends Base1ServiceImpl<BuSpYwclVO, String> implements BuSpYwclService {

	private static Logger logger = LoggerFactory.getLogger(BuSpYwclServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SP_YWCL;
    
    private BuSpYwclDao buSpYwclDao;
    @Autowired
    private JsComClkService jsComClkService;
    @Autowired
    private BuSpClkService buSpClkService;


    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpYwclDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务材料>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务材料查询{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "审批业务材料查询失败", user, "", "");
			SystemException.handleMessageException("审批业务材料查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpYwclVO vo = new BuSpYwclVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            vo.setEvent_uid(new RandomGUID().toString());
            vo.setSerial_no(buSpYwclDao.queryMaxXh(vo.getSpyw_uid()));
            // 插入
			buSpYwclDao.save(vo);
            resultVO = vo.getRowJson();

            
            //需要上传的材料，则保存该材料级别到企业材料库中    by 20140709 longchuxiong
            if(vo!=null&&vo.getSfysc()!=null&&"1".equals(vo.getSfysc())&&vo.getClk_uid()!=null&&!"".equals(vo.getClk_uid())){
            	saveCompanyClk(vo.getClk_uid());	
            }
            
            
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "审批业务材料新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("审批业务材料新增{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "审批业务材料新增失败", user, "", "");
            SystemException.handleMessageException("审批业务材料新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpYwclVO vo = new BuSpYwclVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

          	

            // 修改
            buSpYwclDao.update(vo);
            resultVO = vo.getRowJson();

            
          //需要上传的材料，则保存该材料级别到企业材料库中    by 20140709 longchuxiong
            if(vo!=null&&vo.getSfysc()!=null&&"1".equals(vo.getSfysc())&&vo.getClk_uid()!=null&&!"".equals(vo.getClk_uid())){
            	saveCompanyClk(vo.getClk_uid());	
            }
            
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务材料修改成功", user, "", "");

        } catch (DaoException e) {
        	e.printStackTrace();
            logger.error("审批业务材料修改{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "审批业务材料修改失败", user, "", "");
            SystemException.handleMessageException("审批业务材料修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpYwclVO vo = new BuSpYwclVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			buSpYwclDao.delete(BuSpYwclVO.class, vo.getYwcl_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务材料删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("审批业务材料删除{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "审批业务材料删除失败", user, "", "");
            SystemException.handleMessageException("审批业务材料删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpYwclDaoImpl")
	public void setBuSpYwclDao(BuSpYwclDao buSpYwclDao) {
		this.buSpYwclDao = buSpYwclDao;
	}
	
	
	/**
	 * 添加
	 * */
	public void saveCompanyClk(String clkuid) throws Exception{
			BuSpClkVO vo=buSpClkService.findById(clkuid);
			
	    	List<?> companyList=jsComClkService.getAllCompanyList(null);
	    	String clkmc=vo.getClmc();
	    	String clkid=vo.getClk_uid();
	    	if(companyList!=null&&companyList.size()>0){
	    		for(int i=0;i<companyList.size();i++){
	    			Map maptemp=(Map) companyList.get(i);
			
	    			if(vo.getCl_level().equals(Constants.YWCLKJB_QY)){//企业
//	    				JSONObject object= new JSONObject();
	    				String comid=(String)maptemp.get("COMID");
//	    				String comname= (String)maptemp.get("COMNAME");
//	    				String comaccount= (String)maptemp.get("COMACCOUNT");
	    				
	    				
	    				JSONObject objecttemp= new JSONObject();
	        			objecttemp.put("NODE_TYPE", Constants.YWCLKJB_QY);
	        			objecttemp.put("JS_COMPANY_UID", comid);
	        			objecttemp.put("CLK_UID", "");
	//---------------------------------------------是否需要加判断 重名之类的判断
	        			List<?> comclkList=jsComClkService.getAllCompanyClkList(objecttemp);
	        			Map map=(Map)comclkList.get(0);
	        			
	        			String qyclkid=(String)map.get("ID");

	        			
	        			//组装条件查询判断是否已经存在，已存在不再进行添加
	        			JSONObject obj= new JSONObject();
	        			obj.put("NODE_TYPE", Constants.YWCLKJB_CL);
	        			obj.put("JS_COMPANY_UID", comid);
	        			obj.put("CLK_UID", clkid);
	        			obj.put("P_COM_CJK_UID", qyclkid);
	        			
	        			List<?> tempList=jsComClkService.getAllCompanyClkList(objecttemp);
	        			
	        			if(tempList==null||tempList.size()==0){
		    				JsComClkVO clkvo= new JsComClkVO();
		        			clkvo.setP_com_cjk_uid(qyclkid);
		        			clkvo.setJs_company_uid(comid);
		        			clkvo.setNode_name(clkmc);
		        			clkvo.setNode_type(Constants.YWCLKJB_CL);
		        			clkvo.setClk_uid(clkid);
		        			clkvo.setEnabled("1");
		    				
		    				jsComClkService.saveJsComClkVO(clkvo);
	        			}	
	    				
	    	    	} else if(vo.getCl_level().equals(Constants.YWCLKJB_LX)){//立项
	    	    		
	    				String comid=(String)maptemp.get("COMID");
	    				
	    				JSONObject objecttemp= new JSONObject();
	        			objecttemp.put("NODE_TYPE", Constants.YWCLKJB_LX);
	        			objecttemp.put("JS_COMPANY_UID", comid);
	        			objecttemp.put("CLK_UID", "");
	        			
	        			
	        			List<?> comclkList=jsComClkService.getAllCompanyClkList(objecttemp);
	        			if(comclkList!=null&&comclkList.size()>0){
	        				for(int c=0;c<comclkList.size();c++){
	        					Map map=(Map)comclkList.get(c);
	                			
	                			String qyclkid=(String)map.get("ID");
	                			String lxid=(String)map.get("LXID");
	            				
	                			//组装条件查询判断是否已经存在，已存在不再进行添加
	                			JSONObject obj= new JSONObject();
	                			obj.put("NODE_TYPE", Constants.YWCLKJB_CL);
	                			obj.put("JS_COMPANY_UID", comid);
	                			obj.put("CLK_UID", clkid);
	                			obj.put("P_COM_CJK_UID", qyclkid);
	                			
	                			List<?> tempList=jsComClkService.getAllCompanyClkList(objecttemp);
	                			
	                			if(tempList==null||tempList.size()==0){
		            				JsComClkVO clkvo= new JsComClkVO();
		                			clkvo.setP_com_cjk_uid(qyclkid);
		                			clkvo.setJs_company_uid(comid);
		                			clkvo.setNode_name(clkmc);
		                			clkvo.setNode_type(Constants.YWCLKJB_CL);
		                			clkvo.setClk_uid(clkid);
		                			clkvo.setLixiang_uid(lxid);//立项ID
		                			clkvo.setEnabled("1");  			
		            				
		            				jsComClkService.saveJsComClkVO(clkvo);
	                			}
	        				}
	        			}
		
	    	    	}else if(vo.getCl_level().equals(Constants.YWCLKJB_XM)){//项目
	    	    		String comid=(String)maptemp.get("COMID");
	    				
	    				JSONObject objecttemp= new JSONObject();
	        			objecttemp.put("NODE_TYPE", Constants.YWCLKJB_XM);
	        			objecttemp.put("JS_COMPANY_UID", comid);
	        			objecttemp.put("CLK_UID", "");
	        			
	        			//查看企业材料库中节点类型为XM的数据，作为后面CL的父节点
	        			List<?> comclkList=jsComClkService.getAllCompanyClkList(objecttemp);
	        			if(comclkList!=null&&comclkList.size()>0){
	        				for(int c=0;c<comclkList.size();c++){
	        					Map map=(Map)comclkList.get(c);
	                			
	                			String qyclkid=(String)map.get("ID");
	                			String proid=(String)map.get("PROID");
	            				
	                			//组装条件查询判断是否已经存在，已存在不再进行添加
	                			JSONObject obj= new JSONObject();
	                			obj.put("NODE_TYPE", Constants.YWCLKJB_CL);
	                			obj.put("JS_COMPANY_UID", comid);
	                			obj.put("CLK_UID", clkid);
	                			obj.put("P_COM_CJK_UID", qyclkid);
	                			
	                			List<?> tempList=jsComClkService.getAllCompanyClkList(objecttemp);
	                			
	                			if(tempList==null||tempList.size()==0){
	                				JsComClkVO clkvo= new JsComClkVO();
	                    			clkvo.setP_com_cjk_uid(qyclkid);
	                    			clkvo.setJs_company_uid(comid);
	                    			clkvo.setNode_name(clkmc);
	                    			clkvo.setNode_type(Constants.YWCLKJB_CL);
	                    			clkvo.setClk_uid(clkid);//项目ID
	                    			clkvo.setProjects_uid(proid);
	                    			clkvo.setEnabled("1");  			
	                				
	                				jsComClkService.saveJsComClkVO(clkvo);
	                			}	
	        				}
	        			}
	    	    	}
	    		}	
	    	}
	    }
	    
    
}
