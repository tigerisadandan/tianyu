/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgsx.service.BuSpywSzgcqyrqjdxgczsbController.java
 * 创建日期： 2015-03-25 上午 11:29:02
 * 功能：    服务控制类：sg_02_市政工程企业入区接单项工程注申表
 * 所含类:   BuSpywSzgcqyrqjdxgczsbService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-25 上午 11:29:02  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgsx;


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

import com.ccthanking.business.sgsx.service.BuSpywSzgcqyrqjdxgczsbService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> BuSpywSzgcqyrqjdxgczsbController.java </p>
 * <p> 功能：sg_02_市政工程企业入区接单项工程注申表 </p>
 *
 * <p><a href="BuSpywSzgcqyrqjdxgczsbController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-25
 * 
 */

@Controller
@RequestMapping("/sgsx/buSpywSzgcqyrqjdxgczsbController")
public class BuSpywSzgcqyrqjdxgczsbController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSzgcqyrqjdxgczsbController.class);

    @Autowired
    private BuSpywSzgcqyrqjdxgczsbService buSpywSzgcqyrqjdxgczsbService;

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
        logger.info("<{}>执行操作【sg_02_市政工程企业入区接单项工程注申表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywSzgcqyrqjdxgczsbService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

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
       	 domresult=this.buSpywSzgcqyrqjdxgczsbService.toword(response,request.getParameter("uid"));
            download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}
   }
   
   @RequestMapping(params = "download2")
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

    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【sg_02_市政工程企业入区接单项工程注申表新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSzgcqyrqjdxgczsbService.insert(json.getMsg());
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
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【sg_02_市政工程企业入区接单项工程注申表修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSzgcqyrqjdxgczsbService.update(json.getMsg());
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
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【sg_02_市政工程企业入区接单项工程注申表删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSzgcqyrqjdxgczsbService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
