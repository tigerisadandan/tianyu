/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.AtAdmAuthChannelService.java
 * 创建日期： 2014-11-27 上午 10:22:47
 * 功能：    接口：栏目发布权限
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-27 上午 10:22:47  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.weixin.dao.AtAdmAuthChannelDao;
import com.ccthanking.business.weixin.service.AtAdmAuthChannelService;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.weixin.vo.AtAdmAuthChannelVO;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> AtAdmAuthChannelService.java </p>
 * <p> 功能：栏目发布权限 </p>
 *
 * <p><a href="AtAdmAuthChannelService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-27
 * 
 */

@Service
public class AtAdmAuthChannelServiceImpl extends Base1ServiceImpl<AtAdmAuthChannelVO, String> implements AtAdmAuthChannelService {

	private static Logger logger = LoggerFactory.getLogger(AtAdmAuthChannelServiceImpl.class);
	
	
    private AtAdmAuthChannelDao atAdmAuthChannelDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = atAdmAuthChannelDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("栏目发布权限{}", e.getMessage());
			
			SystemException.handleMessageException("栏目发布权限查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        AtAdmAuthChannelVO vo = new AtAdmAuthChannelVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
           

            // 插入
			atAdmAuthChannelDao.save(vo);
            resultVO = vo.getRowJson();

         //String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("栏目发布权限{}", e.getMessage());
          
            SystemException.handleMessageException("栏目发布权限新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AtAdmAuthChannelVO vo = new AtAdmAuthChannelVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
         

            // 修改
            atAdmAuthChannelDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("栏目发布权限{}", e.getMessage());
           
            SystemException.handleMessageException("栏目发布权限修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json,String adm_auth_channel_uid) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
//		AtAdmAuthChannelVO vo = new AtAdmAuthChannelVO();
		try {
//			JSONArray list = vo.doInitJson(json);
//			JSONObject jsonObj = (JSONObject) list.get(0);
//			vo.setValueFromJson(jsonObj);
			//删除   根据据主键
			atAdmAuthChannelDao.delete(AtAdmAuthChannelVO.class, adm_auth_channel_uid);
			
//			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("栏目发布权限{}", e.getMessage());
           
            SystemException.handleMessageException("栏目发布权限删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("atAdmAuthChannelDaoImpl")
	public void setAtAdmAuthChannelDao(AtAdmAuthChannelDao atAdmAuthChannelDao) {
		this.atAdmAuthChannelDao = atAdmAuthChannelDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) atAdmAuthChannelDao);
	}

	public void awardToUsers(String channelid, String[] userids, User user)
			throws Exception {

		atAdmAuthChannelDao.awardToUsers(channelid, userids, user);
		
	}
    
}
