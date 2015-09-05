/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sgsx.BuSpywJsgckgaqsctjfcDao.java
 * 创建日期： 2015-03-31 上午 09:51:41
 * 功能：   sg_建设工程施工开工前安全条件备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-31 上午 09:51:41  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgckgaqsctjfcVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJsgckgaqsctjfcDao.java </p>
 * <p> 功能接口：sg_建设工程施工开工前安全条件备案表 </p>
 *
 * <p><a href="BuSpywJsgckgaqsctjfcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-31
 * 
 */

public interface BuSpywJsgckgaqsctjfcDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJsgckgaqsctjfcVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
