/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywScxmmjhsdService.java
 * 创建日期： 2014-10-27 下午 05:33:08
 * 功能：    接口：三产项目面积核算单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-27 下午 05:33:08  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;



import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.spyw.vo.BuSpywScxmmjhsdVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywScxmmjhsdService.java </p>
 * <p> 功能：三产项目面积核算单 </p>
 *
 * <p><a href="BuSpywScxmmjhsdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-27
 * 
 */
public interface BuSpywScxmmjhsdService extends IBaseService<BuSpywScxmmjhsdVO, String> {

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
    
    String queryByLzbz(String json) throws Exception;
    
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
    String querytoword(HttpServletResponse response,String id,String filename) throws Exception;

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
