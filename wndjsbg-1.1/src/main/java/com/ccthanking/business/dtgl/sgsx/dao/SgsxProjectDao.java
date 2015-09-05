package com.ccthanking.business.dtgl.sgsx.dao;

import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpYwxxVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SgsxProjectDao.java </p>
 * <p> 功能接口：审批业务信息 </p>
 *
 * <p><a href="SgsxProjectDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2014-07-14
 * 
 */

public interface SgsxProjectDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpYwxxVO vo, Map map);
	
	//查询 状态
	public String queryYwStatus(String json,BuSpYwxxVO vo, Map map);
	
	public String querySFszgc(String gcid);
	
	//查询 业务流转 id
	public String querySgYwlzId(String gcId, String spywId);
	
	
	
	
	
	
	
	
	
	
	
	public String queryBz(String json, BuSpYwxxVO vo, Map map);
	
	public String queryYWLX(String json);

	// 可在此加入业务独特的服务接口
	
	 /**
     * 加载审批业务树
     * @return
     * @throws Exception
     */
    String getAllSpyw() throws Exception;

	

	

	
}
