package com.ccthanking.business.dtgl.dao.impl;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.ccthanking.business.dtgl.dao.GetIndexInformationDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.copj.modules.utils.exception.DaoException;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

@Repository
public class GetIndexInformationDaoImpl implements GetIndexInformationDao {
	
	public String getQuanxian(String userId) {
		
		Connection conn = DBUtil.getConnection();
		StringBuffer domresult = new StringBuffer();		
		String domresult1 = "";		
		String domresult2 = "";		
		String domresult3 = "";		
		String domresult4 = "";		
		String domresult5 = "";		
		try {
			//待审扣分数量
			StringBuffer sql = new StringBuffer();
			sql.append(" select count(*) totals");
			sql.append(" from score s");
			sql.append(" where (s.sh_status = 0 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('3060001', "+userId+") = 1)");
			sql.append(" and s.chuli_type = 'WG'");
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult1 = bs.getJson();
			
			//待审核加分数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from jfsq t, projects_gongcheng g");
			sql.append(" where t.gongcheng_uid = g.gongcheng_uid");
			sql.append(" and sgdt_package.is_my_project(g.projects_uid, "+userId+") > 0");
			sql.append(" and ((t.status = 30 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('3060002', "+userId+") = 1) or");
			sql.append(" (t.status = 50 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('3060003',"+userId+") = 1))");
			bs = DBUtil.query(conn, sql.toString(), null);
			domresult2 = bs.getJson();			
			
			//全面停工整改审核数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from zg_tzd z");
			sql.append(" where z.sh_status = 0");
			sql.append(" and ((z.sh_level = 1 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('4050203',"+userId+") = 1) or");
			sql.append(" (z.sh_level = 2 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('4050204', "+userId+") = 1))");
			sql.append(" and z.zg_xingzhi_uid = 3");
			bs = DBUtil.query(conn, sql.toString(), null);
			domresult3 = bs.getJson();
			
			//局部停工整改审核数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from zg_tzd z");
			sql.append(" where z.sh_status = 0");
			sql.append(" and z.sh_level = 1");
			sql.append(" and quanxian_package.get_bg_user_quanxian('4050202', "+userId+") = 1");
			sql.append(" and z.zg_xingzhi_uid = 2");
			bs = DBUtil.query(conn, sql.toString(), null);
			domresult4 = bs.getJson();
			
			//待审核复工数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from zg_dafu z");
			sql.append(" where z.sh_status = 0");
			sql.append(" and ((z.sh_level = 1 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('4050205',"+userId+") = 1) or");
			sql.append(" (z.sh_level = 2 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('4050206',"+userId+") = 1))");
			bs = DBUtil.query(conn, sql.toString(), null);
			domresult5 = bs.getJson();									
			
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		domresult.append(domresult1);
		domresult.append("&&&&");
		domresult.append(domresult2);
		domresult.append("&&&&");
		domresult.append(domresult3);
		domresult.append("&&&&");
		domresult.append(domresult4);
		domresult.append("&&&&");
		domresult.append(domresult5);
		return domresult.toString();
	}
	
	public String getChartData(String before,String after) {
		
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select t.stzg, t.kqzg, o.org_name,t.dept");
			sql.append(" from (select sum(decode(z.zgd_type, 'Z', 1, 0)) stzg,");
			sql.append(" sum(decode(z.zgd_type, 'K', 1, 0)) kqzg,");
			sql.append(" gc_package.get_dept_uid(z.created_by) dept");
			sql.append(" from zg_tzd z");
			sql.append(" where to_date(z.UPDATED_DATE) >=");
			sql.append(" to_date('"+before+"', ('yyyy-MM-dd'))");
			sql.append(" and to_date(z.UPDATED_DATE) <=");
			sql.append(" to_date('"+after+"', ('YYYY-MM-DD'))");
			sql.append(" group by gc_package.get_dept_uid(z.created_by)) t,");
			sql.append(" organize o");
			sql.append(" where t.dept = o.organize_uid");
			sql.append(" order by o.serial_no");			
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			System.out.println(sql.toString());
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}
	

	//查询整改单
	public String queryZGD() {

		StringBuffer domresult = new StringBuffer();
		String domresult1 = "";
		String domresult2 = "";	
		String domresult3 = "";	
		String domresult4 = "";	
		String domresult5 = "";	
		String domresult6 = "";	
		String domresult7 = "";	
		String domresult8 = "";
		String domresult9 = "";
		String domresult10 = "";
		String domresult11 = "";
		String domresult12 = "";
		String domresult13 = "";
		
		//查询更新整改单
		domresult1 = this.queryZGD_Update("today");
		domresult2 = this.queryZGD_Update("yesterday");
		//queryZGD_Create参数为整改性质，查询限期请传入1，查询局部请传入2，查询全面请传入3
	    //传入其他值为查询所有整改性质的条目
	    domresult3 = this.queryZGD_Create(-1);
	    domresult4 = this.queryZGD_Create(2);
	    domresult5 = this.queryZGD_Create(3);
	  //查询扣分情况：1.今日;2.本周;3.本月;
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int xingqi = cal.get(Calendar.DAY_OF_WEEK);

		cal.clear();
	    //今日
	    String TodayFirstDay = "";
	    String TodayLastDay = "";
	    Date today = new Date();
	    TodayFirstDay = sdf.format(today);
	    TodayLastDay = TodayFirstDay;
	    
	    domresult6 = this.queryKuofen(TodayFirstDay, TodayLastDay);
	    //本周
	    String thisWeekFirstDay = "";
	    String thisWeekLastDay = "";
	    cal.clear();
	    cal.set(Calendar.YEAR,year);
	    
	    if(xingqi!=1){
	    	cal.set(Calendar.WEEK_OF_YEAR,week);
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		    thisWeekFirstDay = sdf.format(cal.getTime());
		    
		    cal.set(Calendar.WEEK_OF_YEAR,week+1);
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		    thisWeekLastDay = sdf.format(cal.getTime());
	    }else if(xingqi==1){
	    	cal.set(Calendar.WEEK_OF_YEAR,week-1);
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		    thisWeekFirstDay = sdf.format(cal.getTime());
		    
		    cal.set(Calendar.WEEK_OF_YEAR,week);
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		    thisWeekLastDay = sdf.format(cal.getTime());
	    }
	    
	    domresult7 = this.queryKuofen(thisWeekFirstDay, thisWeekLastDay);	
	    //本月
	    String thisMonthFirstDay = "";
	    String thisMonthLastDay = "";
	    cal.clear();
	    cal.set(Calendar.YEAR,year);
	    cal.set(Calendar.MONTH, month-1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));
	    thisMonthFirstDay = sdf.format(cal.getTime());
	    
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
	    thisMonthLastDay = sdf.format(cal.getTime());

	    domresult8 = this.queryKuofen(thisMonthFirstDay, thisMonthLastDay);
	    
	    //停工未复工项目数
	    domresult9 = this.queryTGWFGProjects(3);
	    domresult10 = this.queryTGWFGProjects(2);
	    
	    //超时整改单
	    domresult11 = this.queryZGStatus("未闭合");
	    domresult12 = this.queryZGStatus("即将超时未答复");
	    domresult13 = this.queryZGStatus("超时未答复");	    		
		
		domresult.append(domresult1);
		domresult.append("&&&&");
		domresult.append(domresult2);
		domresult.append("&&&&");
		domresult.append(domresult3);
		domresult.append("&&&&");
		domresult.append(domresult4);
		domresult.append("&&&&");
		domresult.append(domresult5);
		domresult.append("&&&&");
		domresult.append(domresult6);
		domresult.append("&&&&");
		domresult.append(domresult7);
		domresult.append("&&&&");
		domresult.append(domresult8);
		domresult.append("&&&&");
		domresult.append(domresult9);
		domresult.append("&&&&");
		domresult.append(domresult10);
		domresult.append("&&&&");
		domresult.append(domresult11);
		domresult.append("&&&&");
		domresult.append(domresult12);
		domresult.append("&&&&");
		domresult.append(domresult13);
		
		return domresult.toString();
	}
	
	
	//查询考勤信息
	public String queryKaoqingInfo() {
		User user = RestContext.getCurrentUser();
		String userUID = user.getUserSN();
		
		Connection conn = DBUtil.getConnection();
		StringBuffer domresult = new StringBuffer();
				
		String MainKaoqingInfo = "";
		
		String Zhuyaorenyuan2Days = "";		
		
		String ZuoriWuyouxiaoKaoQing = "";		
		
		String SgdwGuanjiangangweiWuyouxiaoKaoqing = "";		
		
		String SgdwGuanZhongyaoWuyouxiaoKaoqing = "";		
		
		String SgdwGuanZhuyaoWuyouxiaoKaoqing = "";		
		
		String JldwGuanjiangangweiWuyouxiaoKaoqing = "";	
		
		String JldwGuanZhongyaoWuyouxiaoKaoqing = "";		
		
		String JldwGuanZhuyaoWuyouxiaoKaoqing = "";		
		
		String KaoqingKoufen = "";		
		
		String DaishenKaoqingZanting = "";		
		
		String DaishenKaoqingZhongzhi = "";	
		
		String DaishenFenjieduan = "";		
		
		String KaoqingZhuangtaiBiangeng = "";		
		
		String DaishenJiesuo = "";
		
		String InformationTable = "";
		
		try {
			//重要考勤信息
			StringBuffer sql = new StringBuffer();
			sql.append(" select count(*) totals,");
			sql.append(" sum(k.all_person) all_person,");
			sql.append(" sum(k.zhongyao_person)  zhongyao_person,");
			sql.append(" sum(k.zhuyao_person) zhuyao_person,");
			sql.append(" count(decode(k.twodays_unvalid, 0, null, 1)) twodays_unvalid,");
			sql.append(" sum(k.sg_zhongyao_nums + k.sg_zhuyao_nums + k.jl_zhongyao_nums +");
			sql.append(" k.jl_zhuyao_nums) xukaoqing,");
			sql.append(" sum(k.sg_zhongyao_kq + k.sg_zhuyao_kq + k.jl_zhongyao_kq + k.jl_zhuyao_kq) zr_yikaoqing,");
			sql.append(" sum(k.sg_zhongyao_valid + k.sg_zhuyao_valid + k.jl_zhongyao_valid +");
			sql.append(" k.jl_zhuyao_valid) zr_youxiaokaoqing,");
			sql.append(" sum(k.kq_region_in) kq_region_in,");
			sql.append(" sum(k.kq_region_out) kq_region_out, sum(k.kq_zanting) kq_zanting");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");			
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			MainKaoqingInfo = bs.getJson();
			
			//重要人员缺勤2天项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.twodays_unvalid > 0");
			bs = DBUtil.query(conn, sql.toString(), null);
			Zhuyaorenyuan2Days = bs.getJson();
			
			//昨日无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.sg_zhongyao_valid = 0");
			sql.append(" and k.sg_zhuyao_valid = 0");
			sql.append(" and k.jl_zhongyao_valid = 0");
			sql.append(" and k.jl_zhuyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");
			bs = DBUtil.query(conn, sql.toString(), null);
			ZuoriWuyouxiaoKaoQing = bs.getJson();
			
			
			
			//施工单位关键岗位人员无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.sg_zhongyao_valid = 0");
			sql.append(" and k.sg_zhuyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");

			bs = DBUtil.query(conn, sql.toString(), null);
			SgdwGuanjiangangweiWuyouxiaoKaoqing = bs.getJson();
			
			
			//施工单位重要人员无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.sg_zhongyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");

			bs = DBUtil.query(conn, sql.toString(), null);
			SgdwGuanZhongyaoWuyouxiaoKaoqing = bs.getJson();
			
						
			//施工单位主要人员无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.sg_zhuyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");
			bs = DBUtil.query(conn, sql.toString(), null);
			SgdwGuanZhuyaoWuyouxiaoKaoqing = bs.getJson();
			
			
			//监理单位关键岗位人员无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.jl_zhongyao_valid = 0");
			sql.append(" and k.jl_zhuyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");
			bs = DBUtil.query(conn, sql.toString(), null);
			JldwGuanjiangangweiWuyouxiaoKaoqing = bs.getJson();
			
			
			//监理单位重要人员无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.jl_zhongyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");
			bs = DBUtil.query(conn, sql.toString(), null);
			JldwGuanZhongyaoWuyouxiaoKaoqing = bs.getJson();
			
			
			//监理单位主要人员无有效考勤记录项目
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_day_stat k");
			sql.append(" where k.kq_date = to_date(sysdate - 1)");
			sql.append(" and k.jl_zhuyao_valid = 0");
			sql.append(" and k.gc_status = 'Y'");
			bs = DBUtil.query(conn, sql.toString(), null);
			JldwGuanZhuyaoWuyouxiaoKaoqing = bs.getJson();
			
			
			//目前考勤违规应扣分项目
			sql.setLength(0);
			sql.append(" select count(distinct k.gongcheng_uid) totals");
			sql.append(" from kq_weigui k, projects_gongcheng g");
			sql.append(" where k.gongcheng_uid = g.gongcheng_uid");
			sql.append(" and k.youxiao = 'Y'");
			sql.append(" and sgdt_package.is_my_project(g.projects_uid,"+userUID+") > 0");
			bs = DBUtil.query(conn, sql.toString(), null);
			KaoqingKoufen = bs.getJson();
			
			//待审考勤暂停数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_zanting a, projects_gongcheng g");
			sql.append(" where a.gongcheng_uid = g.gongcheng_uid");
			sql.append(" and a.tags = 'P'");
			sql.append(" and sgdt_package.is_my_project(g.projects_uid, "+userUID+") > 0");
			sql.append(" and ((a.status = 0 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqztyjsh', "+userUID+") = 1) or");
			sql.append(" (a.status = 3 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqztejsh', "+userUID+") = 1))");

			bs = DBUtil.query(conn, sql.toString(), null);
			DaishenKaoqingZanting = bs.getJson();
			
			
			//待审终止考勤数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_zanting a, projects_gongcheng g");
			sql.append(" where a.gongcheng_uid = g.gongcheng_uid");
			sql.append(" and a.tags = 'E'");
			sql.append(" and sgdt_package.is_my_project(g.projects_uid, "+userUID+") > 0");
			sql.append(" and ((a.status = 0 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqzzyjsh', "+userUID+") = 1) or");
			sql.append(" (a.status = 3 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqzzejsh', "+userUID+") = 1) or");
			sql.append(" (a.status = 2 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqzzsjsh', "+userUID+") = 1))");

			bs = DBUtil.query(conn, sql.toString(), null);
			DaishenKaoqingZhongzhi = bs.getJson();
			
			
			//待审分阶段数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_zanting a, projects_gongcheng g");
			sql.append(" where a.gongcheng_uid = g.gongcheng_uid");
			sql.append(" and a.tags = 'F'");
			sql.append(" and sgdt_package.is_my_project(g.projects_uid, "+userUID+") > 0");
			sql.append(" and ((a.status = 0 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqfjdyjsh', "+userUID+") = 1) or");
			sql.append(" (a.status = 3 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_kqfjdyjsh', "+userUID+") = 1))");

			bs = DBUtil.query(conn, sql.toString(), null);
			DaishenFenjieduan =bs.getJson();
			
			//待审考勤状态变更数量	
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from kq_gc_info");
			sql.append(" where status = 0");
			sql.append(" and quanxian_package.get_bg_user_quanxian('bg_kqfjdyjsh', "+userUID+") = 1");

			bs = DBUtil.query(conn, sql.toString(), null);
			KaoqingZhuangtaiBiangeng = bs.getJson();			
			
			//待审人员解锁数量
			sql.setLength(0);
			sql.append(" select count(*) totals");
			sql.append(" from dt_ry_jiesuo j");
			sql.append(" where j.status = 0");
			sql.append(" and ((j.shenpi_status = 1 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_ryjsyjsh', "+userUID+") = 1) or");
			sql.append(" (j.shenpi_status = 2 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_ryjsejsh', "+userUID+") = 1) or");
			sql.append(" (j.shenpi_status = 3 and");
			sql.append(" quanxian_package.get_bg_user_quanxian('bg_ryjssjsh', "+userUID+") = 1))");

			bs = DBUtil.query(conn, sql.toString(), null);
			DaishenJiesuo = bs.getJson();	
			
			
			//目前需考勤项目数量和需考勤人员数量
			sql.setLength(0);			
			sql.append(" select sum(k.sg_zhongyao_nums) sg_zhongyao_nums,");
			sql.append("  sum(k.sg_zhongyao_kq) sg_zhongyao_kq,");
			sql.append("  sum(k.sg_zhongyao_valid) sg_zhongyao_valid,");
			sql.append("  sum(k.sg_zhuyao_nums) sg_zhuyao_nums,");
			sql.append("  sum(k.sg_zhuyao_kq) sg_zhuyao_kq,");
			sql.append("  sum(k.sg_zhuyao_valid) sg_zhuyao_valid,");
			sql.append("  sum(k.jl_zhongyao_nums) jl_zhongyao_nums,");
			sql.append("  sum(k.jl_zhongyao_kq) jl_zhongyao_kq,");
			sql.append("  sum(k.jl_zhongyao_valid) jl_zhongyao_valid,");
			sql.append("  sum(k.jl_zhuyao_nums) jl_zhuyao_nums,");
			sql.append("  sum(k.jl_zhuyao_kq) jl_zhuyao_kq,");
			sql.append("  sum(k.jl_zhuyao_valid) jl_zhuyao_valid");
			sql.append("  from kq_gc_day_stat k");
			sql.append("  where k.kq_date = to_date(sysdate - 1)");			

			bs = DBUtil.query(conn, sql.toString(), null);
			InformationTable = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		
		domresult.append(MainKaoqingInfo);
		domresult.append("&&&&");
		domresult.append(Zhuyaorenyuan2Days);
		domresult.append("&&&&");
		domresult.append(ZuoriWuyouxiaoKaoQing);
		domresult.append("&&&&");
		domresult.append(SgdwGuanjiangangweiWuyouxiaoKaoqing);
		domresult.append("&&&&");
		domresult.append(SgdwGuanZhongyaoWuyouxiaoKaoqing);
		domresult.append("&&&&");
		domresult.append(SgdwGuanZhuyaoWuyouxiaoKaoqing);
		domresult.append("&&&&");
		domresult.append(JldwGuanjiangangweiWuyouxiaoKaoqing);
		domresult.append("&&&&");
		domresult.append(JldwGuanZhongyaoWuyouxiaoKaoqing);
		domresult.append("&&&&");
		domresult.append(JldwGuanZhuyaoWuyouxiaoKaoqing);
		domresult.append("&&&&");
		domresult.append(KaoqingKoufen);
		domresult.append("&&&&");
		domresult.append(DaishenKaoqingZanting);
		domresult.append("&&&&");
		domresult.append(DaishenKaoqingZhongzhi);
		domresult.append("&&&&");
		domresult.append(DaishenFenjieduan);
		domresult.append("&&&&");		
		domresult.append(KaoqingZhuangtaiBiangeng);
		domresult.append("&&&&");		
		domresult.append(DaishenJiesuo);
		domresult.append("&&&&");
		domresult.append(InformationTable);

		return domresult.toString();
	}


	
	//查询今日和昨日更新的整改单条数
	public String queryZGD_Update(String TodayOrYesterday) {
		
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) totals,");
			sql.append(" count(decode(z.zgd_type, 'K', 1, null)) kqnums,");
			sql.append(" count(decode(z.zgd_type, 'Z', 1, null)) zgnums");
			sql.append(" from zg_tzd z");
			if(TodayOrYesterday.equals("today")){
				sql.append(" where to_date(updated_date) = to_date(sysdate)");
			}else if(TodayOrYesterday.equals("yesterday")){
				sql.append(" where to_date(updated_date) = to_date(sysdate)-1");
			}
			
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}
	
	
	//查询今日和昨日更新的整改单条数
	public String queryZGD_Create(int ZG_Xingzhi) {
		
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) totals,");
			sql.append(" count(decode(z.zgd_type, 'K', 1, null)) kqnums,");
			sql.append(" count(decode(z.zgd_type, 'Z', 1, null)) zgnums");
			sql.append(" from zg_tzd z");
			sql.append(" where to_date(created_date) = to_date(sysdate)");
			if(ZG_Xingzhi==1){
				sql.append(" and z.zg_xingzhi_uid = 1");
			}else if(ZG_Xingzhi==2){
				sql.append(" and z.zg_xingzhi_uid = 2");
			}else if(ZG_Xingzhi==3){
				sql.append(" and z.zg_xingzhi_uid = 3");
			}
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}
	
	//查询今日和昨日更新的整改单条数
	public String queryKuofen(String before,String after) {
		
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select count(*) totals");
			sql.append(" from score s");
			sql.append(" where s.chuli_type = 'WG'");
			sql.append(" and to_date(s.create_date) >=");
			sql.append(" to_date('"+before+"', ('YYYY-MM-DD'))");
			sql.append(" and to_date(s.create_date) <=");
			sql.append(" to_date('"+after+"', ('YYYY-MM-DD'))");
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}
	
	//查询停工未复工项目个数
	public String queryTGWFGProjects(int xingzhi) {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(distinct p.projects_uid) as totals");
			sql.append(" from projects p");
			sql.append(" left join projects_gongcheng t");
			sql.append(" on p.projects_uid = t.projects_uid");
			sql.append(" left join zg_tzd zt");
			sql.append(" on t.gongcheng_uid = zt.gongcheng_uid");
			sql.append(" left join zg_dafu zd");
			sql.append(" on zt.zg_tzd_uid = zd.zg_tzd_uid");
			if(xingzhi==2){
				sql.append("  where zt.zg_xingzhi_uid = 2");
			}else if(xingzhi==3){
				sql.append(" where zt.zg_xingzhi_uid = 3");
			}
			sql.append(" and zd.sh_status <> 1");
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}

	//查询整改状态整改单
	public String queryZGStatus(String Status) {
		
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) totals,");
			sql.append(" count(decode(z.zgd_type, 'K', 1, null)) kqnums,");
			sql.append(" count(decode(z.zgd_type, 'Z', 1, null)) zgnums");
			sql.append(" from zg_tzd z");
			if(Status.equals("未闭合")){
				sql.append(" where z.zgstatus <> 4");
			}else if(Status.equals("即将超时未答复")){
				sql.append(" where z.zgstatus = 1");
				sql.append(" and to_date(z.zg_date) >= to_date(sysdate - 2)");
				sql.append(" and to_date(z.zg_date) <= to_date(sysdate)");
			}else if(Status.equals("超时未答复")){
				sql.append(" where z.zgstatus = 1");
				sql.append(" and to_date(z.zg_date) > to_date(sysdate)");
			}
			
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult = bs.getJson();
		} catch (DaoException e) {
			// TODO: handle exception
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return domresult;
	}
		
		

}
