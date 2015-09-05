/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.spxx.BuSpClkDao.java
 * 创建日期： 2014-06-13 上午 11:34:41
 * 功能：   审批业务材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 上午 11:34:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpClkVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpClkDao.java </p>
 * <p> 功能接口：审批业务材料库 </p>
 *
 * <p><a href="BuSpClkDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

public interface BuSpClkDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpClkVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	
	public List<?> getSpClkListByType(Map temmap);
	
	 public String getYwid();
}
