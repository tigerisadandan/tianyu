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

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ccthanking.business.spxx.service.BuSpBzService;
import com.ccthanking.business.spxx.vo.BuSpBzVO;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
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


public class freemarkerTest extends ServiceTest{

	private static Logger logger = LoggerFactory.getLogger(freemarkerTest.class);

	@Autowired
	@Qualifier("buSpBzServiceImpl")
	private BuSpBzService buSpBzService;
    
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
    public void testList() {
    	//主表数据
    	List<?>  temp= buSpBzService.getSpBzList("29", null); 
    	
    	if(temp!=null){
    		Map temMap=(Map)temp.get(0);
    		
    		//子表数据
    		List<?>  gdList= buSpBzService.getSpBzList("29", "GD");  		
    		temMap.put("gdList", gdList);
//    		String id;
    		BuSpBzVO vo = buSpBzService.findById("");
    		
    		BuSpBzVO vo1 = buSpBzService.findById("");
    		vo.put("vo1", vo1);
    		
    		
    		//勾项演示数据
//    		String indexdata ="02,03";
//    		dataMap.put("indexdata", indexdata);
    				
    		String templatePath="D:\\MyEclipse8.5\\Workspaces\\wndjs\\workspaces\\wndjsbg\\src\\test\\java\\com\\ccthanking\\business\\person\\ervice";
    		String templateName="十四、排水方案预审_无锡新区城市排水方案预审申请表.ftl";
    		String filePath="d:\\filePath";
    		String fileName="十四、排水方案预审_无锡新区城市排水方案预审申请表.xml";//后缀 .xml .doc 都可以
    		
    		//图片处理方式
    		temMap.put("BMSH_NAME", FreemarkerHelper.getImageStr(templatePath,"cjn1.jpg"));
    		
    		if(FreemarkerHelper.createWord(vo, templatePath, templateName, filePath, fileName)){
    			//生成成功之后调用转成PDF文件
    			Word2PDF.toPdf(filePath+File.separator+fileName);
    		}
    	}
    }
    

}
