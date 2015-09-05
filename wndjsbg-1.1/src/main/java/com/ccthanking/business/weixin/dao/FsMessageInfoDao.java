/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.weixin.FsMessageInfoDao.java
 * 创建日期： 2014-12-02 上午 11:04:41
 * 功能：   消息表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-02 上午 11:04:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.weixin.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.framework.dao.BsBaseDaoable;
import com.ccthanking.weixin.vo.FsMessageInfoVO;


/**
 * <p> FsMessageInfoDao.java </p>
 * <p> 功能接口：消息表 </p>
 *
 * <p><a href="FsMessageInfoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-02
 * 
 */

public interface FsMessageInfoDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, FsMessageInfoVO vo, Map map);

	// 可在此加入业务独特的服务接口
	public List<?> countMessage(Map map);
}
