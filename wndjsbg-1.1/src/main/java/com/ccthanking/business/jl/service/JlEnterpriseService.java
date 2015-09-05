package com.ccthanking.business.jl.service;

import java.util.Map;

import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgcVO;
import com.ccthanking.business.dtgl.jl.vo.EnterpriseLibraryVO;
import com.ccthanking.framework.service.IBaseService;

public interface JlEnterpriseService {
    String queryCondition(String json) throws Exception;
    String queryOldCondition(String json) throws Exception;
    String queryspyjCondition(String json) throws Exception;
    
    String insert(String json) throws Exception;
    
    String update(String json,String jg) throws Exception;
    
    String delete(String json) throws Exception;
    
	String tuihui(String ids, String yijian) throws Exception;
	
	void updateDshxx(Map map) throws Exception;
	
	String updateEnterprise(String msg);
	
	String updateCopyByStatus(Map map);
}
