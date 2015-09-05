/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywSgqyyjhcsxQyyjhchzService.java
 * 创建日期： 2014-11-12 上午 11:31:47
 * 功能：    接口：施工企业业绩核查汇总表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-12 上午 11:31:47  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.text.SimpleDateFormat;
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

import com.ccthanking.business.sp.dao.BuSpywSgqyyjhcsxQyyjhchzDao;
import com.ccthanking.business.sp.service.BuSpywSgqyyjhcsxQyyjhchzService;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.business.spyw.vo.BuSpywSgqyyjhcsxQyyjhchzVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywSgqyyjhcsxQyyjhchzService.java </p>
 * <p> 功能：施工企业业绩核查汇总表 </p>
 *
 * <p><a href="BuSpywSgqyyjhcsxQyyjhchzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-12
 * 
 */

@Service
public class BuSpywSgqyyjhcsxQyyjhchzServiceImpl extends Base1ServiceImpl<BuSpywSgqyyjhcsxQyyjhchzVO, String> implements BuSpywSgqyyjhcsxQyyjhchzService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSgqyyjhcsxQyyjhchzServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SGQYYJHCSX_QYYJHCHZ;
    
    private BuSpywSgqyyjhcsxQyyjhchzDao buSpywSgqyyjhcsxQyyjhchzDao;

	@Autowired
	@Qualifier("buSpywSgqyyjhcsxQyyjhchzDaoImpl")
	public void setBuSpywSgqyyjhcsxQyyjhchzDao(BuSpywSgqyyjhcsxQyyjhchzDao buSpywSgqyyjhcsxQyyjhchzDao) {
		this.buSpywSgqyyjhcsxQyyjhchzDao = buSpywSgqyyjhcsxQyyjhchzDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywSgqyyjhcsxQyyjhchzDao);
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywSgqyyjhcsxQyyjhchzDao.queryCondition(json);

         
        }catch (DaoException e) {
        	logger.error("施工企业业绩核查汇总表{}", e.getMessage());
			SystemException.handleMessageException("施工企业业绩核查汇总表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
public String download(String id) throws Exception {
        
    	
    	BuSpywSgqyyjhcsxQyyjhchzVO tmp=new BuSpywSgqyyjhcsxQyyjhchzVO();
	 	
	 	tmp =this.findById(id);
	 	

    	List<Map<String, String>> tmpMxVO=buSpywSgqyyjhcsxQyyjhchzDao.find(id);
    	
    	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    	String fileName_pre = Constants.getString("template_word_js_13", "建设工程企业资质申报企业业绩核查汇总表");
    	String templateName=fileName_pre;
    	String fileName="建设工程企业资质申报企业业绩核查汇总表"+id;//+tmp.getPsfayssq_uid();
    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
   
    	tmp.put("templist", tmpMxVO);
    	
    	Date sqrq=tmp.getTbrq();
		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		String sq=rqgs.format(sqrq);
		tmp.put("TBRQ", sq);
    		
        if(FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName)){
        	String pathName=filePath+fileName;
            Word2PDF.toPdf(pathName);
        }
        String path=filePath+fileName+".xml.pdf";
        return path;
    }
    public String insert(String json) throws Exception {

        String resultVO = null;

        try {
            
        	resultVO=buSpywSgqyyjhcsxQyyjhchzDao.insert(json);
         
        } catch (DaoException e) {
            logger.error("施工企业业绩核查汇总表{}", e.getMessage());
            SystemException.handleMessageException("施工企业业绩核查汇总表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        String resultVO = null;

        try {
           
        	resultVO= buSpywSgqyyjhcsxQyyjhchzDao.update(json);
            
        } catch (DaoException e) {
            logger.error("施工企业业绩核查汇总表{}", e.getMessage());
            SystemException.handleMessageException("施工企业业绩核查汇总表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		String resultVo = null;
		try {
			
		
		} catch (DaoException e) {
            logger.error("施工企业业绩核查汇总表{}", e.getMessage());
            SystemException.handleMessageException("施工企业业绩核查汇总表删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

}
