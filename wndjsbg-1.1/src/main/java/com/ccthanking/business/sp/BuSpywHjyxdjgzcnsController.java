/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywHjyxdjgzcnsController.java
 * 创建日期： 2014-06-13 下午 05:31:52
 * 功能：    服务控制类：环境影响登记告知承诺
 * 所含类:   BuSpywHjyxdjgzcnsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 05:31:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.common.vo.AtFileuploadVO;
import com.ccthanking.business.sp.service.BuSpywHjyxdjgzcnsService;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuSpywHjyxdjgzcnsController.java </p>
 * <p> 功能：环境影响登记告知承诺 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Controller
@RequestMapping("/sp/buSpywHjyxdjgzcnsController/")
public class BuSpywHjyxdjgzcnsController {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsController.class);

    @Autowired
    private BuSpywHjyxdjgzcnsService buSpywHjyxdjgzcnsService;

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
        logger.info("<{}>执行操作【环境影响登记告知承诺查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buSpywHjyxdjgzcnsService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    /**
     * 查询附件
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryReadFile")
    @ResponseBody
    public requestJson queryReadFile(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工许可申请表-明细查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        domresult = this.buSpywHjyxdjgzcnsService.queryReadFile(id);
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
        logger.info("<{}>执行操作【施工许可申请表-下载】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id=request.getParameter("id");
        String src=request.getParameter("img");
       
        domresult = this.buSpywHjyxdjgzcnsService.download(id);
        
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
        logger.info("<{}>执行操作【环境影响登记告知承诺新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        resultVO = this.buSpywHjyxdjgzcnsService.insert(json.getMsg(),fils);
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
        logger.info("<{}>执行操作【环境影响登记告知承诺修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        Map<String, AtFileuploadVO> fils = null;
        if(request.getSession().getAttribute(Constants.FILE_KEY)!=null){
        	fils = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
        }
        resultVO = this.buSpywHjyxdjgzcnsService.update(json.getMsg(),fils);
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
        logger.info("<{}>执行操作【环境影响登记告知承诺删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buSpywHjyxdjgzcnsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
