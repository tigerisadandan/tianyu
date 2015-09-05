/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jl.service.EnterpriseZenZizhiController.java
 * 创建日期： 2015-01-26 下午 02:35:20
 * 功能：    服务控制类：企业增项资质
 * 所含类:   EnterpriseZenZizhiService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:35:20  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.jl.service.EnterpriseZenZizhiService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> EnterpriseZenZizhiController.java </p>
 * <p> 功能：企业增项资质 </p>
 *
 * <p><a href="EnterpriseZenZizhiController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Controller
@RequestMapping("/jl/enterpriseZenZizhiController")
public class EnterpriseZenZizhiController {

	private static Logger logger = LoggerFactory.getLogger(EnterpriseZenZizhiController.class);

    @Autowired
    private EnterpriseZenZizhiService enterpriseZenZizhiService;

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
        logger.info("<{}>执行操作【企业增项资质查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.enterpriseZenZizhiService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【企业增项资质新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.enterpriseZenZizhiService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【企业增项资质修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.enterpriseZenZizhiService.update(json.getMsg());
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
        logger.info("<{}>执行操作【企业增项资质删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.enterpriseZenZizhiService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    @RequestMapping(value =  "queryListZeng")
    @ResponseBody
    public requestJson queryListZeng(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【增项资质表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String uid = request.getParameter("uid");
        domresult = this.enterpriseZenZizhiService.queryListZeng(uid);
        j.setMsg(domresult);
        return j;

    }

}
