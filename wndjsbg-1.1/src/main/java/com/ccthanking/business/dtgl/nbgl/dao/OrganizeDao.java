/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.nbgl.OrganizeDao.java
 * 创建日期： 2015-05-25 下午 09:13:36
 * 功能：   组织结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-25 下午 09:13:36  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.nbgl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.nbgl.vo.OrganizeVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> OrganizeDao.java </p>
 * <p> 功能接口：组织结构 </p>
 *
 * <p><a href="OrganizeDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-25
 * 
 */

public interface OrganizeDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, OrganizeVO vo, Map map);
	
	/**
	 * 条件查询(组织结构管理-树结构数据).<br/>
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryOrganize();
	
	/**
	 * 根据当前登陆用户，查询非登录用户的部门的其他用户（其他部门用户）
	 * @return
	 */
	public String queryRestDeptUser();
	
	/**
	 * 查看当前用户所在部门的其他用户
	 * @return
	 */
	public String queryDeptUserByCurrentUser();

	// 可在此加入业务独特的服务接口
}
