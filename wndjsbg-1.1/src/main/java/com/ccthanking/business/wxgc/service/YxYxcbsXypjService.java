/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxYxcbsXypjService.java
 * 创建日期： 2015-01-21 上午 10:47:08
 * 功能：    接口：预选承包商信用评价
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-21 上午 10:47:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service;


import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxYxcbsXypjVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> YxYxcbsXypjService.java </p>
 * <p> 功能：预选承包商信用评价 </p>
 *
 * <p><a href="YxYxcbsXypjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-21
 * 
 */
public interface YxYxcbsXypjService extends IBaseService<YxYxcbsXypjVO,String > {

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
    
    String queryAllXypjXqCondition(Map map) throws Exception;
    String queryXypjLx(Map map)throws Exception;
    String queryfilePathXypj(Map map)throws Exception;
    
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
