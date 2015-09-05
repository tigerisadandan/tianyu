package com.ccthanking.business.weixin.service;

import com.ccthanking.framework.service.IBaseService;
import com.ccthanking.weixin.vo.WxContentVO;

public interface WxContentService  extends IBaseService<WxContentVO, String> {
	/**
     * 根据条件查询记录.
     */
    String queryCondition(String json) throws Exception;
    
    String getWxContentVO(String json, String contentid) throws Exception;
    
    /**
     * 新增记录.
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     */
    String update(String json) throws Exception;

    /**
     * 删除记录
     */
    String delete(String id) throws Exception;

    
 
    
    public String listContent(WxContentVO vo, String channelid,int rownum) throws Exception;
    
    
    public String listContentS(WxContentVO vo, String channelid,int rownum) throws Exception;

}
