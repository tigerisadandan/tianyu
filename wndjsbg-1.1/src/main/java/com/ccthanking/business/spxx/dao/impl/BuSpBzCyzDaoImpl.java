/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpBzCyzDao.java
 * 创建日期： 2014-06-13 下午 04:43:40
 * 功能：   业务材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:43:40  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpBzCyzDao;
import com.ccthanking.business.spxx.vo.BuSpBzCyzVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.ccthanking.framework.util.StringUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuSpBzCyzDao.java </p>
 * <p> 功能：业务材料 </p>
 *
 * <p><a href="BuSpBzCyzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Component
public class BuSpBzCyzDaoImpl  extends BsBaseDaoTJdbc implements BuSpBzCyzDao {

    public String queryCondition(String json, BuSpBzCyzVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "BU_SP_BZ_CYZ t";
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
    public String getUsers(String bz_uid,String uname) {
		User user = ActionContext.getCurrentUserInThread();    
        Connection conn = DBUtil.getConnection();
        JSONArray arr = new JSONArray();
        JSONObject obj = null;
        try {
        	String sql=" select t.users_uid,t.user_name from users t where t.use_y = 'Y' and t.admin_y = 'N' ";
        	if (StringUtils.isNotBlank(uname)) {
				sql += " and t.user_name like '%"+uname+"%' or upper(t.logon_name) like '%"+uname.toUpperCase()+"%'";
				//sql += " or upper(t.logon_name) like '%"+uname.toUpperCase()+"%'";
			}
        	String[][] res = DBUtil.query(conn, sql);
        	if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("USERS_ID", res[i][0]);
					obj.put("USER_NAME", res[i][1]);
					//是否在本步骤中已存在了
//					if ("0".equals(res[i][2])) {
//						obj.put("YXZ", "FALSE");
//					}else{
//						obj.put("YXZ", "TRUE");
//					}
					
					arr.add(obj);
				}
			}

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询用户列表出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return arr.toString();
		
	}

	public String insertUsers(String json) {
		User user = ActionContext.getCurrentUserInThread();    
        Connection conn = DBUtil.getConnection();

        try {
        	JSONObject obj = JSONObject.fromObject(json);
        	String sql_delete = "delete from BU_SP_BZ_CYZ t where t.spbz_uid = "+obj.getString("bz_uid");
        	DBUtil.exec(conn, sql_delete);
        	if (StringUtils.isBlank(obj.getString("users"))) {
				return null;
			}
            String[] arr = obj.getString("users").substring(0,obj.getString("users").length()-1).split(",");
            for (int j = 0; j < arr.length; j++) {
            	if(!arr[j].equals("")){//过滤掉数组中值为空的
            	String sql=" insert into BU_SP_BZ_CYZ (BZCYZ_UID,SPBZ_UID,CYZUID) values (BZCYZ_UID.Nextval,'"+obj.getString("bz_uid")+"',"+arr[j]+")";
            	DBUtil.exec(conn, sql);
            	}
			}
        	
        } catch (Exception e) {
        	e.printStackTrace();
            DaoException.handleMessageException("*********添加步骤参与者出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}
	
	 // 在此可加入其它方法
    public String queryBzCyz(String bz_uid) {
		User user = ActionContext.getCurrentUserInThread();    
        Connection conn = DBUtil.getConnection();
        JSONArray arr = new JSONArray();
        JSONObject obj = null;
        try {
        	String sql="select t.users_uid,t.user_name from bu_sp_bz_cyz c ,users t where c.cyzuid = t.users_uid and c.spbz_uid = "+bz_uid;
        	
        	String[][] res = DBUtil.query(conn, sql);
        	if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("USERS_ID", res[i][0]);
					obj.put("USER_NAME", res[i][1]);
					
					arr.add(obj);
				}
			}

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询步骤参与者数据出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return arr.toString();
		
	}
}
