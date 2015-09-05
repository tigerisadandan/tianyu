/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywJgysbaDao.java
 * 创建日期： 2014-06-09 下午 02:35:53
 * 功能：   竣工验收备案
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 02:35:53  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywJgysbaVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJgysbaDao.java </p>
 * <p> 功能接口：竣工验收备案 </p>
 *
 * <p><a href="BuSpywJgysbaDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

public interface BuSpywJgysbaDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJgysbaVO vo, Map map);

	// 可在此加入业务独特的服务接口
	public String getIdByYwlzuid(String ywlzuid);
}
