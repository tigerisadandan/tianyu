/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.wxy.WxyGdmbDao.java
 * 创建日期： 2015-04-23 下午 02:13:51
 * 功能：   高大模板提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 下午 02:13:51  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.wxy.vo.WxyGdmbVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> WxyGdmbDao.java </p>
 * <p> 功能接口：高大模板提示单 </p>
 *
 * <p><a href="WxyGdmbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

public interface WxyGdmbDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, WxyGdmbVO vo, Map map);

	/**
	 * 退回
	 * @param map
	 * @return
	 */
	public boolean tuiHui(String gcId);
	// 可在此加入业务独特的服务接口
}