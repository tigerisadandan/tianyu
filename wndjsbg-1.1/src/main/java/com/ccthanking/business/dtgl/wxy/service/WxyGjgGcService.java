/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyGjgGcService.java
 * 创建日期： 2015-05-19 下午 01:05:34
 * 功能：    接口：危险源钢结构工程过程管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-19 下午 01:05:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service;


import com.ccthanking.business.dtgl.wxy.vo.WxyGjgGcVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> WxyGjgGcService.java </p>
 * <p> 功能：危险源钢结构工程过程管理 </p>
 *
 * <p><a href="WxyGjgGcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-19
 * 
 */
public interface WxyGjgGcService extends IBaseService<WxyGjgGcVO,String > {

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
