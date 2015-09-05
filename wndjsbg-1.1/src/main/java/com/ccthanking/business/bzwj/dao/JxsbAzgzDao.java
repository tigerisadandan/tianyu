/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.bzwj.JxsbAzgzDao.java
 * 创建日期： 2015-01-16 下午 12:03:25
 * 功能：   机械设备安装告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-16 下午 12:03:25  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.JxsbAzgcVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbAzgzVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgcVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbCzryVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbJcysVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbSyglVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JxsbAzgzDao.java </p>
 * <p> 功能接口：机械设备安装告知 </p>
 *
 * <p><a href="JxsbAzgzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-16
 * 
 */

public interface JxsbAzgzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JxsbAzgzVO vo, Map map);
	
	public String queryAzgc(String json, JxsbAzgcVO vo, Map map);
	public String queryJcys(String json, JxsbJcysVO vo, Map map);
	public String queryCxgc(String json, JxsbCxgcVO vo, Map map);
	public String querySbzl(String json, JxsbSyglVO vo, Map map);
	
	public String querySyglCl(String json, JxsbSyglVO vo, Map map);
	
	
	public String queryDslSb(String json, JxsbSyglVO vo, Map map);
	public String queryDslSbWsl(String json, JxsbSyglVO vo, Map map);
	public String queryDslSbDzx(String json, JxsbSyglVO vo, Map map);
	
    public String queryByView(String json, JxsbAzgzVO vo, Map map);
	
	public String queryAzyr(String json, JxsbCzryVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
