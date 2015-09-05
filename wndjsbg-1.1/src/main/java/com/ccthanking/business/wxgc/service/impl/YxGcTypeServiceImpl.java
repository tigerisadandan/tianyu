/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxGcTypeService.java
 * 创建日期： 2015-01-06 下午 10:29:23
 * 功能：    接口：微型工程类型维护
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-06 下午 10:29:23  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.dao.YxGcTypeDao;
import com.ccthanking.business.wxgc.service.YxGcTypeService;
import com.ccthanking.business.wxgc.vo.YxGcTypeVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxGcTypeService.java </p>
 * <p> 功能：微型工程类型维护 </p>
 *
 * <p><a href="YxGcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-06
 * 
 */

@Service
public class YxGcTypeServiceImpl extends Base1ServiceImpl<YxGcTypeVO, String> implements YxGcTypeService {

	private static Logger logger = LoggerFactory.getLogger(YxGcTypeServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.YX_GC_TYPE;
    
    private YxGcTypeDao yxGcTypeDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxGcTypeDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("微型工程类型维护{}", e.getMessage());
			SystemException.handleMessageException("微型工程类型维护查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxGcTypeVO vo = new YxGcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getAccount());
            vo.setCreated_uid(user.getUserSN());
            vo.setEnabled("1");
            // 插入
			yxGcTypeDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("微型工程类型维护{}", e.getMessage());
            SystemException.handleMessageException("微型工程类型维护新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxGcTypeVO vo = new YxGcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getAccount());
            vo.setUpdate_uid(user.getUserSN());
            // 修改
            yxGcTypeDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("微型工程类型维护{}", e.getMessage());
            SystemException.handleMessageException("微型工程类型维护修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxGcTypeVO vo = new YxGcTypeVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxGcTypeDao.delete(YxGcTypeVO.class, vo.getGc_type_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("微型工程类型维护{}", e.getMessage());
            SystemException.handleMessageException("微型工程类型维护删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxGcTypeDaoImpl")
	public void setYxGcTypeDao(YxGcTypeDao yxGcTypeDao) {
		this.yxGcTypeDao = yxGcTypeDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxGcTypeDao);
	}
    
}
