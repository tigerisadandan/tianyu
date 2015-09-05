/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.buSpywPsfayssqController.java
 * 创建日期： 2014-05-30 下午 04:11:14
 * 功能：    服务控制类：排水
 * 所含类:   buSpywPsfayssqService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-30 下午 04:11:14  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sp.service.BuSpywPsfayssqService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> buSpywPsfayssqController.java </p>
 * <p> 功能：排水 </p>
 *
 * <p><a href="buSpywPsfayssqController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-30
 * 
 */

@Controller
@RequestMapping("/sp/buSpywPsfayssqController/")
public class BuSpywPsfayssqController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywPsfayssqController.class);

    @Autowired
    private BuSpywPsfayssqService BuSpywPsfayssqService;

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
        logger.info("<{}>执行操作【排水方案预审查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.BuSpywPsfayssqService.queryCondition(json.getMsg());
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
   public requestJson download(final HttpServletRequest request, final HttpServletResponse response,requestJson json) throws Exception {
       User user = RestContext.getCurrentUser();
       logger.info("<{}>执行操作【排水方案预审-下载】",user.getName());
       requestJson j = new requestJson();
       String domresult = "";
       String id=request.getParameter("id");
       domresult = this.BuSpywPsfayssqService.download(id);
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
        logger.info("<{}>执行操作【排水新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.BuSpywPsfayssqService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【排水修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.BuSpywPsfayssqService.update(json.getMsg());
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
        logger.info("<{}>执行操作【排水删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.BuSpywPsfayssqService.queryCondition(json.getMsg());
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
        resultVO = this.BuSpywPsfayssqService.ywlzclhf(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
}
