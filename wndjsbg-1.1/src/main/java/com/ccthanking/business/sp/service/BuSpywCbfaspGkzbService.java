/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCbfaspGkzbService.java
 * 创建日期： 2015-05-06 下午 02:07:52
 * 功能：    接口：公开（邀请）招标合同备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-06 下午 02:07:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service;



import com.ccthanking.business.spyw.vo.BuSpywCbfaspGkzbVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuSpywCbfaspGkzbService.java </p>
 * <p> 功能：公开（邀请）招标合同备案表 </p>
 *
 * <p><a href="BuSpywCbfaspGkzbService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-06
 * 
 */
public interface BuSpywCbfaspGkzbService extends IBaseService<BuSpywCbfaspGkzbVO, String> {

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


    String ywlzclhf(String json)throws Exception;
}
