/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywSgqyyjhcsxQyyjhchzMxController.java
 * 创建日期： 2014-11-12 上午 11:32:59
 * 功能：    服务控制类：施工企业业绩核查汇总表_明细
 * 所含类:   BuSpywSgqyyjhcsxQyyjhchzMxService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-12 上午 11:32:59  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.service.BuSpywSgqyyjhcsxQyyjhchzMxService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywSgqyyjhcsxQyyjhchzMxController.java </p>
 * <p> 功能：施工企业业绩核查汇总表_明细 </p>
 *
 * <p><a href="BuSpywSgqyyjhcsxQyyjhchzMxController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-12
 * 
 */

@Controller
@RequestMapping("/sp/buSpywSgqyyjhcsxQyyjhchzMxController")
public class BuSpywSgqyyjhcsxQyyjhchzMxController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSgqyyjhcsxQyyjhchzMxController.class);

    @Autowired
    private BuSpywSgqyyjhcsxQyyjhchzMxService buSpywSgqyyjhcsxQyyjhchzMxService;

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
        logger.info("<{}>执行操作【施工企业业绩核查汇总表_明细查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywSgqyyjhcsxQyyjhchzMxService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【施工企业业绩核查汇总表_明细新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSgqyyjhcsxQyyjhchzMxService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【施工企业业绩核查汇总表_明细修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSgqyyjhcsxQyyjhchzMxService.update(json.getMsg());
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
    @RequestMapping(value = "queryUpdate")
    @ResponseBody
    protected requestJson queryUpdate(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【无锡新区新型墙体材料专项基金返退申请_材料修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String id=request.getParameter("id");
        resultVO = this.buSpywSgqyyjhcsxQyyjhchzMxService.queryUpdate(id);
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
        logger.info("<{}>执行操作【施工企业业绩核查汇总表_明细删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywSgqyyjhcsxQyyjhchzMxService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
