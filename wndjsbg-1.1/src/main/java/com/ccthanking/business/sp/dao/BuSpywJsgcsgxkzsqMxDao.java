/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywJsgcsgxkzsqMxDao.java
 * 创建日期： 2014-05-27 下午 03:05:51
 * 功能：   施工许可申请表-明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 03:05:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqMxVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJsgcsgxkzsqMxDao.java </p>
 * <p> 功能接口：施工许可申请表-明细 </p>
 *
 * <p><a href="BuSpywJsgcsgxkzsqMxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

public interface BuSpywJsgcsgxkzsqMxDao extends BsBaseDaoable {

	/**    
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @throws Exception 
	 * @since v1.00
	 */
	public List<Map<String, String>> find(String id) throws Exception;
	public String queryCondition(String json) throws Exception;
	public String queryUpdate(String id) throws Exception;

	// 可在此加入业务独特的服务接口
}
