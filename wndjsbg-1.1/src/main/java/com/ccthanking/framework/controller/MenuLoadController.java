/**
 * @author wangzh
 */
package com.ccthanking.framework.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.model.responseJson;
import com.ccthanking.framework.service.MenuService;
import com.ccthanking.framework.service.UserService;
import com.ccthanking.framework.util.Encipher;
import com.copj.modules.utils.exception.SystemException;

/**
 * @author wangzh
 */
@Controller
@RequestMapping("/menuLoadController")
public class MenuLoadController {

	@Autowired
	@Qualifier("menuServiceImpl")
	private MenuService menuService;

	
	

	@RequestMapping(params = "print", method = RequestMethod.POST)
	protected requestJson print(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "tabHtml") final String tabHtml) throws Exception {

		System.out.println(tabHtml);

		requestJson j = new requestJson();
		// String resultVO = "";
		// resultVO = this.userService.updatedemo(json.getMsg(),user);
		// j.setMsg(resultVO);
		return j;
	}

	@RequestMapping(params = "printHtml")
	@ResponseBody
	protected String printHtml(HttpServletRequest request, requestJson json) throws Exception {
		String showHtml = "";
		
		return "";
	}

}
