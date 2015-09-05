/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbController.java
 * 创建日期： 2014-12-15 下午 06:22:02
 * 功能：    服务控制类：机械设备
 * 所含类:   JxsbService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 下午 06:22:02  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.dtgl.jxsb.service.JxsbService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JxsbController.java </p>
 * <p> 功能：机械设备 </p>
 *
 * <p><a href="JxsbController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

@Controller
@RequestMapping("/jxsb/jxsbController")
public class JxsbController {

	private static Logger logger = LoggerFactory.getLogger(JxsbController.class);

    @Autowired
    private JxsbService jxsbService;

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
        logger.info("<{}>执行操作【机械设备查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 机械设备禁用.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "disabledJXSB")
    @ResponseBody
    protected requestJson disabledSB(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备禁用】",user.getName());
        requestJson j = new requestJson();
        String jxsb_uid = request.getParameter("jxsb_uid");
        String type = request.getParameter("type");
        boolean resultVO = this.jxsbService.disabledJXSB(jxsb_uid,type);
        j.setSuccess(resultVO);
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
        logger.info("<{}>执行操作【机械设备删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
