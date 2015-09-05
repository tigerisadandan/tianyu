package com.ccthanking.business.xfww.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.gdzxt.xfww.vo.XinfangVO;
import com.ccthanking.business.xfww.dao.XinFangDao;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;

@Component
public class XinFangDaoImpl extends BsBaseDaoTJdbc implements XinFangDao{

	public String queryCondition(String json, XinfangVO vo, Map map) {
		// TODO Auto-generated method stub
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
            String sql = "SELECT * FROM " + "XINFANG t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            //如果需要的话，转换时间格式
            bs.setFieldDateFormat("XF_DATE", "yyyy/MM/dd HH:mm:ss");
            bs.setFieldDateFormat("XC_DATE", "yyyy/MM/dd HH:mm:ss");
            bs.setFieldDateFormat("ZG_DATE", "yyyy/MM/dd HH:mm:ss");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	}

	public boolean delete(String XINFANG_UID) {
		User user = ActionContext.getCurrentUserInThread();//当前用户
		Connection conn = DBUtil.getConnection();
		boolean flag = true;
		try {
			String sql = "delete XINFANG where XINFANG_UID = ?";
			DBUtil.executeUpdate(conn, sql, new Object[]{XINFANG_UID});
		} catch (Exception e) {
			flag = false;
			 DaoException.handleMessageException("*********删除出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
				
		return flag;
	}

	public String getById(String XINFANG_UID) {
		// TODO Auto-generated method stub
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select * from XINFANG where XINFANG_UID = "+XINFANG_UID;
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			//如果需要的话，转换时间格式
            bs.setFieldDateFormat("XF_DATE", "yyyy/MM/dd HH:mm:ss");
            bs.setFieldDateFormat("XC_DATE", "yyyy/MM/dd HH:mm:ss");
            bs.setFieldDateFormat("ZG_DATE", "yyyy/MM/dd HH:mm:ss");
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

}
