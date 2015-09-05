/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.SgbbRyController.java
 * 创建日期： 2014-04-24 下午 07:04:31
 * 功能：    服务控制类：施工报备人员
 * 所含类:   SgbbRyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-24 下午 07:04:31  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgbb.service.SgbbRyService;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SgbbRyController.java </p>
 * <p> 功能：施工报备人员 </p>
 *
 * <p><a href="SgbbRyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-24
 * 
 */

@Controller
@RequestMapping("/sgbb/sgbbRyController/")
public class SgbbRyController {

	private static Logger logger = LoggerFactory.getLogger(SgbbRyController.class);

    @Autowired
    private SgbbRyService sgbbRyService;

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
        logger.info("<{}>执行操作【施工报备人员查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sgbbRyService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【施工报备人员新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgbbRyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【施工报备人员修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgbbRyService.update(json.getMsg());
        
        if (Constants.getBoolean("DATA_SYNC_SGBB", false)) {
        	sgbbRyService.syncUpdateRyxx(resultVO);
        }
        
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
        logger.info("<{}>执行操作【施工报备人员删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sgbbRyService.delete(json.getMsg());
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
    @RequestMapping(value = "queryBbry")
    @ResponseBody
    public requestJson queryBbry(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【根据报备编号查询施工报备人员】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String bb_uid = request.getParameter("bb_uid");
        domresult = this.sgbbRyService.queryBbry(bb_uid);
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
    @RequestMapping(value = "queryLockUser")
    @ResponseBody
    public requestJson queryLockUser(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【根据报备编号查询施工报备人员】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String sg_person_uid = request.getParameter("sg_person_uid");
        String gangwei_uid = request.getParameter("gangwei_uid");
        String sgbb_uid = request.getParameter("sgbb_uid");
        domresult = this.sgbbRyService.queryLockUser(sg_person_uid,gangwei_uid,sgbb_uid);
        j.setMsg(domresult);
        return j;

    }
}
