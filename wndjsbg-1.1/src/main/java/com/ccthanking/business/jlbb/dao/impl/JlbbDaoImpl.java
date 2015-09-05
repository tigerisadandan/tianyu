/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jlbb.JlbbDao.java
 * 创建日期： 2015-01-28 上午 09:10:22
 * 功能：   监理报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:10:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jlbb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.jl.vo.JlbbVO;
import com.ccthanking.business.jlbb.dao.JlbbDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;


/**
 * <p> JlbbDao.java </p>
 * <p> 功能：监理报备 </p>
 *
 * <p><a href="JlbbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Component
public class JlbbDaoImpl  extends BsBaseDaoTJdbc implements JlbbDao {

    public String queryCondition(String json, Map<String,String> map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        String bbstatus = "";
        try {

            // 组织查询条件

            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
			if (condition.indexOf("TABA.STATUS = '20'")!=-1) {
				condition = condition.replace("TABA.STATUS = '20'", "TABA.STATUS in ('20','50')");
			}
			String orderFilter = RequestUtil.getOrderFilter(json);
			
			if(map.get("status")!=null&&StringUtils.isNotBlank((String) map.get("status"))){
				condition += " and TABA.BID_TYPE in ("+map.get("status")+")";
			}
			
			condition += " and (TABA.BID_TYPE in (3) or  (TABA.bid_type in (2, 1) and TABA.KB_DATE < sysdate))";
			condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT TABA.*,TABB.JLRY FROM ( ");
            sql.append("  SELECT distinct(BB.JLBB_UID),bb.jl_company_uid,bb.project_code," );
            sql.append("e.company_name,BB.BB_CODE,BB.PROJECT_NAME,TY.NAMES GCLX,DJ.NAMES AS GCDJ,");
            sql.append("JLY.JL_NAME ZJ,bb.STATUS,BB.TIJIAO_DATE,BB.PROJECTS_UID,BB.KB_DATE,BB.BID_TYPE,bb.gc_type_uid,  ");
            sql.append(" bb.gc_sub_type_uid,bb.guimo,bb.jine,");
            sql.append(" bb.cengshu,bb.gaodu,bb.kuadu,bb.changdu,bb.dantimianji,bb.JLRY_NUMS ");
            sql.append("   FROM JLBB BB ");
            sql.append("  LEFT JOIN GC_TYPE TY ");
            sql.append("  ON BB.GC_SUB_TYPE_UID = TY.GC_TYPE_UID ");
            sql.append("  LEFT JOIN GC_DENGJI DJ ");
            sql.append("  ON BB.GC_DENGJI_UID = DJ.GC_DENGJI_UID ");
            sql.append("  LEFT JOIN JLBB_JLY JLY ");
            sql.append("  ON BB.JLBB_UID = JLY.JLBB_UID ");
            sql.append("  LEFT JOIN GANGWEI GW ");
            sql.append("  ON JLY.GANGWEI_UID = GW.GANGWEI_UID ");
            sql.append("  LEFT JOIN ENTERPRISE_LIBRARY E ");
            sql.append("  ON BB.JL_COMPANY_UID = E.JL_COMPANY_UID ");
            sql.append("  WHERE  GW.CODES = 'ZJ') TABA ");
            sql.append("  LEFT JOIN ");
            sql.append("  (SELECT BB.JLBB_UID,WM_CONCAT(JLY.JL_NAME) AS JLRY FROM JLBB BB  ");
            sql.append("   LEFT JOIN JLBB_JLY JLY ");
            sql.append("  ON BB.JLBB_UID = JLY.JLBB_UID ");
            sql.append("  LEFT JOIN GANGWEI GW  ");
            sql.append("   ON JLY.GANGWEI_UID = GW.GANGWEI_UID ");
            sql.append("   WHERE GW.CODES <> 'ZJ' ");
            sql.append("   GROUP BY BB.JLBB_UID ");
            sql.append("   ) TABB ");
            sql.append("   ON TABA.JLBB_UID = TABB.JLBB_UID ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            
            bs.setFieldDic("STATUS", "JLBB_STATUS");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String queryZbgg(String json) {
		Connection conn = DBUtil.getConnection();
        String domresult = "";
		try {
		     PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
	         String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	            
	            
	         String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	         condition += orderFilter;//把查询出来的数据进行排序
	         if (page == null)page = new PageManager();
	         page.setFilter(condition);
	         StringBuffer sql = new StringBuffer();
	         sql.append("SELECT * FROM ZBGG T");
	         BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
	            
	         domresult = bs.getJson();//把转换好的数据给domresult
	         
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String queryBbxx(String json) {
		Connection conn = DBUtil.getConnection();
        String domresult = "";
		try {
		     PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
	         String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	            
	            
	         String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	         condition += orderFilter;//把查询出来的数据进行排序
	         if (page == null)page = new PageManager();
	         page.setFilter(condition);
	         StringBuffer sql = new StringBuffer();
	         sql.append("SELECT * FROM JLBB T");
	         BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
	            
	         bs.setFieldDic("STATUS", "STATUS");
	         bs.setFieldDic("", "");
	         bs.setFieldDic("", "");
	         bs.setFieldDic("", "");
	         domresult = bs.getJson();//把转换好的数据给domresult

	         
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	
	private String queryParam(String gc_type) {

		Connection conn = DBUtil.getConnection();
		JSONArray jsonArr = new JSONArray();
		try {

			
			String sql = "select * from jl_type_basis where GC_TYPE_UID = "+gc_type;
			
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

	public String queryPersonList(String msg) throws Exception {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		String domresult = "";
		String zhucetype = "";
		String gwid = "";
		String gcdj = "";
		try {
			JSONObject querycondition = JSONObject.fromObject(msg);
			String querycondition_txt = querycondition.getString("querycondition");
	        JSONObject subcondition = JSONObject.fromObject(querycondition_txt);
			querycondition_txt = subcondition.getString("conditions");
	        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(querycondition_txt);
	        int  index = 0;
	        for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				if("zhucetype".equals(obj.get("fieldname"))){
					index = i;
					zhucetype = obj.getString("value");
				}
			}
	        if(index !=0){
	        	jsonArray.remove(index);
	        	index = 0;
	        }
	     
	        
			PageManager page = RequestUtil.getPageManager(msg);
			//String condition = SelectUtil.getConditionList(jsonArray).getConditionWhere();
			/**
			 * 添加多选条件
			 */
			if(!"".equals(zhucetype)){
				String[] str = zhucetype.split(",");
				String sqlstr = "'";
				for (int i = 0; i < str.length; i++) {
					sqlstr += str[i]+"','";
				}
				if(sqlstr.length()!=0){
					sqlstr = sqlstr.substring(0,sqlstr.length()-2);
				}
				
				//condition+= " AND P.ZHUCE_TYPE IN ("+sqlstr+")";
			}
			
	
			
			
			///condition += " AND P.JL_COMPANY_UID = '"+user.getIdCard()+"'";
			//condition += " AND P.STATUS = '1'";
			//System.out.println(condition);
			String orderFilter = RequestUtil.getOrderFilter(msg);
			//condition += orderFilter;
			if (page == null) {
				page = new PageManager();
			}
			//page.setFilter(condition);

			StringBuffer sql = new StringBuffer();
			sql.append("  SELECT P.JL_PERSON_UID,P.PERSON_NAME,P.SEX, ");
			sql.append("  P.SHENFENZHENG,P.ZHUCE_TYPE,P.ZHUCE_CODE, ");
			sql.append("  ZY.NAMES AS ZHUANYE,ZC.ZHICHENG_NAME  AS ZHICHENG ,VALID_DATE");
			sql.append("  FROM PERSON_LIBRARY P ");
			sql.append("  LEFT JOIN WNDJS.ZHUANYE ZY ");
			sql.append("  ON P.ZHUANYE1 = ZY.ZHUANYE_UID ");
			sql.append("  LEFT JOIN ZHICHENG ZC ");
			sql.append("  ON P.ZHICHENG_UID = ZC.ZHICHENG_UID ");

			BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
			
			//while(bs.getResultSet().next()){
			//	System.out.println(bs.getResultSet().next());
			//}
			
			//bs.addInfo(tagName, text)
			//ResultSet rs = bs.getResultSet();
			/*
			while(rs.next()){
				

				
				//System.out.println(isExist(conn,rs.getString("JL_PERSON_UID"),gwid));
				//System.out.println(rs.getString("VALID_DATE"));
				//System.out.println(isOverdue(rs.getString("VALID_DATE")));
			}*/
			bs.setFieldDic("SEX", "SEX");
			bs.setFieldDic("ZHUCE_TYPE", "JLRY_CYZG");
			
			//bs.addInfo("flag", "true");
			//System.out.println(bs.getJson());
			domresult = bs.getJson();
	
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String getNewBaobeiCode() throws Exception {
		String bb_code = null;
		try {
			User user = ActionContext.getCurrentUserInThread();
			String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
			bb_code = user.getAccount()+"-"+dateString;
			String sql = "select bb_code from jlbb where bb_code like '"+user.getAccount()+"-"+dateString+"%' ORDER BY tijiao_date desc ";
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

	public void updateBbzt(String jlbb_uid,String status) throws Exception {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			String sql = "update jlbb set shenhe_date = sysdate,SHENHE_JIEGUO='"+status+"',SHENHE_REN='"+user.getUserSN()+"',status = '"+ status +"' where jlbb_uid = '" +jlbb_uid+"'";
			DBUtil.exec(conn, sql);
			if ("10".equals(status)) {
				String sql_ry = "update jlbb_jly set status = '0' where jlbb_uid = '"+ jlbb_uid+"'";
				DBUtil.exec(conn, sql_ry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	public void unLockBb(String bbid) {
		Connection conn = DBUtil.getConnection();
		//User user = ActionContext.getCurrentUserInThread();
		try {
			//String sql = "update jlbb t set t.status = '50',t.UPDATED_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where t.jlbb_uid = '"+ bbid+"'";
			String sql = "update jlbb t set t.status = '50',t.UPDATED_DATE=sysdate where t.jlbb_uid = '"+ bbid+"'";
			DBUtil.exec(conn, sql);				//解锁报备
			sql = "insert into jly_status_info "
			  +" (jly_status_info_uid,  jl_person_uid, " 
			  +" old_status, new_status, change_date, reason, jlbb_uid, jlbb_jly_uid) "
			  +" select jly_status_info_uid.nextval, jl_person_uid,1,0, "
			  +" sysdate,'报备解锁',jlbb_uid,jlbb_jly_uid   from jlbb_jly "
			  +" where jlbb_uid = '"+bbid +"' and status = 1 and YOUXIAO_Y='Y'";
			DBUtil.exec(conn, sql);			//解锁人员的日志信息
			//sql = "update jlbb_jly t set status = '0',t.CHANGE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where jlbb_uid = '"+bbid+"'";
			sql = "update jlbb_jly t set status = '0',t.UPDATED_DATE=sysdate where jlbb_uid = '"+bbid+"'";
			DBUtil.exec(conn, sql);			//解锁人员状态
		} catch (Exception e) {
			DaoException.handleException("**********解锁报备*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
	}

	public void unLockBbry(String bbid) {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			//String sql = "update jlbb_jly t set status = '0',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where sgbb_ry_uid = '"+bbid+"'";
			String sql = "update jlbb_jly t set status = '0',t.CHANGE_DATE=sysdate, where sgbb_ry_uid = '"+bbid+"'";
			DBUtil.exec(conn, sql);			//解锁人员状态
			
			sql = "insert into jly_status_info "
			  +" (jly_status_info_uid,   jl_person_uid, " 
			  +" old_status, new_status, change_date, reason, jlbb_uid, jlbb_jly_uid) "
			  +" select jly_status_info_uid.nextval, jl_person_uid,1,0, "
			  +" sysdate,'报备解锁',jlbb_uid,jlbb_jly_uid   from jlbb_jly "
			  +" where jlbb_jly_uid = '"+bbid+"'" ;
			DBUtil.exec(conn, sql);			//解锁人员的日志信息
			
			//验证是否所有人员都已解锁,是则更新报备状态
			sql = "select count(*) from jlbb_jly a where a.jlbb_uid = ("
				+ " select b.jlbb_uid from jlbb_jly b where b.jlbb_jly_uid = '"+bbid
				+ "' ) and a.status = '1' and a.YOUXIAO_Y='Y'";
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				if ("0".equals(res[0][0])) {
					sql = "update jlbb t set t.status = '50',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where t.jlbb_uid = (select b.jlbb_uid from jlbb_jly b where b.jlbb_jly_uid = '"+bbid+"')";
					DBUtil.exec(conn, sql);	
				}
			}
			
		} catch (Exception e) {
			DaoException.handleException("**********解锁报备人员*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
	}

	public void personLock(String bbuid) {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			//String sql = "update jlbb_jly t set status = '1',t.UPDATE_DATE=sysdate,t.UPDATE_NAME='"+user.getName()+"',t.UPDATE_UID='"+user.getAccount()+"' where sgbb_ry_uid = '"+bbuid+"'";
			String sql = "update jlbb_jly t set status = '1',t.UPDATE_DATE=sysdate where jlbb_jly_uid = '"+bbuid+"'";
			DBUtil.exec(conn, sql);			//锁定人员状态
			
			sql = "insert into jly_status_info "
			  +" (jly_status_info_uid,  jl_person_uid, " 
			  +" old_status, new_status, change_date, reason, jlbb_uid, jlbb_jly_uid) "
			  +" select jly_status_info_uid.nextval,jl_person_uid,0,1, "
			  +" sysdate,'人员单个锁定',jlbb_uid,jlbb_jly_uid   from jlbb_jly "
			  +" where jlbb_jly_uid = "+bbuid ;
			DBUtil.exec(conn, sql);			//解锁人员的日志信息
			
			
		} catch (Exception e) {
			DaoException.handleException("**********项目已完成，锁定人员*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
	}

	public JasperPrint query4Print(String bbid, String parpentPath,String childPath) {
		Connection conn = DBUtil.getConnection();
		List<?> datalist = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT SR.XUHAO, ");
			sql.append("        (SELECT G.NAMES FROM GANGWEI G WHERE G.GANGWEI_UID = SR.GANGWEI_UID) AS GW, ");
			sql.append("        SR.JL_NAME, ");
			sql.append("        SR.ZHENGSHU_NAME, ");
			sql.append("        SR.ZHENGSHU_CODE, ");
			sql.append("        SR.ZHUANYE, ");
			sql.append("        SR.AGE, ");
			sql.append("        SR.ZHICHENG_NAME, ");
			sql.append("        SR.SHENFENZHENG ");
			sql.append("   FROM JLBB_JLY SR, JLBB S ");
			sql.append("  WHERE SR.JLBB_UID = S.JLBB_UID ");
			sql.append("    AND S.JLBB_UID = 111 ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT LEVEL XUHAO, ");
			sql.append("        NULL  GS, ");
			sql.append("        NULL  SG_NAME, ");
			sql.append("        NULL  ZHENGSHU_NAME, ");
			sql.append("        NULL  ZHENGSHU_CODE, ");
			sql.append("        NULL  ZHUANYE, ");
			sql.append("        NULL  AGE, ");
			sql.append("        NULL  ZHICHENG_NAME, ");
			sql.append("        NULL  SHENFENZHENG ");
			sql.append("   FROM DUAL ");
			sql.append("  WHERE LEVEL > (SELECT COUNT(*) ");
			sql.append("                   FROM JLBB_JLY ");
			sql.append("                  WHERE JLBB_UID = '"+ bbid +"' ");
			sql.append("                    AND STATUS = 1) ");
			sql.append("    AND LEVEL <= 18 ");
			sql.append(" CONNECT BY LEVEL <= 18 ");

			datalist = DBUtil.queryReturnList(conn, sql.toString());
			// 空的 MAP 装载数据 的容器
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 给父表传入子表
			map.put("SUBREPORT_DIR", childPath);
			Map<Object, Object> REPORT_LIST = new HashMap<Object, Object>();
			REPORT_LIST.put("datalist", datalist);
			map.put("REPORT_LIST", REPORT_LIST); 
			
			
			// 给报表绑定数据源
//			map.put("REPORT_CONNECTION", connection);
			// 设置报表最多为多少行
			map.put("REPORT_MAX_COUNT", 19);
			map.put("id", bbid);
			JRBeanCollectionDataSource jar = new JRBeanCollectionDataSource(findHeaderPrint(bbid));
			// 装载数据 并 生成 文件
			JasperPrint fillReport = JasperFillManager.fillReport(parpentPath, map, jar);
			System.out.println("加载数据文件成功！");
			return fillReport;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 查询表单头部信息
	 * 
	 * @author 余健
	 * @return 表单头部信息的MAP对象
	 */
	public List<?> findHeaderPrint(String bbid) {
		Connection conn = DBUtil.getConnection();
		List<?> map = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select decode(jl.bid_type, 1,'公开招标',2,'邀请招标',3,'直接发包') as bid_type, jl.PROJECT_CODE,  ");
			sql.append(" jl.project_name,jl.bb_code,  ");
			sql.append(" (select gt.names from gc_type gt where gt.gc_type_uid = jl.gc_type_uid) as gctype,  ");
			sql.append(" (select gt.names from gc_type gt where gt.gc_type_uid = jl.gc_sub_type_uid) as gcztype,  ");
			sql.append(" (select dj.names from gc_dengji dj where dj.gc_dengji_uid = jl.gc_dengji_uid) as gcdj ,jl.guimo,JLRY_NUMS ");
			sql.append("  ,el.company_name,el.faren,decode(el.zhu_dengji, 1,'甲级',2,'乙级',3,'丙级') as zhu_dengji  ");
			sql.append("  ,(select z.zizhi_name from zizhi z where z.zizhi_uid = el.zhu_zizhi) as zhu_zizhi ");
			sql.append(" from jlbb jl ");
			sql.append(" left join enterprise_library el ");
			sql.append(" on jl.jl_company_uid = el.jl_company_uid   ");
			sql.append(" where jl.jlbb_uid = '"+bbid+"' ");
			
			map =  DBUtil.queryReturnList(conn, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}

	public Object findTablePrint(String bbid) {
		Connection conn = DBUtil.getConnection();
		List<?> datalist = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT SR.XUHAO, ");
			sql.append("        (SELECT G.NAMES FROM GANGWEI G WHERE G.GANGWEI_UID = SR.GANGWEI_UID) AS GW, ");
			sql.append("        SR.JL_NAME, ");
			sql.append("        SR.ZHENGSHU_NAME, ");
			sql.append("        SR.ZHENGSHU_CODE, ");
			sql.append("        SR.ZHUANYE, ");
			sql.append("        SR.AGE, ");
			sql.append("        SR.ZHICHENG_NAME, ");
			sql.append("        SR.SHENFENZHENG ");
			sql.append("   FROM JLBB_JLY SR, JLBB S ");
			sql.append("  WHERE SR.JLBB_UID = S.JLBB_UID ");
			sql.append("    AND S.JLBB_UID ='"+bbid+"'");
			sql.append(" UNION ALL ");
			sql.append(" SELECT LEVEL XUHAO, ");
			sql.append("        NULL  GS, ");
			sql.append("        NULL  SG_NAME, ");
			sql.append("        NULL  ZHENGSHU_NAME, ");
			sql.append("        NULL  ZHENGSHU_CODE, ");
			sql.append("        NULL  ZHUANYE, ");
			sql.append("        NULL  AGE, ");
			sql.append("        NULL  ZHICHENG_NAME, ");
			sql.append("        NULL  SHENFENZHENG ");
			sql.append("   FROM DUAL ");
			sql.append("  WHERE LEVEL > (SELECT COUNT(*) ");
			sql.append("                   FROM JLBB_JLY ");
			sql.append("                  WHERE JLBB_UID = '"+ bbid +"' ");
			sql.append("                    AND STATUS = 1) ");
			sql.append("    AND LEVEL <= 18 ");
			sql.append(" CONNECT BY LEVEL <= 18 ");

			datalist = DBUtil.queryReturnList(conn, sql.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		
		return datalist;
	}
	
}
