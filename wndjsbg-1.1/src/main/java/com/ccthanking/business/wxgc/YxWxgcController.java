/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxWxgcController.java
 * 创建日期： 2014-12-23 下午 01:29:58
 * 功能：    服务控制类：微型工程
 * 所含类:   YxWxgcService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:29:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.wxgc.service.YxWxgcService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.util.Pub;


/**
 * <p> YxWxgcController.java </p>
 * <p> 功能：微型工程 </p>
 *
 * <p><a href="YxWxgcController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Controller
@RequestMapping("/wxgc/yxWxgcController")
public class YxWxgcController {

	private static Logger logger = LoggerFactory.getLogger(YxWxgcController.class);

    @Autowired
    private YxWxgcService yxWxgcService;

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
        logger.info("<{}>执行操作【微型工程查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxWxgcService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }

    @RequestMapping(params = "queryspyj")
    @ResponseBody
    public requestJson queryspyjCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【微型工程审批记录查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.yxWxgcService.queryspyjCondition(json.getMsg());
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
        logger.info("<{}>执行操作【微型工程新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxWxgcService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【微型工程修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxWxgcService.update(json.getMsg());
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
        logger.info("<{}>执行操作【微型工程删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.yxWxgcService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    

    @RequestMapping(params = "validZbj")
    @ResponseBody
    public requestJson validZbj(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【微型工程中标价验证】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map=new HashMap();
        String GC_TYPE_CODE=request.getParameter("GC_TYPE_CODE");
        String ZBJ=request.getParameter("ZBJ");
        map.put("GC_TYPE_CODE", GC_TYPE_CODE);
        map.put("ZBJ", ZBJ);
        domresult = this.yxWxgcService.validZbj(map);
        j.setMsg(domresult);
        return j;
    }
    
    
    //微型工程公告截止时间计算，在开始的时间上延后三天
    @RequestMapping(params = "wxgcGgjzsjJs")
    @ResponseBody
    public requestJson wxgcGgjzsjJs(final HttpServletRequest request, requestJson json) throws Exception {
        requestJson j = new requestJson();
        String GGKSRQ=request.getParameter("GGKSRQ");
        Date GGKSRQDATE= Pub.toDate("yyyy-MM-dd HH:mm", GGKSRQ);
        
		Calendar  cal = Calendar.getInstance();
		cal.setTime(GGKSRQDATE);
		cal.add(Calendar.DATE,3);
		
		Date GGJZRQDATE=cal.getTime();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 
		String strDate=df.format(GGJZRQDATE);
		
		JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("GGJZRQDATE", strDate);
		array.add(jsonObj);
		
        j.setMsg(array.toString());
        return j;
    }


}
