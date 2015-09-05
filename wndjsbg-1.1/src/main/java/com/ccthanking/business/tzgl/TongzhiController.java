/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    tzgl.service.TongzhiController.java
 * 创建日期： 2015-05-15 下午 03:34:18
 * 功能：    服务控制类：通知管理
 * 所含类:   TongzhiService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-15 下午 03:34:18  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.tzgl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.tzgl.service.TongzhiService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> TongzhiController.java </p>
 * <p> 功能：通知管理 </p>
 *
 * <p><a href="TongzhiController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-15
 * 
 */

@Controller
@RequestMapping("/tzgl/tongzhiController")
public class TongzhiController {

	private static Logger logger = LoggerFactory.getLogger(TongzhiController.class);

    @Autowired
    private TongzhiService tongzhiService;

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
        logger.info("<{}>执行操作【通知管理查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.tongzhiService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【通知管理新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        int gongchengnum = Integer.parseInt(request.getParameter("gongchengnum")); //选择工程数量
        String tzjibie = request.getParameter("ZG_CODE");  //通知级别 YB,ZY
        String AJ_Y = request.getParameter("AJ_Y"); //是否发送给安监人员 
        String tongzhiTitle = request.getParameter("TONGZHI_TITLE"); //通知标题
        String tongzhiContent = request.getParameter("TONGZHI_CONTENT"); //通知内容
        
        Map<String, String> tongzhiMap = new HashMap<String, String>();
        tongzhiMap.put("tzjibie", tzjibie);
        tongzhiMap.put("AJ_Y", AJ_Y);
        tongzhiMap.put("tongzhiTitle", tongzhiTitle);
        tongzhiMap.put("tongzhiContent", tongzhiContent);
        
        List<Map<String, String>> gongchengList = new ArrayList<Map<String,String>>();
        for (int i = 1; i <= gongchengnum; i++) {
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("gcuid", request.getParameter("gcuid_"+i));
        	map.put("zongjian", request.getParameter("zongjian_"+i));
        	map.put("zjphone", request.getParameter("zjphone_"+i));
        	map.put("xmjl", request.getParameter("xmjl_"+i));
        	map.put("xmljphone", request.getParameter("xmljphone_"+i));
        	gongchengList.add(map);
		}
        
        resultVO = this.tongzhiService.insert(tongzhiMap,gongchengList);
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
        logger.info("<{}>执行操作【通知管理修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.tongzhiService.update(json.getMsg());
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
        logger.info("<{}>执行操作【通知管理删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.tongzhiService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
