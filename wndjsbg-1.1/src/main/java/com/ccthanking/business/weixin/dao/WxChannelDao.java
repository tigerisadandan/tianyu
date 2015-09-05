package com.ccthanking.business.weixin.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.framework.dao.BsBaseDaoable;
import com.ccthanking.weixin.vo.WxChannelVO;

public interface WxChannelDao  extends BsBaseDaoable{
	public String queryCondition(String json, WxChannelVO vo ) ;
	
	String queryRightCondition(String json, WxChannelVO vo) throws Exception; 
	
	public String getChannelIdByEventKey(String eventkey);
	
	public  List<Map<String, String>> listCondition(WxChannelVO vo,String eventKey,int rownum);
	
	public String loadAllAdmUsers(Map maptemp);
	
	public String loadAllRecUsers(Map maptemp);
	
	public void deleteAllUsers(String channelid);
}
