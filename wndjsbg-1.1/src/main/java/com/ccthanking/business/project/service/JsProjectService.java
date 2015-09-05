/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.JsProjectService.java
 * 创建日期： 2014-09-02 下午 04:35:32
 * 功能：    接口：项目管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-09-02 下午 04:35:32  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service;


import com.ccthanking.business.project.vo.JsProjectVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JsProjectService.java </p>
 * <p> 功能：项目管理 </p>
 *
 * <p><a href="JsProjectService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-09-02
 * 
 */
public interface JsProjectService extends IBaseService<JsProjectVO, String> {

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
    
    String queryCondition2(String json) throws Exception;
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
    
    public String getProjectCount() throws Exception;

    String queryLX(String json) throws Exception;
    
    String queryLXdetail(String json) throws Exception;
}
