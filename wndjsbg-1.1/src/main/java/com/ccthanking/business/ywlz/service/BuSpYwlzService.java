/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzService.java
 * 创建日期： 2014-06-19 下午 04:51:04
 * 功能：    接口：审批业务流转实例
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:51:04  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.ywlz.service;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ccthanking.business.ywlz.vo.BuSpYwlzVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpYwlzService.java </p>
 * <p> 功能：审批业务流转实例 </p>
 *
 * <p><a href="BuSpYwlzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */
public interface BuSpYwlzService extends IBaseService<BuSpYwlzVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,String spyw_uid_res) throws Exception;
    
    String queryClType(String json) throws Exception;
    
    String queryByProjectsid(String json) throws Exception;
    
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
    
    public  BuSpYwlzVO updateVo(BuSpYwlzVO vo) throws Exception;
    
    /**
     * 获取材料记录.
     */
    public  String queryYwcl(Map map) throws Exception;
    public String getSpCount(String spywuid) throws Exception;
    
    public String sfysp(String ywlzuid)throws Exception;
    
    public String getAllDsCount() throws Exception;
    
    String downloadGc(HttpServletResponse response,String json,String bh) throws Exception;
    
    String downloadJz(HttpServletResponse response,String json) throws Exception;
    
    String downloadSz(HttpServletResponse response,String json) throws Exception;

}
