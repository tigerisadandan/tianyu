/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.wxgc.YxUserDao.java
 * 创建日期： 2014-12-23 上午 09:50:28
 * 功能：   消息表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 上午 09:50:28  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxUserVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxUserDao.java </p>
 * <p> 功能接口：微型工程管理系统用户表 </p>
 *
 * <p><a href="YxUserDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

public interface YxUserDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxUserVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
