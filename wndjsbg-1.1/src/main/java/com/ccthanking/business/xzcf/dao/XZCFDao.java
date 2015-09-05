package com.ccthanking.business.xzcf.dao;


import java.sql.Connection;
import java.util.List;

import com.ccthanking.framework.dao.BsBaseDaoable;

public interface XZCFDao extends BsBaseDaoable{
	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
    public String queryCondition(String json);

    
	public String queryDXTYPE(String gcuid, String jsuid);


	public String queryReportxx();


	public List<?> findHeaderPrint(String xzcfuid);


	public String queryXzcfMsg(String gcuid);


	public String queryXzcfXX(String xzcfuid);
	
	//笔录删除
	public boolean delete(Connection conn,String xzcfuid);
	
}