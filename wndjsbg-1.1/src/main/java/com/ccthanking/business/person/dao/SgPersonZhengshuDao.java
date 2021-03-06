/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.person.SgPersonZhengshuDao.java
 * 创建日期： 2014-04-27 下午 01:01:06
 * 功能：   人员证书记录信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:01:06  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.person.dao;

import java.util.Map;

import com.ccthanking.business.person.vo.SgPersonLibraryVO;
import com.ccthanking.business.person.vo.SgPersonZhengshuVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgPersonZhengshuDao.java </p>
 * <p> 功能接口：人员证书记录信息 </p>
 *
 * <p><a href="SgPersonZhengshuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

public interface SgPersonZhengshuDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgPersonZhengshuVO vo, Map map);

	// 可在此加入业务独特的服务接口
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
     * 根据条件  查询证书名字
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryZS(String id) throws Exception;
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
    
    String queryListPersonZhengshu(String json) throws Exception;
    
    void deleteByPersonuid(SgPersonLibraryVO vo);
    
    String updateCopyZhengshu(String t_id,String u_id,String new_id) throws Exception;
    String updateCopyZhengshu2(String IdCard,String new_id) throws Exception;

   
}
