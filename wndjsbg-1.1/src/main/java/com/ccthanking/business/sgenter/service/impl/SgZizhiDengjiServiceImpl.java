/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgZiZhiDengjiService.java
 * 创建日期： 2014-04-10 上午 11:01:40
 * 功能：    接口：资质表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-10 上午 11:01:40  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sgenter.dao.SgZizhiDengjiDao;
import com.ccthanking.business.sgenter.service.SgZizhiDengjiService;
import com.ccthanking.business.sgenter.vo.SgZizhiDengjiVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;



/**
 * <p> SgZiZhiDengjiService.java </p>
 * <p> 功能：资质表 </p>
 *
 * <p><a href="SgZiZhiDengjiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-10
 * 
 */

@Service
public class SgZizhiDengjiServiceImpl extends Base1ServiceImpl<SgZizhiDengjiVO, String> implements SgZizhiDengjiService {

	private static Logger logger = LoggerFactory.getLogger(SgZizhiDengjiServiceImpl.class);
	
	// 业务类型
	@Autowired
	private SgZizhiDengjiDao sgZizhiDengjiDao;
	
    public String queryCondition(String json) throws Exception {
    
        String domresult = sgZizhiDengjiDao.queryCondition(json);
        return domresult;

    }
    
    public String insert(String json) throws Exception {

        String resultVO = sgZizhiDengjiDao.insert(json);
        return resultVO;

    }

    public String update(String json) throws Exception {

        String resultVO = sgZizhiDengjiDao.update(json);

        return resultVO;

    }

    public String delete(String json) throws Exception {

        String resultVo = sgZizhiDengjiDao.delete(json);
       
        return resultVo;

    }

	public String queryZizhiDengji(String zizhiUid) throws Exception {
		String resultVo = sgZizhiDengjiDao.queryZizhiDengji(zizhiUid);
	       
        return resultVo;
	}
    
}
