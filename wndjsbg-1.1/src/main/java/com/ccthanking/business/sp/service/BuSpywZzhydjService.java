/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywZzhydjService.java
 * 创建日期： 2014-05-27 下午 04:27:16
 * 功能：    接口：资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 04:27:16  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.spyw.vo.BuSpywZzhydjVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywZzhydjService.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywZzhydjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */
public interface BuSpywZzhydjService extends IBaseService<BuSpywZzhydjVO, String> {

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
