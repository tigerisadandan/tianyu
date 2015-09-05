/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpywJyfwfsfJsgcjktzdService.java
 * 创建日期： 2014-11-18 下午 03:02:34
 * 功能：    接口：十、交易服务费收费
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 03:02:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service;


import javax.servlet.http.HttpServletResponse;


import com.ccthanking.business.bzwj.BuSpywJyfwfsfJsgcjktzdVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywJyfwfsfJsgcjktzdService.java </p>
 * <p> 功能：十、交易服务费收费 </p>
 *
 * <p><a href="BuSpywJyfwfsfJsgcjktzdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */
public interface BuSpywJyfwfsfJsgcjktzdService extends IBaseService<BuSpywJyfwfsfJsgcjktzdVO, String> {

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
    
    String getCount() throws Exception;
    
    String queryByLzbz(String json) throws Exception;
    
    String querytoword(HttpServletResponse response,String id,String ptfName) throws Exception;
    
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
