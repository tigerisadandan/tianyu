/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywSpfjfsyjgyssqService.java
 * 创建日期： 2014-06-09 下午 01:59:10
 * 功能：    接口：商品房交付使用竣工验收申请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 01:59:10  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywSpfjfsyjgyssqDao;
import com.ccthanking.business.sp.service.BuSpywSpfjfsyjgyssqService;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.business.spyw.vo.BuSpywPssgtscsqVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqPtmxVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqVO;
import com.ccthanking.business.spyw.vo.BuSpywYwsrwsqVO;
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
 * <p> BuSpywSpfjfsyjgyssqService.java </p>
 * <p> 功能：商品房交付使用竣工验收申请 </p>
 *
 * <p><a href="BuSpywSpfjfsyjgyssqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Service
public class BuSpywSpfjfsyjgyssqServiceImpl extends Base1ServiceImpl<BuSpywSpfjfsyjgyssqVO, String> implements BuSpywSpfjfsyjgyssqService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSpfjfsyjgyssqServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SPFJFSYJGYSSQ;
    
    private BuSpywSpfjfsyjgyssqDao buSpywSpfjfsyjgyssqDao;
    @Autowired 
    private BuSpywSpfjfsyjgyssqPtmxServiceImpl buSpywSpfjfsyjgyssqPtmxServiceImpl;
	@Autowired 
	@Qualifier("buSpywSpfjfsyjgyssqDaoImpl")
	public void setBuSpywSpfjfsyjgyssqDao(BuSpywSpfjfsyjgyssqDao buSpywSpfjfsyjgyssqDao) {
		this.buSpywSpfjfsyjgyssqDao = buSpywSpfjfsyjgyssqDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywSpfjfsyjgyssqDao);

	}
    
    // @Override

	 public String download(String id) throws Exception {
	        
	    	
	    	
	    	BuSpywSpfjfsyjgyssqVO tmp =this.findById(id);
	    	List<?> tmpPtmxVO=buSpywSpfjfsyjgyssqPtmxServiceImpl.find(id);
	    	
	    	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
	    	String fileName_pre = Constants.getString("template_word_js_07", "无锡市新区商品房交付使用竣工验收申请表");
	    	String templateName=fileName_pre;
	    	String fileName="无锡市新区商品房交付使用竣工验收申请表"+id;
	    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
	    	float jhmjSum=0.0f,jgmjSum=0.0f;
	    	
	    	for(int i=0;i<tmpPtmxVO.size();i++){
		    	Map map=(Map) tmpPtmxVO.get(i);
		    	String num=(String) map.get("JHMJ");
		    	String num2=(String) map.get("JGMJ");
		    	jhmjSum+=Float.parseFloat(num);
		    	jgmjSum+=Float.parseFloat(num2);
	    	}
	    	//System.out.print(jhmjSum);
	    	//System.out.print(jgmjSum);
	    	tmp.put("JHMJ_HJ", jhmjSum);
	    	tmp.put("JGMJ_HJ", jgmjSum);
	    	//System.out.print(tmp);
	    	//System.out.print(tmpPtmxVO);
	    	if(tmpPtmxVO!=null&&tmpPtmxVO.size()>0){
	    		
//	    		List<?> tmpPt = new ArrayList();
//	    		Map map= new HashMap();
//	    		map.put("XMMC", "312323213");
//	    		tmpPt.
	    		tmp.put("templist", tmpPtmxVO);
	    		//BuSpywSpfjfsyjgyssqPtmxVO ss= (BuSpywSpfjfsyjgyssqPtmxVO)tmpPtmxVO.get(0);//获取对象
	    		//String mc=ss.getXmmc();
	    	}else{
	    		tmp.put("templist", "");
	    	}
	    	Date sqrq=tmp.getXmhtjfsj_date();
			SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
			String sq=rqgs.format(sqrq);
			tmp.put("XMHTJFSJ_DATE", sq);
	       // FreemarkerHelper.createWord(tmp, filePath, templateName, filePath, fileName);
	        
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
    	BuSpywSpfjfsyjgyssqVO vo=new BuSpywSpfjfsyjgyssqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywSpfjfsyjgyssqDao.queryCondition(json);
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—数据查询失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	

    }
    
    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqVO vo=new BuSpywSpfjfsyjgyssqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywSpfjfsyjgyssqDao.insert(json);
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—数据新增成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—数据新增失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—数据新增失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
       

    }

    public String update(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqVO vo=new BuSpywSpfjfsyjgyssqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywSpfjfsyjgyssqDao.update(json);
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—数据更改成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—数据更改失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—数据更改失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywSpfjfsyjgyssqVO vo=new BuSpywSpfjfsyjgyssqVO();
 		String domresult = null;
 		try {
 			domresult = null;
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "商品房交付使用竣工验收申请—数据删除成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSpfjfsyjgyssq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "商品房交付使用竣工验收申请—数据删除失败", user, "", "");
 			SystemException.handleMessageException("商品房交付使用竣工验收申请—数据删除失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;


	}


	


}
