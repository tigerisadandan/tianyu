/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.yhzg.ScoreDao.java
 * 创建日期： 2015-05-17 上午 09:23:51
 * 功能：   分数管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-17 上午 09:23:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.yhzg.vo.ScoreVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> ScoreDao.java </p>
 * <p> 功能接口：分数管理 </p>
 *
 * <p><a href="ScoreDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-05-17
 * 
 */

public interface ScoreDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, ScoreVO vo, Map map);
	
	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition1(String json, ScoreVO vo, Map map);
	
	public String queryFenshu(String json,String condition2);
	
	public String queryJiafen(String json,String shenhe);
	
	public String queryjfxx(String json,String JFSQ_UID);
	
	public String queryspxx(String json,String JFSQ_UID);
	
	public String queryjfdx(String json,String JFSQ_UID);
	
	public String queryjfsj(String json,String JFSQ_UID);
	
	public String updatestatus(String agree,String JFSTATUS,String JFSQ_UID,String shyj);
	

	public String getDshkCount(String msg) ;
	
	public String getDshjCount(String msg) ;

	public String queryById(String sUid);

	public String updatePersonSh(String msg);
	
	public String updateCompanySh(String msg);
	
	public String getShtgkSum(String id,String type);
	
	public String getShtgjSum(String id,String type);

	// 可在此加入业务独特的服务接口
}
