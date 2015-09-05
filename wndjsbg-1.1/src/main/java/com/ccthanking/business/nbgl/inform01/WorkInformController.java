/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件： 
 * 创建日期： 2015-07-07 下午 12:00:14
 * 功能：   内部通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-07-07 下午 12:00:14  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.nbgl.inform01;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.nbgl.inform01.service.WorkInformService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> WorkInformController.java </p>
 * <p> 功能:内部通知管理 </p>
 *
 * <p><a href="WorkInformController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-07-07
 * 
 */



@Controller
@RequestMapping("/nbgl/inform01/WorkInformController")
public class WorkInformController{

	private static Logger logger = LoggerFactory.getLogger(WorkInformController.class);

    @Autowired
    private WorkInformService workInformService;
    
   
    /**
     * 查询有效的通知(页面初始化)
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryValid")
    @ResponseBody
    public requestJson queryData(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【内部通知管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.workInformService.queryCondition(json.getMsg());//String
        j.setMsg(domresult);
        return j;
    }
   
   
    /**
     * 查询 已失效的 通知**
     * 
     * 
     */
    @RequestMapping(params = "queryOutOfDate")
    @ResponseBody
    public requestJson queryOutOfData(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【内部通知管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.workInformService.queryOutOfDate(json.getMsg());//String
        j.setMsg(domresult);
        return j;
    }
    
   
    /**
     * 保存数据json(按钮)
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【内部通知管理新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.workInformService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 查看页面 初始化 数据 (有效通知)
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryValidData")
    @ResponseBody
    public requestJson queryData1(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【内部通知管理查看通知查询】",user.getName());
        String selectId = request.getParameter("NEIBU_TONGZHI_UID");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.workInformService.queryById(selectId);//String
        j.setMsg(domresult);
        return j;
    }
    /**
     * 查看页面删除(有效通知)
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "delete")
    @ResponseBody
    public requestJson delete(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【内部通知管理查看通知删除】",user.getName());
        String selectId = request.getParameter("NEIBU_TONGZHI_UID");
        requestJson j = new requestJson();
        boolean flag = false;
          flag = this.workInformService.delteById(selectId);//String
        j.setSuccess(flag);
        return j;
    }


	/**
	 * 查看通知页面(保存)  更新
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "update")
	@ResponseBody
	protected requestJson update(final HttpServletRequest request, requestJson json) throws Exception {
	    User user = RestContext.getCurrentUser();
	    logger.info("<{}>执行操作【内部通知管理更新】",user.getName());
	    requestJson j = new requestJson();
	    String resultVO = "";
	    resultVO = this.workInformService.update(json.getMsg());
	    j.setMsg(resultVO);
	    return j;
	}
	}


