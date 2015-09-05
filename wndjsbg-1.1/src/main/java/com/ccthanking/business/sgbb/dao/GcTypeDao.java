/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgbb.GcTypeDao.java
 * 创建日期： 2014-04-22 下午 04:07:38
 * 功能：   施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-22 下午 04:07:38  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgbb.dao;

import java.util.Map;

import com.ccthanking.business.sgbb.vo.GcTypeVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> GcTypeDao.java </p>
 * <p> 功能接口：施工报备 </p>
 *
 * <p><a href="GcTypeDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-22
 * 
 */

public interface GcTypeDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, GcTypeVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	 /**
     * 根据父类型查询记录工程类型.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryTypelist(String p_type) throws Exception;
    
    /**
     * 查询工程父类型
     * @param gc_type
     * @return
     */
    public String queryPType(String gc_type);
}
