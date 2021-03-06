/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgenter.SgScoreDetailDao.java
 * 创建日期： 2014-06-09 上午 09:36:33
 * 功能：   分数主体
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:36:33  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgenter.dao;

import java.util.Map;

import com.ccthanking.business.sgenter.vo.SgScoreDetailVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgScoreDetailDao.java </p>
 * <p> 功能接口：分数主体 </p>
 *
 * <p><a href="SgScoreDetailDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

public interface SgScoreDetailDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgScoreDetailVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
