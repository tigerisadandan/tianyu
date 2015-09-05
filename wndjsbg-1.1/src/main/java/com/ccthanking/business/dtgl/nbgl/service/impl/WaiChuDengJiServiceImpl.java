/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.WaiChuDengJiService.java
 * 创建日期： 2015-05-21 下午 03:32:32
 * 功能：    接口：外出登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-21 下午 03:32:32  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl.service.impl;


import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.nbgl.vo.WaiChuDengJiVO;
import com.ccthanking.business.dtgl.nbgl.dao.WaiChuDengJiDao;
import com.ccthanking.business.dtgl.nbgl.service.WaiChuDengJiService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> WaiChuDengJiService.java </p>
 * <p> 功能：外出登记 </p>
 *
 * <p><a href="WaiChuDengJiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-21
 * 
 */

@Service
public class WaiChuDengJiServiceImpl extends Base1ServiceImpl<WaiChuDengJiVO, String> implements WaiChuDengJiService {

	private static Logger logger = LoggerFactory.getLogger(WaiChuDengJiServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.WAICHU_DENGJI;
    
    private WaiChuDengJiDao waiChuDengJiDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = waiChuDengJiDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("外出登记{}", e.getMessage());
			SystemException.handleMessageException("外出登记查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WaiChuDengJiVO vo = new WaiChuDengJiVO();
        try {
            JSONObject response = JSONObject.fromObject(json);
            String response_txt = response.getString("response");
            JSONObject data  = response.fromObject(response_txt);
            String data_txt = data.getString("data");
            JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(data_txt);
            JSONObject obj  = (JSONObject) jsonArray.get(0);
            String ids = (String) obj.get("ids");
            String names =(String)obj.get("names");
            String[] arrayIds = ids.split(",");
            String[] arrayNames = names.split(",");
            
            JSONArray list = vo.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);
            vo.setValueFromJson(jsonObj);
            vo.setWaichu_status("1");
            vo.setCreate_by(user.getUserSN());
            vo.setCreate_date(new Date());
            //先添加自己外出记录
            vo.setWaichu_renyuan_uid(user.getUserSN());
			vo.setRenyuan_name(user.getName());
			this.waiChuDengJiDao.save(vo);
			
			if(arrayIds.length>0 && !arrayIds[0].equals("") && arrayIds[0] != null){
			//循环保存其他人外出记录
            for (int i = 0; i < arrayIds.length; i++) {
            	vo.setWaichu_dengji_uid(null);
				vo.setWaichu_renyuan_uid(arrayIds[i]);
				vo.setRenyuan_name(arrayNames[i]);
				this.waiChuDengJiDao.save(vo);
			}
			}
        } catch (DaoException e) {
            logger.error("组织结构{}", e.getMessage());
            SystemException.handleMessageException("外出登记新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WaiChuDengJiVO vo = new WaiChuDengJiVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            waiChuDengJiDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("外出登记{}", e.getMessage());
            SystemException.handleMessageException("外出登记修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public boolean delete(String wcdjId) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		boolean flag = false;
		try {
			//删除   根据据主键
			 flag = waiChuDengJiDao.delete(wcdjId);

		} catch (DaoException e) {
            logger.error("外出登记{}", e.getMessage());
            SystemException.handleMessageException("外出登记删除失败,请联系相关人员处理");
		} finally {
			
		}
		return flag;
	}
   
    
    

	@Autowired
	@Qualifier("waiChuDengJiDaoImpl")
	public void setWaiChuDengJiDao(WaiChuDengJiDao waiChuDengJiDao) {
		this.waiChuDengJiDao = waiChuDengJiDao;
	}

	public String updateState(String wcdjId) throws Exception {
		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		try {
			this.waiChuDengJiDao.updateState(wcdjId);
			resultVo = "success";
		} catch (DaoException e) {
            logger.error("外出登记{}", e.getMessage());
            SystemException.handleMessageException("外出登记失败,请联系相关人员处理");
		} finally {
			
		}
		return resultVo;
	}

	public String getById(String wcdjId) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = this.waiChuDengJiDao.getById(wcdjId);
		} catch (Exception e) {
			logger.error("外出登记{}",e.getMessage());
			SystemException.handleMessageException("外出登记失败,请联系相关人员处理");
		}
		return resultVo;
	}
    
}
