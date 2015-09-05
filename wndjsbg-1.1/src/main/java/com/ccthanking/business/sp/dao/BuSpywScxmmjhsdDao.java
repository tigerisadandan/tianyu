/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sp.BuSpywScxmmjhsdDao.java
 * 创建日期： 2014-10-27 下午 05:33:08
 * 功能：   三产项目面积核算单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-27 下午 05:33:08  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spyw.vo.BuSpywScxmmjhsdVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywScxmmjhsdDao.java </p>
 * <p> 功能接口：三产项目面积核算单 </p>
 *
 * <p><a href="BuSpywScxmmjhsdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-27
 * 
 */

public interface BuSpywScxmmjhsdDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywScxmmjhsdVO vo, Map map);
	
	public List<Map<String, String>> queryTpfFileNameByScxmid(String id);
	
	public String queryByLzbz(String lzbzId);

	// 可在此加入业务独特的服务接口
}
