/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.yhzg.ZgDafuDao.java
 * 创建日期： 2015-04-21 下午 01:27:03
 * 功能：   整改答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:27:03  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.dtgl.yhzg.vo.ZgDafuVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ZgDafuDao.java </p>
 * <p> 功能接口：整改答复 </p>
 *
 * <p><a href="ZgDafuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

public interface ZgDafuDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, ZgDafuVO vo, Map map);

	public List<?> queryPrint(String tzdfuid);

	public List<?> queryPrint2(String tzduid);

	// 可在此加入业务独特的服务接口
}
