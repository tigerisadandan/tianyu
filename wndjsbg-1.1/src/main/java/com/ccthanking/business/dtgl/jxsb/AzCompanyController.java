/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.AzCompanyController.java
 * 创建日期： 2014-12-11 上午 11:06:42
 * 功能：    服务控制类：安装企业
 * 所含类:   AzCompanyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-11 上午 11:06:42  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.jxsb.service.AzCompanyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> AzCompanyController.java </p>
 * <p> 功能：安装企业 </p>
 *
 * <p><a href="AzCompanyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-11
 * 
 */

@Controller
@RequestMapping("/jxsb/azCompanyController")
public class AzCompanyController {

	private static Logger logger = LoggerFactory.getLogger(AzCompanyController.class);

    @Autowired
    private AzCompanyService azCompanyService;

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
        logger.info("<{}>执行操作【安装企业查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.azCompanyService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryDq")
    @ResponseBody
    public requestJson queryDq(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【安装企业查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.azCompanyService.queryDq(json.getMsg());
        j.setMsg(domresult);
        return j;
    }
    
    @RequestMapping(params = "queryCount")
    @ResponseBody
    public requestJson queryCount(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【安装企业查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String type=request.getParameter("type");
        domresult = this.azCompanyService.queryCount(type);
        j.setMsg(domresult);
        return j;
    }

    
    @RequestMapping(params = "updateOld")
    @ResponseBody
    public requestJson updateOld(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【安装企业查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.azCompanyService.updateOld(json.getMsg());
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
        logger.info("<{}>执行操作【安装企业新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azCompanyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【安装企业修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azCompanyService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params = "updateYqsq")
    @ResponseBody
    protected requestJson updateYqsq(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【安装企业修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azCompanyService.updateYqsq(json.getMsg());
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
        logger.info("<{}>执行操作【安装企业删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azCompanyService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
