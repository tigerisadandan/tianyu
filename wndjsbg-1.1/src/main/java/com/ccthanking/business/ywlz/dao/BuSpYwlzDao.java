/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.ywlz.BuSpYwlzDao.java
 * 创建日期： 2014-06-19 下午 04:51:04
 * 功能：   审批业务流转实例
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:51:04  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.ywlz.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.ywlz.vo.BuSpYwlzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpYwlzDao.java </p>
 * <p> 功能接口：审批业务流转实例 </p>
 *
 * <p><a href="BuSpYwlzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

public interface BuSpYwlzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpYwlzVO vo, Map map);
	
	public String queryGcPrint(String json, BuSpYwlzVO vo, Map map);
	
	public String queryJzPrint(String json, BuSpYwlzVO vo, Map map);
	
	public String querySzPrint(String json, BuSpYwlzVO vo, Map map);
	 
	public String queryClType(String json, BuSpYwlzVO vo, Map map);
	
	public String queryByProjectsid(String json);

	// 可在此加入业务独特的服务接口
    /**
     * 获取材料记录.
     */

    public String queryYwcl(Map map) throws Exception;
    public String queryYwclFromLz(Map map) throws Exception;
    
    
    public List<?> getSpCount(String spywuid);
    
    public List<?> getAllDsCount();
    public List<?> ywlzList (String ywlzuid);
}
