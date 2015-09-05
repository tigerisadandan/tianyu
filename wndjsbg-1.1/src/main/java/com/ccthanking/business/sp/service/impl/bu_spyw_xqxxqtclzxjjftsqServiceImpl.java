/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_xqxxqtclzxjjftsqService.java
 * 创建日期： 2014-10-27 下午 06:40:51
 * 功能：    接口：墙改基金征收及返退审批事项
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-27 下午 06:40:51  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.bu_spyw_xqxxqtclzxjjftsqDao;
import com.ccthanking.business.sp.service.bu_spyw_xqxxqtclzxjjftsqService;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.business.spyw.vo.BuSpywXqxxqtclzxjjftsqVO;
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
 * <p> bu_spyw_xqxxqtclzxjjftsqService.java </p>
 * <p> 功能：墙改基金征收及返退审批事项 </p>
 *
 * <p><a href="bu_spyw_xqxxqtclzxjjftsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-10-27
 * 
 */

@Service
public class bu_spyw_xqxxqtclzxjjftsqServiceImpl extends Base1ServiceImpl<BuSpywXqxxqtclzxjjftsqVO, String> implements bu_spyw_xqxxqtclzxjjftsqService {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_xqxxqtclzxjjftsqServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_XQXXQTCLZXJJFTSQ;
    
    private bu_spyw_xqxxqtclzxjjftsqDao bu_spyw_xqxxqtclzxjjftsqDao;

    @Autowired
	@Qualifier("bu_spyw_xqxxqtclzxjjftsqDaoImpl")
	public void setbu_spyw_xqxxqtclzxjjftsqDao(bu_spyw_xqxxqtclzxjjftsqDao bu_spyw_xqxxqtclzxjjftsqDao) {
		this.bu_spyw_xqxxqtclzxjjftsqDao = bu_spyw_xqxxqtclzxjjftsqDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) bu_spyw_xqxxqtclzxjjftsqDao);
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
        String domresult = "";
        try {

        	domresult = bu_spyw_xqxxqtclzxjjftsqDao.queryCondition(json);
        }catch (DaoException e) {
        	logger.error("墙改基金征收及返退审批事项{}", e.getMessage());
			SystemException.handleMessageException("墙改基金征收及返退审批事项查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
    	
    	String resultVO ="";
        try{
        	
          resultVO = bu_spyw_xqxxqtclzxjjftsqDao.insert(json);
       
        } catch (DaoException e) {
            logger.error("墙改基金征收及返退审批事项{}", e.getMessage());
            SystemException.handleMessageException("墙改基金征收及返退审批事项新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    public String download(String id) throws Exception {
        
    	
		BuSpywXqxxqtclzxjjftsqVO tmp=new BuSpywXqxxqtclzxjjftsqVO();
	 	
	 	tmp =this.findById(id);
	 	
	 
    	List<Map<String, String>> tmpMxVO=bu_spyw_xqxxqtclzxjjftsqDao.find(id);
    	
    	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    	String fileName_pre = Constants.getString("template_word_js_08", "无锡新区新型墙体材料专项基金返退申请表");
    	String templateName=fileName_pre;
    	String fileName="无锡新区新型墙体材料专项基金返退申请表"+id;//+tmp.getPsfayssq_uid();
    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
   
    	Date sqrq=tmp.getSqrq();
		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy年MM月dd日");
		String sq=rqgs.format(sqrq);
		tmp.put("SQRQ", sq);
		
    	tmp.put("templist", tmpMxVO);
        
        if(FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName)){
        	//Word2PDF.toPdf(filePath+File.separator+fileName);
        	String pathName=filePath+fileName;
            Word2PDF.toPdf(pathName);
        }
        String path=filePath+fileName+".xml.pdf";
        return path;
    }

    public String update(String json) throws Exception {

        String resultVO = null;

        try {
          
        	 resultVO = bu_spyw_xqxxqtclzxjjftsqDao.update(json);
        	 
        } catch (DaoException e) {
            logger.error("墙改基金征收及返退审批事项{}", e.getMessage());
           
            SystemException.handleMessageException("墙改基金征收及返退审批事项修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywXqxxqtclzxjjftsqVO vo = new BuSpywXqxxqtclzxjjftsqVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			bu_spyw_xqxxqtclzxjjftsqDao.delete(BuSpywXqxxqtclzxjjftsqVO.class, vo.getXqxxqtclzxjjft_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "墙改基金征收及返退审批事项删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("墙改基金征收及返退审批事项{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "墙改基金征收及返退审批事项删除失败", user, "", "");
            SystemException.handleMessageException("墙改基金征收及返退审批事项删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	
    
}
