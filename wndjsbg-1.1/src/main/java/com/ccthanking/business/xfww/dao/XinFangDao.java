package com.ccthanking.business.xfww.dao;

import java.util.Map;

import com.ccthanking.business.gdzxt.xfww.vo.XinfangVO;
import com.ccthanking.framework.dao.BsBaseDaoable;

public interface XinFangDao extends BsBaseDaoable{

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, XinfangVO vo, Map map);
	
	public boolean delete(String XINFANG_UID);
	
	public String getById(String XINFANG_UID);
}
