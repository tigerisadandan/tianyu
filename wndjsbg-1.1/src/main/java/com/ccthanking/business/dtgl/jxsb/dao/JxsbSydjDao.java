/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.dtgl.jxsb.JxsbSydjDao.java
 * 创建日期： 2014-12-26 上午 10:44:21
 * 功能：   机械设备使用登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-26 上午 10:44:21  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ccthanking.business.dtgl.azqy.vo.JxsbSydjVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JxsbSydjDao.java </p>
 * <p> 功能接口：机械设备使用登记 </p>
 *
 * <p><a href="JxsbSydjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-26
 * 
 */

public interface JxsbSydjDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JxsbSydjVO vo, Map map,HttpServletRequest request);
	
	public String queryList(String json, JxsbSydjVO vo, Map map);
	public String queryJxsb(String json, JxsbSydjVO vo, Map map);
	public String queryRy(String json, JxsbSydjVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
