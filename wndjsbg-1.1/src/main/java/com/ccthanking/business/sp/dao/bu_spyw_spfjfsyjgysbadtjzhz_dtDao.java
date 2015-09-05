/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.bu_spyw_spfjfsyjgysbadtjzhz_dtDao.java
 * 创建日期： 2014-11-06 下午 03:57:43
 * 功能：   商品房交付使用竣工验收备案单体建筑汇总_单体
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:57:43  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzDtVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> bu_spyw_spfjfsyjgysbadtjzhz_dtDao.java </p>
 * <p> 功能接口：商品房交付使用竣工验收备案单体建筑汇总_单体 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysbadtjzhz_dtDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

public interface bu_spyw_spfjfsyjgysbadtjzhz_dtDao extends BsBaseDaoable {

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
	
	public String queryUpdate(String id) throws Exception ;
	// 可在此加入业务独特的服务接口
}
