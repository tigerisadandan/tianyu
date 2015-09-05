/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.MinGongController.java
 * 创建日期： 2015-03-23 下午 12:08:31
 * 功能：    服务控制类：农民工信息
 * 所含类:   MinGongService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-23 下午 12:08:31  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rygl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.rygl.service.MinGongService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> MinGongController.java </p>
 * <p> 功能：农民工信息 </p>
 *
 * <p><a href="MinGongController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-23
 * 
 */

@Controller
@RequestMapping("/rygl/minGongController")
public class MinGongController {

	private static Logger logger = LoggerFactory.getLogger(MinGongController.class);

    @Autowired
    private MinGongService minGongService;

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
        logger.info("<{}>执行操作【农民工信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.minGongService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryByZjNumber")
    @ResponseBody
    public requestJson queryByZjNumber(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【农民工信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.minGongService.queryByZjNumber(json.getMsg());
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
        logger.info("<{}>执行操作【农民工信息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.minGongService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【农民工信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.minGongService.update(json.getMsg());
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
        logger.info("<{}>执行操作【农民工信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.minGongService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
   
    
    /**
     * 查询json2
     * 动态管理——>人员管理
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query2")
    @ResponseBody
    public requestJson queryCondition2(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【农民工信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.minGongService.queryCondition2(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 查询json3
     * 动态管理——>人员管理
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "detail")
    @ResponseBody
    public requestJson queryByMinGongUID(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【农民工信息查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	String MINGONG_UID = request.getParameter("MINGONG_UID");
    	String resultVo = "";
    	resultVo = this.minGongService.queryByMinGongUID(MINGONG_UID);
		j.setMsg(resultVo);    	
    	return j;    	
    }

}
