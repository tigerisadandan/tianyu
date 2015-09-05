package com.ccthanking.business.xfww.service;

import com.ccthanking.business.gdzxt.xfww.vo.XinfangVO;
import com.ccthanking.framework.service.IBaseService;

public interface XinFangService extends IBaseService<XinfangVO,String>{

	//查询数据
	 String queryCondition(String json) throws Exception;
	 
	 //添加数据
	 String insert(String json) throws Exception;

	 //更新数据
	 String update(String json) throws Exception;
	  
	 //删除数据
	 boolean delete(String json) throws Exception;

	 //通过ID获取数据
	 public String getById(String XINFANG_UID) throws Exception;
}
