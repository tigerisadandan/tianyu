/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgWeiguiSjService.java
 * 创建日期： 2015-04-21 下午 01:21:34
 * 功能：    接口：违规事件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:21:34  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service;



import com.ccthanking.business.dtgl.yhzg.vo.ZgWeiguiSjVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ZgWeiguiSjService.java </p>
 * <p> 功能：违规事件 </p>
 *
 * <p><a href="ZgWeiguiSjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */
public interface ZgWeiguiSjService extends IBaseService<ZgWeiguiSjVO,String > {

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

	String getTree();

	String queryZgsj(String msg);

}
