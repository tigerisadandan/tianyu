/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.OrganizeService.java
 * 创建日期： 2015-05-25 下午 09:13:36
 * 功能：    接口：组织结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-25 下午 09:13:36  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.ccthanking.business.dtgl.nbgl.vo.OrganizeVO;
import com.ccthanking.business.dtgl.nbgl.dao.OrganizeDao;
import com.ccthanking.business.dtgl.nbgl.service.OrganizeService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> OrganizeService.java </p>
 * <p> 功能：组织结构 </p>
 *
 * <p><a href="OrganizeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-25
 * 
 */

@Service
public class OrganizeServiceImpl extends Base1ServiceImpl<OrganizeVO, String> implements OrganizeService {

	private static Logger logger = LoggerFactory.getLogger(OrganizeServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.ORGANIZE;
    
    private OrganizeDao organizeDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = organizeDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getDepartment()
                    + " " + user.getName() + "查询<组织结构>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("组织结构{}", e.getMessage());

			SystemException.handleMessageException("组织结构查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryOrganize() throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = organizeDao.queryOrganize();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getDepartment()
                    + " " + user.getName() + "查询<组织结构>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("组织结构{}", e.getMessage());

			SystemException.handleMessageException("组织结构查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        OrganizeVO vo = new OrganizeVO();
        try {
        	JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setSend_y("Y");//默认为启用
            vo.setCreated_date(new Date());//创建时间为当前时间
            vo.setCreated_by(user.getAccount());//当前登陆人
            // 新增
            organizeDao.save(vo);
            resultVO = vo.getRowJson();
        	
        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
            SystemException.handleMessageException("组织结构新增失败,请联系相关人员处理");
        } finally {
        		
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        OrganizeVO vo = new OrganizeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

         
            // 修改
            organizeDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
           
            SystemException.handleMessageException("组织结构修改失败,请联系相关人员处理");
        } finally {
        		
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		OrganizeVO vo = new OrganizeVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			organizeDao.delete(OrganizeVO.class, vo.getOrganize_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());

            SystemException.handleMessageException("组织结构删除失败,请联系相关人员处理");
		} finally {
			
		}
		return resultVo;

	}
    public String queryRestDeptUser() throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
    	String domresult = "";
    	try {
    		domresult = this.organizeDao.queryRestDeptUser();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return domresult;
	}
    
    public String queryDeptUserByCurrentUser() {
    	User user = ActionContext.getCurrentUserInThread();
    	String domresult = "";
    	try {
    		domresult = this.organizeDao.queryDeptUserByCurrentUser();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return domresult;
	}

	@Autowired
	@Qualifier("organizeDaoImpl")
	public void setOrganizeDao(OrganizeDao organizeDao) {
		this.organizeDao = organizeDao;
	}

}
