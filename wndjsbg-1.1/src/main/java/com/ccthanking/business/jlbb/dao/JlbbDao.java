/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.jlbb.JlbbDao.java
 * 创建日期： 2015-01-28 上午 09:10:22
 * 功能：   监理报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:10:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jlbb.dao;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;


import com.ccthanking.business.dtgl.jl.vo.JlbbVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JlbbDao.java </p>
 * <p> 功能接口：监理报备 </p>
 *
 * <p><a href="JlbbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

public interface JlbbDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json,Map<String,String> map);

	// 可在此加入业务独特的服务接口
	
	
	public String queryZbgg(String json);
	
	public String queryBbxx(String json);


	public String queryPersonList(String msg) throws Exception;
	
	public String getNewBaobeiCode() throws Exception;
	
	
	public void updateBbzt (String jlbb_uid,String status) throws Exception;

	public void unLockBb(String bbid);

	public void unLockBbry(String bbid);

	public void personLock(String bbuid);

	public JasperPrint query4Print(String bbid, String parpentPath,
			String childPath);

	public List<?> findHeaderPrint(String bbid);

	public Object findTablePrint(String bbid);
	
	
}
