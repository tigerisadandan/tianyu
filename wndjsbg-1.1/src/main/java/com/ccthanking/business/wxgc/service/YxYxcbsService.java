/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxYxcbsService.java
 * 创建日期： 2014-12-23 下午 01:31:30
 * 功能：    接口：预选承包商
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:31:30  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service;


import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxYxcbsVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> YxYxcbsService.java </p>
 * <p> 功能：预选承包商 </p>
 *
 * <p><a href="YxYxcbsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */
public interface YxYxcbsService extends IBaseService<YxYxcbsVO, String> {

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
    String queryspyjCondition(String json) throws Exception;
    String querytjCondition(String json) throws Exception;
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
    String updateXzYxcbs(String json) throws Exception;
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
    
    String querycbsgctype(Map map) throws Exception;

}
