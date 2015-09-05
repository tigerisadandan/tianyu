/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywYwsrwsqDao.java
 * 创建日期： 2014-06-04 下午 05:30:51
 * 功能：   雨污水入网申请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-04 下午 05:30:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywYwsrwsqVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywYwsrwsqDao.java </p>
 * <p> 功能接口：雨污水入网申请 </p>
 *
 * <p><a href="BuSpywYwsrwsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-04
 * 
 */

public interface BuSpywYwsrwsqDao extends BsBaseDaoable {
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
