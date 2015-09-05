/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcns2Service.java
 * 创建日期： 2014-06-16 下午 04:50:05
 * 功能：    接口：环境影响登记告知承诺附件三
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-16 下午 04:50:05  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcns2Dao;
import com.ccthanking.business.sp.service.BuSpywHjyxdjgzcns2Service;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcns2SpVO;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcns2VO;
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
 * <p> BuSpywHjyxdjgzcns2Service.java </p>
 * <p> 功能：环境影响登记告知承诺附件三 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcns2Service.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-16
 * 
 */

@Service
public class BuSpywHjyxdjgzcns2ServiceImpl extends Base1ServiceImpl<BuSpywHjyxdjgzcns2VO, String> implements BuSpywHjyxdjgzcns2Service {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcns2ServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_HJYXDJGZCNS2;
    
    private BuSpywHjyxdjgzcns2Dao buSpywHjyxdjgzcns2Dao;

	@Autowired
	@Qualifier("buSpywHjyxdjgzcns2DaoImpl")
	public void setBuSpywHjyxdjgzcns2Dao(BuSpywHjyxdjgzcns2Dao buSpywHjyxdjgzcns2Dao) {
		this.buSpywHjyxdjgzcns2Dao = buSpywHjyxdjgzcns2Dao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywHjyxdjgzcns2Dao);
	}
    
    // @Override
	 public String download(String id) throws Exception {
	        
	   
	    	BuSpywHjyxdjgzcns2VO tmp =this.findById(id);
	    	
	    	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
	    	String fileName_pre = Constants.getString("template_word_hb_01_3", "建设项目环境影响登记表告知承诺备案--区域开发及其他类");
	    	String templateName=fileName_pre;
	    	String fileName="建设项目环境影响登记表告知承诺备案--区域开发及其他类"+id;//+tmp.getPsfayssq_uid();
	    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
	    	
	    	String syt=this.queryReadFile(id);
	    	if(StringUtil.isNotBlankStr(syt)){
	    	JSONArray array = JSONArray.fromObject(syt);
	    	JSONObject jo = JSONObject.fromObject(array.get(0));
	    	String sytPath=jo.getString("FILEROOT");
	    	String sytName=jo.getString("FILENAME");
	    	
	    	//FreemarkerHelper.createWord(tmp, filePath, templateName, filePath, fileName);
	    	String encode=FreemarkerHelper.getImageStr(sytPath,sytName);
	        tmp.put("SYT", encode);
	    	}
	    	//Date now = new Date();
	    	Date createDate=tmp.getCreated_date();
			SimpleDateFormat rqgs = new SimpleDateFormat("yyyy年MM月dd日");
			String tbdate=rqgs.format(createDate);
			
			tmp.put("TBRQ_DATE", tbdate);
			
	        if(FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName)){
	        	//Word2PDF.toPdf(filePath+File.separator+fileName);
	        	String pathName=filePath+fileName;
	            Word2PDF.toPdf(pathName);
	       }
	        String path=filePath+fileName+".xml.pdf";
	        return path;
	    }
	 public String queryCondition(String json) throws Exception {
	    	
	    	User user = ActionContext.getCurrentUserInThread();
	    	BuSpywHjyxdjgzcns2VO vo=new BuSpywHjyxdjgzcns2VO();
	 		String domresult = null;
	 		try {
	         domresult = buSpywHjyxdjgzcns2Dao.queryCondition(json);
	         LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据查询成功", user, "", "");

			} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据查询失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据查询失败,请联系相关人员处理");
			} finally {
			}
	        return domresult;

	    }
	  public String queryReadFile(String id) throws Exception {
	        
	        User user = ActionContext.getCurrentUserInThread();
	        BuSpywHjyxdjgzcns2VO vo=new BuSpywHjyxdjgzcns2VO();
			String domresult = null;
			try {
				domresult=buSpywHjyxdjgzcns2Dao.queryReadFile(id);
				LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-附件信息-数据查询成功", user, "", "");

			} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-附件信息-数据查询失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--区域开发及其他类-附件信息-数据查询失败,请联系相关人员处理");
			} finally {
			}
			return domresult;

	    }
	    
	    public String insert(String json,Map files) throws Exception {

	    	User user = ActionContext.getCurrentUserInThread();
	    	BuSpywHjyxdjgzcns2VO vo=new BuSpywHjyxdjgzcns2VO();
			String resultVO = null;
			try {
	         resultVO = buSpywHjyxdjgzcns2Dao.insert(json,files);
	         LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据新增成功", user, "", "");

			} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据新增失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据新增失败,请联系相关人员处理");
			} finally {
			}
	        return resultVO;

	    }

	    public String update(String json,Map files) throws Exception {

	    	User user = ActionContext.getCurrentUserInThread();
	    	BuSpywHjyxdjgzcns2VO vo=new BuSpywHjyxdjgzcns2VO();
			String resultVO = null;
			try {
	         resultVO = buSpywHjyxdjgzcns2Dao.update(json,files);
	         LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据更改成功", user, "", "");

			} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据更改失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据更改失败,请联系相关人员处理");
			} finally {
			}
	        return resultVO;

	    }

	    public String delete(String json) throws Exception {
	    	User user = ActionContext.getCurrentUserInThread();
	    	BuSpywHjyxdjgzcns2VO vo=new BuSpywHjyxdjgzcns2VO();
			String resultVO = null;
			try {

				resultVO=null;
			    LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据删除成功", user, "", "");

			} catch (DaoException e) {
				logger.error("表单信息{}", e.getMessage());
				LogManager.writeUserLog(vo.getHjyxdjgzcns_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
						user.getName() + "建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据删除失败", user, "", "");
				SystemException.handleMessageException("建设项目环境影响登记表告知承诺备案--区域开发及其他类-数据删除失败,请联系相关人员处理");
			} finally {
			}
			return resultVO;

		}
}
