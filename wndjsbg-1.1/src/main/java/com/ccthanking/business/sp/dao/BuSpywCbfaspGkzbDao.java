/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspGkzbDao.java
 * 创建日期： 2015-05-06 下午 02:07:52
 * 功能：   公开（邀请）招标合同备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-06 下午 02:07:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywCbfaspGkzbVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywCbfaspGkzbDao.java </p>
 * <p> 功能接口：公开（邀请）招标合同备案表 </p>
 *
 * <p><a href="BuSpywCbfaspGkzbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-06
 * 
 */

public interface BuSpywCbfaspGkzbDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywCbfaspGkzbVO vo, Map map);
	public String findByZjId(String ywlz,String ty) throws Exception;

	// 可在此加入业务独特的服务接口
}
