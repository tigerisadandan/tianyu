/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpYwxxController.java
 * 创建日期： 2014-06-11 上午 11:51:59
 * 功能：    服务控制类：审批业务信息
 * 所含类:   BuSpYwxxService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-11 上午 11:51:59  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.spxx.service.BuSpYwxxService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpYwxxController.java </p>
 * <p> 功能：审批业务信息 </p>
 *
 * <p><a href="BuSpYwxxController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-11
 * 
 */

@Controller
@RequestMapping("/spxx/buSpYwxxController/")
public class BuSpYwxxController {

	private static Logger logger = LoggerFactory.getLogger(BuSpYwxxController.class);

    @Autowired
    private BuSpYwxxService buSpYwxxService;

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
//        logger.info("<{}>执行操作【审批业务信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpYwxxService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryBz")
    @ResponseBody
    public String queryBz(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
//        logger.info("<{}>执行操作【审批业务信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpYwxxService.queryBz(json.getMsg());
        j.setMsg(domresult);
        return domresult;

    }
    
    @RequestMapping(value = "queryYWLX")
    @ResponseBody
    public requestJson queryYWLX(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
//        logger.info("<{}>执行操作【审批业务信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpYwxxService.queryYWLX(request.getParameter("SPYW_UID"));
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
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务信息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwxxService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwxxService.update(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwxxService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 加载审批业务信息树
     * @param request
     * @param response
     * @throws Exception 
     */
    @RequestMapping(value = "getAllSpyw")
    @ResponseBody
    public void getAllSpyw(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String ywJson = this.buSpYwxxService.getAllSpyw();
    	
		try {
	    	response.setCharacterEncoding("UTF-8");
	    	System.out.println(ywJson);
			response.getWriter().print(ywJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
