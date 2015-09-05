/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCspsxkzsqService.java
 * 创建日期： 2014-06-11 下午 02:27:33
 * 功能：    接口：排水许可证申请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-11 下午 02:27:33  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywCspsxkzsqDao;
import com.ccthanking.business.sp.service.BuSpywCspsxkzsqService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywCspsxkzsqVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywCspsxkzsqService.java </p>
 * <p> 功能：排水许可证申请 </p>
 *
 * <p><a href="BuSpywCspsxkzsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */

@Service
public class BuSpywCspsxkzsqServiceImpl extends Base1ServiceImpl<BuSpywCspsxkzsqVO, String> implements BuSpywCspsxkzsqService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCspsxkzsqServiceImpl.class);
	 @Autowired
	 private BuSpLzhfService buSpLzhfService;
	// 业务类型
    private String ywlx = YwlxManager.Bu_Spyw_Cspsxkzsq;
    
    private BuSpywCspsxkzsqDao buSpywCspsxkzsqDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywCspsxkzsqDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, 
                     user.getName() + "查询<排水许可证申请>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("排水许可证申请{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "排水许可证申请查询失败", user, "", "");
			SystemException.handleMessageException("排水许可证申请查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
 // toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
        try {

			BuSpywCspsxkzsqVO  vo = this.findById(id);


			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_js_17","");
			String workName=Constants.getString("template_word_js_17","")+""+vo.getCspsxkzsq_uid()+"";
			
			//复选框的判断
	    	String Wszx_gyws=vo.getSfclml();
	    	if(org.apache.commons.lang3.StringUtils.isBlank(Wszx_gyws)){
	    		vo.put("SFCLML", "");
	    	}
			
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
		

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywCspsxkzsqVO vo = new BuSpywCspsxkzsqVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setCspsxkzsq_uid(DBUtil.getSequenceValue("CSPSXKZSQ_UID"));
            
            

            vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
            
            vo.setEvent_uid(new RandomGUID().toString());

            // 插入
			buSpywCspsxkzsqDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "排水许可证申请新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("排水许可证申请{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE,  user.getName() + "排水许可证申请新增失败", user, "", "");
            SystemException.handleMessageException("排水许可证申请新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywCspsxkzsqVO vo = new BuSpywCspsxkzsqVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	vo.setEvent_uid(new RandomGUID().toString());

          	vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
          	
            // 修改
            buSpywCspsxkzsqDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "排水许可证申请修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("排水许可证申请{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE,  user.getName() + "排水许可证申请修改失败", user, "", "");
            SystemException.handleMessageException("排水许可证申请修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywCspsxkzsqVO vo = new BuSpywCspsxkzsqVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			vo.setEvent_uid(new RandomGUID().toString());

			//删除   根据据主键
			buSpywCspsxkzsqDao.delete(BuSpywCspsxkzsqVO.class, vo.getCspsxkzsq_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,  user.getName() + "排水许可证申请删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("排水许可证申请{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx ,Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "排水许可证申请删除失败", user, "", "");
            SystemException.handleMessageException("排水许可证申请删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

    
    // toword
    public boolean toword(String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			BuSpywCspsxkzsqVO vo = this.findById(id);

			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_js_17","");
			String workName="十七_"+Constants.getString("template_word_js_17","")+"_"+vo.getCspsxkzsq_uid()+".doc";
			

			//复选框的判断
	    	String Wszx_gyws=vo.getSfclml();
	    	if(org.apache.commons.lang.StringUtils.isBlank(Wszx_gyws)){
	    		vo.put("SFCLML", "");
	    	}
			
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"\\" +workName;

			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			
        }catch (DaoException e) {
        	return false;
        } finally {
        	
        }
        return true;
    }
    
	@Autowired
	@Qualifier("buSpywCspsxkzsqDaoImpl")
	public void setBuSpywCspsxkzsqDao(BuSpywCspsxkzsqDao buSpywCspsxkzsqDao) {
		this.buSpywCspsxkzsqDao = buSpywCspsxkzsqDao;
		super.setBsBaseDaoTJdbc( (BsBaseDaoTJdbc) buSpywCspsxkzsqDao);
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
            String id= buSpywCspsxkzsqDao.getIdByYwlzuid(YWLZ_UID);
            BuSpywCspsxkzsqVO mapFtl=new BuSpywCspsxkzsqVO();
            if(StringUtil.isNotBlankStr(id)){
            	 mapFtl =this.findById(id);      
            }
    	 	
//
//        	//复选框的判断
//        	String Wszx_gyws=mapFtl.getWszx_gyws();
//        	String Wsxz_shws=mapFtl.getWsxz_shws();
//        	String Sfclml=mapFtl.getSfclml();
//        	if(StringUtils.isBlank(Wszx_gyws)){
//        		mapFtl.put("WSZX_GYWS", "");
//        	}
//        	if(StringUtils.isBlank(Wsxz_shws)){
//        		mapFtl.put("WSXZ_SHWS", "");
//        	}
//        	if(StringUtils.isBlank(Sfclml)){
//        		mapFtl.put("SFCLML", "");
//        	}
//            
            
            
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
