/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.spxx.BuSpLzhfDao.java
 * 创建日期： 2014-06-25 下午 03:35:39
 * 功能：   审批业务流转核发文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-25 下午 03:35:39  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.framework.dao.BsBaseDaoable;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;


/**
 * <p> BuSpLzhfDao.java </p>
 * <p> 功能接口：审批业务流转核发文件 </p>
 *
 * <p><a href="BuSpLzhfDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-25
 * 
 */

public interface BuSpLzhfDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuSpLzhfVO vo, Map map);

	// 可在此加入业务独特的服务接口
	//获取模版文件list
	public List<?> getTempletFile(String ywlzUid,String clk_uid);
	//往fs_fileupload 添加数据
	public boolean insertFileUpload(FileUploadVO vo);
	
	
}
