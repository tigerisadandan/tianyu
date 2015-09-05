/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.bu_spyw_spfjfsyjgysbadtjzhz_dtDao.java
 * 创建日期： 2014-11-06 下午 03:57:43
 * 功能：   商品房交付使用竣工验收备案单体建筑汇总_单体
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:57:43  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.bu_spyw_spfjfsyjgysbadtjzhz_dtDao;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzDtVO;
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
 * <p> bu_spyw_spfjfsyjgysbadtjzhz_dtDao.java </p>
 * <p> 功能：商品房交付使用竣工验收备案单体建筑汇总_单体 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysbadtjzhz_dtDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Component
public class bu_spyw_spfjfsyjgysbadtjzhz_dtDaoImpl  extends BsBaseDaoTJdbc implements bu_spyw_spfjfsyjgysbadtjzhz_dtDao {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysbadtjzhz_dtDaoImpl.class);
	private static String SQL_QUERY="select spfjfsyjgysbadtjzhz_dt_uid, spfjfsyjgysbadtjzhz_uid, jgdtdh, jgmj, hs, cs, zgd, units_uid from bu_spyw_spfjfsyjgysbadtjzhz_dt";

	
    public String queryCondition(String json){
    
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
    String ListMX="select spfjfsyjgysbadtjzhz_dt_uid, spfjfsyjgysbadtjzhz_uid, jgdtdh, jgmj, hs, cs, zgd, units_uid from bu_spyw_spfjfsyjgysbadtjzhz_dt where spfjfsyjgysbadtjzhz_uid="+id+" order by spfjfsyjgysbadtjzhz_uid desc";
	JSONArray jsonArr = new JSONArray();
		
		try {
			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListMX);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SPFJFSYJGYSBADTJZHZ_DT_UID",rsMap.get("SPFJFSYJGYSBADTJZHZ_DT_UID"));
				jsonObj.put("UNITS_UID",rsMap.get("UNITS_UID"));
				jsonObj.put("JGDTDH", rsMap.get("JGDTDH"));
			    jsonObj.put("JGMJ", rsMap.get("JGMJ"));
			    jsonObj.put("HS", rsMap.get("HS"));
			    jsonObj.put("CS", rsMap.get("CS"));
			    jsonObj.put("ZGD", rsMap.get("ZGD"));
			   
			    jsonArr.add(jsonObj);
			}
      
     
     
    } catch (Exception e) {
        DBUtil.rollbackConnetion(conn);
        logger.error("商品房交付使用竣工验收备案单体建筑汇总_单体查询失败!");
        e.printStackTrace(System.out);	
    } finally {
        DBUtil.closeConnetion(conn);
    }
	return jsonArr.toString();
    }


}
