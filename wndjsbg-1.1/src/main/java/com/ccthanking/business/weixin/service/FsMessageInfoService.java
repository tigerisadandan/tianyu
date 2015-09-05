/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.FsMessageInfoService.java
 * 创建日期： 2014-12-02 上午 11:04:41
 * 功能：    接口：消息表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-02 上午 11:04:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.weixin.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.framework.service.IBaseService;
import com.ccthanking.weixin.vo.FsMessageInfoVO;


/**
 * <p> FsMessageInfoService.java </p>
 * <p> 功能：消息表 </p>
 *
 * <p><a href="FsMessageInfoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-02
 * 
 */
public interface FsMessageInfoService extends IBaseService<FsMessageInfoVO, String> {

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
    
    /**
     * userto      接受者帐号
     * usertoname  接受者姓名
     * title       消息主题
     * content     消息内容
     * */
    String insertVo(Map messageMap) throws Exception;
    
    public String countMessage(Map map);

}
