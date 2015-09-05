/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzService.java
 * 创建日期： 2014-06-19 下午 04:51:04
 * 功能：    接口：审批业务流转实例
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:51:04  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.ywlz.service.impl;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.export.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJzgcqyrqjdxgczsbVO;
import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.business.ywlz.dao.BuSpYwlzDao;
import com.ccthanking.business.ywlz.service.BuSpYwlzService;
import com.ccthanking.business.ywlz.vo.BuSpYwlzVO;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.ibm.icu.text.DecimalFormat;



/**
 * <p> BuSpYwlzService.java </p>
 * <p> 功能：审批业务流转实例 </p>
 *
 * <p><a href="BuSpYwlzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

@Service
public class BuSpYwlzServiceImpl extends Base1ServiceImpl<BuSpYwlzVO, String> implements BuSpYwlzService {
	private static Logger logger = LoggerFactory.getLogger(BuSpYwlzServiceImpl.class);
   
    private BuSpYwlzDao buSpYwlzDao;
	@Autowired
	@Qualifier("buSpYwlzDaoImpl")
	public void setBuSpYwlzDao(BuSpYwlzDao buSpYwlzDao) {
		this.buSpYwlzDao = buSpYwlzDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)buSpYwlzDao);
		
	}
    
    // @Override
    public String queryCondition(String json,String spyw_uid_res) throws Exception {  
//    	User user = ActionContext.getCurrentUserInThread();   
        String domresult = "";
        try {
        	Map maptemp=new HashMap();
        	maptemp.put("spyw_uid_res", spyw_uid_res);
        	domresult = buSpYwlzDao.queryCondition(json, null, maptemp);
           
        }catch (DaoException e) {
        	logger.error("审批业务流转实例查询{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转实例查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryClType(String json) throws Exception {  
//    	User user = ActionContext.getCurrentUserInThread();   
        String domresult = "";
        try {
        	domresult = buSpYwlzDao.queryClType(json, null, null);
           
        }catch (DaoException e) {
        	logger.error("审批业务流转实例查询{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转实例查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryByProjectsid(String json) throws Exception {  
//    	User user = ActionContext.getCurrentUserInThread();   
        String domresult = "";
        try {
        
        	domresult = buSpYwlzDao.queryByProjectsid(json);
           
        }catch (DaoException e) {
        	logger.error("审批业务流转实例查询{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转实例查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
//		User user = ActionContext.getCurrentUserInThread();	
        String resultVO = null;
        BuSpYwlzVO vo = new BuSpYwlzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));            
            // 插入
			buSpYwlzDao.save(vo);
            resultVO = vo.getRowJson();         
        } catch (DaoException e) {
            logger.error("审批业务流转实例新增{}", e.getMessage());
             SystemException.handleMessageException("审批业务流转实例新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {
//        User user = ActionContext.getCurrentUserInThread();     
        String resultVO = null;
        BuSpYwlzVO vo = new BuSpYwlzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
           // 修改
            buSpYwlzDao.update(vo);
            resultVO = vo.getRowJson();
       
        } catch (DaoException e) {
            logger.error("审批业务流转实例修改{}", e.getMessage());
            SystemException.handleMessageException("审批业务流转实例修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {
//		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		BuSpYwlzVO vo = new BuSpYwlzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			vo.setValueFromJson(jsonObj);
			//删除   根据据主键
			buSpYwlzDao.delete(BuSpYwlzVO.class, vo.getYwlz_uid());

			resultVo = vo.getRowJson();
		} catch (DaoException e) {
            logger.error("审批业务流转实例删除{}", e.getMessage());
            SystemException.handleMessageException("审批业务流转实例删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	public BuSpYwlzVO updateVo(BuSpYwlzVO vo) throws Exception {
//		    User user = ActionContext.getCurrentUserInThread();     
		    BuSpYwlzVO ywlzvo = null;

	        try {

	           // 修改
	            buSpYwlzDao.update(vo);
	            ywlzvo=this.findById(vo.getYwlz_uid());
	        } catch (DaoException e) {
	            logger.error("审批业务流转实例修改{}", e.getMessage());
	            SystemException.handleMessageException("审批业务流转实例修改失败,请联系相关人员处理");
	        } finally {
	        }
	        return ywlzvo;
	}

	public String queryYwcl(Map map) throws Exception {
		if (Constants.YWLZ_DO_INSERT.equals((String)map.get("optype"))) {
			return this.buSpYwlzDao.queryYwcl(map);
		}else if(Constants.YWLZ_DO_EDIT.equals((String)map.get("optype"))){
			return this.buSpYwlzDao.queryYwclFromLz(map);
		}
		return null;
	}

	
	
	/**
	 * 查询一个业务流转下的待审、通过、退回总数据
	 * */
	public String getSpCount(String spywuid) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList= buSpYwlzDao.getSpCount(spywuid);
			if(tempList!=null&&tempList.size()>0){				
					Map temMap=(Map)tempList.get(0);		
					obj.put("DSZ", temMap.get("DSZ"));
					obj.put("YTG", temMap.get("YTG"));
					obj.put("YTH", temMap.get("YTH"));
	
			}
			
		} catch (DaoException e) {
            logger.error("获取审批业务流转统计中待审、通过、退回数据{}", e.getMessage());
            SystemException.handleMessageException("获取审批业务流转统计中待审、通过、退回数据失败,请联系相关人员处理");
		} finally {
		}
		
		return obj.toString();
	}

	
	/**
	 * 审核完成之后，检验是否审核通过，并判断该实例流转是否存在核发材料。
	 * */
	public String sfysp(String ywlzuid) throws Exception {	
		JSONObject obj = new JSONObject();
		try {
		/**	
			BuSpYwlzVO vo=this.findById(ywlzuid);
			if(vo!=null){
				obj.put("STATUS", vo.getStatus());
				obj.put("YWLZ_UID", vo.getYwlz_uid());
				obj.put("SPYW_UID", vo.getSpyw_uid());
				obj.put("PROJECTS_UID", vo.getProjects_uid());
			}
		**/	
			List<?> reslutList=buSpYwlzDao.ywlzList(ywlzuid);
			if(reslutList!=null&&reslutList.size()>0){
				Map temMap=(Map)reslutList.get(0);
				obj.put("STATUS", temMap.get("STATUS"));
				obj.put("YWLZ_UID", temMap.get("YWLZ_UID"));
				obj.put("SPYW_UID", temMap.get("SPYW_UID"));
				obj.put("PROJECTS_UID", temMap.get("PROJECTS_UID"));
				obj.put("HFCLCOUNT", temMap.get("HFCLCOUNT"));
			}
			
			
		} catch (DaoException e) {
            logger.error("根据ywlzuid查询审批业务流转统计{}", e.getMessage());
            SystemException.handleMessageException("根据ywlzuid查询审批业务流转统计失败,请联系相关人员处理");
		} finally {
		}
		return obj.toString();
	}

	public String getAllDsCount() throws Exception {
		JSONArray list=new JSONArray();		
		try {
			List<?> tempList= buSpYwlzDao.getAllDsCount();
			
			if(tempList!=null&&tempList.size()>0){		
				for(int i=0;i<tempList.size();i++){
					JSONObject obj = new JSONObject();
					Map temMap=(Map)tempList.get(i);		
					obj.put("SPYW_UID", temMap.get("SPYW_UID"));
					obj.put("COUNTDS", temMap.get("COUNTDS"));
					list.add(obj);
				}
			}
		} catch (DaoException e) {
            logger.error("获取所有待审的审批业务流转统计数据{}", e.getMessage());
            SystemException.handleMessageException("获取所有待审的审批业务流转统计数据失败,请联系相关人员处理");
		} finally {
		}
		
		return list.toString();
	}
	
	public String downloadGc(HttpServletResponse response,String id,String bh) throws Exception {
	    
    	User user = ActionContext.getCurrentUserInThread();
    	
        String domresult = "";
        try {
        	Connection conn = DBUtil.getConnection();
        	domresult=buSpYwlzDao.queryGcPrint(id, null, null);
        	JSONObject  jasonObject = JSONObject.fromObject(domresult);
			JSONObject  jasonObject2= (JSONObject) jasonObject.get("response");
			JSONArray array= jasonObject2.getJSONArray("data");
			List<Map> list = (List)JSONArray.toCollection(array,Map.class);  
			Map map=list.get(0);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String date=format.format(new Date());
			map.put("SJ", date);
			
			String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
			map.put("BH", "XA"+yearLast+"-"+bh);
			
			String sql="select distinct o.org_name,o.phone,aj.* from PROJECTS_AJZJ aj " +
					"left join projects_gongcheng gc on gc.projects_uid=aj.projects_uid " +
					"left join ORGANIZE o on aj.users_uid=o.user_uid " +
					"where gc.gongcheng_uid="+id+"  order by  ZHUJIAN_Y desc ";
			List<Map<String, String>> rylist =DBUtil.queryReturnList(conn, sql);
			map.put("RLLIST", rylist);
			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="建设工程安监通知书";
			String workName="建设工程安监通知书"+id+".doc";
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
		    
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			domresult= filename+".xml.pdf";
        }catch (DaoException e) {	
        	logger.error("sg_建筑工程企业入区接单项工程注申表PDF{}", e.getMessage());
			SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表PDF失败,请联系相关人员处理");
        }
		return domresult;

    }

   public String downloadJz(HttpServletResponse response,String id) throws Exception {
	    
    	User user = ActionContext.getCurrentUserInThread();
    	
        String domresult = "";
        try {
        	Connection conn = DBUtil.getConnection();
        	domresult=buSpYwlzDao.queryJzPrint(id, null, null);
        	JSONObject  jasonObject = JSONObject.fromObject(domresult);
			JSONObject  jasonObject2= (JSONObject) jasonObject.get("response");
			JSONArray array= jasonObject2.getJSONArray("data");
			List<Map> list = (List)JSONArray.toCollection(array,Map.class);  
			Map map=list.get(0);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String date=format.format(new Date());
			map.put("SJ", date);
			
			String yearLast = new SimpleDateFormat("yyyy",Locale.CHINESE).format(Calendar.getInstance().getTime());
			map.put("NF", yearLast);
			
			String bhsql="select count(*) from bu_sp_ywlz where spyw_uid=114 " +
					"and act_end_date between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
					"and to_date((select Extract(year from sysdate)||'-12-31 23:59:59'from dual),'yyyy-mm-dd hh24:mi:ss')";
			String[][] res=DBUtil.query(bhsql);
			
			int bh=Integer.parseInt(res[0][0])+1;
			String pattern="000";
			DecimalFormat df = new DecimalFormat(pattern);
			map.put("BH", df.format(bh));
			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="建筑注册登记表";
			String workName="建筑注册登记表"+id+".doc";
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
		    
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			domresult= filename+".xml.pdf";
        }catch (DaoException e) {	
        	logger.error("sg_建筑工程企业入区接单项工程注申表PDF{}", e.getMessage());
			SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表PDF失败,请联系相关人员处理");
        }
		return domresult;

    }

   public String downloadSz(HttpServletResponse response,String id) throws Exception {
	    
   	User user = ActionContext.getCurrentUserInThread();
   	
       String domresult = "";
       try {
       	Connection conn = DBUtil.getConnection();
       	domresult=buSpYwlzDao.querySzPrint(id, null, null);
       	JSONObject  jasonObject = JSONObject.fromObject(domresult);
			JSONObject  jasonObject2= (JSONObject) jasonObject.get("response");
			JSONArray array= jasonObject2.getJSONArray("data");
			List<Map> list = (List)JSONArray.toCollection(array,Map.class);  
			Map map=list.get(0);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String date=format.format(new Date());
			map.put("SJ", date);
			
			String yearLast = new SimpleDateFormat("yyyy",Locale.CHINESE).format(Calendar.getInstance().getTime());
			map.put("NF", yearLast);
			
			String bhsql="select count(*) from bu_sp_ywlz where spyw_uid=115 " +
					"and act_end_date between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
					"and to_date((select Extract(year from sysdate)||'-12-31 23:59:59'from dual),'yyyy-mm-dd hh24:mi:ss')";
			String[][] res=DBUtil.query(bhsql);
			
			int bh=Integer.parseInt(res[0][0])+1;
			String pattern="000";
			DecimalFormat df = new DecimalFormat(pattern);
			map.put("BH", df.format(bh));
			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="市政注册表";
			String workName="市政注册表"+id+".doc";
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
		    
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			domresult= filename+".xml.pdf";
       }catch (DaoException e) {	
       	logger.error("sg_建筑工程企业入区接单项工程注申表PDF{}", e.getMessage());
			SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表PDF失败,请联系相关人员处理");
       }
		return domresult;

   }



}
