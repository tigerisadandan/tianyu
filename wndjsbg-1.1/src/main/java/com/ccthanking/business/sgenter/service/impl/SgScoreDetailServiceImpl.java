/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgScoreDetailService.java
 * 创建日期： 2014-06-09 上午 09:36:33
 * 功能：    接口：分数主体
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:36:33  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sgenter.vo.SgScoreDetailVO;
import com.ccthanking.business.sgenter.dao.SgScoreDetailDao;
import com.ccthanking.business.sgenter.service.SgScoreDetailService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgScoreDetailService.java </p>
 * <p> 功能：分数主体 </p>
 *
 * <p><a href="SgScoreDetailService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Service
public class SgScoreDetailServiceImpl extends Base1ServiceImpl<SgScoreDetailVO, String> implements SgScoreDetailService {

	private static Logger logger = LoggerFactory.getLogger(SgScoreDetailServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.SG_SCORE_DETAIL;
    
    private SgScoreDetailDao sgScoreDetailDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgScoreDetailDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<分数主体>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("分数主体{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "分数主体查询失败", user, "", "");
			SystemException.handleMessageException("分数主体查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SgScoreDetailVO vo = new SgScoreDetailVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            

            // 插入
			sgScoreDetailDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "分数主体新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("分数主体{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "分数主体新增失败", user, "", "");
            SystemException.handleMessageException("分数主体新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SgScoreDetailVO vo = new SgScoreDetailVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            sgScoreDetailDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "分数主体修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("分数主体{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "分数主体修改失败", user, "", "");
            SystemException.handleMessageException("分数主体修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SgScoreDetailVO vo = new SgScoreDetailVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			sgScoreDetailDao.delete(SgScoreDetailVO.class, vo.getSg_score_detail_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "分数主体删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("分数主体{}", e.getMessage());
            LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "分数主体删除失败", user, "", "");
            SystemException.handleMessageException("分数主体删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sgScoreDetailDaoImpl")
	public void setSgScoreDetailDao(SgScoreDetailDao sgScoreDetailDao) {
		this.sgScoreDetailDao = sgScoreDetailDao;
	}
    
}
