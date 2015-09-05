/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.bu_spyw_spfjfsyjgysbadtjzhzDao.java
 * 创建日期： 2014-11-06 下午 03:56:47
 * 功能：   商品房交付使用竣工验收备案单体建筑汇总
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:56:47  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> bu_spyw_spfjfsyjgysbadtjzhzDao.java </p>
 * <p> 功能接口：商品房交付使用竣工验收备案单体建筑汇总 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysbadtjzhzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

public interface bu_spyw_spfjfsyjgysbadtjzhzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json);
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
