/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.ProjectsService.java
 * 创建日期： 2014-07-02 下午 12:00:58
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:00:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service;


import com.ccthanking.business.project.vo.ProjectsVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ProjectsService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="ProjectsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */
public interface ProjectsService extends IBaseService<ProjectsVO, String> {

	/**
     * 项目分期查询.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json) throws Exception;
    /**
     * 环保项目查询.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryHb(String json) throws Exception;
  
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

    
    public String getProjectCount(String type) throws Exception;
}
