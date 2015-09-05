/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    dtgl.service.JxsbCxgzService.java
 * 创建日期： 2015-01-26 下午 04:31:09
 * 功能：    接口：机械设备拆卸告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 04:31:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.dao.XyFzDao;
import com.ccthanking.business.dtgl.service.XyFzService;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;


import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JxsbCxgzService.java </p>
 * <p> 功能：信用分值管理 </p>
 *
 * <p><a href="XyFzService.java.html"><i>查看源代码</i></a></p>  
 *
  * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-08-04
 * 
 */

@Service
public class XyFzServiceImpl extends Base1ServiceImpl implements XyFzService {

	private static Logger logger = LoggerFactory.getLogger(XyFzServiceImpl.class);
	
    
    private XyFzDao xyFzDao;

    // @Override
    public String queryCondition(String json,String qiyeType) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = xyFzDao.queryCondition(json,qiyeType);

        }catch (DaoException e) {
        	logger.error("信用分值查询{}", e.getMessage());
			SystemException.handleMessageException("信用分值查询查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public boolean updateScore(String qiyeType,String id,String score){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        boolean domresult = false;
        try {

			domresult = xyFzDao.updateScore(qiyeType, id, score);

        }catch (DaoException e) {
        	logger.error("信用分值查询{}", e.getMessage());
			SystemException.handleMessageException("信用分值查询查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    


	@Autowired
	@Qualifier("xyFzDaoImpl")
	public void setXyFzDao(XyFzDao xyFzDao) {
		this.xyFzDao = xyFzDao;
	}
    
}
