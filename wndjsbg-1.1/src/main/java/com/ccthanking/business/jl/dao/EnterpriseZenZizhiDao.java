/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.jl.EnterpriseZenZizhiDao.java
 * 创建日期： 2015-01-26 下午 02:35:20
 * 功能：   企业增项资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:35:20  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.jl.vo.EnterpriseZenZizhiVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> EnterpriseZenZizhiDao.java </p>
 * <p> 功能接口：企业增项资质 </p>
 *
 * <p><a href="EnterpriseZenZizhiDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

public interface EnterpriseZenZizhiDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, EnterpriseZenZizhiVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	public String queryListZeng(String uid) throws Exception ;
}
