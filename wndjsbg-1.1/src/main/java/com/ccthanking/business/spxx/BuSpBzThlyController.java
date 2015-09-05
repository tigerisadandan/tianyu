/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpBzThlyController.java
 * 创建日期： 2014-06-29 上午 10:05:44
 * 功能：    服务控制类：步骤处理时的退回理由
 * 所含类:   BuSpBzThlyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-29 上午 10:05:44  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.spxx.service.BuSpBzThlyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpBzThlyController.java </p>
 * <p> 功能：步骤处理时的退回理由 </p>
 *
 * <p><a href="BuSpBzThlyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-29
 * 
 */

@Controller
@RequestMapping("/spxx/buSpBzThlyController")
public class BuSpBzThlyController {

	private static Logger logger = LoggerFactory.getLogger(BuSpBzThlyController.class);

    @Autowired
    private BuSpBzThlyService buSpBzThlyService;

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
        logger.info("<{}>执行操作【步骤处理时的退回理由查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpBzThlyService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【步骤处理时的退回理由新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpBzThlyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【步骤处理时的退回理由修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpBzThlyService.update(json.getMsg());
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
        logger.info("<{}>执行操作【步骤处理时的退回理由删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpBzThlyService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
   /**
    * 审批业务信息树
    * */ 

    @ResponseBody
	@RequestMapping(params="getSpYwxx")
	public void getSpYwxx(HttpServletRequest request, HttpServletResponse response) {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【步骤处理时的退回理由删除】",user.getName());
		try {
			String menuJson = this.buSpBzThlyService.getSpYwxx();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(menuJson);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
    }

}
