/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywPssgtscsqService.java
 * 创建日期： 2014-06-04 上午 10:13:09
 * 功能：    接口：排水方案施工图审查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-04 上午 10:13:09  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywPssgtscsqDao;
import com.ccthanking.business.sp.service.BuSpywPssgtscsqService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywPssgtscsqVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywPssgtscsqService.java </p>
 * <p> 功能：排水方案施工图审查 </p>
 *
 * <p><a href="BuSpywPssgtscsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-04
 * 
 */

@Service
public class BuSpywPssgtscsqServiceImpl extends Base1ServiceImpl<BuSpywPssgtscsqVO, String> implements BuSpywPssgtscsqService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywPssgtscsqServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_PSSGTSCSQ;
    @Autowired
    private BuSpLzhfService buSpLzhfService;
    
    private BuSpywPssgtscsqDao buSpywPssgtscsqDao;
    @Autowired
	@Qualifier("buSpywPssgtscsqDaoImpl")
	public void setBuSpywPssgtscsqDao(BuSpywPssgtscsqDao buSpywPssgtscsqDao) {
		this.buSpywPssgtscsqDao = buSpywPssgtscsqDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywPssgtscsqDao);

    }
 // @Override
 public String download(String id) throws Exception {
 	
	 	BuSpywPssgtscsqVO tmp =this.findById(id);
	 	
	 	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    	String fileName_pre = Constants.getString("template_word_js_15", "无锡新区排水施工图审查申请表");
    	String templateName=fileName_pre;
    	String fileName="无锡新区排水施工图审查申请表"+id;//+tmp.getPsfayssq_uid();
    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	
    	
    	
    	//复选框的判断
    	String Wszx_gyws=tmp.getWszx_gyws();
    	String Wsxz_shws=tmp.getWsxz_shws();
    	String Sfclml=tmp.getSfclml();
    	
    	if(StringUtils.isBlank(Wszx_gyws)){
    		tmp.put("WSZX_GYWS", "");
    	}
    	if(StringUtils.isBlank(Wsxz_shws)){
    		tmp.put("WSXZ_SHWS", "");
    	}
    	if(StringUtils.isBlank(Sfclml)){
    		tmp.put("SFCLML", "");
    	}
    	
    	//System.out.print(tmp);
        
        FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName);
        
       // if(FreemarkerHelper.createWord(tmp, filePath, templateName, filePath, fileName)){
        	//Word2PDF.toPdf(filePath+File.separator+fileName);
        	String pathName=filePath+fileName;
            Word2PDF.toPdf(pathName);
       // }
            String path=filePath+fileName+".xml.pdf";
            return path;
    }
    public String queryCondition(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywPssgtscsqVO vo=new BuSpywPssgtscsqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywPssgtscsqDao.queryCondition(json);
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "排水施工图审查申请—数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "排水施工图审查申请—数据查询失败", user, "", "");
 			SystemException.handleMessageException("排水施工图审查申请—数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
  
    }

    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywPssgtscsqVO vo=new BuSpywPssgtscsqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywPssgtscsqDao.insert(json);
 			LogManager.writeUserLog(vo.getPssgtscsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "排水施工图审查申请—数据新增成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getPssgtscsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "排水施工图审查申请—数据新增失败", user, "", "");
 			SystemException.handleMessageException("排水施工图审查申请—数据新增失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
       

    }

    public String update(String json) throws Exception {

        
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywPssgtscsqVO vo=new BuSpywPssgtscsqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywPssgtscsqDao.update(json);
 			LogManager.writeUserLog(vo.getPssgtscsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "排水施工图审查申请—数据更改成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getPssgtscsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "排水施工图审查申请—数据更改失败", user, "", "");
 			SystemException.handleMessageException("排水施工图审查申请—数据更改失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

    }

    public String delete(String json) throws Exception {


    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywPssgtscsqVO vo=new BuSpywPssgtscsqVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getPssgtscsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "排水施工图审查申请—数据删除成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getPssgtscsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "排水施工图审查申请—数据删除失败", user, "", "");
 			SystemException.handleMessageException("排水施工图审查申请—数据删除失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;


	}

    
    /**
     * 材料核发调用获取MAP数据
     * */
	public String ywlzclhf(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();
//        obj.YWLZ_UID = data.YWLZ_UID;
//		obj.YWCL_UID = data.YWCL_UID;
//		obj.PIJIAN_CODE = codeid;
//		obj.PIJIAN_NAME = nameid;
//		obj.LINGJIAN_REN =ljr;
//		obj.LINGJIAN_PHONE=ljrdh;
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            String YWLZ_UID=(String)object.get("YWLZ_UID");
            
            
            //通过业务流转UID查询企业填报的数据，map类型
            String id= buSpywPssgtscsqDao.getIdByYwlzuid(YWLZ_UID);
            BuSpywPssgtscsqVO mapFtl=new BuSpywPssgtscsqVO();
            if(StringUtil.isNotBlankStr(id)){
            	 mapFtl =this.findById(id);      	
            }
    	 	
    	 	
    	 	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
        	String fileName_pre = Constants.getString("template_word_js_15", "无锡新区排水施工图审查申请表");
        	String templateName=fileName_pre;
        	String fileName="无锡新区排水施工图审查申请表";//+tmp.getPsfayssq_uid();
        	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
        	
        	//复选框的判断
        	String Wszx_gyws=mapFtl.getWszx_gyws();
        	String Wsxz_shws=mapFtl.getWsxz_shws();
        	String Sfclml=mapFtl.getSfclml();
        	if(StringUtils.isBlank(Wszx_gyws)){
        		mapFtl.put("WSZX_GYWS", "");
        	}
        	if(StringUtils.isBlank(Wsxz_shws)){
        		mapFtl.put("WSXZ_SHWS", "");
        	}
        	if(StringUtils.isBlank(Sfclml)){
        		mapFtl.put("SFCLML", "");
        	}
            
            
            
        	//组装业务流转核发数据
        	Map mapVo = new HashMap();
        	mapVo.put("YWLZ_UID", YWLZ_UID);
        	mapVo.put("YWCL_UID",object.get("YWCL_UID") );
        	mapVo.put("PIJIAN_CODE",object.get("PIJIAN_CODE") );
        	mapVo.put("PIJIAN_NAME",object.get("PIJIAN_NAME") );
        	mapVo.put("LINGJIAN_PHONE", object.get("LINGJIAN_PHONE"));
        	mapVo.put("LINGJIAN_REN", object.get("LINGJIAN_REN"));
        	mapVo.put("CLK_UID", object.getString("CLK_UID"));
        	mapVo.put("FZ_DATE", object.getString("FZ_DATE"));
        	mapVo.put("YXQ_DATE", object.getString("YXQ_DATE"));
        	
        	if(mapFtl!=null){
        		buSpLzhfService.saveBuSpLzhfVO(mapFtl, mapVo);
        	}
            
            resultVO = vo.getRowJson();    
        } catch (DaoException e) {
            logger.error("审批业务材料核发{}", e);
            SystemException.handleMessageException("审批业务材料核发调用失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

}