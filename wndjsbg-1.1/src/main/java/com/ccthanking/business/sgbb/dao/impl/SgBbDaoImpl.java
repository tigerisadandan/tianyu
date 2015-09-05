/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgbb.SgBbDao.java
 * 创建日期： 2014-04-21 下午 05:57:51
 * 功能：   施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-21 下午 05:57:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sgbb.dao.SgBbDao;
import com.ccthanking.business.sgbb.vo.SgbbVO;
import com.ccthanking.framework.Constants;
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
 * <p>
 * SgBbDao.java
 * </p>
 * <p>
 * 功能：施工报备
 * </p>
 * 
 * <p>
 * <a href="SgBbDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-21
 * 
 */

@Component
public class SgBbDaoImpl extends BsBaseDaoTJdbc implements SgBbDao {
	
	public String queryCondition(String json, SgbbVO vo, Map map) {


		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
			PageManager page = RequestUtil.getPageManager(json);
			String condition = RequestUtil.getConditionList(json).getConditionWhere();
			if (condition.indexOf("t.STATUS = '20'")!=-1) {
				condition = condition.replace("t.STATUS = '20'", "t.STATUS in ('20','50')");
			}
			String orderFilter = RequestUtil.getOrderFilter(json);
			
			if(map.get("status")!=null&&StringUtils.isNotBlank((String) map.get("status"))){
				condition += " and t.BID_TYPE in ("+map.get("status")+")";
			}
			
			condition += " and (t.BID_TYPE in (3) or  (t.bid_type in (2, 1) and t.KB_DATE < sysdate))";
			
//			if(map.get("date_time")!=null&&StringUtils.isNotBlank((String) map.get("date_time"))){
//				String date = (String) map.get("date_time");
//				condition += " and t.KB_DATE < to_date('"+date.replace("-", "").replace(":", "").replace(" ", "")+"','YYYYMMDDHH24MISS')";
//			}
			condition += orderFilter;
			if (page == null)
				page = new PageManager();
			page.setFilter(condition);

			String sql = "select distinct(t.sgbb_uid),pj.PROJECTS_NAME,zx.SHIGONG_PROJECTS_CODE, sel.DENGLU_CODE AS COMPANY_DENGLU_CODE, sel.COMPANY_NAME,t.gc_type_uid, t.event_uid, t.enabled, t.describe, " 
					+" t.created_uid, t.created_name, t.created_date, t.update_uid, t.update_name, t.update_date, "
					+" t.serial_no, t.project_code, t.kb_date, t.bb_code, t.project_name, t.cb_xingzhi, t.cb_zizhi_dengji," 
					+" t.gc_sub_type_uid, t.gc_sub_type_code, t.bid_type, t.guimo, t.cengshu, t.gaodu, t.kuadu, t.shendu, " 
					+" t.jine, t.zhongliang, t.sgry_nums, t.sg_company_uid, t.updated_date, t.description, t.status, " 
					+" t.tijiao_date, t.shenhe_ren, t.shenhe_date, t.shenhe_jieguo, t.shenhe_yijian, t.finish_date, t.projects_uid, t.zuzhi_guanxi_uid ,g.names," 
					+" (select WM_CONCAT(a.sg_name) from sgbb_ry a where a.youxiao_y='Y' and a.sgbb_uid = t.sgbb_uid and a.gangwei_uid = 19) sg_name_jl, "
					+" (select WM_CONCAT(distinct(a.sg_name)) from sgbb_ry a where a.youxiao_y='Y' and a.sgbb_uid = t.sgbb_uid) sg_names "
					+" from sgbb t left join gc_type g on t.gc_sub_type_uid = g.gc_type_uid"
					+" left join sgbb_ry n on n.sgbb_uid = t.sgbb_uid "
					+" left join SG_ENTERPRISE_LIBRARY sel on sel.sg_company_uid = t.sg_company_uid and sel.status = 1"
					+" left join zuzhi_guanxi zx on zx.sgbb_uid = t.sgbb_uid "
					+" left join projects_in_wndjs pj on pj.PROJECTS_UID = zx.PROJECTS_UID ";
//					+" left join zbgg zg on zg.ZBGG_ID = t.ZBGG_ID ";
			BaseResultSet bs = DBUtil.query(conn, sql, page);
			// 合同表
			// bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
			// 项目下达库
			// bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
			// 标段表
			// bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

			// 设置字典
			bs.setFieldDic("STATUS", "SGBB_STATUS");
			bs.setFieldDic("CB_XINGZHI", "CB_XINGZHI");
			bs.setFieldDic("BID_TYPE", "BID_TYPE");
			
			 bs.setFieldDateFormat("KB_DATE", "yyyy-MM-dd HH:mm");
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
	 * 查询子表打印信息.
	 * 
	 * @author 余健
	 */
	public Connection query4Print(String bbid) {
		Connection conn = DBUtil.getConnection();
		List list = null;



		try {
			String sql = "select sr.xuhao,(select g.names from gangwei g where g.gangwei_uid = sr.gangwei_uid) as gw, sr.sg_name, sr.zhengshu_name, sr.zhengshu_code, sr.zhuanye, sr.age,sr.zhicheng_name, sr.shenfenzheng from sgbb_ry sr, sgbb s where sr.sgbb_uid = s.sgbb_uid and s.sgbb_uid = 111 union all select level xuhao, null gs, null sg_name, null zhengshu_name, null zhengshu_code, null zhuanye, null age, null zhicheng_name, null shenfenzheng from dual where level > (select count(*) from sgbb_ry  where sgbb_uid = "+ bbid +"  and status = 1)  and level <= 18 connect by level <= 18";
					
			list=DBUtil.queryReturnList(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return conn;
	}
	

	/**
	 * 查询表单头部信息
	 * 
	 * @author 余健
	 * @return 表单头部信息的MAP对象
	 */
	public List findHeaderPrint(String xmbh) {
		Connection conn = DBUtil.getConnection();
		List map = null;
		try {
			String sql = "select s.project_code, s.project_name, s.bb_code, (select gt.names from  gc_type gt where gt.gc_type_uid=s.gc_type_uid) as gc_type , s.guimo , (select gt.unit from  gc_type gt where gt.gc_type_uid=s.gc_type_uid) as unit , decode(s.bid_type, 1,'公开招标',2,'邀请招标',3,'直接发包') as bid_type, s.sgry_nums,(select sel.company_name from  sg_enterprise_library sel where sel.sg_company_uid=s.sg_company_uid and sel.status=1) as jldw, s.cb_zizhi_dengji as zzdj from " +
					"   sgbb s where sgbb_uid='"+ xmbh+"'";
			
			map =  DBUtil.queryReturnList(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}

	
	public String getNewBaobeiCode() {
		String bb_code = null;
		try {
			User user = ActionContext.getCurrentUserInThread();
			String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
			bb_code = user.getAccount()+"-"+dateString;
			String sql = "select bb_code from sgbb where bb_code like '"+user.getAccount()+"-"+dateString+"%' ORDER BY created_date desc ";
			String[][] result = DBUtil.query(sql);
			if (result!=null&&result[0][0]!=null) {
				int code = Integer.parseInt(result[0][0].substring(result[0][0].length()-3)) + 1;
				if(code<10){
					bb_code += ("00"+code);
				}else if(code<100){
					bb_code += ("0"+code);
				}else if(code>=100){
					bb_code += (""+code);
				}
			}else{
				bb_code += "001";
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return bb_code;
	}
	
//	public static void main(String[] args) {
//		Connection conn = DBUtil.getConnection();
//		try {
//			String sqlCall = "{ call sgbj_package.is_sg_person_unlock(?,?,?,?,?)}";
//			CallableStatement cstmt = conn.prepareCall(sqlCall);
//			cstmt.setInt(1, 20);
//			cstmt.setInt(2, 10);
//			cstmt.setInt(3, 0);
//			cstmt.registerOutParameter(4, OracleTypes.NUMBER);
//			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
//			cstmt.execute();
//			int status = cstmt.getInt(1);
//			String reason = cstmt.getString(2);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeConnetion(conn);
//		}
//	}

	public String queryZbgg(String json) {
		
		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
			PageManager page = RequestUtil.getPageManager(json);
			String condition = RequestUtil.getConditionList(json)
					.getConditionWhere();
			String orderFilter = RequestUtil.getOrderFilter(json);
			condition += orderFilter;
			if (page == null)
				page = new PageManager();
			page.setFilter(condition);

			String sql = "select * from zbgg a ";
			BaseResultSet bs = DBUtil.query(conn, sql, page);
			
			domresult = bs.getJson();

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	
	public String queryParam(String gc_type) {


		Connection conn = DBUtil.getConnection();
		JSONArray jsonArr = new JSONArray();
		try {

			
			String sql = "select * from sg_type_basis where GC_TYPE_UID = "+gc_type;
			
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_TYPE_BASIS_UID",rsMap.get("SG_TYPE_BASIS_UID"));
				jsonObj.put("GC_TYPE_UID", rsMap.get("GC_TYPE_UID"));
			    jsonObj.put("BASIS_CODE", rsMap.get("BASIS_CODE"));
			    jsonObj.put("BASIS_NAME", rsMap.get("BASIS_NAME"));
			    jsonObj.put("BASIS_UNIT", rsMap.get("BASIS_UNIT"));
			    jsonObj.put("GUIMO_Y", rsMap.get("GUIMO_Y"));
			    jsonArr.add(jsonObj);
			}
			

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();

	}
	
	public String queryZg(SgbbVO vo) {

		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			int in_gc_type_uid = Integer.parseInt(vo.getGc_sub_type_uid());
			float in_guimo = StringUtils.isNotBlank(vo.getGuimo())?Float.parseFloat(vo.getGuimo()):0;
			int in_cengshu = StringUtils.isNotBlank(vo.getCengshu())?Integer.parseInt(vo.getCengshu()):0;
			float in_gaodu = StringUtils.isNotBlank(vo.getGaodu())?Float.parseFloat(vo.getGaodu()):0;
			float in_kuadu = StringUtils.isNotBlank(vo.getKuadu())?Float.parseFloat(vo.getKuadu()):0;
			float in_shendu = StringUtils.isNotBlank(vo.getShendu())?Float.parseFloat(vo.getShendu()):0;
			float in_jine = StringUtils.isNotBlank(vo.getJine())?Float.parseFloat(vo.getJine()):0;
			float in_zhongliang = StringUtils.isNotBlank(vo.getZhongliang())?Float.parseFloat(vo.getZhongliang()):0;
			int in_sg_company_uid = Integer.parseInt(user.getIdCard());
			String sql = "select sgbj_package.get_company_fit_y("+in_gc_type_uid+","+in_guimo+","+in_cengshu+","+in_gaodu+","+in_kuadu+","+in_shendu+","+in_jine+","+in_zhongliang+","+in_sg_company_uid+") from dual";
			String[][] res = DBUtil.query(sql);
			if(res!=null&&res[0]!=null){
				domresult = res[0][0];
			}else{
				domresult = "0";
			}
//			domresult = DBUtil.callProcedure(conn, "get_company_fit_y", new Integer[]{in_gc_type_uid,in_guimo,in_cengshu,in_gaodu,in_kuadu,in_shendu,in_jine,in_zhongliang,in_sg_company_uid});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}
	
	public String delete(String sgbb_uid){
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "delete from sgbb s where s.sgbb_uid = " +sgbb_uid;
			String sql_delete_ry = "delete from sgbb_ry s where s.sgbb_uid = "+sgbb_uid;
			DBUtil.exec(conn, sql);
			DBUtil.execSql(conn, sql_delete_ry);
			if (Constants.getBoolean("DATA_SYNC_SGBB", false)) {
				sql = "delete from wndjs.sgbb s where s.sgbb_uid = " +sgbb_uid;
				sql_delete_ry = "delete from wndjs.sgbb_ry s where s.sgbb_uid = "+sgbb_uid;
				DBUtil.exec(conn, sql);
				DBUtil.execSql(conn, sql_delete_ry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
	
	  /**
     * 向老系统库同步数据
     * @param json
     * @throws Exception
     */
    public String insertToOld(String json) throws Exception{
    	Connection conn = null;
    	String flag = null;
    	try {
    		conn = DBUtil.getConnection();
    		JSONObject data = JSONObject.fromObject(json);
			JSONObject data2 = JSONObject.fromObject(JSONObject.fromObject(data.getString("response")).getJSONArray("data").getString(0));
    		String uid = data2.getString("SGBB_UID"); 
			String sql_copy_sgbb = "insert into wndjs.sgbb "
					+" (sgbb_uid, bb_code, project_name, cb_xingzhi, cb_zizhi_dengji, gc_type_uid, gc_sub_type_uid, gc_sub_type_code, bid_type, guimo, cengshu, gaodu, kuadu, shendu, jine, zhongliang, sgry_nums, sg_company_uid, updated_date, description, status, tijiao_date, shenhe_ren, shenhe_date, shenhe_jieguo, shenhe_yijian, finish_date, projects_uid, zuzhi_guanxi_uid, project_code, kb_date, zbgg_id) "
					+" select sgbb_uid, bb_code, project_name, cb_xingzhi, cb_zizhi_dengji, gc_type_uid, gc_sub_type_uid, gc_sub_type_code, bid_type, guimo, cengshu, gaodu, kuadu, shendu, jine, zhongliang, sgry_nums, sg_company_uid, updated_date, description, status, tijiao_date, shenhe_ren, shenhe_date, shenhe_jieguo, shenhe_yijian, finish_date, projects_uid, zuzhi_guanxi_uid, project_code, kb_date, zgbb_id from sgbb where sgbb_uid = " +uid;
			String sql_copy_sgbb_ry = "insert into wndjs.sgbb_ry "
				  	+" (sgbb_ry_uid, sgbb_uid, xuhao, gangwei_uid, must_y, sg_person_uid, sg_name, zhengshu_name, zhuanye, zhengshu_code, zhengshu_date, aqkh_code, age, zhicheng_name, mobile, shenfenzheng, updated_date, status, lock_end_date, changed_date, changed_reason, youxiao_y, old_uid) "
				  	+" select sgbb_ry_uid, sgbb_uid, xuhao, gangwei_uid, must_y, sg_person_uid, sg_name, zhengshu_name, zhuanye, zhengshu_code, zhengshu_date, aqkh_code, age, zhicheng_name, mobile, shenfenzheng, updated_date, status, lock_end_date, changed_date, changed_reason, youxiao_y, old_uid from WNDJS_NEW.sgbb_ry where sgbb_uid = "+uid;
//			String sql_copy_sgbb_ry_vo = "";
			
			DBUtil.exec(conn, sql_copy_sgbb);
			DBUtil.exec(conn, sql_copy_sgbb_ry);
//			DBUtil.exec(conn, sql_copy_sgbb_ry_vo);
			conn.commit();
			
			flag = json;
			
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;
    }
    
    public void updateBbzt(String sgbb_uid,String status){
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			String sql = "update sgbb set shenhe_date = sysdate,SHENHE_JIEGUO="+status+",SHENHE_REN="+user.getUserSN()+",status = "+ status +" where sgbb_uid = " +sgbb_uid;
			DBUtil.exec(conn, sql);
			if ("10".equals(status)) {
				String sql_ry = "update sgbb_ry set status =0 where sgbb_uid = "+ sgbb_uid;
				DBUtil.exec(conn, sql_ry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

    
	/**
	 * 查询子表打印信息修改   20140526 by longchuxiong
	 * 
	 * */
	public List query4PrintForList(String bbid) {
		Connection conn = DBUtil.getConnection();
		List list = null;
		try {
			String sql="select sr.xuhao, "+
						"       (select g.names from gangwei g where g.gangwei_uid = sr.gangwei_uid) as gw, "+
						"      sr.sg_name, sr.zhengshu_name, sr.zhengshu_code, sr.zhuanye, sr.age, "+
						"       sr.zhicheng_name, sr.shenfenzheng "+
						"  from sgbb_ry sr, sgbb s "+
						" where sr.sgbb_uid = s.sgbb_uid "+
						"   and s.sgbb_uid = '"+ bbid +"' "+
						" union all "+
						" select level xuhao, null gs, null sg_name, null zhengshu_name, null zhengshu_code, "+
						"       null zhuanye, null age, null zhicheng_name, null shenfenzheng "+
						"  from dual "+
						" where level > (select count(*) "+
						"                  from sgbb_ry sr, sgbb s "+
						"                 where sr.sgbb_uid = s.sgbb_uid "+
						"                   and s.sgbb_uid = '"+ bbid +"' ) "+
						"   and level <= 18 "+
						" connect by level <= 18 ";
			list=DBUtil.queryReturnList(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}

	public String unLockBb(String uid) {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			String sql = "update sgbb t set t.status = '50',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where t.sgbb_uid = "+ uid;
			DBUtil.exec(conn, sql);				//解锁报备
			sql = "insert into sgy_status_info "
			  +" (sgy_status_info_uid,    created_uid, created_name, created_date, sg_person_uid, " 
			  +" old_status, new_status, change_date, reason, sgbb_uid, sgbb_ry_uid) "
			  +" select sgy_status_info_uid.nextval, '"+user.getAccount()+"', '"+user.getName()+"',sysdate,sg_person_uid,1,0, "
			  +" sysdate,'报备解锁',sgbb_uid,sgbb_ry_uid   from sgbb_ry "
			  +" where sgbb_uid = "+uid +" and status = 1 and YOUXIAO_Y='Y'";
			DBUtil.exec(conn, sql);			//解锁人员的日志信息
			sql = "update sgbb_ry t set status = '0',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where sgbb_uid = "+uid;
			DBUtil.exec(conn, sql);			//解锁人员状态
		} catch (Exception e) {
			DaoException.handleException("**********解锁报备*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public String unLockBbry(String uid) {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			String sql = "update sgbb_ry t set status = '0',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where sgbb_ry_uid = "+uid;
			DBUtil.exec(conn, sql);			//解锁人员状态
			
			sql = "insert into sgy_status_info "
			  +" (sgy_status_info_uid,    created_uid, created_name, created_date, sg_person_uid, " 
			  +" old_status, new_status, change_date, reason, sgbb_uid, sgbb_ry_uid) "
			  +" select sgy_status_info_uid.nextval, '"+user.getAccount()+"', '"+user.getName()+"',sysdate,sg_person_uid,1,0, "
			  +" sysdate,'报备解锁',sgbb_uid,sgbb_ry_uid   from sgbb_ry "
			  +" where sgbb_ry_uid = "+uid ;
			DBUtil.exec(conn, sql);			//解锁人员的日志信息
			
			//验证是否所有人员都已解锁,是则更新报备状态
			sql = "select count(*) from sgbb_ry a where a.sgbb_uid = ("
				+ " select b.sgbb_uid from sgbb_ry b where b.sgbb_ry_uid = "+uid
				+ " ) and a.status = '1' and a.YOUXIAO_Y='Y'";
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				if ("0".equals(res[0][0])) {
					sql = "update sgbb t set t.status = '50',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where t.sgbb_uid = (select b.sgbb_uid from sgbb_ry b where b.sgbb_ry_uid = "+uid+")";
					DBUtil.exec(conn, sql);	
				}
			}
			
		} catch (Exception e) {
			DaoException.handleException("**********解锁报备人员*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
	public String personLock(String uid) {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			String sql = "update sgbb_ry t set status = '1',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where sgbb_ry_uid = "+uid;
			DBUtil.exec(conn, sql);			//锁定人员状态
			
			sql = "insert into sgy_status_info "
			  +" (sgy_status_info_uid,    created_uid, created_name, created_date, sg_person_uid, " 
			  +" old_status, new_status, change_date, reason, sgbb_uid, sgbb_ry_uid) "
			  +" select sgy_status_info_uid.nextval, '"+user.getAccount()+"', '"+user.getName()+"',sysdate,sg_person_uid,0,1, "
			  +" sysdate,'人员单个锁定',sgbb_uid,sgbb_ry_uid   from sgbb_ry "
			  +" where sgbb_ry_uid = "+uid ;
			DBUtil.exec(conn, sql);			//解锁人员的日志信息
			
			
		} catch (Exception e) {
			DaoException.handleException("**********项目已完成，锁定人员*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
}
