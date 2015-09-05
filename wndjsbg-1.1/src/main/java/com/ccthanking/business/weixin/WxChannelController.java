package com.ccthanking.business.weixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.business.weixin.service.WxChannelService;

@Controller
@RequestMapping("/wndjsbg/wxChannelController/")
public class WxChannelController {
	
	@Autowired
	private WxChannelService wxChannelService;
	/**
	 * 查询json
	 */
	@RequestMapping(value = "query")
	@ResponseBody
	public void queryCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = RestContext.getCurrentUser();
		String domresult = "";
		domresult = this.wxChannelService.queryCondition(null);
		response.setCharacterEncoding("UTF-8");		
		try {
			response.getWriter().print(domresult);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 查询所有权限范围内的栏目信息
	 * 
	 * */
	@RequestMapping(value = "rightCondition")
	@ResponseBody
	public void queryRightCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = RestContext.getCurrentUser();
		String domresult = "";
		domresult = this.wxChannelService.queryRightCondition(null);
		response.setCharacterEncoding("UTF-8");		
		try {
			response.getWriter().print(domresult);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * 保存数据json
	 */
	@RequestMapping(value = "insert")
	@ResponseBody
	protected requestJson insert(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.wxChannelService.insert(json.getMsg());		
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 修改记录.
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	protected requestJson update(HttpServletRequest request, requestJson json)
			throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.wxChannelService.update(json.getMsg());
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 删除记录.
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	protected requestJson delete(HttpServletRequest request)
			throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String channel_uid=request.getParameter("channel_uid");
		String resultVO = "";
		resultVO = this.wxChannelService.delete(channel_uid);
		j.setMsg(resultVO);
		return j;
	}
	
	
	
	 /**
     * 栏目分配权限时，加载角色数据
     * */
	@ResponseBody
	@RequestMapping(value="loadAllAdmUsers")
	public void loadAllAdmUsers(HttpServletRequest request, HttpServletResponse response) {
		String CHANNEL_UID = request.getParameter("CHANNEL_UID");
		Map temp=new HashMap();
		temp.put("CHANNEL_UID", CHANNEL_UID);
		
		String userJson = wxChannelService.loadAllAdmUsers(temp);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(userJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /**
     * 栏目分配权限时，加载角色数据
     * */
	@ResponseBody
	@RequestMapping(value="loadAllRecUsers")
	public void loadAllRecUsers(HttpServletRequest request, HttpServletResponse response) {
		String CHANNEL_UID = request.getParameter("CHANNEL_UID");
		Map temp=new HashMap();
		temp.put("CHANNEL_UID", CHANNEL_UID);
		
		String userJson = wxChannelService.loadAllRecUsers(temp);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(userJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
