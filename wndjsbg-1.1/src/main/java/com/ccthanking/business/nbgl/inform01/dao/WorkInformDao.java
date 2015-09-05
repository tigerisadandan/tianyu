package com.ccthanking.business.nbgl.inform01.dao;

import com.ccthanking.framework.dao.BsBaseDaoable;

public interface WorkInformDao extends BsBaseDaoable{

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json);
	
	public String queryOutOfDate(String json);
    //更新 保存 按钮(查看通知页面)
	//public String updateById(String nbId);

	public String queryById(String selectId);

	public boolean deleteById(String selectId);
	
}
