package com.ccthanking.business.xzcf.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.jl.vo.EnterpriseZenZizhiVO;
import com.ccthanking.business.xzcf.vo.BiluVO;
import com.ccthanking.business.xzcf.vo.BiluWendaVO;
import com.ccthanking.business.xzcf.vo.XzcfVO;
import com.ccthanking.business.xzcf.dao.XZCFDao;
import com.ccthanking.business.xzcf.service.XZCFService;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.Pub;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.ibm.icu.text.SimpleDateFormat;


@Service
public class XZCFServiceImpl extends Base1ServiceImpl<XzcfVO,String> implements XZCFService {

	private static Logger logger = LoggerFactory.getLogger(XZCFServiceImpl.class);

	private XZCFDao xzcfDao;
	
	
	//注入dao
	@Autowired
	@Qualifier("XZCFDaoImpl")
	public void setXZCFDao(XZCFDao xzcfDao){
		this.xzcfDao = xzcfDao;
	}
	
	public String queryCondition(String gcuid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {
			domresult = xzcfDao.queryCondition(gcuid);
        }catch (DaoException e) {
        	logger.error("{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;
	}

	public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();		
        String resultVO = null;
        Connection conn = DBUtil.getConnection();
        XzcfVO vo =new XzcfVO();
        try {
        	 conn.setAutoCommit(false);
        	 JSONArray list = vo.doInitJson(json);
        	 JSONObject jsonObj = (JSONObject) list.get(0);
             String dxid = (String)jsonObj.get("DX_UID");
             String gcuid = (String)jsonObj.get("GONGCHENG_UID");
             String response = xzcfDao.queryDXTYPE(gcuid, dxid);
             JSONObject ob = JSONObject.fromObject(response);
             String  DX_NAME = ((JSONObject) ob.getJSONObject("response").getJSONArray("data").get(0)).get("COMPANY_NAME").toString();
           
             vo.setValueFromJson(jsonObj);
             if((String)jsonObj.get("DX_NAME_SV") == null && (String)jsonObj.get("DX_NAME_SV")==""){
            	 vo.setDx_name(DX_NAME);
             }
             vo.setDx_name(DX_NAME);
             //设置调查对象的名称
             vo.setBl_dcdx(DX_NAME);
             vo.setCreated_by(user.getUserSN());
             vo.setCreated_date(new Date());
             System.out.println(user.getUsername());
             BaseDAO.insert(conn, vo);
             insertBiluWenda(conn, jsonObj, vo);
            
             resultVO = vo.getRowJson();
             conn.commit();
			
        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
            SystemException.handleMessageException("信息新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;
	}
	
	//@SuppressWarnings("unused")
	private void insertBiluWenda(Connection conn,  JSONObject obj, XzcfVO vo) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
	     String resultVO = null;
	     BiluWendaVO biluVO = null;
	     JSONArray BiLuArray = null;
	     JSONArray BiluContent = null;
	     try{
	    	      BiLuArray = obj.getJSONArray("XUHAO");//BILU_WENDA_UID
	    	      BiluContent = obj.getJSONArray("CONTENT");//BILU_WENDA_UID
	        }catch(JSONException e){
	        	System.out.println("没有添加笔录问答!");
	        }
	      if(BiLuArray!=null && BiluContent !=null){
	        	for (int i = 2; i <  BiLuArray.size(); i++){
					if(StringUtils.isBlank((String) BiluContent.get(i))){
						continue;
					}
					if(StringUtils.isBlank((String) BiLuArray.get(i))){
						continue;
					}
					 biluVO = new BiluWendaVO();
				
		         /*   biluVO.setJl_company_uid(vo.getJl_company_uid());
		            biluVO.setZizhi_uid(obj.getJSONArray("ZENG_ZIZHI_UID").getString(i));
		            biluVO.setZizhi_code(obj.getJSONArray("ZIZHI_CODE").getString(i+1));
		            biluVO.setZizhi_dengji(obj.getJSONArray("ZIZHI_DENGJI").getString(i+1));
		            biluVO.setValid_date(Pub.toDate("yyyy-MM-dd",obj.getJSONArray("ZXZHYXQ_DATE").getString(i+1)));*/
		            System.out.println(vo.getXzcf_uid());
		            biluVO.setXzcf_uid(vo.getXzcf_uid());
			    	biluVO.setQ_a(obj.getJSONArray("Q_A").getString(i));
			    	System.out.println(obj.getJSONArray("Q_A").getString(i));
			        biluVO.setXuhao(obj.getJSONArray("XUHAO").getString(i));
			    	System.out.println(obj.getJSONArray("XUHAO").getString(i));
			    	biluVO.setContent(obj.getJSONArray("CONTENT").getString(i));
		            System.out.println(obj.getJSONArray("CONTENT").getString(i));
		            BaseDAO.insert(conn, biluVO);
		            	
		        
				}
	        }
	}


	public String update(String msg, String xzcfuid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();		
        String resultVO = null;
        Connection conn = DBUtil.getConnection();
        XzcfVO vo =new XzcfVO();
        try {      
        	 conn.setAutoCommit(false);
        	 JSONArray list = vo.doInitJson(msg);
        	 JSONObject obj = (JSONObject) list.get(0);
        	 vo.setValueFromJson(obj);
             vo.setXzcf_uid(xzcfuid);
             vo.setUpdated_by(user.getUserSN());
             vo.setUpdated_date(new Date());
             BaseDAO.update(conn, vo);
            
            
             updateBiluWenda(conn,obj,vo);
           
             resultVO = vo.getRowJson();
             conn.commit();

        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
            SystemException.handleMessageException("信息新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;
	}

	private void updateBiluWenda(Connection conn,JSONObject obj, XzcfVO vo) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
	     BiluWendaVO biluVO = null;
	     JSONArray BiLuArray = null;
	     JSONArray BiluContent = null;
	     boolean flag = false;
	     try{
    	      BiLuArray = obj.getJSONArray("XUHAO");//BILU_WENDA_UID
    	      BiluContent = obj.getJSONArray("CONTENT");//BILU_WENDA_UID
	    	    	
	    	   /*   for (int i = 0; i < BiluContent.size(); i++) {
	    	    	  BiLuArray.getString(i);
				}
				*/
				if( BiluContent != null ||BiluContent != null ){
					 flag = xzcfDao.delete(conn,vo.getXzcf_uid());
					}
	        }catch(JSONException e){
	        	System.out.println("没有可更新的笔录问答!");
	        }
	     if(flag){
	        for (int i = 0; i <  BiLuArray.size(); i++){
				if(StringUtils.isNotBlank((String) BiluContent.getString(i)) && StringUtils.isNotBlank((String) BiLuArray.getString(i)) ){
						  biluVO = new BiluWendaVO();
				            biluVO.setXzcf_uid(vo.getXzcf_uid());
					    	biluVO.setQ_a(obj.getJSONArray("Q_A").getString(i));
					        biluVO.setXuhao(obj.getJSONArray("XUHAO").getString(i));
					    	biluVO.setContent(obj.getJSONArray("CONTENT").getString(i));
				            BaseDAO.insert(conn, biluVO);
					}
					
				}
	    	  }
	}
	
	
	
	
	
	
	

	
	public String queryDXTYPE(String gcuid, String jsuid) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {
			domresult = xzcfDao.queryDXTYPE(gcuid,jsuid);
        }catch (DaoException e) {
        	logger.error("{行政处罚类型查询}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;
	}
	
	public String queryReportxx() throws Exception {
        User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {
			domresult = xzcfDao.queryReportxx();
        }catch (DaoException e) {
        	logger.error("{行政处罚调查人员查询}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;
	}
	
	

	//打印
	public String toword(HttpServletResponse response, String xzcfuid){

		Map<String, Object> map =  null;
		
		try {
			List<?> list = this.xzcfDao.findHeaderPrint(xzcfuid);
			if(list.size()!=0){
			//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map = (Map<String, Object>) list.get(0);
			}
		
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");//转前 ftl路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName="行政处罚相关－立案登记+笔录表+调查报告";//行政处罚相关－立案登记+笔录表+调查报告
			String workName="行政处罚相关－立案登记+笔录表+调查报告"+xzcfuid;// System.nanoTime()
			System.out.println("转换前路径:=="+filePath+"转换后的pdf路径:=="+pdfPath+"模板名:=="+ftlName+"打印区分:=="+workName);
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+""+workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";//filename+".xml.pdf"
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String queryXzcfMsg(String gcuid) {
			User user = ActionContext.getCurrentUserInThread();
		    
	        String domresult = "";
	        try {
				domresult = xzcfDao.queryXzcfMsg(gcuid);
	        }catch (DaoException e) {
	        	logger.error("{}", e.getMessage());
				SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
	        } finally {
	        	
	        }
	        return domresult;
		}

	public String queryXzcfXX(String xzcfuid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {
			domresult = xzcfDao.queryXzcfXX(xzcfuid);
        }catch (DaoException e) {
        	logger.error("{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;
	}
 //询问笔录新增
	public String insertXwbl(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();		
        String resultVO = null;
        Connection conn = DBUtil.getConnection();
        BiluVO vo = new BiluVO();
        try {
        	 conn.setAutoCommit(false);
        	 JSONArray list = vo.doInitJson(json);
        	 JSONObject jsonObj = (JSONObject) list.get(0);
             String gcuid = (String)jsonObj.get("GONGCHENG_UID");
             vo.setValueFromJson(jsonObj);
             vo.setGongcheng_uid(gcuid);
            // vo.setXzcf_uid(xzcf_uid);
             vo.setCreate_by(user.getUserSN());  
             vo.setCreate_date(new Date());  
             BaseDAO.insert(conn, vo);
             
             insertXwblForwenda(conn, jsonObj,vo);
            
             resultVO = vo.getRowJson();
             conn.commit();
			
        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
            SystemException.handleMessageException("信息新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;
	}
	
	private void insertXwblForwenda(Connection conn,  JSONObject obj, BiluVO vo) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
	     BiluWendaVO biluVO = null;
	     JSONArray BiLuArray = null;
	     JSONArray BiluContent = null;
	     try{
	    	      BiLuArray = obj.getJSONArray("XUHAO");//BILU_WENDA_UID
	    	      BiluContent = obj.getJSONArray("CONTENT");//BILU_WENDA_UID
	        }catch(JSONException e){
	        	System.out.println("没有添加笔录问答!");
	        }
	      if(BiLuArray!=null && BiluContent !=null){
	        	for (int i = 0; i <  BiLuArray.size(); i++){
					if(StringUtils.isNotBlank((String) BiluContent.getString(i)) && StringUtils.isNotBlank((String) BiLuArray.getString(i))){
						
						 biluVO = new BiluWendaVO();
			         /*   biluVO.setJl_company_uid(vo.getJl_company_uid());
			            biluVO.setZizhi_uid(obj.getJSONArray("ZENG_ZIZHI_UID").getString(i));
			            biluVO.setZizhi_code(obj.getJSONArray("ZIZHI_CODE").getString(i+1));
			            biluVO.setZizhi_dengji(obj.getJSONArray("ZIZHI_DENGJI").getString(i+1));
			            biluVO.setValid_date(Pub.toDate("yyyy-MM-dd",obj.getJSONArray("ZXZHYXQ_DATE").getString(i+1)));*/
			            biluVO.setBilu_uid(vo.getBilu_uid());
				    	biluVO.setQ_a(obj.getJSONArray("Q_A").getString(i));
				        biluVO.setXuhao(obj.getJSONArray("XUHAO").getString(i));
				    	biluVO.setContent(obj.getJSONArray("CONTENT").getString(i));
			            BaseDAO.insert(conn, biluVO);
					}
				
		            	
		        
				}
	        }
	}
	}
	
	

	