/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpYwclService.java
 * 创建日期： 2014-06-18 上午 10:56:57
 * 功能：    接口：审批业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-18 上午 10:56:57  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service;


import com.ccthanking.business.spxx.vo.BuSpYwclVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpYwclService.java </p>
 * <p> 功能：审批业务材料 </p>
 *
 * <p><a href="BuSpYwclService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-18
 * 
 */
public interface BuSpYwclService extends IBaseService<BuSpYwclVO, String> {

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
