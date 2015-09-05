/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.wxy.WxyWjDao.java
 * 创建日期： 2015-04-29 下午 03:06:53
 * 功能：   网架及索膜结构安装工程提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-29 下午 03:06:53  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.wxy.vo.WxyWjVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> WxyWjDao.java </p>
 * <p> 功能接口：网架及索膜结构安装工程提示单 </p>
 *
 * <p><a href="WxyWjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-29
 * 
 */

public interface WxyWjDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, WxyWjVO vo, Map map);

	
	/**
	 * 退回
	 * @param map
	 * @return
	 */
	public boolean tuiHui(String gcId);
	// 可在此加入业务独特的服务接口
}
