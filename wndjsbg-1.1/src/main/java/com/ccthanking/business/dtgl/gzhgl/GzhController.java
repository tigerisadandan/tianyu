/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhController.java
 * 创建日期： 2015-05-27 上午 10:40:51
 * 功能：    服务控制类：告知会
 * 所含类:   GzhService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-27 上午 10:40:51  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.gzhgl.service.GzhService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> GzhController.java </p>
 * <p> 功能：告知会 </p>
 *
 * <p><a href="GzhController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-27
 * 
 */

@Controller
@RequestMapping("/gzhgl/gzhController")
public class GzhController {

	private static Logger logger = LoggerFactory.getLogger(GzhController.class);

    @Autowired
    private GzhService gzhService;

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
        logger.info("<{}>执行操作【告知会查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.gzhService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 根据工程编号查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "querySingle")
    @ResponseBody
    public requestJson querySingleCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【告知会查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.gzhService.querySingleCondition(json.getMsg());
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
        logger.info("<{}>执行操作【告知会新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhService.insert(json.getMsg());
        j.setMsg(resultVO);
        JSONObject object = JSONObject.fromObject(resultVO);
        JSONObject responseVal = (JSONObject) object.get("response");
        JSONArray dataVal = (JSONArray) responseVal.get("data");
        JSONObject jo = (JSONObject) dataVal.get(0);
        String gzh_uid = jo.getString("GZH_UID");
        request.getSession().setAttribute("gzh_uid",gzh_uid);
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
        logger.info("<{}>执行操作【告知会修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhService.update(json.getMsg());
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
        logger.info("<{}>执行操作【告知会删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 工程单位查询	
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "queryUndoneProject")
    @ResponseBody
    public requestJson queryUndoneProject(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目单位查询】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhService.queryUndoneProject(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 签到人员查询	
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "querySgPersonByGC")
    @ResponseBody
    public requestJson querySgPersonByGC(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【签到人员查询】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhService.querySgPersonByGC(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    

}
