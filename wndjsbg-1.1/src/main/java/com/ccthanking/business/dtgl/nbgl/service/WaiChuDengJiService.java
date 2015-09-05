/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.WaiChuDengJiService.java
 * 创建日期： 2015-05-21 下午 03:32:32
 * 功能：    接口：外出登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-21 下午 03:32:32  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl.service;


import com.ccthanking.business.dtgl.nbgl.vo.WaiChuDengJiVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> WaiChuDengJiService.java </p>
 * <p> 功能：外出登记 </p>
 *
 * <p><a href="WaiChuDengJiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-21
 * 
 */
public interface WaiChuDengJiService extends IBaseService<WaiChuDengJiVO, String> {

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
    boolean delete(String json) throws Exception;
    
    /**
     * 修改外出登记状态为“已返回”
     * @param vo
     * @return
     * @throws Exception
     */
    String updateState(String wcdjId) throws Exception;
    
    /**
	 * 根据外出编号，查询外出登记信息
	 * @param wcdjId
	 * @return
	 */
	public String getById(String wcdjId) throws Exception;

}
