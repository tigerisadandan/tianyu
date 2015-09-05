/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.bzwj.BuSpywJyfwfsfJsgcjktzdDao.java
 * 创建日期： 2014-11-18 下午 03:02:34
 * 功能：   十、交易服务费收费
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 03:02:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.bzwj.BuSpywJyfwfsfJsgcjktzdVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJyfwfsfJsgcjktzdDao.java </p>
 * <p> 功能接口：十、交易服务费收费 </p>
 *
 * <p><a href="BuSpywJyfwfsfJsgcjktzdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

public interface BuSpywJyfwfsfJsgcjktzdDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJyfwfsfJsgcjktzdVO vo, Map map);

	public List<Map<String, String>> queryTpfFileNameByScxmid(String id);
	
	public String  queryByLzbz(String id);
	
	public String  getCount();
	// 可在此加入业务独特的服务接口
}
