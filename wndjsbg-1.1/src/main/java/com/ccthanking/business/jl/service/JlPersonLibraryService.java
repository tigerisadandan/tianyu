package com.ccthanking.business.jl.service;

import java.util.Map;


public interface JlPersonLibraryService {
	String queryCondition(String json) throws Exception;
	String queryNotNull(String json) throws Exception;
	
	
	 String updateShenhe(String json,String u_id,String status,String t_id) throws Exception;
	 
	 String updateStatusOne(String u_id,String js) throws Exception;
	 
	 String queryCodeIsEmpty(String json) throws Exception;
	 
	 
	 String queryStatusIsEmpty(String json,String bz,String personUID) throws Exception;
	 
	 String updateCopyPerson(String t_id,String u_id) throws Exception;
	 
	 
	 String tuihui(String ids,String yijian) throws Exception;
	 
	String update(String msg, String jg) throws Exception;
	
	void updateDshxx(Map<String, String> map) throws Exception;
	String updatePerson(String msg);
	
	String jiesuo(String id) throws Exception;
	
	String suoding(String id, String yijian, String sjfw, String jzY) throws Exception;
}
