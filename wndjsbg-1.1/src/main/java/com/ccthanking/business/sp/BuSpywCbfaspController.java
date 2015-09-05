/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCbfaspController.java
 * 创建日期： 2014-05-28 上午 10:34:34
 * 功能：    服务控制类：资质
 * 所含类:   BuSpywCbfaspService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 上午 10:34:34  蒋根亮   创建文件，实现基本功能
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.business.sp.service.BuSpywCbfaspService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywCbfaspController.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywCbfaspController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

@Controller
@RequestMapping("/sp/buSpywCbfaspController/")
public class BuSpywCbfaspController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspController.class);

    @Autowired
    private BuSpywCbfaspService buSpywCbfaspService;

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
        logger.info("<{}>执行操作【项目直接发包合同备案表查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywCbfaspService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }

    /**
     **
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public requestJson download(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表-下载】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        String ty=request.getParameter("ty");
        String ywlz=request.getParameter("ywlz");
        domresult = this.buSpywCbfaspService.download(id,ywlz,ty);
        
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
        logger.info("<{}>执行操作【资质新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 保存数据该流程的核发数据
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insertHF")
    @ResponseBody
    protected requestJson insertHF(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspService.insertHF(json.getMsg());
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
        logger.info("<{}>执行操作【建设项目直接发包合同备案表修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspService.update(json.getMsg());
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
        logger.info("<{}>执行操作【资质删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(value = "findByPerson")
    @ResponseBody
    public String findByPerson(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质删除】",user.getName());
        String resultVO = "";
        String lx=request.getParameter("lx");
        String uid=request.getParameter("uid");
        String bb_code=request.getParameter("code");
        resultVO = this.buSpywCbfaspService.findByPerson(lx,uid,bb_code);
        return resultVO;
    }
    @RequestMapping(value = "findByCompany")
    @ResponseBody
    public String findByCompany(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质删除】",user.getName());
        String resultVO = "";
        String lx=request.getParameter("lx");
        String bb_code=request.getParameter("code");
        resultVO = this.buSpywCbfaspService.findByCompany(lx,bb_code);
        return resultVO;
    }
    @RequestMapping(value = "getNum")
    @ResponseBody
    public String getNum(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【资质删除】",user.getName());
        String resultVO = "";
        String lx=request.getParameter("lx");
        resultVO = this.buSpywCbfaspService.getNum(lx);
        return resultVO;
    }
  
    /**
     * 业务材料核发
     */
    @RequestMapping(value = "ywlzclhf")
    @ResponseBody
    protected requestJson ywlzclhf(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【材料核发】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywCbfaspService.ywlzclhf(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
  
 
}
