/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhService.java
 * 创建日期： 2015-05-27 上午 10:40:51
 * 功能：    接口：告知会
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-27 上午 10:40:51  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl.service;


import com.ccthanking.business.dtgl.gzhgl.vo.GzhVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> GzhService.java </p>
 * <p> 功能：告知会 </p>
 *
 * <p><a href="GzhService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-27
 * 
 */
public interface GzhService extends IBaseService<GzhVO, String> {

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
	 * 根据工程编号查询对应的告知会
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String querySingleCondition(String json) throws Exception;
    
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
	 * 查询未完成的项目名称和建设单位
	 * @return
	 */
	public String queryUndoneProject(String json) throws Exception ;
	
	/**
	 * 根据工程，查询工程下面的人员
	 * @param json 工程编号
	 * @return
	 */
	public String querySgPersonByGC(String json);
	
	

}
