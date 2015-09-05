/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.SgSpWxygcqdService.java
 * 创建日期： 2015-04-23 上午 11:44:17
 * 功能：    接口：较大危险源工程清单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 上午 11:44:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service.impl;


import java.util.Map;

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
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.wxy.vo.SgSpWxygcqdVO;
import com.ccthanking.business.dtgl.wxy.dao.SgSpWxygcqdDao;
import com.ccthanking.business.dtgl.wxy.service.SgSpWxygcqdService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgSpWxygcqdService.java </p>
 * <p> 功能：较大危险源工程清单 </p>
 *
 * <p><a href="SgSpWxygcqdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

@Service
public class SgSpWxygcqdServiceImpl extends Base1ServiceImpl<SgSpWxygcqdVO, String> implements SgSpWxygcqdService {

	private static Logger logger = LoggerFactory.getLogger(SgSpWxygcqdServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.SG_SP_WXYGCQD;
    
    private SgSpWxygcqdDao sgSpWxygcqdDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgSpWxygcqdDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("较大危险源工程清单{}", e.getMessage());
			SystemException.handleMessageException("较大危险源工程清单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryWxYBh(String id) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgSpWxygcqdDao.queryWxYBh(id, null, null);

           
        }catch (DaoException e) {
        	logger.error("较大危险源工程清单{}", e.getMessage());
			SystemException.handleMessageException("较大危险源工程清单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    
    public String queryGcStatus(Map id) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgSpWxygcqdDao.queryGcStatus(id, null, null);

           
        }catch (DaoException e) {
        	logger.error("较大危险源工程清单{}", e.getMessage());
			SystemException.handleMessageException("较大危险源工程清单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SgSpWxygcqdVO vo = new SgSpWxygcqdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
         
            // 插入
			sgSpWxygcqdDao.save(vo);
            resultVO = vo.getRowJson();

          
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("较大危险源工程清单{}", e.getMessage());
           SystemException.handleMessageException("较大危险源工程清单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SgSpWxygcqdVO vo = new SgSpWxygcqdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

       
            // 修改
            sgSpWxygcqdDao.update(vo);
            resultVO = vo.getRowJson();

       
        } catch (DaoException e) {
            logger.error("较大危险源工程清单{}", e.getMessage());
           SystemException.handleMessageException("较大危险源工程清单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			sgSpWxygcqdDao.delete(SgSpWxygcqdVO.class, vo.getWxygcqd_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("较大危险源工程清单{}", e.getMessage());
            SystemException.handleMessageException("较大危险源工程清单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sgSpWxygcqdDaoImpl")
	public void setSgSpWxygcqdDao(SgSpWxygcqdDao sgSpWxygcqdDao) {
		this.sgSpWxygcqdDao = sgSpWxygcqdDao;
	}

	
}
