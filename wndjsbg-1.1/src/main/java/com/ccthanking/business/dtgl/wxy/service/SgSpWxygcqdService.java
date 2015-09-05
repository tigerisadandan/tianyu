/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.SgSpWxygcqdService.java
 * 创建日期： 2015-04-23 上午 11:44:17
 * 功能：    接口：较大危险源工程清单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 上午 11:44:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service;


import java.util.Map;

import com.ccthanking.business.dtgl.wxy.vo.SgSpWxygcqdVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SgSpWxygcqdService.java </p>
 * <p> 功能：较大危险源工程清单 </p>
 *
 * <p><a href="SgSpWxygcqdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */
public interface SgSpWxygcqdService extends IBaseService<SgSpWxygcqdVO, String> {

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
    
    String queryWxYBh(String json) throws Exception;
    
    String queryGcStatus(Map json) throws Exception;
    
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
