/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.gongcheng.ProjectsGongchengDao.java
 * 创建日期： 2014-10-16 下午 04:22:49
 * 功能：   施工内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-16 下午 04:22:49  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.gongcheng.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.net.aso.g;

import org.springframework.stereotype.Component;

import com.ccthanking.business.gongcheng.dao.ProjectsGongchengDao;
import com.ccthanking.business.project.vo.ProjectsGongchengVO;
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
 * <p> ProjectsGongchengDao.java </p>
 * <p> 功能：施工内容 </p>
 *
 * <p><a href="ProjectsGongchengDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-16
 * 
 */

@Component
public class ProjectsGongchengDaoImpl  extends BsBaseDaoTJdbc implements ProjectsGongchengDao {

    public String queryCondition(String json, ProjectsGongchengVO vo, Map map){    
    	User user = ActionContext.getCurrentUserInThread();   
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	BaseResultSet bs=null;
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            if(map.get("type").toString().equals("list")){
            	if(map.get("status").equals("")){ 
            		condition +=" and GC_STATUS in (3)"; //无效		
            	}else{
                    condition +=" and GC_STATUS in ("+map.get("status")+")";
            	}
            }
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            

            if(map.get("type").equals("list")){
            	String sql = "select * from( select distinct '' ajy,'' zjy,'' syfz,'' dj,el.company_name JL_COMPANY_NAME," +
                		"(select ry.SG_NAME from sgbb_ry ry where ry.sgbb_uid=b.sgbb_uid and GANGWEI_UID='19') XMJL," +
                		"(select ry.SG_NAME from sgbb_ry ry where ry.sgbb_uid=b.sgbb_uid and GANGWEI_UID='10') ZJ," +
                		"g.*,j.COMPANY_name,j.JS_COMPANY_UID,s.COMPANY_NAME as SG_COMPANY_NAME,p.PROJECTS_NAME " +
                		"from projects_gongcheng g " +
                		"left join dt_gc_sgbb d on g.GONGCHENG_UID=d.GONGCHENG_UID " +
                		"left join sgbb b on d.SGBB_UID=b.SGBB_UID " +
                		"left join sgbb_ry r on b.sgbb_uid=r.sgbb_uid " +
                		"left join projects p on p.projects_uid=g.projects_uid " +
                		"left join js_company j on p.js_company_uid=j.js_company_uid " +
                		"LEFT JOIN DT_GC_JLBB JBB ON JBB.GONGCHENG_UID=g.GONGCHENG_UID " +
                		"LEFT JOIN JLBB JLB ON JLB.JLBB_UID=JBB.JLBB_UID " +
                		"LEFT JOIN JLBB_JLY JLY ON JLB.JLBB_UID = JLY.JLBB_UID " +
                		"LEFT JOIN ENTERPRISE_LIBRARY EL ON EL.JL_COMPANY_UID = JLB.JL_COMPANY_UID and el.status='1' " +
                		"left join SG_ENTERPRISE_LIBRARY s on s.SG_COMPANY_UID = b.SG_COMPANY_UID where  g.status='10' ) ";
            	 bs = DBUtil.query(conn, sql, page);
            }else{
            String sql = "SELECT * FROM (  SELECT G.*,(SELECT P.PROJECTS_NAME FROM PROJECTS P WHERE P.PROJECTS_UID=G.PROJECTS_UID) AS PROJECTS_NAME,(SELECT U.USER_NAME FROM USERS U WHERE TO_CHAR(U.USERS_UID)=G.SHENHE_REN) AS SHENHE_NAME,(SELECT F.PROJECTS_CODE FROM PROJECTS F WHERE F.PROJECTS_UID=G.PROJECTS_UID ) AS PROJECTS_CODE  FROM PROJECTS_GONGCHENG G  )";
             bs = DBUtil.query(conn, sql, page);
            }
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            bs.setFieldDic("CB_XINGZHI", "SG_CBXZ");
            bs.setFieldDic("BID_TYPE", "SG_FBFS");
            bs.setFieldDic("NEIRONG", "SGNR");
            

            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("UPDATE_DATE", "yyyy-MM-dd");
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
    
    public String querygc(String json, ProjectsGongchengVO vo,String gcid){
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
    		BaseResultSet bs=null;
    		// 组织查询条件
    		PageManager page = RequestUtil.getPageManager(json);
    		String condition = RequestUtil.getConditionList(json).getConditionWhere();
    		String orderFilter = RequestUtil.getOrderFilter(json);
    		condition += BusinessUtil.getCommonCondition(user, null);
    		if(gcid!=null && !"".equals(gcid)){
    			String gcidstr="";
    			String gcidnum[] = gcid.split(",");
    			for (int i = 0; i < gcidnum.length; i++) {
    				gcidstr+="\'"+gcidnum[i]+"\',";
    			}
    			gcidstr = gcidstr.substring(0, gcidstr.length()-1);
    			condition +=" and pg.gongcheng_uid in ("+gcidstr+")";
    		}
    		condition +=" and pg.gc_status = '0'";
    		condition += orderFilter;
    		if (page == null)
    			page = new PageManager();
    		page.setFilter(condition);
			String sql = "select distinct (pg.gongcheng_uid),pg.gongcheng_name gcmc," +
					"p.projects_name xmmc,j.company_name jsdw,sg.company_name sgdw," +
					"sgpl.person_name xmjl,sgpl.phone xmjlphone,pl.person_name zj," +
					"pl.phone zjphone from projects_gongcheng pg " +
					"left join projects p on pg.projects_uid = p.projects_uid " +
					"left join js_company j on p.js_company_uid = j.js_company_uid " +
					"left join dt_gc_sgbb dtsg on pg.gongcheng_uid = dtsg.gongcheng_uid " +
					"left join sgbb s on dtsg.sgbb_uid = s.sgbb_uid " +
					"left join sg_enterprise_library sg on s.sg_company_uid = sg.sg_company_uid " +
					"left join (select jlrys.gongcheng_uid, jlys.jl_person_uid from " +
					"dt_gc_jlry jlrys, jlbb_jly jlys where jlrys.jlbb_jly_uid = jlys.jlbb_jly_uid and jlys.gangwei_uid = '10') zjs " +
					"on zjs.gongcheng_uid = pg.gongcheng_uid " +
					"left join person_library pl on zjs.jl_person_uid = pl.jl_person_uid and pl.status = '1' " +
					"left join (select dtsgry.gongcheng_uid, dtsgry.sg_person_uid " +
					"from dt_gc_sgry dtsgry, sgbb_ry sgry where dtsgry.sgbb_ry_uid = sgry.sgbb_ry_uid and sgry.gangwei_uid = '19') xmjl " +
					"on xmjl.gongcheng_uid = pg.gongcheng_uid " +
					"left join sg_person_library sgpl on xmjl.sg_person_uid = sgpl.sg_person_uid and sgpl.status = '1'";
			bs = DBUtil.query(conn, sql, page);
//    		bs.setFieldDic("CB_XINGZHI", "SG_CBXZ");
//    		bs.setFieldDic("BID_TYPE", "SG_FBFS");
//    		bs.setFieldDic("NEIRONG", "SGNR");
//    		
//    		
//    		bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
//    		bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm:ss");
//    		bs.setFieldDateFormat("UPDATE_DATE", "yyyy-MM-dd");
    		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    public String queryAllGc(String json, ProjectsGongchengVO vo, Map map){
        
        User user = ActionContext.getCurrentUserInThread();
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += " and g.STATUS in ('10','1') and s.STATUS='1'  ";
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "select g.*,j.COMPANY_name,j.JS_COMPANY_UID,s.COMPANY_NAME as SG_COMPANY_NAME from projects_gongcheng g " +
            		"left join dt_gc_sgbb d on g.GONGCHENG_UID=d.GONGCHENG_UID " +
            		"left join sgbb b on d.SGBB_UID=b.SGBB_UID " +
            		"left join projects p on p.projects_uid=g.projects_uid " +
            		"left join js_company j on p.js_company_uid=j.js_company_uid " +
            		"left join SG_ENTERPRISE_LIBRARY s on s.SG_COMPANY_UID = b.SG_COMPANY_UID " ;
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
    public String queryStatusNums(String projects_uid) {
		Connection conn = DBUtil.getConnection();
		String num = "";
		JSONArray arr = new JSONArray();
		try {
			String sql="";
//			if(projects_uid=="null"||projects_uid==null){
				sql="SELECT COUNT(*),s.status from PROJECTS_GONGCHENG S group by s.status ";
//			}else{
//			 sql="SELECT COUNT(*),S.STATUS FROM PROJECTS_GONGCHENG S WHERE S.PROJECTS_UID='"+projects_uid+"'  GROUP BY  S.STATUS,S.PROJECTS_UID";
//			}
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				
				for (int i = 0; i < res.length; i++) {
					JSONObject obj = new JSONObject();
					obj.put("nums", res[i][0]);
					obj.put("status", res[i][1]);
					
					arr.add(obj);
				}
			}
			
			num = arr.toString();
		} catch (Exception e) {
			DaoException.handleMessageException("*********获得提交次数出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return num;
	}
    
    //安监员查询
    public String getAJY(){
    	User user = ActionContext.getCurrentUserInThread();	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	StringBuffer sql=new StringBuffer();
        	sql.append("select t.user_uid, org_name");
        	sql.append(" from ORGANIZE t");
        	sql.append(" where t.p_organize_uid in (select organize_uid");
        	sql.append(" from organize");
        	sql.append(" where org_type = 'D'");
        	sql.append(" and code = 'AJ')");
        	sql.append(" and t.org_type = 'U'");
        	sql.append(" and t.code like '%AJ%'");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);        
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    
    //质监员查询
    public String getZJY(){
    	User user = ActionContext.getCurrentUserInThread();	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	StringBuffer sql=new StringBuffer();
        	sql.append("select t.user_uid, org_name");
        	sql.append(" from ORGANIZE t");
        	sql.append(" where t.p_organize_uid in (select organize_uid");
        	sql.append(" from organize");
        	sql.append(" where org_type = 'D'");
        	sql.append(" and code = 'AJ')");
        	sql.append(" and t.org_type = 'U'");
        	sql.append(" and t.code like '%ZJ%'");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);        
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

    //施工工地首页的查询条件
	public String queryCondition2(String json,ProjectsGongchengVO vo ,Map map) {
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
            
            String status=(String) map.get("status");
            String AJ_uid=(String) map.get("AJ_uid");
            String ZJ_uid=(String) map.get("ZJ_uid");
            String order=(String) map.get("order");
            
            StringBuffer sql = new StringBuffer();
            sql.append(" select * from ( ");
            sql.append(" select G.GONGCHENG_UID,  ");
            sql.append(" p.projects_uid,");
            sql.append(" gc_package.get_jsdw_uid(g.projects_uid) jsdw_uid,");
            sql.append(" gc_package.get_jsdw_name(g.projects_uid) jsdw,");
            sql.append(" p.projects_name,");
            sql.append(" g.projects_uid || gc_package.get_project_ajzj(g.projects_uid, 1) prj_ajy,");
            sql.append(" gc_package.get_project_ajzj(g.projects_uid, 1) ajy,");
            sql.append(" g.projects_uid || gc_package.get_project_ajzj(g.projects_uid, 2) prj_zjy,");
            sql.append(" gc_package.get_project_ajzj(g.projects_uid, 2) zjy,");
            sql.append(" gc_package.get_sgdw(g.gongcheng_uid) sgdw,");
            sql.append(" gc_package.get_xmjl(g.gongcheng_uid) xmjl, g.gongcheng_name,");
            sql.append(" gc_package.get_jldw(g.gongcheng_uid) jldw,");
            sql.append(" gc_package.get_zj(g.gongcheng_uid) zj,");
            sql.append(" decode(g.gc_status, -1, '未开工', 0, '在建', 1, '完工', 2, '竣工') gc_status,");
            sql.append(" GC_PACKAGE.GET_GC_SCORE(G.GONGCHENG_UID, ADD_MONTHS(sysdate, -1)) SCORE,");
            sql.append(" G.GC_DENGJI, GC_PACKAGE.GET_GC_UPDATE_INFO(G.GONGCHENG_UID) LAST_INFO,");
            sql.append(" g.kq_status");
            sql.append(" from projects_gongcheng g, projects p");
            sql.append(" where g.projects_uid = p.projects_uid");
            
            //添加开工状态
            if(status!=null&&status!=""){
            	sql.append(" and g.gc_status in ("+status+")");
            }else{
            	sql.append(" and 1=2");
            }
            
            //是否是安监
            if(AJ_uid!=null&&AJ_uid!=""){
            	sql.append(" and exists (select 1");
                sql.append(" from projects_ajzj pa");
                sql.append(" where pa.jiandu_type = 1");
            	sql.append(" and pa.users_uid = "+AJ_uid);
            	sql.append(" and pa.projects_uid = p.projects_uid)");
            }
            
            //是否是质监
            if(ZJ_uid!=null&&ZJ_uid!=""){
            	sql.append(" and exists (select 1");
                sql.append(" from projects_ajzj pa");
                sql.append(" where pa.jiandu_type = 2");
            	sql.append(" and pa.users_uid = "+ZJ_uid);
            	sql.append(" and pa.projects_uid = p.projects_uid)");
            }
            
            //排序
            sql.append(" order by");
            //选择时间排序
            if("time".equals(order)){
            	sql.append(" gc_package.get_prj_last_update(g.projects_uid),");	
            }
            sql.append("  P.JS_COMPANY_UID desc, P.PROJECTS_NAME, P.PROJECTS_UID desc,");
            sql.append("  g.created_date desc, g.rowid");           
            sql.append(" )");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);        
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	
	//施工工地首页的查询条件
		public String queryCondition3(String json,String condition3,String AJZ_UID) {		
			
			User user = ActionContext.getCurrentUserInThread();	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {
	            // 组织查询条件
	            PageManager page = RequestUtil.getPageManager(json);
	            String condition = RequestUtil.getConditionList(json).getConditionWhere();
	            //通过condition添加条件
	            if("yqmtgwfgxm".equals(condition3)){
	            	condition +=" and zg_xingzhi_uid = 3";
	            	condition +=" and sh_status <> 1";
	            }else if("yjbtgwfgxm".equals(condition3)){
	            	condition +=" and zg_xingzhi_uid = 2";
	            	condition +=" and sh_status <> 1";
	            }
	            
	            if(!"".equals(AJZ_UID)&&AJZ_UID!=null){
					condition +=" and sgdt_package.is_team_project(pgz.projects_uid,"+AJZ_UID+")>0 ";
				}
				
	            String orderFilter = RequestUtil.getOrderFilter(json);           
	            condition += orderFilter;  
	            if (page == null)
	                page = new PageManager();         
	            page.setFilter(condition);
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append(" select distinct * from ( ");
	            sql.append(" select G.GONGCHENG_UID,");
	            sql.append(" p.projects_uid,");
	            sql.append(" gc_package.get_jsdw_uid(g.projects_uid) jsdw_uid,");
	            sql.append(" p.projects_name,");	            
	            sql.append(" gc_package.get_jsdw_name(g.projects_uid) jsdw,");
	            sql.append(" gc_package.get_sgdw(g.gongcheng_uid) sgdw,");
	            sql.append(" gc_package.get_jldw(g.gongcheng_uid) jldw,");
	            sql.append(" gc_package.get_project_ajzj(g.projects_uid, 1) ajy,");	            
	            sql.append(" gc_package.get_project_ajzj(g.projects_uid, 2) zjy,");
	            sql.append(" zt.created_date ztcreated_date,");
	            sql.append(" gc_package.get_gc_update_info(g.gongcheng_uid) gc_info,");
				sql.append(" zt.zg_xingzhi_uid,");
				sql.append(" zd.sh_status");
	            
	            sql.append(" from projects p");
	            sql.append(" left join projects_gongcheng g");
				sql.append(" on p.projects_uid = g.projects_uid");
				sql.append(" left join gc_update_info gi");
				sql.append(" on gi.gongcheng_uid = g.gongcheng_uid");
				sql.append(" left join zg_tzd zt");
				sql.append(" on g.gongcheng_uid = zt.gongcheng_uid");
				sql.append(" left join zg_dafu zd");
				sql.append(" on zt.zg_tzd_uid = zd.zg_tzd_uid");
				
	            //排序
	            sql.append(" order by");
	            sql.append(" gc_package.get_prj_last_update(g.projects_uid),");
	            sql.append(" P.JS_COMPANY_UID desc, P.PROJECTS_NAME, P.PROJECTS_UID desc,");
	            sql.append(" g.created_date desc, g.rowid");           
	            sql.append(" ) pgz");
	            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);        
	            domresult = bs.getJson();	            
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return domresult;
		}
		
		//安监部门查询
	    public String queryAjDept(){
	    	User user = ActionContext.getCurrentUserInThread();	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {
	        	String sql ="select ORGANIZE_UID,ORG_NAME from organize where code like '%AJ%' and org_type = 'D'";
	            BaseResultSet bs = DBUtil.query(conn, sql, null);        
	            domresult = bs.getJson();
	            
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return domresult;
		}

	
}
