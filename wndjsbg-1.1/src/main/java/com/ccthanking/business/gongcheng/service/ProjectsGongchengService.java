/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gongcheng.service.ProjectsGongchengService.java
 * 创建日期： 2014-10-16 下午 04:22:49
 * 功能：    接口：施工内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-16 下午 04:22:49  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.gongcheng.service;



import java.util.Map;

import com.ccthanking.business.project.vo.ProjectsGongchengVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ProjectsGongchengService.java </p>
 * <p> 功能：施工内容 </p>
 *
 * <p><a href="ProjectsGongchengService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-16
 * 
 */
public interface ProjectsGongchengService extends IBaseService<ProjectsGongchengVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,Map map) throws Exception;
    
    /**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition2(String json,Map map) throws Exception;
        
    String queryCondition3(String json,String condition,String AJZ_UID) throws Exception;
    
    
    /**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String querygc(String json,String gcid) throws Exception;
    
    String queryAllGc(String json) throws Exception;
    
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

    String queryStatusNums(String projectsid)  throws Exception;
    
    String getAJY() throws Exception;
    
    String getZJY() throws Exception;
    
    String queryAjDept() throws Exception;
}
