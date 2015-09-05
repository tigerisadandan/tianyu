/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.JsProjectController.java
 * 创建日期： 2014-09-02 下午 04:35:32
 * 功能：    服务控制类：项目管理
 * 所含类:   JsProjectService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-09-02 下午 04:35:32  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.bzwj.GongCheng;
import com.ccthanking.business.project.service.JsProjectService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JsProjectController.java </p>
 * <p> 功能：项目管理 </p>
 *
 * <p><a href="JsProjectController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-09-02
 * 
 */

@Controller
@RequestMapping("/project/jsProjectController")
public class JsProjectController {

	private static Logger logger = LoggerFactory.getLogger(JsProjectController.class);

    @Autowired
    private JsProjectService jsProjectService;

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
        logger.info("<{}>执行操作【项目管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jsProjectService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "setInSession")
    @ResponseBody
    public String setInSession(HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【选择工程setInSession id】",user.getName());
        String str = request.getParameter("gcid");
        String gcname = request.getParameter("gcname");
        String sgname = request.getParameter("sgname");
//        if(request.getSession().getAttribute("projects")!=null){
//        	request.getSession().removeAttribute("projects");
//        }
        
        GongCheng.addToGcId(str);
        GongCheng.addToGcName(gcname);
        GongCheng.addToSgCompanyName(sgname);
        
        return str;
    }
    
    @RequestMapping(params = "query2")
    @ResponseBody
    public requestJson queryCondition2(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jsProjectService.queryCondition2(json.getMsg());
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
        logger.info("<{}>执行操作【项目管理新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jsProjectService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目管理修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jsProjectService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(params = "queryLX")
    @ResponseBody
    protected requestJson queryLX(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【查询项目立项信息】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jsProjectService.queryLX(json.getMsg());
        j.setMsg(domresult);
        return j;
    }
    @RequestMapping(params = "queryLXdetail")
    @ResponseBody
    protected requestJson queryLXdetail(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【查询立项详细信息】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jsProjectService.queryLXdetail(json.getMsg());
        j.setMsg(domresult);
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
        logger.info("<{}>执行操作【项目管理删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jsProjectService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 查询记录数.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "getProjectCount")
    @ResponseBody
    public requestJson getProjectCount(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
//        String spywuid=request.getParameter("spywuid");
        String resultVO = "";
        resultVO = this.jsProjectService.getProjectCount();

        j.setMsg(resultVO);
        return j;
    }
}
