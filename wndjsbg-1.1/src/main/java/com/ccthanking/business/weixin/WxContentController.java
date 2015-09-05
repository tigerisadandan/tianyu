package com.ccthanking.business.weixin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.business.weixin.service.WxContentService;

@Controller
@RequestMapping("/wxContentController/")
public class WxContentController {
	
	@Autowired
	private WxContentService wxContentService;
	
	@RequestMapping(value = "listContet")
	@ResponseBody
	public String listContet(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		String channelid=request.getParameter("channelid");
		String domresult = this.wxContentService.listContentS(null, channelid, 100);
		return domresult;
	}
	
	
	/**
	 * 查询json
	 */
	@RequestMapping(value = "query")
	@ResponseBody
	public requestJson queryCondition(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String domresult = "";
		domresult = this.wxContentService.queryCondition(json.getMsg());
		j.setMsg(domresult);
		return j;

	}
	
	
	
	@RequestMapping(value = "getWxContentVO")
	@ResponseBody
	public requestJson getWxContentVO(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String contentid=request.getParameter("contentid");
		String domresult = "";
		domresult = this.wxContentService.getWxContentVO(json.getMsg(),contentid);
		j.setMsg(domresult);
		return j;

	}
	
	/**
	 * @param 跳转中转类
	 */
	@RequestMapping(value = "contentinput")
	protected ModelAndView contentInput( HttpServletRequest request,  ModelAndView mav) throws Exception {
		User user = RestContext.getCurrentUser();
		String pagepath = "/jsp/weixin/content_input";
		mav.setViewName(pagepath);
		return mav;
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
		resultVO = this.wxContentService.insert(json.getMsg());		
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
		resultVO = this.wxContentService.update(json.getMsg());
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 删除记录.
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public requestJson delete(HttpServletRequest request)
			throws Exception {
		User user = RestContext.getCurrentUser();
		requestJson j = new requestJson();
		String id=request.getParameter("content_uid");
		String resultVO = "";
		resultVO = this.wxContentService.delete(id);
		j.setMsg(resultVO);
		return j;
	}
	

}
