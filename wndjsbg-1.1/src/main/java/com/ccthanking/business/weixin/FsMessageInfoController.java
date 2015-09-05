/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.FsMessageInfoController.java
 * 创建日期： 2014-12-02 上午 11:04:41
 * 功能：    服务控制类：消息表
 * 所含类:   FsMessageInfoService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-02 上午 11:04:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.weixin;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> FsMessageInfoController.java </p>
 * <p> 功能：消息表 </p>
 *
 * <p><a href="FsMessageInfoController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-02
 * 
 */

@Controller
@RequestMapping("/fsMessageInfoController")
public class FsMessageInfoController {

	private static Logger logger = LoggerFactory.getLogger(FsMessageInfoController.class);

    @Autowired
    private FsMessageInfoService fsMessageInfoService;

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
        logger.info("<{}>执行操作【消息表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.fsMessageInfoService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    
    @RequestMapping(params = "countMessage")
    @ResponseBody
    public requestJson countMessage(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【消息表查询】",user.getName());
        requestJson j = new requestJson();
        Map map= new HashMap();
        String domresult = this.fsMessageInfoService.countMessage(map);
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
        logger.info("<{}>执行操作【消息表新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.fsMessageInfoService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【消息表修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.fsMessageInfoService.update(json.getMsg());
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
        logger.info("<{}>执行操作【消息表删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.fsMessageInfoService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
