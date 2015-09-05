/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.jlbb.JlbbJlyDao.java
 * 创建日期： 2015-01-28 上午 09:21:37
 * 功能：   监理项目的人员报备信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:21:37  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jlbb.dao;

import java.sql.Connection;
import java.util.Map;

import com.ccthanking.business.dtgl.jl.vo.JlbbJlyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JlbbJlyDao.java </p>
 * <p> 功能接口：监理项目的人员报备信息 </p>
 *
 * <p><a href="JlbbJlyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

public interface JlbbJlyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JlbbJlyVO vo, Map map);
	
	
	//public JlyStatusInfoVO insertJlbbStatus(Connection con,JlbbJlyVO vo ,String reason);


	public String checkPerson(String pid ,String gwid, String gcdj);

	// 可在此加入业务独特的服务接口
}
