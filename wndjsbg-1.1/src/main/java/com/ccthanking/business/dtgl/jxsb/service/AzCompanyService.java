/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.AzCompanyService.java
 * 创建日期： 2014-12-11 上午 11:06:42
 * 功能：    接口：安装企业
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-11 上午 11:06:42  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service;


import com.ccthanking.business.dtgl.azqy.vo.AzCompanyVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> AzCompanyService.java </p>
 * <p> 功能：安装企业 </p>
 *
 * <p><a href="AzCompanyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-11
 * 
 */
public interface AzCompanyService extends IBaseService<AzCompanyVO, String> {

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
    
    
    String queryDq(String json) throws Exception;
    
    String queryCount(String json) throws Exception;
    
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
    
    String updateOld(String json) throws Exception;
    
    String updateYqsq(String json) throws Exception;

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
