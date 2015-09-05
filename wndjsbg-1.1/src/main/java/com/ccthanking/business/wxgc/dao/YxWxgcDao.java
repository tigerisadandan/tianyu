/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.wxgc.YxWxgcDao.java
 * 创建日期： 2014-12-23 下午 01:29:58
 * 功能：   微型工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:29:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.wxgc.vo.YxWxgcVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> YxWxgcDao.java </p>
 * <p> 功能接口：微型工程 </p>
 *
 * <p><a href="YxWxgcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

public interface YxWxgcDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, YxWxgcVO vo, Map map);
	
	public String queryspjlCondition(String json, YxWxgcVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	public String getCode(int length);
	
	public List<Map<String,String>> gcTypeList(Map map);
}
