/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyMqService.java
 * 创建日期： 2015-04-28 下午 06:31:22
 * 功能：    接口：幕墙工程提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-28 下午 06:31:22  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.dtgl.wxy.vo.WxyMqVO;
import com.ccthanking.business.dtgl.wxy.vo.WxySjkVO;
import com.ccthanking.business.dtgl.wxy.dao.WxyMqDao;
import com.ccthanking.business.dtgl.wxy.service.WxyMqService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> WxyMqService.java </p>
 * <p> 功能：幕墙工程提示单 </p>
 *
 * <p><a href="WxyMqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-28
 * 
 */

@Service
public class WxyMqServiceImpl extends Base1ServiceImpl<WxyMqVO, String> implements WxyMqService {

	private static Logger logger = LoggerFactory.getLogger(WxyMqServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.WXY_MQ;
    
    private WxyMqDao wxyMqDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = wxyMqDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("幕墙工程提示单{}", e.getMessage());
			SystemException.handleMessageException("幕墙工程提示单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public boolean tuiHui(String gcId) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		boolean flag = false;
        try {
             flag = this.wxyMqDao.tuiHui(gcId);
        } catch (DaoException e) {
            logger.error("幕墙提示单{}", e.getMessage());
            SystemException.handleMessageException("幕墙提示单修改失败,请联系相关人员处理");
        } finally {
        	
        }
        return flag;

    }
    
//  toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

        	WxyMqVO vo = this.findById(id);
        	
        	SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		    String cdate=rqgs.format(vo.getCreate_date());
		    String shdate=rqgs.format(vo.getShenhe_date());
		    String bdate=rqgs.format(vo.getPlan_b_date());
		    String edate=rqgs.format(vo.getPlan_e_date());
		    vo.remove("CREATE_DATE");
		    vo.put("CREATE_DATE", cdate);
		    vo.remove("SHENHE_DATE");
		    vo.put("SHENHE_DATE", shdate);
		    vo.remove("ZJLZ_DATE");
		    vo.put("ZJLZ_DATE", shdate);
		    vo.remove("PLAN_B_DATE");
		    vo.put("PLAN_B_DATE", bdate);
		    vo.remove("PLAN_E_DATE");
		    vo.put("PLAN_E_DATE", edate);
        	
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="幕墙安装工程申报表";
			String workName="幕墙安装工程申报表"+vo.getWxy_mq_uid()+".doc";
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
		    
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			domresult= filename+".xml.pdf";
        }catch (DaoException e) {	
        	logger.error("幕墙安装工程申报表PDF{}", e.getMessage());
			SystemException.handleMessageException("幕墙安装工程申报表PDF失败,请联系相关人员处理");
        }
		return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WxyMqVO vo = new WxyMqVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
       
            // 插入
			wxyMqDao.save(vo);
            resultVO = vo.getRowJson();
 
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("幕墙工程提示单{}", e.getMessage());
            SystemException.handleMessageException("幕墙工程提示单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WxyMqVO vo = new WxyMqVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            wxyMqDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("幕墙工程提示单{}", e.getMessage());
            SystemException.handleMessageException("幕墙工程提示单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		WxyMqVO vo = new WxyMqVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			wxyMqDao.delete(WxyMqVO.class, vo.getWxy_mq_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("幕墙工程提示单{}", e.getMessage());
           SystemException.handleMessageException("幕墙工程提示单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("wxyMqDaoImpl")
	public void setWxyMqDao(WxyMqDao wxyMqDao) {
		this.wxyMqDao = wxyMqDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) wxyMqDao);
	}
    
}
