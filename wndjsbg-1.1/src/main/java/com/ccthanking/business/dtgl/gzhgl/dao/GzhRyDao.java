/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.gzhgl.GzhRyDao.java
 * 创建日期： 2015-05-31 下午 04:34:35
 * 功能：   告知会参与人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-31 下午 04:34:35  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.gzhgl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.gzhgl.vo.GzhRyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> GzhRyDao.java </p>
 * <p> 功能接口：告知会参与人员 </p>
 *
 * <p><a href="GzhRyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-31
 * 
 */

public interface GzhRyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, GzhRyVO vo, Map map);
	
	/**
	 * 根据告知会编号，查询对应的项目/单位
	 * @param gzhId
	 * @return
	 */
	public String queryXmDwByGzhId(String json);
	
	/**
	 * 根据告知会编号，查询要参加的人员
	 * @param json
	 * @return
	 */
	public String queryQianDaoRYByGzhId(String json);
	

	// 可在此加入业务独特的服务接口
}
