/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sgzxt.getZxtIndexInformationDao.java
 * 创建日期： 2015-08-13 下午 03:19:20
 * 功能：   施工子系统首页信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-08-13 下午 03:19:20  老虎是只耽耽   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgzxt.dao;


import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> getZxtIndexInformationDao.java </p>
 * <p> 功能接口：施工子系统首页信息 </p>
 *
 * <p><a href="getZxtIndexInformationDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">老虎是只耽耽</a>
 * @version 0.1
 * @since 2015-08-13
 * 
 */

public interface getZxtIndexInformationDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json,String GongCheng_UID);
	
	public String queryGC_SCORE(String json);
	
	public String queryGC_SCORE_detail(String json);
	
	public String querySGRY(String json);
	
	public String queryJLRY(String json);
	
	public String queryRYXX_detail(String json,String personType);
	
	public String queryRYXX_Fileid(String json,String PERSON_UID,String File_type);
	
	public String querygc(String json);

	// 可在此加入业务独特的服务接口
}
