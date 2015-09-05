/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.AzPersonController.java
 * 创建日期： 2014-12-15 上午 11:01:01
 * 功能：    服务控制类：安装人员库
 * 所含类:   AzPersonService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 上午 11:01:01  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.dtgl.jxsb.service.AzPersonService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> AzPersonController.java </p>
 * <p> 功能：安装人员库 </p>
 *
 * <p><a href="AzPersonController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

@Controller
@RequestMapping("/jxsb/azPersonController")
public class AzPersonController {

	private static Logger logger = LoggerFactory.getLogger(AzPersonController.class);

    @Autowired
    private AzPersonService azPersonService;

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
        logger.info("<{}>执行操作【安装人员库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.azPersonService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【安装人员库新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azPersonService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【安装人员库修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azPersonService.update(json.getMsg());
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
        logger.info("<{}>执行操作【安装人员库删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.azPersonService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
