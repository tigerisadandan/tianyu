/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_spfjfsyjgysxmqkService.java
 * 创建日期： 2014-11-06 下午 03:53:16
 * 功能：    接口：商品房交付使用竣工验收项目情况表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:53:16  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.bu_spyw_spfjfsyjgysxmqkDao;
import com.ccthanking.business.sp.service.bu_spyw_spfjfsyjgysbadtjzhzService;
import com.ccthanking.business.sp.service.bu_spyw_spfjfsyjgysxmqkService;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysxmqkVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.ibm.icu.text.SimpleDateFormat;



/**
 * <p> bu_spyw_spfjfsyjgysxmqkService.java </p>
 * <p> 功能：商品房交付使用竣工验收项目情况表 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysxmqkService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Service
public class bu_spyw_spfjfsyjgysxmqkServiceImpl extends Base1ServiceImpl<BuSpywSpfjfsyjgysxmqkVO, String> implements bu_spyw_spfjfsyjgysxmqkService {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysxmqkServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SPFJFSYJGYSXMQK;
    
    private bu_spyw_spfjfsyjgysxmqkDao bu_spyw_spfjfsyjgysxmqkDao;
    @Autowired
    private bu_spyw_spfjfsyjgysbadtjzhzService bu_spyw_spfjfsyjgysbadtjzhzService;
    @Autowired
	@Qualifier("bu_spyw_spfjfsyjgysxmqkDaoImpl")
	public void setbu_spyw_spfjfsyjgysxmqkDao(bu_spyw_spfjfsyjgysxmqkDao bu_spyw_spfjfsyjgysxmqkDao) {
		this.bu_spyw_spfjfsyjgysxmqkDao = bu_spyw_spfjfsyjgysxmqkDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) bu_spyw_spfjfsyjgysxmqkDao);
	}
   
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = bu_spyw_spfjfsyjgysxmqkDao.queryCondition(json);

           
        }catch (DaoException e) {
        	logger.error("商品房交付使用竣工验收项目情况表{}", e.getMessage());
			SystemException.handleMessageException("商品房交付使用竣工验收项目情况表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    public String download(String id,String tid) throws Exception {
        
    	
	    	BuSpywSpfjfsyjgysxmqkVO tmp=new BuSpywSpfjfsyjgysxmqkVO();
	    	BuSpywSpfjfsyjgysbadtjzhzVO tmp2=new BuSpywSpfjfsyjgysbadtjzhzVO();	
	    	
    	 	tmp =this.findById(id);
    	 	tmp2=bu_spyw_spfjfsyjgysbadtjzhzService.findByDtId(tid);
    	 	
    	 	List<Map<String, String>> tmpDtVO=bu_spyw_spfjfsyjgysxmqkDao.findDt(tid);
        	List<Map<String, String>> tmpMxVO=bu_spyw_spfjfsyjgysxmqkDao.find(id);
        	    	 	
        	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
        	String fileName_pre = Constants.getString("template_word_js_07_02", "无锡新区商品房交付使用竣工验收项目情况表");
        	String templateName=fileName_pre;
        	String fileName="无锡新区商品房交付使用竣工验收项目情况表"+id;//+tmp.getPsfayssq_uid();
        	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
        	
        	tmp2.put("templist", tmpMxVO);
        	tmp2.put("templist2", tmpDtVO);
	        	float jgmjSum=0.0f,gdSum=0.0f;
	        	int hsSum=0,csSum=0;
		    	for(int i=0;i<tmpDtVO.size();i++){
			    	Map map=(Map) tmpDtVO.get(i);
			    	if(!"".equals(map.get("JGMJ"))){
				    	String num=(String) map.get("JGMJ");
				    	jgmjSum+=Float.parseFloat(num);
			    	}
			    	if(!"".equals(map.get("HS"))){
			    		String num2=(String) map.get("HS");
			    		hsSum+=Integer.parseInt(num2);
			    	}
			    	if(!"".equals(map.get("CS"))){
			    		String num3=(String) map.get("CS");
			    		csSum+=Integer.parseInt(num3);
			    	}
			    	if(!"".equals(map.get("ZGD"))){
			    		String num4=(String) map.get("ZGD");
			    		gdSum+=Float.parseFloat(num4);
			    	}
			    	
		    	}
		    	
		    	tmp2.put("HJMJ", jgmjSum);
		    	tmp2.put("HJHS", hsSum);
		    	tmp2.put("HJCS", csSum);
		    	tmp2.put("HJGD", gdSum);
		    	
		    	tmp2.put("KGRQ", tmp.getKgrq());
		    	tmp2.put("JGRQ", tmp.getJgrq());
		    	tmp2.put("GHHGZMJ", tmp.getGhhgzmj());
		    	tmp2.put("DS", tmp.getDs());
		    	tmp2.put("QZZZMJ", tmp.getQzzzmj());
		    	tmp2.put("QZGJPTMJ", tmp.getQzgjptmj());
		    	tmp2.put("QZFZZMJ", tmp.getQzfzzmj());
		    	
		    	tmp2.put("DWMC", tmp.getDwmc());
		    	tmp2.put("FZR", tmp.getFzr());
		    	tmp2.put("LXR", tmp.getLxr());
		    	tmp2.put("LXDH", tmp.getLxdh());
		    	tmp2.put("LXDZ", tmp.getLxdz());
		    	tmp2.put("XMMC", tmp.getXmmc());
		    	tmp2.put("XMXZ", tmp.getXmxz());
		    	tmp2.put("XMDZ", tmp.getXmdz());
		    	tmp2.put("SZQ", tmp.getSzq());
		    	
	        	Date sqrq=tmp2.getTbrq();
	    		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy年MM月dd日");
	    		String sq=rqgs.format(sqrq);
	    		tmp2.put("TBRQ", sq);
	    		tmp2.put("DTDWMC", tmp2.getDwmc());
	    		//tmp.put("DESCRIBE", tmp2.getDescribe());
	    		Date kgrq=tmp.getKgrq();
	        	Date jgrq=tmp.getJgrq();
	        	SimpleDateFormat rqgs2 = new SimpleDateFormat("yyyy-MM-dd");
	    		String kg=rqgs2.format(kgrq);
	    		String jg=rqgs2.format(jgrq);
		    	tmp2.put("KGRQ", kg);
		    	tmp2.put("JGRQ", jg);
	    		
            if(FreemarkerHelper.createWord(tmp2, templatePath, templateName, filePath, fileName)){
            	//Word2PDF.toPdf(filePath+File.separator+fileName);
            	String pathName=filePath+fileName;
                Word2PDF.toPdf(pathName);
            }
            String path=filePath+fileName+".xml.pdf";
            return path;
        }
  
    public String insert(String json) throws Exception {

		
		
        String resultVO = null;

        try {
        	resultVO = bu_spyw_spfjfsyjgysxmqkDao.insert(json);
           
            
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表{}", e.getMessage());
         
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        
        String resultVO = null;

        try {
        	resultVO = bu_spyw_spfjfsyjgysxmqkDao.update(json);
          
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表修{}", e.getMessage());
            
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {


		String resultVo = null;
		try {
			
		} catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表{}", e.getMessage());
           
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	
    
}
