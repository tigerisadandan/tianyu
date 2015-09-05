/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgECreditCommendRewardService.java
 * 创建日期： 2014-04-20 下午 02:19:52
 * 功能：    接口：企业荣誉
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:19:52  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sgenter.dao.SgECreditCommendRewardDao;
import com.ccthanking.business.sgenter.service.SgECreditCommendRewardService;
import com.ccthanking.business.sgenter.vo.SgECreditCommendRewardVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgECreditCommendRewardService.java </p>
 * <p> 功能：企业荣誉 </p>
 *
 * <p><a href="SgECreditCommendRewardService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

@Service
public class SgECreditCommendRewardServiceImpl extends Base1ServiceImpl<SgECreditCommendRewardVO, String> implements SgECreditCommendRewardService {

	private static Logger logger = LoggerFactory.getLogger(SgECreditCommendRewardServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.SG_E_CREDIT_COMMEND_REWARD;
    
    private SgECreditCommendRewardDao sgECreditCommendRewardDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgECreditCommendRewardDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("企业荣誉{}", e.getMessage());
			SystemException.handleMessageException("企业荣誉查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SgECreditCommendRewardVO vo = new SgECreditCommendRewardVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            BusinessUtil.setInsertCommonFields(vo, user);

            // 插入
			sgECreditCommendRewardDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("企业荣誉{}", e.getMessage());
            SystemException.handleMessageException("企业荣誉新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SgECreditCommendRewardVO vo = new SgECreditCommendRewardVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

          	BusinessUtil.setUpdateCommonFields(vo, user);

            // 修改
            sgECreditCommendRewardDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("企业荣誉{}", e.getMessage());
            SystemException.handleMessageException("企业荣誉修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SgECreditCommendRewardVO vo = new SgECreditCommendRewardVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			resultVo = vo.getRowJson();
//

		} catch (DaoException e) {
            logger.error("企业荣誉{}", e.getMessage());
            SystemException.handleMessageException("企业荣誉删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sgECreditCommendRewardDaoImpl")
	public void setSgECreditCommendRewardDao(SgECreditCommendRewardDao sgECreditCommendRewardDao) {
		this.sgECreditCommendRewardDao = sgECreditCommendRewardDao;
	}

	public String queryJxList(String uid) throws Exception {
		return sgECreditCommendRewardDao.queryJxList(uid);
	}
    
}
