/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rcjc.service.DtRcjcController.java
 * 创建日期： 2015-06-23 下午 05:51:24
 * 功能：    服务控制类：日常检查
 * 所含类:   DtRcjcService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-06-23 下午 05:51:24  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rcjc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.rcjc.service.DtRcjcService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> DtRcjcController.java </p>
 * <p> 功能：日常检查 </p>
 *
 * <p><a href="DtRcjcController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongweixiong@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-06-23
 * 
 */

@Controller
@RequestMapping("/rcjc/dtRcjcController")
public class DtRcjcController {

	private static Logger logger = LoggerFactory.getLogger(DtRcjcController.class);

    @Autowired
    private DtRcjcService dtRcjcService;

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
        logger.info("<{}>执行操作【日常检查查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.dtRcjcService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【日常检查新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRcjcService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【日常检查修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRcjcService.update(json.getMsg());
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
        logger.info("<{}>执行操作【日常检查删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRcjcService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
