/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgScoreService.java
 * 创建日期： 2014-06-09 上午 09:35:20
 * 功能：    接口：分数
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:35:20  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service;


import com.ccthanking.business.sgenter.vo.SgScoreVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SgScoreService.java </p>
 * <p> 功能：分数 </p>
 *
 * <p><a href="SgScoreService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */
public interface SgScoreService extends IBaseService<SgScoreVO, String> {

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

    /**
     * 查询分数信息
     * @param uid
     * @param type
     * scoreInfo  :总分数信息
     * jbInfo     :基础分数信息
     * xmInfo     :项目分数信息
     * jxInfo     :奖励分数信息
     * @return
     * @throws Exception
     */
    String tongjiScore(String uid,String type) throws Exception;
}
