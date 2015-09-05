/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    dtgl.service.JxsbCxgzController.java
 * 创建日期： 2015-01-26 下午 04:31:09
 * 功能：    服务控制类：机械设备拆卸告知
 * 所含类:   JxsbCxgzService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 04:31:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.service.XyFzService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.message.comet.MsgUtils;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.SystemException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p> XyFzController.java </p>
 * <p> 功能：信用分值管理 </p>
 *
 * <p><a href="XyFzController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-08-04
 * 
 */

@Controller
@RequestMapping("/dtgl/xyfzController")
public class XyFzController {

	private static Logger logger = LoggerFactory.getLogger(XyFzController.class);

    @Autowired
    private XyFzService xyFzService;

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
        logger.info("<{}>执行操作【查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String type = request.getParameter("type");
        if(type != null || "".equals(type)){
        		String qiyeType = request.getParameter("qiyeType");
        		domresult = this.xyFzService.queryCondition(json.getMsg(),qiyeType);
        	
        }
        
        j.setMsg(domresult);
        return j;
    }
    /**
     * 更新积分
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "updateScore")
    @ResponseBody
    public requestJson updateScore(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【更新】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String id = request.getParameter("id");
        String score = request.getParameter("score");
		String qiyeType = request.getParameter("qiyeType");
		boolean result = this.xyFzService.updateScore(qiyeType, id, score);
        j.setSuccess(result);
        return j;
    }
}
