/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgbb.SgbbRyDao.java
 * 创建日期： 2014-04-24 下午 07:04:31
 * 功能：   施工报备人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-24 下午 07:04:31  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgbb.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sgbb.dao.SgbbRyDao;
import com.ccthanking.business.sgbb.vo.SgbbRyVO;
import com.ccthanking.business.sgbb.vo.SgyStatusInfoVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.QuerySet;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> SgbbRyDao.java </p>
 * <p> 功能：施工报备人员 </p>
 *
 * <p><a href="SgbbRyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-24
 * 
 */

@Component
public class SgbbRyDaoImpl  extends BsBaseDaoTJdbc implements SgbbRyDao {

    public String queryCondition(String json, SgbbRyVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
           
            condition += " order by xuhao ";
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT t.*,b.names,c.zhicheng_uid FROM SGBB_RY t left join gangwei b on b.gangwei_uid = t.gangwei_uid left join SG_PERSON_LIBRARY c on c.SG_PERSON_UID = t.SG_PERSON_UID and c.status = 1 left join zhicheng d on d.zhicheng_uid = c.zhicheng_uid";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    // 在此可加入其它方法
    public String queryBbry(String bb_uid){
        
    	Connection conn = DBUtil.getConnection();
		String queryDictSql = "select a.*,b.names from sgbb_ry a left join gangwei b on b.gangwei_uid = a.gangwei_uid  where a.SGBB_UID = "+bb_uid +" order by a.xuhao";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, queryDictSql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("SGBB_RY_UID",rsMap.get("SGBB_RY_UID"));
			    json.put("SGBB_UID", rsMap.get("SGBB_UID"));
			    json.put("ENABLED", rsMap.get("ENABLED"));
			    json.put("DESCRIBE", rsMap.get("DESCRIBE"));
			    json.put("XUHAO", rsMap.get("XUHAO"));
			    json.put("MUST_Y", rsMap.get("MUST_Y"));
			    json.put("SG_PERSON_UID", rsMap.get("SG_PERSON_UID"));
			    json.put("SG_NAME", rsMap.get("SG_NAME"));
			    json.put("ZHENGSHU_NAME", rsMap.get("ZHENGSHU_NAME"));
			    json.put("ZHENGSHU_CODE", rsMap.get("ZHENGSHU_CODE"));
			    json.put("ZHENGSHU_DATE", rsMap.get("ZHENGSHU_DATE"));
			    json.put("AQKH_CODE", rsMap.get("AQKH_CODE"));
			    json.put("AGE", rsMap.get("AGE"));
			    json.put("ZHICHENG_NAME", rsMap.get("ZHICHENG_NAME"));
			    json.put("MOBILE", rsMap.get("MOBILE"));
			    json.put("SHENFENZHENG", rsMap.get("SHENFENZHENG"));
			    json.put("STATUS", rsMap.get("STATUS"));
			    json.put("GANGWEI_UID", rsMap.get("GANGWEI_UID"));
			    json.put("NAMES", rsMap.get("NAMES"));
			    json.put("ZHUANYE", rsMap.get("ZHUANYE"));
			    jsonArr.add(json);
			}
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();

    }
    
    public String queryLockUser(String sg_person_uid,String gangwei_uid,String sgbb_uid) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call sgbj_package.is_sg_person_unlock(?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.setInt(1, StringUtils.isNotBlank(sg_person_uid)?Integer.parseInt(sg_person_uid):0);
			cstmt.setInt(2, StringUtils.isNotBlank(gangwei_uid)?Integer.parseInt(gangwei_uid):0);
			cstmt.setInt(3, StringUtils.isNotBlank(sgbb_uid)?Integer.parseInt(sgbb_uid):0);
			cstmt.registerOutParameter(4, OracleTypes.NUMBER);
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.execute();
			int status = cstmt.getInt(4);
			String reason = cstmt.getString(5);
			obj.put("STATUS", status);
			obj.put("REASON", reason);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();

	}

