/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.bu_spyw_xqxxqtclzxjjftsq_clDao.java
 * 创建日期： 2014-10-28 下午 04:49:05
 * 功能：   无锡新区新型墙体材料专项基金返退申请_材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 下午 04:49:05  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> bu_spyw_xqxxqtclzxjjftsq_clDao.java </p>
 * <p> 功能接口：无锡新区新型墙体材料专项基金返退申请_材料 </p>
 *
 * <p><a href="bu_spyw_xqxxqtclzxjjftsq_clDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */

public interface bu_spyw_xqxxqtclzxjjftsq_clDao extends BsBaseDaoable {

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

	// 可在此加入业务独特的服务接口
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
	
	/**
	 * 修改数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryUpdate(String id) throws Exception ;
}
