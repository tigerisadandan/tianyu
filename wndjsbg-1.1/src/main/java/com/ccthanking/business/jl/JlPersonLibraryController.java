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
import com.ccthanking.business.jl.service.JlPersonLibraryService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;

@Controller
@RequestMapping("/jlPersonLibraryController/")
public class JlPersonLibraryController {
	private static Logger logger = LoggerFactory.getLogger(JlPersonLibraryController.class);
	
	@Autowired
	private JlPersonLibraryService jlPersonLibraryService;
	
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
        logger.info("<{}>执行操作【监理人员信息库查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jlPersonLibraryService.queryCondition(json.getMsg());
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
    @RequestMapping(value = "queryNotNull")
    @ResponseBody
    public requestJson queryNotNull(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监理人员信息库查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.jlPersonLibraryService.queryNotNull(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    }
    
    /**
     * 提交审核.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(value = "updateShenhe")
    @ResponseBody
    protected requestJson updateShenhe(HttpServletRequest request, requestJson json) throws Exception {
        
        requestJson j = new requestJson();
        String resultVO = "";
        //String sty=request.getParameter("sty");
        String u_id=request.getParameter("u_id");
        String status=request.getParameter("status");
        String t_id=request.getParameter("t_id");
       // Map<String, AtFileuploadVO> fils = null;
/*        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }*/
        resultVO = this.jlPersonLibraryService.updateShenhe(json.getMsg(),u_id,status,t_id);
  
        j.setMsg(resultVO);
        return j;
    }
    
    
    @RequestMapping(value = "updateStatusOne")
    @ResponseBody
    protected requestJson updateStatusOne(HttpServletRequest request, requestJson json) throws Exception {
        
        requestJson j = new requestJson();
        String resultVO = "";
       
        String u_id=request.getParameter("u_id");
        String js=request.getParameter("js");
        resultVO = this.jlPersonLibraryService.updateStatusOne(u_id,js);
      
    
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(value = "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
    	
    	requestJson j = new requestJson();
    	String resultVO = "";
    	
    	//String u_id=request.getParameter("u_id");
    	String jg=request.getParameter("jg");
    	resultVO = this.jlPersonLibraryService.update(json.getMsg(),jg);
    	
    	
    	j.setMsg(resultVO);
    	return j;
    }
    
    @RequestMapping(value = "updatePerson")
    @ResponseBody
    protected requestJson updatePerson(HttpServletRequest request, requestJson json) throws Exception {
    	
    	requestJson j = new requestJson();
    	String resultVO = "";
    	resultVO = this.jlPersonLibraryService.updatePerson(json.getMsg());
    	
    	
    	j.setMsg(resultVO);
    	return j;
    }
    
    /**
     * 查询数据库中是否有该记录json
     * 给新增人员判断
     * 并返回该人员的jl_person_uid
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryCodeIsEmpty")
    @ResponseBody
    public requestJson queryCodeIsEmpty(final HttpServletRequest request) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【人员表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String msg = request.getParameter("shenfenID");
        domresult = this.jlPersonLibraryService.queryCodeIsEmpty(msg);
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 查询数据库中是否有该记录json
     * 给已入库的人员进行修改时的判断
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryStatusIsEmpty")
    @ResponseBody
    public requestJson queryStatusIsEmpty(final HttpServletRequest request) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【人员表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String bz=request.getParameter("bz");
        String personUID=request.getParameter("personUID");
        String msg = request.getParameter("shenfenID");
        domresult = this.jlPersonLibraryService.queryStatusIsEmpty(msg,bz,personUID);
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 复制并新增数据及附件
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateCopyPerson")
	@ResponseBody
	public requestJson updateCopyPerson(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【人员复制并新增数据及附件】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String t_id=request.getParameter("t_id");
         String u_id=request.getParameter("u_id");
        
         domresult=this.jlPersonLibraryService.updateCopyPerson(t_id,u_id);
        /* if(Constants.getBoolean("DATA_SGRY_PERSON", false)){
         sgPersonLibraryService.insertToOldPersonTwo(domresult,shenfenID,status);
         }*/
         //domresult=sgPersonZhengshuService.updateCopyZhengshu(sg_uid);
    	 j.setMsg(domresult);
	     return j;

	}
    
    /**
     * 退回
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "tuihui")
	@ResponseBody
	public requestJson tuihui(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【人员不通过】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String ids=request.getParameter("ids");
         String yijian=request.getParameter("yijian");
        
         domresult = this.jlPersonLibraryService.tuihui(ids,yijian);
        /* if(Constants.getBoolean("DATA_SGRY_PERSON", false)){
         sgPersonLibraryService.insertToOldPersonTwo(domresult,shenfenID,status);
         }*/
         //domresult=sgPersonZhengshuService.updateCopyZhengshu(sg_uid);
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
        Map map = new HashMap();
        String jg = request.getParameter("jg");
        String yj = request.getParameter("yj");
        String uid = request.getParameter("uid");
        String jlqyid = request.getParameter("jlqyid");
        String pname = request.getParameter("pname");
        map.put("uid",uid); 
		map.put("jg",jg);
		map.put("yj",yj);
		map.put("jlqyid", jlqyid);
		map.put("pname", pname);
		this.jlPersonLibraryService.updateDshxx(map);
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 锁定
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "suoding")
	@ResponseBody
	public requestJson suoding(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【人员锁定】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String id=request.getParameter("id");
         String yijian=request.getParameter("yijian");
         String sjfw=request.getParameter("sjfw");
         String jz_y=request.getParameter("jz_y");
         domresult=jlPersonLibraryService.suoding(id,yijian,sjfw,jz_y);
      
    	 j.setMsg(domresult);
	     return j;

	}
    /**
     * 解锁
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "jiesuo")
	@ResponseBody
	public requestJson jiesuo(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【人员解锁】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String id=request.getParameter("id");
     
         domresult=jlPersonLibraryService.jiesuo(id);
      
    	 j.setMsg(domresult);
	     return j;

	}
	
}
