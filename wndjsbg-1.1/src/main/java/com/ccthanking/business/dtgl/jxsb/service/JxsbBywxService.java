/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbBywxService.java
 * 创建日期： 2014-12-24 下午 05:10:41
 * 功能：    接口：机械设备保养维修记录表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-24 下午 05:10:41  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service;


import javax.servlet.http.HttpServletRequest;

import com.ccthanking.business.dtgl.azqy.vo.JxsbBywxVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JxsbBywxService.java </p>
 * <p> 功能：机械设备保养维修记录表 </p>
 *
 * <p><a href="JxsbBywxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-24
 * 
 */
public interface JxsbBywxService extends IBaseService<JxsbBywxVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,HttpServletRequest request) throws Exception;
    
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

}
