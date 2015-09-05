/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgsx.service.BuSpywJsxmkqcthbaService.java
 * 创建日期： 2015-04-01 下午 03:44:28
 * 功能：    接口：sg_《无锡新区建设工程人脸识别考勤常态化备案表》
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-01 下午 03:44:28  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgsx.service;



import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsxmkqcthbaVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywJsxmkqcthbaService.java </p>
 * <p> 功能：sg_《无锡新区建设工程人脸识别考勤常态化备案表》 </p>
 *
 * <p><a href="BuSpywJsxmkqcthbaService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-01
 * 
 */
public interface BuSpywJsxmkqcthbaService extends IBaseService<BuSpywJsxmkqcthbaVO,String > {

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
    
    String toword(HttpServletResponse response,String json) throws Exception;
    
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
