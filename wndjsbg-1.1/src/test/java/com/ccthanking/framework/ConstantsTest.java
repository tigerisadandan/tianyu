/*
 * ConstantsTest.java  v1.00  2013-3-15
 * Peoject	copj-test
 * Copyright (c) 2013 Wisdragon
 *
 * Filename	:	ConstantsTest.java  v1.00 2013-3-15
 * Project	: 	copj-test
 * Copyight	:	Copyright (c) 2013 Wisdragon
 */
package com.ccthanking.framework;

import net.sf.json.JSONObject;

/**
 * test class.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2013-3-15
 * 
 */
public class ConstantsTest {

    public static void main(String[] args) {
    	JSONObject object = new JSONObject();
    	
    	object.put("XM", "张三");
    	
    	String xm=object.getString("XM");
    	String mx=(String)object.get("NL");
    	String mx1=(String)object.get("XM");
    	
    	System.out.println(Constants.webAppRootKey);
        
    	System.out.println(Constants.getString("SCROLLNEWS",""));
    }

}
