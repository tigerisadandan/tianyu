/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2014
 * 文件：   com.ccthanking.business.resources.JsComClkDao.java
 * 创建日期： 2014-06-14 下午 05:05:25
 * 功能：   企业材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-14 下午 05:05:25  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.resources.dao;

import java.util.List;

import net.sf.json.JSONObject;

import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JsComClkDao.java </p>
 * <p> 功能接口：企业材料库 </p>
 *
 * <p><a href="JsComClkDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-14
 * 
 */

public interface JsComClkDao extends BsBaseDaoable {

	//查询注册公司list  add by longchuxiong 20140617
	public List<?> getAllCompanyList(String json); 
	
	
	public List<?> getAllCompanyClkList(JSONObject object);
}
