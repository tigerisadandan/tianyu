/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    tzgl.service.TongzhiService.java
 * 创建日期： 2015-05-15 下午 03:34:18
 * 功能：    接口：通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-15 下午 03:34:18  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.tzgl.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.tzgl.vo.MsgVO;
import com.ccthanking.business.tzgl.vo.TongzhiGongchengVO;
import com.ccthanking.business.tzgl.vo.TongzhiVO;
import com.ccthanking.business.tzgl.dao.TongzhiDao;
import com.ccthanking.business.tzgl.service.TongzhiService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> TongzhiService.java </p>
 * <p> 功能：通知管理 </p>
 *
 * <p><a href="TongzhiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-15
 * 
 */

@Service
public class TongzhiServiceImpl extends Base1ServiceImpl<TongzhiVO, String> implements TongzhiService {

	private static Logger logger = LoggerFactory.getLogger(TongzhiServiceImpl.class);
	
	// 业务类型
   //private String ywlx = YwlxManager.TONGZHI;
    
    private TongzhiDao tongzhiDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = tongzhiDao.queryCondition(json, null, null);
			
      
        }catch (DaoException e) {
        	logger.error("通知管理{}", e.getMessage());
			
			SystemException.handleMessageException("通知管理查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(Map<String, String> tongzhiMap,List<Map<String,String>> gongchengList) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        TongzhiVO tzVO = new TongzhiVO();
        
        try {
        	if(!"".equals(tongzhiMap.get("AJ_Y")) && tongzhiMap.get("AJ_Y")!=null){
        		tzVO.setAj_y("Y");
        	}
        	tzVO.setTz_level(tongzhiMap.get("tzjibie"));
        	tzVO.setCreate_date(new Date());
        	tzVO.setCreate_by(user.getUserSN());
        	tzVO.setTongzhi_title(tongzhiMap.get("tongzhiTitle"));
        	tzVO.setTongzhi_content(tongzhiMap.get("tongzhiContent"));
            // 插入
			tongzhiDao.save(tzVO);  
            resultVO = tzVO.getRowJson();  
            
            for(Map<String, String> map:gongchengList){
            	TongzhiGongchengVO tzgcVO = new TongzhiGongchengVO();
            	tzgcVO.setTongzhi_uid(tzVO.getTongzhi_uid());
            	tzgcVO.setGongcheng_uid(map.get("gcuid"));
            	BaseDAO.insert(DBUtil.getConnection(), tzgcVO);
            	
            	MsgVO msgVOzj = new MsgVO();
            	msgVOzj.setPhone_number(map.get("zjphone"));
            	msgVOzj.setContent(tongzhiMap.get("tongzhiContent"));
            	msgVOzj.setSend_y("N");
            	msgVOzj.setSend_to(map.get("zongjian"));
            	msgVOzj.setMsg_type("TZ");
            	msgVOzj.setChuli_uid(tzVO.getTongzhi_uid());
            	msgVOzj.setSend_type("2");
            	msgVOzj.setGongcheng_uid(map.get("gcuid"));
            	msgVOzj.setCreate_date(new Date());
            	BaseDAO.insert(DBUtil.getConnection(), msgVOzj); //保存总监短信信息
            	
            	MsgVO msgVO = new MsgVO();
            	msgVO.setPhone_number(map.get("xmljphone"));
            	msgVO.setContent(tongzhiMap.get("tongzhiContent"));
            	msgVO.setSend_y("N");
            	msgVO.setSend_to(map.get("xmjl"));
            	msgVO.setMsg_type("TZ");
            	msgVO.setChuli_uid(tzVO.getTongzhi_uid());
            	msgVO.setSend_type("2");
            	msgVO.setGongcheng_uid(map.get("gcuid"));
            	msgVO.setCreate_date(new Date());
            	BaseDAO.insert(DBUtil.getConnection(), msgVO);//保存项目经理短信信息
            	
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("通知管理{}", e.getMessage());
            SystemException.handleMessageException("通知管理新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        TongzhiVO vo = new TongzhiVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
            //vo.setYwlx(ywlx);

            // 修改
            tongzhiDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("通知管理{}", e.getMessage());
            SystemException.handleMessageException("通知管理修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		TongzhiVO vo = new TongzhiVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			//tongzhiDao.delete(TongzhiVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("通知管理{}", e.getMessage());
            SystemException.handleMessageException("通知管理删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("tongzhiDaoImpl")
	public void setTongzhiDao(TongzhiDao tongzhiDao) {
		this.tongzhiDao = tongzhiDao;
	}
    
}
