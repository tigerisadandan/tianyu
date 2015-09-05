/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_spfjfsyjgysxmqkController.java
 * 创建日期： 2014-11-06 下午 03:53:16
 * 功能：    服务控制类：商品房交付使用竣工验收项目情况表
 * 所含类:   bu_spyw_spfjfsyjgysxmqkService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:53:16  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sp.service.bu_spyw_spfjfsyjgysxmqkService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> bu_spyw_spfjfsyjgysxmqkController.java </p>
 * <p> 功能：商品房交付使用竣工验收项目情况表 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysxmqkController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Controller
@RequestMapping("/sp/bu_spyw_spfjfsyjgysxmqkController")
public class bu_spyw_spfjfsyjgysxmqkController {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysxmqkController.class);

    @Autowired
    private bu_spyw_spfjfsyjgysxmqkService buSpywSpfjfsyjgysxmqkService;

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
        logger.info("<{}>执行操作【商品房交付使用竣工验收项目情况表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywSpfjfsyjgysxmqkService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【商品房交付使用竣工验收项目情况表新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSpfjfsyjgysxmqkService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     **
     * 下载json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public requestJson download(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【商品房交付使用竣工验收项目情况表-下载】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        String tid=request.getParameter("tid");
        domresult = this.buSpywSpfjfsyjgysxmqkService.download(id,tid);
        
        j.setMsg(domresult);
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
        logger.info("<{}>执行操作【商品房交付使用竣工验收项目情况表修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSpfjfsyjgysxmqkService.update(json.getMsg());
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
        logger.info("<{}>执行操作【商品房交付使用竣工验收项目情况表删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSpfjfsyjgysxmqkService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
