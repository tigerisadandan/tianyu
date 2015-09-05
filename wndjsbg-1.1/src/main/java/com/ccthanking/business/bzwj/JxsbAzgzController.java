/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.JxsbAzgzController.java
 * 创建日期： 2015-01-16 下午 12:03:25
 * 功能：    服务控制类：机械设备安装告知
 * 所含类:   JxsbAzgzService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-16 下午 12:03:25  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.bzwj.service.JxsbAzgzService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> JxsbAzgzController.java </p>
 * <p> 功能：机械设备安装告知 </p>
 *
 * <p><a href="JxsbAzgzController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-16
 * 
 */

@Controller
@RequestMapping("/bzwj/jxsbAzgzController")
public class JxsbAzgzController {

	private static Logger logger = LoggerFactory.getLogger(JxsbAzgzController.class);

    @Autowired
    private JxsbAzgzService jxsbAzgzService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }

    
    @RequestMapping(params = "download2")
    public void download(HttpServletRequest request, HttpServletResponse response,String path){
		FileUploadService ser = new FileUploadService();
		String encoding = request.getCharacterEncoding();
		response.setCharacterEncoding(encoding);
		logger.info("<{}>>执行操作【资质转PDF】"+path,"--");
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
    
    @RequestMapping(value = "download")
    @ResponseBody
    public void downloadCondition(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
        try {
        	domresult= this.jxsbAzgzService.toword(response,request.getParameter("uid"));
            download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}
    }
    
    @RequestMapping(value = "downloadAzgc")
    @ResponseBody
    public void downloadConditionAzgc(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
        try {
        	domresult= this.jxsbAzgzService.towordAzgc(response,request.getParameter("uid"));
            download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}

    }
    
    @RequestMapping(value = "downloadJcys")
    @ResponseBody
    public void downloadConditionJcys(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
        
        try {
        	domresult= this.jxsbAzgzService.towordJcys(response,request.getParameter("uid"));
            download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}

    }
    
    @RequestMapping(value = "downloadCxgc")
    @ResponseBody
    public void downloadConditionCxgc(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");


        try {
        	domresult= this.jxsbAzgzService.towordCxgc(response,request.getParameter("uid"));
            download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}


    }
    
    @RequestMapping(value = "queryByView")
    @ResponseBody
    public requestJson queryByView(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryByView(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryAzyr")
    @ResponseBody
    public requestJson queryAzyr(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryAzyr(json.getMsg());
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
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbAzgzService.insert(json.getMsg());
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
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String type=request.getParameter("type");
        resultVO = this.jxsbAzgzService.update(json.getMsg(),type);
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(params = "UpdateSH")
    @ResponseBody
    protected requestJson UpdateSH(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String ids=request.getParameter("ids");
        String type=request.getParameter("type");
        resultVO = this.jxsbAzgzService.UpdateSH(ids,type);
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
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbAzgzService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params = "queryAzgc")
    @ResponseBody
    public requestJson queryAzgc(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryAzgc(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryJcys")
    @ResponseBody
    public requestJson queryJcys(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryJcys(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryCxgc")
    @ResponseBody
    public requestJson queryCxgc(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryCxgc(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "querySbzl")
    @ResponseBody
    public requestJson querySbzl(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.querySbzl(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryDslSb")
    @ResponseBody
    public requestJson queryDslSb(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.queryDslSb(json.getMsg(),request.getParameter("type"));
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "querySyglCl")
    @ResponseBody
    public requestJson querySyglCl(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备安装告知查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbAzgzService.querySyglCl(json.getMsg());
        j.setMsg(domresult);
        return j;

    }

}
