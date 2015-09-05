package com.ccthanking.business.yhzg;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.yhzg.service.ZgXmjdService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;

@Controller
@RequestMapping("/yhzg/zgXmjdController")
public class ZgXmjdController {
	
	private static Logger logger = LoggerFactory.getLogger(ZgXmjdController.class);
	@Autowired
	private ZgXmjdService zgXmjdService;
	
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
        logger.info("<{}>执行操作【查询项目进度】",user.getName());
        String name = request.getParameter("name");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.zgXmjdService.queryCondition(name);
        j.setMsg(domresult);
        return j;

    }
}
