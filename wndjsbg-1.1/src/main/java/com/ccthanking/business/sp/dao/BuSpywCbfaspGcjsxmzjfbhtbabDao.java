/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.BuSpywCbfaspGcjsxmzjfbhtbabDao.java
 * 创建日期： 2014-11-18 下午 05:55:20
 * 功能：   房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 05:55:20  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao;

import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywCbfaspGcjsxmzjfbhtbabDao.java </p>
 * <p> 功能接口：房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件 </p>
 *
 * <p><a href="BuSpywCbfaspGcjsxmzjfbhtbabDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

public interface BuSpywCbfaspGcjsxmzjfbhtbabDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json);

	// 可在此加入业务独特的服务接口
}
