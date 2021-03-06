/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcnsZyssDao.java
 * 创建日期： 2014-06-16 上午 09:33:02
 * 功能：   环境影响登记告知承诺主要设施
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-16 上午 09:33:02  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsZyssVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywHjyxdjgzcnsZyssDao.java </p>
 * <p> 功能接口：环境影响登记告知承诺主要设施 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsZyssDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-16
 * 
 */

public interface BuSpywHjyxdjgzcnsZyssDao extends BsBaseDaoable {
	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json) throws Exception ;
	public String queryZyssUpdate(String json) throws Exception ;

	public List<Map<String, String>> find(String id) throws Exception;


	// 可在此加入业务独特的服务接口
	/**
	 * 新增数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String insert(String json) throws Exception ;
	
	/**
	 * 修改数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String update(String json) throws Exception ;
}
