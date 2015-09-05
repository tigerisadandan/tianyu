/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.jxsb.JxsbCxgcDao.java
 * 创建日期： 2015-01-28 下午 06:05:57
 * 功能：   机械设备拆卸过程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 06:05:57  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgcVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JxsbCxgcDao.java </p>
 * <p> 功能接口：机械设备拆卸过程 </p>
 *
 * <p><a href="JxsbCxgcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

public interface JxsbCxgcDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JxsbCxgcVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
