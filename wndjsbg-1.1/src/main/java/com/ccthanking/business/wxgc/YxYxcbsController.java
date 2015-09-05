/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxYxcbsController.java
 * 创建日期： 2014-12-23 下午 01:31:30
 * 功能：    服务控制类：预选承包商
 * 所含类:   YxYxcbsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:31:30  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.wxgc.service.YxYxcbsService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> YxYxcbsController.java </p>
 * <p> 功能：预选承包商 </p>
 *
 * <p><a href="YxYxcbsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Controller
@RequestMapping("/wxgc/yxYxcbsController")
public class YxYxcbsController {

	private static Logger logger = LoggerFactory.getLogger(YxYxcbsController.class);

    @Autowired
    private YxYxcbsService yxYxcbsService;

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
        logger.info("<{}>执行操作【预选承包商查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxYxcbsService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }

    @RequestMapping(params = "queryspyj")
    @ResponseBody
    public requestJson queryspyj(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【勘察设计企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxYxcbsService.queryspyjCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    

    @RequestMapping(params = "querytj")
    @ResponseBody
    public requestJson querytj(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【勘察设计企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxYxcbsService.querytjCondition(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    
    //限制预选承包商
    @RequestMapping(params = "updateXzYxcbs")
    @ResponseBody
    protected requestJson updateXzYxcbs(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【预选承包商修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsService.updateXzYxcbs(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxYxcbsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    //查询预选承包商和微型工程类型关联信息
    @RequestMapping(params = "querycbsgctype")
    @ResponseBody
    public requestJson querycbsgctype(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【预选承包商查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map= new HashMap();
        map.put("YXCBS_UID", request.getParameter("YXCBS_UID"));
        
        domresult = this.yxYxcbsService.querycbsgctype(map);
        j.setMsg(domresult);
        return j;
    }
}
