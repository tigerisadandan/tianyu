/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgEnterPriseLibraryController.java
 * 创建日期： 2014-04-09 上午 10:51:19
 * 功能：    服务控制类：业务页面模版表
 * 所含类:   SgEnterPriseLibraryService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-09 上午 10:51:19  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.business.sgenter.service.SgEnterPriseLibraryService;
import com.ccthanking.business.sgenter.vo.AtFileuploadVO;
import com.ccthanking.business.sgenter.vo.SgEnterpriseLibraryVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.util.Pub;


/**
 * <p> SgEnterPriseLibraryController.java </p>
 * <p> 功能：业务页面模版表 </p>
 *
 * <p><a href="SgEnterPriseLibraryController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-09
 * 
 */

@Controller
@RequestMapping("/sgenter/sgEnterPriseLibraryController/")
public class SgEnterPriseLibraryController {

	private static Logger logger = LoggerFactory.getLogger(SgEnterPriseLibraryController.class);

    @Autowired
    private SgEnterPriseLibraryService sgEnterPriseLibraryService;
    
    
    /**
	 * @param 企业登录验证
	 * @param username
	 * @param password
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception 
	 */
	@RequestMapping(params = "login", method = RequestMethod.POST)
	protected ModelAndView login(final HttpServletRequest request, final ModelAndView mav,HttpServletResponse response,
			@RequestParam(value = "denglu_code") final String username, @RequestParam(value = "pwd") final String password) throws Exception {
		SgEnterpriseLibraryVO vo = this.sgEnterPriseLibraryService.getUserByUsernameAndPassword(username,password);
		if (vo != null) {
			User user = new UserVO();
			user.setAccount(vo.getDenglu_code());
			user.setPWD(vo.getPwd());
			user.setIdCard(vo.getSg_company_uid());
			user.setAccount(vo.getSg_enterprise_library_uid());
			user.setName(vo.getCompany_name());
			user.setDepartment("100000000021");
			user.setSex("1");
			user.setPersonKind("3");
			user.setScretLevel("1");
			user.setFlag("1");
			
			RestContext.setCurrentUser(user);
			
			request.getSession().setAttribute(Globals.USER_KEY, user);
			request.getSession().setAttribute("userId", vo.getSg_company_uid());
			String requestIp = getIpAddr(request);
			if(!Pub.empty(requestIp)){
				user.setIP(requestIp);
			}
			LogManager.writeLoginLog(user, LogManager.LOGIN_STATUS_SUCCESS);
			mav.setViewName("/jsp/framework/portal/frame");
			return mav;
		}else
		{
			request.setAttribute("error", "用户名不存在或密码不正确");
			mav.setViewName("/index");
			return mav;
		}
		
	}	
	
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
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        String zt = request.getParameter("ZT");
        if (StringUtils.isBlank(zt)) {
        	zt = "";
		}
        Map map = new HashMap();
        map.put("ZT", zt);
        domresult = this.sgEnterPriseLibraryService.queryCondition(json.getMsg(), map);
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
    @RequestMapping(value = "queryApproval")
    @ResponseBody
    public requestJson queryApproval(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgEnterPriseLibraryService.queryApproval(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(value = "tuihui")
    @ResponseBody
    public requestJson updatetuihui(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        String ids=request.getParameter("ids");
        String liyou=request.getParameter("yijian");
        domresult = this.sgEnterPriseLibraryService.updatetuihui(ids,liyou);
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
        logger.info("<{}>执行操作【业务页面模版表新增】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String resultVO = "";
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
       
        resultVO = this.sgEnterPriseLibraryService.insert(json.getMsg(),fils);
        j.setMsg(resultVO);
        
		if (Constants.getBoolean("DATA_SYNC_SGENTERPRISE", false)) {
			sgEnterPriseLibraryService.insertToOld(resultVO);
		}
        //注册完成,注销临时对象
//        request.getSession().invalidate();
        //新增完成 删除附件sessionList
        request.getSession().removeAttribute(Constants.FILE_KEY);
        return j;
    }
    
    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getSeq")
    @ResponseBody
    protected String getSeq(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表新增】",user!=null?user.getName():"注册用户");
        String sel = DBUtil.getSequenceValue("SG_ENTERPRISE_LIBRARY_UID");
        String seccr = DBUtil.getSequenceValue("CREDIT_COMMEND_REWARD_UID");
        String secp = DBUtil.getSequenceValue("CREDIT_PROJECTS_UID");
        return "{\"ID\":\""+sel+"\",\"JXID\":\""+seccr+"\",\"XMID\":\""+secp+"\"}";
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
        logger.info("<{}>执行操作【业务页面模版表修改】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String resultVO = "";
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        resultVO = this.sgEnterPriseLibraryService.update(json.getMsg(),fils,request.getParameter("id"));
        
//        if (Constants.DATA_SYNC_SGENTERPRISE) {
//			sgEnterPriseLibraryService.insertToOld(resultVO);
//		}
        if (Constants.getBoolean("DATA_SYNC_SGENTERPRISE", false)) {
			sgEnterPriseLibraryService.insertToOld(resultVO);
		}
        sgEnterPriseLibraryService.updateCopyToRk(resultVO);
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
        logger.info("<{}>执行操作【业务页面模版表删除】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgEnterPriseLibraryService.delete(json.getMsg());
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
    @RequestMapping(value = "loadAllZizhi")
    @ResponseBody
    public requestJson queryAll(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgEnterPriseLibraryService.queryCondition(json.getMsg(),null);
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
    @RequestMapping(value = "queryCodeIsEmpty")
    @ResponseBody
    public requestJson queryCodeIsEmpty(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        String msg = "{\"code\":\""+request.getParameter("code")+"\",\"id\":\""+request.getParameter("id")+"\"}";
        domresult = this.sgEnterPriseLibraryService.queryCodeIsEmpty(msg);
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loadFilesList")
    @ResponseBody
    public requestJson loadFilesList(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        String target_id = request.getParameter("tid");
        String file_type = request.getParameter("ftype");
        String business_type = request.getParameter("business_type");
        Map map = new HashMap<String,String>();
        map.put("target_id", target_id);
        map.put("file_type", file_type);
        map.put("business_type",business_type);
        domresult = this.sgEnterPriseLibraryService.loadFiles(map);
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
    @RequestMapping(value = "updateCopySGXX")
    @ResponseBody
    public requestJson updateCopySGXX(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        this.sgEnterPriseLibraryService.updateCopySGXX();
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
    @RequestMapping(value = "queryAppList")
    @ResponseBody
    public requestJson queryAppList(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        String status = request.getParameter("status");
        Map map = new HashMap();
        map.put("status", status==null?"":status);
        domresult = this.sgEnterPriseLibraryService.queryAppList(json.getMsg(),map);
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
    @RequestMapping(value = "queryOnSp")
    @ResponseBody
    public requestJson queryOnSp(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【审批中的企业信息】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgEnterPriseLibraryService.queryOnSp(json.getMsg());
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
    @RequestMapping(value = "updateCopyByStatus")
    @ResponseBody
    public requestJson updateCopyByStatus(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【业务页面模版表查询】",user!=null?user.getName():"注册用户");
        requestJson j = new requestJson();
        String domresult = "";
        Map map = new HashMap();
        String uid = request.getParameter("uid");
        String status = request.getParameter("status");
        String reason = request.getParameter("reason");
        map.put("uid",uid); 
		map.put("status",status);
		map.put("reason",reason);
		domresult = this.sgEnterPriseLibraryService.updateCopyByStatus(map);
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
        String id = request.getParameter("id");
        String denglu_code = (String)request.getParameter("denglu_code");//企业登陆code
        String companyUid = request.getParameter("companyUid");//企业编号
        String company_name = request.getParameter("company_name");//企业名称
        map.put("uid",uid); 
		map.put("jg",jg);
		map.put("yj",yj);
		map.put("id",id);
		map.put("denglu_code", denglu_code);
		map.put("companyUid", companyUid);
		map.put("company_name", company_name);
		this.sgEnterPriseLibraryService.updateDshxx(map);
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 确认奖项信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateQy")
    @ResponseBody
    public requestJson updateQy(final HttpServletRequest request) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【确认奖项信息】",user!=null?user.getName():"注册用户");
        
        requestJson j = new requestJson();
        String domresult = "success";
        String uid = request.getParameter("uid");
		this.sgEnterPriseLibraryService.updateQueren(uid);
        j.setMsg(domresult);
        return j;

    }
    
}
