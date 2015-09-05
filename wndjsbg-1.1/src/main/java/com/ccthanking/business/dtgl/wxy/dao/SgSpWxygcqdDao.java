/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.wxy.SgSpWxygcqdDao.java
 * 创建日期： 2015-04-23 上午 11:44:17
 * 功能：   较大危险源工程清单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 上午 11:44:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.wxy.vo.SgSpWxygcqdVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgSpWxygcqdDao.java </p>
 * <p> 功能接口：较大危险源工程清单 </p>
 *
 * <p><a href="SgSpWxygcqdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

public interface SgSpWxygcqdDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgSpWxygcqdVO vo, Map map);
	
	public String queryWxYBh(String json, SgSpWxygcqdVO vo, Map map);
	
	public String queryGcStatus(Map json, SgSpWxygcqdVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
