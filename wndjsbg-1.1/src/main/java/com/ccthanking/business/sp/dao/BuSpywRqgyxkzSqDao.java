/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.BuSpywRqgyxkzSqDao.java
 * 创建日期： 2014-11-11 下午 04:54:18
 * 功能：   燃气供应许可证
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-11 下午 04:54:18  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywRqgyxkzSqDao.java </p>
 * <p> 功能接口：燃气供应许可证 </p>
 *
 * <p><a href="BuSpywRqgyxkzSqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-11
 * 
 */

public interface BuSpywRqgyxkzSqDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json) throws Exception ;

	/**
	 * 新增数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String insert(String json) throws Exception ;
	public String findByZjId(String ywlz) throws Exception;
	/**
	 * 修改数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String update(String json) throws Exception ;
	// 可在此加入业务独特的服务接口
}
