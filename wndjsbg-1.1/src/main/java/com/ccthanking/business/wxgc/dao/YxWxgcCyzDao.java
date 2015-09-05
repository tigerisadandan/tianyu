/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.wxgc.YxWxgcCyzDao.java
 * 创建日期： 2014-12-22 下午 02:58:58
 * 功能：   微型工程参与者
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-22 下午 02:58:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxWxgcCyzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxWxgcCyzDao.java </p>
 * <p> 功能接口：微型工程参与者 </p>
 *
 * <p><a href="YxWxgcCyzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-22
 * 
 */

public interface YxWxgcCyzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxWxgcCyzVO vo, Map map);

	public List<Map<String ,String>> wxgcCyzList(Map map);
}
