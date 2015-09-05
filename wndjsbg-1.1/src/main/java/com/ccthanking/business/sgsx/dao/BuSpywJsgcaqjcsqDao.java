/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sgsx.BuSpywJsgcaqjcsqDao.java
 * 创建日期： 2015-04-03 下午 03:13:08
 * 功能：   sg_《建设工程安全监督申报表》
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-03 下午 03:13:08  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgcaqjcsqVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJsgcaqjcsqDao.java </p>
 * <p> 功能接口：sg_《建设工程安全监督申报表》 </p>
 *
 * <p><a href="BuSpywJsgcaqjcsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-03
 * 
 */

public interface BuSpywJsgcaqjcsqDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJsgcaqjcsqVO vo, Map map);
	
	public String queryByGcId(String json, BuSpywJsgcaqjcsqVO vo, Map map);
	
	public String queryDtgc(String json, BuSpywJsgcaqjcsqVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
