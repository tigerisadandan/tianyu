/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.wxgc.YxWxgcPbfsDao.java
 * 创建日期： 2015-03-11 下午 04:35:38
 * 功能：   微型工程评标方式
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-11 下午 04:35:38  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxWxgcPbfsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxWxgcPbfsDao.java </p>
 * <p> 功能接口：微型工程评标方式 </p>
 *
 * <p><a href="YxWxgcPbfsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-03-11
 * 
 */

public interface YxWxgcPbfsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxWxgcPbfsVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
