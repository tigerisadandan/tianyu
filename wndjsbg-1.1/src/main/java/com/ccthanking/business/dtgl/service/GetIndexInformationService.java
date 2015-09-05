package com.ccthanking.business.dtgl.service;

public interface GetIndexInformationService {
	//通过权限代码和用户名UID查询是否有权
	String getQuanxianService(String userId);
	
	//获取柱状图所需要的数据
	String getChartData(String before,String after);
	
	//查询整改单信息
	String queryZGD();
	
	//查询考勤信息
	String queryKaoqingInfo();
	
	
}
