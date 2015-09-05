/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbSydjService.java
 * 创建日期： 2014-12-26 上午 10:44:21
 * 功能：    接口：机械设备使用登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-26 上午 10:44:21  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.dtgl.azqy.vo.JxsbSydjVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JxsbSydjService.java </p>
 * <p> 功能：机械设备使用登记 </p>
 *
 * <p><a href="JxsbSydjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-26
 * 
 */
public interface JxsbSydjService extends IBaseService<JxsbSydjVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,HttpServletRequest request) throws Exception;
    
    String queryList(String json) throws Exception;
    
    String queryJxsb(String json) throws Exception;
    
    String queryRy(String json) throws Exception;
    String toword(HttpServletResponse response,String json) throws Exception;
    
    String downloadDjz(HttpServletResponse response,String json) throws Exception;
    
    
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
    String update(String json,String type) throws Exception;

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
