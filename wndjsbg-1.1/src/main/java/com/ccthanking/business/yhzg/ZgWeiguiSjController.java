/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgWeiguiSjController.java
 * 创建日期： 2015-04-21 下午 01:21:34
 * 功能：    服务控制类：违规事件
 * 所含类:   ZgWeiguiSjService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:21:34  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.yhzg.service.ZgWeiguiSjService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ZgWeiguiSjController.java </p>
 * <p> 功能：违规事件 </p>
 *
 * <p><a href="ZgWeiguiSjController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Controller
@RequestMapping("/yhzg/zgWeiguiSjController")
public class ZgWeiguiSjController {

	private static Logger logger = LoggerFactory.getLogger(ZgWeiguiSjController.class);

    @Autowired
    private ZgWeiguiSjService zgWeiguiSjService;

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
        logger.info("<{}>执行操作【违规事件查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.zgWeiguiSjService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryZgsj")
    @ResponseBody
    public requestJson queryZgsj(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【违规事件查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgWeiguiSjService.queryZgsj(json.getMsg());
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
        logger.info("<{}>执行操作【违规事件新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgWeiguiSjService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【违规事件修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgWeiguiSjService.update(json.getMsg());
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
        logger.info("<{}>执行操作【违规事件删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgWeiguiSjService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "getTree")
    @ResponseBody
    public void getTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【违规事件查询】",user.getName());
        String domresult = this.zgWeiguiSjService.getTree();
        response.setCharacterEncoding("UTF-8");
        try {
          response.getWriter().print(domresult);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

}
