/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpYwxxDao.java
 * 创建日期： 2014-06-11 上午 11:51:59
 * 功能：   审批业务信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-11 上午 11:51:59  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpYwxxDao;
import com.ccthanking.business.spxx.vo.BuSpYwxxVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;

/**
 * <p>
 * BuSpYwxxDao.java
 * </p>
 * <p>
 * 功能：审批业务信息
 * </p>
 * 
 * <p>
 * <a href="BuSpYwxxDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */

@Component
public class BuSpYwxxDaoImpl extends BsBaseDaoTJdbc implements BuSpYwxxDao {

	public String queryCondition(String json, BuSpYwxxVO vo, Map map) {

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

			String sql = "SELECT t.*,t2.SPYWMC P_YWMC FROM BU_SP_YWXX t LEFT JOIN  BU_SP_YWXX t2 on t2.SPYW_UID = t.P_SPYW_UID";
			BaseResultSet bs = DBUtil.query(conn, sql, page);
			bs.setFieldDic("SPYWLX", "SPYWLX");
			bs.setFieldDic("SFZLC", "SF");
			bs.setFieldDic("MULTI_Y", "SF");
			
			domresult = bs.getJson();

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}
	
	public String queryBz(String json, BuSpYwxxVO vo, Map map) {

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

			String sql = "SELECT t.DESCRIBE FROM BU_SP_YWXX t ";
			
			String[][] res = DBUtil.query(conn, sql+" where "+condition);
			domresult = res[0][0];
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}
	
	public String queryYWLX(String json) {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			String sql = "select *from bu_sp_ywxx where SPYW_UID="+json;
			BaseResultSet bs = DBUtil.query(conn, sql, null);
			domresult = bs.getJson();

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}

	public String getAllSpyw() throws Exception {
		Connection conn = null;
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			conn = DBUtil.getConnection();
			String sql_spyw = "select t.SPYW_UID,t.P_SPYW_UID,t.SPYWMC from bu_sp_ywxx t";
			JSONObject rootJson = new JSONObject();
			rootJson.put("id", "");
			rootJson.put("parentId", "0");
			rootJson.put("name", "业务树");
			rootJson.put("open", "true");
			rootJson.put("levelno", "0");
			arr.add(rootJson);
			String[][] res = DBUtil.query(conn, sql_spyw);
			if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("id", res[i][0]);
					obj.put("parentId", res[i][1]);
					obj.put("name", res[i][2]);
					
					arr.add(obj);
				}
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return arr.toString();
	}

	// 在此可加入其它方法

}
