/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.SgZhengshuService.java
 * 创建日期： 2014-04-27 下午 01:22:52
 * 功能：    接口：人员证书信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:22:52  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.person.service.impl;


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

import com.ccthanking.business.person.vo.SgZhengshuVO;
import com.ccthanking.business.person.dao.SgPersonZhengshuDao;
import com.ccthanking.business.person.dao.SgZhengshuDao;
import com.ccthanking.business.person.service.SgZhengshuService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgZhengshuService.java </p>
 * <p> 功能：人员证书信息 </p>
 *
 * <p><a href="SgZhengshuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

@Service
public class SgZhengshuServiceImpl extends Base1ServiceImpl<SgZhengshuVO, String> implements SgZhengshuService {

	private static Logger logger = LoggerFactory.getLogger(SgZhengshuServiceImpl.class);
	private SgZhengshuDao sgZhengshuDao;
	@Autowired
	@Qualifier("sgZhengshuDaoImpl")
	public void setSgZhengshuDao(SgZhengshuDao sgZhengshuDao) {
		this.sgZhengshuDao = sgZhengshuDao;
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
        String domresult=sgZhengshuDao.queryCondition(json);
        return domresult;

    }
    
    public String insert(String json) throws Exception {

    	String domresult=sgZhengshuDao.insert(json);
        return domresult;

    }

    public String update(String json) throws Exception {

    	String domresult=sgZhengshuDao.update(json);
        return domresult;

    }

    public String delete(String json) throws Exception {

    	String domresult=sgZhengshuDao.delete(json);
        return domresult;

	}

	public String queryAllZhengshu() throws Exception {
		String domresult=sgZhengshuDao.queryAllZhengshu();
        return domresult;

	}

	public String queryZsByGw(String personUid, String gwUid) throws Exception {
		
		String domresult=sgZhengshuDao.queryZsByGw(personUid, gwUid);
        return domresult;

	}
	
	public String queryZs(String personUid,String zhengshu_type) throws Exception {
		
		String domresult=sgZhengshuDao.queryZs(personUid, zhengshu_type);
        return domresult;

	}
 
}
