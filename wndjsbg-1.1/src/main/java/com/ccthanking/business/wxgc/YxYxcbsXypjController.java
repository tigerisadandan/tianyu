/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxYxcbsXypjController.java
 * 创建日期： 2015-01-21 上午 10:47:08
 * 功能：    服务控制类：预选承包商信用评价
 * 所含类:   YxYxcbsXypjService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-21 上午 10:47:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.wxgc.service.YxYxcbsXypjService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> YxYxcbsXypjController.java </p>
 * <p> 功能：预选承包商信用评价 </p>
 *
 * <p><a href="YxYxcbsXypjController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-21
 * 
 */

@Controller
@RequestMapping("/wxgc/yxYxcbsXypjController")
public class YxYxcbsXypjController {

	private static Logger logger = LoggerFactory.getLogger(YxYxcbsXypjController.class);

    @Autowired
    private YxYxcbsXypjService yxYxcbsXypjService;

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
        logger.info("<{}>执行操作【预选承包商信用评价查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxYxcbsXypjService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }

    
    @RequestMapping(params = "queryAllXypjXq")
    @ResponseBody
    public requestJson queryAllXypjXqCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【预选承包商信用评价详情查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map =new HashMap();
        String xypj_uid=request.getParameter("xypj_uid");
        map.put("xypj_uid", xypj_uid);
        
        domresult = this.yxYxcbsXypjService.queryAllXypjXqCondition(map);
        j.setMsg(domresult);
        
        return j;
    }
    
    @RequestMapping(params = "queryXypjLx")
    @ResponseBody
    public requestJson queryXypjLxCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【预选承包商信用评价类型查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map =new HashMap();
        String xypj_uid=request.getParameter("xypjuid");
        map.put("xypj_uid", xypj_uid);
        
        domresult = this.yxYxcbsXypjService.queryXypjLx(map);
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
        logger.info("<{}>执行操作【预选承包商信用评价新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsXypjService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商信用评价修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsXypjService.update(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商信用评价删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsXypjService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    
    
    @RequestMapping(params = "getYxcbsXypjFile")
	@ResponseBody
	public void getWxgcgsFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String timestamp=request.getParameter("timestamp");
    	String sessionid=request.getSession().getId();
    	String filepath=(String)request.getSession().getAttribute(sessionid);
    	String dytimestamp=(String)request.getSession().getAttribute("dytimestamp");
    	if(filepath==null||"".equals(filepath)){
        	String XYPJ_UID=request.getParameter("XYPJ_UID");
        	Map map=new HashMap();
        	map.put("XYPJ_UID", XYPJ_UID);
        	filepath=this.yxYxcbsXypjService.queryfilePathXypj(map);
        	request.getSession().setAttribute(sessionid, filepath);
        	request.getSession().setAttribute("dytimestamp", timestamp);
    	}else{
    		if(!StringUtils.isEmpty(dytimestamp)&&!dytimestamp.equals(timestamp)){
    	    	String XYPJ_UID=request.getParameter("XYPJ_UID");
    	    	Map map=new HashMap();
    	    	map.put("XYPJ_UID", XYPJ_UID);
    	    	filepath=this.yxYxcbsXypjService.queryfilePathXypj(map);
            	request.getSession().setAttribute(sessionid, filepath);
    		}
    		request.getSession().setAttribute("dytimestamp", timestamp);
    	}

    	File file=new File(filepath);
    	if (file.exists()) {
			int bytes = 0;
			response.setCharacterEncoding("GBK");
			response.setContentType("application/pdf");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition","inline; filename=\"" + MimeUtility.encodeWord(file.getName()) + "\"");
			response.setHeader("Content-type", "application/pdf");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("content", "text/html");
			ServletOutputStream op = response.getOutputStream();
			byte[] bbuf = new byte[1024];
			FileInputStream in = new FileInputStream(file);
			while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, bytes);
			}
			in.close();
			op.flush();
			op.close();
		}
    }
}