	public void deleteByUids(String uids,String sgbb_uid) {
		String sql_delete = "delete from sgbb_ry where sgbb_uid = "+sgbb_uid+" and sgbb_ry_uid not in ("+uids+")";
		Connection conn = null;
    	try {
    		conn = DBUtil.getConnection();
			DBUtil.exec(conn,sql_delete);
			
			if(Constants.getBoolean("DATA_SYNC_SGBB", false)){
				sql_delete = "delete from wndjs.sgbb_ry where sgbb_uid = "+sgbb_uid+" and sgbb_ry_uid not in ("+uids+")";
			}
			
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	public void syncUpdateRyxx(String uid) {
		
		
		Connection conn = null;
		String[][] res = null;
		String sql = null;
		List<String> ryids = new ArrayList<String>();
		try {
			conn = DBUtil.getConnection();
			sql = "select t.*,g.names,y.project_name,y.project_code from sgbb_ry t,sgbb y,gangwei g  where t.sgbb_uid = y.sgbb_uid and t.gangwei_uid = g.gangwei_uid and  t.sgbb_uid = " + uid;
			QuerySet qs = DBUtil.executeQuery(sql, null, conn);
			for (int i = 0; i < qs.getRowCount(); i++) {
				String ry_uid = qs.getString(i+1, "SGBB_RY_UID");
				ryids.add(ry_uid);
				String p_uid = qs.getString(i+1, "SG_PERSON_UID");
				String xuhao = qs.getString(i+1, "XUHAO");
				String code = qs.getString(i+1, "PROJECT_CODE");
				String name = qs.getString(i+1, "PROJECT_NAME");
				String cdate = qs.getString(i+1, "CREATED_DATE");
				String udate = qs.getString(i+1, "UPDATED_DATE");
				String gangwei = qs.getString(i+1, "GANGWEI_UID");
				if(StringUtils.isBlank(cdate)){
					 SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				        
					cdate = format.format(new Date());
				}
				if(StringUtils.isBlank(udate)){
					udate = cdate;
				}
				String reason = udate+"在"+name+"("+code+")报备中加锁";
				sql = " select t.sgbb_ry_uid, t.sg_person_uid, t.gangwei_uid from wndjs.sgbb_ry t where t.sgbb_ry_uid = "+ry_uid;
				res = DBUtil.query(conn, sql);
				String sql_update = null;
				
				boolean flag = false;
				if (res!=null) {
					if(res[0][0]!=null){
						if(p_uid.equals(res[0][1])&&gangwei.equals(res[0][2])){
							//人员和岗位未发生改变,只更新xuhao字段
							sql_update = "update wndjs.sgbb_ry set xuhao = " + xuhao + " where sgbb_ry_uid = " + res[0][0];
							flag = false;
						}else{
							//人员或岗位发生改变,更新相关字段
//							sql_update = "update wndjs.sgbb_ry "
//								+" set sgbb_uid = v.sgbb_uid, "
//								+" xuhao = v.xuhao, gangwei_uid = v.gangwei_uid, "
//								+" must_y = v.must_y,sg_person_uid = v.sg_person_uid, "
//								+" sg_name = v.sg_name, zhengshu_name = v.zhengshu_name, "
//								+" zhuanye = v.zhuanye, zhengshu_code = v.zhengshu_code, "
//								+" zhengshu_date = v.zhengshu_date, aqkh_code = v.aqkh_code, "
//								+" age = v.age, zhicheng_name = v.zhicheng_name, "
//								+" mobile = v.mobile,shenfenzheng = v.shenfenzheng, "
//								+" updated_date = v.updated_date,status = v.status "
//								+" from (select * from sgbb_ry where sgbb_ry_uid = "+ry_uid+") v"
//								+" where sgbb_ry_uid = "+ry_uid;
							sql_update = "update wndjs.sgbb_ry  set "
								+" (xuhao, gangwei_uid, must_y,sg_person_uid,sg_name, "
								+" zhengshu_name,zhuanye, zhengshu_code,zhengshu_date, "
								+" aqkh_code,age,zhicheng_name,mobile, shenfenzheng, "
								+" updated_date,status) = ( "
								+" select xuhao, gangwei_uid, must_y,sg_person_uid, "
								+"    sg_name,zhengshu_name,zhuanye,zhengshu_code, "
								+"   zhengshu_date,aqkh_code,age,zhicheng_name, "
								+"  mobile,shenfenzheng,updated_date,status "
								+" from sgbb_ry where sgbb_ry_uid = "+ry_uid
								+" )"
								+" where sgbb_ry_uid = "+ry_uid;;
							flag = true;
							
						}
					}else{
						//未找到报备人员信息,新增数据
						sql_update = "insert into wndjs.sgbb_ry "
							  +" (sgbb_ry_uid, sgbb_uid, xuhao, gangwei_uid, must_y, sg_person_uid, sg_name, zhengshu_name, zhuanye, zhengshu_code, zhengshu_date, aqkh_code, age, zhicheng_name, mobile, shenfenzheng, updated_date, status, lock_end_date, changed_date, changed_reason, youxiao_y, old_uid) "
							  +" select sgbb_ry_uid, sgbb_uid, xuhao, gangwei_uid, must_y, sg_person_uid, sg_name, zhengshu_name, zhuanye, zhengshu_code, zhengshu_date, aqkh_code, age, zhicheng_name, mobile, shenfenzheng, updated_date, status, lock_end_date, changed_date, changed_reason, youxiao_y, old_uid from sgbb_ry"
							  +" where sgbb_ry_uid = " + ry_uid;
						
						flag = true;
					}
					
					DBUtil.exec(conn, sql_update);
					if(flag){
						User user = ActionContext.getCurrentUserInThread();
						
						SgyStatusInfoVO stVO = new SgyStatusInfoVO();
						stVO.setChange_date(new Date());
						stVO.setCreated_date(new Date());
						stVO.setCreated_name(user.getName());
						stVO.setCreated_uid(user.getUserSN());
						stVO.setOld_status("0");
						stVO.setNew_status("1");
						stVO.setReason(reason);
						stVO.setSg_person_uid(p_uid);
						stVO.setSgbb_uid(uid);
						stVO.setSgbb_ry_uid(ry_uid);
						
						syncSgbbStatus(stVO);
					}
					
				}
				
			}
			if(ryids.size()>0){
				String ryid_str = ryids.toString().substring(1,ryids.toString().length()-1);
				String sql_delete = "delete from wndjs.sgbb_ry where sgbb_uid = " + uid +" and sgbb_ry_uid not in ("+ryid_str+") ";
				DBUtil.exec(conn, sql_delete);
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	
	
	public SgyStatusInfoVO insertSgbbStatus(Connection conn,SgbbRyVO vo,String reason){
		
		User user = ActionContext.getCurrentUserInThread();
		SgyStatusInfoVO stVO = new SgyStatusInfoVO();
		stVO.setChange_date(new Date());
		stVO.setCreated_date(new Date());
		stVO.setCreated_name(user.getName());
		stVO.setCreated_uid(user.getUserSN());
		stVO.setOld_status("0");
		stVO.setNew_status("1");
		stVO.setReason(reason);
		stVO.setSg_person_uid(vo.getSg_person_uid());
		stVO.setSgbb_uid(vo.getSgbb_uid());
		stVO.setSgbb_ry_uid(vo.getSgbb_ry_uid());
		try {
			if(conn==null)
			conn = DBUtil.getConnection();
			
			BaseDAO.insert(conn, stVO);
			
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return stVO;
	}
	
	public void syncSgbbStatus(SgyStatusInfoVO stVO){
		if(Constants.getBoolean("DATA_SYNC_SGBB", false)){
			String copy_sql = "insert into wndjs.sgy_status_info "
			  +" (sgy_status_info_uid, sg_person_uid, old_status, new_status, change_date, reason, sgbb_uid, sgbb_ry_uid) values "
			  +" (?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				DBUtil.executeUpdate(copy_sql, new Object[]{DBUtil.getSequenceValue("wndjs.SGY_STATUS_INFO_UID"),stVO.getSg_person_uid(),
						stVO.getOld_status(),stVO.getNew_status(),stVO.getChange_date(),stVO.getReason(),stVO.getSgbb_uid(),stVO.getSgbb_ry_uid()});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean queryPersonUid(String ryUid,String gangwei,String pUid){
		boolean flag = true;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) sg_person_uid from sgbb_ry t where t.sgbb_ry_uid = "+ryUid+" and gangwei_uid = "+ gangwei +" and sg_person_uid = " + pUid;
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				if(Integer.parseInt(res[0][0])>0){
					flag = false;
				}
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}
}

