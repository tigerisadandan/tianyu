/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.qyxx.YxKcEnterpriseGcalxxDao.java
 * 创建日期： 2015-01-28 下午 12:09:51
 * 功能：   勘察企业工程案例信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 12:09:51  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxKcEnterpriseGcalxxDao;
import com.ccthanking.business.wxgc.vo.YxKcEnterpriseGcalxxVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxKcEnterpriseGcalxxDao.java </p>
 * <p> 功能：勘察企业工程案例信息 </p>
 *
 * <p><a href="YxKcEnterpriseGcalxxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Component
public class YxKcEnterpriseGcalxxDaoImpl  extends BsBaseDaoTJdbc implements YxKcEnterpriseGcalxxDao {

    public String queryCondition(String json, YxKcEnterpriseGcalxxVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM YX_KC_ENTERPRISE_GCALXX t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public boolean deletAlgcxxByKcUid(String id) {
		Connection conn = DBUtil.getConnection();
        boolean domresult = false;
        try {
            String sql = " delete from YX_KC_ENTERPRISE_GCALXX where KC_ENTERPRISE_UID='"+id+"'";
            domresult= DBUtil.exec(conn, sql);
            conn.commit();
        } catch (Exception e) {
            DaoException.handleMessageException("*********删除勘察企业的案例信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    // 在此可加入其它方法

}
