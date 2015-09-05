/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxWxgcPbfsController.java
 * 创建日期： 2015-03-11 下午 04:35:38
 * 功能：    服务控制类：微型工程评标方式
 * 所含类:   YxWxgcPbfsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-11 下午 04:35:38  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.service.YxWxgcPbfsService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> YxWxgcPbfsController.java </p>
 * <p> 功能：微型工程评标方式 </p>
 *
 * <p><a href="YxWxgcPbfsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-03-11
 * 
 */

@Controller
@RequestMapping("/wxgc/yxWxgcPbfsController")
public class YxWxgcPbfsController {

	private static Logger logger = LoggerFactory.getLogger(YxWxgcPbfsController.class);

    @Autowired
    private YxWxgcPbfsService yxWxgcPbfsService;

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
        logger.info("<{}>执行操作【微型工程评标方式查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxWxgcPbfsService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【微型工程评标方式新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxWxgcPbfsService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【微型工程评标方式修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxWxgcPbfsService.update(json.getMsg());
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
        logger.info("<{}>执行操作【微型工程评标方式删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxWxgcPbfsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
