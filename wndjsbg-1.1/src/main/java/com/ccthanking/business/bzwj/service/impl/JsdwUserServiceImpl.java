/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.JsdwUserService.java
 * 创建日期： 2015-04-27 上午 11:38:59
 * 功能：    接口：建设单位用户
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-27 上午 11:38:59  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service.impl;


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

import com.ccthanking.business.bzwj.dao.JsdwUserDao;
import com.ccthanking.business.bzwj.service.JsdwUserService;
import com.ccthanking.business.common.vo.JsdwUserVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JsdwUserService.java </p>
 * <p> 功能：建设单位用户 </p>
 *
 * <p><a href="JsdwUserService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-27
 * 
 */

@Service
public class JsdwUserServiceImpl extends Base1ServiceImpl<JsdwUserVO, String> implements JsdwUserService {

	private static Logger logger = LoggerFactory.getLogger(JsdwUserServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JSDW_USER;
    
    private JsdwUserDao jsdwUserDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jsdwUserDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("建设单位用户{}", e.getMessage());
			SystemException.handleMessageException("建设单位用户查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JsdwUserVO vo = new JsdwUserVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
          
            // 插入
			jsdwUserDao.save(vo);
            resultVO = vo.getRowJson();

          
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("建设单位用户{}", e.getMessage());
            SystemException.handleMessageException("建设单位用户新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JsdwUserVO vo = new JsdwUserVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          
            // 修改
            jsdwUserDao.update(vo);
            resultVO = vo.getRowJson();

          
        } catch (DaoException e) {
            logger.error("建设单位用户{}", e.getMessage());
           SystemException.handleMessageException("建设单位用户修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JsdwUserVO vo = new JsdwUserVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			jsdwUserDao.delete(JsdwUserVO.class, vo.getJsdw_user_uid());

			resultVo = vo.getRowJson();

			
		} catch (DaoException e) {
            logger.error("建设单位用户{}", e.getMessage());
            SystemException.handleMessageException("建设单位用户删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    public String deleteById(String id) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JsdwUserVO vo = new JsdwUserVO();
		try {
			
		
			//删除   根据据主键
			jsdwUserDao.delete(JsdwUserVO.class, id);

			resultVo = vo.getRowJson();

			
		} catch (DaoException e) {
            logger.error("建设单位用户{}", e.getMessage());
            SystemException.handleMessageException("建设单位用户删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jsdwUserDaoImpl")
	public void setJsdwUserDao(JsdwUserDao jsdwUserDao) {
		this.jsdwUserDao = jsdwUserDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) jsdwUserDao);
	}
    
}
