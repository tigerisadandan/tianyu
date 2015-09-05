/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgenter.SgECreditCommendRewardDao.java
 * 创建日期： 2014-04-20 下午 02:19:52
 * 功能：   企业荣誉
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:19:52  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgenter.dao;

import java.util.Map;

import com.ccthanking.business.sgenter.vo.SgECreditCommendRewardVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgECreditCommendRewardDao.java </p>
 * <p> 功能接口：企业荣誉 </p>
 *
 * <p><a href="SgECreditCommendRewardDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

public interface SgECreditCommendRewardDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgECreditCommendRewardVO vo, Map map);

	// 可在此加入业务独特的服务接口
	
	 /**
     * 获取奖项列表
     * @param uid
     * @return
     * @throws Exception
     */
    String queryJxList(String uid);
    
    /**
     * 复制奖项信息
     * @param sg_uid
     * @return
     */
    String updateCopyJxxx(String sg_uid);
}
