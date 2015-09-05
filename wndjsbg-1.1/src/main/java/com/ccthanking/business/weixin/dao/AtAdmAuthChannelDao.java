/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.weixin.AtAdmAuthChannelDao.java
 * 创建日期： 2014-11-27 上午 10:22:47
 * 功能：   栏目发布权限
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-27 上午 10:22:47  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.weixin.dao;

import java.util.Map;

import com.ccthanking.weixin.vo.AtAdmAuthChannelVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> AtAdmAuthChannelDao.java </p>
 * <p> 功能接口：栏目发布权限 </p>
 *
 * <p><a href="AtAdmAuthChannelDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-27
 * 
 */

public interface AtAdmAuthChannelDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, AtAdmAuthChannelVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	 public void awardToUsers(String channelid, String[] userids, User user) throws Exception;
}
