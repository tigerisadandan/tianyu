/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgScoreDetailController.java
 * 创建日期： 2014-06-09 上午 09:36:33
 * 功能：    服务控制类：分数主体
 * 所含类:   SgScoreDetailService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:36:33  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgenter.service.SgScoreDetailService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgScoreDetailController.java </p>
 * <p> 功能：分数主体 </p>
 *
 * <p><a href="SgScoreDetailController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Controller
@RequestMapping("/sgenter/sgScoreDetailController")
public class SgScoreDetailController {

	private static Logger logger = LoggerFactory.getLogger(SgScoreDetailController.class);

    @Autowired
    private SgScoreDetailService sgScoreDetailService;

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
        logger.info("<{}>执行操作【分数主体查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgScoreDetailService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【分数主体新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgScoreDetailService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【分数主体修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgScoreDetailService.update(json.getMsg());
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
        logger.info("<{}>执行操作【分数主体删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgScoreDetailService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
