/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jlbb.service.JlbbJlyService.java
 * 创建日期： 2015-01-28 上午 09:21:37
 * 功能：    接口：监理项目的人员报备信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:21:37  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb.service.impl;


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
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.jl.vo.JlbbJlyVO;
import com.ccthanking.business.jlbb.dao.JlbbJlyDao;
import com.ccthanking.business.jlbb.service.JlbbJlyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JlbbJlyService.java </p>
 * <p> 功能：监理项目的人员报备信息 </p>
 *
 * <p><a href="JlbbJlyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Service
public class JlbbJlyServiceImpl extends Base1ServiceImpl<JlbbJlyVO, String> implements JlbbJlyService {

	private static Logger logger = LoggerFactory.getLogger(JlbbJlyServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.JLBB_JLY;
    
    private JlbbJlyDao jlbbJlyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jlbbJlyDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("监理项目的人员报备信息{}", e.getMessage());
			SystemException.handleMessageException("监理项目的人员报备信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JlbbJlyVO vo = new JlbbJlyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
      

            // 插入
			jlbbJlyDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("监理项目的人员报备信息{}", e.getMessage());
            SystemException.handleMessageException("监理项目的人员报备信息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JlbbJlyVO vo = new JlbbJlyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);

            // 修改
            jlbbJlyDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("监理项目的人员报备信息{}", e.getMessage());
            SystemException.handleMessageException("监理项目的人员报备信息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JlbbJlyVO vo = new JlbbJlyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			//jlbbJlyDao.delete(JlbbJlyVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("监理项目的人员报备信息{}", e.getMessage());
            SystemException.handleMessageException("监理项目的人员报备信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jlbbJlyDaoImpl")
	public void setJlbbJlyDao(JlbbJlyDao jlbbJlyDao) {
		this.jlbbJlyDao = jlbbJlyDao;
	}

	public String checkPerson(String pid ,String gwid, String gcdj) {
		
		return this.jlbbJlyDao.checkPerson(pid,gwid,gcdj);
	}
    
}
