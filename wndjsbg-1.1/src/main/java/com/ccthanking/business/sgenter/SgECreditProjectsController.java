/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgECreditProjectsController.java
 * 创建日期： 2014-04-20 下午 02:22:53
 * 功能：    服务控制类：企业参与项目
 * 所含类:   SgECreditProjectsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:22:53  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgenter.service.SgECreditProjectsService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgECreditProjectsController.java </p>
 * <p> 功能：企业参与项目 </p>
 *
 * <p><a href="SgECreditProjectsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

@Controller
@RequestMapping("/sgenter/sgECreditProjectsController")
public class SgECreditProjectsController {

	private static Logger logger = LoggerFactory.getLogger(SgECreditProjectsController.class);

    @Autowired
    private SgECreditProjectsService sgECreditProjectsService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【企业参与项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgECreditProjectsService.queryCondition(json.getMsg());
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
    @RequestMapping(value =  "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【企业参与项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgECreditProjectsService.insert(json.getMsg());
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
    @RequestMapping(value =  "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【企业参与项目修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgECreditProjectsService.update(json.getMsg());
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
    @RequestMapping(value =  "delete")
    @ResponseBody
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【企业参与项目删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgECreditProjectsService.delete(json.getMsg());
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
    @RequestMapping(value =  "queryXmList")
    @ResponseBody
    public requestJson queryXmList(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【企业荣誉查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String uid = request.getParameter("uid");
        domresult = this.sgECreditProjectsService.queryXmList(uid);
        j.setMsg(domresult);
        return j;

    }

}
