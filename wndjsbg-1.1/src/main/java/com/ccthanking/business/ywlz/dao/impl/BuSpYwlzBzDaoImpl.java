/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.ywlz.BuSpYwlzBzDao.java
 * 创建日期： 2014-06-19 下午 04:49:00
 * 功能：   审批业务流转步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:49:00  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.ywlz.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.ywlz.dao.BuSpYwlzBzDao;
import com.ccthanking.business.ywlz.vo.BuSpYwlzBzVO;
import com.ccthanking.business.ywlz.vo.BuSpYwlzVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.ccthanking.framework.util.WorkdayUtils;
import com.copj.modules.utils.exception.DaoException;
import com.visural.common.StringUtil;


/**
 * <p> BuSpYwlzBzDao.java </p>
 * <p> 功能：审批业务流转步骤 </p>
 *
 * <p><a href="BuSpYwlzBzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

@Component
public class BuSpYwlzBzDaoImpl  extends BsBaseDaoTJdbc implements BuSpYwlzBzDao {

    public String queryCondition(String json, BuSpYwlzBzVO vo, Map map){
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUtil.getSJYXCondition(null);
//            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

//            String sql = "SELECT * FROM " + "BU_SP_YWLZ_BZ t";
            String sql="select * from (select t.FSZCZ_DATE,t.ZS_NAME,t.zs_code,t.ljr_name,t.ljr_date,t.LJR_PHONE,t.lzbz_uid,t.ywlz_uid,t.spbz_uid,t.bzmc,t.bzxh,t.status,t.begin_date,t.plan_end_date," +
            		"t.act_end_date,t.chaoshi_yuanyin,t.chuli_ren,t.chuli_jieguo,(select u.user_name from users u where to_char(u.users_uid)=t.chuli_ren ) as username," +
            		"t.chuli_yijian,decode(t.status,  1,'已通过',  -1,'已退回', 0,'流转中',40,'未提交') as zstatus,"+
					"	decode(t.chuli_jieguo,  1,'同意并通过',  -1,'不同意已退回', 0,'待审') as zjiegou "+
					"	from bu_sp_ywlz_bz t) ";
            
            
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            bs.setFieldDateFormat("BEGIN_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("PLAN_END_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("ACT_END_DATE", "yyyy-MM-dd HH:mm:ss");
            
            
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
            DaoException.handleMessageException("*********查询出错!*********",e);
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryJlRy(String json, BuSpYwlzBzVO vo, Map map){
        
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            String sql="select * from ORGANIZE o " +
            		" connect by prior o.organize_uid=o.p_organize_uid " +
            		" start with o.p_organize_uid=101 and o.org_type='D' ";
            
            
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            bs.setFieldDateFormat("BEGIN_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("PLAN_END_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("ACT_END_DATE", "yyyy-MM-dd HH:mm:ss");
            
            
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
            DaoException.handleMessageException("*********查询出错!*********",e);
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    
    /**
     * 通告业务流转实例 ywlzuid 和业务流转实例的开始时间beginDate 
     * 查询状态为通过的符合条件的业务流转步骤数据
     * sfcs  是否超时
     * */
	public List<?> getSpYwLzBzList(String ywlzuid, Date beginDate,String status,String chulijiegou,String chuliren) {
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
            String sql = "select bz.*,decode(sign(SYSDATE-bz.plan_end_date),1,1,0) as sfcs " +
            		"from BU_SP_YWLZ_BZ bz where 1=1  ";//and bz.status ='1'
            
            if(StringUtil.isNotBlankStr(ywlzuid)){
            	sql+=" and bz.ywlz_uid ='"+ywlzuid+"' ";
            }
            
            if(beginDate!=null){	
            	/****/
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//            	java.util.Date date=new java.util.Date();  
            	String str=sdf.format(beginDate); 
            	sql+=" and to_char(bz.begin_date,'yyyy-mm-dd hh24:mi:ss') >= '"+str+"' ";
            	
//            	sql+=" and bz.begin_date >= '"+beginDate+"' ";         	
            }
            
            if(StringUtil.isNotBlankStr(status)){
            	sql+=" and bz.status ='"+status+"' ";
            }
            
            if(StringUtil.isNotBlankStr(chulijiegou)){
            	sql+=" and bz.chuli_jieguo ='"+chulijiegou+"' ";		
            }
            
            if(StringUtil.isNotBlankStr(chuliren)){
//            	sql+=" and bz.chuli_ren ='"+chuliren+"' ";		
            	sql+="and bz.spbz_uid in( select cyz.spbz_uid from bu_sp_bz_cyz cyz where cyz.cyzuid ='"+chuliren+"' )";
            }
            
            sql+=" order by bz.begin_date desc ";
            
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询业务流转步骤数据出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}


	public List<?> geSpYwClList(String ywlzuid) {
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
            String sql = "select cl.clnr,cl.clsx,cl.sl,cl.sfysc,cl.url,xx.spyw_uid,lz.ywlz_uid,lz.projects_uid "+
		            "from bu_sp_ywcl cl "+
		            "	left join bu_sp_ywxx xx on xx.spyw_uid=cl.spyw_uid "+
		            "	left join bu_sp_ywlz lz on lz.spyw_uid=xx.spyw_uid "+
		            "	where 1=1 ";
            if(StringUtil.isNotBlankStr(ywlzuid)){
            	sql+=" and lz.ywlz_uid='"+ywlzuid+"' ";
            }
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********根据ywlzuid查询审批业务材料出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
	
	public String insertBz(String ywlzUid) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select t.spbz_uid,t.spyw_uid,t.tslx,t.bzxh,t.bzmc,t.clts from bu_sp_bz t where t.spyw_uid = (select g.spyw_uid from bu_sp_ywlz g where g.ywlz_uid = "+ywlzUid+") order by t.bzxh";
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				
				BuSpYwlzBzVO vo = new BuSpYwlzBzVO();
				vo.setLzbz_uid(DBUtil.getSequenceValue("LZBZ_UID", conn));
				vo.setSpbz_uid(res[0][0]);
				vo.setYwlz_uid(ywlzUid);
				vo.setBzxh(res[0][3]);
				vo.setBzmc(res[0][4]);
				vo.setStatus("0");
				vo.setBegin_date(new Date());
				vo.setChuli_jieguo("0");
				Date date = null;
				if ("GZR".equals(res[0][2])) {
					WorkdayUtils work = new WorkdayUtils();
					date = work.getWorkday(vo.getBegin_date(), Integer.parseInt(res[0][5]));
				}else if("ZRR".equals(res[0][2])){
					Calendar cal = Calendar.getInstance();
			        cal.setTime(vo.getBegin_date());
			        cal.add(Calendar.DATE, Integer.parseInt(res[0][5]));
			        date = cal.getTime();
				}
				vo.setPlan_end_date(date);
				
				BaseDAO.insert(conn, vo);
			}
		} catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}
	
	public String updateSH(String ywlz_uid,String xmid) throws Exception{
		Connection conn = DBUtil.getConnection();
		BuSpYwlzVO vo = new BuSpYwlzVO();
		try {
			vo.setYwlz_uid(ywlz_uid);
			vo.setStatus("0");
			vo.setBegin_date(new Date());
			vo.setPlan_end_date(queryWorkDays(ywlz_uid));
			vo.setCishu(updateSqcs(ywlz_uid,xmid));
			BaseDAO.update(conn, vo);
			
		} catch (Exception e) {
			DaoException.handleMessageException("*********提交业务流转实例出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return vo.getRowJson();
	}
	
	public Date queryWorkDays(String ywlz_uid) throws Exception {
		Date date = new Date();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select t.tslx,t.clts from bu_sp_bz t where t.spyw_uid =(select spyw_uid from bu_sp_ywlz where ywlz_uid = "+ywlz_uid+")";
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					WorkdayUtils work = new WorkdayUtils();
					if(("GZR").equals(res[i][0])){
						date = work.getWorkday(date, Integer.parseInt(res[i][1]));
					}else if(("ZRR").equals(res[i][0])){
						Calendar cal = Calendar.getInstance();
				        cal.setTime(date);
				        cal.add(Calendar.DATE, Integer.parseInt(res[i][1]));
				        date = cal.getTime();
					}
				}
			}
		} catch (Exception e) {
			 DaoException.handleMessageException("*********获取审核计划日期查询出错!*********");
		}finally {
			DBUtil.closeConnetion(conn);
		}
		
		return date;
	}
	
	public String updateSqcs(String ywlz_uid,String xmid) throws Exception{
		Connection conn = DBUtil.getConnection();
		String num = "";
		try {
			String sql = "select max(t.cishu) from bu_sp_ywlz t where t.spyw_uid = (select spyw_uid from bu_sp_ywlz where ywlz_uid  ="+ ywlz_uid +") and projects_uid ="+xmid;
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				if (StringUtils.isNoneBlank(res[0][0])) {
					num = Integer.parseInt(res[0][0])+1+"";
				}else{
					num = "1";
				}
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********获得提交次数出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return "".equals(num)?"1":num;
	}
    
    // 在此可加入其它方法

}
