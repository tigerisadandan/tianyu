package com.ccthanking.framework.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.model.requestJson;

public interface UserServiceCust extends UserService {
	
	
	String queryUser(String json) throws Exception;
	
	String executeUser(String json, User user, String id, String update) throws Exception;

	String queryUnique(String account, User user);
	

	public String loadAllUser(HttpServletRequest request) throws Exception;

	public String loadDeptUser(HttpServletRequest request) throws Exception;

	public void awardRoleToPerson(String roleid, String rolename, String[] accountNameAndId, User user)
			throws Exception;

	String queryUserFile(HttpServletRequest request, requestJson js) throws Exception;

	// 查询个人信息（主要是首页修改个人信息的时候使用，author by liujs）

	String personInfo(HttpServletRequest request, String json) throws Exception;

}
