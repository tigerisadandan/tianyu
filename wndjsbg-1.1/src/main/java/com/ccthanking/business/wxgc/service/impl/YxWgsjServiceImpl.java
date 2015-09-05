/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxWgsjService.java
 * 创建日期： 2015-01-21 上午 11:47:15
 * 功能：    接口：微型工程违规事件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-21 上午 11:47:15  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.wxgc.dao.YxWgsjDao;
import com.ccthanking.business.wxgc.service.YxWgsjService;
import com.ccthanking.business.wxgc.vo.YxWgsjVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;



/**
 * <p> YxWgsjService.java </p>
 * <p> 功能：微型工程违规事件 </p>
 *
 * <p><a href="YxWgsjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-21
 * 
 */

@Service
public class YxWgsjServiceImpl extends Base1ServiceImpl<YxWgsjVO, String> implements YxWgsjService {

	private static Logger logger = LoggerFactory.getLogger(YxWgsjServiceImpl.class);
    
    private YxWgsjDao yxWgsjDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = yxWgsjDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("微型工程违规事件{}", e.getMessage());
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxWgsjVO vo = new YxWgsjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));


            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            // 插入
			yxWgsjDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("微型工程违规事件{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxWgsjVO vo = new YxWgsjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
          
            // 修改
            yxWgsjDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("微型工程违规事件{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxWgsjVO vo = new YxWgsjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxWgsjDao.delete(YxWgsjVO.class, vo.getWgsj_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("微型工程违规事件{}", e.getMessage());
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxWgsjDaoImpl")
	public void setYxWgsjDao(YxWgsjDao yxWgsjDao) {
		this.yxWgsjDao = yxWgsjDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) yxWgsjDao);
	}
    
}
