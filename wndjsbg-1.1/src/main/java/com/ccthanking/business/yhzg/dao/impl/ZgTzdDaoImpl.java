/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.yhzg.ZgTzdDao.java
 * 创建日期： 2015-04-21 下午 02:41:29
 * 功能：   整改通知单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 02:41:29  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.yhzg.vo.ZgTzdVO;
import com.ccthanking.business.yhzg.dao.ZgTzdDao;

import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> ZgTzdDao.java </p>
 * <p> 功能：整改通知单 </p>
 *
 * <p><a href="ZgTzdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Component
public class ZgTzdDaoImpl  extends BsBaseDaoTJdbc implements ZgTzdDao {

    public String queryCondition01(String json, ZgTzdVO vo, Map map){
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

           // String sql = "SELECT * FROM " + "ZG_TZD t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT T.ZG_TZD_UID,T.GONGCHENG_UID,DF.ZG_DAFU_UID , ");
            sql.append(" DECODE(T.ZGSTATUS,'1','未答复','2','已答复','3','需要重新整改','4','已闭合') AS ZGZT, ");
            sql.append(" DECODE(T.ZG_XINGZHI_UID,'1','限期整改','2','局部停工整改','3','全面停工整改') AS ZGXZ, ");
            sql.append(" T.XMJL_KOUFEN,T.JL_KOUFEN, ");
            //sql.append(" C.WG_MIAOSHU,C.ZG_CONTENT_UID, ");
            sql.append(" T.FAFANG_DATE,T.ZG_DATE ");
            sql.append(" FROM ZG_TZD T ");
            sql.append(" LEFT JOIN ZG_DAFU DF ");
            sql.append(" ON T.ZG_TZD_UID = DF.ZG_TZD_UID ");
            //sql.append(" LEFT JOIN ZG_CONTENT C ");
            //sql.append(" ON T.ZG_TZD_UID = C.ZG_TZD_UID ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
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
            bs.setFieldDateFormat("FAFANG_DATE", "yyyy/MM/dd");
            bs.setFieldDateFormat("ZG_DATE", "yyyy/MM/dd");
            

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    
    public String getDeptName(String ORGANIZE_UID){
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {          
            String sql = "select t.org_name from ORGANIZE t where t.organize_uid ="+ORGANIZE_UID;          
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    
    public String queryCondition(String json,Map map){
    	String condition2 = (String) map.get("condition2");
    	String userUid = (String) map.get("userUid");
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {    		
    		// 组织查询条件
    		PageManager page = RequestUtil.getPageManager(json);
    		String condition = RequestUtil.getConditionList(json).getConditionWhere();
    		//此处添加条件
    		if("today-update".equals(condition2)){
    			condition += " and to_date(t.UPDATED_DATE) = to_date(sysdate)";
    		}else if("today-update-shiti".equals(condition2)){
    			condition += " and to_date(t.UPDATED_DATE) = to_date(sysdate) and t.ZGD_TYPE = 'Z' ";
    		}else if("today-update-kaoqing".equals(condition2)){
    			condition += " and to_date(t.UPDATED_DATE) = to_date(sysdate) and t.ZGD_TYPE = 'K' ";
    		}else if("yesterday-update".equals(condition2)){
    			condition += " and to_date(t.UPDATED_DATE) = to_date(sysdate-1)";
    		}else if("yesterday-update-shiti".equals(condition2)){
    			condition += " and to_date(t.UPDATED_DATE) = to_date(sysdate-1) and t.ZGD_TYPE = 'Z' ";
    		}else if("yesterday-update-kaoqing".equals(condition2)){
    			condition += " and to_date(t.UPDATED_DATE) = to_date(sysdate-1) and t.ZGD_TYPE = 'K' ";
    		}else if("today-create".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate)";
    		}else if("today-create-shiti".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate) and t.ZGD_TYPE = 'Z'";
    		}else if("today-create-kaoqing".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate) and t.ZGD_TYPE = 'K'";
    		}else if("jbtg".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate) and t.ZG_XINGZHI_UID = 2";
    		}else if("qmtg".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate) and t.ZG_XINGZHI_UID = 3";
    		}else if("qmtg-shiti".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate) and t.ZG_XINGZHI_UID = 3 and t.ZGD_TYPE = 'Z'";
    		}else if("qmtg-kaoqing".equals(condition2)){
    			condition += " and to_date(t.CREATED_DATE) = to_date(sysdate) and t.ZG_XINGZHI_UID = 3 and t.ZGD_TYPE = 'K'";
    		}else if("weibihe".equals(condition2)){
    			condition += " and t.ZGSTATUS <> 4";
    		}else if("weibihe-shiti".equals(condition2)){
    			condition += " and t.ZGSTATUS <> 4 and t.ZGD_TYPE = 'Z'";
    		}else if("weibihe-kaoqing".equals(condition2)){
    			condition += " and t.ZGSTATUS <> 4 and t.ZGD_TYPE = 'K'";
    		}else if("jjcswdf".equals(condition2)){
    			condition += " and t.ZGSTATUS = 1 and to_date(t.zg_date) >= to_date(sysdate - 2) and to_date(t.zg_date) <= to_date(sysdate)";
    		}else if("jjcswdf-shiti".equals(condition2)){
    			condition += " and t.ZGSTATUS = 1 and to_date(t.zg_date) >= to_date(sysdate - 2) and to_date(t.zg_date) <= to_date(sysdate) and t.ZGD_TYPE = 'Z'";
    		}else if("jjcswdf-kaoqing".equals(condition2)){
    			condition += " and t.ZGSTATUS = 1 and to_date(t.zg_date) >= to_date(sysdate - 2) and to_date(t.zg_date) <= to_date(sysdate) and t.ZGD_TYPE = 'K'";
    		}else if("cswdf".equals(condition2)){
    			condition += " and t.ZGSTATUS = 1 and to_date(t.zg_date) > to_date(sysdate)";
    		}else if("cswdf-shiti".equals(condition2)){
    			condition += " and t.ZGSTATUS = 1 and to_date(t.zg_date) > to_date(sysdate) and t.ZGD_TYPE = 'Z'";    			
    		}else if("cswdf-kaoqing".equals(condition2)){
    			condition += " and t.ZGSTATUS = 1 and to_date(t.zg_date) > to_date(sysdate) and t.ZGD_TYPE = 'K'";
    		}else if("dshqmtg".equals(condition2)){
    			condition += " and t.SH_STATUS = 0 and ((t.SH_LEVEL = 1 and quanxian_package.get_bg_user_quanxian('4050203', "+userUid+") = 1)";
    			condition += " or (t.SH_LEVEL = 2 and quanxian_package.get_bg_user_quanxian('4050204', "+userUid+") = 1)) and t.ZG_XINGZHI_UID = 3";
    		}else if("dshjbtg".equals(condition2)){
    			condition += " and t.SH_STATUS = 0 and t.SH_LEVEL = 1 and quanxian_package.get_bg_user_quanxian('4050202', "+userUid+") = 1 and t.ZG_XINGZHI_UID = 2";
    		}else if("dshfg".equals(condition2)){
    			condition += " and d.sh_status = 0 and ((d.sh_level = 1 and quanxian_package.get_bg_user_quanxian('4050205', "+userUid+") = 1)";
    			condition += " or (d.sh_level = 2 and quanxian_package.get_bg_user_quanxian('4050206', "+userUid+") = 1))";
    		}
    		String orderFilter = RequestUtil.getOrderFilter(json);
    		condition += orderFilter;
    		if (page == null)
    			page = new PageManager();
    		page.setFilter(condition);
    		
    		StringBuffer sql = new StringBuffer();
    		if(condition.indexOf("WG_MIAOSHU")==-1){
    			sql.append(" SELECT  T.ZG_TZD_UID,");
    		}else{
    			sql.append(" SELECT DISTINCT T.ZG_TZD_UID,");
    		}    		
    		sql.append(" DECODE(T.ZG_XINGZHI_UID,'1','限期整改','2','局部停工整改','3','全面停工整改') AS ZGXZ, ");
    		sql.append(" T.XMJL_KOUFEN,T.JL_KOUFEN, RET_ZG_CONTENT(T.ZG_TZD_UID) ZG_TZD_CONTENT,");
    		sql.append(" T.FAFANG_DATE,T.ZG_DATE ");
    		sql.append(" FROM ZG_TZD T ");
    		if(condition.indexOf("WG_MIAOSHU")!=-1){
    			sql.append(" LEFT JOIN ZG_CONTENT C ");
        		sql.append(" ON T.ZG_TZD_UID = C.ZG_TZD_UID ");
    		}
    		if("dshfg".equals(condition2)){
    			sql.append(" LEFT JOIN ZG_DAFU D");   			
    			sql.append(" ON T.ZG_TZD_UID = D.ZG_TZD_UID");   			
    		}
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

    		bs.setFieldDateFormat("FAFANG_DATE", "yyyy-MM-dd");
    		bs.setFieldDateFormat("ZG_DATE", "yyyy-MM-dd");    		    		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    public String queryZGD2(String zhenggai, String deptUid, String before,String after,String json){
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	// 组织查询条件
    		PageManager page = RequestUtil.getPageManager(json);
    		String condition = RequestUtil.getConditionList(json).getConditionWhere();
    		
    		condition +=" and T.CREATED_BY in (select o.USER_UID from organize o where o.P_ORGANIZE_UID = '"+deptUid+"')";
    		if("STZG".equals(zhenggai)){
    			condition +=" and T.ZGD_TYPE = 'Z'";
    		}else if("KQZG".equals(zhenggai)){
    			condition +=" and T.ZGD_TYPE = 'K'";
    		}
    		condition +=" and to_date(t.UPDATED_DATE) >= to_date('"+before+"', ('yyyy-MM-dd'))";
    		condition +=" and to_date(t.UPDATED_DATE) <= to_date('"+after+"', ('yyyy-MM-dd'))";
        	String orderFilter = RequestUtil.getOrderFilter(json);
    		condition += orderFilter;
    		if (page == null)
    			page = new PageManager();
    		page.setFilter(condition);
        	StringBuffer sql = new StringBuffer();
        	if(condition.indexOf("WG_MIAOSHU")==-1){
    			sql.append(" SELECT  T.ZG_TZD_UID,");
    		}else{
    			sql.append(" SELECT DISTINCT T.ZG_TZD_UID,");
    		}    		
    		sql.append(" DECODE(T.ZG_XINGZHI_UID,'1','限期整改','2','局部停工整改','3','全面停工整改') AS ZGXZ, ");
    		sql.append(" T.XMJL_KOUFEN,T.JL_KOUFEN, ");
    		sql.append(" T.FAFANG_DATE,T.ZG_DATE ");
    		sql.append(" FROM ZG_TZD T ");
    		if(condition.indexOf("WG_MIAOSHU")!=-1){
    			sql.append(" LEFT JOIN ZG_CONTENT C ");
        		sql.append(" ON T.ZG_TZD_UID = C.ZG_TZD_UID ");
    		}
    		System.out.println(sql.toString());
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            domresult = bs.getJson();            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
    }

	public String queryUid(String json) {
		String tzdUid = "";
		try {
			tzdUid = DBUtil.getSequenceValue("ZG_TZD_UID");
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询通知单uid出错!*********");
		}
		return tzdUid ;
	}

	public String getCode(String zglx,int year) {
		String code = "";
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select max(to_number(substr(t.zg_code, 9))) ");
			sql.append("   from zg_tzd t ");
			sql.append("  where t.zg_xingzhi_uid = '"+zglx+"' ");
			sql.append("    and substr(t.zg_code, 0, 4) = '"+year+"' ");
			String[][] codes = DBUtil.query(sql.toString());
			if(codes!=null&&StringUtils.isNotBlank(codes[0][0])){
				code = Integer.parseInt(codes[0][0]) + 1 + "";;
				if(code.length()==1){
					code = "000"+code;
				}else if(code.length()==2){
					code = "00"+code;
				}else if(code.length()==3){
					code = "0"+code;
				}
			}else{
				code = "0001";
			}
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询通知单编号出错!*********");
		}
		return code ;
	}

	public String queryForm(String msg) {
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "ZG_TZD t";
   
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
            bs.setFieldDateFormat("FAFANG_DATE", "yyyy/MM/dd");
            bs.setFieldDateFormat("ZG_DATE", "yyyy/MM/dd");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public String getJtCount(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "0";
        try {
            // 组织查询条件
            String sql = "select count(0)jt from zg_tzd where ZGSTATUS = 1 and ZG_XINGZHI_UID = '2'";
            String[][] res = DBUtil.query(conn, sql);
            domresult = res[0][0];
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public String getQtCount(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "0";
        try {
            // 组织查询条件
            String sql = "select count(0) from zg_tzd where ZGSTATUS = 1 and ZG_XINGZHI_UID = '3'";
            String[][] res = DBUtil.query(conn, sql);
            domresult = res[0][0];
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public String querySh(String msg) {
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

           // String sql = "SELECT * FROM " + "ZG_TZD t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT TZD.ZG_TZD_UID,P.PROJECTS_NAME,SE.COMPANY_NAME AS SGDW,E.COMPANY_NAME AS JLDW, ");
            sql.append(" DECODE(TZD.ZG_XINGZHI_UID,'1','限时整改','2','局部停工整改','3','全面停工整改') AS ZGXZ, ");
            sql.append(" TZD.SGDW_KOUFEN,TZD.XMJL_KOUFEN,TZD.JLDW_KOUFEN,TZD.JL_KOUFEN, ");
            sql.append(" DECODE(TZD.FAFANG_DANWEI,'AJ','安监站','ZJ','质监站','JG','建管办') AS FFDW,  ");
            sql.append(" TZD.FAFANG_DATE,TZD.ZG_DATE ");
            sql.append(" FROM ZG_TZD TZD ");
            sql.append(" LEFT JOIN PROJECTS_GONGCHENG  PG ");
            sql.append(" ON TZD.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN PROJECTS P ");
            sql.append(" ON PG.PROJECTS_UID = P.PROJECTS_UID ");
            sql.append(" LEFT JOIN DT_GC_JLBB DJ ");
            sql.append(" ON DJ.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN JLBB J ");
            sql.append(" ON DJ.JLBB_UID = J.JLBB_UID ");
            sql.append(" LEFT JOIN ENTERPRISE_LIBRARY E ");
            sql.append(" ON J.JL_COMPANY_UID = E.JL_COMPANY_UID ");
            sql.append(" LEFT JOIN DT_GC_SGBB DS ");
            sql.append(" ON DS.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN SGBB S ");
            sql.append(" ON DS.SGBB_UID = S.SGBB_UID ");
            sql.append(" LEFT JOIN SG_ENTERPRISE_LIBRARY SE ");
            sql.append(" ON S.SG_COMPANY_UID = SE.SG_COMPANY_UID ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
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
            bs.setFieldDateFormat("FAFANG_DATE", "yyyy/MM/dd");
            bs.setFieldDateFormat("ZG_DATE", "yyyy/MM/dd");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public List<?> queryPrint(String tzduid) {
		Connection conn = DBUtil.getConnection();
		List<?> map = null;
        StringBuffer sql = new StringBuffer();
		try {
            sql.append(" SELECT TZD.ZG_TZD_UID,P.PROJECTS_NAME,SE.COMPANY_NAME AS SGDW,E.COMPANY_NAME AS JLDW, ");
            sql.append(" DECODE(TZD.ZG_XINGZHI_UID,'1','限时整改','2','局部停工整改','3','全面停工整改') AS ZGXZ, ");
            sql.append(" TZD.SGDW_KOUFEN,TZD.XMJL_KOUFEN,TZD.JLDW_KOUFEN,TZD.JL_KOUFEN, ");
            sql.append(" DECODE(TZD.FAFANG_DANWEI,'AJ','安监站','ZJ','质监站','JG','建管办') AS FFDW,  ");
            sql.append(" TZD.FAFANG_DATE,TZD.ZG_DATE ");
            sql.append(" FROM ZG_TZD TZD ");
            sql.append(" LEFT JOIN PROJECTS_GONGCHENG  PG ");
            sql.append(" ON TZD.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN PROJECTS P ");
            sql.append(" ON PG.PROJECTS_UID = P.PROJECTS_UID ");
            sql.append(" LEFT JOIN DT_GC_JLBB DJ ");
            sql.append(" ON DJ.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN JLBB J ");
            sql.append(" ON DJ.JLBB_UID = J.JLBB_UID ");
            sql.append(" LEFT JOIN ENTERPRISE_LIBRARY E ");
            sql.append(" ON J.JL_COMPANY_UID = E.JL_COMPANY_UID ");
            sql.append(" LEFT JOIN DT_GC_SGBB DS ");
            sql.append(" ON DS.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN SGBB S ");
            sql.append(" ON DS.SGBB_UID = S.SGBB_UID ");
            sql.append(" LEFT JOIN SG_ENTERPRISE_LIBRARY SE ");
            sql.append(" ON S.SG_COMPANY_UID = SE.SG_COMPANY_UID ");
            sql.append("  where e.status = '1' and se.status = '1' and tzd.zg_tzd_uid = '"+tzduid+"' ");
			map =  DBUtil.queryReturnList(conn, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}
    
	
	/**
	 * 调用存储过程 计算扣分情况
	 */
	public String setScore(String tzdUid) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call ZG_PACKAGE.zg_to_wg(?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.setInt(1, StringUtils.isNotBlank(tzdUid)?Integer.parseInt(tzdUid):0);
			cstmt.execute();
			//int status = cstmt.getInt(4);
			//String reason = cstmt.getString(5);
			//obj.put("STATUS", status);
			//obj.put("REASON", reason);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}
	
	
	/**
	 * 调用存储过程 计算扣分情况
	 */
	public String queryScore(String tzdUid) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call ZG_PACKAGE.set_zg_score(?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.setInt(1, StringUtils.isNotBlank(tzdUid)?Integer.parseInt(tzdUid):0);
			cstmt.execute();
			//int status = cstmt.getInt(4);
			//String reason = cstmt.getString(5);
			//obj.put("STATUS", status);
			//obj.put("REASON", reason);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}
	
	
	/**
	 * 调用存储过程 计算扣分情况
	 */
	public String delScore(String tzdUid) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call ZG_PACKAGE.del_zg_to_wg(?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.setInt(1, StringUtils.isNotBlank(tzdUid)?Integer.parseInt(tzdUid):0);
			cstmt.execute();
			//int status = cstmt.getInt(4);
			//String reason = cstmt.getString(5);
			//obj.put("STATUS", status);
			//obj.put("REASON", reason);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}
    // 在此可加入其它方法

}
