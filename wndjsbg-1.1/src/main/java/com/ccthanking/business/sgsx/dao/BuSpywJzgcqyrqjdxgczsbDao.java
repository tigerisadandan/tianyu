/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sgsx.BuSpywJzgcqyrqjdxgczsbDao.java
 * 创建日期： 2015-03-24 下午 01:54:36
 * 功能：   sg_建筑工程企业入区接单项工程注申表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 下午 01:54:36  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJzgcqyrqjdxgczsbVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJzgcqyrqjdxgczsbDao.java </p>
 * <p> 功能接口：sg_建筑工程企业入区接单项工程注申表 </p>
 *
 * <p><a href="BuSpywJzgcqyrqjdxgczsbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */

public interface BuSpywJzgcqyrqjdxgczsbDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJzgcqyrqjdxgczsbVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
