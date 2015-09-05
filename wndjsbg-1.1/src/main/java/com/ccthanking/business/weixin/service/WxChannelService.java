package com.ccthanking.business.weixin.service;

import java.util.Map;

import com.ccthanking.framework.service.IBaseService;
import com.ccthanking.weixin.vo.WxChannelVO;

public interface WxChannelService  extends IBaseService<WxChannelVO, String> {


	public String getChannelIdByEventKey(String eventkey) throws Exception;
	/**
     * 根据条件查询记录.
     */
    String queryCondition(String json) throws Exception;
    
    
    String queryRightCondition(String json) throws Exception;   
    
    /**
     * 新增记录.
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     */
    String update(String json) throws Exception;

    /**
     * 删除记录.
     */
    String delete(String channel_uid) throws Exception;
    
    
  
    
    public String loadAllAdmUsers(Map maptemp);
    
    public String loadAllRecUsers(Map maptemp);
}
