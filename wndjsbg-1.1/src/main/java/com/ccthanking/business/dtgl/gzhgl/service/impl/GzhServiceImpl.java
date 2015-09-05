/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhService.java
 * 创建日期： 2015-05-27 上午 10:40:51
 * 功能：    接口：告知会
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-27 上午 10:40:51  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl.service.impl;


import java.util.Date;

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
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.gzhgl.vo.GzhVO;
import com.ccthanking.business.dtgl.gzhgl.dao.GzhDao;
import com.ccthanking.business.dtgl.gzhgl.service.GzhService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> GzhService.java </p>
 * <p> 功能：告知会 </p>
 *
 * <p><a href="GzhService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-27
 * 
 */

@Service
public class GzhServiceImpl extends Base1ServiceImpl<GzhVO, String> implements GzhService {

	private static Logger logger = LoggerFactory.getLogger(GzhServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.GZH;
    
    private GzhDao gzhDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = gzhDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("告知会{}", e.getMessage());
			SystemException.handleMessageException("告知会查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String querySingleCondition(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = gzhDao.querySingleCondition(json);
        }catch (DaoException e) {
        	logger.error("告知会{}", e.getMessage());
			SystemException.handleMessageException("告知会查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        GzhVO vo = new GzhVO();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            String traget_uid = obj.getString("target_uid");
            vo.setValueFromJson(obj);
            vo.setCreate_date(new Date());//当前时间
            vo.setCreate_by(user.getUserSN());//当前登录人
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            // 插入
			gzhDao.save(vo);
			
            resultVO = vo.getRowJson();
            
            if(vo!=null&&vo.getGzh_uid()!=null&&!"".equals(vo.getGzh_uid())){
				 FileUploadUtilService. updateBytragetUid (traget_uid,vo.getGzh_uid());                 
			}
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
        } catch (DaoException e) {
            logger.error("告知会{}", e.getMessage());
            SystemException.handleMessageException("告知会新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        GzhVO vo = new GzhVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            String traget_uid = obj.getString("target_uid");
            vo.setValueFromJson(obj);
            
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); 
             **/
            // 修改
            gzhDao.update(vo);
            resultVO = vo.getRowJson();
            
            //绑定附件
            if(vo!=null&&vo.getGzh_uid()!=null&&!"".equals(vo.getGzh_uid())){
				 FileUploadUtilService. updateBytragetUid (traget_uid,vo.getGzh_uid());                 
			}

        } catch (DaoException e) {
            logger.error("告知会{}", e.getMessage());
            
            SystemException.handleMessageException("告知会修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		GzhVO vo = new GzhVO();
		try {
			
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			//vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			gzhDao.delete(GzhVO.class, jsonObj.getString("t.GZH_UID"));

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("告知会{}", e.getMessage());
            SystemException.handleMessageException("告知会删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
	public String queryUndoneProject(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = gzhDao.queryUndoneProject(json);
			
			
        }catch (DaoException e) {
        	logger.error("告知会{}", e.getMessage());
			SystemException.handleMessageException("项目单位查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;
	}
	
	public String querySgPersonByGC(String json) {
		User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = gzhDao.querySgPersonByGC(json);
        }catch (DaoException e) {
        	logger.error("告知会{}", e.getMessage());
			SystemException.handleMessageException("签到人员查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
	
	@Autowired
	@Qualifier("gzhDaoImpl")
	public void setGzhDao(GzhDao gzhDao) {
		this.gzhDao = gzhDao;
	}
 
}
