/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcns2SpDao.java
 * 创建日期： 2014-06-17 下午 02:06:22
 * 功能：   建设项目环境影响评价报告表（书）审批--附件三
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:06:22  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcns2SpVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywHjyxdjgzcns2SpDao.java </p>
 * <p> 功能接口：建设项目环境影响评价报告表（书）审批--附件三 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcns2SpDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */

public interface BuSpywHjyxdjgzcns2SpDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json);
	 /**
     * 根据条件查询附件记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryReadFile(String id) throws Exception;
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
