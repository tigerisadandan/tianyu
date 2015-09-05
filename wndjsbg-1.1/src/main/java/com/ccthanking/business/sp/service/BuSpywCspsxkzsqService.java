/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCspsxkzsqService.java
 * 创建日期： 2014-06-11 下午 02:27:33
 * 功能：    接口：排水许可证申请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-11 下午 02:27:33  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.spyw.vo.BuSpywCspsxkzsqVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywCspsxkzsqService.java </p>
 * <p> 功能：排水许可证申请 </p>
 *
 * <p><a href="BuSpywCspsxkzsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */
public interface BuSpywCspsxkzsqService extends IBaseService<BuSpywCspsxkzsqVO, String> {

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
    
    String toword(HttpServletResponse response,String json) throws Exception;

     String ywlzclhf(String json)throws Exception;
}
