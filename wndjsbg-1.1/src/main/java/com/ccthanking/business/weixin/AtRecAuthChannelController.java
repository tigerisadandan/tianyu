/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.AtRecAuthChannelController.java
 * 创建日期： 2014-11-27 上午 10:19:22
 * 功能：    服务控制类：栏目受众权限
 * 所含类:   AtRecAuthChannelService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-27 上午 10:19:22  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.weixin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.weixin.service.AtRecAuthChannelService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> AtRecAuthChannelController.java </p>
 * <p> 功能：栏目受众权限 </p>
 *
 * <p><a href="AtRecAuthChannelController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-27
 * 
 */

@Controller
@RequestMapping("/wndjsbg/weixin/atRecAuthChannelController")
public class AtRecAuthChannelController {

	private static Logger logger = LoggerFactory.getLogger(AtRecAuthChannelController.class);

    @Autowired
    private AtRecAuthChannelService atRecAuthChannelService;

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
        logger.info("<{}>执行操作【栏目受众权限查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.atRecAuthChannelService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【栏目受众权限新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.atRecAuthChannelService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【栏目受众权限修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.atRecAuthChannelService.update(json.getMsg());
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
        logger.info("<{}>执行操作【栏目受众权限删除】",user.getName());
        String rec_auth_channel_uid=request.getParameter("rec_auth_channel_uid");
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.atRecAuthChannelService.delete(json.getMsg(),rec_auth_channel_uid);
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 保存接受用户和栏目关联关系数据
     * */
	@RequestMapping(params = "awardToUsers")
	@ResponseBody
	protected requestJson awardToUsers(HttpServletRequest request,requestJson json) throws Exception {
		User user = (User)  RestContext.getCurrentUser();;
		String channelid = request.getParameter("channelid");
		String val = request.getParameter("val");
		
		String[] userids = "".equals(val) ? new String[0] : val.split(",");
		atRecAuthChannelService.awardToUsers(channelid, userids, user);
		requestJson j = new requestJson();
		j.setMsg("");
		return j;
	}
	

}
