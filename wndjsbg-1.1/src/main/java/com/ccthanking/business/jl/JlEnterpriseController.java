package com.ccthanking.business.jl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jl.service.JlEnterpriseService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;



@Controller
@RequestMapping("/jlEnterpriseController")
public class JlEnterpriseController {
	private static Logger logger = LoggerFactory.getLogger(JlEnterpriseController.class);
	
	@Autowired
	private JlEnterpriseService jlEnterpriseService;

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
        logger.info("<{}>执行操作【监理企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jlEnterpriseService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;
    }
    
    @RequestMapping(params = "queryold")
    @ResponseBody
    public requestJson queryOldCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监理企业信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jlEnterpriseService.queryOldCondition(json.getMsg());
        j.setMsg(domresult);
        return j;
    }

    
    @RequestMapping(params = "queryspyj")
    @ResponseBody
    public requestJson queryspyj(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监理企业审批意见查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jlEnterpriseService.queryspyjCondition(json.getMsg());
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
        logger.info("<{}>执行操作【监理企业信息库新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlEnterpriseService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【监理企业信息库修改】",user.getName());
        requestJson j = new requestJson();
        String jg = request.getParameter("jg");
        String resultVO = "";
        resultVO = this.jlEnterpriseService.update(json.getMsg(),jg);
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(value = "updateEnterprise")
    @ResponseBody
    protected requestJson updateEnterprise(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理企业信息库修改】",user.getName());
    	requestJson j = new requestJson();
    	String resultVO = "";
    	resultVO = this.jlEnterpriseService.updateEnterprise(json.getMsg());
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
        logger.info("<{}>执行操作【监理企业信息库删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jlEnterpriseService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    
    /**
     * 退回
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "tuihui")
	@ResponseBody
	public requestJson tuihui(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【企业不通过】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String ids=request.getParameter("ids");
         String yijian=request.getParameter("yijian");
        
         domresult = this.jlEnterpriseService.tuihui(ids,yijian);

    	 j.setMsg(domresult);
	     return j;

	}
    
    
    @RequestMapping(value = "updateDshxx")
    @ResponseBody
    public requestJson updateDshxx(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "success";
        Map<String,String> map = new HashMap<String,String> ();
        String jg = request.getParameter("jg");
        String yj = request.getParameter("yj");
        String uid = request.getParameter("uid");
        String qyId = request.getParameter("qyId");
        String qyName = request.getParameter("qyName");
        String qyCode = request.getParameter("qyCode");
        map.put("uid",uid); 
		map.put("jg",jg);
		map.put("yj",yj);
		map.put("qyId",qyId);
		map.put("qyName",qyName);
		map.put("qyCode",qyCode);
		this.jlEnterpriseService.updateDshxx(map);
        j.setMsg(domresult);
        return j;

    }
    
    
    /** 
    * @param json
    * @return
    * @throws Exception
    */
   @RequestMapping(value = "updateCopyByStatus")
   @ResponseBody
   public requestJson updateCopyByStatus(final HttpServletRequest request) throws Exception {
       User user = RestContext.getCurrentUser();
       logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
       requestJson j = new requestJson();
       String domresult = "";
       Map<String,String>  map = new HashMap<String,String> ();
       String uid = request.getParameter("uid");
       String status = request.getParameter("status");
       String reason = request.getParameter("reason");
       map.put("uid",uid); 
		map.put("status",status);
		map.put("reason",reason);
		domresult = this.jlEnterpriseService.updateCopyByStatus(map);
       j.setMsg(domresult);
       return j;

   }

}
