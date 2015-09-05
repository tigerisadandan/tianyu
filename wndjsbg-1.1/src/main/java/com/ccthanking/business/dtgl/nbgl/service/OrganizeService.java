/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.OrganizeService.java
 * 创建日期： 2015-05-25 下午 09:13:36
 * 功能：    接口：组织结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-25 下午 09:13:36  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl.service;


import com.ccthanking.business.dtgl.nbgl.vo.OrganizeVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> OrganizeService.java </p>
 * <p> 功能：组织结构 </p>
 *
 * <p><a href="OrganizeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-25
 * 
 */
public interface OrganizeService extends IBaseService<OrganizeVO, String> {

    
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
	 * 根据条件查询记录(组织结构管理-树结构数据).
	 * 
    
	 * @param json
	 * @param user
	 * @return
	 * @throws Exception
	 * @since v1.00
	 */
	String queryOrganize() throws Exception;
    
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
	 * 根据当前登陆用户，查询非登录用户的部门的其他用户（其他部门用户）
	 * @return
	 */
    String queryRestDeptUser() throws Exception;
    
    /**
	 * 查看当前用户所在部门的其他用户
	 * @return
	 */
	public String queryDeptUserByCurrentUser();

}
