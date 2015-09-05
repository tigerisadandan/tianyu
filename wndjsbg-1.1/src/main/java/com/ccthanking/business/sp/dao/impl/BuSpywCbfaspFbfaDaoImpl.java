/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspFbfaDao.java
 * 创建日期： 2014-05-28 下午 04:30:30
 * 功能：   资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 下午 04:30:30  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywCbfaspFbfaDao;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspFbfaVO;
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
 * <p>
 * BuSpywCbfaspFbfaDao.java
 * </p>
 * <p>
 * 功能：资质
 * </p>
 * 
 * <p>
 * <a href="BuSpywCbfaspFbfaDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

@Component
public class BuSpywCbfaspFbfaDaoImpl extends BsBaseDaoTJdbc implements
		BuSpywCbfaspFbfaDao {

	
	public List<?> find(String id) throws Exception{
		
		Connection conn = DBUtil.getConnection();
		String ListMX="select FBFA_UID, CBFASP_UID, BDHFBH, FBNR, FBFS, DATE_JHFBSJ from BU_SPYW_CBFASP_FBFA where CBFASP_UID="+id+"order by FBFA_UID desc";	    	
        
		List<?> bs = DBUtil.queryReturnList(conn, ListMX);
		DBUtil.closeConnetion(conn);
		return bs;
		
	} 
	public String queryCondition(String json, BuSpywCbfaspFbfaVO vo, Map map) {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
			PageManager page = RequestUtil.getPageManager(json);
			String condition = RequestUtil.getConditionList(json).getConditionWhere();
			String orderFilter = RequestUtil.getOrderFilter(json);
			condition += BusinessUtil.getCommonCondition(user, null);
			condition += orderFilter;
			if (page == null)
				page = new PageManager();
			page.setFilter(condition);

			String sql = "SELECT * FROM " + "BU_SPYW_CBFASP_FBFA t";
			BaseResultSet bs = DBUtil.query(conn, sql, page);
			// 合同表
			// bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
			// 项目下达库
			// bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
			// 标段表
			// bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

			// 设置字典

			// 设置查询条件
			// bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
			// bs.setFieldThousand("DYJLSDZ");

			domresult = bs.getJson();

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}

	public String deleteFasps(String uid) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			String sql = "delete from bu_spyw_cbfasp_fbfa t where t.cbfasp_uid = "
					+ uid;
			DBUtil.exec(conn, sql);
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}

	public String queryFbfs(String cbf_uid) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		String domresult = "";
		try {
			String sql = "select t.bdhfbh,t.fbnr,t.fbfs,t.date_jhfbsj from  bu_spyw_cbfasp_fbfa t where t.cbfasp_uid = "+ cbf_uid;
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("BH", res[i][0]);
					obj.put("FBNR", res[i][1]);
					obj.put("FBFS", res[i][2]);
					obj.put("JHFBSJ", res[i][3]);
					
					arr.add(obj);
				}
			}
			domresult = arr.toString();
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}
	// 在此可加入其它方法

}
