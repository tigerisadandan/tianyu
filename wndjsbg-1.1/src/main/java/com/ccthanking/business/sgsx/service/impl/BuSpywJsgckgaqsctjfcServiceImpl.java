/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgsx.service.BuSpywJsgckgaqsctjfcService.java
 * 创建日期： 2015-03-31 上午 09:51:41
 * 功能：    接口：sg_建设工程施工开工前安全条件备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-31 上午 09:51:41  曹伟杰   创建文件，实现基本功能
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
import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgckgaqsctjfcVO;
import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.business.sgsx.dao.BuSpywJsgckgaqsctjfcDao;
import com.ccthanking.business.sgsx.service.BuSpywJsgckgaqsctjfcService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywJsgckgaqsctjfcService.java </p>
 * <p> 功能：sg_建设工程施工开工前安全条件备案表 </p>
 *
 * <p><a href="BuSpywJsgckgaqsctjfcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-31
 * 
 */

@Service
public class BuSpywJsgckgaqsctjfcServiceImpl extends Base1ServiceImpl<BuSpywJsgckgaqsctjfcVO, String> implements BuSpywJsgckgaqsctjfcService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgckgaqsctjfcServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_JSGCKGAQSCTJFC;
    
    private BuSpywJsgckgaqsctjfcDao buSpywJsgckgaqsctjfcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJsgckgaqsctjfcDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("sg_建设工程施工开工前安全条件备案表{}", e.getMessage());
			SystemException.handleMessageException("sg_建设工程施工开工前安全条件备案表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
 // toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			BuSpywJsgckgaqsctjfcVO  vo = this.findById(id);
			if(vo.getXmjl_khz_y().equals("1")){
				vo.setXmjl_khz_y("有");
			}else if(vo.getXmjl_khz_y().equals("0")){
				vo.setXmjl_khz_y("无");
			}
			
			if(vo.getAqy_count_y().equals("1")){
				vo.setAqy_count_y("符合");
			}else if(vo.getAqy_count_y().equals("0")){
				vo.setAqy_count_y("不符合");
			}
			
			if(vo.getStyp_y().equals("1")){
				vo.setStyp_y("符合");
			}else if(vo.getStyp_y().equals("0")){
				vo.setStyp_y("不符合");
			}
			
			if(vo.getSgbp_y().equals("1")){
				vo.setSgbp_y("符合");
			}else if(vo.getSgbp_y().equals("0")){
				vo.setSgbp_y("不符合");
			}
			
			if(vo.getPmt_fh_y().equals("1")){
				vo.setPmt_fh_y("符合");
			}else if(vo.getPmt_fh_y().equals("0")){
				vo.setPmt_fh_y("不符合");
			}
			
			if(vo.getAqy_fh_y().equals("1")){
				vo.setAqy_fh_y("是");
			}else if(vo.getAqy_fh_y().equals("0")){
				vo.setAqy_fh_y("否");
			}
			
			if(vo.getAqglzd_y().equals("1")){
				vo.setAqglzd_y("是");
			}else if(vo.getAqglzd_y().equals("0")){
				vo.setAqglzd_y("否");
			}
			
			if(vo.getAqsgfa_y().equals("1")){
				vo.setAqsgfa_y("是");
			}else if(vo.getAqsgfa_y().equals("0")){
				vo.setAqsgfa_y("否");
			}
			
			if(vo.getAqsgsx_y().equals("1")){
				vo.setAqsgsx_y("是");
			}else if(vo.getAqsgsx_y().equals("0")){
				vo.setAqsgsx_y("否");
			}
			
			if(vo.getLsss_y().equals("1")){
				vo.setLsss_y("是");
			}else if(vo.getLsss_y().equals("0")){
				vo.setLsss_y("否");
			}
			
			if(vo.getWl_y().equals("1")){
				vo.setWl_y("是");
			}else if(vo.getWl_y().equals("0")){
				vo.setWl_y("否");
			}
			
			if(vo.getCxt_y().equals("1")){
				vo.setCxt_y("是");
			}else if(vo.getCxt_y().equals("0")){
				vo.setCxt_y("否");
			}
			
			if(vo.getPmt_y().equals("1")){
				vo.setPmt_y("是");
			}else if(vo.getPmt_y().equals("0")){
				vo.setPmt_y("否");
			}
			
			if(vo.getPmt_bz_y().equals("1")){
				vo.setPmt_bz_y("是");
			}else if(vo.getPmt_bz_y().equals("0")){
				vo.setPmt_bz_y("否");
			}
			
			if(vo.getJxsb_y().equals("1")){
				vo.setJxsb_y("是");
			}else if(vo.getJxsb_y().equals("0")){
				vo.setJxsb_y("否");
			}
			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="建设工程施工开工前安全条件备案表";
			String workName="建设工程施工开工前安全条件备案表"+vo.getJsgckgaqsctjfc_uid()+".doc";
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
        BuSpywJsgckgaqsctjfcVO vo = new BuSpywJsgckgaqsctjfcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setJsgckgaqsctjfc_uid(DBUtil.getSequenceValue("JSGCKGAQSCTJFC_UID"));
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            vo.setCreated_date(new Date());
            // 插入
			buSpywJsgckgaqsctjfcDao.save(vo);
            resultVO = vo.getRowJson();

          
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("sg_建设工程施工开工前安全条件备案表{}", e.getMessage());
            SystemException.handleMessageException("sg_建设工程施工开工前安全条件备案表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywJsgckgaqsctjfcVO vo = new BuSpywJsgckgaqsctjfcVO();

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
            buSpywJsgckgaqsctjfcDao.update(vo);
            resultVO = vo.getRowJson();

       
        } catch (DaoException e) {
            logger.error("sg_建设工程施工开工前安全条件备案表{}", e.getMessage());
            SystemException.handleMessageException("sg_建设工程施工开工前安全条件备案表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywJsgckgaqsctjfcVO vo = new BuSpywJsgckgaqsctjfcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			buSpywJsgckgaqsctjfcDao.delete(BuSpywJsgckgaqsctjfcVO.class, vo.getJsgckgaqsctjfc_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("sg_建设工程施工开工前安全条件备案表{}", e.getMessage());
            SystemException.handleMessageException("sg_建设工程施工开工前安全条件备案表删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywJsgckgaqsctjfcDaoImpl")
	public void setBuSpywJsgckgaqsctjfcDao(BuSpywJsgckgaqsctjfcDao buSpywJsgckgaqsctjfcDao) {
		this.buSpywJsgckgaqsctjfcDao = buSpywJsgckgaqsctjfcDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJsgckgaqsctjfcDao);
	}
    
}
