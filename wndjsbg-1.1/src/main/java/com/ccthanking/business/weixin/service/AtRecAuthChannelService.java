/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.AtRecAuthChannelService.java
 * 创建日期： 2014-11-27 上午 10:19:22
 * 功能：    接口：栏目受众权限
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-27 上午 10:19:22  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.weixin.service;


import com.ccthanking.weixin.vo.AtRecAuthChannelVO;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> AtRecAuthChannelService.java </p>
 * <p> 功能：栏目受众权限 </p>
 *
 * <p><a href="AtRecAuthChannelService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-27
 * 
 */
public interface AtRecAuthChannelService extends IBaseService<AtRecAuthChannelVO, String> {

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
    String delete(String json,String rec_auth_channel_uid) throws Exception;
    
    public void awardToUsers(String channelid, String[] userids, User user) throws Exception;

}
