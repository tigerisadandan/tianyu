/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.dtgl.jxsb.JxsbDsjjDao.java
 * 创建日期： 2014-12-25 下午 01:18:44
 * 功能：   机械设备顶升加节记录表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-25 下午 01:18:44  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.JxsbDsjjVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JxsbDsjjDao.java </p>
 * <p> 功能接口：机械设备顶升加节记录表 </p>
 *
 * <p><a href="JxsbDsjjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-25
 * 
 */

public interface JxsbDsjjDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JxsbDsjjVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
