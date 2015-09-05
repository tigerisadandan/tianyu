/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gongcheng.service.ProjectsGongchengController.java
 * 创建日期： 2014-10-16 下午 04:22:49
 * 功能：    服务控制类：施工内容
 * 所含类:   ProjectsGongchengService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-16 下午 04:22:49  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.gongcheng;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.gongcheng.service.ProjectsGongchengService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ProjectsGongchengController.java </p>
 * <p> 功能：施工内容 </p>
 *
 * <p><a href="ProjectsGongchengController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-16
 * 
 */

@Controller
@RequestMapping("/gongcheng/projectsGongchengController")
public class ProjectsGongchengController {

	private static Logger logger = LoggerFactory.getLogger(ProjectsGongchengController.class);

    @Autowired
    private ProjectsGongchengService projectsGongchengService;

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
        logger.info("<{}>执行操作【施工内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map=new HashMap();
        map.put("type", request.getParameter("type"));
        map.put("status", request.getParameter("status"));
        domresult = this.projectsGongchengService.queryCondition(json.getMsg(), map);
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 查询json
     * 施工工地首页
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query2")
    @ResponseBody
    public requestJson queryCondition1(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map=new HashMap();
        String status=request.getParameter("status");
        String AJ_uid=request.getParameter("AJ_uid");
        String ZJ_uid=request.getParameter("ZJ_uid");
        String order=request.getParameter("order");
        map.put("status", status);
        map.put("AJ_uid",AJ_uid);
        map.put("ZJ_uid",ZJ_uid);
        map.put("order", order);
        domresult = this.projectsGongchengService.queryCondition2(json.getMsg(),map);
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
    @RequestMapping(params = "query3")
    @ResponseBody
    public requestJson queryCondition3(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map=new HashMap();
        String condition = request.getParameter("condition");
        String AJZ_UID = request.getParameter("AJZ_UID");       
        domresult = this.projectsGongchengService.queryCondition3(json.getMsg(), condition,AJZ_UID);
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryAllGc")
    @ResponseBody
    public requestJson queryAllGc(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsGongchengService.queryAllGc(json.getMsg());
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
        logger.info("<{}>执行操作【施工内容新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsGongchengService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【施工内容修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsGongchengService.update(json.getMsg());
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
        logger.info("<{}>执行操作【施工内容删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsGongchengService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "queryStatusNums")
    @ResponseBody
    public requestJson queryStatusNums(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【queryStatusNums】",user.getName());
        String resultVO = "";
        requestJson j = new requestJson();
        String projectsid = request.getParameter("projectsid");
        resultVO = this.projectsGongchengService.queryStatusNums(projectsid);
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 查询工程信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "querygc")
    @ResponseBody
    public requestJson querygc(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【查询工程信息】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String gcid = request.getParameter("gcid");
        domresult = this.projectsGongchengService.querygc(json.getMsg(), gcid);
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询安监员信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "getAJY")
    @ResponseBody
    public requestJson getAJY(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【查询工程信息】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsGongchengService.getAJY();
        j.setMsg(domresult);
        return j;

    }
        
    /**
     * 查询质监员信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "getZJY")
    @ResponseBody
    public requestJson getZJY(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【查询工程信息】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsGongchengService.getZJY();
        j.setMsg(domresult);
        return j;
    }
    
    
    @RequestMapping(params = "queryAjDept")
    @ResponseBody
    public requestJson queryAjDept(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【查询安监部门信息】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsGongchengService.queryAjDept();
        j.setMsg(domresult);
        return j;
    }
    
}
