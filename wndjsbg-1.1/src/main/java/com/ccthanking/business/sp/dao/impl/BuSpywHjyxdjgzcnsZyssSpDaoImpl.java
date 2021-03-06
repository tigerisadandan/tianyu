/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcnsZyssSpDao.java
 * 创建日期： 2014-06-17 下午 02:10:09
 * 功能：   建设项目环境影响评价报告表（书）审批--主要设施
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:10:09  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcnsZyssSpDao;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsZyssSpVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuSpywHjyxdjgzcnsZyssSpDao.java </p>
 * <p> 功能：建设项目环境影响评价报告表（书）审批--主要设施 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsZyssSpDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */

@Component
public class BuSpywHjyxdjgzcnsZyssSpDaoImpl  extends BsBaseDaoTJdbc implements BuSpywHjyxdjgzcnsZyssSpDao {

	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsZyssSpDaoImpl.class);
	private static String SQL_QUERY="select hjyxdjgzcnszyss_uid, hjyxdjgzcns_uid, mc, gg, sl," +
			" dw from bu_spyw_hjyxdjgzcns_zyss_sp";
	
	public List<Map<String, String>> find(String id) throws Exception{
		
		Connection conn = DBUtil.getConnection();
        String ListMX="select hjyxdjgzcnszyss_uid, hjyxdjgzcns_uid, mc, gg, sl,dw FROM BU_SPYW_HJYXDJGZCNS_ZYSS_SP where HJYXDJGZCNS_UID="+id+"order by HJYXDJGZCNS_UID desc";
        
		List<Map<String, String>> bs = DBUtil.queryReturnList(conn, ListMX);
		
		return bs;
		
	} 
    public String queryCondition(String json){
    
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
            logger.error("信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
    }
 public String queryZyssUpdate(String id) throws Exception {
        
		Connection conn = DBUtil.getConnection();
        String ListMX="select hjyxdjgzcnszyss_uid, hjyxdjgzcns_uid, mc, gg, sl,dw FROM BU_SPYW_HJYXDJGZCNS_ZYSS_SP where HJYXDJGZCNS_UID="+id+"order by HJYXDJGZCNS_UID desc";
    	JSONArray jsonArr = new JSONArray();
    		
    		try {
    			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
    			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
    			for (int i = 0; i < rsList.size(); i++) {
    				Map<String, String> rsMap = rsList.get(i);
    				JSONObject jsonObj = new JSONObject();
    				jsonObj.put("HJYXDJGZCNSZYSS_UID",rsMap.get("HJYXDJGZCNSZYSS_UID"));
    				jsonObj.put("MC", rsMap.get("MC"));
    				jsonObj.put("GG", rsMap.get("GG"));
    			    jsonObj.put("SL", rsMap.get("SL"));
    			    jsonObj.put("DW", rsMap.get("DW"));
    			   
    			    jsonArr.add(jsonObj);
    			}
          
         
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("信息查询失败!");
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
