/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywCbfaspGcjsxmzjfbhtbabController.java
 * 创建日期： 2014-11-18 下午 05:55:20
 * 功能：    服务控制类：房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件
 * 所含类:   BuSpywCbfaspGcjsxmzjfbhtbabService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 05:55:20  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.service.BuSpywCbfaspGcjsxmzjfbhtbabService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywCbfaspGcjsxmzjfbhtbabController.java </p>
 * <p> 功能：房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件 </p>
 *
 * <p><a href="BuSpywCbfaspGcjsxmzjfbhtbabController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

@Controller
@RequestMapping("/sp/buSpywCbfaspGcjsxmzjfbhtbabController")
public class BuSpywCbfaspGcjsxmzjfbhtbabController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspGcjsxmzjfbhtbabController.class);

    @Autowired
    private BuSpywCbfaspGcjsxmzjfbhtbabService buSpywCbfaspGcjsxmzjfbhtbabService;

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
        logger.info("<{}>执行操作【房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywCbfaspGcjsxmzjfbhtbabService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspGcjsxmzjfbhtbabService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspGcjsxmzjfbhtbabService.update(json.getMsg());
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
        logger.info("<{}>执行操作【房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspGcjsxmzjfbhtbabService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 业务材料核发 施工
     */
    @RequestMapping(value = "ywlzclhf")
    @ResponseBody
    protected requestJson ywlzclhf(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【材料核发】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspGcjsxmzjfbhtbabService.ywlzclhf(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
}
