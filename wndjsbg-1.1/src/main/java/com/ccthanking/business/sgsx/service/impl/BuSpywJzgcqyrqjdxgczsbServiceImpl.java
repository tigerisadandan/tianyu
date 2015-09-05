/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgsx.service.BuSpywJzgcqyrqjdxgczsbService.java
 * 创建日期： 2015-03-24 下午 01:54:36
 * 功能：    接口：sg_建筑工程企业入区接单项工程注申表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 下午 01:54:36  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgsx.service.impl;


import java.util.Date;

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

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsxmkqtshbaVO;
import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJzgcqyrqjdxgczsbVO;
import com.ccthanking.business.sgsx.dao.BuSpywJzgcqyrqjdxgczsbDao;
import com.ccthanking.business.sgsx.service.BuSpywJzgcqyrqjdxgczsbService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywJzgcqyrqjdxgczsbService.java </p>
 * <p> 功能：sg_建筑工程企业入区接单项工程注申表 </p>
 *
 * <p><a href="BuSpywJzgcqyrqjdxgczsbService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */

@Service
public class BuSpywJzgcqyrqjdxgczsbServiceImpl extends Base1ServiceImpl<BuSpywJzgcqyrqjdxgczsbVO, String> implements BuSpywJzgcqyrqjdxgczsbService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJzgcqyrqjdxgczsbServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_JZGCQYRQJDXGCZSB;
    
    private BuSpywJzgcqyrqjdxgczsbDao buSpywJzgcqyrqjdxgczsbDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJzgcqyrqjdxgczsbDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("sg_建筑工程企业入区接单项工程注申表{}", e.getMessage());
			SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
 //  toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			BuSpywJzgcqyrqjdxgczsbVO vo = this.findById(id);
			if(vo.getGc_zj().subSequence(0, 1).equals(".")){
				vo.setGc_zj("0"+vo.getGc_zj());
			}
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="省（内）外建筑工程企业进入无锡新区单项工程项目注册申请表";
			String workName="省（内）外建筑工程企业进入无锡新区单项工程项目注册申请表"+vo.getJzgcqyrqjdxgczsb_uid()+".doc";
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
        BuSpywJzgcqyrqjdxgczsbVO vo = new BuSpywJzgcqyrqjdxgczsbVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setJzgcqyrqjdxgczsb_uid(DBUtil.getSequenceValue("JZGCQYRQJDXGCZSB_UID"));
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            vo.setCreated_date(new Date());
            // 插入
			buSpywJzgcqyrqjdxgczsbDao.save(vo);
            resultVO = vo.getRowJson();

          	//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("sg_建筑工程企业入区接单项工程注申表{}", e.getMessage());
            SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywJzgcqyrqjdxgczsbVO vo = new BuSpywJzgcqyrqjdxgczsbVO();

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
            buSpywJzgcqyrqjdxgczsbDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("sg_建筑工程企业入区接单项工程注申表{}", e.getMessage());
            SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywJzgcqyrqjdxgczsbVO vo = new BuSpywJzgcqyrqjdxgczsbVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			buSpywJzgcqyrqjdxgczsbDao.delete(BuSpywJzgcqyrqjdxgczsbVO.class, vo.getJzgcqyrqjdxgczsb_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("sg_建筑工程企业入区接单项工程注申表{}", e.getMessage());
            SystemException.handleMessageException("sg_建筑工程企业入区接单项工程注申表删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywJzgcqyrqjdxgczsbDaoImpl")
	public void setBuSpywJzgcqyrqjdxgczsbDao(BuSpywJzgcqyrqjdxgczsbDao buSpywJzgcqyrqjdxgczsbDao) {
		this.buSpywJzgcqyrqjdxgczsbDao = buSpywJzgcqyrqjdxgczsbDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJzgcqyrqjdxgczsbDao);
	}
    
}
