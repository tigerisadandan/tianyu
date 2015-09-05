/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpywJyfwfsfJsgcjktzdController.java
 * 创建日期： 2014-11-18 下午 03:02:34
 * 功能：    服务控制类：十、交易服务费收费
 * 所含类:   BuSpywJyfwfsfJsgcjktzdService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 03:02:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.bzwj.service.BuSpywJyfwfsfJsgcjktzdService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywJyfwfsfJsgcjktzdController.java </p>
 * <p> 功能：十、交易服务费收费 </p>
 *
 * <p><a href="BuSpywJyfwfsfJsgcjktzdController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

@Controller
@RequestMapping("/bzwj/buSpywJyfwfsfJsgcjktzdController")
public class BuSpywJyfwfsfJsgcjktzdController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJyfwfsfJsgcjktzdController.class);

    @Autowired
    private BuSpywJyfwfsfJsgcjktzdService buSpywJyfwfsfJsgcjktzdService;

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
        logger.info("<{}>执行操作【十、交易服务费收费查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywJyfwfsfJsgcjktzdService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "getCount")
    @ResponseBody
    public requestJson getCount(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【十、交易服务费收费查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywJyfwfsfJsgcjktzdService.getCount();
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryByLzbz")
    @ResponseBody
    public requestJson queryByLzbz(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【三产项目面积核算单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywJyfwfsfJsgcjktzdService.queryByLzbz(request.getParameter("LZBZ_UID"));
        j.setMsg(domresult);
        return j;

    }
  
    
    @RequestMapping(value = "download")
    @ResponseBody
    public String downloadCondition(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【十、交易服务费收费查询转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
        return  this.buSpywJyfwfsfJsgcjktzdService.querytoword(response,request.getParameter("uid"),request.getParameter("filename"));

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
        logger.info("<{}>执行操作【十、交易服务费收费新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywJyfwfsfJsgcjktzdService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【十、交易服务费收费修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywJyfwfsfJsgcjktzdService.update(json.getMsg());
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
        logger.info("<{}>执行操作【十、交易服务费收费删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywJyfwfsfJsgcjktzdService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
