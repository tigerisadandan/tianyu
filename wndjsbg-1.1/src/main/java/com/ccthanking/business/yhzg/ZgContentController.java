/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgContentController.java
 * 创建日期： 2015-04-21 下午 01:24:35
 * 功能：    服务控制类：需整改的安全隐患
 * 所含类:   ZgContentService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:24:35  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.yhzg.service.ZgContentService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> ZgContentController.java </p>
 * <p> 功能：需整改的安全隐患 </p>
 *
 * <p><a href="ZgContentController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Controller
@RequestMapping("/yhzg/zgContentController")
public class ZgContentController {

	private static Logger logger = LoggerFactory.getLogger(ZgContentController.class);

    @Autowired
    private ZgContentService zgContentService;

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
        logger.info("<{}>执行操作【需整改的安全隐患查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.zgContentService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryByTzdUid")
    @ResponseBody
    public requestJson queryByTzdUid(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【需整改的安全隐患查询】",user.getName());
    	String tzdUid = request.getParameter("tzdUid");
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgContentService.queryByTzdUid(tzdUid);
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
    @RequestMapping(params = "queryScore")
    @ResponseBody
    public requestJson queryScore(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【需整改的安全隐患查询】",user.getName());
    	String tzdUid = request.getParameter("tzdUid");
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgContentService.queryScore(tzdUid);
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
    @RequestMapping(params = "getContent")
    @ResponseBody
    public requestJson getContent(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【需整改的安全隐患查询】",user.getName());
    	String tzdUid = request.getParameter("tzdUid");
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgContentService.getContent(tzdUid);
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
    @RequestMapping(params = "getPicNum")
    @ResponseBody
    public requestJson getPicNum(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【需整改的安全隐患查询】",user.getName());
    	String tzdUid = request.getParameter("tzdUid");
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.zgContentService.getPicNum(tzdUid);
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
       
        logger.info("<{}>执行操作【需整改的安全隐患新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgContentService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【需整改的安全隐患修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgContentService.update(json.getMsg());
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
        logger.info("<{}>执行操作【需整改的安全隐患删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.zgContentService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
