/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.nbgl.OrganizeDao.java
 * 创建日期： 2015-05-25 下午 09:13:36
 * 功能：   组织结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-25 下午 09:13:36  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.nbgl.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.nbgl.dao.OrganizeDao;
import com.ccthanking.business.dtgl.nbgl.vo.OrganizeVO;
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
 * <p> OrganizeDao.java </p>
 * <p> 功能：组织结构 </p>
 *
 * <p><a href="OrganizeDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-25
 * 
 */

@Component
public class OrganizeDaoImpl  extends BsBaseDaoTJdbc implements OrganizeDao {

    public String queryCondition(String json, OrganizeVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            
            String sql = "SELECT * FROM " + "ORGANIZE t";
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
    
    public String queryOrganize(){
        
    	Connection conn = DBUtil.getConnection();
    	String sql = "SELECT * FROM " + "ORGANIZE t";
		JSONArray jsonArr = new JSONArray();
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
				for (int i = 0; i < rsList.size(); i++) {
					Map<String, String> rsMap = rsList.get(i);
					JSONObject json = new JSONObject();
					json.put("id", rsMap.get("ORGANIZE_UID"));
					json.put("parentId", rsMap.get("P_ORGANIZE_UID"));
					json.put("name", rsMap.get("ORG_NAME"));
					json.put("type", rsMap.get("ORG_TYPE"));
					String type = rsMap.get("ORG_TYPE");
					if(type.equals("C")){//机构
						json.put("icon", "../../../../img/zzjg/pd_dm_company.gif");
					}else if(type.equals("D")){//部门
						json.put("icon", "../../../../img/zzjg/pd_dm_depart.gif");
					}else if(type.equals("U")){//人员
						json.put("icon", "../../../../img/zzjg/pd_dm_person.gif");
					}
						
					
					jsonArr.add(json);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();

    }
    
    public String queryRestDeptUser(){
    	User user = ActionContext.getCurrentUserInThread();
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
			String sql = "select o.* from organize o where  o.p_organize_uid != (select P_ORGANIZE_UID from organize where USER_UID = "+user.getUserSN()+") and o.SEND_Y = 'Y' and o.org_type = 'U' ";
			BaseResultSet bs = DBUtil.query(conn, sql,null);
			domresult = bs.getJson();
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
    	return domresult;
    }
	public String queryDeptUserByCurrentUser() {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select o.* from organize o where  o.p_organize_uid = (select P_ORGANIZE_UID from organize where USER_UID = "+user.getUserSN()+") and o.SEND_Y = 'Y' and o.org_type = 'U' ";
			BaseResultSet bs = DBUtil.query(conn, sql,null);
			domresult = bs.getJson();
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
    	return domresult;
	}
    
    // 在此可加入其它方法

}
