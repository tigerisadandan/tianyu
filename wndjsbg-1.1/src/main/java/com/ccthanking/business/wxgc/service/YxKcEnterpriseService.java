/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxKcEnterpriseService.java
 * 创建日期： 2014-12-23 下午 01:25:43
 * 功能：    接口：勘察设计企业信息库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:25:43  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service;


import com.ccthanking.business.wxgc.vo.YxKcEnterpriseVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> YxKcEnterpriseService.java </p>
 * <p> 功能：勘察设计企业信息库 </p>
 *
 * <p><a href="YxKcEnterpriseService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */
public interface YxKcEnterpriseService extends IBaseService<YxKcEnterpriseVO, String> {

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
    String queryOldCondition(String json) throws Exception;
    String queryspyjCondition(String json) throws Exception;
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
