/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.jhgl.service.WorkPlanService.java
 * 创建日期： 2015-05-22 下午 12:00:14
 * 功能：    接口：计划管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-22 下午 12:00:14  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.nbgl.jhgl.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.nbgl.jhgl.vo.WorkPlanVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> WorkPlanService.java </p>
 * <p> 功能：计划管理 </p>
 *
 * <p><a href="WorkPlanService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-22
 * 
 */
public interface WorkPlanService extends IBaseService<WorkPlanVO, String> {
    
	/**
	 * 根据 时间 查询出 所有的周记录及日期
	 */
	String queryByTime(String nianfen) throws Exception;
	
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
    String insert(List<Map<String, String>> list) throws Exception;

    /**
     * 修改记录.
     * 
     * @param list
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
   // String update(List<Map<String, String>> list) throws Exception;

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
     * 查用户的部门编号
     * @param user_uid
     * @return
     * @throws Exception
     */
	String queryOid(String user_uid)throws Exception;
	
	/**
	 * 根据时间段 查询 部门的下周计划
	 * @param timeStr
	 * @return
	 * @throws Exception
	 */
	String queryByData(String timeStr) throws Exception;
    
	/**
	 * 据时间段 查询 4个部门的数据
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	String getDataForDept(String str) throws Exception;
    
	/**
	 * 打印工作计划
	 * @param response
	 * @param bbid
	 * @return
	 */
	String toword(HttpServletResponse response, String bbid)throws Exception;
    
	/**
	 * 查询 部门 名
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	String queryCode(String deptId)throws Exception;

}
