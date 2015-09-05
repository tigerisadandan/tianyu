/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzCyzService.java
 * 创建日期： 2014-06-13 下午 04:43:40
 * 功能：    接口：业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:43:40  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


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

import com.ccthanking.business.spxx.vo.BuSpBzCyzVO;
import com.ccthanking.business.spxx.dao.BuSpBzCyzDao;
import com.ccthanking.business.spxx.service.BuSpBzCyzService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpBzCyzService.java </p>
 * <p> 功能：业务材料 </p>
 *
 * <p><a href="BuSpBzCyzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Service
public class BuSpBzCyzServiceImpl extends Base1ServiceImpl<BuSpBzCyzVO, String> implements BuSpBzCyzService {

	private static Logger logger = LoggerFactory.getLogger(BuSpBzCyzServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SP_BZ_CYZ;
    
    private BuSpBzCyzDao buSpBzCyzDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpBzCyzDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<业务材料>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("业务材料{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "业务材料查询失败", user, "", "");
			SystemException.handleMessageException("业务材料查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpBzCyzVO vo = new BuSpBzCyzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            

            // 插入
			buSpBzCyzDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx,  Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "业务材料新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("业务材料{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx,  Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "业务材料新增失败", user, "", "");
            SystemException.handleMessageException("业务材料新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpBzCyzVO vo = new BuSpBzCyzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));


            // 修改
            buSpBzCyzDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx,  Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "业务材料修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("业务材料{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx,  Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "业务材料修改失败", user, "", "");
            SystemException.handleMessageException("业务材料修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpBzCyzVO vo = new BuSpBzCyzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			buSpBzCyzDao.delete(BuSpBzCyzVO.class, vo.getBzcyz_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "业务材料删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("业务材料{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "业务材料删除失败", user, "", "");
            SystemException.handleMessageException("业务材料删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpBzCyzDaoImpl")
	public void setBuSpBzCyzDao(BuSpBzCyzDao buSpBzCyzDao) {
		this.buSpBzCyzDao = buSpBzCyzDao;
	}

	public String getUsers(String bzUid,String uname) {
		return buSpBzCyzDao.getUsers(bzUid,uname);
	}

	public String insertUsers(String json) {
		return buSpBzCyzDao.insertUsers(json);
	}

	public String queryBzCyz(String bzUid) {
		return buSpBzCyzDao.queryBzCyz(bzUid);
	}
    
}
