package com.ccthanking.business.sgenter.manage.model;

public interface SgEnterPriseLibrary {
	public String getDenglu_code(); // 获取帐户名称

	public void setDenglu_code(String denglu_code); // 设置帐户名称

	public String getPwd(); // 获取帐户密码

	public void setPwd(String pwd); // 设置账户密码
	
	public String getSg_enterprise_library_uid();
	
	public void setSg_enterprise_library_uid(String sg_enterprise_library_uid);
	
	public String getSg_company_uid();
	
	public void setSg_company_uid(String sg_company_uid);
}
