package com.ccthanking.business.dtgl.sgsx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.sgsx.dao.SgsxProjectDao;
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
 * SgsxProjectDaoImpl.java
 * </p>
 * <p>
 * 功能：审批业务信息
 * </p>
 * <p>
 * <a href="SgsxProjectDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:luhonggang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2014-07-14
 * 
 */

@Component
public class SgsxProjectDaoImpl extends BsBaseDaoTJdbc implements SgsxProjectDao {

	public String queryCondition(String json, BuSpYwxxVO vo, Map map) {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
			/*PageManager page = RequestUtil.getPageManager(json);
			String condition = RequestUtil.getConditionList(json).getConditionWhere();
			String orderFilter = RequestUtil.getOrderFilter(json);
			condition += orderFilter;
			if (page == null)
				page = new PageManager();
			page.setFilter(condition);*/
//
			//String sql = "select xx.spyw_uid,lz.status from bu_sp_ywxx xx left join bu_sp_ywlz lz on lz.spyw_uid=xx.spyw_uid and lz.projects_uid='"+gcid+"' " +
			//"where xx.spywlx='SG' and p_spyw_uid is not null ";
			String sql = "SELECT t.*,t2.SPYWMC P_YWMC FROM BU_SP_YWXX t LEFT JOIN  BU_SP_YWXX t2 on t2.SPYW_UID = t.P_SPYW_UID "+
					     "where t.spyw_uid  in(113,117,118,119)"+
					     "order by case t.spyw_uid "+
					            "when 113 then 1 "+
					            "when 117 then 2 "+
					            "when 118 then 3 "+
					            "when 119 then 4 end";
					              
					          
			BaseResultSet bs = DBUtil.query(conn, sql,null);
			bs.setFieldDic("SPYWLX", "SG");
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
	//查询 状态
	public String queryYwStatus(String gcid, BuSpYwxxVO vo, Map map) {
		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件

			String sql = "select xx.spyw_uid, lz.status,g.SFSZGC,xx.SPYWMC from bu_sp_ywxx xx "+
						   "left join bu_sp_ywlz lz "+
						   "on lz.spyw_uid = xx.spyw_uid "+
						   "and lz.projects_uid = '"+gcid+"' "+
						   "left join projects_gongcheng g "+
						   "on g.GONGCHENG_UID = lz.projects_uid "+
						   "where  xx.spywlx = 'SG' "+
						   "and p_spyw_uid is not null and lz.status is not null";
					
			BaseResultSet bs = DBUtil.query(conn, sql, null);
			
			domresult = bs.getJson();

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}
    //是否是市政 工程
	public String querySFszgc(String gcid){
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
		    String sql="select SFSZGC  from projects_gongcheng WHERE GONGCHENG_UID = "+gcid;
			BaseResultSet bs = DBUtil.query(conn, sql, null);
			
			domresult = bs.getJson();
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	
	public String querySgYwlzId(String gcId,String spywId) {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
		    String sql=" select ywlz_uid from bu_sp_ywlz  where  SPYW_UID="+spywId+" AND PROJECTS_UID="+gcId;
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			
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

