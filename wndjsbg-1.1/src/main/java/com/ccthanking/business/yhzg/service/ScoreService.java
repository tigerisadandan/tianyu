/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ScoreService.java
 * 创建日期： 2015-05-17 上午 09:23:51
 * 功能：    接口：分数管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-17 上午 09:23:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service;




import com.ccthanking.business.dtgl.yhzg.vo.ScoreVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> ScoreService.java </p>
 * <p> 功能：分数管理 </p>
 *
 * <p><a href="ScoreService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-05-17
 * 
 */
public interface ScoreService extends IBaseService<ScoreVO, String> {

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
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition1(String json) throws Exception;
    String queryFenshu(String json,String condition) throws Exception;
    
    String queryJiafen(String json,String shenhe) throws Exception;
    
    String queryjfxx(String json,String JFSQ_UID) throws Exception;
    
    String queryspxx(String json,String JFSQ_UID) throws Exception;
    
    String queryjfdx(String json,String JFSQ_UID) throws Exception;
    
    String queryjfsj(String json,String JFSQ_UID) throws Exception;
    
    String updatestatus(String agree,String JFSTATUS,String JFSQ_UID,String shyj) throws Exception;
    
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
    
    
	String getDshkCount(String msg) ;
	
	String getDshjCount(String msg) ;

	String queryById(String sUid);

	String updatePersonSh(String msg);
	
	String updateCompanySh(String msg);
	
	/**
	 * //查询指定的 类型的 审核通过的 扣分总数
	 * @param id
	 * @param type
	 * @return
	 */
	String getShtgkSum(String id,String type);
	
	/**
	 * //查询指定的 类型的 审核通过的 加分总数
	 * @param id
	 * @param type
	 * @return
	 */
	String getShtgjSum(String id,String type);

}
