/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.wxgc.YxGcTypeDao.java
 * 创建日期： 2015-01-06 下午 10:29:23
 * 功能：   微型工程类型维护
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-06 下午 10:29:23  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxGcTypeVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxGcTypeDao.java </p>
 * <p> 功能接口：微型工程类型维护 </p>
 *
 * <p><a href="YxGcTypeDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-06
 * 
 */

public interface YxGcTypeDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxGcTypeVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
