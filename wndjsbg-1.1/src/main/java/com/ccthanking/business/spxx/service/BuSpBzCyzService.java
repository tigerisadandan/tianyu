/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzCyzService.java
 * 创建日期： 2014-06-13 下午 04:43:40
 * 功能：    接口：业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:43:40  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service;


import com.ccthanking.business.spxx.vo.BuSpBzCyzVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpBzCyzService.java </p>
 * <p> 功能：业务材料 </p>
 *
 * <p><a href="BuSpBzCyzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */
public interface BuSpBzCyzService extends IBaseService<BuSpBzCyzVO, String> {

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
    
    /**
	 * 为步骤信息提供用户选择
	 * @param bz_uid
	 * @return
	 */
	public String getUsers(String bz_uid,String uname);
	
	 /**
	 * 添加参与者信息
	 * @param bz_uid
	 * @return
	 */
	public String insertUsers(String json);
	
	/**
	 * 查询步骤参与者信息
	 * @param bz_uid
	 * @return
	 */
	public String queryBzCyz(String bz_uid);

}
