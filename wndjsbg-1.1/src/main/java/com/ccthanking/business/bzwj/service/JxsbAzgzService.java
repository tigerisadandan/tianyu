/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.JxsbAzgzService.java
 * 创建日期： 2015-01-16 下午 12:03:25
 * 功能：    接口：机械设备安装告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-16 下午 12:03:25  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service;

import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.dtgl.azqy.vo.JxsbAzgzVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JxsbAzgzService.java </p>
 * <p> 功能：机械设备安装告知 </p>
 *
 * <p><a href="JxsbAzgzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-16
 * 
 */
public interface JxsbAzgzService extends IBaseService<JxsbAzgzVO, String> {

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
    
    String queryAzgc(String json) throws Exception;
    String queryJcys(String json) throws Exception;
    String queryCxgc(String json) throws Exception;
    
    String querySyglCl(String json) throws Exception;
    
    String queryDslSb(String json,String type) throws Exception;
    
    String toword(HttpServletResponse response,String json) throws Exception;
    
    String towordAzgc(HttpServletResponse response,String json) throws Exception;
    
    String towordJcys(HttpServletResponse response,String json) throws Exception;
    
    String towordCxgc(HttpServletResponse response,String json) throws Exception;
    
    String querySbzl(String json) throws Exception;
    
String queryByView(String json) throws Exception;
    
    String queryAzyr(String json) throws Exception;
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
    String update(String json,String type) throws Exception;
    
    String UpdateSH(String json,String type) throws Exception;

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
