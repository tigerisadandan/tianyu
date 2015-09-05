/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jl.EnterpriseZenZizhiDao.java
 * 创建日期： 2015-01-26 下午 02:35:20
 * 功能：   企业增项资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:35:20  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jl.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.jl.vo.EnterpriseZenZizhiVO;
import com.ccthanking.business.jl.dao.EnterpriseZenZizhiDao;
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
 * <p> EnterpriseZenZizhiDao.java </p>
 * <p> 功能：企业增项资质 </p>
 *
 * <p><a href="EnterpriseZenZizhiDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Component
public class EnterpriseZenZizhiDaoImpl  extends BsBaseDaoTJdbc implements EnterpriseZenZizhiDao {

    public String queryCondition(String json, EnterpriseZenZizhiVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);

            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "ENTERPRISE_ZEN_ZIZHI t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
	public String queryListZeng(String uid) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from ENTERPRISE_ZEN_ZIZHI a"
			+" where a.JL_COMPANY_UID = '"+uid+"'";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("ENTERPRISE_ZEN_ZIZHI_UID",rsMap.get("ENTERPRISE_ZEN_ZIZHI_UID"));
				jsonObj.put("ZIZHI_UID", rsMap.get("ZIZHI_UID"));
			    jsonObj.put("ZIZHI_CODE", rsMap.get("ZIZHI_CODE"));
			    jsonObj.put("ZENG_VALID_DATE", rsMap.get("VALID_DATE"));
			    jsonObj.put("ZIZHI_DENGJI", rsMap.get("ZIZHI_DENGJI"));
		
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

}
