/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.sgbb.SgBbDao.java
 * 创建日期： 2014-04-21 下午 05:57:51
 * 功能：   施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-21 下午 05:57:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.sgbb.vo.SgbbVO;
import com.ccthanking.framework.dao.BsBaseDaoable;

/**
 * <p>
 * SgBbDao.java
 * </p>
 * <p>
 * 功能接口：施工报备
 * </p>
 * 
 * <p>
 * <a href="SgBbDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-21
 * 
 */

public interface SgBbDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SgbbVO vo, Map map);

	/**
	 * 根据报备编码查找人员报备信息.<br/>
	 * 
	 * @param bbid
	 * @return 人员报备信息集合
	 */
	public Connection query4Print(String bbid);
	/**
	 * 根据项目编号查询项目信息 .<br/>
	 * @author 余健
	 * @param xmbh
	 * @return 项目信息
	 */
	public List findHeaderPrint(String xmbh);
	// 可在此加入业务独特的服务接口
	
	/**
	 * 获取新的报备编号
	 */
	public String getNewBaobeiCode();
	
	/**
	 * 查询项目信息.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryZbgg(String json);
	
	/**
	 * 查询所需参数
	 * @param gc_type
	 * @return
	 */
	public String queryParam(String gc_type);
	
	/**
	 * 查询是否有资质
	 * @param vo
	 * @return
	 */
	public String queryZg(SgbbVO vo);
	
	/**
	 * 删除报备
	 * @param sgbb_uid
	 * @return
	 */
	public String delete(String sgbb_uid);
	  /**
     * 向老系统库同步数据
     * @param json
     * @throws Exception
     */
    String insertToOld(String json) throws Exception;
    
    /**
     * 更新施工报备到已中标或未中标状态
     * @param sgbb_uid
     * @param status
     */
    void updateBbzt(String sgbb_uid,String status);
    
    
	public List query4PrintForList(String bbid);
	
	/**
	 * 解锁全部报备人员
	 * @param uid
	 * @return
	 */
	public String unLockBb(String uid);
	
	/**
	 * 解锁单个报备人员
	 * @param uid
	 * @return
	 */
	public String unLockBbry(String uid);
	/**
	 * 锁定单个报备人员
	 * @param uid
	 * @return
	 */
	public String personLock(String uid);
}
