/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgzxt.service.getZxtIndexInformationController.java
 * 创建日期： 2015-08-13 下午 03:19:20
 * 功能：    服务控制类：施工子系统首页信息
 * 所含类:   getZxtIndexInformationService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-08-13 下午 03:19:20  老虎是只耽耽   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgzxt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgzxt.service.getZxtIndexInformationService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> getZxtIndexInformationController.java </p>
 * <p> 功能：施工子系统首页信息 </p>
 *
 * <p><a href="getZxtIndexInformationController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">老虎是只耽耽</a>
 * @version 0.1
 * @since 2015-08-13
 * 
 */

@Controller
@RequestMapping("/sgzxt/getZxtIndexInformationController")
public class getZxtIndexInformationController {

	private static Logger logger = LoggerFactory.getLogger(getZxtIndexInformationController.class);

    @Autowired
    private getZxtIndexInformationService getZxtIndexInformationService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson query(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工子系统首页信息查询】",user.getName());
        requestJson j = new requestJson();
        String GongCheng_UID = request.getParameter("GongCheng_UID");
        String domresult = "";
        domresult = this.getZxtIndexInformationService.queryCondition(json.getMsg(),GongCheng_UID);
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "queryGC_SCORE")
    @ResponseBody
    public requestJson queryGC_SCORE(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【施工子系统首页信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.queryGC_SCORE(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    @RequestMapping(params = "queryGC_SCORE_detail")
    @ResponseBody
    public requestJson queryGC_SCORE_detail(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【施工子系统首页信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.queryGC_SCORE_detail(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    
    @RequestMapping(params = "querySGRY")
    @ResponseBody
    public requestJson querySGRY(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【施工单位人员信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.querySGRY(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    @RequestMapping(params = "queryJLRY")
    @ResponseBody
    public requestJson queryJLRY(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理单位人员信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.queryJLRY(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    
    @RequestMapping(params = "queryRYXX_detail")
    @ResponseBody
    public requestJson queryRYXX_detail(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理单位人员信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String personType = request.getParameter("personType");
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.queryRYXX_detail(json.getMsg(),personType);
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    @RequestMapping(params = "queryRYXX_Fileid")
    @ResponseBody
    public requestJson queryRYXX_Fileid(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【人员信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String PERSON_UID = request.getParameter("PERSON_UID");
    	String File_type = request.getParameter("File_type");
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.queryRYXX_Fileid(json.getMsg(),PERSON_UID,File_type);
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    @RequestMapping(params = "querygc")
    @ResponseBody
    public requestJson querygc(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【工程信息查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.getZxtIndexInformationService.querygc(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }

}
