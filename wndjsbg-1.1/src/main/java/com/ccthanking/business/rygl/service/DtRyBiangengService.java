/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.DtRyBiangengService.java
 * 创建日期： 2015-04-12 下午 08:31:04
 * 功能：    接口：人员变更
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-12 下午 08:31:04  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rygl.service;



import com.ccthanking.business.dtgl.rygl.vo.DtRyBiangengVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> DtRyBiangengService.java </p>
 * <p> 功能：人员变更 </p>
 *
 * <p><a href="DtRyBiangengService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java1@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-12
 * 
 */
public interface DtRyBiangengService extends IBaseService<DtRyBiangengVO,String > {

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

	String getBgCount(String msg);

	String queryBg(String msg);

	String updateTg(String msg);

	String updateTh(String msg);

}
