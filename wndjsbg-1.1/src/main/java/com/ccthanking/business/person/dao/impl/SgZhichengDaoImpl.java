/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.ZhichengDao.java
 * 创建日期： 2014-04-27 下午 01:35:40
 * 功能：   职称信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:35:40  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.person.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.person.dao.SgZhichengDao;
import com.ccthanking.business.person.vo.ZhichengVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;


/**
 * <p> ZhichengDao.java </p>
 * <p> 功能：职称信息 </p>
 *
 * <p><a href="ZhichengDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

@Component
public class SgZhichengDaoImpl  extends BsBaseDaoTJdbc implements SgZhichengDao {

    public String queryCondition(String json, ZhichengVO vo, Map map){
       
        return null;

    }

	public String delete(String json) throws Exception {
		
		return null;
	}

	public String insert(String json) throws Exception {
	
		return null;
	}

	public String queryCondition(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from zhicheng order by SERIAL_NO";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("ZHICHENG_UID",rsMap.get("ZHICHENG_UID"));
				jsonObj.put("ZHICHENG_NAME", rsMap.get("ZHICHENG_NAME"));
			    
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	public String update(String json) throws Exception {
		
		return null;
	}
 
}
