/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.wxgc.YxYxcbsDao.java
 * 创建日期： 2014-12-23 下午 01:31:30
 * 功能：   预选承包商
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:31:30  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxLhEnterpriseVO;
import com.ccthanking.business.wxgc.vo.YxYxcbsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxYxcbsDao.java </p>
 * <p> 功能接口：预选承包商 </p>
 *
 * <p><a href="YxYxcbsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

public interface YxYxcbsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxYxcbsVO vo, Map map);
	public String queryspjlCondition(String json, YxYxcbsVO vo, Map map);
	
	public String queryCondition(String json);

	// 可在此加入业务独特的服务接口
	
	public List<Map<String,String>> cbsgctypeList(Map map);
	
}
