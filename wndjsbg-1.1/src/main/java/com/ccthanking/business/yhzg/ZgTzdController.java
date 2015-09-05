/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgTzdController.java
 * 创建日期： 2015-04-21 下午 02:41:29
 * 功能：    服务控制类：整改通知单
 * 所含类:   ZgTzdService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 02:41:29  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.yhzg.service.ZgTzdService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> ZgTzdController.java </p>
 * <p> 功能：整改通知单 </p>
 *
 * <p><a href="ZgTzdController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Controller
@RequestMapping("/yhzg/zgTzdController")
public class ZgTzdController {

	private static Logger logger = LoggerFactory.getLogger(ZgTzdController.class);

    @Autowired
    private ZgTzdService zgTzdService;

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
        logger.info("<{}>执行操作【整改通知单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.zgTzdService.query(json.getMsg());
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
    @RequestMapping(params = "queryZGD")
    @ResponseBody
    public requestJson queryZGD(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【整改通知单查询】",user.getName());
        requestJson j = new requestJson();
        String condition2 = request.getParameter("condition");
        String domresult = "";
        domresult = this.zgTzdService.queryCondition(json.getMsg(),condition2);
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
    @RequestMapping(params = "queryZGD2")
    @ResponseBody
    public requestJson queryZGD2(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单查询】",user.getName());
    	requestJson j = new requestJson();
    	String zhenggai = request.getParameter("zhenggai");
    	String whichone = request.getParameter("whichone");
    	String deptUid = request.getParameter("deptUid");
    	String week = request.getParameter("week");
    	String year = request.getParameter("year");
    	String month = request.getParameter("month");
    	String before = "";
    	String after = "";
    	if("1".equals(whichone)){
    		//第一个周表
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			Calendar cal = Calendar.getInstance();
	        int year2 =cal.get(Calendar.YEAR);
	        
	        cal.set(Calendar.YEAR, year2);
	        cal.set(Calendar.WEEK_OF_YEAR,Integer.parseInt(week));
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	        before = sdf.format(cal.getTime());
	        
	        cal.set(Calendar.WEEK_OF_YEAR,Integer.parseInt(week)+1);
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	        after = sdf.format(cal.getTime());	        
    		
    	}else if("2".equals(whichone)){
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

	        cal.set(Calendar.YEAR, Integer.parseInt(year));     
	        cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DATE));	        
	        before = sdf.format(cal.getTime());	        
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
	        after = sdf.format(cal.getTime());
    	}   	
    	String domresult = "";
    	domresult = this.zgTzdService.queryZGD2(zhenggai,deptUid,before,after,json.getMsg());
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
    
    @RequestMapping(params = "getDeptName101")
    @ResponseBody
    public requestJson getDeptName(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【整改通知单查询】",user.getName());
        requestJson j = new requestJson();
        String ORGANIZE_UID = request.getParameter("deptUid");
        String domresult = "";
        domresult = this.zgTzdService.getDeptName(ORGANIZE_UID);
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
    @RequestMapping(params = "queryForm")
    @ResponseBody
    public requestJson queryForm(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgTzdService.queryForm(json.getMsg());
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
    @RequestMapping(params = "queryUid")
    @ResponseBody
    public requestJson queryUid(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgTzdService.queryUid(json.getMsg());
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
    @RequestMapping(params = "getQtCount")
    @ResponseBody
    public requestJson getQtCount(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgTzdService.getQtCount(json.getMsg());
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
    @RequestMapping(params = "getJtCount")
    @ResponseBody
    public requestJson getJtCount(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgTzdService.getJtCount(json.getMsg());
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
    @RequestMapping(params = "querySh")
    @ResponseBody
    public requestJson querySh(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgTzdService.querySh(json.getMsg());
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
        logger.info("<{}>执行操作【整改通知单新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgTzdService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【整改通知单修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgTzdService.update(json.getMsg());
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
    @RequestMapping(params = "updateSh")
    @ResponseBody
    protected requestJson updateSh(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【整改通知单修改】",user.getName());
    	requestJson j = new requestJson();
    	String resultVO = "";
    	resultVO = this.zgTzdService.updateSh(json.getMsg());
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
        logger.info("<{}>执行操作【整改通知单删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgTzdService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
	@RequestMapping(params = "printZgtzd")
	@ResponseBody
	public void printZgtzd(HttpServletRequest request, HttpServletResponse response, requestJson json) {
		String tzduid = request.getParameter("tzduid");
		String path = "";
		FileUploadService ser = new FileUploadService();
		String encoding = request.getCharacterEncoding();
		response.setCharacterEncoding(encoding);
		//String path = request.getParameter("path");
		
		try {
			path = this.zgTzdService.toword(response, tzduid);
			File file = new File(path);
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
		} catch (Exception e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}

		//return path;
	}

}
