/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywSpfjfsyjgyssqPtmxDao.java
 * 创建日期： 2014-06-09 下午 02:02:22
 * 功能：   商品房交付使用竣工验收申请——公建配套明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 02:02:22  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywSpfjfsyjgyssqPtmxDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywSpfjfsyjgyssqPtmxDao.java </p>
 * <p> 功能：商品房交付使用竣工验收申请——公建配套明细 </p>
 *
 * <p><a href="BuSpywSpfjfsyjgyssqPtmxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Component
public class BuSpywSpfjfsyjgyssqPtmxDaoImpl  extends BsBaseDaoTJdbc implements BuSpywSpfjfsyjgyssqPtmxDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywSpfjfsyjgyssqPtmxDaoImpl.class);
	private static String SQL_QUERY="select gjptysmx_uid, spfjfsyjgyssq_uid, xmmc, jhmj, gs," +
			" jgmj, wz from bu_spyw_spfjfsyjgyssq_ptmx";
    
	
	public List<?> find(String id) throws Exception{
		
		Connection conn = DBUtil.getConnection();
		String ListMX="select gjptysmx_uid, spfjfsyjgyssq_uid, xmmc, jhmj, gs, jgmj, wz from bu_spyw_spfjfsyjgyssq_ptmx where SPFJFSYJGYSSQ_UID="+id+"order by gjptysmx_uid desc";	    	
        
		List<?> bs = DBUtil.queryReturnList(conn, ListMX);
		return bs;
		
	} 
	public String queryCondition(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	  PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
              String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
              
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
            logger.error("商品房交付使用-公建配套验收信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}           
	 public String queryUpdate(String id) throws Exception {
	        
			Connection conn = DBUtil.getConnection();
			 String ListMX="select gjptysmx_uid, spfjfsyjgyssq_uid, xmmc, jhmj, gs, jgmj, wz from bu_spyw_spfjfsyjgyssq_ptmx where SPFJFSYJGYSSQ_UID="+id+"order by gjptysmx_uid desc";	    	JSONArray jsonArr = new JSONArray();
	    		
	    		try {
	    			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
	    			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
	    			for (int i = 0; i < rsList.size(); i++) {
	    				Map<String, String> rsMap = rsList.get(i);
	    				JSONObject jsonObj = new JSONObject();
	    				jsonObj.put("GJPTYSMX_UID",rsMap.get("GJPTYSMX_UID"));
	    				jsonObj.put("XMMC", rsMap.get("XMMC"));
	    			    jsonObj.put("JHMJ", rsMap.get("JHMJ"));
	    			    jsonObj.put("GS", rsMap.get("GS"));
	    			    jsonObj.put("JGMJ", rsMap.get("JGMJ"));
	    			    jsonObj.put("WZ", rsMap.get("WZ"));
	    			    jsonArr.add(jsonObj);
	    			}
	          
	         
	         
	        } catch (Exception e) {
	            DBUtil.rollbackConnetion(conn);
	            logger.error("商品房交付使用-公建配套验收信息查询失败!");
	            e.printStackTrace(System.out);	
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
			return jsonArr.toString();
	        }

	public String insert(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String update(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
