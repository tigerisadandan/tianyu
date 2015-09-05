/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.qyxx.YxKcEnterpriseGcalxxDao.java
 * 创建日期： 2015-01-28 下午 12:09:51
 * 功能：   勘察企业工程案例信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 12:09:51  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxKcEnterpriseGcalxxVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxKcEnterpriseGcalxxDao.java </p>
 * <p> 功能接口：勘察企业工程案例信息 </p>
 *
 * <p><a href="YxKcEnterpriseGcalxxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

public interface YxKcEnterpriseGcalxxDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxKcEnterpriseGcalxxVO vo, Map map);

	public boolean deletAlgcxxByKcUid(String id);
}
