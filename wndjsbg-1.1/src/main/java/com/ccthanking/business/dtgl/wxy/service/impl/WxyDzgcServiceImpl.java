/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyDzgcService.java
 * 创建日期： 2015-04-23 下午 02:20:11
 * 功能：    接口：吊装工程提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 下午 02:20:11  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service.impl;


import java.text.SimpleDateFormat;

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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.wxy.vo.WxyDzgcVO;
import com.ccthanking.business.dtgl.wxy.vo.WxySjkVO;
import com.ccthanking.business.dtgl.wxy.dao.WxyDzgcDao;
import com.ccthanking.business.dtgl.wxy.service.WxyDzgcService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> WxyDzgcService.java </p>
 * <p> 功能：吊装工程提示单 </p>
 *
 * <p><a href="WxyDzgcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

@Service
public class WxyDzgcServiceImpl extends Base1ServiceImpl<WxyDzgcVO, String> implements WxyDzgcService {

	private static Logger logger = LoggerFactory.getLogger(WxyDzgcServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.WXY_DZGC;
    
    private WxyDzgcDao wxyDzgcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = wxyDzgcDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("吊装工程提示单{}", e.getMessage());
			SystemException.handleMessageException("吊装工程提示单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public boolean tuiHui(String gcId) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		boolean flag = false;
        try {
             flag = wxyDzgcDao.tuiHui(gcId);
        } catch (DaoException e) {
            logger.error("吊装工程提示单{}", e.getMessage());
            SystemException.handleMessageException("吊装工程提示单修改失败,请联系相关人员处理");
        } finally {
        	
        }
        return flag;

    }
    
//  toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

        	WxyDzgcVO vo = this.findById(id);
        	
        	SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		    String cdate=rqgs.format(vo.getCreate_date());
		    String shdate=rqgs.format(vo.getShenhe_date());
		    String bdate=rqgs.format(vo.getPlan_b_date());
		    String edate=rqgs.format(vo.getPlan_e_date());
		    String ZJLZ_DATE=rqgs.format(vo.getZjlz_date());
		    vo.remove("CREATE_DATE");
		    vo.put("CREATE_DATE", cdate);
		    vo.remove("SHENHE_DATE");
		    vo.put("SHENHE_DATE", shdate);
		    vo.remove("ZJLZ_DATE");
		    vo.put("ZJLZ_DATE", ZJLZ_DATE);
		    vo.remove("PLAN_B_DATE");
		    vo.put("PLAN_B_DATE", bdate);
		    vo.remove("PLAN_E_DATE");
		    vo.put("PLAN_E_DATE", edate);
        	
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="吊装工程申报表";
			String workName="吊装工程申报表"+vo.getWxy_dzgc_uid()+".doc";
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
		    
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			domresult= filename+".xml.pdf";
        }catch (DaoException e) {	
        	logger.error("深基坑工程申报表PDF{}", e.getMessage());
			SystemException.handleMessageException("深基坑工程申报表PDF失败,请联系相关人员处理");
        }
		return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WxyDzgcVO vo = new WxyDzgcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
          
            // 插入
			wxyDzgcDao.save(vo);
            resultVO = vo.getRowJson();

         
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("吊装工程提示单{}", e.getMessage());
            SystemException.handleMessageException("吊装工程提示单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WxyDzgcVO vo = new WxyDzgcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            wxyDzgcDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("吊装工程提示单{}", e.getMessage());
           SystemException.handleMessageException("吊装工程提示单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		WxyDzgcVO vo = new WxyDzgcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

					//删除   根据据主键
			wxyDzgcDao.delete(WxyDzgcVO.class, vo.getWxy_dzgc_uid());

			resultVo = vo.getRowJson();

	
		} catch (DaoException e) {
            logger.error("吊装工程提示单{}", e.getMessage());
            SystemException.handleMessageException("吊装工程提示单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("wxyDzgcDaoImpl")
	public void setWxyDzgcDao(WxyDzgcDao wxyDzgcDao) {
		this.wxyDzgcDao = wxyDzgcDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) wxyDzgcDao);
	}
    
}
