/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.wxgc.YxUserQyDao.java
 * 创建日期： 2015-01-07 上午 09:22:01
 * 功能：   招标人(实施方区域)维护
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-07 上午 09:22:01  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxUserQyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxUserQyDao.java </p>
 * <p> 功能接口：招标人(实施方区域)维护 </p>
 *
 * <p><a href="YxUserQyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-07
 * 
 */

public interface YxUserQyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxUserQyVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
