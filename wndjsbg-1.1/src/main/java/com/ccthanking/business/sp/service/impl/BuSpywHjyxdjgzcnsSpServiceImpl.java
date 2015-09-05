/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsSpService.java
 * 创建日期： 2014-06-17 下午 02:04:43
 * 功能：    接口：建设项目环境影响评价报告表（书）审批
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:04:43  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcnsSpDao;
import com.ccthanking.business.sp.service.BuSpywHjyxdjgzcnsSpService;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsSpVO;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywHjyxdjgzcnsSpService.java </p>
 * <p> 功能：建设项目环境影响评价报告表（书）审批 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsSpService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */

@Service
public class BuSpywHjyxdjgzcnsSpServiceImpl extends Base1ServiceImpl<BuSpywHjyxdjgzcnsSpVO, String> implements BuSpywHjyxdjgzcnsSpService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsSpServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_HJYXDJGZCNS_SP;
    
    private BuSpywHjyxdjgzcnsSpDao buSpywHjyxdjgzcnsSpDao;
    @Autowired
    private BuSpywHjyxdjgzcnsNrgmSpServiceImpl buSpywHjyxdjgzcnsNrgmSpServiceImpl;
    @Autowired
    private BuSpywHjyxdjgzcnsZyssSpServiceImpl buSpywHjyxdjgzcnsZyssSpServiceImpl;
    @Autowired
	@Qualifier("buSpywHjyxdjgzcnsSpDaoImpl")
	public void setBuSpywHjyxdjgzcnsSpDao(BuSpywHjyxdjgzcnsSpDao buSpywHjyxdjgzcnsSpDao) {
		this.buSpywHjyxdjgzcnsSpDao = buSpywHjyxdjgzcnsSpDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywHjyxdjgzcnsSpDao);

	}
    
    // @Override
    public String download(String id) throws Exception {
        
    	
    	
    	BuSpywHjyxdjgzcnsSpVO tmp =this.findById(id);
    	List<Map<String, String>> tmpNrgmVO=buSpywHjyxdjgzcnsNrgmSpServiceImpl.find(id);
    	List<Map<String, String>> tmpZyssVO=buSpywHjyxdjgzcnsZyssSpServiceImpl.find(id);

    	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    	String fileName_pre = Constants.getString("template_word_hb_02_2", "建设项目环境影响评价报告表（书）审批--工业类");
    	String templateName=fileName_pre;
    	String fileName="建设项目环境影响评价报告表（书）审批--工业类"+id;//+tmp.getPsfayssq_uid();
    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	
    	String syt=this.queryReadFile(id);
    	if(StringUtil.isNotBlankStr(syt)){
    	JSONArray array = JSONArray.fromObject(syt);
    	JSONObject jo = JSONObject.fromObject(array.get(0));
    	String sytPath=jo.getString("FILEROOT");
    	String sytName=jo.getString("FILENAME");
    	
       // FreemarkerHelper.createWord(tmp, filePath, templateName, filePath, fileName);
    	String encode=FreemarkerHelper.getImageStr(sytPath,sytName);
        tmp.put("SYT", encode);
    	}
    	//Date now = new Date();
    	Date createDate=tmp.getCreated_date();
		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy年MM月dd日");
		String tbdate=rqgs.format(createDate);
		
		tmp.put("TBRQ_DATE", tbdate);
		
    	tmp.put("templist", tmpNrgmVO);
    	tmp.put("templist2", tmpZyssVO);
    	
        if(FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName)){
        	//Word2PDF.toPdf(filePath+File.separator+fileName);
        	String pathName=filePath+fileName;
            Word2PDF.toPdf(pathName);
        }
        
        String path=filePath+fileName+".xml.pdf";
        return path;
        }
    public String queryCondition(String json) throws Exception {
        
    	String domresult=buSpywHjyxdjgzcnsSpDao.queryCondition(json);
        return domresult;

    }
   public String queryReadFile(String id) throws Exception {
        
    	String domresult=buSpywHjyxdjgzcnsSpDao.queryReadFile(id);
        return domresult;

    }
    
    
    public String insert(String json,Map files) throws Exception {

		
        String resultVO = buSpywHjyxdjgzcnsSpDao.insert(json,files);
        
        return resultVO;

    }

    public String update(String json,Map files) throws Exception {

    	String resultVO = buSpywHjyxdjgzcnsSpDao.update(json,files);
        return resultVO;

    }

    public String delete(String json) throws Exception {


		String resultVO = null;

		
		return resultVO;

	}
	
}
