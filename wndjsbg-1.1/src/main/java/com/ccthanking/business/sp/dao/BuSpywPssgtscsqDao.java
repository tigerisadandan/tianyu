/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywPssgtscsqDao.java
 * 创建日期： 2014-06-04 上午 10:13:09
 * 功能：   排水方案施工图审查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-04 上午 10:13:09  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywPssgtscsqVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywPssgtscsqDao.java </p>
 * <p> 功能接口：排水方案施工图审查 </p>
 *
 * <p><a href="BuSpywPssgtscsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-04
 * 
 */

public interface BuSpywPssgtscsqDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json) throws Exception;
	/**
	 * 新增数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String insert(String json) throws Exception;
	/**
	 * 更改数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String update(String json) throws Exception;
	// 可在此加入业务独特的服务接口
	
	public String getIdByYwlzuid(String ywlzuid);
}
