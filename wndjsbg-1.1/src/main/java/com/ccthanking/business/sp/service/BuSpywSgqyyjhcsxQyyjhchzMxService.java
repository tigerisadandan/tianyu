/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywSgqyyjhcsxQyyjhchzMxService.java
 * 创建日期： 2014-11-12 上午 11:32:59
 * 功能：    接口：施工企业业绩核查汇总表_明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-12 上午 11:32:59  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import com.ccthanking.business.spyw.vo.BuSpywSgqyyjhcsxQyyjhchzMxVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywSgqyyjhcsxQyyjhchzMxService.java </p>
 * <p> 功能：施工企业业绩核查汇总表_明细 </p>
 *
 * <p><a href="BuSpywSgqyyjhcsxQyyjhchzMxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-12
 * 
 */
public interface BuSpywSgqyyjhcsxQyyjhchzMxService extends IBaseService<BuSpywSgqyyjhcsxQyyjhchzMxVO, String> {

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
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryUpdate(String id) throws Exception;
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
