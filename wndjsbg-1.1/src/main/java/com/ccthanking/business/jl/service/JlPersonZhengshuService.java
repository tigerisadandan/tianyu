/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jl.service.JlPersonZhengshuService.java
 * 创建日期： 2015-01-26 下午 02:23:22
 * 功能：    接口：监理人员证书
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:23:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jl.service;



import com.ccthanking.business.dtgl.jl.vo.JlPersonZhengshuVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JlPersonZhengshuService.java </p>
 * <p> 功能：监理人员证书 </p>
 *
 * <p><a href="JlPersonZhengshuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */
public interface JlPersonZhengshuService extends IBaseService<JlPersonZhengshuVO, String> {

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
    
    
    
    String queryListPersonZhengshu(String json) throws Exception;

}