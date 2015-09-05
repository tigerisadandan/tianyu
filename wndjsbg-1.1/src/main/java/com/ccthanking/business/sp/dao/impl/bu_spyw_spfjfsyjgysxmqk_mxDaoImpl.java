/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.bu_spyw_spfjfsyjgysxmqk_mxDao.java
 * 创建日期： 2014-11-06 下午 03:55:33
 * 功能：   商品房交付使用竣工验收项目情况表_明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:55:33  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.bu_spyw_spfjfsyjgysxmqk_mxDao;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysxmqkMxVO;
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
 * <p> bu_spyw_spfjfsyjgysxmqk_mxDao.java </p>
 * <p> 功能：商品房交付使用竣工验收项目情况表_明细 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysxmqk_mxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Component
public class bu_spyw_spfjfsyjgysxmqk_mxDaoImpl  extends BsBaseDaoTJdbc implements bu_spyw_spfjfsyjgysxmqk_mxDao {
	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysxmqk_mxDaoImpl.class);
	private static String SQL_QUERY="select spfjfsyjgysxmqk_mx_uid, spfjfsyjgysxmqk_uid, dh, mph, cs, mpfmj, mpcs, jzfmj, jzts, bz from bu_spyw_spfjfsyjgysxmqk_mx";

	
    public String queryCondition(String json, BuSpywSpfjfsyjgysxmqkMxVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY, page);
           
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    // 在此可加入其它方法
	
	public String queryUpdate(String id) throws Exception {
    
	Connection conn = DBUtil.getConnection();
    String ListMX="select spfjfsyjgysxmqk_mx_uid, spfjfsyjgysxmqk_uid, dh, mph, cs, mpfmj, mpcs, jzfmj, jzts, bz from bu_spyw_spfjfsyjgysxmqk_mx where spfjfsyjgysxmqk_uid="+id+" order by spfjfsyjgysxmqk_uid desc";
	JSONArray jsonArr = new JSONArray();
		
		try {
			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SPFJFSYJGYSXMQK_MX_UID",rsMap.get("SPFJFSYJGYSXMQK_MX_UID"));
				jsonObj.put("DH",rsMap.get("DH"));
				jsonObj.put("MPH", rsMap.get("MPH"));
			    jsonObj.put("CS", rsMap.get("CS"));
			    jsonObj.put("MPFMJ", rsMap.get("MPFMJ"));
			    jsonObj.put("MPCS", rsMap.get("MPCS"));
			    jsonObj.put("JZFMJ", rsMap.get("JZFMJ"));
			    jsonObj.put("JZTS", rsMap.get("JZTS"));
			    jsonObj.put("BZ", rsMap.get("BZ"));
			    jsonArr.add(jsonObj);
			}
      
     
     
    } catch (Exception e) {
        DBUtil.rollbackConnetion(conn);
        logger.error("商品房交付使用竣工验收项目情况表_明细查询失败!");
        e.printStackTrace(System.out);	
    } finally {
        DBUtil.closeConnetion(conn);
    }
	return jsonArr.toString();
    }
}
