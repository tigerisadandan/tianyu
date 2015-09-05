/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.ZhichengService.java
 * 创建日期： 2014-04-27 下午 01:35:40
 * 功能：    接口：职称信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:35:40  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.person.vo.ZhichengVO;
import com.ccthanking.business.person.dao.SgPersonZhengshuDao;
import com.ccthanking.business.person.dao.SgZhichengDao;
import com.ccthanking.business.person.service.SgZhichengService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ZhichengService.java </p>
 * <p> 功能：职称信息 </p>
 *
 * <p><a href="ZhichengService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

@Service
public class SgZhichengServiceImpl extends Base1ServiceImpl<ZhichengVO, String> implements SgZhichengService {

	private static Logger logger = LoggerFactory.getLogger(SgZhichengServiceImpl.class);
	private SgZhichengDao sgZhichengDao;
	@Autowired
	@Qualifier("sgZhichengDaoImpl")
	public void setSgZhichengDao(SgZhichengDao sgZhichengDao) {
		this.sgZhichengDao = sgZhichengDao;
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	String domresult=sgZhichengDao.queryCondition(json);
        return domresult;

    }
    
    public String insert(String json) throws Exception {

    	String domresult=sgZhichengDao.insert(json);
        return domresult;


    }

    public String update(String json) throws Exception {

    	String domresult=sgZhichengDao.update(json);
        return domresult;


    }

    public String delete(String json) throws Exception {

    	String domresult=sgZhichengDao.delete(json);
        return domresult;

	}

}
