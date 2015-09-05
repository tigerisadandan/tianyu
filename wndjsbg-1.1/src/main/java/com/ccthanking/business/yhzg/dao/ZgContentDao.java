/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.yhzg.ZgContentDao.java
 * 创建日期： 2015-04-21 下午 01:24:35
 * 功能：   需整改的安全隐患
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:24:35  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.yhzg.vo.ZgContentVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ZgContentDao.java </p>
 * <p> 功能接口：需整改的安全隐患 </p>
 *
 * <p><a href="ZgContentDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

public interface ZgContentDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, ZgContentVO vo, Map map);

	public String queryByTzdUid(String tzdUid);

	public String queryScore(String tzdUid);

	public String getContent(String tzdUid);

	public String getPicNum(String tzdUid);

	// 可在此加入业务独特的服务接口
}
