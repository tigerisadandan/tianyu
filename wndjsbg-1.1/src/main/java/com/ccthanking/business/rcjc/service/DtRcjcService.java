/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rcjc.service.DtRcjcService.java
 * 创建日期： 2015-06-23 下午 05:51:24
 * 功能：    接口：日常检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-06-23 下午 05:51:24  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rcjc.service;


import com.ccthanking.business.dtgl.rcjc.vo.DtRcjcVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> DtRcjcService.java </p>
 * <p> 功能：日常检查 </p>
 *
 * <p><a href="DtRcjcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongweixiong@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-06-23
 * 
 */
public interface DtRcjcService extends IBaseService<DtRcjcVO, String> {

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
