/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jl.service.JlPersonZhengshuController.java
 * 创建日期： 2015-01-26 下午 02:23:22
 * 功能：    服务控制类：监理人员证书
 * 所含类:   JlPersonZhengshuService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:23:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jl.service.JlPersonZhengshuService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JlPersonZhengshuController.java </p>
 * <p> 功能：监理人员证书 </p>
 *
 * <p><a href="JlPersonZhengshuController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Controller
@RequestMapping("/jl/jlPersonZhengshuController")
public class JlPersonZhengshuController {

	private static Logger logger = LoggerFactory.getLogger(JlPersonZhengshuController.class);

    @Autowired
    private JlPersonZhengshuService jlPersonZhengshuService;

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
        logger.info("<{}>执行操作【监理人员证书查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jlPersonZhengshuService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【监理人员证书新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlPersonZhengshuService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【监理人员证书修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlPersonZhengshuService.update(json.getMsg());
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
        logger.info("<{}>执行操作【监理人员证书删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlPersonZhengshuService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryListPersonZhengshu")
    @ResponseBody
    public requestJson queryListPersonZhengshu(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【增项资质表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String uid = request.getParameter("uid");
        domresult = this.jlPersonZhengshuService.queryListPersonZhengshu(uid);
        j.setMsg(domresult);
        return j;

    }

}
