/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgEnterPriseLibraryService.java
 * 创建日期： 2014-04-09 上午 10:51:19
 * 功能：    接口：业务页面模版表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-09 上午 10:51:19  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.service;


import java.util.Map;

import com.ccthanking.business.sgenter.vo.SgEnterpriseLibraryVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SgEnterPriseLibraryService.java </p>
 * <p> 功能：业务页面模版表 </p>
 *
 * <p><a href="SgEnterPriseLibraryService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-09
 * 
 */
public interface SgEnterPriseLibraryService extends IBaseService<SgEnterpriseLibraryVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,Map map) throws Exception;
    
    String updatetuihui(String json,String liyou) throws Exception;
    
    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json,Map map) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json,Map map,String id) throws Exception;

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
     * 加载资质列表
     * @return
     * @throws Exception
     */
    String queryAll() throws Exception;
    
    /**
     * 判断登陆编号和密码是否正确,若返回当前登录的用户
     * @param denglu_code
     * @param pwd
     * @return
     * @throws Exception
     */
    SgEnterpriseLibraryVO getUserByUsernameAndPassword(String denglu_code,String pwd) throws Exception;
    
    /**
     * 查询组织结构代码是否重复
     * @return
     * @throws Exception
     */
    String queryCodeIsEmpty(String json) throws Exception;
    
    /**
     * 查询审核历史.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryApproval(String json) throws Exception;
    
    /**
     * 加载附件信息
     * @param target_id
     * @return
     * @throws Exception
     */
    String loadFiles(Map map) throws Exception;
    
    
    /**
     * 复制一份状态为40的施工信息
     * @return
     * @throws Exception
     */
    void updateCopySGXX() throws Exception;
    
    /**
     * 向老系统库同步数据
     * @param json
     * @throws Exception
     */
    String insertToOld(String json) throws Exception;
    
    /** 查询公司审批列表
    * @param json
    * @param map
    * @return
    * @throws Exception
    */
   String queryAppList(String json,Map map) throws Exception;
   
   /**
    * 模拟审核 获取状态为30的最新审核数据
    * @param json
    * @return
    * @throws Exception
    */
   String queryOnSp(String json) throws Exception;
   
   /**
    * 复制一份状态为40的施工信息
    * @return
    * @throws Exception
    */
   String updateCopyByStatus(Map map) throws Exception;
   
   /**
    * 更新审核信息
    * @param map
    * @throws Exception
    */
   void updateDshxx(Map map) throws Exception;
   
   /**
    * 更新入库信息
    * @param json
    * @return
    * @throws Exception
    */
   String updateCopyToRk(String json) throws Exception;
   
   /**
    * 确认奖项信息
    * @param uid
    * @throws Exception
    */
   void updateQueren(String uid) throws Exception;
}
