/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgDafuService.java
 * 创建日期： 2015-04-21 下午 01:27:03
 * 功能：    接口：整改答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:27:03  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service;



import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.dtgl.yhzg.vo.ZgDafuVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ZgDafuService.java </p>
 * <p> 功能：整改答复 </p>
 *
 * <p><a href="ZgDafuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */
public interface ZgDafuService extends IBaseService<ZgDafuVO, String> {

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

	String toword(HttpServletResponse response, String tzdfuid);

	String toword2(HttpServletResponse response, String tzduid);

}
