/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.BuSpywSgqyyjhcsxQyyjhchzMxDao.java
 * 创建日期： 2014-11-12 上午 11:32:59
 * 功能：   施工企业业绩核查汇总表_明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-12 上午 11:32:59  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywSgqyyjhcsxQyyjhchzMxDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywSgqyyjhcsxQyyjhchzMxDao.java </p>
 * <p> 功能：施工企业业绩核查汇总表_明细 </p>
 *
 * <p><a href="BuSpywSgqyyjhcsxQyyjhchzMxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-12
 * 
 */

@Component
public class BuSpywSgqyyjhcsxQyyjhchzMxDaoImpl  extends BsBaseDaoTJdbc implements BuSpywSgqyyjhcsxQyyjhchzMxDao {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcsgxkzsqMxDaoImpl.class);
	private static String SQL_QUERY="select sgqyyjhcsx_qyyjhchz_mx_uid, sgqyyjhcsx_qyyjhchz_uid, sbdwmc, xmmc, gcdz, z" +
			"bsj, kjgkcsjsmsj, zbj, sjwcdw, zyjszb1, zyjszb2, sffsaqsg, sfysmsjyz, event_uid, enabled, " +
			"describe, created_uid, created_name, created_date, update_uid, update_name, update_date, " +
			"serial_no from bu_spyw_sgqyyjhcsx_qyyjhchz_mx";

	public String queryCondition(String json) throws Exception {
	    
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
            String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
            //condition += "  and sss='ddd' "
            String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
           
            condition += orderFilter;//把查询出来的数据进行排序
          
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);
            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY, page);//连接数据库，进行查询，结果集给bs
            domresult = bs.getJson();//把转换好的数据给domresult
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("施工企业业绩核查汇总表_明细信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	
	public String queryUpdate(String id) throws Exception {
    
	Connection conn = DBUtil.getConnection();
    String ListMX="select sgqyyjhcsx_qyyjhchz_mx_uid, sgqyyjhcsx_qyyjhchz_uid, sbdwmc, xmmc, gcdz, zbsj, kjgkcsjsmsj, zbj, sjwcdw, zyjszb1, zyjszb2, sffsaqsg, sfysmsjyz,  describe from bu_spyw_sgqyyjhcsx_qyyjhchz_mx where sgqyyjhcsx_qyyjhchz_uid="+id+" order by sgqyyjhcsx_qyyjhchz_uid desc";
	JSONArray jsonArr = new JSONArray();
		
		try {
			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SGQYYJHCSX_QYYJHCHZ_MX_UID",rsMap.get("SGQYYJHCSX_QYYJHCHZ_MX_UID"));
				jsonObj.put("SBDWMC",rsMap.get("SBDWMC"));
				jsonObj.put("XMMC", rsMap.get("XMMC"));
			    jsonObj.put("GCDZ", rsMap.get("GCDZ"));
			    jsonObj.put("ZBSJ", rsMap.get("ZBSJ"));
			    jsonObj.put("KJGKCSJSMSJ", rsMap.get("KJGKCSJSMSJ"));
			    jsonObj.put("ZBJ", rsMap.get("ZBJ"));
			    
			    
				jsonObj.put("SJWCDW", rsMap.get("SJWCDW"));
			    jsonObj.put("ZYJSZB1", rsMap.get("ZYJSZB1"));
			    jsonObj.put("ZYJSZB2", rsMap.get("ZYJSZB2"));
			    jsonObj.put("SFFSAQSG", rsMap.get("SFFSAQSG"));
			    jsonObj.put("SFYSMSJYZ", rsMap.get("SFYSMSJYZ"));
			    jsonObj.put("DESCRIBE", rsMap.get("DESCRIBE"));
			    jsonArr.add(jsonObj);
			}
      
     
     
    } catch (Exception e) {
        DBUtil.rollbackConnetion(conn);
        logger.error("施工企业业绩核查汇总表_明细信息查询失败!");
        e.printStackTrace(System.out);	
    } finally {
        DBUtil.closeConnetion(conn);
    }
	return jsonArr.toString();
    }
    // 在此可加入其它方法

public String insert(String json) throws Exception {
	// TODO Auto-generated method stub
	return null;
}

    // 在此可加入其它方法

}
