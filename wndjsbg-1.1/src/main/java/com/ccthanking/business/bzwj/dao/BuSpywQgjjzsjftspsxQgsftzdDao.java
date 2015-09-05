/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.bzwj.BuSpywQgjjzsjftspsxQgsftzdDao.java
 * 创建日期： 2014-11-07 上午 09:41:47
 * 功能：   八墙体材料专项基金预收款收费通知单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-07 上午 09:41:47  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.bzwj.BuSpywQgjjzsjftspsxQgsftzdVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.BsBaseDaoable;
import com.ccthanking.framework.handle.ActionContext;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;


/**
 * <p> BuSpywQgjjzsjftspsxQgsftzdDao.java </p>
 * <p> 功能接口：八墙体材料专项基金预收款收费通知单 </p>
 *
 * <p><a href="BuSpywQgjjzsjftspsxQgsftzdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-07
 * 
 */

public interface BuSpywQgjjzsjftspsxQgsftzdDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywQgjjzsjftspsxQgsftzdVO vo, Map map);
	
	public String getCount();
	
	 public List<Map<String, String>> queryTpfFileNameByScxmid(String id);
	
	 public String queryByLzbz(String json) ;

	// 可在此加入业务独特的服务接口
}
