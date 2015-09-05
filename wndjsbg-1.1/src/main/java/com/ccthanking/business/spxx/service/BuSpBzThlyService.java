/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzThlyService.java
 * 创建日期： 2014-06-29 上午 10:05:44
 * 功能：    接口：步骤处理时的退回理由
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-29 上午 10:05:44  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service;


import com.ccthanking.business.spxx.vo.BuSpBzThlyVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpBzThlyService.java </p>
 * <p> 功能：步骤处理时的退回理由 </p>
 *
 * <p><a href="BuSpBzThlyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-29
 * 
 */
public interface BuSpBzThlyService extends IBaseService<BuSpBzThlyVO, String> {

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
    
    
    public String getSpYwxx() throws Exception;
    

}
