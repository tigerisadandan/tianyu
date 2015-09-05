/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.SgSpWxygcqdController.java
 * 创建日期： 2015-04-23 上午 11:44:17
 * 功能：    服务控制类：较大危险源工程清单
 * 所含类:   SgSpWxygcqdService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 上午 11:44:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.wxy.service.SgSpWxygcqdService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgSpWxygcqdController.java </p>
 * <p> 功能：较大危险源工程清单 </p>
 *
 * <p><a href="SgSpWxygcqdController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

@Controller
@RequestMapping("/wxy/sgSpWxygcqdController")
public class SgSpWxygcqdController {

	private static Logger logger = LoggerFactory.getLogger(SgSpWxygcqdController.class);

    @Autowired
    private SgSpWxygcqdService sgSpWxygcqdService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【较大危险源工程清单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgSpWxygcqdService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryWxYBh")
    @ResponseBody
    public String queryWxYBh(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【较大危险源工程清单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgSpWxygcqdService.queryWxYBh(request.getParameter("GCTYPE"));
        return domresult;

    }
    
    @RequestMapping(value = "queryGcStatus")
    @ResponseBody
    public String queryGcStatus(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【较大危险源工程清单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map=new HashMap();
        map.put("TYPE", request.getParameter("type"));
        map.put("id", request.getParameter("id"));
        domresult = this.sgSpWxygcqdService.queryGcStatus(map);
        return domresult;

    }

    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【较大危险源工程清单新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgSpWxygcqdService.insert(json.getMsg());
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
    @RequestMapping(value = "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【较大危险源工程清单修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgSpWxygcqdService.update(json.getMsg());
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
    @RequestMapping(value = "delete")
    @ResponseBody
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【较大危险源工程清单删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgSpWxygcqdService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
