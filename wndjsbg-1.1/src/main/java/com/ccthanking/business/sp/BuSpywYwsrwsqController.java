/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywYwsrwsqController.java
 * 创建日期： 2014-06-04 下午 05:30:51
 * 功能：    服务控制类：雨污水入网申请
 * 所含类:   BuSpywYwsrwsqService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-04 下午 05:30:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sp.service.BuSpywYwsrwsqService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywYwsrwsqController.java </p>
 * <p> 功能：雨污水入网申请 </p>
 *
 * <p><a href="BuSpywYwsrwsqController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-04
 * 
 */

@Controller
@RequestMapping("/sp/buSpywYwsrwsqController/")
public class BuSpywYwsrwsqController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywYwsrwsqController.class);

    @Autowired
    private BuSpywYwsrwsqService buSpywYwsrwsqService;

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
        logger.info("<{}>执行操作【雨污水入网申请查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywYwsrwsqService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    /**
     **
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public requestJson download(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【雨污水入网申请-下载】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        domresult = this.buSpywYwsrwsqService.download(id);
        
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
        logger.info("<{}>执行操作【雨污水入网申请新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywYwsrwsqService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【雨污水入网申请修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywYwsrwsqService.update(json.getMsg());
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
        logger.info("<{}>执行操作【雨污水入网申请删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywYwsrwsqService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 业务材料核发
     */
    @RequestMapping(value = "ywlzclhf")
    @ResponseBody
    protected requestJson ywlzclhf(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【材料核发】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywYwsrwsqService.ywlzclhf(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
}
