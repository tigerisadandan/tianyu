package com.ccthanking.business.person;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jsx3.gui.Alerts;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.person.service.SgPersonLibraryService;
import com.ccthanking.business.sgenter.vo.AtFileuploadVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.util.Pub;



@Controller
@RequestMapping("/person/SgPersonLibraryController/")
public class SgPersonLibraryController {

    private static Logger logger = LoggerFactory.getLogger(SgPersonLibraryController.class);

    @Autowired
    private SgPersonLibraryService sgPersonLibraryService;
   
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
        logger.info("<{}>执行操作【人员表查询】", user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        
        /*domresult = this.sg_Person_LibraryService.queryCondition(json.getMsg());*/
        
        
        String id = request.getParameter("id");
        if (Pub.empty(id)) {
            domresult = this.sgPersonLibraryService.queryCondition(json.getMsg());//把页面传过来的json查询条件放进方法中进行取值，
           //domresult接收从数据库中查询出来的，已经转换好的数据
        } else {
            String data = "{querycondition: {conditions: [{\"value\":\"" + id
                    + "\",\"fieldname\":\"ghh.id\",\"operation\":\"=\",\"logic\":\"and\"}]}}";
            domresult = this.sgPersonLibraryService.queryCondition(data);
        }

        j.setMsg(domresult);

        return j;

    }
    @RequestMapping(value = "queryNotNull")
    @ResponseBody
    public requestJson queryNotNull(final HttpServletRequest request, requestJson json) throws Exception {

        User user = RestContext.getCurrentUser();

        logger.info("<{}>执行操作【人员表查询】", user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        
        String id = request.getParameter("id");
        if (Pub.empty(id)) {
            domresult = this.sgPersonLibraryService.queryConditionNotNull(json.getMsg());
        } else {
            String data = "{querycondition: {conditions: [{\"value\":\"" + id
                    + "\",\"fieldname\":\"ghh.id\",\"operation\":\"=\",\"logic\":\"and\"}]}}";
            domresult = this.sgPersonLibraryService.queryConditionNotNull(data);
        }
        j.setMsg(domresult);

        return j;

    }
public static void main(String[] args) {
	System.out.println(StringUtils.upperCase("abc"));
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

        requestJson j = new requestJson();
        String resultVO = "";
        
        String status=request.getParameter("status");
        //获取页面的附件
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        resultVO = this.sgPersonLibraryService.insert(json.getMsg(),status,fils);
        
        j.setMsg(resultVO);
        request.getSession().removeAttribute(Constants.FILE_KEY);
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
       
        requestJson j = new requestJson();
        String resultVO = "";
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        resultVO = this.sgPersonLibraryService.update(json.getMsg(),fils);
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(value = "updateStatus")
    @ResponseBody
    protected requestJson updateStatus(HttpServletRequest request, requestJson json) throws Exception {
       
        requestJson j = new requestJson();
        String resultVO = "";
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        String msg = request.getParameter("shenfenID");
        resultVO = this.sgPersonLibraryService.update(msg,fils);
        j.setMsg(resultVO);
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
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        resultVO = this.sgPersonLibraryService.updateShenhe(json.getMsg(),fils,u_id,status,t_id);
        /*if(Constants.getBoolean("DATA_SGRY_PERSON", false)){
        	 sgPersonLibraryService.insertToOldPerson(resultVO);        }
       */
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
        resultVO = this.sgPersonLibraryService.updateStatusOne(u_id,js);
      
    
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

        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgPersonLibraryService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(value = "queryList")
    @ResponseBody
    public requestJson queryList(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【人员ID查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
      
        String id = request.getParameter("sg_person_uid");
        String data = "{querycondition: {conditions: [{\"value\":\"" + id
        + "\",\"fieldname\":\"ghh.id\",\"operation\":\"=\",\"logic\":\"and\"}]}}";
         domresult = this.sgPersonLibraryService.queryList(data);
        //domresult = this.sg_Person_LibraryService.queryList();
        j.setMsg(domresult);
        return j;

    }
  
    /**
     * 查询数据库中是否有该记录json
     * 给新增人员判断
     * 并返回该人员的sg_person_uid
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
        domresult = this.sgPersonLibraryService.queryCodeIsEmpty(msg);
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
        domresult = this.sgPersonLibraryService.queryStatusIsEmpty(msg,bz,personUID);
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
    @RequestMapping(value = "queryPersonZhengshu")
	@ResponseBody
	public requestJson queryPersonZhengshu(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【人员复制并新增数据及附件】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String t_id=request.getParameter("t_id");
         String u_id=request.getParameter("u_id");
        
         domresult=sgPersonLibraryService.updateCopyPerson(t_id,u_id);
        /* if(Constants.getBoolean("DATA_SGRY_PERSON", false)){
         sgPersonLibraryService.insertToOldPersonTwo(domresult,shenfenID,status);
         }*/
         //domresult=sgPersonZhengshuService.updateCopyZhengshu(sg_uid);
    	 j.setMsg(domresult);
	     return j;

	}
    /**
     * 入库状态更改
     * 复制并新增一条状态为40的数据及附件
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryPersonZhengshu2")
	@ResponseBody
	public requestJson queryPersonZhengshu2(final HttpServletRequest request,requestJson json) throws Exception {
    	
    	User user = RestContext.getCurrentUser();
         logger.info("<{}>执行操作【人员复制并新增数据及附件】",user.getName());
         requestJson j = new requestJson();
         String domresult = "";
         String IdCard=request.getParameter("id");
         String status=request.getParameter("status");
         String shenfenID=request.getParameter("shenfenID");
         domresult=sgPersonLibraryService.updateCopyPerson2(status,IdCard,shenfenID);
         
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
        
         domresult=sgPersonLibraryService.tuihui(ids,yijian);
        /* if(Constants.getBoolean("DATA_SGRY_PERSON", false)){
         sgPersonLibraryService.insertToOldPersonTwo(domresult,shenfenID,status);
         }*/
         //domresult=sgPersonZhengshuService.updateCopyZhengshu(sg_uid);
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
         domresult=sgPersonLibraryService.suoding(id,yijian,sjfw,jz_y);
      
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
     
         domresult=sgPersonLibraryService.jiesuo(id);
      
    	 j.setMsg(domresult);
	     return j;

	}
}
