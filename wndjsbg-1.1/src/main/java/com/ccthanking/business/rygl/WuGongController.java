/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.WuGongController.java
 * 创建日期： 2015-03-24 上午 11:44:51
 * 功能：    服务控制类：务工信息
 * 所含类:   WuGongService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 上午 11:44:51  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.rygl.service.WuGongService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> WuGongController.java </p>
 * <p> 功能：务工信息 </p>
 *
 * <p><a href="WuGongController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */

@Controller
@RequestMapping("/rygl/wuGongController")
public class WuGongController {

	private static Logger logger = LoggerFactory.getLogger(WuGongController.class);

    @Autowired
    private WuGongService wuGongService;

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
        logger.info("<{}>执行操作【务工信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.wuGongService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【务工信息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wuGongService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【务工信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wuGongService.update(json.getMsg());
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
        logger.info("<{}>执行操作【务工信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wuGongService.delete(json.getMsg());
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
    @RequestMapping(params = "wugongtj")
    @ResponseBody
    public requestJson wugongtj(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【务工信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.wuGongService.wugongtj(json.getMsg());
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
    @RequestMapping(params = "queryById")
    @ResponseBody
    public requestJson queryById(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【务工信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.wuGongService.queryById(json.getMsg());
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
    @RequestMapping(params = "queryJineng")
    @ResponseBody
    public requestJson queryJineng(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【务工信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.wuGongService.queryJineng(json.getMsg());
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
    @RequestMapping(params = "queryTijianInfo")
    @ResponseBody
    public requestJson queryTijianInfo(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【务工信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.wuGongService.queryTijianInfo(json.getMsg());
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
    @RequestMapping(params = "queryGzqk")
    @ResponseBody
    public requestJson queryqueryGzqk(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【务工信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.wuGongService.queryGzqk(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }

}
