/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.project.JsProjectDao.java
 * 创建日期： 2014-09-02 下午 04:35:32
 * 功能：   项目管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-09-02 下午 04:35:32  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.project.vo.JsProjectVO;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JsProjectDao.java </p>
 * <p> 功能接口：项目管理 </p>
 *
 * <p><a href="JsProjectDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-09-02
 * 
 */

public interface JsProjectDao extends BsBaseDaoable {

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
	
	public String queryCondition2(String json, ProjectsVO vo, Map map);

	
	 public List<?> getProjectCount() throws Exception;
	
	public String getProjectsCode(String jscompany,int length) throws Exception;
	// 可在此加入业务独特的服务接口
	public void clean(String jsproject) throws Exception;
	
	public String queryLX(String json) throws Exception;
	
	public String queryLXdetail(String json) throws Exception;
	
	public List<Map<String, String>> lxList(String jsprojectuid) throws Exception;
}
