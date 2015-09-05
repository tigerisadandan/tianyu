package com.ccthanking.business.jl.dao;

import java.util.Map;

import com.ccthanking.framework.dao.BsBaseDaoable;

public interface JlEnterpriseDao extends BsBaseDaoable{
	
	String queryCondition(String json) throws Exception;
	
	 String getDengluCode() throws Exception;
	 
	 String queryspyjCondition(String json) throws Exception;
	 
	 
	 String queryOldCondition(String json) throws Exception;

	String tuihui(String ids, String yijian) throws Exception;
	
	 void updateDshxx(Map map) throws Exception;

	String updateCopyByStatus(Map map);
}
