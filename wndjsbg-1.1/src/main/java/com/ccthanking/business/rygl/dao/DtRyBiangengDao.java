/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.rygl.DtRyBiangengDao.java
 * 创建日期： 2015-04-12 下午 08:31:04
 * 功能：   人员变更
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-12 下午 08:31:04  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.rygl.vo.DtRyBiangengVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> DtRyBiangengDao.java </p>
 * <p> 功能接口：人员变更 </p>
 *
 * <p><a href="DtRyBiangengDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java1@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-12
 * 
 */

public interface DtRyBiangengDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, DtRyBiangengVO vo, Map map);

	public String getBgCount(String msg);

	public String queryBg(String msg);

	public String updateTg(String msg);

	public String updateTh(String msg);

	// 可在此加入业务独特的服务接口
}
