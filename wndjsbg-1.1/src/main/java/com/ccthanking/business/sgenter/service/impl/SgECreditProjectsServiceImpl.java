/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgECreditProjectsService.java
 * 创建日期： 2014-04-20 下午 02:22:53
 * 功能：    接口：企业参与项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:22:53  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sgenter.dao.SgECreditProjectsDao;
import com.ccthanking.business.sgenter.service.SgECreditProjectsService;
import com.ccthanking.business.sgenter.vo.SgECreditProjectsVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgECreditProjectsService.java </p>
 * <p> 功能：企业参与项目 </p>
 *
 * <p><a href="SgECreditProjectsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

@Service
public class SgECreditProjectsServiceImpl extends Base1ServiceImpl<SgECreditProjectsVO, String> implements SgECreditProjectsService {

	private static Logger logger = LoggerFactory.getLogger(SgECreditProjectsServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.SG_E_CREDIT_PROJECTS;
    //YwlxManager.SG_E_CREDIT_PROJECTS;
    private SgECreditProjectsDao sgECreditProjectsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgECreditProjectsDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("企业参与项目{}", e.getMessage());
			SystemException.handleMessageException("企业参与项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SgECreditProjectsVO vo = new SgECreditProjectsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            BusinessUtil.setInsertCommonFields(vo, user);

            // 插入
			sgECreditProjectsDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getCredit_projects_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "企业参与项目新增成功", user, "", "");
            
            
        } catch (DaoException e) {
            logger.error("企业参与项目{}", e.getMessage());
            LogManager.writeUserLog(vo.getCredit_projects_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "企业参与项目新增失败", user, "", "");
            SystemException.handleMessageException("企业参与项目新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SgECreditProjectsVO vo = new SgECreditProjectsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

          	BusinessUtil.setUpdateCommonFields(vo, user);

            // 修改
            sgECreditProjectsDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getCredit_projects_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "企业参与项目修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("企业参与项目{}", e.getMessage());
            LogManager.writeUserLog(vo.getCredit_projects_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "企业参与项目修改失败", user, "", "");
            SystemException.handleMessageException("企业参与项目修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SgECreditProjectsVO vo = new SgECreditProjectsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			sgECreditProjectsDao.delete(SgECreditProjectsVO.class, vo.getCredit_projects_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(vo.getCredit_projects_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,user.getName() + "企业参与项目删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("企业参与项目{}", e.getMessage());
            LogManager.writeUserLog(vo.getCredit_projects_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "企业参与项目删除失败", user, "", "");
            SystemException.handleMessageException("企业参与项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sgECreditProjectsDaoImpl")
	public void setSgECreditProjectsDao(SgECreditProjectsDao sgECreditProjectsDao) {
		this.sgECreditProjectsDao = sgECreditProjectsDao;
	}
	
	public String queryXmList(String uid) throws Exception {
		return sgECreditProjectsDao.queryXmList(uid);
	}
}
