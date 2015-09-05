/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxWxgcPbfsService.java
 * 创建日期： 2015-03-11 下午 04:35:38
 * 功能：    接口：微型工程评标方式
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-11 下午 04:35:38  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service;


import com.ccthanking.business.wxgc.vo.YxWxgcPbfsVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> YxWxgcPbfsService.java </p>
 * <p> 功能：微型工程评标方式 </p>
 *
 * <p><a href="YxWxgcPbfsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-03-11
 * 
 */
public interface YxWxgcPbfsService extends IBaseService<YxWxgcPbfsVO,String > {

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
