/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_spfjfsyjgysbadtjzhzService.java
 * 创建日期： 2014-11-06 下午 03:56:47
 * 功能：    接口：商品房交付使用竣工验收备案单体建筑汇总
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:56:47  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> bu_spyw_spfjfsyjgysbadtjzhzService.java </p>
 * <p> 功能：商品房交付使用竣工验收备案单体建筑汇总 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysbadtjzhzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */
public interface bu_spyw_spfjfsyjgysbadtjzhzService extends IBaseService<BuSpywSpfjfsyjgysbadtjzhzVO, String> {

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
    BuSpywSpfjfsyjgysbadtjzhzVO findByDtId(String tid) throws Exception;
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
