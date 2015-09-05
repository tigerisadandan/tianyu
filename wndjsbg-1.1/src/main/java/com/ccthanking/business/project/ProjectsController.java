/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.ProjectsController.java
 * 创建日期： 2014-07-02 下午 12:00:58
 * 功能：    服务控制类：项目
 * 所含类:   ProjectsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:00:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.project.service.ProjectsService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ProjectsController.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="ProjectsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */

@Controller
@RequestMapping("/project/projectsController")
public class ProjectsController {

	private static Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    private ProjectsService projectsService;

    /**项目分期查询
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
        logger.info("<{}>执行操作【项目分期查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    /**环保项目查询
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryHb")
    @ResponseBody
    public requestJson queryHb(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【环保项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsService.queryHb(json.getMsg());
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
        logger.info("<{}>执行操作【项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目审核】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    @RequestMapping(params = "getProjectCount")
    @ResponseBody
    public requestJson getProjectCount(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤项目分期统计查询】",user.getName());
        requestJson j = new requestJson();
//        String spywuid=request.getParameter("spywuid");
        String resultVO = "";
        String type=request.getParameter("type");
        resultVO = this.projectsService.getProjectCount(type);

        j.setMsg(resultVO);
        return j;
    }
  
}
