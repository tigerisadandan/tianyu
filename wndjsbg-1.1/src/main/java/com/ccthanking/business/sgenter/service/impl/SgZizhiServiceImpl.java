/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgZiZhiService.java
 * 创建日期： 2014-04-10 上午 10:27:57
 * 功能：    接口：资质表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-10 上午 10:27:57  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sgenter.dao.SgZizhiDao;
import com.ccthanking.business.sgenter.service.SgZizhiService;
import com.ccthanking.business.sgenter.vo.SgZizhiVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;



/**
 * <p> SgZiZhiService.java </p>
 * <p> 功能：资质表 </p>
 *
 * <p><a href="SgZiZhiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-10
 * 
 */

@Service
public class SgZizhiServiceImpl extends Base1ServiceImpl<SgZizhiVO, String> implements SgZizhiService {

	private static Logger logger = LoggerFactory.getLogger(SgZizhiServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.SG_ZIZHI;
	@Autowired
	private SgZizhiDao sgZizhiDao ;
	
    // @Override
    public String queryCondition(String json) throws Exception {
    
       // Connection conn = DBUtil.getConnection();
        String domresult = sgZizhiDao.queryCondition(json);
       
        return domresult;

    }
    
    // @Override
    public String insert(String json) throws Exception {

        
        String resultVO = sgZizhiDao.insert(json);
        
        return resultVO;

    }

    // @Override
    public String update(String json) throws Exception {

        String resultVO = sgZizhiDao.update(json);

        
        return resultVO;

    }

    // @Override
    public String delete(String json) throws Exception {

        String resultVO = sgZizhiDao.delete(json);
      
        return resultVO;

    }

	public String queryAllZizhi() throws Exception {
		 
		String resultVO = sgZizhiDao.queryAllZizhi();
	      
		return resultVO;
	}
	public String queryAllZhuanye(String json) throws Exception {
		 
		String resultVO = sgZizhiDao.queryAllZizhi();
	      
		return resultVO;
	}
}
