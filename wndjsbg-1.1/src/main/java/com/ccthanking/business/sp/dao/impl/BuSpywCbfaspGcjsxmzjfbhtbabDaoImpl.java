/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspDao.java
 * 创建日期： 2014-05-28 上午 10:34:34
 * 功能：   资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 上午 10:34:34  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywCbfaspGcjsxmzjfbhtbabDao;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuSpywCbfaspDao.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywCbfaspDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

@Component
public class BuSpywCbfaspGcjsxmzjfbhtbabDaoImpl  extends BsBaseDaoTJdbc implements BuSpywCbfaspGcjsxmzjfbhtbabDao {

    public String queryCondition(String json){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_CBFASP_GCJSXMZJFBHTBAB t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
        

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }


	public String getCount(int uid, String lx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNum(int uid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String queryCondition(String json, BuSpywCbfaspVO vo, Map map) {
		// TODO Auto-generated method stub
		return null;
	}
   

}
