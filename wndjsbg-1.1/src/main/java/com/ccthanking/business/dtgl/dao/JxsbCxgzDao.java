/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.JxsbCxgzDao.java
 * 创建日期： 2015-01-26 下午 04:31:09
 * 功能：   机械设备拆卸告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 04:31:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JxsbCxgzDao.java </p>
 * <p> 功能接口：机械设备拆卸告知 </p>
 *
 * <p><a href="JxsbCxgzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

public interface JxsbCxgzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JxsbCxgzVO vo, Map map);
	
	public String queryRy(String json, JxsbCxgzVO vo, Map map);

	// 可在此加入业务独特的服务接口
}