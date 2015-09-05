/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspDao.java
 * 创建日期： 2014-05-28 上午 10:34:34
 * 功能：   资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 上午 10:34:34  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywCbfaspDao.java </p>
 * <p> 功能接口：资质 </p>
 *
 * <p><a href="BuSpywCbfaspDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

public interface BuSpywCbfaspDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywCbfaspVO vo, Map map);
	public String findByZjId(String ywlz,String ty) throws Exception;
	
	public String findByPerson(String lx,String uid,String bb_code) throws Exception;
	
	public String findByCompany(String lx,String bb_code) throws Exception;
	
	public String getIdByYwlzuid(String ywlzuid,String lx);
	
	// 可在此加入业务独特的服务接口
	public String getNum(int uid) throws Exception;
	
	public String getCount(int uid,String lx) throws Exception;
}
