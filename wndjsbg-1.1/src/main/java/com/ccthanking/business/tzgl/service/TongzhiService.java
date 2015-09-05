/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    tzgl.service.TongzhiService.java
 * 创建日期： 2015-05-15 下午 03:34:18
 * 功能：    接口：通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-15 下午 03:34:18  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.tzgl.service;




import java.util.List;
import java.util.Map;

import com.ccthanking.business.tzgl.vo.TongzhiVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> TongzhiService.java </p>
 * <p> 功能：通知管理 </p>
 *
 * <p><a href="TongzhiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-15
 * 
 */
public interface TongzhiService extends IBaseService<TongzhiVO, String> {

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
    String insert(Map<String, String> tongzhiMap,List<Map<String,String>> gongchengList) throws Exception;

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
