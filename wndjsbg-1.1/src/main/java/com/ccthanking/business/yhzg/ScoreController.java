/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ScoreController.java
 * 创建日期： 2015-05-17 上午 09:23:51
 * 功能：    服务控制类：分数管理
 * 所含类:   ScoreService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-17 上午 09:23:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.yhzg.service.ScoreService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ScoreController.java </p>
 * <p> 功能：分数管理 </p>
 *
 * <p><a href="ScoreController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-05-17
 *  
 */

@Controller
@RequestMapping("/yhzg/scoreController")
public class ScoreController {

	private static Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreService scoreService;

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
        logger.info("<{}>执行操作【分数管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.scoreService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 查询json加分信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryjfxx")
    @ResponseBody
    public requestJson queryjfxx(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String JFSQ_UID = request.getParameter("JFSQ_UID");
    	String domresult = "";
    	domresult = this.scoreService.queryjfxx(json.getMsg(),JFSQ_UID);
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    /**
     * 查询json加分事件
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryjfsj")
    @ResponseBody
    public requestJson queryjfsj(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String JFSQ_UID = request.getParameter("JFSQ_UID");    	
    	String domresult = "";
    	domresult = this.scoreService.queryjfsj(json.getMsg(),JFSQ_UID);
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    
    /**
     * 查询json审核信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "shenhetonggou")
    @ResponseBody
    public requestJson shenhetonggou(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String JFSQ_UID = request.getParameter("JFSQ_UID");    	
    	String JFSTATUS = request.getParameter("JFSTATUS");    	
    	String agree = request.getParameter("agree");    	
    	String shyj = request.getParameter("shyj");    	
    	String domresult = "";
    	domresult = this.scoreService.updatestatus(agree,JFSTATUS,JFSQ_UID,shyj);
    	j.setMsg(domresult);
    	return j;
    	
    }
    /**
     * 查询json扣分
     * 
     * @param json
     * @return
     * @throws Exception
     */
    
    @RequestMapping(params = "queryFenshu")
    @ResponseBody
    public requestJson queryFenshu(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String condition = request.getParameter("condition");
    	String domresult = "";
    	domresult = this.scoreService.queryFenshu(json.getMsg(),condition);
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
    @RequestMapping(params = "query1")
    @ResponseBody
    public requestJson queryCondition1(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【分数管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.scoreService.queryCondition1(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询json加分
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryJiafen")
    @ResponseBody
    public requestJson queryJiafen(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String shenhe = request.getParameter("shenhe");
    	String domresult = "";
    	domresult = this.scoreService.queryJiafen(json.getMsg(),shenhe);
    	j.setMsg(domresult);
    	return j;    	
    }
    
    
    
    /**
     * 查询json审核信息
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryspxx")
    @ResponseBody
    public requestJson queryspxx(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String JFSQ_UID = request.getParameter("JFSQ_UID");
    	String domresult = "";
    	domresult = this.scoreService.queryspxx(json.getMsg(),JFSQ_UID);
    	j.setMsg(domresult);
    	return j;
    	
    }
    
    
    /**
     * 查询json加分对象
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryjfdx")
    @ResponseBody
    public requestJson queryjfdx(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String JFSQ_UID = request.getParameter("JFSQ_UID");    	
    	String domresult = "";
    	domresult = this.scoreService.queryjfdx(json.getMsg(),JFSQ_UID);
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
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	String sUid = request.getParameter("sUid");
    	domresult = this.scoreService.queryById(sUid);
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
    @RequestMapping(params = "getDshkCount")
    @ResponseBody
    public requestJson getDshkCount(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.scoreService.getDshkCount(json.getMsg());
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
    @RequestMapping(params = "getDshjCount")
    @ResponseBody
    public requestJson getDshjCount(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.scoreService.getDshjCount(json.getMsg());
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
        logger.info("<{}>执行操作【分数管理新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.scoreService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【分数管理修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.scoreService.update(json.getMsg());
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
    @RequestMapping(params = "updatePersonSh")
    @ResponseBody
    protected requestJson updatePersonSh(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理修改】",user.getName());
    	requestJson j = new requestJson();
    	String resultVO = "";
    	resultVO = this.scoreService.updatePersonSh(json.getMsg());
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
    @RequestMapping(params = "updateCompanySh")
    @ResponseBody
    protected requestJson updateCompanySh(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理修改】",user.getName());
    	requestJson j = new requestJson();
    	String resultVO = "";
    	resultVO = this.scoreService.updateCompanySh(json.getMsg());
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
        logger.info("<{}>执行操作【分数管理删除】",user.getName());
        requestJson j = new requestJson();
        String id =  request.getParameter("id");
        String resultVO = "";
        resultVO = this.scoreService.delete(id);
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 计算信用分值
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "calcXyf")
    @ResponseBody
    public requestJson calcXyf(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【分数管理查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	String id = request.getParameter("id");
    	String type = request.getParameter("type");
    	String jia = this.scoreService.getShtgjSum(id, type);
    	String kou = this.scoreService.getShtgkSum(id, type);
    	String result = calcResult(jia,kou);
    	j.setMsg(result);
    	return j;
    	
    }
    
    private String calcResult(String jia,String kou){
    	double j = Double.parseDouble(jia);
    	double k = Double.parseDouble(kou);
    	double r = 100 + j -k;
    	if(Math.round(r)-r==0){
    		return String.valueOf((long)r);
    	}
    	 return String.valueOf(r);
    }
    
    
    

}
