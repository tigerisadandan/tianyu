/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpClkService.java
 * 创建日期： 2014-06-13 上午 11:34:41
 * 功能：    接口：审批业务材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 上午 11:34:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


import java.util.Date;
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
import com.ccthanking.business.spxx.dao.BuSpClkDao;
import com.ccthanking.business.spxx.service.BuSpClkService;
import com.ccthanking.business.spxx.vo.BuSpClkVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpClkService.java </p>
 * <p> 功能：审批业务材料库 </p>
 *
 * <p><a href="BuSpClkService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Service
public class BuSpClkServiceImpl extends Base1ServiceImpl<BuSpClkVO, String> implements BuSpClkService {

	private static Logger logger = LoggerFactory.getLogger(BuSpClkServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SP_CLK;
    
    private BuSpClkDao buSpClkDao;
    @Autowired
    private JsComClkService jsComClkService;

	
    @Autowired
	@Qualifier("buSpClkDaoImpl")
	public void setBuSpClkDao(BuSpClkDao buSpClkDao) {
		this.buSpClkDao = buSpClkDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpClkDao);
	}
   

	// @Override
    public String queryCondition(String json) throws Exception {
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpClkDao.queryCondition(json, null, null);

                  
        }catch (DaoException e) {
        	logger.error("审批业务材料库{}", e.getMessage());
        	SystemException.handleException("审批业务材料库查询失败",e);
		} finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpClkVO vo = new BuSpClkVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            
            vo.setValueFromJson(object);
            String ywid=object.getString("ywid");
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);

//            EventVO eventVO = EventManager.createEvent(ywlx, user);// 生成事件
//		    vo.setEvent_uid(eventVO.getSjbh());
            
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            
            // 插入
			buSpClkDao.save(vo);
            resultVO = vo.getRowJson();
                        
            FileUploadOldService.updateFjztOrYwidByYwid(ywid,vo.getClk_uid(),Constants.FS_FILEUPLOAD_FJLB_SPYWCL);//更新附件状态

       /**   
        * 屏蔽掉，暂时不从该处入口进行添加数据，不能判断该添加的业务材料库的材料是否就是要上传的材料
        * 而企业材料库里面所添加的节点都是要求上传的材料库节点
            //保存信息至企业材料库
            if(vo!=null&&vo.getClk_uid()!=null&&!"".equals(vo.getClk_uid())){
            	saveCompanyClk(vo);
            }           
      **/              
        } catch (DaoException e) {
            logger.error("审批业务材料库新增{}", e.getMessage());
            SystemException.handleException("审批业务材料库新增失败",e);
          } finally {
        }
        return resultVO;
    }
    
    public void saveCompanyClk(BuSpClkVO vo) throws Exception{
	
    	List<?> companyList=jsComClkService.getAllCompanyList(null);
    	String clkmc=vo.getClmc();
    	String clkid=vo.getClk_uid();
    	if(companyList!=null&&companyList.size()>0){
    		for(int i=0;i<companyList.size();i++){
    			Map maptemp=(Map) companyList.get(i);
		
    			if(vo.getCl_level().equals(Constants.YWCLKJB_QY)){//企业
//    				JSONObject object= new JSONObject();
    				String comid=(String)maptemp.get("COMID");
//    				String comname= (String)maptemp.get("COMNAME");
//    				String comaccount= (String)maptemp.get("COMACCOUNT");
    				
    				
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
    

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpClkVO vo = new BuSpClkVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject)list.get(0);
            vo.setValueFromJson(object);
            String ywid=object.getString("ywid");
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(ywlx, user);// 生成事件
//		    vo.setEvent_uid(eventVO.getSjbh());
          	
          	vo.setUpdate_date(new Date());
          	vo.setUpdate_name(user.getName());
          	vo.setUpdate_uid(user.getAccount());
          	
            // 修改
            buSpClkDao.update(vo);
            resultVO = vo.getRowJson();
            
            
            FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getClk_uid(),Constants.FS_FILEUPLOAD_FJLB_SPYWCL);

        } catch (DaoException e) {
            logger.error("审批业务材料库更新{}", e.getMessage());
            SystemException.handleException("审批业务材料库更新失败",e);
         } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpClkVO vo = new BuSpClkVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			buSpClkDao.delete(BuSpClkVO.class, vo.getClk_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务材料库删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("审批业务材料库{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "审批业务材料库删除失败", user, "", "");
            SystemException.handleException("审批业务材料库删除失败",e);
		} finally {
		}
		return resultVo;

	}

	public List<?> getSpClkListByType(Map temmap) {
		// TODO Auto-generated method stub
		return buSpClkDao.getSpClkListByType(temmap);
	}

	public String getYwid() {
	
		return buSpClkDao.getYwid();
	}


}
