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
package com.ccthanking.business.dtgl.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JxsbCxgzService.java </p>
 * <p> 功能：信用分值管理  </p>
 *
 * <p><a href="XyFzDao.java.html"><i>查看源代码</i></a></p>  
 *
  * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-08-04
 * 
 */
public interface XyFzService extends IBaseService {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,String qiyeType) throws Exception;
    
    /**
	 * 更新表score字段的值
	 * @param qiyeType
	 * @param id
	 * @param score
	 * @return
	 */
	 public boolean updateScore(String qiyeType,String id,String score);
    

}
