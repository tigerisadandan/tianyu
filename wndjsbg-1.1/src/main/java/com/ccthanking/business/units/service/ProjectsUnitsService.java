/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    units.service.ProjectsUnitsService.java
 * 创建日期： 2014-10-17 下午 01:43:10
 * 功能：    接口：单位工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-17 下午 01:43:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.units.service;



import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ProjectsUnitsService.java </p>
 * <p> 功能：单位工程 </p>
 *
 * <p><a href="ProjectsUnitsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-17
 * 
 */
public interface ProjectsUnitsService extends IBaseService<ProjectsUnitsVO, String> {

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
    
    String querybyIds(String json) throws Exception;
    
    String querybygcid(String id,String type,String cUid)throws Exception;
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

}
