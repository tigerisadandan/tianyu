/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.nbgl.jhgl.WorkPlanDao.java
 * 创建日期： 2015-05-22 下午 12:00:14
 * 功能：   计划管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-22 下午 12:00:14  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.nbgl.jhgl.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.nbgl.jhgl.vo.WorkPlanVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> WorkPlanDao.java </p>
 * <p> 功能接口：计划管理 </p>
 *
 * <p><a href="WorkPlanDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-22
 * 
 */

public interface WorkPlanDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, WorkPlanVO vo, Map map);

	/**
	 * 根据 传入 的时间 查询出 数据 返回 String
	 * @param time
	 * @return
	 */
	public String getByTime(String time);

	/**
	 * 查 用户的 部门 id 
	 * @param user_uid
	 * @return
	 */
	public String getByOrgId(String user_uid);
	
	/**
	 * 根据 时间段 查询数据
	 * @param timeStr
	 * @return
	 */
	public String getDataByTime(String timeStr);
   
	/**
	   据时间段查询 部门数据
	 * @param msg
	 * @return
	 */
	public String getDataForDept(String msg);
	
	
    /**
     * 打印
     * @param uuid
     * @return
     */
	public List<?> findHeaderPrint(String uuid);
    
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public String getDeptName(String msg);
  

	// 可在此加入业务独特的服务接口
}
