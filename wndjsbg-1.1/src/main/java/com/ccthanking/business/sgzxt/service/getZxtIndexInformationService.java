/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgzxt.service.getZxtIndexInformationService.java
 * 创建日期： 2015-08-13 下午 03:19:20
 * 功能：    接口：施工子系统首页信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-08-13 下午 03:19:20  老虎是只耽耽   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgzxt.service;



/**
 * <p> getZxtIndexInformationService.java </p>
 * <p> 功能：施工子系统首页信息 </p>
 *
 * <p><a href="getZxtIndexInformationService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">老虎是只耽耽</a>
 * @version 0.1
 * @since 2015-08-13
 * 
 */
public interface getZxtIndexInformationService{

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,String GongCheng_UID) throws Exception;
    
    String queryGC_SCORE(String json) throws Exception;
    
    String queryGC_SCORE_detail(String json) throws Exception;    
    
    String querySGRY(String json) throws Exception;
    
    String queryJLRY(String json) throws Exception;
    
    String queryRYXX_detail(String json,String personType) throws Exception;
    
    String queryRYXX_Fileid(String json,String PERSON_UID,String File_type) throws Exception;
    
    String querygc(String json) throws Exception;  
}
