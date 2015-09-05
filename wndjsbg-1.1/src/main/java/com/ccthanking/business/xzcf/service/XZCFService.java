package com.ccthanking.business.xzcf.service;


import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.xzcf.vo.XzcfVO;
import com.ccthanking.framework.service.IBaseService;

public interface XZCFService extends IBaseService<XzcfVO,String>{

	//查询数据
	 String queryCondition(String json) throws Exception;
	 
	 //添加数据
	 String insert(String json) throws Exception;
     //信访 笔录 添加
	 String insertXwbl(String json)throws Exception;
	 
	//查询 对象的 类型 及信息
	 String queryDXTYPE(String gcuid, String jsuid) throws Exception;
	 
	//查询 调查人员信息
	 String queryReportxx() throws Exception;
	 
	 String queryXzcfMsg(String gcuid) throws Exception;
	//修改 页面 
	 String queryXzcfXX(String xzcfuid) throws Exception;
	 
	 //更新数据
	 String update(String msg, String xzcfuid)throws Exception;
	  
	 
	 
	 /**
	 * 
	 * @param response
	 * @param bbid
	 * @return
	 */
	String toword(HttpServletResponse response, String xzcfuid) throws Exception;
	 //删除数据
	 //boolean delete(String json) throws Exception;

	

	



	

	

	
	
}
