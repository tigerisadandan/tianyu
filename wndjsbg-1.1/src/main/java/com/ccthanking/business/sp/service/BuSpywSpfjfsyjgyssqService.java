/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywSpfjfsyjgyssqService.java
 * 创建日期： 2014-06-09 下午 01:59:10
 * 功能：    接口：商品房交付使用竣工验收申请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 01:59:10  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywSpfjfsyjgyssqService.java </p>
 * <p> 功能：商品房交付使用竣工验收申请 </p>
 *
 * <p><a href="BuSpywSpfjfsyjgyssqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */
public interface BuSpywSpfjfsyjgyssqService extends IBaseService<BuSpywSpfjfsyjgyssqVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
	String download(String id) throws Exception ;
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
