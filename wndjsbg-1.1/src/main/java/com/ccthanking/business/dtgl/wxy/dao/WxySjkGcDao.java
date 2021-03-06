/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.wxy.WxySjkGcDao.java
 * 创建日期： 2015-05-08 下午 04:33:53
 * 功能：   深基坑过程管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-08 下午 04:33:53  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.wxy.vo.WxySjkGcVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> WxySjkGcDao.java </p>
 * <p> 功能接口：深基坑过程管理 </p>
 *
 * <p><a href="WxySjkGcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-08
 * 
 */

public interface WxySjkGcDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, WxySjkGcVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
