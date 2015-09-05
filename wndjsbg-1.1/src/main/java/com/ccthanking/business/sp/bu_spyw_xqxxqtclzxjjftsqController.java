/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_xqxxqtclzxjjftsqController.java
 * 创建日期： 2014-10-27 下午 06:40:51
 * 功能：    服务控制类：墙改基金征收及返退审批事项
 * 所含类:   bu_spyw_xqxxqtclzxjjftsqService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-27 下午 06:40:51  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sp.service.bu_spyw_xqxxqtclzxjjftsqService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> bu_spyw_xqxxqtclzxjjftsqController.java </p>
 * <p> 功能：墙改基金征收及返退审批事项 </p>
 *
 * <p><a href="bu_spyw_xqxxqtclzxjjftsqController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-10-27
 * 
 */

@Controller
@RequestMapping("/sp/bu_spyw_xqxxqtclController")
public class bu_spyw_xqxxqtclzxjjftsqController {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_xqxxqtclzxjjftsqController.class);

    @Autowired
    private bu_spyw_xqxxqtclzxjjftsqService bu_spyw_xqxxqtclzxjjftsqService;

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
        logger.info("<{}>执行操作【墙改基金征收及返退审批事项查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.bu_spyw_xqxxqtclzxjjftsqService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【墙改基金征收及返退审批事项新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.bu_spyw_xqxxqtclzxjjftsqService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【墙改基金征收及返退审批事项修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.bu_spyw_xqxxqtclzxjjftsqService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     **
     * 下载json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public requestJson download(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【墙改基金征收及返退审批表-下载】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        domresult = this.bu_spyw_xqxxqtclzxjjftsqService.download(id);
        
        j.setMsg(domresult);
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
        logger.info("<{}>执行操作【墙改基金征收及返退审批事项删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.bu_spyw_xqxxqtclzxjjftsqService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
