/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpLzhfService.java
 * 创建日期： 2014-06-25 下午 03:35:39
 * 功能：    接口：审批业务流转核发文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-25 下午 03:35:39  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service;


import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpLzhfVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpLzhfService.java </p>
 * <p> 功能：审批业务流转核发文件 </p>
 *
 * <p><a href="BuSpLzhfService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-25
 * 
 */
public interface BuSpLzhfService extends IBaseService<BuSpLzhfVO, String> {

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
    
    
    public  boolean saveBuSpLzhfVO(Map mapFtl,Map mapVo);
    
    public String insertBuSpLzhfVO(String json,Map temmap) throws Exception;

}
