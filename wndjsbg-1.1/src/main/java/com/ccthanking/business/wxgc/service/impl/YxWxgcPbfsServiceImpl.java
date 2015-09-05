/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxWxgcPbfsService.java
 * 创建日期： 2015-03-11 下午 04:35:38
 * 功能：    接口：微型工程评标方式
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-11 下午 04:35:38  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.dao.YxWxgcPbfsDao;
import com.ccthanking.business.wxgc.service.YxWxgcPbfsService;
import com.ccthanking.business.wxgc.vo.YxWxgcPbfsVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxWxgcPbfsService.java </p>
 * <p> 功能：微型工程评标方式 </p>
 *
 * <p><a href="YxWxgcPbfsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-03-11
 * 
 */

@Service
public class YxWxgcPbfsServiceImpl extends Base1ServiceImpl<YxWxgcPbfsVO, String> implements YxWxgcPbfsService {

	private static Logger logger = LoggerFactory.getLogger(YxWxgcPbfsServiceImpl.class);
	
	// 业务类型
    
    private YxWxgcPbfsDao yxWxgcPbfsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxWxgcPbfsDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("微型工程评标方式{}", e.getMessage());
			SystemException.handleMessageException("微型工程评标方式查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxWxgcPbfsVO vo = new YxWxgcPbfsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键

            // 插入
			yxWxgcPbfsDao.save(vo);
            resultVO = vo.getRowJson();

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("微型工程评标方式{}", e.getMessage());
            SystemException.handleMessageException("微型工程评标方式新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxWxgcPbfsVO vo = new YxWxgcPbfsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键

            // 修改
            yxWxgcPbfsDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("微型工程评标方式{}", e.getMessage());
            SystemException.handleMessageException("微型工程评标方式修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxWxgcPbfsVO vo = new YxWxgcPbfsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxWxgcPbfsDao.delete(YxWxgcPbfsVO.class, vo.getPbfs_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("微型工程评标方式{}", e.getMessage());
            SystemException.handleMessageException("微型工程评标方式删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxWxgcPbfsDaoImpl")
	public void setYxWxgcPbfsDao(YxWxgcPbfsDao yxWxgcPbfsDao) {
		this.yxWxgcPbfsDao = yxWxgcPbfsDao;
	}
    
}
