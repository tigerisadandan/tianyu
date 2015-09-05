package com.ccthanking.business.dtgl.dao.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.dao.QueryZGDDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.copj.modules.utils.exception.DaoException;

@Component
public class QueryZGDImpl implements QueryZGDDao {

public String queryZGD(){		
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select * from zg_tzd";
			BaseResultSet bs = DBUtil.query(conn, sql, null);
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}

}
