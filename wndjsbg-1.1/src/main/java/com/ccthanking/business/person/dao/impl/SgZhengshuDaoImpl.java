/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.SgZhengshuDao.java
 * 创建日期： 2014-04-27 下午 01:22:52
 * 功能：   人员证书信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:22:52  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.person.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.person.dao.SgZhengshuDao;
import com.ccthanking.business.person.vo.SgZhengshuVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;


/**
 * <p> SgZhengshuDao.java </p>
 * <p> 功能：人员证书信息 </p>
 *
 * <p><a href="SgZhengshuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

@Component
public class SgZhengshuDaoImpl  extends BsBaseDaoTJdbc implements SgZhengshuDao {

	public String queryCondition(String json) throws Exception {
		
		return null;
	}
    public String queryCondition(String json, SgZhengshuVO vo, Map map){
    
    	return null;

    }
    
    public String queryZsByGw(String personUid, String gwUid) throws Exception {
		Connection conn = DBUtil.getConnection();
        JSONArray jsonArr = new JSONArray();
        try {

//           String sql = "select e.zhengshu_level,a.sg_person_uid,b.zhengshu_code,e.zhengshu_name,b.end_date ,f.zhuanye_name "
//        	   	+" from sg_person_library a left join sg_person_zhengshu b on a.sg_person_library_uid = b.sg_person_uid "
//				+" left join sg_zhengshu e on b.sg_zhengshu_uid = e.sg_zhengshu_uid "
//				+" left join cb_gangwei_zhengshu c on c.sg_zhengshu_uid = e.sg_zhengshu_uid "
//				+" left join gangwei d on d.gangwei_uid = c.gangwei_uid "
//				+" left join sg_zizhi f on b.sg_zizhi_uid = f.sg_zizhi_uid "
//				+" where a.status = 1 and a.sg_person_uid = "+personUid+" and d.gangwei_uid = "+gwUid+" order by d.gangwei_uid";
//           List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
//			for (int i = 0; i < rsList.size(); i++) {
//				Map<String, String> rsMap = rsList.get(i);
//				JSONObject jsonObj = new JSONObject();
//				jsonObj.put("SG_PERSON_UID",rsMap.get("SG_PERSON_UID"));
//				jsonObj.put("ZHENGSHU_CODE", rsMap.get("ZHENGSHU_CODE"));
//				jsonObj.put("ZHENGSHU_NAME", rsMap.get("ZHENGSHU_NAME"));
//				jsonObj.put("END_DATE", rsMap.get("END_DATE"));
//				jsonObj.put("ZHUANGYE_NAME", rsMap.get("ZHUANGYE_NAME"));
//				jsonObj.put("ZHUANGYE_LEVEL", rsMap.get("ZHUANGYE_LEVEL"));
//			    
//			    jsonArr.add(jsonObj);
//			}
        	
        	String sql = "select sgbj_package.get_person_zhengshu_by_gangwei("+personUid+","+gwUid+") from dual";
        	String[][] rsList = DBUtil.query(conn, sql);
        	if(rsList!=null&&rsList[0][0]!=null){
        		return rsList[0][0];
        	}
 			
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return null;
	}

	public String delete(String json) throws Exception {
		
		return null;
	}

	public String insert(String json) throws Exception {
		
		return null;
	}

	public String queryAllZhengshu() throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from sg_zhengshu order by SERIAL_NO";
		JSONArray jsonArr = new JSONArray();

		try {
			List<Map<String, String>> rsList = DBUtil
					.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_ZHENGSHU_UID", rsMap.get("SG_ZHENGSHU_UID"));
				jsonObj.put("ZHENGSHU_NAME", rsMap.get("ZHENGSHU_NAME"));

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
	public String queryZs(String personUid,String zhengshu_type) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select c.zhengshu_level from sg_person_library a "
				+" left join sg_person_zhengshu b on a.sg_person_library_uid = b.sg_person_uid"
				+" left join sg_zhengshu c on c.sg_zhengshu_uid = b.sg_zhengshu_uid"
				+" where a.sg_person_uid ="+personUid+" and a.status = 1 and c.zhengshu_type = '"+zhengshu_type+"' order by c.zhengshu_level";
			String[][] result = DBUtil.query(conn, sql);
			if(result!=null&&StringUtils.isNotBlank(result[0][0])){
				return result[0][0];
			}
		} catch (Exception e) {
			 DBUtil.rollbackConnetion(conn);
//           logger.error("人员信息查询失败!");
           e.printStackTrace(System.out);	
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
}
