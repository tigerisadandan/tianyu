/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywJsgcsgxkzsqMxDao.java
 * 创建日期： 2014-05-27 下午 03:05:51
 * 功能：   施工许可申请表-明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 03:05:51  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywJsgcsgxkzsqMxDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywJsgcsgxkzsqMxDao.java </p>
 * <p> 功能：施工许可申请表-明细 </p>
 *
 * <p><a href="BuSpywJsgcsgxkzsqMxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

@Component
public class BuSpywJsgcsgxkzsqMxDaoImpl  extends BsBaseDaoTJdbc implements BuSpywJsgcsgxkzsqMxDao {
	
	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcsgxkzsqMxDaoImpl.class);
	private static String SQL_QUERY="select SGXKZSQMX_UID, GCMX, GCZJ, JZMJ, CS, CD, YT, JGLX from BU_SPYW_JSGCSGXKZSQ_MX";

	public List<Map<String, String>> find(String id) throws Exception{
		//List<BuSpywJsgcsgxkzsqMxVO> list=new ArrayList<BuSpywJsgcsgxkzsqMxVO>();
		//Map aa=new HashMap();
		//aa.put("SGXKZSQ_UID", id);
		Connection conn = DBUtil.getConnection();
        String ListMX="select GCMX, GCZJ, JZMJ, CS, CD, YT, JGLX FROM BU_SPYW_JSGCSGXKZSQ_MX where SGXKZSQ_UID="+id+"order by SGXKZSQ_UID desc";
        
		List<Map<String, String>> bs = DBUtil.queryReturnList(conn, ListMX);
		
		return bs;
		
	} 
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
                logger.error("建设工程施工许可证-子表明细-信息查询失败!");
                e.printStackTrace(System.out);	
            } finally {
                DBUtil.closeConnetion(conn);
            }
            return domresult;
    	}
    
    public String queryUpdate(String id) throws Exception {
        
		Connection conn = DBUtil.getConnection();
        String ListMX="select GCMX, GCZJ, JZMJ, CS, CD, YT, JGLX FROM BU_SPYW_JSGCSGXKZSQ_MX where SGXKZSQ_UID="+id+"order by SGXKZSQ_UID desc";
    	JSONArray jsonArr = new JSONArray();
    		
    		try {
    			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
    			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
    			for (int i = 0; i < rsList.size(); i++) {
    				Map<String, String> rsMap = rsList.get(i);
    				JSONObject jsonObj = new JSONObject();
    				jsonObj.put("SGXKZSQMX_UID",rsMap.get("SGXKZSQMX_UID"));
    				jsonObj.put("GCMX",rsMap.get("GCMX"));
    				jsonObj.put("GCZJ", rsMap.get("GCZJ"));
    			    jsonObj.put("JZMJ", rsMap.get("JZMJ"));
    			    jsonObj.put("CS", rsMap.get("CS"));
    			    jsonObj.put("CD", rsMap.get("CD"));
    			    jsonObj.put("YT", rsMap.get("YT"));
    			    jsonObj.put("JGLX", rsMap.get("JGLX"));
    			    jsonArr.add(jsonObj);
    			}
          
         
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("建设工程施工许可证-子表明细-更改操作-信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return jsonArr.toString();
        }
}
