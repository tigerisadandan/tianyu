/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpLzhfController.java
 * 创建日期： 2014-06-25 下午 03:35:39
 * 功能：    服务控制类：审批业务流转核发文件
 * 所含类:   BuSpLzhfService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-25 下午 03:35:39  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpLzhfController.java </p>
 * <p> 功能：审批业务流转核发文件 </p>
 *
 * <p><a href="BuSpLzhfController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-25
 * 
 */

@Controller
@RequestMapping("/spxx/buSpLzhfController")
public class BuSpLzhfController {

	private static Logger logger = LoggerFactory.getLogger(BuSpLzhfController.class);

    @Autowired
    private BuSpLzhfService buSpLzhfService;

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
        logger.info("<{}>执行操作【审批业务流转核发文件查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpLzhfService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务流转核发文件新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpLzhfService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务流转核发文件修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpLzhfService.update(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务流转核发文件删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpLzhfService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    /**
     * 材料核发
     * 1、不走表单的controller,也不生成pdf表单或者word文件，只保存核发数据
     */
    @RequestMapping(value = "/{typeId}/ywlzclhf")
    @ResponseBody
    protected requestJson ywlzclhf(final HttpServletRequest request, requestJson json,@PathVariable String typeId) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【材料核发】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        Map temmap=new HashMap();
        temmap.put("TYPEID", typeId);
        resultVO = this.buSpLzhfService.insertBuSpLzhfVO(json.getMsg(),temmap);
        j.setMsg(resultVO);
        return j;
    }

}
