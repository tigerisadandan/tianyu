/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.SgBbController.java
 * 创建日期： 2014-04-21 下午 05:57:51
 * 功能：    服务控制类：施工报备
 * 所含类:   SgBbService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-21 下午 05:57:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgbb.service.SgBbService;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;

/**
 * <p>
 * SgBbController.java
 * </p>
 * <p>
 * 功能：施工报备
 * </p>
 * 
 * <p>
 * <a href="SgBbController.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-21
 * 
 */

@Controller
@RequestMapping("/sgbb/sgBbController/")
public class SgBbController {

	private static Logger logger = LoggerFactory
			.getLogger(SgBbController.class);

	@Autowired
	private SgBbService sgBbService;

	/**
	 * 查询json
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "query")
	@ResponseBody
	public requestJson queryCondition(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备查询】", user.getName());
		requestJson j = new requestJson();
		String domresult = "";
		String status = request.getParameter("statuses");
		String type = request.getParameter("type");
		String date_time = request.getParameter("date_time");
		if(!StringUtils.isNotBlank(type)){
			type = "";
		}
		if(!StringUtils.isNotBlank(date_time)){
			date_time = "";
		}
		Map map = new HashMap();
		map.put("status", status);
		map.put("date_time", date_time);
		map.put("type", type);
		domresult = this.sgBbService.queryCondition(json.getMsg(),map);
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
	@RequestMapping(value = "insert")
	@ResponseBody
	protected requestJson insert(final HttpServletRequest request,
			requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备新增】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.sgBbService.insert(json.getMsg());
		if (Constants.getBoolean("DATA_SYNC_SGBB", false)) {
			sgBbService.insertToOld(resultVO);
		}
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
	@RequestMapping(value = "update")
	@ResponseBody
	protected requestJson update(HttpServletRequest request, requestJson json)
			throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备修改】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		resultVO = this.sgBbService.update(json.getMsg());
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
	@RequestMapping(value = "delete")
	@ResponseBody
	public requestJson delete(HttpServletRequest request)
			throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备删除】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		String sgbb_uid = request.getParameter("sgbb_uid");
		resultVO = this.sgBbService.delete(sgbb_uid);
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 验证是否有资格进行报备, 若有一并返回报备所需参数
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryBbtj")
	@ResponseBody
	public requestJson queryBbtj(HttpServletRequest request, requestJson json)
			throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备删除】", user.getName());
		requestJson j = new requestJson();
		String resultVO = "";
		String type = request.getParameter("ck_type");
		String person_uid = request.getParameter("person_uid");
		Map map = new HashMap();
		map.put("type", type);
		map.put("person_uid", person_uid);
		resultVO = this.sgBbService.queryBbtj(json.getMsg(), map);
		if (resultVO == null) {
			resultVO = "";
		}
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 打印requestJson对象[打印报表].
	 * 
	 * @author 余健
	 * @param request
	 * @param response
	 * @param json
	 * @return 以文件流的形式打印文件
	 */
	@RequestMapping(value = "query4Print")
	@ResponseBody
	public requestJson query4Print(HttpServletRequest request,
			HttpServletResponse response, requestJson json) {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备表的获取】", user.getName());
//		requestJson j = new requestJson();
		String bbid = "";
		String type = "";

		String parpentPath = "WEB-INF/jasper/bbfu.jasper";
		parpentPath = request.getSession().getServletContext()
				.getRealPath(parpentPath);
		// 获取子报表
//		String pat = request.getSession().getServletContext().getRealPath("WEB-INF/jasper");
		String childPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "\\\\");
		childPath += "WEB-INF\\\\jasper\\\\";
		bbid = request.getParameter("bbid");
		type = request.getParameter("type");
		JasperPrint jasperPrint = sgBbService.query4Print(bbid, parpentPath,childPath);
		if ("word".equals(type)) {
			outputWord(response, jasperPrint, type);
		} else {
			outputPDF(response, jasperPrint, type);
		}
		return null;
	}

	/**
	 * 根据不同的下载方式，提供下载.
	 * 
	 * @author 余健.
	 * @param response
	 * @param jasperPrint
	 * @param type下载类型
	 *            .
	 * 
	 */
	private void outputWord(HttpServletResponse response,
			JasperPrint jasperPrint, String type) {
		try {
			// 获取到线程中的user
			User user = RestContext.getCurrentUser();
			OutputStream ouputStream = response.getOutputStream();
			long time = System.currentTimeMillis();
			String fileName = user.getName();
			// 设置相应参数，以附件形式保存word\
			response.setContentType("application/msword;charset=UTF-8");
			fileName = new String(
					("fileName" + type + time + ".doc").getBytes("UTF-8"),
					"ISO8859_1");
			response.setCharacterEncoding("UTF-8");
			((HttpServletResponse) response).setHeader("Content-Disposition",
					"attachment; filename=" + fileName);
			JRRtfExporter exporter = new JRRtfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.exportReport();// 导出
			ouputStream.close();// 关闭流
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导出为PDF.
	 * 
	 * @author 余健
	 * @param jasperPrint
	 * @param ouputStream
	 */
	private void outputPDF(HttpServletResponse response,
			JasperPrint jasperPrint, String type) {
		try {
			// 获取到线程中的user
			User user = RestContext.getCurrentUser();
			OutputStream ouputStream = response.getOutputStream();
			long time = System.currentTimeMillis();
			String fileName = user.getName();
			// 设置相应参数，以附件形式保存word\
			response.setContentType("application/pdf;charset=UTF-8");
			fileName = new String(
					("fileName" + type + time + ".pdf").getBytes("UTF-8"),
					"ISO8859_1");
			response.setCharacterEncoding("UTF-8");
			((HttpServletResponse) response).setHeader("Content-Disposition",
					"attachment; filename=" + fileName);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.exportReport();// 导出
			ouputStream.close();// 关闭流
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询json
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryZbgg")
	@ResponseBody
	public requestJson queryZbgg(final HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备查询】", user.getName());
		requestJson j = new requestJson();
		String domresult = "";
		domresult = this.sgBbService.queryZbgg(json.getMsg());
		j.setMsg(domresult);
		return j;

	}
	
	/**
	 * 更新报备状态
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateBbzt")
	@ResponseBody
	public requestJson updateBbzt(final HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备查询】", user.getName());
		requestJson j = new requestJson();
		String domresult = "success";
		String bbid = request.getParameter("bbid");
		String status = request.getParameter("status");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		this.sgBbService.updateBbzt(bbid,status,code,name);
		j.setMsg(domresult);
		return j;

	}
	
	/**
	 * 解锁施工人员
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateBbToUnlock")
	@ResponseBody
	public requestJson updateBbToUnlock(final HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备人员解锁】", user.getName());
		requestJson j = new requestJson();
		String domresult = "success";
		String bbid = request.getParameter("uid");
		String optype = request.getParameter("optype");
		try {
			this.sgBbService.updateBbToUnlock(bbid ,optype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(domresult);
		return j;

	}
	/**
	 * 锁定施工人员
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personLock")
	@ResponseBody
	public requestJson personLock(final HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备人员解锁】", user.getName());
		requestJson j = new requestJson();
		String domresult = "success";
		String bbid = request.getParameter("uid");
		try {
			this.sgBbService.personLock(bbid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(domresult);
		return j;

	}
}
