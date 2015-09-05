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
package com.ccthanking.business.person.service;


import com.ccthanking.business.person.vo.ZhichengVO;

import com.ccthanking.framework.service.IBaseService;


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
public interface SgZhichengService extends IBaseService<ZhichengVO, String> {

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
