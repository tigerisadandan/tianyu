package com.ccthanking.business.xfww;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.xfww.service.XinFangService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;

@Controller
@RequestMapping("/xfww/XinFangController")
public class XinFangController {
	private static Logger logger = LoggerFactory
			.getLogger(XinFangController.class);
	
	@Autowired
	private XinFangService xinfangService;

	/**
	 * 查询json
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */	
	//查询方法
	@RequestMapping(params = "query")
	@ResponseBody
	public requestJson queryCondition(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【信访记录查询】", user.getName());
		requestJson j = new requestJson();
		String domresult = "";
		domresult = this.xinfangService.queryCondition(json.getMsg());
		j.setMsg(domresult);
		return j;

	}
	
	/**
	 * 修改记录.
	 * 
	 * @param request
	 * @param json
	 * @return
	 * @throws Exception
	 * @since v1.00
	 */
	@RequestMapping(params = "update")
	@ResponseBody
	protected requestJson update(HttpServletRequest request, requestJson json)
			throws Exception {
		User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【信访记录修改】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.xinfangService.update(json.getMsg());
		j.setMsg(resultVO);
		return j;
	}
	
	/**
	 * 保存数据json
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insert")
	@ResponseBody
	protected requestJson insert(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【信访记录新增】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.xinfangService.insert(json.getMsg());
		j.setMsg(resultVO);
		return j;
	}	

	
	/**
	 * 删除记录.
	 * 
	 * @param request
	 * @param json
	 * @return
	 * @throws Exception
	 * @since v1.00
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public requestJson delete(HttpServletRequest request, requestJson json)
			throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【信访记录删除】", user.getName());
		requestJson j = new requestJson();
		
		String XINFANG_UID = request.getParameter("XINFANG_UID");
		boolean flag = this.xinfangService.delete(XINFANG_UID);
		j.setSuccess(flag);
		return j;
	}

	
	
	@RequestMapping(params = "detail")
	@ResponseBody
	public requestJson getById(HttpServletRequest request,requestJson json)throws Exception{
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【信访记录明细】",user.getName());
		requestJson j = new requestJson();
		 
		String XINFANG_UID = request.getParameter("XINFANG_UID");
		String resultVo = "";
		resultVo = this.xinfangService.getById(XINFANG_UID);
		j.setMsg(resultVo);
		return j;
	}
	

	public void setXinfangService(XinFangService xinfangService) {
		this.xinfangService = xinfangService;
	}
	
}
