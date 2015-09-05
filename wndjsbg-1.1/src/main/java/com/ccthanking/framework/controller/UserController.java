/**
 * @author wangzh
 */
package com.ccthanking.framework.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.OperatePermManager;
import com.ccthanking.framework.http.listener.OnlineUserEvent;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.model.responseJson;
import com.ccthanking.framework.service.UserService;
import com.ccthanking.framework.service.UserServiceCust;
import com.ccthanking.framework.util.Encipher;
import com.copj.modules.utils.event.EventUtil;
import com.copj.modules.utils.exception.SystemException;

/**
 * @author wangzh
 */
@Controller
@RequestMapping("/userController")
public class UserController {

	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@Autowired
	@Qualifier("usersServiceImpl")
	private UserServiceCust userServiceCust;

	/**
	 * @param 用户注销
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(params = "logout", method = RequestMethod.GET)
	protected ModelAndView logout(final HttpServletRequest request, final ModelAndView mav) throws Exception {
		User user = RestContext.getCurrentUser();
		if (user != null) {
			com.ccthanking.framework.log.LogManager.writeLogoutLog(user);
			//去掉缓存中该用户的操作按钮权限数据 20140819
			OperatePermManager.getInstance().removeSetOperatePerm(user);
			
		}
		request.getSession().invalidate();
		RestContext.clearContext();//防止两个用户菜单出现错乱
		mav.setViewName("index");
		return mav;

	}

	/**
	 * @param 用户登录验证
	 * @param username
	 * @param password
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	protected ModelAndView login(final HttpServletRequest request, final ModelAndView mav,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password)
			throws Exception {
		User user = this.userService.getUserByUsernameAndPassword(username, password);
		if (user != null) {

			// 设置IP
			user.setIP(getRemoteAddrIp(request));

			request.getSession().setAttribute("userId", user.getAccount());

			// 设置threadLocal信息
			// RestContext.setCurrentSession(request.getSession());
			// RestContext.setCurrentUser(user);
			RestContext.setCurrentUserInThread(user);

			// request.getSession().setAttribute(Globals.USER_KEY, user);

			if (Constants.LOGIN_TYPE.equals("springsecurity")) {
				UsernamePasswordAuthenticationToken uptoken = new UsernamePasswordAuthenticationToken(
						user.getAccount(), user.getPassword(), user.getAuthorities());
				uptoken.setDetails(user);
				SecurityContextHolder.getContext().setAuthentication(uptoken);
			}
			
			//用户按钮权限初始化到缓存中  20140819
			OperatePermManager.getInstance().initOperate(user);
			EventUtil.addEvent(new OnlineUserEvent(request.getSession().getId(), username, user.getName(), new Date()));

			LogManager.writeLoginLog(user, LogManager.LOGIN_STATUS_SUCCESS);
			String pagepath = "/jsp/framework/portal/frame_homepage";

			if (User.SUPER_USER.equals(user.getPersonKind())) {
				pagepath = "/jsp/framework/portal/frame_admin";
			}

			mav.setViewName(pagepath);

			return mav;

		} else {
			request.setAttribute("error", "用户名不存在或密码不正确");
			mav.setViewName("/index");
			return mav;
		}

	}

	/**
	 * @param 用户登录验证
	 * @param username
	 * @param password
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	protected ModelAndView loginGet(final HttpServletRequest request, final ModelAndView mav) throws Exception {
		User user = RestContext.getCurrentUser();
		if (user != null) {
			mav.setViewName("/jsp/framework/portal/frame_homepage");
			return mav;
		} else {
			mav.setViewName("/index");
			return mav;
		}

	}

	@RequestMapping(value = "checkvalid")
	@ResponseBody
	public responseJson checkvalid(final HttpServletRequest request, requestJson js) throws Exception {
		System.out.println(js.getMsg());
		User user = RestContext.getCurrentUser();
		System.out.println(user.getAccount());
		responseJson j = new responseJson();

		// String menuTreeHtml = menuService.getMenuTreeHtml(user);

		// j.setMsg(menuTreeHtml);
		j.setSuccess(true);
		return j;
	}

	/**
	 * @param 保存用户信息
	 * @return org.springframework.web.servlet.ModelAndView
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	protected Map<String, Object> regPost(final HttpServletRequest reqeuest,
			@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password,
			@RequestParam(value = "realName") final String realName, @RequestParam(value = "email") final String email,
			@RequestParam(value = "sex") final Integer sex) {
		Map<String, Object> model = new HashMap<String, Object>();
		/*
		 * User user = new User();
		 * user.setLastLoginIp(reqeuest.getRemoteAddr()); long now =
		 * System.currentTimeMillis(); user.setLastLoginTime(now);
		 * user.setLoginTimes(0); user.setLogonIp(reqeuest.getRemoteAddr());
		 * user.setPasswrod(password); user.setRealName(realName);
		 * user.setRegTime(now); user.setRole(0); user.setStatus(0);
		 * user.setUserName(username); user.setUserSex(sex); model.put("result",
		 * this.userService.insert(user));
		 */
		return model;
	}

	@RequestMapping(value = "print", method = RequestMethod.POST)
	protected requestJson print(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "tabHtml") final String tabHtml) throws Exception {

		System.out.println(tabHtml);

		requestJson j = new requestJson();
		// String resultVO = "";
		// resultVO = this.userService.updatedemo(json.getMsg(),user);
		// j.setMsg(resultVO);
		return j;
	}

	@RequestMapping(params = "queryUser")
	@ResponseBody
	public requestJson queryUser(HttpServletRequest request, requestJson json) {
		requestJson j = new requestJson();
		try {
			String domResult = this.userServiceCust.queryUser(json.getMsg());
			j.setMsg(domResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	@RequestMapping(params = "awardRoleToUser")
	@ResponseBody
	protected requestJson awardRoleToUser(HttpServletRequest request, requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		String account = request.getParameter("account");
		String checkValue = request.getParameter("checkValue");
		System.out.println("awardRoleToUser : " + checkValue);

		String[] roleNameAndId = "".equals(checkValue) ? new String[0] : checkValue.split(",");
		userService.awardRoleToUser(account, roleNameAndId, user);
		requestJson j = new requestJson();
		j.setMsg("");
		return j;
	}
	
	@RequestMapping(params = "awardRoleToPerson")
	@ResponseBody
	protected requestJson awardRoleToPerson(HttpServletRequest request, requestJson json) throws Exception {
		User user = (User) RestContext.getCurrentUser();
		String roleid = request.getParameter("roleid");
		String rolename = request.getParameter("rolename");
		String checkValue = request.getParameter("checkValue");

		String[] accountNameAndId = "".equals(checkValue) ? new String[0] : checkValue.split(",");
		userServiceCust.awardRoleToPerson(roleid, rolename, accountNameAndId, user);
		requestJson j = new requestJson();
		j.setMsg("");

		// String account = request.getParameter("account");
		// String domresult = "";
		// domresult = this.userService.resetPw(account);
		// j.setMsg(domresult);
		return j;

	}

	@RequestMapping(params = "resetPw")
	@ResponseBody
	public requestJson resetPw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String account = request.getParameter("account");
		String domresult = "";
		domresult = this.userServiceCust.resetPw(account);
		j.setMsg(domresult);
		return j;

	}

	@RequestMapping(value = "modifyPassword")
	@ResponseBody
	protected requestJson modifyPassword(HttpServletRequest request, requestJson json) throws Exception {

		User user = RestContext.getCurrentUser();
		JSONArray list = new BaseVO().doInitJson(json.getMsg());
		JSONObject jsonObj = (JSONObject) list.get(0);

		String oldPass = jsonObj.getString("oldpassword");
		if (oldPass == null) {
			oldPass = "";
		}
		String newPass = jsonObj.getString("newpassword");
		if (newPass == null)
			newPass = "";

		// 转为大写 密码不区分大小写
		oldPass = oldPass.toUpperCase();

		requestJson j = new requestJson();

		String newPass_md5 = "";
		if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
			oldPass = DigestUtils.md5Hex(oldPass);
			newPass_md5 = DigestUtils.md5Hex(newPass.toUpperCase());
		} else {
			oldPass = Encipher.EncodePasswd(oldPass);
			newPass_md5 = Encipher.EncodePasswd(newPass.toUpperCase());
		}

		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			// String sqlQuery =
			// "select t.password from fs_org_person t where t.account='" +
			// user.getAccount() + "'";

			// 增加用户from来源，以决定后续修改哪个表
			String sqlQuery = "SELECT u.pwd FROM \"USERS\" u WHERE u.users_uid=" + user.getAccount();
			String[][] res = DBUtil.query(sqlQuery);
			String password = res[0][0];
			if (password == null)
				password = "";

			if (!oldPass.equals(password)) {
				j.setPrompt("输入的旧密码不正确!");
				SystemException.handleException("输入的旧密码不正确!");
			}
			// newPass = Encipher.EncodePasswd(newPass);

			String sql = "update \"USERS\" t set t.pwd='" + newPass_md5 + "', MIMA='" + newPass
					+ "', updated_date = SYSDATE,updated_by ='" + user.getAccount() + "' where  users_uid="
					+ user.getAccount();

			boolean result = DBUtil.exec(conn, sql);
			conn.commit();
			CacheManager.broadcastChanges(CacheManager.CACHE_USER, user.getAccount(), CacheManager.UPDATE);
			
			LogManager.writeUserLog("", "", Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,
					"用户 [" + user.getAccount() + " / " + user.getName() + "] 修改密码成功", user, "ACCOUNT",
					user.getAccount());
			j.setMsg("");

			return j;
		} catch (Exception e) {
			conn.rollback();
			LogManager.writeUserLog("", "", Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE,
					"用户 [" + user.getAccount() + " / " + user.getName() + "] 修改密码失败!" + e.getMessage(), user,
					"ACCOUNT", user.getAccount());
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
			conn = null;
		}
		return j;
	}
	
	//********************用户添加/修改*****************************//

	@RequestMapping(params = "executeUser")
	@ResponseBody
	protected requestJson executeUser(HttpServletRequest request, requestJson json) throws Exception {
		User user = (User) RestContext.getCurrentUser();;
		requestJson j = new requestJson();
		String id = request.getParameter("id");
		String update = request.getParameter("update");
		try {
			String domResult = this.userServiceCust.executeUser(json.getMsg(), user, id, update);
			j.setMsg(domResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	@RequestMapping(params = "queryUnique")
	@ResponseBody
	protected String queryUnique(HttpServletRequest request, requestJson json) throws Exception {
		User user = (User) RestContext.getCurrentUser();;
		String account = request.getParameter("account");
		String domResult = this.userServiceCust.queryUnique(account, user);
		return domResult;
	}
	
	//********************用户添加/修改*****************************//

	public static String getRemoteAddrIp(HttpServletRequest request) {
		String ipFromNginx = getHeader(request, "X-Real-IP");
		return StringUtils.isEmpty(ipFromNginx) ? request.getRemoteAddr() : ipFromNginx;
	}
	private static String getHeader(HttpServletRequest request, String headName) {
		String value = request.getHeader(headName);
		return !StringUtils.isBlank(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
	}

}
