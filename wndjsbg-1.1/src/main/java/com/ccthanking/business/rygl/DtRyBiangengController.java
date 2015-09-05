/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.DtRyBiangengController.java
 * 创建日期： 2015-04-12 下午 08:31:04
 * 功能：    服务控制类：人员变更
 * 所含类:   DtRyBiangengService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-12 下午 08:31:04  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.rygl.service.DtRyBiangengService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> DtRyBiangengController.java </p>
 * <p> 功能：人员变更 </p>
 *
 * <p><a href="DtRyBiangengController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java1@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-12
 * 
 */

@Controller
@RequestMapping("/rygl/dtRyBiangengController")
public class DtRyBiangengController {

	private static Logger logger = LoggerFactory.getLogger(DtRyBiangengController.class);

    @Autowired
    private DtRyBiangengService dtRyBiangengService;

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
        logger.info("<{}>执行操作【人员变更查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.dtRyBiangengService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryBg")
    @ResponseBody
    public requestJson queryBg(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【人员变更查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.dtRyBiangengService.queryBg(json.getMsg());
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
    @RequestMapping(params = "getBgCount")
    @ResponseBody
    public requestJson getBgCount(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【人员变更查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.dtRyBiangengService.getBgCount(json.getMsg());
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
        logger.info("<{}>执行操作【人员变更新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRyBiangengService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【人员变更修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRyBiangengService.update(json.getMsg());
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
    @RequestMapping(params = "updateTg")
    @ResponseBody
    protected requestJson updateTg(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【人员变更修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRyBiangengService.updateTg(json.getMsg());
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
    @RequestMapping(params = "updateTh")
    @ResponseBody
    protected requestJson updateTh(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【人员变更修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRyBiangengService.updateTh(json.getMsg());
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
        logger.info("<{}>执行操作【人员变更删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.dtRyBiangengService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
