/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.qyxx.YxLhEnterpriseGcalxxDao.java
 * 创建日期： 2015-01-28 上午 09:18:45
 * 功能：   绿化企业工程案例信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:18:45  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxLhEnterpriseGcalxxDao;
import com.ccthanking.business.wxgc.vo.YxLhEnterpriseGcalxxVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxLhEnterpriseGcalxxDao.java </p>
 * <p> 功能：绿化企业工程案例信息 </p>
 *
 * <p><a href="YxLhEnterpriseGcalxxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Component
public class YxLhEnterpriseGcalxxDaoImpl  extends BsBaseDaoTJdbc implements YxLhEnterpriseGcalxxDao {

    public String queryCondition(String json, YxLhEnterpriseGcalxxVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null){
                page = new PageManager();
            }
            page.setFilter(condition);

            String sql = "SELECT * FROM YX_LH_ENTERPRISE_GCALXX t";
            
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    /**
	 * 根据绿化企业UID删除其案例信息
	 * */
	public boolean deletAlgcxxByLhUid(String id) {
		Connection conn = DBUtil.getConnection();
        boolean domresult = false;
        try {
            String sql = " delete from YX_LH_ENTERPRISE_GCALXX where LH_ENTERPRISE_UID='"+id+"'";
            domresult= DBUtil.exec(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********删除绿化企业的案例信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    // 在此可加入其它方法

}
