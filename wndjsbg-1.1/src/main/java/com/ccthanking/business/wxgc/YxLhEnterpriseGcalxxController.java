/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    qyxx.service.YxLhEnterpriseGcalxxController.java
 * 创建日期： 2015-01-28 上午 09:18:45
 * 功能：    服务控制类：绿化企业工程案例信息
 * 所含类:   YxLhEnterpriseGcalxxService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:18:45  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.wxgc.service.YxLhEnterpriseGcalxxService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> YxLhEnterpriseGcalxxController.java </p>
 * <p> 功能：绿化企业工程案例信息 </p>
 *
 * <p><a href="YxLhEnterpriseGcalxxController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Controller
@RequestMapping("/qyxx/yxLhEnterpriseGcalxxController")
public class YxLhEnterpriseGcalxxController {

	private static Logger logger = LoggerFactory.getLogger(YxLhEnterpriseGcalxxController.class);

    @Autowired
    private YxLhEnterpriseGcalxxService yxLhEnterpriseGcalxxService;

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
        logger.info("<{}>执行操作【绿化企业工程案例信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxLhEnterpriseGcalxxService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【绿化企业工程案例信息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxLhEnterpriseGcalxxService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【绿化企业工程案例信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxLhEnterpriseGcalxxService.update(json.getMsg());
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
        logger.info("<{}>执行操作【绿化企业工程案例信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxLhEnterpriseGcalxxService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
