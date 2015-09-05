/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbBywxService.java
 * 创建日期： 2014-12-24 下午 05:10:41
 * 功能：    接口：机械设备保养维修记录表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-24 下午 05:10:41  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service.impl;


import javax.servlet.http.HttpServletRequest;

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

import com.ccthanking.business.dtgl.azqy.vo.JxsbBywxVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbBywxDao;
import com.ccthanking.business.dtgl.jxsb.service.JxsbBywxService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JxsbBywxService.java </p>
 * <p> 功能：机械设备保养维修记录表 </p>
 *
 * <p><a href="JxsbBywxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-24
 * 
 */

@Service
public class JxsbBywxServiceImpl extends Base1ServiceImpl<JxsbBywxVO, String> implements JxsbBywxService {

	private static Logger logger = LoggerFactory.getLogger(JxsbBywxServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JXSB_BYWX;
    
    private JxsbBywxDao jxsbBywxDao;

    // @Override
    public String queryCondition(String json,HttpServletRequest request) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbBywxDao.queryCondition(json, null, null,request);

            
        }catch (DaoException e) {
        	logger.error("机械设备保养维修记录表{}", e.getMessage());
			SystemException.handleMessageException("机械设备保养维修记录表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JxsbBywxVO vo = new JxsbBywxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
     
            // 插入
			jxsbBywxDao.save(vo);
            resultVO = vo.getRowJson();

        	//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("机械设备保养维修记录表{}", e.getMessage());
            SystemException.handleMessageException("机械设备保养维修记录表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbBywxVO vo = new JxsbBywxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          // 修改
            jxsbBywxDao.update(vo);
            resultVO = vo.getRowJson();

        
        } catch (DaoException e) {
            logger.error("机械设备保养维修记录表{}", e.getMessage());
            SystemException.handleMessageException("机械设备保养维修记录表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JxsbBywxVO vo = new JxsbBywxVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);
			//删除   根据据主键
			jxsbBywxDao.delete(JxsbBywxVO.class, vo.getJxsb_bywx_uid());

			resultVo = vo.getRowJson();
	
		} catch (DaoException e) {
            logger.error("机械设备保养维修记录表{}", e.getMessage());
           SystemException.handleMessageException("机械设备保养维修记录表删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jxsbBywxDaoImpl")
	public void setJxsbBywxDao(JxsbBywxDao jxsbBywxDao) {
		this.jxsbBywxDao = jxsbBywxDao;
	}
    
}
