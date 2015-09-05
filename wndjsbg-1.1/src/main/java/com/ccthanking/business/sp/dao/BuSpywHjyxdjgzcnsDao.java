/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcnsDao.java
 * 创建日期： 2014-06-13 下午 05:31:52
 * 功能：   环境影响登记告知承诺
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 05:31:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywHjyxdjgzcnsDao.java </p>
 * <p> 功能接口：环境影响登记告知承诺 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

public interface BuSpywHjyxdjgzcnsDao extends BsBaseDaoable {

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
	/**
	 * 条件查询附件.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryReadFile(String id) throws Exception ;

	
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
	public String insert(String json,Map files) throws Exception ;
	
	/**
	 * 修改数据.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String update(String json,Map files) throws Exception ;
}
