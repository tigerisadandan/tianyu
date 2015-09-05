/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzBzController.java
 * 创建日期： 2014-06-19 下午 04:49:00
 * 功能：    服务控制类：审批业务流转步骤
 * 所含类:   BuSpYwlzBzService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:49:00  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.ywlz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.ywlz.service.BuSpYwlzBzService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpYwlzBzController.java </p>
 * <p> 功能：审批业务流转步骤 </p>
 *
 * <p><a href="BuSpYwlzBzController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

@Controller
@RequestMapping("/ywlz/buSpYwlzBzController")
public class BuSpYwlzBzController {

	private static Logger logger = LoggerFactory.getLogger(BuSpYwlzBzController.class);

    @Autowired
    private BuSpYwlzBzService buSpYwlzBzService;

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
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpYwlzBzService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryJlRy")
    @ResponseBody
    public requestJson queryJlRy(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpYwlzBzService.queryJlRy(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务流转步骤新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params = "insertJlRy")
    @ResponseBody
    protected requestJson insertJlRy(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.insertJlRy(request.getParameter("strJl"),request.getParameter("strgcid"),request.getParameter("jdxzid"));
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
        logger.info("<{}>执行操作【审批业务流转步骤修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.updateYwLzBzVo(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params = "updateFfzcz")
    @ResponseBody
    protected requestJson updateFfzcz(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.updateFfzcz(request.getParameter("lzbzid"));
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
        logger.info("<{}>执行操作【审批业务流转步骤删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    @RequestMapping(params = "geYwLzBzList")
    @ResponseBody
    public requestJson geYwLzBzList(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转实例步骤查询】",user.getName());
        requestJson j = new requestJson();
        String ywlzuid = request.getParameter("ywlzuid");
        String domresult = "";
        domresult = this.buSpYwlzBzService.geYwLzBzList(ywlzuid);
        j.setMsg(domresult);
        return j;
    }
    
    @RequestMapping(params = "getYwLzBzVo")
    @ResponseBody
    public requestJson getYwLzBzVo(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String ywlzuid=request.getParameter("ywlzuid");
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.getYwLzBzVo(ywlzuid);
        j.setMsg(resultVO);
        return j;
    }
    

    @RequestMapping(params = "queryYwLzBzLjrCode")
    @ResponseBody
    protected String queryYwLzBzLjrCode(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.queryYwLzBzLjrCode(json.getMsg());
        return resultVO;
    }
    
    @RequestMapping(params = "getYwLzBzVoNoUser")
    @ResponseBody
    public requestJson getYwLzBzVoNoUser(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转步骤查询】",user.getName());
        requestJson j = new requestJson();
        String ywlzuid=request.getParameter("ywlzuid");
        String resultVO = "";
        resultVO = this.buSpYwlzBzService.getYwLzBzVoNoUser(ywlzuid);
        j.setMsg(resultVO);
        return j;
    }
    
    
    //通过YWLZUID来关联到审批业务信息下，查询出具体的那些材料文件
    @RequestMapping(params = "geSpYwClList")
    @ResponseBody
    public requestJson geSpYwClList(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批业务流转实例步骤查询】",user.getName());
        requestJson j = new requestJson();
        String ywlzuid = request.getParameter("ywlzuid");
        String domresult = "";
        domresult = this.buSpYwlzBzService.geSpYwClList(ywlzuid);
        j.setMsg(domresult);
        return j;
    }
}
