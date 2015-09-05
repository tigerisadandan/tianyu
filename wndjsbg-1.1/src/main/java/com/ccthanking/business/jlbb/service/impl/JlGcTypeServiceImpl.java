/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.GcTypeService.java
 * 创建日期： 2014-04-22 下午 04:07:38
 * 功能：    接口：施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-22 下午 04:07:38  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.jlbb.dao.JlGcTypeDao;
import com.ccthanking.business.jlbb.service.JlGcTypeService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;


import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> GcTypeService.java </p>
 * <p> 功能：施工报备 </p>
 *
 * <p><a href="GcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-22
 * 
 */

@Service
public class JlGcTypeServiceImpl extends Base1ServiceImpl implements JlGcTypeService {

	private static Logger logger = LoggerFactory.getLogger(JlGcTypeServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.GC_TYPE;
    
    private JlGcTypeDao jlgcTypeDao;
    
	@Autowired
	@Qualifier("jlGcTypeDaoImpl")
	public void setJlgcTypeDao(JlGcTypeDao jlgcTypeDao) {
		this.jlgcTypeDao = jlgcTypeDao;
	}

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jlgcTypeDao.queryCondition(json);

        }catch (DaoException e) {
        	logger.error("施工报备{}", e.getMessage());
			SystemException.handleMessageException("施工报备查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    

	public String queryTypelist(String pType) throws Exception {
		// TODO Auto-generated method stub
		return jlgcTypeDao.queryTypelist(pType);
	}

	public String queryPType(String gcType) throws Exception {
		// TODO Auto-generated method stub
		return jlgcTypeDao.queryPType(gcType);
	}
	
	

    
}
