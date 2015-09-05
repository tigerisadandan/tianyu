/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.dtgl.jxsb.AzCompanyDao.java
 * 创建日期： 2014-12-11 上午 11:06:42
 * 功能：   安装企业
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-11 上午 11:06:42  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.AzCompanyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> AzCompanyDao.java </p>
 * <p> 功能接口：安装企业 </p>
 *
 * <p><a href="AzCompanyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-11
 * 
 */

public interface AzCompanyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, AzCompanyVO vo, Map map);
	
	public String queryDq(String json, AzCompanyVO vo, Map map);
	
	public String queryCount(String json, AzCompanyVO vo, Map map);

	public String queryCode(String json, AzCompanyVO vo, Map map);
	// 可在此加入业务独特的服务接口
}
