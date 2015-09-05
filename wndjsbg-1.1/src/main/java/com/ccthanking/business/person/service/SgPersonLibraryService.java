/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.SgPersonLibraryService.java
 * 创建日期： 2014-04-28 上午 09:29:27
 * 功能：    接口：人员信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-28 上午 09:29:27  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.person.service;


import java.util.Map;

import com.ccthanking.business.person.vo.SgPersonLibraryVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SgPersonLibraryService.java </p>
 * <p> 功能：人员信息 </p>
 *
 * <p><a href="SgPersonLibraryService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-28
 * 
 */
public interface SgPersonLibraryService extends IBaseService<SgPersonLibraryVO, String> {

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
    String queryConditionNotNull(String json) throws Exception;
    /**
     * 新增记录.
     * 
     * @param json
     * @param status 
     * @param user
     * @return 
     * @throws Exception
     * @since v1.00
     */
    String insert(String json, String status,Map fils) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json,Map fils) throws Exception;

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
    /**
     * 通过ID查询.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryList(String json) throws Exception;
   
    /**
     * 通过查询判断是否存在.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCodeIsEmpty(String json) throws Exception;
    /**
     * 通过查询判断是否存在.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryStatusIsEmpty(String json,String bz,String personUID) throws Exception;
    /**
     * 提交审核.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String updateShenhe(String json,Map fils,String u_id,String status,String t_id) throws Exception;
    String updateStatusOne(String u_id,String js) throws Exception;


    /**
     * 复制人员和关联证书以及附件的记录
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String updateCopyPerson(String t_id,String u_id) throws Exception;
    
    String updateCopyPerson2(String status,String IdCard,String shenfenID) throws Exception;

    String tuihui(String ids,String yijian) throws Exception;
    
    String suoding(String id,String yijian,String sjfw,String jz_y) throws Exception;
    
    String jiesuo(String id) throws Exception;

}
