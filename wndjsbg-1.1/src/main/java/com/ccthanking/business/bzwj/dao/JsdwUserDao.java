/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sp.JsdwUserDao.java
 * 创建日期： 2015-04-27 上午 11:38:59
 * 功能：   建设单位用户
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-27 上午 11:38:59  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao;

import java.util.Map;

import com.ccthanking.business.common.vo.JsdwUserVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JsdwUserDao.java </p>
 * <p> 功能接口：建设单位用户 </p>
 *
 * <p><a href="JsdwUserDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-27
 * 
 */

public interface JsdwUserDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JsdwUserVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
