package com.ccthanking.business.nbgl.jhgl;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.nbgl.jhgl.service.WorkPlanService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> WorkPlanController.java </p>
 * <p> 功能：计划管理 </p>
 *
 * <p><a href="WorkPlanController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-06-22
 * 
 */



@Controller
@RequestMapping("/nbgl/jhgl/workPlanController")
public class WorkPlanController {

	private static Logger logger = LoggerFactory.getLogger(WorkPlanController.class);

    @Autowired
    private WorkPlanService workPlanService;
    
    /**
     * 打印 工作计划
     * @param request
     * @param response
     * @param json
     * @return 
     * @throws Exception 
     */
	@RequestMapping(params = "print")
	@ResponseBody
	public String print(HttpServletRequest request, HttpServletResponse response, requestJson json) throws Exception {
		String timeStr = request.getParameter("timeStr");
		String path = "";
		try {
			path = this.workPlanService.toword(response,timeStr);
			download(request,response,path);
		} catch (SystemException e) {
			logger.error("错误提示：{}", e.getMessage());
			logger.error("***打印报表出错!***", e);
			MsgUtils.sendErrorMsgMySelfNewPageForword("错误提示", e.getMessage(), request, response);
		}
		return path;
	}
	@RequestMapping(params = "download")
    public void download(HttpServletRequest request, HttpServletResponse response,String path){
		FileUploadService ser = new FileUploadService();
		String encoding = request.getCharacterEncoding();
		response.setCharacterEncoding(encoding);
		//String path = request.getParameter("path");
		File file = new File(path);
         try {
			if (file.exists()) {
			       int bytes = 0;
			       ServletOutputStream op = response.getOutputStream();
			       
			       response.setContentType(ser.getMimeType(file));
			       response.setContentLength((int) file.length());
					// response.setCharacterEncoding("UTF-8");
			       response.setHeader( "Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord(file.getName()) + "\"" );
			       byte[] bbuf = new byte[1024];
			       DataInputStream in = new DataInputStream(new FileInputStream(file));
			       while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
			           op.write(bbuf, 0, bytes);
			       }
			       in.close();
			       op.flush();
			       op.close();
			       
			   }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

  }
    /**
     * 查询 部门 名称
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDeptName") // select o.org_name from organize o where  organize_uid =16
    @ResponseBody
    public requestJson queryCodeIsEmpty(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
        String deptId = request.getParameter("deptId");
        requestJson j = new requestJson();
        String domresult = "";
       // String msg = "{\"code\":\""+request.getParameter("code")+"\",\"id\":\""+request.getParameter("id")+"\"}";
        domresult = this.workPlanService.queryCode(deptId);
        j.setMsg(domresult);
        return j;


    }
    /**
     * 查询当前用户所在部门的下周计划
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDeptData")
    @ResponseBody
    public requestJson queryData(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
         System.out.println("进入 deptdata..");
        requestJson j = new requestJson();
        String  timeStr = request.getParameter("timeStr");
        String domresult = "";
      
        domresult = this.workPlanService.queryByData(timeStr);//String
        j.setMsg(domresult);
        return j;
    }
   
    /**
     * 获取 p_organize_uid 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryExist")
    @ResponseBody
    public requestJson queryByUID(final HttpServletRequest request, requestJson json) throws Exception {
    	
	User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
        requestJson j = new requestJson();
        String  user_uid = request.getParameter("user_uid");
        String domresult = "";
      
        domresult = this.workPlanService.queryOid(user_uid);//String
        j.setMsg(domresult);
        return j;
    }
    
    /**
     * 查询周
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query01")
    @ResponseBody
    public requestJson queryByWeekTime(final HttpServletRequest request, requestJson json) throws Exception {
    	
	User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
        requestJson j = new requestJson();
        String  nianfen = request.getParameter("nianfen");
        String domresult = "";
      
        domresult = this.workPlanService.queryByTime(nianfen);//String
        j.setMsg(domresult);
        return j;
    }
    /**
     * 查询 json
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
      
        domresult = this.workPlanService.queryCondition(json.getMsg());//String
        j.setMsg(domresult);
        return j;

    }

    /**
     * 保存数据json(非更新)
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【计划管理新增】",user.getName());
        requestJson j = new requestJson();
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        for (int i = 1; i <= 7; i++) {
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("deptId", request.getParameter("deptId_"+i));
        	map.put("name", request.getParameter("name_"+i));
        	map.put("workId", request.getParameter("workId_"+i));
        	map.put("day", request.getParameter("day_"+i));
        	map.put("status", request.getParameter("status_"+i));//获取状态
        	map.put("contentam", request.getParameter("contentam_"+i));
        	map.put("contentpm", request.getParameter("contentpm_"+i));
        	list.add(map);
		}
        String resultVO = "";
        resultVO = this.workPlanService.insert(list);
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 查询 部门数据.(打印页面显示)
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "querydata")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
User user = RestContext.getCurrentUser();
    	
        logger.info("<{}>执行操作【计划管理查询】",user.getName());
        String getTime = request.getParameter("getTime");
        requestJson j = new requestJson();
        String domresult = "";
      
        domresult = this.workPlanService.getDataForDept(getTime);//String
        
        j.setMsg(domresult);
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
        logger.info("<{}>执行操作【计划管理删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.workPlanService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    
    
    
}
