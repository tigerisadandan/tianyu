/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.GcTypeService.java
 * 创建日期： 2014-04-22 下午 04:07:38
 * 功能：    接口：施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-22 下午 04:07:38  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb.service;




import com.ccthanking.framework.service.IBaseService;


/**
 * <p> GcTypeService.java </p>
 * <p> 功能：施工报备 </p>
 *
 * <p><a href="GcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-22
 * 
 */
public interface JlGcTypeService extends IBaseService {

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
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryTypelist(String p_type) throws Exception;
     
    /**
     * 查询工程父类型
     * @param gc_type
     * @return
     * @throws Exception
     */
    public String queryPType(String gc_type) throws Exception;

}
