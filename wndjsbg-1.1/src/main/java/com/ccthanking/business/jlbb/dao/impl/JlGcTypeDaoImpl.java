/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgbb.GcTypeDao.java
 * 创建日期： 2014-04-22 下午 04:07:38
 * 功能：   施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-22 下午 04:07:38  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.jlbb.dao.JlGcTypeDao;
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
 * GcTypeDao.java
 * </p>
 * <p>
 * 功能：监理报备
 * </p>
 * 
 * <p>
 * <a href="GcTypeDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-22
 * 
 */

@Component
public class JlGcTypeDaoImpl extends BsBaseDaoTJdbc implements JlGcTypeDao {

	public String queryCondition(String json) {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
			PageManager page = RequestUtil.getPageManager(json);
			String condition = RequestUtil.getConditionList(json)
					.getConditionWhere();
			String orderFilter = RequestUtil.getOrderFilter(json);
			condition += BusinessUtil.getSJYXCondition(null);
			condition += BusinessUtil.getCommonCondition(user, null);
			condition += orderFilter;
			if (page == null)
				page = new PageManager();
			page.setFilter(condition);

			String sql = "SELECT * FROM " + "GC_TYPE t";
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

	public String queryTypelist(String pType) throws Exception {
		
			Connection conn = DBUtil.getConnection();
			
			JSONArray jsonArr = new JSONArray();
			
			try {
				String sql = "select * from gc_type t where enabled = 1 ";
				if(StringUtils.isNotBlank(pType)){
					sql += " and t.p_type_uid = "+pType+" ";
				}
				sql += " order by t.SERIAL_NO";
				List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
				for (int i = 0; i < rsList.size(); i++) {
					Map<String, String> rsMap = rsList.get(i);
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("GC_TYPE_UID",rsMap.get("GC_TYPE_UID"));
					jsonObj.put("P_TYPE_UID", rsMap.get("P_TYPE_UID"));
				    jsonObj.put("ENABLED", rsMap.get("ENABLED"));
				    jsonObj.put("CREATED_UID", rsMap.get("CREATED_UID"));
				    jsonObj.put("CREATED_NAME", rsMap.get("CREATED_NAME"));
				    jsonObj.put("CREATED_DATE", rsMap.get("CREATED_DATE"));
				    jsonObj.put("ZHUANYE_NAME", rsMap.get("ZHUANYE_NAME"));
				    jsonObj.put("SERIAL_NO", rsMap.get("SERIAL_NO"));
				    jsonObj.put("CODES", rsMap.get("CODES"));
				    jsonObj.put("NAMES", rsMap.get("NAMES"));
				    jsonObj.put("NODE_TYPE", rsMap.get("NODE_TYPE"));
				    jsonObj.put("UNIT", rsMap.get("UNIT"));
				    jsonObj.put("TAGS", rsMap.get("TAGS"));
				    //jsonObj.put("SG_ZIZHI_UID", rsMap.get("SG_ZIZHI_UID"));
				    jsonArr.add(jsonObj);
				}
				
			} catch (Exception e) {
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}
			return jsonArr.toString();
	}
	// 在此可加入其它方法

	public String queryPType(String gc_type) {
		
		Connection conn = DBUtil.getConnection();
		
		JSONArray jsonArr = new JSONArray();
		
		try {
			String sql = "select * from gc_type t where enabled = 1  ";
			if(StringUtils.isNotBlank(gc_type)){
				sql += " and t.gc_type_uid = (select n.p_type_uid from gc_type n where n.gc_type_uid = "+gc_type+") ";
			}
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("GC_TYPE_UID",rsMap.get("GC_TYPE_UID"));
				jsonObj.put("P_TYPE_UID", rsMap.get("P_TYPE_UID"));
			    jsonObj.put("ENABLED", rsMap.get("ENABLED"));
			    jsonObj.put("CREATED_UID", rsMap.get("CREATED_UID"));
			    jsonObj.put("CREATED_NAME", rsMap.get("CREATED_NAME"));
			    jsonObj.put("CREATED_DATE", rsMap.get("CREATED_DATE"));
			    jsonObj.put("ZHUANYE_NAME", rsMap.get("ZHUANYE_NAME"));
			    jsonObj.put("SERIAL_NO", rsMap.get("SERIAL_NO"));
			    jsonObj.put("CODES", rsMap.get("CODES"));
			    jsonObj.put("NAMES", rsMap.get("NAMES"));
			    jsonObj.put("NODE_TYPE", rsMap.get("NODE_TYPE"));
			    jsonObj.put("UNIT", rsMap.get("UNIT"));
			    jsonObj.put("TAGS", rsMap.get("TAGS"));
			    //jsonObj.put("SG_ZIZHI_UID", rsMap.get("SG_ZIZHI_UID"));
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
}
}
