/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.ZtbZbdlController.java
 * 创建日期： 2015-01-14 上午 11:44:05
 * 功能：    服务控制类：招标代理机构维护
 * 所含类:   ZtbZbdlService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-14 上午 11:44:05  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.service.ZtbZbdlService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ZtbZbdlController.java </p>
 * <p> 功能：招标代理机构维护 </p>
 *
 * <p><a href="ZtbZbdlController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-14
 * 
 */

@Controller
@RequestMapping("/wxgc/ztbZbdlController")
public class ZtbZbdlController {

	private static Logger logger = LoggerFactory.getLogger(ZtbZbdlController.class);

    @Autowired
    private ZtbZbdlService ztbZbdlService;

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
        logger.info("<{}>执行操作【招标代理机构维护查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.ztbZbdlService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【招标代理机构维护新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.ztbZbdlService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【招标代理机构维护修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.ztbZbdlService.update(json.getMsg());
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
        logger.info("<{}>执行操作【招标代理机构维护删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.ztbZbdlService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
