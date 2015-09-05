/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.tzgl.TongzhiDao.java
 * 创建日期： 2015-05-15 下午 03:34:18
 * 功能：   通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-15 下午 03:34:18  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.tzgl.dao;

import java.util.Map;

import com.ccthanking.business.tzgl.vo.TongzhiVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> TongzhiDao.java </p>
 * <p> 功能接口：通知管理 </p>
 *
 * <p><a href="TongzhiDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-15
 * 
 */

public interface TongzhiDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, TongzhiVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
