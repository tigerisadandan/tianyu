/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.nbgl.WaiChuDengJiDao.java
 * 创建日期： 2015-05-21 下午 03:32:32
 * 功能：   外出登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-21 下午 03:32:32  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.nbgl.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.nbgl.dao.WaiChuDengJiDao;
import com.ccthanking.business.dtgl.nbgl.vo.WaiChuDengJiVO;
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
 * <p> WaiChuDengJiDao.java </p>
 * <p> 功能：外出登记 </p>
 *
 * <p><a href="WaiChuDengJiDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-21
 * 
 */

@Component
public class WaiChuDengJiDaoImpl  extends BsBaseDaoTJdbc implements WaiChuDengJiDao {

    public String queryCondition(String json, WaiChuDengJiVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();//当前登陆用户
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            condition = condition.replace("/", "");
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            String sql = "SELECT * FROM " + "WAICHU_DENGJI t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            bs.setFieldDateFormat("WAICHU_SHIJIAN", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("FANHUI_SHIJIAN", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("YJ_FH_SHIJIAN", "yyyy-MM-dd HH:mm:ss");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public boolean updateState(String wcdjId) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection connection = DBUtil.getConnection();
		boolean flag  = true;
		try {
			String sql = "update waichu_dengji wc set wc.waichu_status = 2,fanhui_shijian=(select sysdate from dual) where wc.waichu_dengji_uid = ? ";
			DBUtil.executeUpdate(connection, sql, new Object[]{wcdjId});
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(connection);
		}
		return flag;
	}

	public String getById(String wcdjId) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select * from waichu_dengji where WAICHU_DENGJI_UID = "+wcdjId;
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			bs.setFieldDateFormat("WAICHU_SHIJIAN", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("FANHUI_SHIJIAN", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("YJ_FH_SHIJIAN", "yyyy-MM-dd HH:mm:ss");
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public boolean delete(String wcdjId) {
		User user = ActionContext.getCurrentUserInThread();//当前用户
		Connection conn = DBUtil.getConnection();
		boolean flag = true;
		try {
			String sql = "delete WAICHU_DENGJI where WAICHU_DENGJI_UID = ?";
			DBUtil.executeUpdate(conn, sql, new Object[]{wcdjId});
		} catch (Exception e) {
			flag = false;
			 DaoException.handleMessageException("*********删除出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
				
		return flag;
	}
    
    // 在此可加入其它方法

}
