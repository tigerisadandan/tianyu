package com.ccthanking.business.dtgl.sgsx;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.sgsx.service.SgsxProjectService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgsxProjectController.java </p>
 * <p> 功能：施工手续 </p>
 *
 * <p><a href="SgsxProjectControllerr.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luohonggang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-07-14
 * 
 */

@Controller
@RequestMapping("/sgsx/SgsxProjectController")
public class SgsxProjectController {
	private static Logger logger = LoggerFactory.getLogger(SgsxProjectController.class);

    @Autowired
    private SgsxProjectService sgsxProjectService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryYWxxMc")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgsxProjectService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "querySgCommpany")
    @ResponseBody
    public requestJson queryBz(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgsxProjectService.querySFszgc((request.getParameter("gcid")));
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "queryYwType")
    @ResponseBody
    public requestJson queryYwType(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
//        logger.info("<{}>执行操作【审批业务信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgsxProjectService.queryYwStatus((request.getParameter("gcid")));
        j.setMsg(domresult);
        return j;

    }
    
    
    @RequestMapping(value = "querySgYwlzId")
    @ResponseBody
    public requestJson querySgYwlzId(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        requestJson j = new requestJson();
        String gcId = request.getParameter("gongcheng_uid");
        String spywId = request.getParameter("spyw_uid");
        String domresult = "";
        domresult = this.sgsxProjectService.querySgYwlzId(gcId,spywId);
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
        logger.info("<{}>执行操作【审批业务信息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgsxProjectService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgsxProjectService.update(json.getMsg());
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
        logger.info("<{}>执行操作【审批业务信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgsxProjectService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 加载审批业务信息树
     * @param request
     * @param response
     * @throws Exception 
     */
    @RequestMapping(value = "getAllSpyw")
    @ResponseBody
    public void getAllSpyw(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String ywJson = this.sgsxProjectService.getAllSpyw();
    	
		try {
	    	response.setCharacterEncoding("UTF-8");
	    	System.out.println(ywJson);
			response.getWriter().print(ywJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}

