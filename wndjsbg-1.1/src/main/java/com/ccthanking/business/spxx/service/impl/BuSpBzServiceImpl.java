/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzService.java
 * 创建日期： 2014-06-13 下午 04:41:17
 * 功能：    接口：业务信息步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:41:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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

import com.ccthanking.business.spxx.vo.BuSpBzVO;
import com.ccthanking.business.spxx.dao.BuSpBzCyzDao;
import com.ccthanking.business.spxx.dao.BuSpBzDao;
import com.ccthanking.business.spxx.service.BuSpBzService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpBzService.java </p>
 * <p> 功能：业务信息步骤 </p>
 *
 * <p><a href="BuSpBzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Service
public class BuSpBzServiceImpl extends Base1ServiceImpl<BuSpBzVO, String> implements BuSpBzService {

	private static Logger logger = LoggerFactory.getLogger(BuSpBzServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SP_BZ;
    
    private BuSpBzDao buSpBzDao;
    
    private BuSpBzCyzDao buSpBzCyzDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpBzDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<业务信息步骤>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("业务信息步骤{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "业务信息步骤查询失败", user, "", "");
			SystemException.handleMessageException("业务信息步骤查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpBzVO vo = new BuSpBzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            vo.setBzxh(buSpBzDao.getPxh(vo.getSpyw_uid()));
            
            // 插入
			buSpBzDao.save(vo);
			
			 JSONArray arr = (JSONArray) obj.get("USERS_UID");
            if (arr!=null) {
            	String uids = "";
				for (int i = 0; i < arr.size(); i++) {
					uids += arr.getString(i)+",";
				}
				
				if (StringUtils.isNotBlank(uids)) {
					String jsonStr = "{\"users\":\""+uids.substring(0,uids.length()-1)+"\",\"bz_uid\":\""+vo.getSpbz_uid()+"\"}";
					buSpBzCyzDao.insertUsers(jsonStr);
				}
			}
			
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getSpbz_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "业务信息步骤新增成功", user, "", "");
            
        } catch (DaoException e) {
            logger.error("业务信息步骤{}", e.getMessage());
            LogManager.writeUserLog(vo.getSpbz_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "业务信息步骤新增失败", user, "", "");
            SystemException.handleMessageException("业务信息步骤新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpBzVO vo = new BuSpBzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            buSpBzDao.update(vo);
            String id=obj.getString("USERS_UID");
            if(!"".equals(id)){
            JSONArray arr = (JSONArray) obj.get("USERS_UID");
            //页面给了一个隐藏的fieldname为USERS_UID的空值文本框，保证传进来的是数组
            	String uids = "";
            	if (arr!=null) {
					for (int i = 0; i < arr.size(); i++) {
						uids += arr.getString(i)+",";
					}
    			}
				
//				if (StringUtils.isNotBlank(uids)) {
					String jsonStr = "{\"users\":\""+uids+"\",\"bz_uid\":\""+vo.getSpbz_uid()+"\"}";
					buSpBzCyzDao.insertUsers(jsonStr);
//				}
            }else{
            	String jsonStr = "{\"users\":\""+""+"\",\"bz_uid\":\""+vo.getSpbz_uid()+"\"}";
				buSpBzCyzDao.insertUsers(jsonStr);
            }
            
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getSpbz_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "业务信息步骤修改成功", user, "", "");

        } catch (DaoException e) {
        	e.printStackTrace();
            logger.error("业务信息步骤{}", e.getMessage());
            LogManager.writeUserLog(vo.getSpbz_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "业务信息步骤修改失败", user, "", "");
            SystemException.handleMessageException("业务信息步骤修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpBzVO vo = new BuSpBzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			buSpBzDao.delete(BuSpBzVO.class, vo.getSpbz_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "业务信息步骤删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("业务信息步骤{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "业务信息步骤删除失败", user, "", "");
            SystemException.handleMessageException("业务信息步骤删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpBzDaoImpl")
	public void setBuSpBzDao(BuSpBzDao buSpBzDao) {
		this.buSpBzDao = buSpBzDao;
	}
	
	@Autowired
	@Qualifier("buSpBzCyzDaoImpl")
	public void setBuSpBzDao(BuSpBzCyzDao buSpBzCyzDao) {
		this.buSpBzCyzDao = buSpBzCyzDao;
	}

	/**
	 * 查询审批步骤和审批参与者信息,按审批步骤序号降序排列
	 * spywid  审批业务ID
	 * bzlx    步骤类型
	 * add by long 20140619
	 * */
	public List<?> getSpBzList(String spywid, String bzlx) {
		
		return buSpBzDao.getSpBzList(spywid, bzlx);
	}
    
}
