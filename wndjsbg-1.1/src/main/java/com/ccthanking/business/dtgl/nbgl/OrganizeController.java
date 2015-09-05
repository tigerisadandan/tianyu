/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.OrganizeController.java
 * 创建日期： 2015-05-25 下午 09:13:36
 * 功能：    服务控制类：组织结构
 * 所含类:   OrganizeService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-25 下午 09:13:36  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.nbgl.service.OrganizeService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> OrganizeController.java </p>
 * <p> 功能：组织结构 </p>
 *
 * <p><a href="OrganizeController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-25
 * 
 */

@Controller
@RequestMapping("/nbgl/organizeController")
public class OrganizeController {

	private static Logger logger = LoggerFactory.getLogger(OrganizeController.class);

    @Autowired
    private OrganizeService organizeService;

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
        logger.info("<{}>执行操作【组织结构查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.organizeService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询json 组织机构管理（树结构）
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryOrganize")
    @ResponseBody
    public void queryOrganize(final HttpServletRequest request, requestJson json,final HttpServletResponse response) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【组织结构查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.organizeService.queryOrganize();
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().print(domresult);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
        logger.info("<{}>执行操作【组织结构新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.organizeService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【组织结构修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.organizeService.update(json.getMsg());
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
        logger.info("<{}>执行操作【组织结构删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.organizeService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 查询当前部门用户
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDeptUser")
    @ResponseBody
    public requestJson queryCurrDeptUser(HttpServletRequest request,requestJson json) throws Exception{
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【组织结构删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.organizeService.queryDeptUserByCurrentUser();
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 查询其他部门用户
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryRestDeptUser")
    @ResponseBody
    public requestJson queryRestDeptUser(HttpServletRequest request,requestJson json) throws Exception{
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【组织结构删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.organizeService.queryRestDeptUser();
        j.setMsg(resultVO);
        return j;
    }
}
