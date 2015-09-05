/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbCxgcController.java
 * 创建日期： 2015-01-28 下午 06:05:57
 * 功能：    服务控制类：机械设备拆卸过程
 * 所含类:   JxsbCxgcService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 06:05:57  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.jxsb.service.JxsbCxgcService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JxsbCxgcController.java </p>
 * <p> 功能：机械设备拆卸过程 </p>
 *
 * <p><a href="JxsbCxgcController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Controller
@RequestMapping("/jxsb/jxsbCxgcController")
public class JxsbCxgcController {

	private static Logger logger = LoggerFactory.getLogger(JxsbCxgcController.class);

    @Autowired
    private JxsbCxgcService jxsbCxgcService;

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
        logger.info("<{}>执行操作【机械设备拆卸过程查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jxsbCxgcService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备拆卸过程新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbCxgcService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【机械设备拆卸过程修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbCxgcService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(value = "updateZhuXiao")
    @ResponseBody
    protected requestJson updateZhuXiao(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【机械设备拆卸过程修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String sbid=request.getParameter("sbid");
        String syglid=request.getParameter("id");
        resultVO = this.jxsbCxgcService.updateZhuXiao(sbid,syglid);
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
        logger.info("<{}>执行操作【机械设备拆卸过程删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jxsbCxgcService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
