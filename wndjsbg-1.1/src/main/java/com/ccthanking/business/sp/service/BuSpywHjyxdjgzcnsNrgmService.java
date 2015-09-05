/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsNrgmService.java
 * 创建日期： 2014-06-13 下午 05:53:33
 * 功能：    接口：环境影响登记告知承诺内容规模
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 05:53:33  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsNrgmVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywHjyxdjgzcnsNrgmService.java </p>
 * <p> 功能：环境影响登记告知承诺内容规模 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsNrgmService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */
public interface BuSpywHjyxdjgzcnsNrgmService extends IBaseService<BuSpywHjyxdjgzcnsNrgmVO, String> {

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
    String queryNrgmUpdate(String id) throws Exception;
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
