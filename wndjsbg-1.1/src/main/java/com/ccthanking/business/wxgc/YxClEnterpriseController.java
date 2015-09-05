/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxClEnterpriseController.java
 * 创建日期： 2014-12-23 下午 01:25:43
 * 功能：    服务控制类：材料设备企业信息库
 * 所含类:   yxClEnterpriseService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:25:43  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.service.YxClEnterpriseService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> YxClEnterpriseController.java </p>
 * <p> 功能：材料设备企业信息库 </p>
 *
 * <p><a href="YxClEnterpriseController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Controller
@RequestMapping("/wxgc/yxClEnterpriseController")
public class YxClEnterpriseController {

	private static Logger logger = LoggerFactory.getLogger(YxClEnterpriseController.class);

    @Autowired
    private YxClEnterpriseService yxClEnterpriseService;

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
        logger.info("<{}>执行操作【材料设备企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxClEnterpriseService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;
    }
    
    @RequestMapping(params = "queryold")
    @ResponseBody
    public requestJson queryOldCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【材料设备企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxClEnterpriseService.queryOldCondition(json.getMsg());
        j.setMsg(domresult);
        return j;
    }
    
    
    @RequestMapping(params = "queryspyj")
    @ResponseBody
    public requestJson queryspyj(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【材料设备企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxClEnterpriseService.queryspyjCondition(json.getMsg());
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
        logger.info("<{}>执行操作【材料设备企业信息库新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxClEnterpriseService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【材料设备企业信息库修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxClEnterpriseService.update(json.getMsg());
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
        logger.info("<{}>执行操作【材料设备企业信息库删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxClEnterpriseService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
