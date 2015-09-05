/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jlbb.service.JlbbService.java
 * 创建日期： 2015-01-28 上午 09:10:22
 * 功能：    接口：监理报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:10:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb.service;



import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import com.ccthanking.business.dtgl.jl.vo.JlbbVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JlbbService.java </p>
 * <p> 功能：监理报备 </p>
 *
 * <p><a href="JlbbService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */
public interface JlbbService extends IBaseService<JlbbVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,Map<String,String> map) throws Exception;
    
    String queryBbxx(String json) throws Exception;
    
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
    
    
    
    String queryZbgg(String json) throws Exception;


	String queryPersonList(String msg) throws Exception;
	
	
	void updateBbzt (String jlbb_uid,String status,String code,String name) throws Exception;

	void updateBbToUnlock(String bbid, String optype) throws Exception;
	
	void personLock(String bbuid) throws Exception;

	JasperPrint query4Print(String bbid, String parpentPath, String childPath);

	String toword(HttpServletResponse response, String bbid);

}
