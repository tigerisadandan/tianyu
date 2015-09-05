package com.ccthanking.framework.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.service.MenuService;

@Controller
@RequestMapping("/MenuTreeController")
public class MenuTreeController {	
	
	@Autowired
	private MenuService menuService;

	/**
	 * 获取TREE
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */	
	//查询方法
	@RequestMapping(params = "getTree")
	@ResponseBody
	public void getTree(final HttpServletRequest request,HttpServletResponse response,
			requestJson json) throws Exception {
		String menuJson = menuService.getAllMenu();
        response.setCharacterEncoding("UTF-8");
        try
        {
            response.getWriter().print(menuJson);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
	}	
}
