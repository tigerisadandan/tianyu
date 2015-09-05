/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgZiZhiController.java
 * 创建日期： 2014-04-10 上午 10:27:57
 * 功能：    服务控制类：资质表
 * 所含类:   SgZiZhiService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-10 上午 10:27:57  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.person;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.person.service.SgZhengshuService;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgZhengshuController.java </p>
 * <p> 功能：证书表 </p>
 *
 * <p><a href="SgZhengshuController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-10
 * 
 */

@Controller
@RequestMapping("/person/SgZhengshuController/")
public class SgZhengshuController {

	private static Logger logger = LoggerFactory.getLogger(SgZhengshuController.class);

    @Autowired
    private SgZhengshuService sgZhengshuService;

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
        logger.info("<{}>执行操作【资质表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgZhengshuService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【资质表新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgZhengshuService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【资质表修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgZhengshuService.update(json.getMsg());
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
        logger.info("<{}>执行操作【资质表删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgZhengshuService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 获取证书信息下拉列表
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loadAllZhengshu")
    @ResponseBody
    public requestJson queryAllZizhi(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【证书表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgZhengshuService.queryAllZhengshu();
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryZsByGw")
    @ResponseBody
    public requestJson queryZsByGw(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String person_uid = request.getParameter("person_uid");
        String gw_uid = request.getParameter("gw_uid");
        domresult = this.sgZhengshuService.queryZsByGw(person_uid,gw_uid);
        j.setMsg(domresult);
        return j;

    }

}
