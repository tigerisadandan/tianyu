/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.bu_spyw_xqxxqtclzxjjftsq_clDao.java
 * 创建日期： 2014-10-28 下午 04:49:05
 * 功能：   无锡新区新型墙体材料专项基金返退申请_材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 下午 04:49:05  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.bu_spyw_xqxxqtclzxjjftsq_clDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> bu_spyw_xqxxqtclzxjjftsq_clDao.java </p>
 * <p> 功能：无锡新区新型墙体材料专项基金返退申请_材料 </p>
 *
 * <p><a href="bu_spyw_xqxxqtclzxjjftsq_clDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */

@Component
public class bu_spyw_xqxxqtclzxjjftsq_clDaoImpl  extends BsBaseDaoTJdbc implements bu_spyw_xqxxqtclzxjjftsq_clDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcsgxkzsqMxDaoImpl.class);
	private static String SQL_QUERY="select cl_uid, xqxxqtclzxjjft_uid, lb, qtbw, clpzjmc, zsbh, sjsylhjldw, zbz from bu_spyw_xqxxqtclzxjjftsq_cl";

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
            logger.error("无锡新区新型墙体材料专项基金返退申请_材料信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	
	public String queryUpdate(String id) throws Exception {
    
	Connection conn = DBUtil.getConnection();
    String ListMX="select cl_uid, xqxxqtclzxjjft_uid, lb, qtbw, clpzjmc, zsbh, sjsylhjldw, zbz from bu_spyw_xqxxqtclzxjjftsq_cl where xqxxqtclzxjjft_uid="+id+" order by xqxxqtclzxjjft_uid desc";
	JSONArray jsonArr = new JSONArray();
		
		try {
			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("CL_UID",rsMap.get("CL_UID"));
				jsonObj.put("LB",rsMap.get("LB"));
				jsonObj.put("QTBW", rsMap.get("QTBW"));
			    jsonObj.put("CLPZJMC", rsMap.get("CLPZJMC"));
			    jsonObj.put("ZSBH", rsMap.get("ZSBH"));
			    jsonObj.put("SJSYLHJLDW", rsMap.get("SJSYLHJLDW"));
			    jsonObj.put("ZBZ", rsMap.get("ZBZ"));
			    jsonArr.add(jsonObj);
			}
      
     
     
    } catch (Exception e) {
        DBUtil.rollbackConnetion(conn);
        logger.error("无锡新区新型墙体材料专项基金返退申请_材料信息查询失败!");
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



}
