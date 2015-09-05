package com.ccthanking.business.weixin.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.framework.dao.BsBaseDaoable;
import com.ccthanking.weixin.vo.WxContentVO;

public interface WxContentDao  extends BsBaseDaoable{
	public String queryCondition(String json, WxContentVO vo ) ;
	
	public  List<Map<String, String>> listCondition(WxContentVO vo,String eventKey,int rownum);
	
	
	public  List<Map<String, String>> listContent(WxContentVO vo,String channelid,int rownum);

}
