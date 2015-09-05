/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.gongcheng.ProjectsGongchengDao.java
 * 创建日期： 2014-10-16 下午 04:22:49
 * 功能：   施工内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-16 下午 04:22:49  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.gongcheng.dao;

import java.util.Map;

import com.ccthanking.business.project.vo.ProjectsGongchengVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ProjectsGongchengDao.java </p>
 * <p> 功能接口：施工内容 </p>
 *
 * <p><a href="ProjectsGongchengDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-16
 * 
 */

public interface ProjectsGongchengDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, ProjectsGongchengVO vo, Map map);
	
	public String queryCondition2(String json,ProjectsGongchengVO vo, Map map);
	
	public String queryCondition3(String json,String condition3,String AJZ_UID);
	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String querygc(String json, ProjectsGongchengVO vo,String gcid);
	
	public String queryAllGc(String json, ProjectsGongchengVO vo, Map map);
	
	public String queryStatusNums(String projects_uid);
	
	public String getAJY();
	
	public String getZJY();
	
	public String queryAjDept();
	
	

	// 可在此加入业务独特的服务接口
}
