package com.ccthanking.business.nbgl.inform01.service;

import com.ccthanking.business.nbgl.jhgl.vo.NeibuTongzhiVO;
import com.ccthanking.framework.service.IBaseService;
/**
 * <p> WorkInformService.java </p>
 * <p> 功能:内部通知管理 </p>
 *
 * <p><a href="WorkInformService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:lhtarena@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-07-07
 * 
 */
public interface WorkInformService extends IBaseService<NeibuTongzhiVO,String>{
	 
	 //查询失效通知 数据
	 String queryOutOfDate(String json) throws Exception;

	 //查询有效的通知
	 String queryCondition(String json) throws Exception;
	  
	 //保存数据
	public String insert(String json) throws Exception;
    //更新数据
	String update(String msg)throws Exception;

	String queryById(String selectId);

	boolean delteById(String selectId);

	
}
