/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.spxx.BuSpBzThlyDao.java
 * 创建日期： 2014-06-29 上午 10:05:44
 * 功能：   步骤处理时的退回理由
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-29 上午 10:05:44  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao;

import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpBzThlyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpBzThlyDao.java </p>
 * <p> 功能接口：步骤处理时的退回理由 </p>
 *
 * <p><a href="BuSpBzThlyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-29
 * 
 */

public interface BuSpBzThlyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpBzThlyVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	public String getSpYwxx();
}
