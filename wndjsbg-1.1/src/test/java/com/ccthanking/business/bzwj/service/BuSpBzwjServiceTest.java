/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpBzwjServiceTest.java
 * 创建日期： 2014-10-28 上午 11:02:10
 * 功能：    测试类：步骤文件
 * 所含类:   BuSpBzwjService.java
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 上午 11:02:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service;

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

import com.ccthanking.business.spxx.vo.BuSpBzwjVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.test.JsonBinder;
import com.ccthanking.framework.test.ServiceTest;
import com.ccthanking.framework.util.DateTimeUtil;
import com.ccthanking.framework.util.RandomGUID;
/**
 * <p> BuSpBzwjAction.java </p>
 * <p> 功能：步骤文件, 测试类. </p>
 *
 * <p><a href="BuSpBzwjManagerTest.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */


public class BuSpBzwjServiceTest extends ServiceTest{

	private static Logger logger = LoggerFactory.getLogger(BuSpBzwjServiceTest.class);
	
	@Autowired
    @Qualifier("buSpBzwjServiceImpl")
    private BuSpBzwjService service;
    
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
    //	BuSpBzwjVO tmp = service.findById("");
    //    logger.info("{}",tmp);

    }
    
    @Test
    public void testCRUD() {

        HashMap response = new HashMap();
        HashMap data = new HashMap();
        response.put("response", data);

        // 主键
        String id = new RandomGUID().toString();

        BuSpBzwjVO tmp = new BuSpBzwjVO();
        tmp.setBzwj_uid(id);
        //tmp.setInternal("ZFRQ", DateTimeUtil.getDate());
      //  tmp.set("test date "+DateTimeUtil.getDateTime());

        List<BuSpBzwjVO> list = new ArrayList<BuSpBzwjVO>();
        list.add(tmp);

        data.put("data", list);

        String json = JsonBinder.buildNonDefaultBinder().toJson(response);

        try {
      //      String inJson = service.insert(json);

//            BuSpBzwjVO inVO = new BuSpBzwjVO();
       //     JSONArray jlist = tmp.doInitJson(inJson);
     //       tmp.setValueFromJson((JSONObject) jlist.get(0));

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
