/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyDzgcGcController.java
 * 创建日期： 2015-05-19 下午 02:16:34
 * 功能：    服务控制类：危险源吊装工程过程管理
 * 所含类:   WxyDzgcGcService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-19 下午 02:16:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.wxy.service.WxyDzgcGcService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> WxyDzgcGcController.java </p>
 * <p> 功能：危险源吊装工程过程管理 </p>
 *
 * <p><a href="WxyDzgcGcController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-19
 * 
 */

@Controller
@RequestMapping("/wxy/wxyDzgcGcController")
public class WxyDzgcGcController {

	private static Logger logger = LoggerFactory.getLogger(WxyDzgcGcController.class);

    @Autowired
    private WxyDzgcGcService wxyDzgcGcService;

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
        logger.info("<{}>执行操作【危险源吊装工程过程管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.wxyDzgcGcService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【危险源吊装工程过程管理新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wxyDzgcGcService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【危险源吊装工程过程管理修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wxyDzgcGcService.update(json.getMsg());
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
        logger.info("<{}>执行操作【危险源吊装工程过程管理删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.wxyDzgcGcService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
