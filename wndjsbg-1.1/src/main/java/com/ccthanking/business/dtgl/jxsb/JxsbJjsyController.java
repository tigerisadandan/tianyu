/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbJjsyController.java
 * 创建日期： 2014-12-25 下午 05:12:41
 * 功能：    服务控制类：机械设备降级使用管理表
 * 所含类:   JxsbJjsyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-25 下午 05:12:41  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.dtgl.jxsb.service.JxsbJjsyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JxsbJjsyController.java </p>
 * <p> 功能：机械设备降级使用管理表 </p>
 *
 * <p><a href="JxsbJjsyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-25
 * 
 */

@Controller
@RequestMapping("/jxsb/jxsbJjsyController")
public class JxsbJjsyController {

	private static Logger logger = LoggerFactory.getLogger(JxsbJjsyController.class);

    @Autowired
    private JxsbJjsyService jxsbJjsyService;

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
        logger.info("<{}>执行操作【机械设备降级使用管理表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbJjsyService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备降级使用管理表新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbJjsyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备降级使用管理表修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbJjsyService.update(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备降级使用管理表删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbJjsyService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
