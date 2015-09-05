/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.WaiChuDengJiController.java
 * 创建日期： 2015-05-21 下午 03:32:32
 * 功能：    服务控制类：外出登记
 * 所含类:   WaiChuDengJiService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-21 下午 03:32:32  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.nbgl.service.WaiChuDengJiService;
import com.ccthanking.business.dtgl.nbgl.vo.WaiChuDengJiVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;

/**
 * <p>
 * WaiChuDengJiController.java
 * </p>
 * <p>
 * 功能：外出登记
 * </p>
 * 
 * <p>
 * <a href="WaiChuDengJiController.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-21
 * 
 */

@Controller
@RequestMapping("/nbgl/waiChuDengJiController")
public class WaiChuDengJiController {

	private static Logger logger = LoggerFactory
			.getLogger(WaiChuDengJiController.class);

	@Autowired
	private WaiChuDengJiService waiChuDengJiService;

	/**
	 * 查询json
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "query")
	@ResponseBody
	public requestJson queryCondition(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【外出登记查询】", user.getName());
		requestJson j = new requestJson();
		String domresult = "";
		domresult = this.waiChuDengJiService.queryCondition(json.getMsg());
		j.setMsg(domresult);
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
		logger.info("<{}>执行操作【外出登记新增】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.waiChuDengJiService.insert(json.getMsg());
		j.setMsg(resultVO);
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
		logger.info("<{}>执行操作【外出登记修改】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.waiChuDengJiService.update(json.getMsg());
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
		logger.info("<{}>执行操作【外出登记删除】", user.getName());
		requestJson j = new requestJson();
		String wcdjId = request.getParameter("wcdjId");
		boolean flag = this.waiChuDengJiService.delete(wcdjId);
		j.setSuccess(flag);
		return j;
	}

	/**
	 * 修改外出记录状态 -----修改为返回.
	 * 
	 * @param request
	 * @param json
	 * @return
	 * @throws Exception
	 * @since v1.00
	 */
	@RequestMapping(params = "updateState")
	@ResponseBody
	public requestJson updateState(HttpServletRequest request, requestJson json)
			throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【外出登记修改】", user.getName());
		requestJson j = new requestJson();

		String id = request.getParameter("wcdjId");
		String domresult = "";
		try {
			waiChuDengJiService.updateState(id);
			domresult = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(domresult);
		return j;
	}
	@RequestMapping(params = "detail")
	@ResponseBody
	public requestJson getById(HttpServletRequest request,requestJson json)throws Exception{
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【外出登记明细】",user.getName());
		requestJson j = new requestJson();
		 
		String wcdjId = request.getParameter("wcdjId");
		String resultVo = "";
		resultVo = this.waiChuDengJiService.getById(wcdjId);
		j.setMsg(resultVo);
		return j;
	}
	
	

}
