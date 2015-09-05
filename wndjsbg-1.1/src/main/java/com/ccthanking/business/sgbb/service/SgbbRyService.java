/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.SgbbRyService.java
 * 创建日期： 2014-04-24 下午 07:04:31
 * 功能：    接口：施工报备人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-24 下午 07:04:31  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.service;

import com.ccthanking.business.sgbb.vo.SgbbRyVO;
import com.ccthanking.framework.service.IBaseService;

/**
 * <p>
 * SgbbRyService.java
 * </p>
 * <p>
 * 功能：施工报备人员
 * </p>
 * 
 * <p>
 * <a href="SgbbRyService.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-24
 * 
 */
public interface SgbbRyService extends IBaseService<SgbbRyVO, String> {

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
	 * 查找报备人员
	 * 
	 * @param bb_uid
	 * @return
	 * @throws Exception
	 */
	String queryBbry(String bb_uid) throws Exception;

	/**
	 * 查询人员的锁定状态
	 * 
	 * @param sg_person_uid
	 * @param wangwei_uid
	 * @param sgbb_uid
	 * @return
	 * @throws Exception
	 */
	String queryLockUser(String sg_person_uid, String gangwei_uid, String sgbb_uid) throws Exception;

	void syncUpdateRyxx(String uid) throws Exception;

}
