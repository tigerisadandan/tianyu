/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.GcTypeController.java
 * 创建日期： 2014-04-22 下午 04:07:38
 * 功能：    服务控制类：施工报备
 * 所含类:   GcTypeService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-22 下午 04:07:38  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgbb.service.GcTypeService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> GcTypeController.java </p>
 * <p> 功能：施工报备 </p>
 *
 * <p><a href="GcTypeController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-22
 * 
 */

@Controller
@RequestMapping("/sgbb/gcTypeController/")
public class GcTypeController {

	private static Logger logger = LoggerFactory.getLogger(GcTypeController.class);

    @Autowired
    private GcTypeService gcTypeService;

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
        logger.info("<{}>执行操作【施工报备查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.gcTypeService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询工程父类型
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryPType")
    @ResponseBody
    public requestJson queryPType(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工报备查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String gc_type = request.getParameter("uid");
        domresult = this.gcTypeService.queryPType(gc_type);
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
    @RequestMapping(value = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工报备新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gcTypeService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【施工报备修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gcTypeService.update(json.getMsg());
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
        logger.info("<{}>执行操作【施工报备删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gcTypeService.delete(json.getMsg());
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
    @RequestMapping(value = "queryTypelist")
    @ResponseBody
    public requestJson queryTypelist(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工报备查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String pType = request.getParameter("pType");
        domresult = this.gcTypeService.queryTypelist(pType);
        j.setMsg(domresult);
        return j;

    }

}
