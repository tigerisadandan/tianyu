/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsService.java
 * 创建日期： 2014-06-13 下午 05:31:52
 * 功能：    接口：环境影响登记告知承诺
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 05:31:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywHjyxdjgzcnsService.java </p>
 * <p> 功能：环境影响登记告知承诺 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */
public interface BuSpywHjyxdjgzcnsService extends IBaseService<BuSpywHjyxdjgzcnsVO, String> {

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
	String download(String id) throws Exception ;
	 /**
     * 根据条件查询附件记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryReadFile(String id) throws Exception;
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

}
