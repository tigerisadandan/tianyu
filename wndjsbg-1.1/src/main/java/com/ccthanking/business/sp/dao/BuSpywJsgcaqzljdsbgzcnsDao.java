/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywJsgcaqzljdsbgzcnsDao.java
 * 创建日期： 2014-06-10 下午 02:52:43
 * 功能：   安全、质量监督申报告知承诺书
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-10 下午 02:52:43  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywJsgcaqzljdsbgzcnsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJsgcaqzljdsbgzcnsDao.java </p>
 * <p> 功能接口：安全、质量监督申报告知承诺书 </p>
 *
 * <p><a href="BuSpywJsgcaqzljdsbgzcnsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-10
 * 
 */

public interface BuSpywJsgcaqzljdsbgzcnsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJsgcaqzljdsbgzcnsVO vo, Map map);

	// 可在此加入业务独特的服务接口
	public String getIdByYwlzuid(String ywlzuid);
}
