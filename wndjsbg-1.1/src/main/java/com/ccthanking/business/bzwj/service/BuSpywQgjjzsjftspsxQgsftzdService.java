/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpywQgjjzsjftspsxQgsftzdService.java
 * 创建日期： 2014-11-07 上午 09:41:47
 * 功能：    接口：八墙体材料专项基金预收款收费通知单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-07 上午 09:41:47  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.bzwj.BuSpywQgjjzsjftspsxQgsftzdVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.IBaseService;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;


/**
 * <p> BuSpywQgjjzsjftspsxQgsftzdService.java </p>
 * <p> 功能：八墙体材料专项基金预收款收费通知单 </p>
 *
 * <p><a href="BuSpywQgjjzsjftspsxQgsftzdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-07
 * 
 */
public interface BuSpywQgjjzsjftspsxQgsftzdService extends IBaseService<BuSpywQgjjzsjftspsxQgsftzdVO, String> {

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
    
    String getCount() throws Exception;
    
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
    String querytoword(HttpServletResponse response,String id,String ptfName) throws Exception;

}
