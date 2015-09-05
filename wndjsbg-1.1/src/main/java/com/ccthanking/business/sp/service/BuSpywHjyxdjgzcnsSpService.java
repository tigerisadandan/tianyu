/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsSpService.java
 * 创建日期： 2014-06-17 下午 02:04:43
 * 功能：    接口：建设项目环境影响评价报告表（书）审批
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:04:43  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsSpVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywHjyxdjgzcnsSpService.java </p>
 * <p> 功能：建设项目环境影响评价报告表（书）审批 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsSpService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */
public interface BuSpywHjyxdjgzcnsSpService extends IBaseService<BuSpywHjyxdjgzcnsSpVO, String> {

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
