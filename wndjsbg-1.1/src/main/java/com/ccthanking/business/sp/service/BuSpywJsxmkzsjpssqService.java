/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywJsxmkzsjpssqService.java
 * 创建日期： 2014-05-30 下午 02:15:19
 * 功能：    接口：设计评审
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-30 下午 02:15:19  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.spyw.vo.BuSpywJsxmkzsjpssqVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywJsxmkzsjpssqService.java </p>
 * <p> 功能：设计评审 </p>
 *
 * <p><a href="BuSpywJsxmkzsjpssqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-05-30
 * 
 */
public interface BuSpywJsxmkzsjpssqService extends IBaseService<BuSpywJsxmkzsjpssqVO, String> {

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
