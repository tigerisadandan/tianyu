/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzCyzController.java
 * 创建日期： 2014-06-13 下午 04:43:40
 * 功能：    服务控制类：业务材料
 * 所含类:   BuSpBzCyzService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:43:40  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.spxx.service.BuSpBzCyzService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpBzCyzController.java </p>
 * <p> 功能：业务材料 </p>
 *
 * <p><a href="BuSpBzCyzController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Controller
@RequestMapping("/spxx/buSpBzCyzController")
public class BuSpBzCyzController {

	private static Logger logger = LoggerFactory.getLogger(BuSpBzCyzController.class);

    @Autowired
    private BuSpBzCyzService buSpBzCyzService;

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
        logger.info("<{}>执行操作【业务材料查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpBzCyzService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【业务材料新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpBzCyzService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【业务材料修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpBzCyzService.update(json.getMsg());
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
        logger.info("<{}>执行操作【业务材料删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpBzCyzService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "getUsers")
    @ResponseBody
    protected requestJson getUsers(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务材料新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        resultVO = this.buSpBzCyzService.getUsers(uid,uname);
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "addUsers")
    @ResponseBody
    protected requestJson addUsers(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务材料新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        resultVO = this.buSpBzCyzService.getUsers(uid,uname);
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params = "queryBzCyz")
    @ResponseBody
    protected requestJson queryBzCyz(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务材料新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String uid = request.getParameter("uid");
        resultVO = this.buSpBzCyzService.queryBzCyz(uid);
        j.setMsg(resultVO);
        return j;
    }
}
