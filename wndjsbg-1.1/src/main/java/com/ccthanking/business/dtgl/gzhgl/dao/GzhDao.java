/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.gzhgl.GzhDao.java
 * 创建日期： 2015-05-27 上午 10:40:51
 * 功能：   告知会
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-27 上午 10:40:51  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.gzhgl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.gzhgl.vo.GzhVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> GzhDao.java </p>
 * <p> 功能接口：告知会 </p>
 *
 * <p><a href="GzhDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-27
 * 
 */

public interface GzhDao extends BsBaseDaoable {

	/**
	 * 条件查询
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, GzhVO vo, Map map);
	
	/**
	 * 根据工程编号查询对应的告知会
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String querySingleCondition(String json);
	
	/**
	 * 查询未完成的项目名称和建设单位
	 * @return
	 */
	public String queryUndoneProject(String json);
	
	
	/**
	 * 根据工程，查询工程下面的人员
	 * @param json 工程编号
	 * @return
	 */
	public String querySgPersonByGC(String json);

	// 可在此加入业务独特的服务接口
}
