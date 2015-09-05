/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzController.java
 * 创建日期： 2014-06-19 下午 04:51:04
 * 功能：    服务控制类：审批业务流转实例
 * 所含类:   BuSpYwlzService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:51:04  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.ywlz;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.ywlz.service.BuSpYwlzService;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> BuSpYwlzController.java </p>
 * <p> 功能：审批业务流转实例 </p>
 *
 * <p><a href="BuSpYwlzController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

@Controller
@RequestMapping("/ywlz/buSpYwlzController")
public class BuSpYwlzController {

	private static Logger logger = LoggerFactory.getLogger(BuSpYwlzController.class);

    @Autowired
    private BuSpYwlzService buSpYwlzService;

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
        logger.info("<{}>执行操作【审批业务流转实例查询】",user.getName());
        requestJson j = new requestJson();
        String spyw_uid_res=request.getParameter("spyw_uid_res");
        String domresult = "";
        domresult = this.buSpYwlzService.queryCondition(json.getMsg(), spyw_uid_res);
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryClType")
    @ResponseBody
    public requestJson queryClType(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转实例查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpYwlzService.queryClType(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryByProjectsid")
    @ResponseBody
    public requestJson queryByProjectsid(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转实例查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("projects_uid");
        domresult = this.buSpYwlzService.queryByProjectsid(id);
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
        logger.info("<{}>执行操作【审批业务流转实例新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务流转实例修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzService.update(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务流转实例删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    /**
     * 查询业务材料
     * */
    @RequestMapping(value = "queryYwcl")
    @ResponseBody
    public String queryYwcl(HttpServletRequest request, requestJson json) throws Exception {
       
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转实例查询】",user.getName());
        String resultVO = "";
        Map map = new HashMap();
        String spyw_uid = request.getParameter("spyw_uid");
        String ywlz_uid = request.getParameter("ywlz_uid");
        if (StringUtils.isNotBlank(ywlz_uid)) {
			map.put("ywlz_uid", ywlz_uid);
			map.put("optype", Constants.YWLZ_DO_EDIT);
		}else{
			map.put("spyw_uid", spyw_uid);
			map.put("optype", Constants.YWLZ_DO_INSERT);
		}
       
        map.put("spyw_uid", spyw_uid);
        
        resultVO = this.buSpYwlzService.queryYwcl(map);
        return resultVO;
    }
    
    
    @RequestMapping(params = "getSpCount")
    @ResponseBody
    public requestJson getSpCount(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String spywuid=request.getParameter("spywuid");
        String resultVO = "";
        resultVO = this.buSpYwlzService.getSpCount(spywuid);
        j.setMsg(resultVO);
        return j;
    }
    
    //sfysp通过审批业务流转UID查询
    @RequestMapping(params = "sfysp")
    @ResponseBody
    public requestJson sfysp(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String ywlzuid=request.getParameter("ywlzuid");
        String resultVO = "";
        resultVO = this.buSpYwlzService.sfysp(ywlzuid);
        j.setMsg(resultVO);
        return j;
    }
    
    
    /**
     * 手续审批大菜单获取待审统计数据
     * */
    @RequestMapping(params = "getAllDsCount")
    @ResponseBody
    public requestJson getAllDsCount(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzService.getAllDsCount();
        j.setMsg(resultVO);
        return j;
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
    
    @RequestMapping(value = "downloadGc")
    @ResponseBody
    public void downloadGc(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
       
        try {
        	 domresult=this.buSpYwlzService.downloadGc(response,request.getParameter("uid"),request.getParameter("bh"));
             download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}
    }
    
    @RequestMapping(value = "downloadJz")
    @ResponseBody
    public void downloadJz(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
       
        try {
        	 domresult=this.buSpYwlzService.downloadJz(response,request.getParameter("uid"));
             download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}
    }
    
    @RequestMapping(value = "downloadSz")
    @ResponseBody
    public void downloadSz(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【资质转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
       
        try {
        	 domresult=this.buSpYwlzService.downloadSz(response,request.getParameter("uid"));
             download(request,response,domresult);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}
    }
}
