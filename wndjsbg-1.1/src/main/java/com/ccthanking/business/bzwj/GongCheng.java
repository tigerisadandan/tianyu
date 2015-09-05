package com.ccthanking.business.bzwj;

import java.util.HashMap;
import java.util.Map;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;

public class GongCheng {
	private static Map<String,String> GcId;//工程id
	
	private static Map<String,String> GcName; //工程名字
	
	private static Map<String,String> SgCompanyName; //施工方单位
	
	public static String getGcid(){
		init();
		return GcId.get(RestContext.getCurrentUser().getIdCard());
	}

	public static String getGcName(){
		init();
		return GcName.get(RestContext.getCurrentUser().getIdCard());
	}
	
	public static String getSgCompanyName(){
		init();
		return SgCompanyName.get(RestContext.getCurrentUser().getIdCard());
	}
	
	private static void init(){
		if (GcId==null) {
			GcId = new HashMap<String,String>();
		}
		if (GcName==null) {
			GcName = new HashMap<String,String>();
		}
		if (SgCompanyName==null) {
			SgCompanyName = new HashMap<String,String>();
		}
	}
	
	public static void addToGcId(String projects_uid){
		init();
		
		User user = RestContext.getCurrentUser();
		if (GcId.get(user.getIdCard())!=null) {
			GcId.remove(user.getIdCard());
		}
		GcId.put(user.getIdCard(), projects_uid);
	}
	
	public static void addToGcName(String projects_uid){
		init();
		
		User user = RestContext.getCurrentUser();
		if (GcName.get(user.getIdCard())!=null) {
			GcName.remove(user.getIdCard());
		}
		GcName.put(user.getIdCard(), projects_uid);
	}
	
	public static void addToSgCompanyName(String projects_uid){
		init();
		
		User user = RestContext.getCurrentUser();
		if (SgCompanyName.get(user.getIdCard())!=null) {
			SgCompanyName.remove(user.getIdCard());
		}
		SgCompanyName.put(user.getIdCard(), projects_uid);
	}
}
