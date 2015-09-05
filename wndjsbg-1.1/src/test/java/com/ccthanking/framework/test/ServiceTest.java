/*
 * ServiceTest.java  v1.00  2013-8-27
 * Peoject	xmg
 * Copyright (c) 2013 KcpmIT
 *
 * Filename	:	ServiceTest.java  v1.00 2013-8-27
 * Project	: 	xmg
 * Copyight	:	Copyright (c) 2013 KcpmIT
 */
package com.ccthanking.framework.test;

import org.apache.commons.lang.StringUtils;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;

/**
 * base service Test class.
 * 
 * @author <a href="mailto:jianggl88@gmail.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2013-8-27
 * 
 */
@ContextConfiguration(locations = { "classpath:application_context.xml", "classpath:application_viewresolver.xml" })
// public class ServiceTest extends AbstractJUnit4SpringContextTests {
public class ServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static User user;

	@BeforeClass
	public static void setUp1() {

		String path = ServiceTest.class.getClassLoader().getResource(".").getPath();
		int index = path.indexOf("WEB-INF");
		if (index != -1) {
			// class下请取
			path = StringUtils.substringBefore(path, "WEB-INF").substring(1);
		} else {
			path = StringUtils.substringBefore(path, "target").substring(1) + "\\src\\main\\webapp";
		}

		// System.setProperty("webApp.root",
		// "E:\\WorkDir\\Project\\kcit\\pcci\\xmgl\\WebContent");	
		System.setProperty(Constants.getString(Constants.webAppRootKey, "wndjsbg.root"), path);	
	}

}
