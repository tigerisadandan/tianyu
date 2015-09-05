/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.ywlz.BuSpYwlzBzDao.java
 * 创建日期： 2014-06-19 下午 04:49:00
 * 功能：   审批业务流转步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:49:00  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.ywlz.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.ywlz.vo.BuSpYwlzBzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpYwlzBzDao.java </p>
 * <p> 功能接口：审批业务流转步骤 </p>
 *
 * <p><a href="BuSpYwlzBzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

public interface BuSpYwlzBzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpYwlzBzVO vo, Map map);
	
	public String queryJlRy(String json, BuSpYwlzBzVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	public List<?> getSpYwLzBzList(String ywlzuid,Date beginDate,String status,String chulijiegou,String chuliren);
	
	public List<?> geSpYwClList(String ywlzuid);
	
	public String insertBz(String ywlzUid) throws Exception;
	
	public String updateSH(String ywlz_uid,String xmid) throws Exception;

}
