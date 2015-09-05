/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgContentService.java
 * 创建日期： 2015-04-21 下午 01:24:35
 * 功能：    接口：需整改的安全隐患
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:24:35  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service;



import com.ccthanking.business.dtgl.yhzg.vo.ZgContentVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ZgContentService.java </p>
 * <p> 功能：需整改的安全隐患 </p>
 *
 * <p><a href="ZgContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */
public interface ZgContentService extends IBaseService<ZgContentVO, String> {

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

	String queryByTzdUid(String tzdUid);

	String queryScore(String tzdUid);

	String getContent(String tzdUid);

	String getPicNum(String tzdUid);

}
