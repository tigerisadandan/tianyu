/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywJsgcsgxkzsqService.java
 * 创建日期： 2014-05-27 下午 03:04:23
 * 功能：    接口：施工许可申请表-明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 03:04:23  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywJsgcsgxkzsqService.java </p>
 * <p> 功能：施工许可申请表-明细 </p>
 *
 * <p><a href="BuSpywJsgcsgxkzsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */
public interface BuSpywJsgcsgxkzsqService extends IBaseService<BuSpywJsgcsgxkzsqVO, String> {

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
     * 根据条件查询附件记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryReadFile(String json) throws Exception;
    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json,Map files) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json,Map files) throws Exception;

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
