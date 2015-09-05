/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    qyxx.service.YxClEnterpriseGcalxxService.java
 * 创建日期： 2015-01-28 下午 12:09:51
 * 功能：    接口：设备材料企业工程案例信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 12:09:51  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service;

import com.ccthanking.business.wxgc.vo.YxClEnterpriseGcalxxVO;
import com.ccthanking.framework.service.IBaseService;




/**
 * <p> YxClEnterpriseGcalxxService.java </p>
 * <p> 功能：设备材料企业工程案例信息 </p>
 *
 * <p><a href="YxClEnterpriseGcalxxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */
public interface YxClEnterpriseGcalxxService extends IBaseService<YxClEnterpriseGcalxxVO,String > {

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

}
