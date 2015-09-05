/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    units.service.ProjectsUnitsController.java
 * 创建日期： 2014-10-17 下午 01:43:10
 * 功能：    服务控制类：单位工程
 * 所含类:   ProjectsUnitsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-17 下午 01:43:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.units;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.units.service.ProjectsUnitsService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ProjectsUnitsController.java </p>
 * <p> 功能：单位工程 </p>
 *
 * <p><a href="ProjectsUnitsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-17
 * 
 */

@Controller
@RequestMapping("/units/projectsUnitsController")
public class ProjectsUnitsController {

	private static Logger logger = LoggerFactory.getLogger(ProjectsUnitsController.class);

    @Autowired
    private ProjectsUnitsService projectsUnitsService;

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
        logger.info("<{}>执行操作【单位工程查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsUnitsService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "querybyIds")
    @ResponseBody
    public requestJson querybyIds(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【单位工程查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectsUnitsService.querybyIds(request.getParameter("IDS"));
        j.setMsg(domresult);
        return j;

    }
    
    
    @RequestMapping(params = "querybygcid")
    @ResponseBody
    public requestJson queryConditionbygcid(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【单位工程查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String gcid=request.getParameter("gcid");
        String type=request.getParameter("type");
        String cUid=request.getParameter("cUid");
        domresult = this.projectsUnitsService.querybygcid(gcid,type,cUid);
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
        logger.info("<{}>执行操作【单位工程新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsUnitsService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【单位工程修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsUnitsService.update(json.getMsg());
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
        logger.info("<{}>执行操作【单位工程删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectsUnitsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
