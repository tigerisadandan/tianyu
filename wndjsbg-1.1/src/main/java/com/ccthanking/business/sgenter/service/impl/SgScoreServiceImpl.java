/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgScoreService.java
 * 创建日期： 2014-06-09 上午 09:35:20
 * 功能：    接口：分数
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:35:20  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service.impl;


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

import com.ccthanking.business.sgenter.vo.SgScoreVO;
import com.ccthanking.business.sgenter.dao.SgScoreDao;
import com.ccthanking.business.sgenter.service.SgScoreService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgScoreService.java </p>
 * <p> 功能：分数 </p>
 *
 * <p><a href="SgScoreService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Service
public class SgScoreServiceImpl extends Base1ServiceImpl<SgScoreVO, String> implements SgScoreService {

	private static Logger logger = LoggerFactory.getLogger(SgScoreServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.SG_SCORE;
    
    private SgScoreDao sgScoreDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgScoreDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<分数>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("分数{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "分数查询失败", user, "", "");
			SystemException.handleMessageException("分数查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SgScoreVO vo = new SgScoreVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键

            // 插入
			sgScoreDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "分数新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("分数{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "分数新增失败", user, "", "");
            SystemException.handleMessageException("分数新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SgScoreVO vo = new SgScoreVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            sgScoreDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "分数修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("分数{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "分数修改失败", user, "", "");
            SystemException.handleMessageException("分数修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SgScoreVO vo = new SgScoreVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			sgScoreDao.delete(SgScoreVO.class, vo.getSg_score_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "分数删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("分数{}", e.getMessage());
            LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "分数删除失败", user, "", "");
            SystemException.handleMessageException("分数删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sgScoreDaoImpl")
	public void setSgScoreDao(SgScoreDao sgScoreDao) {
		this.sgScoreDao = sgScoreDao;
	}

	public String tongjiScore(String uid,String type) throws Exception {
		String returnStr = "";
		if ("scoreInfo".equals(type)) {
			JSONArray obj = sgScoreDao.getScoreInfo(uid);
			returnStr = obj.toString();
		}else if("jbInfo".equals(type)) {
			JSONArray arr = sgScoreDao.getJCscore(uid);
			returnStr = arr.toString();
		}else if("xmInfo".equals(type)) {
			JSONArray arr = sgScoreDao.getXmInfo(uid);
			returnStr = arr.toString();
		}else if("jxInfo".equals(type)) {
			JSONArray arr = sgScoreDao.getJxInfo(uid);
			returnStr = arr.toString();
		}
		
		return returnStr;
	}
    
}
