/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.service.WaiChuDengJiServiceTest.java
 * 创建日期： 2015-05-21 下午 03:32:32
 * 功能：    测试类：外出登记
 * 所含类:   WaiChuDengJiService.java
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-21 下午 03:32:32  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.nbgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ccthanking.business.dtgl.nbgl.vo.WaiChuDengJiVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.test.JsonBinder;
import com.ccthanking.framework.test.ServiceTest;
import com.ccthanking.framework.util.DateTimeUtil;
import com.ccthanking.framework.util.RandomGUID;
/**
 * <p> WaiChuDengJiAction.java </p>
 * <p> 功能：外出登记, 测试类. </p>
 *
 * <p><a href="WaiChuDengJiManagerTest.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-21
 * 
 */


public class WaiChuDengJiServiceTest extends ServiceTest{

	private static Logger logger = LoggerFactory.getLogger(WaiChuDengJiServiceTest.class);
	
	@Autowired
    @Qualifier("waiChuDengJiServiceImpl")
    private WaiChuDengJiService service;
    
    @Before
    public void before() {
        try {
            if (user == null) {
                user = (User) UserManager.getInstance().getUserByLoginName("superman");
                ActionContext.setCurrentUserInThread(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFindbyPk() {

        WaiChuDengJiVO tmp = service.findById("11");
        logger.info("{}",tmp);

    }
    
    @Test
    public void testCRUD() {

        HashMap response = new HashMap();
        HashMap data = new HashMap();
        response.put("response", data);

        // 主键
        String id = new RandomGUID().toString();

        WaiChuDengJiVO tmp = new WaiChuDengJiVO();
       // tmp.setId(id);
        //tmp.setInternal("ZFRQ", DateTimeUtil.getDate());
       // tmp.setBz("test date "+DateTimeUtil.getDateTime());

        List<WaiChuDengJiVO> list = new ArrayList<WaiChuDengJiVO>();
        list.add(tmp);

        data.put("data", list);

        String json = JsonBinder.buildNonDefaultBinder().toJson(response);

        try {
            String inJson = service.insert(json);

//            WaiChuDengJiVO inVO = new WaiChuDengJiVO();
            JSONArray jlist = tmp.doInitJson(inJson);
            tmp.setValueFromJson((JSONObject) jlist.get(0));

            // 修改
            tmp.setInternal("LRR", "_test_");

            list.clear();
            list.add(tmp);
            data.put("data", list);
            json = JsonBinder.buildNonDefaultBinder().toJson(response);
            logger.info(json);

            service.update(json);

            //删除
            service.delete(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info(JsonBinder.buildNonDefaultBinder().toJson(response));
    }

}
