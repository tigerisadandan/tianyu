/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.WuGongService.java
 * 创建日期： 2015-03-24 上午 11:44:51
 * 功能：    接口：务工信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 上午 11:44:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rygl.service;



import com.ccthanking.business.dtgl.rygl.vo.WuGongVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> WuGongService.java </p>
 * <p> 功能：务工信息 </p>
 *
 * <p><a href="WuGongService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */
public interface WuGongService extends IBaseService<WuGongVO, String> {

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

	String wugongtj(String msg);

	String queryById(String msg);

	String queryJineng(String msg);

	String queryTijianInfo(String msg);

	String queryGzqk(String msg);

}
