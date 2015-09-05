/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxCbsGcTypeController.java
 * 创建日期： 2014-12-25 下午 04:33:18
 * 功能：    服务控制类：预选承包商和微型工程类型关联关系
 * 所含类:   YxCbsGcTypeService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-25 下午 04:33:18  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.wxgc.service.YxCbsGcTypeService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> YxCbsGcTypeController.java </p>
 * <p> 功能：预选承包商和微型工程类型关联关系 </p>
 *
 * <p><a href="YxCbsGcTypeController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-25
 * 
 */

@Controller
@RequestMapping("/wxgc/yxCbsGcTypeController")
public class YxCbsGcTypeController {

	private static Logger logger = LoggerFactory.getLogger(YxCbsGcTypeController.class);

    @Autowired
    private YxCbsGcTypeService yxCbsGcTypeService;

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
        logger.info("<{}>执行操作【预选承包商和微型工程类型关联关系查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxCbsGcTypeService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商和微型工程类型关联关系新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxCbsGcTypeService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商和微型工程类型关联关系修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxCbsGcTypeService.update(json.getMsg());
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
        logger.info("<{}>执行操作【预选承包商和微型工程类型关联关系删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxCbsGcTypeService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
