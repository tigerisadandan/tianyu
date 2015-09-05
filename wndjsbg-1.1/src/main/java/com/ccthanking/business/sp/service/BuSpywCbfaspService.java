/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCbfaspService.java
 * 创建日期： 2014-05-28 上午 10:34:34
 * 功能：    接口：资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 上午 10:34:34  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;


import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywCbfaspService.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywCbfaspService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */
public interface BuSpywCbfaspService extends IBaseService<BuSpywCbfaspVO, String> {

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
    String download(String id,String ywlz,String ty) throws Exception ;
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
    String insertHF(String json) throws Exception;

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
    String findByPerson(String lx,String uid,String bb_code) throws Exception;
    String findByCompany(String lx,String bb_code) throws Exception;
    String getNum(String lx) throws Exception;
    
    String ywlzclhf(String json)throws Exception;
    
  
}
