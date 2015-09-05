/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.wxgc.YxKcEnterpriseDao.java
 * 创建日期： 2014-12-23 下午 01:25:43
 * 功能：   勘察设计企业信息库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:25:43  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.Map;

import net.sf.json.JSONObject;

import com.ccthanking.business.wxgc.vo.YxKcEnterpriseVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxKcEnterpriseDao.java </p>
 * <p> 功能接口：勘察设计企业信息库 </p>
 *
 * <p><a href="YxKcEnterpriseDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

public interface YxKcEnterpriseDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxKcEnterpriseVO vo, Map map);
	public String queryOldCondition(String json, YxKcEnterpriseVO vo, Map map);
	public String queryspjlCondition(String json, YxKcEnterpriseVO vo, Map map);
	public String getDengluCode() throws Exception;
	// 可在此加入业务独特的服务接口
	
	//还原数据对象
	public JSONObject hisQyxxJSONObject(String CL_ENTERPRISE_UID);
}
