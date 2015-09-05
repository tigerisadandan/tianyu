/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.SgZhengshuDao.java
 * 创建日期： 2014-04-27 下午 01:22:52
 * 功能：   人员证书信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:22:52  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.person.dao;

import java.util.Map;

import com.ccthanking.business.person.vo.SgZhengshuVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgZhengshuDao.java </p>
 * <p> 功能接口：人员证书信息 </p>
 *
 * <p><a href="SgZhengshuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

public interface SgZhengshuDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgZhengshuVO vo, Map map);

	 /**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json) throws Exception;

    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json) throws Exception;

    /**
     * 删除记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json) throws Exception;
    
    
    /**
     * 加载列表
     * @param json
     * @return
     * @throws Exception
     */
    String queryAllZhengshu() throws Exception;
    
    /**
     * 根据岗位获取用户证书
     * @return
     * @throws Exception
     */
    String queryZsByGw(String person_uid,String gw_uid) throws Exception;
    
    /**
     * 根据用户UID查询项目经理资质
     * @return
     * @throws Exception
     */
    String queryZs(String person_uid,String zhengshu_type) throws Exception;
}
