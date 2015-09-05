/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.sgsx.BuSpywJsxmkqtshbaDao.java
 * 创建日期： 2015-04-01 下午 03:46:50
 * 功能：   sg_《无锡新区建设工程人脸识别考勤特殊化备案表》
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-01 下午 03:46:50  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao;

import java.util.Map;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsxmkqtshbaVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuSpywJsxmkqtshbaDao.java </p>
 * <p> 功能接口：sg_《无锡新区建设工程人脸识别考勤特殊化备案表》 </p>
 *
 * <p><a href="BuSpywJsxmkqtshbaDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-01
 * 
 */

public interface BuSpywJsxmkqtshbaDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpywJsxmkqtshbaVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
