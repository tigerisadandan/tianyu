/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.project.ProjectsDao.java
 * 创建日期： 2014-07-02 下午 12:00:58
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:00:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ProjectsDao.java </p>
 * <p> 功能接口：项目 </p>
 *
 * <p><a href="ProjectsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */

public interface ProjectsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, ProjectsVO vo, Map map);
	// 可在此加入业务独特的服务接口
	public String queryHb(String json, ProjectsVO vo, Map map);
	
    public List<?> getProjectCount(String type);
    
    public void updateUnitsCode(String Sql);
    
    public String getCode(String code,String jscompany,String projects_type,int length);
}
