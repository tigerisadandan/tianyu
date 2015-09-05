/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzThlyService.java
 * 创建日期： 2014-06-29 上午 10:05:44
 * 功能：    接口：步骤处理时的退回理由
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-29 上午 10:05:44  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


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
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.spxx.vo.BuSpBzThlyVO;
import com.ccthanking.business.spxx.dao.BuSpBzThlyDao;
import com.ccthanking.business.spxx.service.BuSpBzThlyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpBzThlyService.java </p>
 * <p> 功能：步骤处理时的退回理由 </p>
 *
 * <p><a href="BuSpBzThlyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-29
 * 
 */

@Service
public class BuSpBzThlyServiceImpl extends Base1ServiceImpl<BuSpBzThlyVO, String> implements BuSpBzThlyService {

	private static Logger logger = LoggerFactory.getLogger(BuSpBzThlyServiceImpl.class);

    private BuSpBzThlyDao buSpBzThlyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpBzThlyDao.queryCondition(json, null, null);
     
        }catch (DaoException e) {
        	logger.error("步骤处理时的退回理由{}", e.getMessage());
			SystemException.handleMessageException("步骤处理时的退回理由查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpBzThlyVO vo = new BuSpBzThlyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键

            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            vo.setCreated_date(new Date());
            // 插入
			buSpBzThlyDao.save(vo);
            resultVO = vo.getRowJson();

                 
        } catch (DaoException e) {
            logger.error("步骤处理时的退回理由新增{}", e.getMessage());
            SystemException.handleMessageException("步骤处理时的退回理由新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpBzThlyVO vo = new BuSpBzThlyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
            vo.setUpdate_date(new Date());
            // 修改
            buSpBzThlyDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("步骤处理时的退回理由更新{}", e.getMessage());
            SystemException.handleMessageException("步骤处理时的退回理由修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpBzThlyVO vo = new BuSpBzThlyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			buSpBzThlyDao.delete(BuSpBzThlyVO.class, vo.getThly_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("步骤处理时的退回理由好散出{}", e.getMessage());
           SystemException.handleMessageException("步骤处理时的退回理由删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpBzThlyDaoImpl")
	public void setBuSpBzThlyDao(BuSpBzThlyDao buSpBzThlyDao) {
		this.buSpBzThlyDao = buSpBzThlyDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpBzThlyDao);
	}

	public String getSpYwxx() throws Exception {
		return buSpBzThlyDao.getSpYwxx();
	}
    
}
