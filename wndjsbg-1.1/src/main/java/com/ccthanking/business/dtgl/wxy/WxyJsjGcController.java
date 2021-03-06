/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyJsjGcController.java
 * 创建日期： 2015-05-19 下午 01:36:56
 * 功能：    服务控制类：危险源脚手架工程过程管理
 * 所含类:   WxyJsjGcService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-19 下午 01:36:56  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.wxy.service.WxyJsjGcService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> WxyJsjGcController.java </p>
 * <p> 功能：危险源脚手架工程过程管理 </p>
 *
 * <p><a href="WxyJsjGcController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-19
 * 
 */

@Controller
@RequestMapping("/wxy/wxyJsjGcController")
public class WxyJsjGcController {

	private static Logger logger = LoggerFactory.getLogger(WxyJsjGcController.class);

    @Autowired
    private WxyJsjGcService wxyJsjGcService;

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
        logger.info("<{}>执行操作【危险源脚手架工程过程管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.wxyJsjGcService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【危险源脚手架工程过程管理新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wxyJsjGcService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【危险源脚手架工程过程管理修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wxyJsjGcService.update(json.getMsg());
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
        logger.info("<{}>执行操作【危险源脚手架工程过程管理删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wxyJsjGcService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
