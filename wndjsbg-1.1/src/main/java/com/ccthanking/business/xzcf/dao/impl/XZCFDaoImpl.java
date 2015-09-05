package com.ccthanking.business.xzcf.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Db;
import org.springframework.stereotype.Repository;

import com.ccthanking.business.xzcf.dao.XZCFDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.copj.modules.utils.exception.DaoException;

@Repository
public class XZCFDaoImpl extends BsBaseDaoTJdbc implements XZCFDao{

	public String queryCondition(String gcuid) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
           String sql="select c.js_company_uid, c.company_name, 'JSDW' duixiang_type, c.faren, "+
					       "c.faren_mobile, c.fzr lianxiren, c.fzr_mobile lianxiren_mobile, c.address, "+ 
					       "c.postcode, c.fax, 1 xuhao "+
					 " from projects_gongcheng g, projects p, js_company c "+
					 "where g.projects_uid = p.projects_uid "+
					   "and p.js_company_uid = c.js_company_uid "+
					   "and g.gongcheng_uid = 69 "+
					"union "+
					"select s.sg_company_uid, e.company_name, 'SGDW' duixiang_type, e.faren, "+
					       "e.faren_mobile, e.lianxiren, e.lianxiren_mobile, e.address, e.postcode, e.fax, "+
					       "2 xuhao "+
					  "from projects_gongcheng g, dt_gc_sgbb gs, sgbb s, sg_enterprise_library e "+
					 "where g.gongcheng_uid = gs.gongcheng_uid "+
					   "and gs.sgbb_uid = s.sgbb_uid "+
					   "and s.sg_company_uid = e.sg_company_uid "+
					   "and e.status = 1 "+
					   "and g.gongcheng_uid = "+gcuid+ 
					" union "+
					"select j.jl_company_uid, e.company_name, 'JLDW' duixiang_type, e.faren, "+
					       "e.faren_mobile, e.lianxiren, e.lianxiren_mobile, e.address, e.postcode, e.fax, "+
					       "3 xuhao "+
					  "from projects_gongcheng g, dt_gc_jlbb gj, jlbb j, enterprise_library e "+
					 "where g.gongcheng_uid = gj.gongcheng_uid "+
					   "and gj.jlbb_uid = j.jlbb_uid "+
					   "and j.jl_company_uid = e.jl_company_uid "+
					   "and e.status = 1 "+
					   "and g.gongcheng_uid ="+gcuid+ 
					" union "+
					"select gs.sg_person_uid, p.person_name, 'XMJL' duixiang_type, null, null, null, "+
					      " p.phone, p.address, null, null, 4 xuhao "+
					  "from projects_gongcheng g, dt_gc_sgry gs, sgbb_ry r, gangwei w, sg_person_library p "+
					 "where g.gongcheng_uid = gs.gongcheng_uid "+
					   "and gs.sgbb_ry_uid = r.sgbb_ry_uid "+
					   "and r.gangwei_uid = w.gangwei_uid "+
					   "and gs.sg_person_uid = p.sg_person_uid "+
					   "and w.codes = 'XMJL' "+
					   "and p.status = 1 "+
					   "and g.gongcheng_uid = "+gcuid+ 
					" union "+
					"select gj.jl_person_uid, p.person_name, 'ZJ' duixiang_type, null, null, null, p.phone, "+
					      "p.address, null, null, 5 xuhao "+
					  "from projects_gongcheng g, dt_gc_jlry gj, jlbb_jly r, gangwei w, person_library p "+
					 "where g.gongcheng_uid = gj.gongcheng_uid "+
					   "and gj.jlbb_jly_uid = r.jlbb_jly_uid "+
					   "and r.gangwei_uid = w.gangwei_uid "+
					   "and gj.jl_person_uid = p.jl_person_uid "+
					   "and w.codes = 'ZJ' "+
					   "and p.status = 1 "+
					   "and g.gongcheng_uid = "+gcuid+ 
					 " order by xuhao ";

