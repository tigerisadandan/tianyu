package com.ccthanking.business.dtgl.sgsx.service;

import com.ccthanking.business.spxx.vo.BuSpYwxxVO;
import com.ccthanking.framework.service.IBaseService;

public interface SgsxProjectService extends IBaseService<BuSpYwxxVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json) throws Exception;
    
    String queryYwStatus(String string)throws Exception;
    //是否是 市政工程
    String querySFszgc(String string)throws Exception;
    
    String querySgYwlzId(String gcId, String spywId)throws Exception;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    String queryBz(String json) throws Exception;
    
    String queryYWLX(String json) throws Exception;
    
    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json) throws Exception;

    /**
     * 删除记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json) throws Exception;

    /**
     * 加载审批业务树
     * @return
     * @throws Exception
     */
    String getAllSpyw() throws Exception;

	

	

	
}

