/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzServiceTest.java
 * 创建日期： 2014-06-23 下午 05:32:10
 * 功能：    测试类：审批业务流转实例
 * 所含类:   BuSpYwlzService.java
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-23 下午 05:32:10  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.person.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.test.ServiceTest;
/**
 * <p> BuSpYwlzAction.java </p>
 * <p> 功能：审批业务流转实例, 测试类. </p>
 *
 * <p><a href="BuSpYwlzManagerTest.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-23
 * 
 */


public class hfTest extends ServiceTest{

	private static Logger logger = LoggerFactory.getLogger(hfTest.class);

	@Autowired
	@Qualifier("buSpLzhfServiceImpl")
	private BuSpLzhfService buSpLzhfService;
    
    @Before
    public void before() {
        try {
            if (user == null) {
                user = (User) UserManager.getInstance().getUserByLoginName("bg_test");
                ActionContext.setCurrentUserInThread(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testList() {
    	Map mapFtl = new HashMap();
    	Map mapVo= new HashMap();
    	
    	mapVo.put("LINGJIAN_PHONE", "13102328647");
    	mapVo.put("LINGJIAN_REN", "领件人");
    	mapVo.put("PIJIAN_CODE", "100101");
    	mapVo.put("PIJIAN_NAME", "核发_01文件");
    	mapVo.put("YWCL_UID", "1");
    	mapVo.put("YWLZ_UID", "1");
    	
//    	mapVo.put("YWLZUID", "");
//    	mapVo.put("XMUID", "");
//    	mapVo.put("COMUID", "");
    	
    	
    	mapFtl.put("DWMC", "上海技术有限公司");
    	mapFtl.put("DWDZ", "上海杨浦区四平路2101号");
    	
    	buSpLzhfService.saveBuSpLzhfVO(mapFtl, mapVo);
    }
    

}
