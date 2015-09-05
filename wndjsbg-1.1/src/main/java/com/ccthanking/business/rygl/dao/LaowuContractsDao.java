/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.rygl.LaowuContractsDao.java
 * 创建日期： 2015-03-23 下午 05:19:19
 * 功能：   劳务合同
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-23 下午 05:19:19  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao;

import java.util.Map;


import com.ccthanking.business.dtgl.rygl.vo.LaowuContractsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> LaowuContractsDao.java </p>
 * <p> 功能接口：劳务合同 </p>
 *
 * <p><a href="LaowuContractsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-23
 * 
 */

public interface LaowuContractsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	
	public String queryCondition(String json, LaowuContractsVO vo, Map map);

	public String queryById(String msg);

	// 可在此加入业务独特的服务接口
}
