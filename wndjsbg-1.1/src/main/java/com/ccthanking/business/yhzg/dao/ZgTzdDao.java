/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.yhzg.ZgTzdDao.java
 * 创建日期： 2015-04-21 下午 02:41:29
 * 功能：   整改通知单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 02:41:29  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.dtgl.yhzg.vo.ZgTzdVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ZgTzdDao.java </p>
 * <p> 功能接口：整改通知单 </p>
 *
 * <p><a href="ZgTzdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

public interface ZgTzdDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition01(String json, ZgTzdVO vo, Map map);
	
	public String queryCondition(String json,Map map);
	
	public String queryZGD2(String zhenggai, String deptUid, String before,String after,String json);		

	public String queryUid(String json);

	public String getCode(String zglx,int year);

	public String queryForm(String msg);

	public String getJtCount(String msg);

	public String getQtCount(String msg);

	public String querySh(String msg);

	public List<?> queryPrint(String tzduid);

	public String setScore(String tzdUid);
	
	public String delScore(String tzdUid);
	
	public String queryScore(String tzdUid);
	// 可在此加入业务独特的服务接口
	public String getDeptName(String ORGANIZE_UID);
}
