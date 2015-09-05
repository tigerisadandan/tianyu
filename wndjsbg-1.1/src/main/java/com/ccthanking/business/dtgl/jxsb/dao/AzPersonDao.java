/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.dtgl.jxsb.AzPersonDao.java
 * 创建日期： 2014-12-15 上午 11:01:01
 * 功能：   安装人员库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 上午 11:01:01  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.AzPersonVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> AzPersonDao.java </p>
 * <p> 功能接口：安装人员库 </p>
 *
 * <p><a href="AzPersonDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

public interface AzPersonDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, AzPersonVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
