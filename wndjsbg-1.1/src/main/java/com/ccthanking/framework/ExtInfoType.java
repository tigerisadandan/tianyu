/*
 * ExtInfoType.java  v1.00  2014-10-20
 * Peoject	kcit-base-3.0.1
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	ExtInfoType.java  v1.00 2014-10-20
 * Project	: 	kcit-base-3.0.1
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.framework;

import com.ccthanking.framework.coreapp.orgmanage.UserManager;

/**
 * 扩信息类型.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-10-20
 * 
 */
public enum ExtInfoType {

	USERIDNUM("userid");

	public final String fieldName;

	ExtInfoType(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue(Object value) {

		if (fieldName.equals("userid")) {
			try {
				return UserManager.getInstance().getUserNameByUserID(String.valueOf(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "";
	}
}
