/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ScoreService.java
 * 创建日期： 2015-05-17 上午 09:23:51
 * 功能：    接口：分数管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-17 上午 09:23:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;


import com.ccthanking.business.dtgl.yhzg.vo.ScoreVO;
import com.ccthanking.business.yhzg.dao.ScoreDao;
import com.ccthanking.business.yhzg.service.ScoreService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ScoreService.java </p>
 * <p> 功能：分数管理 </p>
 *
 * <p><a href="ScoreService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-05-17
 * 
 */

@Service
public class ScoreServiceImpl extends Base1ServiceImpl<ScoreVO, String> implements ScoreService {

	private static Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.SCORE;
    
    private ScoreDao scoreDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = scoreDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("分数管理{}", e.getMessage());
			SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryCondition1(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = scoreDao.queryCondition1(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("分数管理{}", e.getMessage());
			SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String queryFenshu(String json,String condition) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.queryFenshu(json,condition);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
    
    // @Override
    public String queryJiafen(String json,String shenhe) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.queryJiafen(json,shenhe);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
    
    // @Override
    public String queryjfxx(String json,String JFSQ_UID) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.queryjfxx(json,JFSQ_UID);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
    
    
    // @Override
    public String queryspxx(String json,String JFSQ_UID) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.queryspxx(json,JFSQ_UID);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
    
    
    // @Override
    public String queryjfdx(String json,String JFSQ_UID) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.queryjfdx(json,JFSQ_UID);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
    
    
    // @Override
    public String queryjfsj(String json,String JFSQ_UID) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.queryjfsj(json,JFSQ_UID);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
    
    // @Override
    public String updatestatus(String agree,String JFSTATUS,String JFSQ_UID,String shyj) throws Exception {    	
    	User user = ActionContext.getCurrentUserInThread();   	
    	String domresult = "";
    	try {   		
    		domresult = scoreDao.updatestatus(agree,JFSTATUS,JFSQ_UID,shyj);    		
    	}catch (DaoException e) {
    		logger.error("分数管理{}", e.getMessage());
    		SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;   	
    }
   
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ScoreVO vo = new ScoreVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            

            // 插入
			scoreDao.save(vo);
            resultVO = vo.getRowJson();
            


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("分数管理{}", e.getMessage());
            SystemException.handleMessageException("分数管理新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ScoreVO vo = new ScoreVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            scoreDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("分数管理{}", e.getMessage());
            SystemException.handleMessageException("分数管理修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ScoreVO vo = new ScoreVO();
		try {
			/*
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);
			 */
			//删除   根据据主键
			scoreDao.delete(ScoreVO.class, json);

			resultVo = vo.getRowJson();
			

		} catch (DaoException e) {
            logger.error("分数管理{}", e.getMessage());
            SystemException.handleMessageException("分数管理删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

    public String getShtgkSum(String id,String type) {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {

			domresult = scoreDao.getShtgkSum(id, type);

           
        }catch (DaoException e) {
        	logger.error("分数管理{}", e.getMessage());
			SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}

	public String getShtgjSum(String id,String type) {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {

			domresult = scoreDao.getShtgjSum(id, type);

        }catch (DaoException e) {
        	logger.error("分数管理{}", e.getMessage());
			SystemException.handleMessageException("分数管理查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
	
	@Autowired
	@Qualifier("scoreDaoImpl")
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	public String getDshjCount(String msg) {
		return this.scoreDao.getDshjCount(msg);
	}

	public String getDshkCount(String msg) {
		return this.scoreDao.getDshkCount(msg);
	}
	

	public String queryById(String sUid) {
		// TODO Auto-generated method stub
		return this.scoreDao.queryById(sUid);
	}

	public String updateCompanySh(String msg) {
		// TODO Auto-generated method stub
		return this.scoreDao.updateCompanySh(msg);
	}
	public String updatePersonSh(String msg) {
		// TODO Auto-generated method stub
		return this.scoreDao.updatePersonSh(msg);
	}
    
}
