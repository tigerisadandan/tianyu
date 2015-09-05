/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhRyServiceTest.java
 * 创建日期： 2015-05-31 下午 04:34:35
 * 功能：    测试类：告知会参与人员
 * 所含类:   GzhRyService.java
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-31 下午 04:34:35  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl.service;

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

import com.ccthanking.business.dtgl.gzhgl.vo.GzhRyVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.test.JsonBinder;
import com.ccthanking.framework.test.ServiceTest;
import com.ccthanking.framework.util.DateTimeUtil;
import com.ccthanking.framework.util.RandomGUID;
/**
 * <p> GzhRyAction.java </p>
 * <p> 功能：告知会参与人员, 测试类. </p>
 *
 * <p><a href="GzhRyManagerTest.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-31
 * 
 */


public class GzhRyServiceTest extends ServiceTest{

	private static Logger logger = LoggerFactory.getLogger(GzhRyServiceTest.class);
	
	@Autowired
    @Qualifier("gzhRyServiceImpl")
    private GzhRyService service;
    
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

        GzhRyVO tmp = service.findById("11");
        logger.info("{}",tmp);

    }
    
    @Test
    public void testCRUD() {

        HashMap response = new HashMap();
        HashMap data = new HashMap();
        response.put("response", data);

        // 主键
        String id = new RandomGUID().toString();

        GzhRyVO tmp = new GzhRyVO();
        //tmp.setId(id);
        //tmp.setInternal("ZFRQ", DateTimeUtil.getDate());
        //tmp.setBz("test date "+DateTimeUtil.getDateTime());

        List<GzhRyVO> list = new ArrayList<GzhRyVO>();
        list.add(tmp);

        data.put("data", list);

        String json = JsonBinder.buildNonDefaultBinder().toJson(response);

        try {
            String inJson = service.insert(json);

//            GzhRyVO inVO = new GzhRyVO();
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
