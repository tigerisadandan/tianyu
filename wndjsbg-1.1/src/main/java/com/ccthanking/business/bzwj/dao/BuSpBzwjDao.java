/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.bzwj.BuSpBzwjDao.java
 * 创建日期： 2014-10-28 上午 11:02:10
 * 功能：   步骤文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 上午 11:02:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao;

import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpBzwjVO;
import com.ccthanking.business.spxx.vo.BuSpYwlzBzwjVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpBzwjDao.java </p>
 * <p> 功能接口：步骤文件 </p>
 *
 * <p><a href="BuSpBzwjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */

public interface BuSpBzwjDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpBzwjVO vo, Map map);
	public String querylzbzwj(String json, BuSpYwlzBzwjVO vo, Map map);
	
	
	// 可在此加入业务独特的服务接口
}
