/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.AtRecAuthChannelService.java
 * 创建日期： 2014-11-27 上午 10:19:22
 * 功能：    接口：栏目受众权限
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-27 上午 10:19:22  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.weixin.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.weixin.dao.AtRecAuthChannelDao;
import com.ccthanking.business.weixin.service.AtRecAuthChannelService;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.weixin.vo.AtRecAuthChannelVO;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> AtRecAuthChannelService.java </p>
 * <p> 功能：栏目受众权限 </p>
 *
 * <p><a href="AtRecAuthChannelService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-27
 * 
 */

@Service
public class AtRecAuthChannelServiceImpl extends Base1ServiceImpl<AtRecAuthChannelVO, String> implements AtRecAuthChannelService {

	private static Logger logger = LoggerFactory.getLogger(AtRecAuthChannelServiceImpl.class);
	
	 
    private AtRecAuthChannelDao atRecAuthChannelDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = atRecAuthChannelDao.queryCondition(json, null, null);

          
        }catch (DaoException e) {
        	logger.error("栏目受众权限{}", e.getMessage());
		
			SystemException.handleMessageException("栏目受众权限查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        AtRecAuthChannelVO vo = new AtRecAuthChannelVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
           

            // 插入
			atRecAuthChannelDao.save(vo);
            resultVO = vo.getRowJson();

      
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("栏目受众权限{}", e.getMessage());
         
            SystemException.handleMessageException("栏目受众权限新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AtRecAuthChannelVO vo = new AtRecAuthChannelVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
         
            // 修改
            atRecAuthChannelDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("栏目受众权限{}", e.getMessage());         
            SystemException.handleMessageException("栏目受众权限修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json,String rec_auth_channel_uid) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
//		AtRecAuthChannelVO vo = new AtRecAuthChannelVO();
		try {
//			JSONArray list = vo.doInitJson(json);
//			JSONObject jsonObj = (JSONObject) list.get(0);
//
//			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			atRecAuthChannelDao.delete(AtRecAuthChannelVO.class, rec_auth_channel_uid);

//			resultVo = vo.getRowJson();		
		} catch (DaoException e) {
            logger.error("栏目受众权限{}", e.getMessage());
           
            SystemException.handleMessageException("栏目受众权限删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("atRecAuthChannelDaoImpl")
	public void setAtRecAuthChannelDao(AtRecAuthChannelDao atRecAuthChannelDao) {
		this.atRecAuthChannelDao = atRecAuthChannelDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) atRecAuthChannelDao);
	}

	public void awardToUsers(String channelid, String[] userids, User user)
			throws Exception {
		atRecAuthChannelDao.awardToUsers(channelid, userids, user);
		
	}
    
}
