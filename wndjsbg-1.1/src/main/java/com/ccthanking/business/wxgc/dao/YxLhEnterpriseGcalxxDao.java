/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.qyxx.YxLhEnterpriseGcalxxDao.java
 * 创建日期： 2015-01-28 上午 09:18:45
 * 功能：   绿化企业工程案例信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:18:45  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxLhEnterpriseGcalxxVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxLhEnterpriseGcalxxDao.java </p>
 * <p> 功能接口：绿化企业工程案例信息 </p>
 *
 * <p><a href="YxLhEnterpriseGcalxxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

public interface YxLhEnterpriseGcalxxDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxLhEnterpriseGcalxxVO vo, Map map);

	public boolean deletAlgcxxByLhUid(String id);
}
