/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgsx.service.BuSpywJsgcaqjcsqController.java
 * 创建日期： 2015-04-03 下午 03:13:08
 * 功能：    服务控制类：sg_《建设工程安全监督申报表》
 * 所含类:   BuSpywJsgcaqjcsqService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-03 下午 03:13:08  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.sgsx.service.BuSpywJsgcaqjcsqService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> BuSpywJsgcaqjcsqController.java </p>
 * <p> 功能：sg_《建设工程安全监督申报表》 </p>
 *
 * <p><a href="BuSpywJsgcaqjcsqController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-03
 * 
 */

@Controller
@RequestMapping("/sgsx/buSpywJsgcaqjcsqController")
public class BuSpywJsgcaqjcsqController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcaqjcsqController.class);

    @Autowired
    private BuSpywJsgcaqjcsqService buSpywJsgcaqjcsqService;

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
        logger.info("<{}>执行操作【sg_《建设工程安全监督申报表》查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywJsgcaqjcsqService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryByGcId")
    @ResponseBody
    public requestJson queryByGcId(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【sg_《建设工程安全监督申报表》查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywJsgcaqjcsqService.queryByGcId(request.getParameter("gcid"));
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryDtgc")
    @ResponseBody
    public requestJson queryDtgc(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【sg_《建设工程安全监督申报表》查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywJsgcaqjcsqService.queryDtgc(request.getParameter("ywid"));
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
        	domresult=  this.buSpywJsgcaqjcsqService.toword(response,request.getParameter("uid"),request.getParameter("gcid"));
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
        logger.info("<{}>执行操作【sg_《建设工程安全监督申报表》新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywJsgcaqjcsqService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【sg_《建设工程安全监督申报表》修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywJsgcaqjcsqService.update(json.getMsg());
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
        logger.info("<{}>执行操作【sg_《建设工程安全监督申报表》删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywJsgcaqjcsqService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
