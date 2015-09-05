package com.ccthanking.business.xfww.service.impl;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.gdzxt.xfww.vo.XinfangVO;
import com.ccthanking.business.xfww.dao.XinFangDao;
import com.ccthanking.business.xfww.service.XinFangService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

@Service
public class XinFangServiceImpl extends Base1ServiceImpl<XinfangVO,String> implements XinFangService {

	private static Logger logger = LoggerFactory.getLogger(XinFangServiceImpl.class);

	private XinFangDao xinFangDao;
	
	public String queryCondition(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {

			domresult = xinFangDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("信访信息{}", e.getMessage());
			SystemException.handleMessageException("信息查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;
	}

	public String insert(String json) throws Exception {
		// TODO Auto-generated method stub
		User user = ActionContext.getCurrentUserInThread();		
        String resultVO = null;
        XinfangVO vo = new XinfangVO();
        try {           
            JSONArray list = vo.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);
            vo.setValueFromJson(jsonObj);
            //设置参数
            vo.setCreated_by(user.getUserSN());
            vo.setCreated_date(new Date());
			this.xinFangDao.save(vo);	
			String target_uid = jsonObj.getString("target_uid");
			//添加更新附件
			if(vo!=null&&vo.getXinfang_uid()!=null&&!"".equals(vo.getXinfang_uid())){
			 FileUploadUtilService.updateBytragetUid(target_uid,vo.getXinfang_uid());                 
			}
        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
            SystemException.handleMessageException("信息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

	public String update(String json) throws Exception {
		
		 User user = ActionContext.getCurrentUserInThread();
		 
	     String resultVO = null;
	     XinfangVO vo = new XinfangVO();

	     try {
	          JSONArray list = vo.doInitJson(json);
	          JSONObject jsonObj = (JSONObject) list.get(0);
	          vo.setValueFromJson(jsonObj);
	          // 设置参数
	          vo.setUpdated_by(user.getUserSN());
	          vo.setUpdated_date(new Date());	          
	          xinFangDao.update(vo);
	          resultVO = vo.getRowJson();
	          
	          String targetUid = jsonObj.getString("target_uid");
				//添加更新附件
				if(vo!=null&&vo.getXinfang_uid()!=null&&!"".equals(vo.getXinfang_uid())){
				 FileUploadUtilService.updateBytragetUid(targetUid,vo.getXinfang_uid());                 
				}
	        } catch (DaoException e) {
	            logger.error("信访信息{}", e.getMessage());
	            SystemException.handleMessageException("信息修改失败,请联系相关人员处理");
	        } finally {	        	
	        }
	        return resultVO;
	}

	public boolean delete(String XINFANG_UID) throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
		boolean flag = false;
		try {
			//删除   根据据主键
			 flag = xinFangDao.delete(XINFANG_UID);
		} catch (DaoException e) {
            logger.error("信访信息{}", e.getMessage());
            SystemException.handleMessageException("信息删除失败,请联系相关人员处理");
		} finally {
			
		}
		return flag;
	}

	public String getById(String XINFANG_UID) throws Exception {
		// TODO Auto-generated method stub
		return xinFangDao.getById(XINFANG_UID);
	}
	
	
	//注入dao
	@Autowired
	@Qualifier("xinFangDaoImpl")
	public void setXinFangDao(XinFangDao xinFangDao) {
		this.xinFangDao = xinFangDao;
	}

	
}
