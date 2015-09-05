/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsZyssSpController.java
 * 创建日期： 2014-06-17 下午 02:10:09
 * 功能：    服务控制类：建设项目环境影响评价报告表（书）审批--主要设施
 * 所含类:   BuSpywHjyxdjgzcnsZyssSpService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:10:09  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.sp.service.BuSpywHjyxdjgzcnsZyssSpService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywHjyxdjgzcnsZyssSpController.java </p>
 * <p> 功能：建设项目环境影响评价报告表（书）审批--主要设施 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsZyssSpController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */

@Controller
@RequestMapping("/sp/buSpywHjyxdjgzcnsZyssSpController/")
public class BuSpywHjyxdjgzcnsZyssSpController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsZyssSpController.class);

    @Autowired
    private BuSpywHjyxdjgzcnsZyssSpService buSpywHjyxdjgzcnsZyssSpService;

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
        logger.info("<{}>执行操作【建设项目环境影响评价报告表（书）审批--主要设施查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywHjyxdjgzcnsZyssSpService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    /**
     * 更改时获取数据
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryZyssUpdate")
    @ResponseBody
    public requestJson queryNrgmUpdate(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【建设项目环境影响评价报告表（书）审批-明细查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        domresult = this.buSpywHjyxdjgzcnsZyssSpService.queryZyssUpdate(id);
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
        logger.info("<{}>执行操作【建设项目环境影响评价报告表（书）审批--主要设施新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywHjyxdjgzcnsZyssSpService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【建设项目环境影响评价报告表（书）审批--主要设施修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywHjyxdjgzcnsZyssSpService.update(json.getMsg());
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
        logger.info("<{}>执行操作【建设项目环境影响评价报告表（书）审批--主要设施删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywHjyxdjgzcnsZyssSpService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