            BaseResultSet bs = DBUtil.query(conn, sql,null);
            //如果需要的话，转换时间格式
            bs.setFieldDateFormat("BL_DIAOCHA_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("UPDATED_DATE", "yyyy-MM-dd HH:mm:ss");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	}

	//删除 笔录问答表中 的记录
	public boolean delete(Connection conn,String xzcfuid) {
		User user = ActionContext.getCurrentUserInThread();//当前用户
		boolean flag = true;
		try {
	        DBUtil.exec("delete BILU_WENDA  where XZCF_UID ="+xzcfuid);
		} catch (Exception e) {
			flag = false;
			 DaoException.handleMessageException("*********删除笔录问答记录出错!*********");
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}

	public String getById(String XINFANG_UID) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select * from XINFANG where XINFANG_UID = "+XINFANG_UID;
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			
			//如果需要的话，转换时间格式 
			//设置 想要的日期格式: "yyyy/MM/dd EE" 日期+星期几 
            bs.setFieldDateFormat("XF_DATE", "yyyy/MM/dd HH:mm:ss");
            bs.setFieldDateFormat("XC_DATE", "yyyy/MM/dd HH:mm:ss");
            bs.setFieldDateFormat("ZG_DATE", "yyyy/MM/dd HH:mm:ss");
			domresult = bs.getJson();
		} catch (Exception e){
			 DaoException.handleMessageException("*********查询出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String queryDXTYPE(String gcuid, String jsuid) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

           String sql="select * from ("+
           		      " select c.js_company_uid, c.company_name, 'JSDW' duixiang_type, c.faren, "+
					       "c.faren_mobile, c.fzr lianxiren, c.fzr_mobile lianxiren_mobile, c.address, "+ 
					       "c.postcode, c.fax, 1 xuhao "+
					 " from projects_gongcheng g, projects p, js_company c "+
					 "where g.projects_uid = p.projects_uid "+
					   "and p.js_company_uid = c.js_company_uid "+
					   "and g.gongcheng_uid = 69 "+
					"union "+
					"select s.sg_company_uid, e.company_name, 'SGDW' duixiang_type, e.faren, "+
					       "e.faren_mobile, e.lianxiren, e.lianxiren_mobile, e.address, e.postcode, e.fax, "+
					       "2 xuhao "+
					  "from projects_gongcheng g, dt_gc_sgbb gs, sgbb s, sg_enterprise_library e "+
					 "where g.gongcheng_uid = gs.gongcheng_uid "+
					   "and gs.sgbb_uid = s.sgbb_uid "+
					   "and s.sg_company_uid = e.sg_company_uid "+
					   "and e.status = 1 "+
					   "and g.gongcheng_uid = "+gcuid+ 
					" union "+
					"select j.jl_company_uid, e.company_name, 'JLDW' duixiang_type, e.faren, "+
					       "e.faren_mobile, e.lianxiren, e.lianxiren_mobile, e.address, e.postcode, e.fax, "+
					       "3 xuhao "+
					  "from projects_gongcheng g, dt_gc_jlbb gj, jlbb j, enterprise_library e "+
					 "where g.gongcheng_uid = gj.gongcheng_uid "+
					   "and gj.jlbb_uid = j.jlbb_uid "+
					   "and j.jl_company_uid = e.jl_company_uid "+
					   "and e.status = 1 "+
					   "and g.gongcheng_uid ="+gcuid+ 
					" union "+
					"select gs.sg_person_uid, p.person_name, 'XMJL' duixiang_type, null, null, null, "+
					      " p.phone, p.address, null, null, 4 xuhao "+
					  "from projects_gongcheng g, dt_gc_sgry gs, sgbb_ry r, gangwei w, sg_person_library p "+
					 "where g.gongcheng_uid = gs.gongcheng_uid "+
					   "and gs.sgbb_ry_uid = r.sgbb_ry_uid "+
					   "and r.gangwei_uid = w.gangwei_uid "+
					   "and gs.sg_person_uid = p.sg_person_uid "+
					   "and w.codes = 'XMJL' "+
					   "and p.status = 1 "+
					   "and g.gongcheng_uid = "+gcuid+ 
					" union "+
					"select gj.jl_person_uid, p.person_name, 'ZJ' duixiang_type, null, null, null, p.phone, "+
					      "p.address, null, null, 5 xuhao "+
					  "from projects_gongcheng g, dt_gc_jlry gj, jlbb_jly r, gangwei w, person_library p "+
					 "where g.gongcheng_uid = gj.gongcheng_uid "+
					   "and gj.jlbb_jly_uid = r.jlbb_jly_uid "+
					   "and r.gangwei_uid = w.gangwei_uid "+
					   "and gj.jl_person_uid = p.jl_person_uid "+
					   "and w.codes = 'ZJ' "+
					   "and p.status = 1 "+
					   "and g.gongcheng_uid = "+gcuid+ 
					 " order by xuhao ) xinxi WHERE xinxi.JS_COMPANY_UID="+jsuid;
            BaseResultSet bs = DBUtil.query(conn, sql,null);
            //如果需要的话，转换时间格式
            bs.setFieldDateFormat("BL_DIAOCHA_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("UPDATED_DATE", "yyyy-MM-dd HH:mm:ss");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询选择对象类型出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public String queryReportxx() {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select o.organize_uid,o.org_name from ORGANIZE o "+
                         "where o.ORG_TYPE='U' AND o.ZJ_CODE is not null and o.CODE is not null";
			BaseResultSet bs = DBUtil.query(conn,sql,null);
		
			domresult = bs.getJson();
		} catch (Exception e){
			 DaoException.handleMessageException("*********查询调查人员信息出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public List<?> findHeaderPrint(String xzcfuid) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		List<?> map = null;
		try {
			String sql = "select * from xzcf  where xzcf_uid ="+xzcfuid;
		  /* BaseResultSet bs = DBUtil.query(conn,sql,null);
			
			 bs.setFieldDateFormat("BL_DIAOCHA_DATE", "yyyy-MM-dd");
	         bs.setFieldDateFormat("CREATED_DATE", "YYYY-MM-dd ");
	         bs.setFieldDateFormat("UPDATED_DATE", "YYYY-MM-dd ");*/
			map =  DBUtil.queryReturnList(conn,sql.toString());
		
			
		   
		} catch (Exception e){
			 DaoException.handleMessageException("*********查询调查人员信息出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return map;
		
	}

	public String queryXzcfMsg(String gcuid) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			
			String sql = "select * from xzcf x where x.gongcheng_uid = "+gcuid+" order by XZCF_UID asc";
			BaseResultSet bs = DBUtil.query(conn,sql,null);
		
			domresult = bs.getJson();
		} catch (Exception e){
			 DaoException.handleMessageException("*********查询行政处罚信息出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
    //修改页面初始化 数据
	public String queryXzcfXX(String xzcfuid) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			String sql = "select * from xzcf  x left join bilu_wenda b "+
						 " on x.xzcf_uid = b.xzcf_uid "+
						 " where x.xzcf_uid ="+xzcfuid +
						 " order by b.bilu_wenda_uid asc,xuhao asc";
			BaseResultSet bs = DBUtil.query(conn,sql,null);
		
			domresult = bs.getJson();
		} catch (Exception e){
			 DaoException.handleMessageException("*********查询行政处罚信息出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	}



	

	

