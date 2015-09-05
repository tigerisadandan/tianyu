/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbService.java
 * 创建日期： 2014-12-15 下午 06:22:02
 * 功能：    接口：机械设备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 下午 06:22:02  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service.impl;


import java.sql.Connection;
import java.util.Date;

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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.azqy.vo.JxsbVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbDao;
import com.ccthanking.business.dtgl.jxsb.service.JxsbService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> JxsbService.java </p>
 * <p> 功能：机械设备 </p>
 *
 * <p><a href="JxsbService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

@Service
public class JxsbServiceImpl extends Base1ServiceImpl<JxsbVO, String> implements JxsbService {

	private static Logger logger = LoggerFactory.getLogger(JxsbServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JXSB;
    
    private JxsbDao jxsbDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbDao.queryCondition(json, null, null);

          
            
        }catch (DaoException e) {
        	logger.error("机械设备{}", e.getMessage());
			SystemException.handleMessageException("机械设备查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JxsbVO vo = new JxsbVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
          
            // 插入
			jxsbDao.save(vo);
            resultVO = vo.getRowJson();

          
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("机械设备{}", e.getMessage());
             SystemException.handleMessageException("机械设备新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbVO vo = new JxsbVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
            vo.setValueFromJson(object);
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
   // 修改
            
            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");
            String SHENHE_YIJIAN=object.getString("SHENHE_YIJIAN");

            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(user.getUserSN());
            vo.setShenhe_yijian(SHENHE_YIJIAN);
            vo.setShenhe_jieguo(SHENHE_JIEGUO);
            
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	vo.setStatus("10");
            	vo.setSy_status("WSY");
            }else{
            	vo.setStatus("20");
            }
            
            jxsbDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("机械设备{}", e.getMessage());
            SystemException.handleMessageException("机械设备修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JxsbVO vo = new JxsbVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			
			//删除   根据据主键
			jxsbDao.delete(JxsbVO.class, vo.getJxsb_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("机械设备{}", e.getMessage());
            SystemException.handleMessageException("机械设备删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    public boolean disabledJXSB(String jxsb_uid,String type) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		Connection conn = null;
        boolean resultVO = false;
        
        try {
        	conn = DBUtil.getConnection();
        	String sql = "update JXSB set enabled = "+type+" where jxsb_uid = "+jxsb_uid;
        	resultVO = DBUtil.execUpdateSql(conn, sql);
        } catch (DaoException e) {
            logger.error("机械设备{}", e.getMessage());
             SystemException.handleMessageException("机械设备禁用失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;
	}

	@Autowired
	@Qualifier("jxsbDaoImpl")
	public void setJxsbDao(JxsbDao jxsbDao) {
		this.jxsbDao = jxsbDao;
	}

	
    
}
