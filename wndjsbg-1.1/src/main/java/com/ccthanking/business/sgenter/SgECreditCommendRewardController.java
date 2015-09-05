/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgECreditCommendRewardController.java
 * 创建日期： 2014-04-20 下午 02:19:52
 * 功能：    服务控制类：企业荣誉
 * 所含类:   SgECreditCommendRewardService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:19:52  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sgenter.service.SgECreditCommendRewardService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgECreditCommendRewardController.java </p>
 * <p> 功能：企业荣誉 </p>
 *
 * <p><a href="SgECreditCommendRewardController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

@Controller
@RequestMapping("/sgenter/sgECreditCommendRewardController")
public class SgECreditCommendRewardController {

	private static Logger logger = LoggerFactory.getLogger(SgECreditCommendRewardController.class);

    @Autowired
    private SgECreditCommendRewardService sgECreditCommendRewardService;

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
        logger.info("<{}>执行操作【企业荣誉查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgECreditCommendRewardService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【企业荣誉新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgECreditCommendRewardService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【企业荣誉修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgECreditCommendRewardService.update(json.getMsg());
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
        logger.info("<{}>执行操作【企业荣誉删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgECreditCommendRewardService.delete(json.getMsg());
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
    @RequestMapping(value =  "queryJxList")
    @ResponseBody
    public requestJson queryJxList(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【企业荣誉查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String uid = request.getParameter("uid");
        domresult = this.sgECreditCommendRewardService.queryJxList(uid);
        j.setMsg(domresult);
        return j;

    }

}
