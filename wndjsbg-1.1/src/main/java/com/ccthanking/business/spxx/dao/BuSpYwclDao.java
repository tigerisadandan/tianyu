/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.spxx.BuSpYwclDao.java
 * 创建日期： 2014-06-18 上午 10:56:57
 * 功能：   审批业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-18 上午 10:56:57  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao;

import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpYwclVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpYwclDao.java </p>
 * <p> 功能接口：审批业务材料 </p>
 *
 * <p><a href="BuSpYwclDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-18
 * 
 */

public interface BuSpYwclDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpYwclVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	/**
	 * 获取排序号
	 */
	public String queryMaxXh(String ywid);
}
