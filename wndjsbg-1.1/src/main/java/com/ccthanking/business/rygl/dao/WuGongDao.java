/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.rygl.WuGongDao.java
 * 创建日期： 2015-03-24 上午 11:44:51
 * 功能：   务工信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 上午 11:44:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.rygl.vo.WuGongVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> WuGongDao.java </p>
 * <p> 功能接口：务工信息 </p>
 *
 * <p><a href="WuGongDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */

public interface WuGongDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, WuGongVO vo, Map map);

	public String wugongtj(String msg);

	public String queryById(String msg);

	public String queryGzqk(String msg);

	public String queryJineng(String msg);

	public String queryTijianInfo(String msg);

	// 可在此加入业务独特的服务接口
}
