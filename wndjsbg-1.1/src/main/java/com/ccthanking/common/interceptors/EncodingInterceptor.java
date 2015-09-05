package com.ccthanking.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;

@Repository
public class EncodingInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(EncodingInterceptor.class.getName());

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		logger.debug("Interceptor url ={}", request.getRequestURI());

		Object userid = request.getSession().getAttribute("userId");
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.preHandle(request, response, object);

	}

}
