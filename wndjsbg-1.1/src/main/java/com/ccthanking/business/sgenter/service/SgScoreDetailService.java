/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgScoreDetailService.java
 * 创建日期： 2014-06-09 上午 09:36:33
 * 功能：    接口：分数主体
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:36:33  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service;


import com.ccthanking.business.sgenter.vo.SgScoreDetailVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SgScoreDetailService.java </p>
 * <p> 功能：分数主体 </p>
 *
 * <p><a href="SgScoreDetailService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */
public interface SgScoreDetailService extends IBaseService<SgScoreDetailVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json) throws Exception;
    
    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json) throws Exception;

    /**
     * 删除记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json) throws Exception;

}
