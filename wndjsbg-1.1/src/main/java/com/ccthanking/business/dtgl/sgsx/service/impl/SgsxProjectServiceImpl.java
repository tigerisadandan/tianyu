package com.ccthanking.business.dtgl.sgsx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.sgsx.dao.SgsxProjectDao;
import com.ccthanking.business.dtgl.sgsx.service.SgsxProjectService;
import com.ccthanking.business.spxx.vo.BuSpYwxxVO;

import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

/**
 * <p> SgsxProjectServiceImpl.java </p>
 * <p> 功能：审批业务信息 </p>
 *
 * <p><a href="SgsxProjectServiceImpl.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */

@Service
public class SgsxProjectServiceImpl extends Base1ServiceImpl<BuSpYwxxVO,String> implements SgsxProjectService {

	private static Logger logger = LoggerFactory.getLogger(SgsxProjectServiceImpl.class);
	
	// 业务类型
   // private String ywlx = YwlxManager.BU_SP_YWXX;
    
    private SgsxProjectDao sgsxProjectDao;
    
    @Autowired
	public void setsgsxProjectDao(SgsxProjectDao sgsxProjectDao) {
		this.sgsxProjectDao = sgsxProjectDao;
		//super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)sgsxProjectDao);
	}
    

    // @Override
    public String queryCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = sgsxProjectDao.queryCondition(json,null,null);

        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }

    public String queryYwStatus(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = sgsxProjectDao.queryYwStatus(json,null,null);

//            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
    
    
    public String querySFszgc(String gcid) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = sgsxProjectDao.querySFszgc(gcid);
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			SystemException.handleMessageException("判断是否是市政信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
    //查询 业务流转id
    public String querySgYwlzId(String gcId,String spywId) throws Exception {
    	 User user = ActionContext.getCurrentUserInThread();
         
         String domresult = "";
         try {
 			domresult = sgsxProjectDao.querySgYwlzId(gcId,spywId);
         }catch (DaoException e) {
         	logger.error("审批业务信息{}", e.getMessage());
 			SystemException.handleMessageException("业务流转id查询失败,请联系相关人员处理");
         } finally {
         }
         return domresult;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public String queryBz(String json) throws Exception {
		//  select SFSZGC  from projects_gongcheng WHERE GONGCHENG_UID = 69;
		return null;
	}


	public String queryYWLX(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public String insert(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public String update(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public String delete(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public String getAllSpyw() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	


	
}
  /*  public String queryBz(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgsxProjectDao.queryBz(json, null, null);

//            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
		//	LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息查询失败", user, "", "");
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryYWLX(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgsxProjectDao.queryYWLX(json);

//            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<审批业务信息>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("审批业务信息{}", e.getMessage());
			//LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息查询失败", user, "", "");
			SystemException.handleMessageException("审批业务信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpYwxxVO vo = new BuSpYwxxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           vo.setEvent_uid(new RandomGUID().toString());
           String bz = vo.getDescribe();
           bz = bz.replace("&lt;", "<").replace("&gt;", ">");
           vo.setDescribe(bz);
           
           
           vo.setCreated_date(Pub.getCurrentDate());
           vo.setCreated_name(user.getName());
           vo.setCreated_uid(user.getAccount());
           
            // 插入
           sgsxProjectDao.save(vo);
            resultVO = vo.getRowJson();

            //LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "审批业务信息新增成功", user, "", "");

            
        } catch (DaoException e) {
            logger.error("审批业务信息{}", e.getMessage());
          //  LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息新增失败", user, "", "");
            SystemException.handleMessageException("审批业务信息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpYwxxVO vo = new BuSpYwxxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            
             * vo.setId(new RandomGUID().toString()); // 主键
              vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); 

            // 修改
            String bz = vo.getDescribe();
            bz = bz.replace("&lt;", "<").replace("&gt;", ">");
            vo.setDescribe(bz);
            
            vo.setUpdate_date(Pub.getCurrentDate());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
            
            sgsxProjectDao.update(vo);
            resultVO = vo.getRowJson();

          //  LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务信息修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("审批业务信息{}", e.getMessage());
           // LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息修改失败", user, "", "");
            SystemException.handleMessageException("审批业务信息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpYwxxVO vo = new BuSpYwxxVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			sgsxProjectDao.delete(BuSpYwxxVO.class, vo.getSpyw_uid());

			resultVo = vo.getRowJson();

		//	LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "审批业务信息删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("审批业务信息{}", e.getMessage());
          //  LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "审批业务信息删除失败", user, "", "");
            SystemException.handleMessageException("审批业务信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	public String getAllSpyw() throws Exception {
		return sgsxProjectDao.getAllSpyw();
	}
    
}
*/