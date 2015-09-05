/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcnsZyssSpDao.java
 * 创建日期： 2014-06-17 下午 02:10:09
 * 功能：   建设项目环境影响评价报告表（书）审批--主要设施
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:10:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsZyssSpVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywHjyxdjgzcnsZyssSpDao.java </p>
 * <p> 功能接口：建设项目环境影响评价报告表（书）审批--主要设施 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsZyssSpDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */

public interface BuSpywHjyxdjgzcnsZyssSpDao extends BsBaseDaoable {

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
