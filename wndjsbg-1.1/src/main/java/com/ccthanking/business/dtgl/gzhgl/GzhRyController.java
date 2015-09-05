/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhRyController.java
 * 创建日期： 2015-05-31 下午 04:34:35
 * 功能：    服务控制类：告知会参与人员
 * 所含类:   GzhRyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-31 下午 04:34:35  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.gzhgl.service.GzhRyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> GzhRyController.java </p>
 * <p> 功能：告知会参与人员 </p>
 *
 * <p><a href="GzhRyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-31
 * 
 */

@Controller
@RequestMapping("/gzhgl/gzhRyController")
public class GzhRyController {

	private static Logger logger = LoggerFactory.getLogger(GzhRyController.class);

    @Autowired
    private GzhRyService gzhRyService;

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
        logger.info("<{}>执行操作【告知会参与人员查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.gzhRyService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【告知会参与人员新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhRyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【告知会参与人员修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhRyService.update(json.getMsg());
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
        logger.info("<{}>执行操作【告知会参与人员删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.gzhRyService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 根据查询结果添加.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "insertByQuery")
    @ResponseBody
    public requestJson insertByQuery(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【告知会参与人员删除】",user.getName());
        String pIds1  = request.getParameter("pIds1");
        String personIds = request.getParameter("personIds");
        String type = request.getParameter("type");
        requestJson j = new requestJson();
        String resultVO = "";
        String gzh_uid = (String) request.getSession().getAttribute("gzh_uid");
        resultVO = this.gzhRyService.insertByQuery(gzh_uid,pIds1,personIds,type);
        request.getSession().removeAttribute("gzh_uid");
        j.setMsg(resultVO);
        return j;
    }
    /**
	 * 根据告知会编号，查询对应的项目/单位
	 * @param gzhId
	 * @return
	 */
    @RequestMapping(params = "queryXmDwByGzhId")
    @ResponseBody
    public requestJson queryXmDwByGzhId(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【告知会项目/单位查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.gzhRyService.queryXmDwByGzhId(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
  	 * 根据告知会编号，查询对应签到人员
  	 * @param gzhId
  	 * @return
  	 */
      @RequestMapping(params = "queryQianDaoRYByGzhId")
      @ResponseBody
      public requestJson queryQianDaoRYByGzhId(final HttpServletRequest request, requestJson json) throws Exception {
          User user = RestContext.getCurrentUser();
          logger.info("<{}>执行操作【告知会签到人员查询】",user.getName());
          requestJson j = new requestJson();
          String domresult = "";
          domresult = this.gzhRyService.queryQianDaoRYByGzhId(json.getMsg());
          j.setMsg(domresult);
          return j;
      }
    

}
