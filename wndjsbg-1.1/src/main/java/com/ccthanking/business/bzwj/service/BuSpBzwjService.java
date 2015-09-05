/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpBzwjService.java
 * 创建日期： 2014-10-28 上午 11:02:10
 * 功能：    接口：步骤文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 上午 11:02:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service;



import com.ccthanking.business.spxx.vo.BuSpBzwjVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpBzwjService.java </p>
 * <p> 功能：步骤文件 </p>
 *
 * <p><a href="BuSpBzwjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */
public interface BuSpBzwjService extends IBaseService<BuSpBzwjVO, String> {

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
    String querylzbzwj(String json) throws Exception;
    
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
    
    String insertlzwj(String json) throws Exception;
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
