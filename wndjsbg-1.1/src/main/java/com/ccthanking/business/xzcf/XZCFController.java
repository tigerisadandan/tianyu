/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.jhgl.service.WorkPlanController.java
 * 创建日期： 2015-05-22 下午 12:00:14
 * 功能：    服务控制类：计划管理
 * 所含类:   WorkPlanService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-22 下午 12:00:14  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.xzcf;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.xzcf.service.XZCFService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * <p> XZCFControllerr.java </p>
 * <p> 功能：行政处罚 </p>
 *
 * <p><a href="XZCFController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-07-28
 * 
 */
@Controller
@RequestMapping("/xzcf/XZCFController")
public class XZCFController {

	private static Logger logger = LoggerFactory.getLogger(XZCFController.class);

    @Autowired
    private XZCFService xzcfService;
    
    /**
     * 打印 工作计划
     * @param request
     * @param response
     * @param json
     * @return 
     * @throws Exception 
     */
	@RequestMapping(params = "printXzcf")
	@ResponseBody
	public String print(HttpServletRequest request, HttpServletResponse response, requestJson json) throws Exception {
		String xzcfuid = request.getParameter("xzcfuid");
		String path = "";
		try {
			path = this.xzcfService.toword(response,xzcfuid);
			download(request,response,path);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印行政处罚清单出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("打印错误提示", e.getMessage(), request, response);
		}
		return path;
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
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

  }
    /**
     * 查询 部门 名称
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDeptName") // select o.org_name from organize o where  organize_uid =16
    @ResponseBody
    public requestJson queryCodeIsEmpty(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
        String deptId = request.getParameter("deptId");
        requestJson j = new requestJson();
        String domresult = "";
       // String msg = "{\"code\":\""+request.getParameter("code")+"\",\"id\":\""+request.getParameter("id")+"\"}";
        domresult = this.xzcfService.queryCondition(deptId);
        j.setMsg(domresult);
        return j;


    }
    /**
     * 查询当前用户所在部门的下周计划
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDeptData")
    @ResponseBody
    public requestJson queryData(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
         System.out.println("进入 deptdata..");
        requestJson j = new requestJson();
        String  timeStr = request.getParameter("timeStr");
        String domresult = "";
      
        //domresult = this.xzcfService.queryByData(timeStr);//String
        j.setMsg(domresult);
        return j;
    }
   
    
   
    /**
     * 查询 json
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【行政处罚查询】",user.getName());
        requestJson j = new requestJson();
        String gcuid = request.getParameter("gongcheng_uid");
        String domresult = "";
      
        domresult = this.xzcfService.queryCondition(gcuid);//String
        j.setMsg(domresult);
        return j;

    }

    /**
     * 保存数据json(非更新)
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【行政处罚新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.xzcfService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    /**
     * 信访笔录 保存 数据
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insertXwbl")
    @ResponseBody
    protected requestJson insertXwbl(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【询问笔录新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.xzcfService.insertXwbl(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 查询 当前选定的当事人 的类型信息
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDXTYPE")
    @ResponseBody
    public requestJson queryDXTYPE(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【行政处罚查询】",user.getName());
        requestJson j = new requestJson();
        String gcuid = request.getParameter("gongcheng_uid");
        String jsuid = request.getParameter("js_company_uid");
        String domresult = "";
        domresult = this.xzcfService.queryDXTYPE(gcuid,jsuid);//String
        j.setMsg(domresult);
        return j;

    }
   /**
    * 查询 调查 人员信息
    * @param request
    * @param json
    * @return
    * @throws Exception
    */
    @RequestMapping(params = "queryReportxx")
    @ResponseBody
    public requestJson  queryReportxx(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【行政处罚查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.xzcfService.queryReportxx();//String
        j.setMsg(domresult);
        return j;

    }
    /**
     * 查询 行政处罚 页面 初始化数据
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    
    @RequestMapping(params = "queryXzcfMsg")
    @ResponseBody
    public requestJson  queryXzcfMsg(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【行政处罚查询】",user.getName());
        requestJson j = new requestJson();
        String gcuid = request.getParameter("gcuid");
        String domresult = "";
        domresult = this.xzcfService. queryXzcfMsg(gcuid);//String
        j.setMsg(domresult);
        return j;

    }
   /**
    * 
    * @param request
    * @param json
    * @return
    * @throws Exception
    */
    @RequestMapping(params = "queryXzcfXX")
    @ResponseBody
    public requestJson  queryXzcfXX(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【行政处罚查询】",user.getName());
        requestJson j = new requestJson();
        String xzcfuid = request.getParameter("xzcfuid");
        String domresult = "";
        domresult = this.xzcfService. queryXzcfXX(xzcfuid);//String
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
     @RequestMapping(params = "update")
     @ResponseBody
     public requestJson  update(final HttpServletRequest request, requestJson json) throws Exception {
     	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【行政处罚查询】",user.getName());
         requestJson j = new requestJson();
         String xzcfuid = request.getParameter("xzcfuid");
         String domresult = "";
         domresult = this.xzcfService.update(json.getMsg(),xzcfuid);//String
         j.setMsg(domresult);
         return j;

     }
     
    
    
}
