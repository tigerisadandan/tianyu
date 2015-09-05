/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzBzService.java
 * 创建日期： 2014-06-19 下午 04:49:00
 * 功能：    接口：审批业务流转步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:49:00  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.ywlz.service;


import com.ccthanking.business.ywlz.vo.BuSpYwlzBzVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpYwlzBzService.java </p>
 * <p> 功能：审批业务流转步骤 </p>
 *
 * <p><a href="BuSpYwlzBzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */
public interface BuSpYwlzBzService extends IBaseService<BuSpYwlzBzVO, String> {

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
    
    String queryJlRy(String json) throws Exception;
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
    
    String insertJlRy(String json,String gcid,String xzid) throws Exception;
    
    String getYwLzBzVoNoUser(String json) throws Exception;
    
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
    
    String updateFfzcz(String json) throws Exception;

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
     * 审核步骤初始化调用方法
     * ywlzid  业务
     * bzcs 步骤次数  从0开始记开始步骤
     * 
     * 业务实例生成是调用即添加审批步骤的第一步记录数据
     * 后面在步骤参与者审核通过后调用该方法
     * */
    public String saveYwLzBzVo(String ywlzuid,int bzcs) throws Exception;
    
    public String updateYwLzBzVo(String json) throws Exception;
    
    public String queryYwLzBzLjrCode(String json) throws Exception;
    
    //获取审核列表
    public String geYwLzBzList(String ywlzuid) throws Exception;
    
    public String getYwLzBzVo(String ywlzuid)throws Exception;
    
    public String geSpYwClList(String ywlzuid)throws Exception;
    
}
