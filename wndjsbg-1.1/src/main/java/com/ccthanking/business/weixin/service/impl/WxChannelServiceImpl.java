package com.ccthanking.business.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.weixin.dao.WxChannelDao;
import com.ccthanking.business.weixin.dao.WxContentDao;
import com.ccthanking.business.weixin.service.WxChannelService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.weixin.vo.WxChannelVO;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

@Service
public class WxChannelServiceImpl   extends Base1ServiceImpl<WxChannelVO, String> implements WxChannelService{
	private static Logger logger = LoggerFactory.getLogger(WxChannelServiceImpl.class);
	private WxChannelDao wxChannelDao;
	private WxContentDao wxContentDao;
	
	@Autowired
	@Qualifier("wxChannelDaoImpl")
	public void setWxChannelDao(WxChannelDao wxChannelDao) {
		this.wxChannelDao = wxChannelDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) wxChannelDao);
	}
	

	@Autowired
	@Qualifier("wxContentDaoImpl")
	public void setWxContentDao(WxContentDao wxContentDao) {
		this.wxContentDao = wxContentDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) wxContentDao);
	}

	//根据eventkey查询所对应的栏目ID并组成串
	public String getChannelIdByEventKey(String eventkey) throws Exception{
		 String domresult = "";
	        try {
				domresult = wxChannelDao.getChannelIdByEventKey(eventkey);       
	        }catch (DaoException e) {
	        	logger.error("栏目ID查询{}", e.getMessage());
				SystemException.handleMessageException("栏目ID查询失败,请联系相关人员处理");
	        } finally {
	        }
		return domresult;
	}
	
	
	public String delete(String channel_uid) throws Exception {
		String resultVo = null;
//		WxChannelVO vo = new WxChannelVO();
		try {
			
			List<Map<String, String>> templist= wxContentDao.listContent(null, channel_uid, 10);
			
			if(templist!=null&&templist.size()>0){
				resultVo = "已被使用，不能删除";
			}else{			
				//删除   根据据主键
				wxChannelDao.delete(WxChannelVO.class, channel_uid);
				wxChannelDao.deleteAllUsers(channel_uid);//删除关联关系
				
				resultVo = "删除成功";
			}		

		} catch (DaoException e) {
			resultVo = "删除失败";
            logger.error("栏目删除{}", e.getMessage());
            SystemException.handleMessageException("栏目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;
	}

	public String insert(String json) throws Exception {
		String resultVO = null;
		WxChannelVO vo = new WxChannelVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            // 插入
            wxChannelDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("栏目新增{}", e.getMessage());
            SystemException.handleMessageException("栏目新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

	
	public String queryCondition(String json) throws Exception {
		 String domresult = "";
	        try {
				domresult = wxChannelDao.queryCondition(json, null);       
	        }catch (DaoException e) {
	        	logger.error("栏目查询{}", e.getMessage());
				SystemException.handleMessageException("栏目查询失败,请联系相关人员处理");
	        } finally {
	        }
		return domresult;
	}
	
	/**
	 * 查询所有权限范围内的栏目信息
	 * 
	 * */
	public String queryRightCondition(String json) throws Exception {
		 String domresult = "";
	        try {
				domresult = wxChannelDao.queryRightCondition(json, null);       
	        }catch (DaoException e) {
	        	logger.error("栏目查询{}", e.getMessage());
				SystemException.handleMessageException("栏目查询失败,请联系相关人员处理");
	        } finally {
	        }
		return domresult;
	}

	public String update(String json) throws Exception {
		 User user = ActionContext.getCurrentUserInThread();
	        
	        String resultVO = null;
	        WxChannelVO vo = new WxChannelVO();

	        try {
	            JSONArray list = vo.doInitJson(json);
	            JSONObject object=  (JSONObject) list.get(0);
	            vo.setValueFromJson(object);	            
	            // 设置主键
//	            vo.setId(new RandomGUID().toString()); // 主键
	            wxChannelDao.update(vo);
	            resultVO = vo.getRowJson();
	            
	        } catch (DaoException e) {
	            logger.error("栏目修改{}", e.getMessage());
	            SystemException.handleMessageException("栏目修改失败,请联系相关人员处理");
	        } finally {
	        }
	        return resultVO;
	}

	

	public String loadAllAdmUsers(Map maptemp) {
		
		return wxChannelDao.loadAllAdmUsers(maptemp);
	}
	
	public String loadAllRecUsers(Map maptemp) {
		
		return wxChannelDao.loadAllRecUsers(maptemp);
	}
	
}
