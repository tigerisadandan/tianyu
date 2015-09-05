/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.wxgc.YxYxcbsXypjDao.java
 * 创建日期： 2015-01-21 上午 10:47:08
 * 功能：   预选承包商信用评价
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-21 上午 10:47:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxYxcbsXypjVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxYxcbsXypjDao.java </p>
 * <p> 功能接口：预选承包商信用评价 </p>
 *
 * <p><a href="YxYxcbsXypjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-21
 * 
 */

public interface YxYxcbsXypjDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxYxcbsXypjVO vo, Map map);
	
	public List<Map<String,String>> queryAllXypjXqCondition(Map map);
	
	public List<Map<String,String>> queryXypjLx(Map map);

	// 可在此加入业务独特的服务接口
}
