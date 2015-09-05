/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jlbb.service.JlbbController.java
 * 创建日期： 2015-01-28 上午 09:10:22
 * 功能：    服务控制类：监理报备
 * 所含类:   JlbbService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:10:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jlbb.service.JlbbService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> JlbbController.java </p>
 * <p> 功能：监理报备 </p>
 *
 * <p><a href="JlbbController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Controller
@RequestMapping("/jlbb/jlbbController")
public class JlbbController {

	private static Logger logger = LoggerFactory.getLogger(JlbbController.class);

    @Autowired
    private JlbbService jlbbService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监理报备查询】",user.getName());
        requestJson j = new requestJson();
        String statuses = request.getParameter("statuses");
        String date_time = request.getParameter("date_time");
        String type = request.getParameter("type");
		if(!StringUtils.isNotBlank(type)){
			type = "";
		}
		if(!StringUtils.isNotBlank(date_time)){
			date_time = "";
		}
        Map<String,String> map = new HashMap<String, String>();
        map.put("statuses", statuses);
        map.put("date_time", date_time);
        map.put("type", type);
        String domresult = "";
        domresult = this.jlbbService.queryCondition(json.getMsg(),map);
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
    @RequestMapping(value = "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监理报备修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlbbService.update(json.getMsg());
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
    @RequestMapping(value = "updateBbzt")
    @ResponseBody
    protected requestJson updateBbzt(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理报备状态修改】",user.getName());
		requestJson j = new requestJson();
		String domresult = "success";
		String bbid = request.getParameter("bbid");
		String status = request.getParameter("status");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		this.jlbbService.updateBbzt(bbid,status,code,name);
		j.setMsg(domresult);
    	return j;
    }
    
    /**
     * 解锁人员.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(value = "updateBbToUnlock")
    @ResponseBody
    protected requestJson updateBbToUnlock(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理报备修改】",user.getName());
    	requestJson j = new requestJson();
		String domresult = "success";
		String bbid = request.getParameter("uid");
		String optype = request.getParameter("optype");
		try {
			this.jlbbService.updateBbToUnlock(bbid ,optype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(domresult);
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
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监理报备删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlbbService.delete(json.getMsg());
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
	public requestJson query4Print(HttpServletRequest request, HttpServletResponse response, requestJson json) {
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【施工报备表的获取】", user.getName());
		String bbid = "";
		String type = "";

		String parpentPath = "WEB-INF/jasper/jlbbry.jasper";
		parpentPath = request.getSession().getServletContext().getRealPath(parpentPath);
		// 获取子报表
		String childPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "\\\\");
		childPath += "WEB-INF\\\\jasper\\\\";
		bbid = request.getParameter("bbid");
		type = request.getParameter("type");
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = jlbbService.query4Print(bbid, parpentPath, childPath);

			if ("word".equals(type)) {
				outputWord(response, jasperPrint, type);
			} else {
				outputPDF(response, jasperPrint, type);
			}

		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
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
	private void outputWord(HttpServletResponse response, JasperPrint jasperPrint, String type) {
		try {
			// 获取到线程中的user
			User user = RestContext.getCurrentUser();
			OutputStream ouputStream = response.getOutputStream();
			long time = System.currentTimeMillis();
			String fileName = user.getName();
			// 设置相应参数，以附件形式保存word\
			response.setContentType("application/msword;charset=UTF-8");
			fileName = new String(("fileName" + type + time + ".doc").getBytes("UTF-8"), "ISO8859_1");
			response.setCharacterEncoding("UTF-8");
			((HttpServletResponse) response).setHeader("Content-Disposition", "attachment; filename=" + fileName);
			JRRtfExporter exporter = new JRRtfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
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
	private void outputPDF(HttpServletResponse response, JasperPrint jasperPrint, String type) {
		try {
			// 获取到线程中的user
			User user = RestContext.getCurrentUser();
			OutputStream ouputStream = response.getOutputStream();
			long time = System.currentTimeMillis();
			String fileName = user.getName();
			// 设置相应参数，以附件形式保存word\
			response.setContentType("application/pdf;charset=UTF-8");
			fileName = new String(("fileName" + type + time + ".pdf").getBytes("UTF-8"), "ISO8859_1");
			response.setCharacterEncoding("UTF-8");
			((HttpServletResponse) response).setHeader("Content-Disposition", "attachment; filename=" + fileName);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
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
	
	@RequestMapping(params = "print")
	@ResponseBody
	public void print(HttpServletRequest request, HttpServletResponse response, requestJson json) {
		String bbid = request.getParameter("bbid");
		String path = "";
		try {
			path = this.jlbbService.toword(response, bbid);
			download(request,response,path);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}

		//return path;
	}
    
	@RequestMapping(params = "download")
    public void download(HttpServletRequest request, HttpServletResponse response,String path){
		FileUploadService ser = new FileUploadService();
		String encoding = request.getCharacterEncoding();
		response.setCharacterEncoding(encoding);
		//String path = request.getParameter("path");
		File file = new File(path);
         try {
			if (file.exists()) {
			       int bytes = 0;
			       ServletOutputStream op = response.getOutputStream();
			       
			       response.setContentType(ser.getMimeType(file));
			       response.setContentLength((int) file.length());
					// response.setCharacterEncoding("UTF-8");
			       response.setHeader( "Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord(file.getName()) + "\"" );
			       byte[] bbuf = new byte[1024];
			       DataInputStream in = new DataInputStream(new FileInputStream(file));
			       while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
			           op.write(bbuf, 0, bytes);
			       }
			       in.close();
			       op.flush();
			       op.close();
			       
			       
			   }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  }

}
