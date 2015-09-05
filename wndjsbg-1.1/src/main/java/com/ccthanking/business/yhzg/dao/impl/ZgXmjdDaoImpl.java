package com.ccthanking.business.yhzg.dao.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import com.ccthanking.business.yhzg.dao.ZgXmjdDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.copj.modules.utils.exception.DaoException;

@Component
public class ZgXmjdDaoImpl extends BsBaseDaoTJdbc implements ZgXmjdDao{

	public String queryCondition(String name) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" select * from zg_xmjd where parent = '");
            sql.append(name);
            sql.append("'");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

}
