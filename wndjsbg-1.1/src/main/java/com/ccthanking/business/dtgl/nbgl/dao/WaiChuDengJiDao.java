/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dtgl.nbgl.WaiChuDengJiDao.java
 * 创建日期： 2015-05-21 下午 03:32:32
 * 功能：   外出登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-21 下午 03:32:32  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.nbgl.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.nbgl.vo.WaiChuDengJiVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> WaiChuDengJiDao.java </p>
 * <p> 功能接口：外出登记 </p>
 *
 * <p><a href="WaiChuDengJiDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-21
 * 
 */

public interface WaiChuDengJiDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, WaiChuDengJiVO vo, Map map);
	
	/**
	 * 根据外出编号删除
	 * @param wcdjId 外出编号
	 * @return
	 */
	public boolean delete(String wcdjId);
	
	/**
	 * 修改外出为已返回
	 * @return
	 */
	public boolean updateState(String wcdjId);
	
	/**
	 * 根据外出编号，查询外出登记信息
	 * @param wcdjId
	 * @return
	 */
	public String getById(String wcdjId);
	
	// 可在此加入业务独特的服务接口
}
