/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgbb.SgbbRyDao.java
 * 创建日期： 2014-04-24 下午 07:04:31
 * 功能：   施工报备人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-24 下午 07:04:31  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgbb.dao;

import java.sql.Connection;
import java.util.Map;

import com.ccthanking.business.sgbb.vo.SgbbRyVO;
import com.ccthanking.business.sgbb.vo.SgyStatusInfoVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgbbRyDao.java </p>
 * <p> 功能接口：施工报备人员 </p>
 *
 * <p><a href="SgbbRyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-24
 * 
 */

public interface SgbbRyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgbbRyVO vo, Map map);

	// 可在此加入业务独特的服务接口
	String queryBbry(String bb_uid);
	
	/**
	 * 查询人员的锁定情况
	 * @param sg_person_uid
	 * @param wangwei_uid
	 * @param sgbb_uid
	 * @return
	 */
	public String queryLockUser(String sg_person_uid,String gangwei_uid,String sgbb_uid);
	
	
	public void deleteByUids(String uids,String sgbb_uid);
	
	
	public void syncUpdateRyxx(String uid);
	
	public SgyStatusInfoVO insertSgbbStatus(Connection conn,SgbbRyVO vo,String reason);
	
	public boolean queryPersonUid(String ryUid,String gangwei,String pUid);
}
