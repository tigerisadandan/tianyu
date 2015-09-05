/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件： 
 * 创建日期： 2015-07-07 下午 12:00:14
 * 功能：   内部通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-07-07 下午 12:00:14  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.nbgl.inform01.dao.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import com.ccthanking.business.nbgl.inform01.dao.WorkInformDao;
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
 * <p> WorkInformDao.java </p>
 * <p> 功能接口：内部通知管理 </p>
 *
 * <p><a href="WorkInformDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:lhtarena@163.com">卢红冈</a>
 * @version 0.1
 * @since 2015-07-07
 * 
 */
@Component
public class WorkInformDaoImpl  extends BsBaseDaoTJdbc implements WorkInformDao{
    
	/**
	 * 有效的 通知
	 */
	public String queryCondition(String json) {
		
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            //condition = condition.replace("/", "");
            String orderFilter = RequestUtil.getOrderFilter(json);
          //  condition += BusinessUtil.getCommonCondition(user, null);
            condition = "SHIXIAO_DATE  >=(select sysdate from dual) and "+condition+orderFilter;
           
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            //查 通知的標題/內容/状态/生效日期   有效通知 = 失效通知大于当前系统时间
            String sql = "SELECT NEIBU_TONGZHI_UID,TONGZHI_BIAOTI,TONGZHI_NEIRONG,STATUS,SHENGXIAO_DATE FROM " + "NEIBU_TONGZHI n ";
                         
            BaseResultSet bs = DBUtil.query(conn, sql,page);
            //如果需要的话，转换时间格式
            bs.setFieldDateFormat("SHENGXIAO_DATE", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("SHIXIAO_DATE", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm:ss");
            domresult = bs.getJson();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询有效通知出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	}
    /**
     * 查询失效的通知
     */
	public String queryOutOfDate(String json) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition = "SHIXIAO_DATE <=(select sysdate from dual) and  STATUS = 2 AND "+condition+orderFilter;
           
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            //查 通知的標題/內容/状态/生效日期   有效通知 = 失效通知大于当前系统时间
            String sql = "SELECT NEIBU_TONGZHI_UID,TONGZHI_BIAOTI,TONGZHI_NEIRONG,SHENGXIAO_DATE,SHIXIAO_DATE FROM NEIBU_TONGZHI n ";
                         
            BaseResultSet bs = DBUtil.query(conn, sql,page);
            //如果需要的话，转换时间格式
            bs.setFieldDateFormat("SHENGXIAO_DATE", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("SHIXIAO_DATE", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm:ss");
            domresult = bs.getJson();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询失效通知出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    //首页查看页面初始化
	public String queryById(String selectId) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "SELECT TONGZHI_BIAOTI,TONGZHI_NEIRONG,SHENGXIAO_DATE,SHIXIAO_DATE FROM NEIBU_TONGZHI n WHERE NEIBU_TONGZHI_UID="+selectId;
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			//如果需要的话，转换时间格式
			 bs.setFieldDateFormat("SHENGXIAO_DATE", "yyyy-MM-dd HH:mm");
	         bs.setFieldDateFormat("SHIXIAO_DATE", "yyyy-MM-dd HH:mm");
	        // bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm:ss");
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询查看通知页面数据出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public boolean deleteById(String selectId) {
		User user = ActionContext.getCurrentUserInThread();//当前用户
		Connection conn = DBUtil.getConnection();
		boolean flag = true;
		try {
			String sql = "DELETE NEIBU_TONGZHI n WHERE NEIBU_TONGZHI_UID = ?";
			DBUtil.executeUpdate(conn, sql, new Object[]{selectId});
		} catch (Exception e) {
			flag = false;
			 DaoException.handleMessageException("*********删除通知出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
				
		return flag;
	}

	
}
	
	


	