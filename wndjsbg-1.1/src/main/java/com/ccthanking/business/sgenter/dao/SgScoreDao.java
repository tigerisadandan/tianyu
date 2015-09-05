/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgenter.SgScoreDao.java
 * 创建日期： 2014-06-09 上午 09:35:20
 * 功能：   分数
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:35:20  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgenter.dao;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ccthanking.business.sgenter.vo.SgScoreVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgScoreDao.java </p>
 * <p> 功能接口：分数 </p>
 *
 * <p><a href="SgScoreDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

public interface SgScoreDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgScoreVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	/**
	 * 获取综合评分信息
	 */
	public JSONArray getScoreInfo(String uid);
	
	/**
	 * 获取奖项评分信息
	 * @param uid
	 * @return
	 */
	public JSONArray getJxInfo(String uid);
	
	/**
	 * 获取项目评分信息
	 * @param uid
	 * @return
	 */
	public JSONArray getXmInfo(String uid);
	
	/**
	 * 获取基本分
	 * @param uid
	 * @return
	 */
	public JSONArray getJCscore(String uid);
	
}
