/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpywQgjjzsjftspsxQgsftzdController.java
 * 创建日期： 2014-11-07 上午 09:41:47
 * 功能：    服务控制类：八墙体材料专项基金预收款收费通知单
 * 所含类:   BuSpywQgjjzsjftspsxQgsftzdService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-07 上午 09:41:47  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.bzwj.service.BuSpywQgjjzsjftspsxQgsftzdService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywQgjjzsjftspsxQgsftzdController.java </p>
 * <p> 功能：八墙体材料专项基金预收款收费通知单 </p>
 *
 * <p><a href="BuSpywQgjjzsjftspsxQgsftzdController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-07
 * 
 */

@Controller
@RequestMapping("/bzwj/buSpywQgjjzsjftspsxQgsftzdController")
public class BuSpywQgjjzsjftspsxQgsftzdController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywQgjjzsjftspsxQgsftzdController.class);

    @Autowired
    private BuSpywQgjjzsjftspsxQgsftzdService buSpywQgjjzsjftspsxQgsftzdService;

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
        logger.info("<{}>执行操作【八墙体材料专项基金预收款收费通知单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywQgjjzsjftspsxQgsftzdService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "getCount")
    @ResponseBody
    public requestJson getCount(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【八墙体材料专项基金预收款收费通知单查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywQgjjzsjftspsxQgsftzdService.getCount();
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
        domresult = this.buSpywQgjjzsjftspsxQgsftzdService.queryByLzbz(request.getParameter("LZBZ_UID"));
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
        logger.info("<{}>执行操作【八墙体材料专项基金预收款收费通知单新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywQgjjzsjftspsxQgsftzdService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【八墙体材料专项基金预收款收费通知单修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywQgjjzsjftspsxQgsftzdService.update(json.getMsg());
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
        logger.info("<{}>执行操作【八墙体材料专项基金预收款收费通知单删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywQgjjzsjftspsxQgsftzdService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(value = "download")
    @ResponseBody
    public String downloadCondition(final HttpServletRequest request,final HttpServletResponse response, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>>执行操作【三产项目面积核算单转PDF】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("uid");
        return  this.buSpywQgjjzsjftspsxQgsftzdService.querytoword(response,request.getParameter("uid"),request.getParameter("filename"));

    }

}
