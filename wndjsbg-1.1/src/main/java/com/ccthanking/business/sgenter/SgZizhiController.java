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
package com.ccthanking.business.sgenter;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgenter.service.SgZizhiService;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgZiZhiController.java </p>
 * <p> 功能：资质表 </p>
 *
 * <p><a href="SgZiZhiController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-10
 * 
 */

@Controller
@RequestMapping("/sgenter/sgZizhiController")
public class SgZizhiController {

	private static Logger logger = LoggerFactory.getLogger(SgZizhiController.class);

    @Autowired
    private SgZizhiService sgZiZhiService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgZiZhiService.queryCondition(json.getMsg());
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
    @RequestMapping(value =  "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表新增】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgZiZhiService.insert(json.getMsg());
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
    @RequestMapping(value =  "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表修改】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgZiZhiService.update(json.getMsg());
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
    @RequestMapping(value =  "delete")
    @ResponseBody
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表删除】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgZiZhiService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 获取资质信息下拉列表
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "loadAllZizhi")
    @ResponseBody
    public requestJson queryAllZizhi(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgZiZhiService.queryAllZizhi();
        j.setMsg(domresult);
        return j;

    }
    /**
     * 获取资质信息下拉列表
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "loadAllZhuanye")
    @ResponseBody
    public requestJson queryAllZhuanye(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgZiZhiService.queryAllZhuanye(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
}
