/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.spxx.BuSpBzDao.java
 * 创建日期： 2014-06-13 下午 04:41:17
 * 功能：   业务信息步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:41:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpBzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpBzDao.java </p>
 * <p> 功能接口：业务信息步骤 </p>
 *
 * <p><a href="BuSpBzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

public interface BuSpBzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpBzVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	/**
	 * 获取排序号
	 */
	public String getPxh(String spyw_uid);
	
	/**
	 * 查询审批步骤和审批参与者信息,按审批步骤序号降序排列
	 * spywid  审批业务ID
	 * bzlx    步骤类型
	 * add by long 20140619
	 * */
	public List<?> getSpBzList(String spywid,String bzlx);
	
	
}
