package com.ccthanking.business.commons;

import java.util.Date;

import com.ccthanking.framework.util.Pub;

public class Utils {
	public static Date formatToDate(String dateStr){
		if (!Pub.empty(dateStr)) {
			String val = dateStr.replaceAll("-", "")
					.replaceAll("_", "").replaceAll(":", "")
					.replaceAll("/", "").replaceAll(" ", "").trim()
					+ "00000000000000";
			return Pub.toDate("yyyyMMddHHmmss", val.substring(0, 14));
		}
		return new Date();
	}
}
