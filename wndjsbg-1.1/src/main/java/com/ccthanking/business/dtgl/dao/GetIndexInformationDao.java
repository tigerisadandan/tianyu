package com.ccthanking.business.dtgl.dao;

public interface GetIndexInformationDao {

	String getQuanxian(String userId);
	
	String getChartData(String before,String after);
	
	String queryZGD();
	
	String queryKaoqingInfo();
}
