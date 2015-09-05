/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgenter.SgECreditProjectsDao.java
 * 创建日期： 2014-04-20 下午 02:22:53
 * 功能：   企业参与项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-20 下午 02:22:53  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgenter.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.sgenter.dao.SgECreditProjectsDao;
import com.ccthanking.business.sgenter.vo.SgECreditProjectsVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> SgECreditProjectsDao.java </p>
 * <p> 功能：企业参与项目 </p>
 *
 * <p><a href="SgECreditProjectsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-20
 * 
 */

@Component
public class SgECreditProjectsDaoImpl  extends BsBaseDaoTJdbc implements SgECreditProjectsDao {

    public String queryCondition(String json, SgECreditProjectsVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT a.*,( select count(*) from at_fileupload b where b.ENABLED=1 and b.target_uid=a.CREDIT_PROJECTS_UID) FJS FROM SG_E_CREDIT_PROJECTS a";
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
    
    // 在此可加入其它方法

    /**
     * 获取增项资质列表
     * @param uid
     * @return
     * @throws Exception
     */
    public String queryXmList(String uid) {
    	Connection conn = DBUtil.getConnection();
		String sql = "select t.*,sgk_package.company_project_change(t.CREDIT_PROJECTS_UID) CZ from SG_E_CREDIT_PROJECTS t where t.ENABLED = 1 and t.SG_ENTERPRISE_LIBRARY_UID = "+uid+" order by t.created_date" ;
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("CREDIT_PROJECTS_UID",rsMap.get("CREDIT_PROJECTS_UID"));
				jsonObj.put("SG_ENTERPRISE_LIBRARY_UID", rsMap.get("SG_ENTERPRISE_LIBRARY_UID"));
			    jsonObj.put("ENABLED", rsMap.get("ENABLED"));
			    jsonObj.put("DESCRIBE", rsMap.get("DESCRIBE"));
			    jsonObj.put("CREATED_UID", rsMap.get("CREATED_UID"));
			    jsonObj.put("CREATED_NAME", rsMap.get("CREATED_NAME"));
			    jsonObj.put("CREATED_DATE", rsMap.get("CREATED_DATE"));
			    jsonObj.put("SERIAL_NO", rsMap.get("SERIAL_NO"));
			    jsonObj.put("PROJECT_NAME", rsMap.get("PROJECT_NAME"));
			    jsonObj.put("YXBEGIN_DATE", rsMap.get("YXBEGIN_DATE"));
			    jsonObj.put("CZ", rsMap.get("CZ"));
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
    }

	public String updateCopyXmxx(String sgUid) {
		Connection conn = null;
		try {
			User user = ActionContext.getCurrentUserInThread();
			conn = DBUtil.getConnection();
			String sql = "select CREDIT_PROJECTS_UID from SG_E_CREDIT_PROJECTS where SG_ENTERPRISE_LIBRARY_UID = (select SG_ENTERPRISE_LIBRARY_UID from SG_ENTERPRISE_LIBRARY where status = 1 and sg_company_uid = "+user.getIdCard()+")";
			String[][] results = DBUtil.query(conn,sql);
			if(results==null){
				return null;
			};
			for (int i = 0; i < results.length; i++) {
				String new_id = DBUtil.getSequenceValue("CREDIT_PROJECTS_UID");
				String copy_sql = "insert into SG_E_CREDIT_PROJECTS (CREDIT_PROJECTS_UID, SG_ENTERPRISE_LIBRARY_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, PROJECT_NAME, YXBEGIN_DATE, SF_CHECKED, PROJECT_SCORE) "
						+" select "+new_id+", "+sgUid+", EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, PROJECT_NAME, YXBEGIN_DATE, SF_CHECKED, PROJECT_SCORE from  "
						+" SG_E_CREDIT_PROJECTS where CREDIT_PROJECTS_UID = "+results[i][0];
				DBUtil.exec(copy_sql);
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+results[i][0];
				DBUtil.exec(copy_file);
				
			}
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return "";
	}
}
