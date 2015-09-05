/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpYwclDao.java
 * 创建日期： 2014-06-18 上午 10:56:57
 * 功能：   审批业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-18 上午 10:56:57  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpYwclDao;
import com.ccthanking.business.spxx.vo.BuSpYwclVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuSpYwclDao.java </p>
 * <p> 功能：审批业务材料 </p>
 *
 * <p><a href="BuSpYwclDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-18
 * 
 */

@Component
public class BuSpYwclDaoImpl  extends BsBaseDaoTJdbc implements BuSpYwclDao {

    public String queryCondition(String json, BuSpYwclVO vo, Map map){
    
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

            String sql = "SELECT t.*,t1.CLMC,t1.CL_LEVEL,t1.CLLX FROM " + "BU_SP_YWCL t left join BU_SP_CLK t1 on t.CLK_UID = t1.CLK_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            bs.setFieldDic("CL_LEVEL", "SPYWCLJB");
            bs.setFieldDic("CLLX", "SPYWCLLX");
            bs.setFieldDic("CLSX", "CLSX");
            bs.setFieldDic("SFYSC", "SF");
            

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    public String queryMaxXh(String ywid){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "1";
        try {

           String sql = "select max(SERIAL_NO) from BU_SP_YWCL";
           String[][] res = DBUtil.query(sql);
           if(res!=null){
        	   if (StringUtils.isNotBlank(res[0][0])) {
        		   domresult = (Integer.parseInt(res[0][0])+1)+"";
        	   }else{
        		   domresult = "1";
        	   }
           }
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    // 在此可加入其它方法

}
