/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.SgBbService.java
 * 创建日期： 2014-04-21 下午 05:57:51
 * 功能：    接口：施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-21 下午 05:57:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.service;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import com.ccthanking.business.sgbb.vo.SgbbVO;

import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.IBaseService;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

/**
 * <p>
 * SgBbService.java
 * </p>
 * <p>
 * 功能：施工报备
 * </p>
 * 
 * <p>
 * <a href="SgBbService.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-21
 * 
 */
public interface SgBbService extends IBaseService<SgbbVO, String> {

	/**
	 * 根据条件查询记录.
	 * 
	 * @param json
	 * @param user
	 * @return
	 * @throws Exception
	 * @since v1.00
	 */
	String queryCondition(String json,Map map) throws Exception;

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
	String delete(String sgbb_uid) throws Exception;

	/**
	 * 获取工程所需参数
	 * 
	 * @return
	 * @throws Exception
	 */
	String queryBbtj(String json, Map map) throws Exception;

	/**
	 * 产生JasperPrint 对象.
	 * 
	 * @param bbid
	 * @paramp arpentPath
	 * @param childPath
	 * @return 人员配备表JasperPrint 对象
	 */
	JasperPrint query4Print(String bbid, String parpentPath, String childPath);

	/**
	 * 查询出报表头部信息.
	 * 
	 * @author 余健
	 * @param xmbh
	 * @return
	 */
	public List findHeaderPrint(String xmbh);
	
	String queryZbgg(String json) throws Exception ;
	
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
    void updateBbzt(String sgbb_uid,String status,String code,String name) throws Exception;
    
    /**
     * 解锁施工人员
     * @param sgbb_uid
     * @param status
     * @throws Exception 
     */
    void updateBbToUnlock(String sgbb_uid,String status) throws Exception;
    
    /**
     * 锁定施工人员
     * @param sgbb_uid
     * @param status
     * @throws Exception 
     */
    void personLock(String sgbb_uid) throws Exception;
    
}
