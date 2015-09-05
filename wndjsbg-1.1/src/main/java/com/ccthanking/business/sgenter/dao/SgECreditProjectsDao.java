/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgenter.SgECreditProjectsDao.java
 * 创建日期： 2014-04-20 下午 02:22:53
 * 功能：   企业参与项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:22:53  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgenter.dao;

import java.util.Map;

import com.ccthanking.business.sgenter.vo.SgECreditProjectsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgECreditProjectsDao.java </p>
 * <p> 功能接口：企业参与项目 </p>
 *
 * <p><a href="SgECreditProjectsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

public interface SgECreditProjectsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgECreditProjectsVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	/**
     * 获取项目列表
     * @param uid
     * @return
     * @throws Exception
     */
    String queryXmList(String uid);
    
    /**
     * 复制项目信息
     * @param sg_uid
     * @return
     */
    String updateCopyXmxx(String sg_uid);
}
