/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgsx.service.BuSpywJsgcaqjcsqService.java
 * 创建日期： 2015-04-03 下午 03:13:08
 * 功能：    接口：sg_《建设工程安全监督申报表》
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-03 下午 03:13:08  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgsx.service.impl;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgcaqjcsqVO;
import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.business.sgsx.dao.BuSpywJsgcaqjcsqDao;
import com.ccthanking.business.sgsx.service.BuSpywJsgcaqjcsqService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywJsgcaqjcsqService.java </p>
 * <p> 功能：sg_《建设工程安全监督申报表》 </p>
 *
 * <p><a href="BuSpywJsgcaqjcsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-03
 * 
 */

@Service
public class BuSpywJsgcaqjcsqServiceImpl extends Base1ServiceImpl<BuSpywJsgcaqjcsqVO, String> implements BuSpywJsgcaqjcsqService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcaqjcsqServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_JSGCAQJCSQ;
    
    private BuSpywJsgcaqjcsqDao buSpywJsgcaqjcsqDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJsgcaqjcsqDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("sg_《建设工程安全监督申报表》{}", e.getMessage());
			SystemException.handleMessageException("sg_《建设工程安全监督申报表》查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
public String queryByGcId(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJsgcaqjcsqDao.queryByGcId(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("sg_《建设工程安全监督申报表》{}", e.getMessage());
			SystemException.handleMessageException("sg_《建设工程安全监督申报表》查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // toword
    public String toword(HttpServletResponse response,String id,String ywlzuid) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			BuSpywJsgcaqjcsqVO  vo = this.findById(id);
	            
			if(vo.getTfkwgc_y().equals("1")){
				vo.put("TFKWGC_Y_SV", "有");
			}else if(vo.getTfkwgc_y().equals("0")){
				vo.put("TFKWGC_Y_SV", "无");
			}
			
			if(vo.getZhichenggc_y().equals("1")){
				vo.put("ZHICHENGGC_Y_SV", "有");
			}else if(vo.getZhichenggc_y().equals("0")){
				vo.put("ZHICHENGGC_Y_SV", "无");
			}
			
			if(vo.getGlgj_y().equals("1")){
				vo.put("GLGJ_Y_SV", "有");
			}else if(vo.getGlgj_y().equals("0")){
				vo.put("GLGJ_Y_SV", "无");
			}
			
			if(vo.getDljsjgc_y().equals("1")){
				vo.put("DLJSJGC_Y_SV", "有");
			}else if(vo.getDljsjgc_y().equals("0")){
				vo.put("DLJSJGC_Y_SV", "无");
			}
			if(vo.getZzycpt_y().equals("1")){
				vo.put("ZZYCPT_Y_SV", "有");
			}else if(vo.getZzycpt_y().equals("0")){
				vo.put("ZZYCPT_Y_SV", "无");
			}
			if(vo.getJsjgc_y().equals("1")){
				vo.put("JSJGC_Y_SV", "有");
			}else if(vo.getJsjgc_y().equals("0")){
				vo.put("JSJGC_Y_SV", "无");
			}
			if(vo.getCcgc_y().equals("1")){
				vo.put("CCGC_Y_SV", "有");
			}else if(vo.getCcgc_y().equals("0")){
				vo.put("CCGC_Y_SV", "无");
			}
			if(vo.getGjwccgc_y().equals("1")){
				vo.put("GJWCCGC_Y_SV", "有");
			}else if(vo.getGjwccgc_y().equals("0")){
				vo.put("GJWCCGC_Y_SV", "无");
			}
			if(vo.getYxccgc_y().equals("1")){
				vo.put("YXCCGC_Y_SV", "有");
			}else if(vo.getYxccgc_y().equals("0")){
				vo.put("YXCCGC_Y_SV", "无");
			}
			if(vo.getBfjzccgc_y().equals("1")){
				vo.put("BFJZCCGC_Y_SV", "有");
			}else if(vo.getBfjzccgc_y().equals("0")){
				vo.put("BFJZCCGC_Y_SV", "无");
			}
			if(vo.getBpccgc_y().equals("1")){
				vo.put("BPCCGC_Y_SV", "有");
			}else if(vo.getBpccgc_y().equals("0")){
				vo.put("BPCCGC_Y_SV", "无");
			}
			
			domresult = buSpywJsgcaqjcsqDao.queryDtgc(ywlzuid, null, null);
			
			JSONObject  jasonObject = JSONObject.fromObject(domresult);
			JSONObject  jasonObject2= (JSONObject) jasonObject.get("response");
			JSONArray array= jasonObject2.getJSONArray("data");
			List<ProjectsUnitsVO> list = (List)JSONArray.toCollection(array,ProjectsUnitsVO.class);  
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("XH",i+1);
			}
			vo.put("DT_LIST", list);
			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="建设工程安全监督申报表";
			String workName="建设工程安全监督申报表"+vo.getJsgcaqjcsq_uid()+".doc";
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
			
		    	
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
		

    }
    
    public String queryDtgc(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJsgcaqjcsqDao.queryDtgc(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("sg_《建设工程安全监督申报表》{}", e.getMessage());
			SystemException.handleMessageException("sg_《建设工程安全监督申报表》查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywJsgcaqjcsqVO vo = new BuSpywJsgcaqjcsqVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setJsgcaqjcsq_uid(DBUtil.getSequenceValue("JSGCAQJCSQ_UID"));
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            vo.setCreated_date(new Date());
        
            // 插入
			buSpywJsgcaqjcsqDao.save(vo);
            resultVO = vo.getRowJson();

         
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("sg_《建设工程安全监督申报表》{}", e.getMessage());
           SystemException.handleMessageException("sg_《建设工程安全监督申报表》新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywJsgcaqjcsqVO vo = new BuSpywJsgcaqjcsqVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
            // 修改
            buSpywJsgcaqjcsqDao.update(vo);
            resultVO = vo.getRowJson();

      
        } catch (DaoException e) {
            logger.error("sg_《建设工程安全监督申报表》{}", e.getMessage());
          SystemException.handleMessageException("sg_《建设工程安全监督申报表》修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywJsgcaqjcsqVO vo = new BuSpywJsgcaqjcsqVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			buSpywJsgcaqjcsqDao.delete(BuSpywJsgcaqjcsqVO.class, vo.getJsgcaqjcsq_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("sg_《建设工程安全监督申报表》{}", e.getMessage());
             SystemException.handleMessageException("sg_《建设工程安全监督申报表》删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywJsgcaqjcsqDaoImpl")
	public void setBuSpywJsgcaqjcsqDao(BuSpywJsgcaqjcsqDao buSpywJsgcaqjcsqDao) {
		this.buSpywJsgcaqjcsqDao = buSpywJsgcaqjcsqDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJsgcaqjcsqDao);
	}
    
}
