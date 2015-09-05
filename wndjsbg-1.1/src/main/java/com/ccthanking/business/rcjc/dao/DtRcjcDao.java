/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.rcjc.DtRcjcDao.java
 * 创建日期： 2015-06-23 下午 05:51:24
 * 功能：   日常检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-06-23 下午 05:51:24  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rcjc.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.rcjc.vo.DtRcjcVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> DtRcjcDao.java </p>
 * <p> 功能接口：日常检查 </p>
 *
 * <p><a href="DtRcjcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongweixiong@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-06-23
 * 
 */

public interface DtRcjcDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, DtRcjcVO vo, Map<?,?> map);

	// 可在此加入业务独特的服务接口
}
