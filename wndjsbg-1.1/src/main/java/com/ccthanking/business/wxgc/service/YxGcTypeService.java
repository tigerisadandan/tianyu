/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxGcTypeService.java
 * 创建日期： 2015-01-06 下午 10:29:23
 * 功能：    接口：微型工程类型维护
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-06 下午 10:29:23  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service;


import com.ccthanking.business.wxgc.vo.YxGcTypeVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> YxGcTypeService.java </p>
 * <p> 功能：微型工程类型维护 </p>
 *
 * <p><a href="YxGcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-06
 * 
 */
public interface YxGcTypeService extends IBaseService<YxGcTypeVO, String> {

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
