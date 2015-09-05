/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.wxgc.YxCbsGcTypeDao.java
 * 创建日期： 2014-12-25 下午 04:33:18
 * 功能：   预选承包商和微型工程类型关联关系
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-25 下午 04:33:18  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxCbsGcTypeVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxCbsGcTypeDao.java </p>
 * <p> 功能接口：预选承包商和微型工程类型关联关系 </p>
 *
 * <p><a href="YxCbsGcTypeDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-25
 * 
 */

public interface YxCbsGcTypeDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxCbsGcTypeVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
