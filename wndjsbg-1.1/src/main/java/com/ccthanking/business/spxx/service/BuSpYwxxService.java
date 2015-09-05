/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpYwxxService.java
 * 创建日期： 2014-06-11 上午 11:51:59
 * 功能：    接口：审批业务信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-11 上午 11:51:59  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service;


import com.ccthanking.business.spxx.vo.BuSpYwxxVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpYwxxService.java </p>
 * <p> 功能：审批业务信息 </p>
 *
 * <p><a href="BuSpYwxxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */
public interface BuSpYwxxService extends IBaseService<BuSpYwxxVO, String> {

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
    
    String queryBz(String json) throws Exception;
    
    String queryYWLX(String json) throws Exception;
    
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

    /**
     * 加载审批业务树
     * @return
     * @throws Exception
     */
    String getAllSpyw() throws Exception;
}
