/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxXypjWgsjService.java
 * 创建日期： 2015-01-22 上午 09:41:08
 * 功能：    接口：微型工程违规事件和信用评价关联
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-22 上午 09:41:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


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

import com.ccthanking.business.wxgc.vo.YxXypjWgsjVO;
import com.ccthanking.business.wxgc.dao.YxXypjWgsjDao;
import com.ccthanking.business.wxgc.service.YxXypjWgsjService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxXypjWgsjService.java </p>
 * <p> 功能：微型工程违规事件和信用评价关联 </p>
 *
 * <p><a href="YxXypjWgsjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-22
 * 
 */

@Service
public class YxXypjWgsjServiceImpl extends Base1ServiceImpl<YxXypjWgsjVO, String> implements YxXypjWgsjService {

	private static Logger logger = LoggerFactory.getLogger(YxXypjWgsjServiceImpl.class);
	
    private YxXypjWgsjDao yxXypjWgsjDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxXypjWgsjDao.queryCondition(json, null, null);
            
        }catch (DaoException e) {
        	logger.error("微型工程违规事件和信用评价关联{}", e.getMessage());
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        YxXypjWgsjVO vo = new YxXypjWgsjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
			yxXypjWgsjDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("微型工程违规事件和信用评价关联{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxXypjWgsjVO vo = new YxXypjWgsjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 修改
            yxXypjWgsjDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("微型工程违规事件和信用评价关联{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxXypjWgsjVO vo = new YxXypjWgsjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			vo.setValueFromJson(jsonObj);
			//删除   根据据主键
			yxXypjWgsjDao.delete(YxXypjWgsjVO.class, vo.getXypjwgsj_uid());
			resultVo = vo.getRowJson();
		} catch (DaoException e) {
            logger.error("微型工程违规事件和信用评价关联{}", e.getMessage());
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxXypjWgsjDaoImpl")
	public void setYxXypjWgsjDao(YxXypjWgsjDao yxXypjWgsjDao) {
		this.yxXypjWgsjDao = yxXypjWgsjDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) yxXypjWgsjDao);
	}
    
}
