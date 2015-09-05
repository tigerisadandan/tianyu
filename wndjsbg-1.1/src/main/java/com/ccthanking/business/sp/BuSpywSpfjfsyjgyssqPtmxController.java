/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywSpfjfsyjgyssqPtmxController.java
 * 创建日期： 2014-06-09 下午 02:02:22
 * 功能：    服务控制类：商品房交付使用竣工验收申请——公建配套明细
 * 所含类:   BuSpywSpfjfsyjgyssqPtmxService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 02:02:22  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sp.service.BuSpywSpfjfsyjgyssqPtmxService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywSpfjfsyjgyssqPtmxController.java </p>
 * <p> 功能：商品房交付使用竣工验收申请——公建配套明细 </p>
 *
 * <p><a href="BuSpywSpfjfsyjgyssqPtmxController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Controller
@RequestMapping("/sp/spfjfsyjgyssqPtmxController/")
public class BuSpywSpfjfsyjgyssqPtmxController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSpfjfsyjgyssqPtmxController.class);

    @Autowired
    private BuSpywSpfjfsyjgyssqPtmxService buSpywSpfjfsyjgyssqPtmxService;

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
        logger.info("<{}>执行操作【商品房交付使用竣工验收申请——公建配套明细查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywSpfjfsyjgyssqPtmxService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    /**
     * 更改时获取数据
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryUpdate")
    @ResponseBody
    public requestJson queryUpdate(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工许可申请表-明细查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        domresult = this.buSpywSpfjfsyjgyssqPtmxService.queryUpdate(id);
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
        logger.info("<{}>执行操作【商品房交付使用竣工验收申请——公建配套明细新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSpfjfsyjgyssqPtmxService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【商品房交付使用竣工验收申请——公建配套明细修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSpfjfsyjgyssqPtmxService.update(json.getMsg());
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
        logger.info("<{}>执行操作【商品房交付使用竣工验收申请——公建配套明细删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSpfjfsyjgyssqPtmxService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
