/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.units.ProjectsUnitsDao.java
 * 创建日期： 2014-10-17 下午 01:43:10
 * 功能：   单位工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-17 下午 01:43:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.units.dao;

import java.util.Map;

import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ProjectsUnitsDao.java </p>
 * <p> 功能接口：单位工程 </p>
 *
 * <p><a href="ProjectsUnitsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-17
 * 
 */

public interface ProjectsUnitsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, ProjectsUnitsVO vo, Map map);
	
	public String querybyIds(String json, ProjectsUnitsVO vo, Map map);

	// 可在此加入业务独特的服务接口
	public String querybygcid(String id,String type,String cUid);
}
