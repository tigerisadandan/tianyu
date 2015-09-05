package com.ccthanking.business.rygl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.rygl.service.XcryglService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;

@Controller
@RequestMapping("/rygl/xcryglController")
public class XcryglController {
	
	private static Logger logger = LoggerFactory.getLogger(XcryglController.class);
	@Autowired
	private XcryglService xcryglService;
	
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
        logger.info("<{}>执行操作【务工信息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.xcryglService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
}
