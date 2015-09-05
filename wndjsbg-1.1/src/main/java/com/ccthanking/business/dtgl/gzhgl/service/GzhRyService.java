/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhRyService.java
 * 创建日期： 2015-05-31 下午 04:34:35
 * 功能：    接口：告知会参与人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-31 下午 04:34:35  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl.service;


import com.ccthanking.business.dtgl.gzhgl.vo.GzhRyVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> GzhRyService.java </p>
 * <p> 功能：告知会参与人员 </p>
 *
 * <p><a href="GzhRyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-31
 * 
 */
public interface GzhRyService extends IBaseService<GzhRyVO, String> {

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
     * 根据查询结果添加.
     * @param json
     * @return
     * @throws Exception
     */
    String insertByQuery(String gzhId,String pIds1,String personIds,String type) throws Exception;
    
    /**
	 * 根据告知会编号，查询对应的项目/单位
	 * @param gzhId
	 * @return
	 */
	public String queryXmDwByGzhId(String json) throws Exception;
	
	/**
	 * 根据告知会编号，查询要参加的人员
	 * @param json
	 * @return
	 */
	public String queryQianDaoRYByGzhId(String json) throws Exception;

}
