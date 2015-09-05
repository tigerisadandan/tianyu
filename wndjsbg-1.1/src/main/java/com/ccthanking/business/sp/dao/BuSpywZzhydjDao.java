/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywZzhydjDao.java
 * 创建日期： 2014-05-27 下午 04:27:16
 * 功能：   资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 04:27:16  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywZzhydjVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywZzhydjDao.java </p>
 * <p> 功能接口：资质 </p>
 *
 * <p><a href="BuSpywZzhydjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

public interface BuSpywZzhydjDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywZzhydjVO vo, Map map);


	// 可在此加入业务独特的服务接口
	public String getIdByYwlzuid(String ywlzuid);
}
