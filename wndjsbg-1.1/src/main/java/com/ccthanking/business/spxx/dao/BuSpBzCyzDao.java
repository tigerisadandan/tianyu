/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.spxx.BuSpBzCyzDao.java
 * 创建日期： 2014-06-13 下午 04:43:40
 * 功能：   业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:43:40  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao;

import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpBzCyzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpBzCyzDao.java </p>
 * <p> 功能接口：业务材料 </p>
 *
 * <p><a href="BuSpBzCyzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

public interface BuSpBzCyzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpBzCyzVO vo, Map map);

	// 可在此加入业务独特的服务接口
	

	/**
	 * 为步骤信息提供用户选择
	 * @param bz_uid
	 * @return
	 */
	public String getUsers(String bz_uid,String uname);
	
	/**
	 * 添加参与者信息
	 * @param json
	 * @return
	 */
	public String insertUsers(String json);
	
	/**
	 * 查询步骤参与者信息
	 * @param bz_uid
	 * @return
	 */
	public String queryBzCyz(String bz_uid);
	
}
