package com.ccthanking.business.dtgl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.service.QueryZGDService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;



@Controller
@RequestMapping("/QueryZGDController")
public class QueryZGDController{
	private static Logger logger = LoggerFactory
			.getLogger(QueryZGDController.class);
	
	private  QueryZGDService queryZGDService;
	/**
	 * 查询整改单
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryZGD")
	@ResponseBody
	public requestJson queryZGD(final HttpServletRequest request) throws Exception {
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【查询用户权限记录查询】", user.getName());				

		requestJson j = new requestJson();
		String domresult = "";
	   domresult = queryZGDService.queryZGD();
	    j.setMsg(domresult);
	    
		return j;
	}
	
	
	
	public QueryZGDService getQueryZGDService() {
		return queryZGDService;
	}
	
	@Autowired
	public void setQueryZGDService(QueryZGDService queryZGDService) {
		this.queryZGDService = queryZGDService;
	}
	
}
