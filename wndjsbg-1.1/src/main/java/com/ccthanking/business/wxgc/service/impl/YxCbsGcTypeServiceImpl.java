/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxCbsGcTypeService.java
 * 创建日期： 2014-12-25 下午 04:33:18
 * 功能：    接口：预选承包商和微型工程类型关联关系
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-25 下午 04:33:18  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.vo.YxCbsGcTypeVO;
import com.ccthanking.business.wxgc.dao.YxCbsGcTypeDao;
import com.ccthanking.business.wxgc.service.YxCbsGcTypeService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxCbsGcTypeService.java </p>
 * <p> 功能：预选承包商和微型工程类型关联关系 </p>
 *
 * <p><a href="YxCbsGcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-25
 * 
 */

@Service
public class YxCbsGcTypeServiceImpl extends Base1ServiceImpl<YxCbsGcTypeVO, String> implements YxCbsGcTypeService {

	private static Logger logger = LoggerFactory.getLogger(YxCbsGcTypeServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.YX_CBS_GC_TYPE;
    
    private YxCbsGcTypeDao yxCbsGcTypeDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxCbsGcTypeDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("预选承包商和微型工程类型关联关系{}", e.getMessage());
			SystemException.handleMessageException("预选承包商和微型工程类型关联关系查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxCbsGcTypeVO vo = new YxCbsGcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键

            // 插入
			yxCbsGcTypeDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("预选承包商和微型工程类型关联关系{}", e.getMessage());
            SystemException.handleMessageException("预选承包商和微型工程类型关联关系新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();   
        String resultVO = null;
        YxCbsGcTypeVO vo = new YxCbsGcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            yxCbsGcTypeDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("预选承包商和微型工程类型关联关系{}", e.getMessage());
            SystemException.handleMessageException("预选承包商和微型工程类型关联关系修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxCbsGcTypeVO vo = new YxCbsGcTypeVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
//			yxCbsGcTypeDao.delete(YxCbsGcTypeVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("预选承包商和微型工程类型关联关系{}", e.getMessage());
            SystemException.handleMessageException("预选承包商和微型工程类型关联关系删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxCbsGcTypeDaoImpl")
	public void setYxCbsGcTypeDao(YxCbsGcTypeDao yxCbsGcTypeDao) {
		this.yxCbsGcTypeDao = yxCbsGcTypeDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxCbsGcTypeDao);
	}
    
}
