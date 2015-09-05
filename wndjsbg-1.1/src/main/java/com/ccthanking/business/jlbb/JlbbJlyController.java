/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jlbb.service.JlbbJlyController.java
 * 创建日期： 2015-01-28 上午 09:21:37
 * 功能：    服务控制类：监理项目的人员报备信息
 * 所含类:   JlbbJlyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:21:37  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jlbb.service.JlbbJlyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JlbbJlyController.java </p>
 * <p> 功能：监理项目的人员报备信息 </p>
 *
 * <p><a href="JlbbJlyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Controller
@RequestMapping("/jlbb/jlbbJlyController")
public class JlbbJlyController {

	private static Logger logger = LoggerFactory.getLogger(JlbbJlyController.class);

    @Autowired
    private JlbbJlyService jlbbJlyService;

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
        logger.info("<{}>执行操作【监理项目的人员报备信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jlbbJlyService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    @RequestMapping(value = "checkPerson")
    @ResponseBody
    public requestJson checkPerson(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理项目的人员检查】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	String gwid = request.getParameter("gwid");
    	String gcdj = request.getParameter("gcdj");
    	String pid = request.getParameter("pid");
    	domresult = this.jlbbJlyService.checkPerson(pid,gwid,gcdj);
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
        logger.info("<{}>执行操作【监理项目的人员报备信息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlbbJlyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【监理项目的人员报备信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlbbJlyService.update(json.getMsg());
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
        logger.info("<{}>执行操作【监理项目的人员报备信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlbbJlyService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
