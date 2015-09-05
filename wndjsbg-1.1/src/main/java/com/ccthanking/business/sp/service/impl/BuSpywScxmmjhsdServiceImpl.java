/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywScxmmjhsdService.java
 * 创建日期： 2014-10-27 下午 05:33:08
 * 功能：    接口：三产项目面积核算单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-27 下午 05:33:08  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.json.types.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.project.dao.JsCompanyDao;
import com.ccthanking.business.project.service.JsCompanyService;
import com.ccthanking.business.project.service.ProjectsService;
import com.ccthanking.business.project.vo.JsCompanyVO;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.business.sp.dao.BuSpywScxmmjhsdDao;
import com.ccthanking.business.sp.service.BuSpywScxmmjhsdService;
import com.ccthanking.business.spyw.vo.BuSpywScxmmjhsdVO;
import com.ccthanking.business.spyw.vo.BuSpywZzhydjVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywScxmmjhsdService.java </p>
 * <p> 功能：三产项目面积核算单 </p>
 *
 * <p><a href="BuSpywScxmmjhsdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-27
 * 
 */

@Service
public class BuSpywScxmmjhsdServiceImpl extends Base1ServiceImpl<BuSpywScxmmjhsdVO, String> implements BuSpywScxmmjhsdService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywScxmmjhsdServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SCXMMJHSD;
    
    private BuSpywScxmmjhsdDao buSpywScxmmjhsdDao;
    
    private JsCompanyService jsCompanyService;  //企业
    
    private ProjectsService projectsService;  //项目分期
    

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywScxmmjhsdDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("三产项目面积核算单{}", e.getMessage());
            
            SystemException.handleMessageException("三产项目面积核算单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryByLzbz(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywScxmmjhsdDao.queryByLzbz(json);

            
        }catch (DaoException e) {
        	logger.error("三产项目面积核算单{}", e.getMessage());
            
            SystemException.handleMessageException("三产项目面积核算单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywScxmmjhsdVO vo = new BuSpywScxmmjhsdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//          
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//		    vo.setSjbh(eventVO.getSjbh());
            vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getAccount());
            vo.setCreated_date(new Date());
            // 插入
			buSpywScxmmjhsdDao.save(vo);
            resultVO = vo.getRowJson();

        

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("三产项目面积核算单{}", e.getMessage());
            
            SystemException.handleMessageException("三产项目面积核算单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String querytoword(HttpServletResponse response,String id,String ptfName) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();

    
        try {

        	BuSpywScxmmjhsdVO  vo = this.findById(id);

        	
        	
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_BZWJ_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
//			String ftlName=Constants.getString("template_word_js_09","");   不需要初始化    直接步骤文件模板名
//			String workName=Constants.getString("template_word_js_09","")+""+vo.getScxmmjhsd_uid()+"";
			
			String workName="";
			if(ptfName==""){
				ptfName=null;
			}
			if("null".equals(ptfName)){
				ptfName=null;
			}
			if(ptfName==null){
				List<Map<String, String>> obj=buSpywScxmmjhsdDao.queryTpfFileNameByScxmid(vo.getScxmmjhsd_uid());
		        workName=obj.get(0).get("TMPWJNAME")+vo.getScxmmjhsd_uid();
		        ptfName=obj.get(0).get("TMPWJNAME");
			}else{
			 workName=ptfName+vo.getScxmmjhsd_uid()+"";  
			}
			if(vo.getRq()==null){
				vo.setRq(new Date());
			}
			Date dd =vo.getRq();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String tbdate="";
			tbdate = sdf.format(dd);
			Map mapvo=vo;
			String date=mapvo.get("RQ")+"";
			mapvo.remove("RQ");
			mapvo.put("RQ",tbdate);
			
			
			FreemarkerHelper.createWord(vo, filePath, ptfName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"/" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			String sql_add="update bu_sp_ywlz_bzwj set alprint=1 where lzbz_wj_uid='"+vo.getLzbz_wj_uid()+"'";
            DBUtil.exec(sql_add);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
		

    }
    
    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywScxmmjhsdVO vo = new BuSpywScxmmjhsdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
              vo.setUpdate_date(new Date());
//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            // 修改
            buSpywScxmmjhsdDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("三产项目面积核算单{}", e.getMessage());
            
            SystemException.handleMessageException("三产项目面积核算单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywScxmmjhsdVO vo = new BuSpywScxmmjhsdVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			buSpywScxmmjhsdDao.delete(BuSpywScxmmjhsdVO.class, vo.getScxmmjhsd_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("三产项目面积核算单{}", e.getMessage());
            
            SystemException.handleMessageException("三产项目面积核算单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywScxmmjhsdDaoImpl")
	public void setBuSpywScxmmjhsdDao(BuSpywScxmmjhsdDao buSpywScxmmjhsdDao) {
		this.buSpywScxmmjhsdDao = buSpywScxmmjhsdDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywScxmmjhsdDao);
	}

	
  
   
}