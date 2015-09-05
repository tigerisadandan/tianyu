/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxUserService.java
 * 创建日期： 2014-12-23 上午 09:50:28
 * 功能：    接口：微型工程用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 上午 09:50:28  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.wxgc.dao.YxUserDao;
import com.ccthanking.business.wxgc.service.YxUserService;
import com.ccthanking.business.wxgc.vo.YxUserVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxUserService.java </p>
 * <p> 功能：微型工程管理系统用户表 </p>
 *
 * <p><a href="YxUserService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Service
public class YxUserServiceImpl extends Base1ServiceImpl<YxUserVO, String> implements YxUserService {

	private static Logger logger = LoggerFactory.getLogger(YxUserServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.yx_user;
    
    private YxUserDao yxUserDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxUserDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("微型工程用户信息{}", e.getMessage());
			SystemException.handleMessageException("微型工程用户信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxUserVO vo = new YxUserVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);
          
            
//            vo.setMima(vo.getPwd().toUpperCase());
//		    vo.setPwd(DigestUtils.md5Hex(vo.getPwd().toUpperCase()));
            vo.setMima("123456");
 		    vo.setPwd(DigestUtils.md5Hex("123456"));
        	
            vo.setEnabled("1");
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());

            // 插入
			yxUserDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("微型工程用户信息{}", e.getMessage());
            SystemException.handleMessageException("微型工程用户信息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxUserVO vo = new YxUserVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
           
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
            String IS_CZMM=(String)obj.get("IS_CZMM");
            String USER_UID=(String)obj.get("USER_UID");
            vo.setUser_uid(USER_UID);
            
            if(IS_CZMM.equals("1")){
            	vo.setMima("123456");
     		    vo.setPwd(DigestUtils.md5Hex("123456"));
            }else{
            	 vo.setValueFromJson(obj);
            }
            // 修改
            yxUserDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("微型工程用户信息{}", e.getMessage());
            SystemException.handleMessageException("微型工程用户信息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxUserVO vo = new YxUserVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

//			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxUserDao.delete(YxUserVO.class, vo.getUser_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("微型工程用户信息{}", e.getMessage());
            SystemException.handleMessageException("微型工程用户信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxUserDaoImpl")
	public void setYxUserDao(YxUserDao yxUserDao) {
		this.yxUserDao = yxUserDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxUserDao);
	}
    
}
