/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzService.java
 * 创建日期： 2014-06-13 下午 04:41:17
 * 功能：    接口：业务信息步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:41:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service;


import java.util.List;

import com.ccthanking.business.spxx.vo.BuSpBzVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpBzService.java </p>
 * <p> 功能：业务信息步骤 </p>
 *
 * <p><a href="BuSpBzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */
public interface BuSpBzService extends IBaseService<BuSpBzVO, String> {

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
	 * 查询审批步骤和审批参与者信息,按审批步骤序号降序排列
	 * spywid  审批业务ID
	 * bzlx    步骤类型
	 * add by long 20140619
	 * */
    public List<?> getSpBzList(String spywid,String bzlx);

}
