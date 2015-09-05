/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCbfaspFbfaService.java
 * 创建日期： 2014-05-28 下午 04:30:30
 * 功能：    接口：发包初步方案
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 下午 04:30:30  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.List;

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

import com.ccthanking.business.spyw.vo.BuSpywCbfaspFbfaVO;
import com.ccthanking.business.sp.dao.BuSpywCbfaspFbfaDao;
import com.ccthanking.business.sp.service.BuSpywCbfaspFbfaService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywCbfaspFbfaService.java </p>
 * <p> 功能：发包初步方案 </p>
 *
 * <p><a href="BuSpywCbfaspFbfaService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

@Service
public class BuSpywCbfaspFbfaServiceImpl extends Base1ServiceImpl<BuSpywCbfaspFbfaVO, String> implements BuSpywCbfaspFbfaService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspFbfaServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_CBFASP_FBFA;
    
    private BuSpywCbfaspFbfaDao buSpywCbfaspFbfaDao;

    // @Override
    public List<?> find(String id) throws Exception {
		
		List<?> list=buSpywCbfaspFbfaDao.find(id);
        
        return list;

    }
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywCbfaspFbfaDao.queryCondition(json, null, null);

            //LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS,  user.getName() + "查询<发包初步方案>", user, "", "");
  
        }catch (DaoException e) {
        	logger.error("发包初步方案{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "发包初步方案查询失败", user, "", "");
			SystemException.handleMessageException("发包初步方案查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywCbfaspFbfaVO vo = new BuSpywCbfaspFbfaVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            

		    vo.setEvent_uid(new RandomGUID().toString());

            // 插入
			buSpywCbfaspFbfaDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "发包初步方案新增成功", user, "", "");

            
        } catch (DaoException e) {
            logger.error("发包初步方案{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "发包初步方案新增失败", user, "", "");
            SystemException.handleMessageException("发包初步方案新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywCbfaspFbfaVO vo = new BuSpywCbfaspFbfaVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          

            // 修改
            buSpywCbfaspFbfaDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "发包初步方案修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("发包初步方案{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "发包初步方案修改失败", user, "", "");
            SystemException.handleMessageException("发包初步方案修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywCbfaspFbfaVO vo = new BuSpywCbfaspFbfaVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			buSpywCbfaspFbfaDao.delete(BuSpywCbfaspFbfaVO.class, vo.getFbfa_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "发包初步方案删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("发包初步方案{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "发包初步方案删除失败", user, "", "");
            SystemException.handleMessageException("发包初步方案删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywCbfaspFbfaDaoImpl")
	public void setBuSpywCbfaspFbfaDao(BuSpywCbfaspFbfaDao buSpywCbfaspFbfaDao) {
		this.buSpywCbfaspFbfaDao = buSpywCbfaspFbfaDao;
	}
	
	public String queryFbfs(String cbfUid) throws Exception {
		// TODO Auto-generated method stub
		return buSpywCbfaspFbfaDao.queryFbfs(cbfUid);
	}
    
}
