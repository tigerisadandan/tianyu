/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyDzgcService.java
 * 创建日期： 2015-04-23 下午 02:20:11
 * 功能：    接口：吊装工程提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 下午 02:20:11  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.dtgl.wxy.vo.WxyDzgcVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> WxyDzgcService.java </p>
 * <p> 功能：吊装工程提示单 </p>
 *
 * <p><a href="WxyDzgcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */
public interface WxyDzgcService extends IBaseService<WxyDzgcVO,String > {

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
     * 退回
     * @param map
     * @return
     * @throws Exception
     */
    boolean tuiHui(String gcId) throws Exception;
    
    String toword(HttpServletResponse response,String json) throws Exception;
    
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
