/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.jhgl.service.WorkInformServiceImpl.java
 * 创建日期： 2015-07-07下午 12:00:14
 * 功能：    实现类
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-07-07 下午 12:00:14  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.nbgl.inform01.service.impl;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccthanking.business.nbgl.inform01.dao.WorkInformDao;
import com.ccthanking.business.nbgl.inform01.service.WorkInformService;
import com.ccthanking.business.nbgl.jhgl.vo.NeibuTongzhiVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

/**
 * <p> WorkInformServiceImpl.java </p>
 * <p> 功能:内部通知管理 </p>
 *
 * <p><a href="WorkInformServiceImpl.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:lhtarena@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-07-07
 * 
 */
//计划管理 业务层实现类 
@Service
public class WorkInformServiceImpl extends Base1ServiceImpl<NeibuTongzhiVO, String> implements WorkInformService {

	private static Logger logger = LoggerFactory.getLogger(WorkInformServiceImpl.class);
    private WorkInformDao workInformDao;

    @Autowired
	public void setworkInformDao(WorkInformDao workInformDao) {
		this.workInformDao = workInformDao;
	}
    
    // @Override
    public String queryCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = workInformDao.queryCondition(json);
        }catch (DaoException e) {
        	logger.error("内部通知管理{}", e.getMessage());
			SystemException.handleMessageException("有效通知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    

	public String queryOutOfDate(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = workInformDao.queryOutOfDate(json);
        }catch (DaoException e) {
        	logger.error("内部通知管理{}", e.getMessage());
			SystemException.handleMessageException("失效通知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

	}

	public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();		
        String resultVO = null;
        NeibuTongzhiVO vo = new NeibuTongzhiVO();
        try { 
            JSONArray list = vo.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);
            vo.setValueFromJson(jsonObj);
            if(vo.getShixiao_date()!=null && System.currentTimeMillis()<vo.getShixiao_date().getTime()){
            	 vo.setStatus("1");//生效
            }else{
            	 vo.setStatus("2");//失效
            }
            //设置参数
           //SimpleDateFormat("YY/MM/dd HH:mm:ss").format(new Date())
            vo.setCreate_date(new Date());
			this.workInformDao.save(vo);	
        } catch (DaoException e) {
            logger.error("通知新增{}", e.getMessage());
            SystemException.handleMessageException("通知新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

	
    //页面 查看通知 初始化
	public String queryById(String selectId) {
		User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = workInformDao.queryById(selectId);
        }catch (DaoException e) {
        	logger.error("内部通知管理{}", e.getMessage());
			SystemException.handleMessageException("查看通知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

	}

	public boolean delteById(String selectId) {
		User user = ActionContext.getCurrentUserInThread();
		boolean flag = false;
		try {
			//删除   根据据主键
			flag =this.workInformDao.deleteById(selectId);
		} catch (DaoException e) {
            logger.error("信访信息{}", e.getMessage());
            SystemException.handleMessageException("信息删除失败,请联系相关人员处理");
		} finally {
			
		}
		return flag;
	}
    //更新
	public String update(String msg) throws Exception {
		User user = ActionContext.getCurrentUserInThread();		
        String resultVO = null;
        NeibuTongzhiVO vo = new NeibuTongzhiVO();
        try { 
            JSONArray list = vo.doInitJson(msg);
            JSONObject jsonObj = (JSONObject) list.get(0);
            vo.setValueFromJson(jsonObj);
            if(System.currentTimeMillis()<vo.getShixiao_date().getTime()){
            	 vo.setStatus("1");//生效
            }else{
            	 vo.setStatus("2");//失效
            }
            //设置参数
           //SimpleDateFormat("YY/MM/dd HH:mm:ss").format(new Date())
            vo.setCreate_date(new Date());
            vo.setNeibu_tongzhi_uid(vo.getNeibu_tongzhi_uid());
            
			this.workInformDao.update(vo);	
        } catch (DaoException e) {
            logger.error("通知更新{}", e.getMessage());
            SystemException.handleMessageException("通知更新失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}
		
	}

