/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywRqgyxkzSqService.java
 * 创建日期： 2014-11-11 下午 04:54:18
 * 功能：    接口：燃气供应许可证
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-11 下午 04:54:18  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import com.ccthanking.business.spyw.vo.BuSpywRqgyxkzSqVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywRqgyxkzSqService.java </p>
 * <p> 功能：燃气供应许可证 </p>
 *
 * <p><a href="BuSpywRqgyxkzSqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-11
 * 
 */
public interface BuSpywRqgyxkzSqService extends IBaseService<BuSpywRqgyxkzSqVO, String> {

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
	String download(String id) throws Exception ;
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
    String ywlzclhf(String json)throws Exception;

}
