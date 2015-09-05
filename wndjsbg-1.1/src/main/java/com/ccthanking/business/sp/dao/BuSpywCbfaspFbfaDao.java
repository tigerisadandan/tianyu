/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspFbfaDao.java
 * 创建日期： 2014-05-28 下午 04:30:30
 * 功能：   资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 下午 04:30:30  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywCbfaspFbfaVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywCbfaspFbfaDao.java </p>
 * <p> 功能接口：资质 </p>
 *
 * <p><a href="BuSpywCbfaspFbfaDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

public interface BuSpywCbfaspFbfaDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywCbfaspFbfaVO vo, Map map);
	// 可在此加入业务独特的服务接口
	
	public List<?> find(String id) throws Exception;
	/**
	 * 根据初步发包方案UID删除所有方案数据
	 */
	String deleteFasps(String uid) throws Exception;
	
	
	/**
	 * 获取发包方式列表
	 * @param cbf_uid
	 * @return
	 * @throws Exception
	 */
	String queryFbfs(String cbf_uid) throws Exception;
}
