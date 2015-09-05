/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgzxt.getZxtIndexInformationDao.java
 * 创建日期： 2015-08-13 下午 03:19:20
 * 功能：   施工子系统首页信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-08-13 下午 03:19:20  老虎是只耽耽   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgzxt.dao.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import com.ccthanking.business.sgzxt.dao.getZxtIndexInformationDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> getZxtIndexInformationDao.java </p>
 * <p> 功能：施工子系统首页信息 </p>
 *
 * <p><a href="getZxtIndexInformationDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">老虎是只耽耽</a>
 * @version 0.1
 * @since 2015-08-13
 * 
 */

@Component
public class getZxtIndexInformationDaoImpl  extends BsBaseDaoTJdbc implements getZxtIndexInformationDao {

    public String queryCondition(String json,String GongCheng_UID){
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	StringBuffer sql = new StringBuffer();
        	sql.append("SELECT  DECODE(G.GC_STATUS, -1, '未开工', 0, '在建', 1, '完工', 2, '竣工') GC_STATUS,");
        	sql.append(" GC_PACKAGE.GET_PROJECT_AJZJ(P.PROJECTS_UID,1) AJY,");
        	sql.append(" GC_PACKAGE.GET_PROJECT_AJZJ(P.PROJECTS_UID,2) ZJY FROM PROJECTS_GONGCHENG G ");
        	sql.append(" LEFT JOIN PROJECTS P ON G.PROJECTS_UID = P.PROJECTS_UID ");
        	sql.append("WHERE G.GONGCHENG_UID="+GongCheng_UID);
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
            String dqxmxx1 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT O.ORG_NAME JDXZ");
            sql.append("  FROM PROJECTS_AJZJ AZ");
            sql.append("  LEFT JOIN ORGANIZE O");
            sql.append("    ON AZ.USERS_UID = O.ORGANIZE_UID");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON AZ.PROJECTS_UID = G.PROJECTS_UID");
            sql.append("  WHERE AZ.JIANDU_TYPE = 100");
            sql.append("   AND G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String dqxmxx2 = bs.getJson();
            
            sql = this.getShenpiStates(sql,"119", GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String sxsp1 = bs.getJson();
            
            sql = this.getShenpiStates(sql,"32", GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String sxsp2 = bs.getJson();
            
            sql = this.getShenpiStates(sql,"33", GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String sxsp3 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT P.PROJECTS_NAME,");
            sql.append("       P.PLAN_KG_DATE,");
            sql.append("       P.JG_DATE,");
            sql.append("       P.LIANXIREN,");
            sql.append("       P.LIANXIREN_PHONE,");
            sql.append("       P.ZONG_TOUZI,");
            sql.append("       P.JIANZHU_MIANJI");
            sql.append("  FROM PROJECTS P");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON P.PROJECTS_UID = G.PROJECTS_UID");
            sql.append("  WHERE G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String jsdw1 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT JS.JS_COMPANY_UID, JS.COMPANY_NAME, JS.SCORE");
            sql.append("  FROM JS_COMPANY JS");
            sql.append("  LEFT JOIN PROJECTS P");
            sql.append("    ON JS.JS_COMPANY_UID = P.JS_COMPANY_UID");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON P.PROJECTS_UID = G.PROJECTS_UID");
            sql.append(" WHERE G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String jsdw2 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT ZZHYDJ.KCDWNAME, ZZHYDJ.SJDWMC");
            sql.append("  FROM BU_SPYW_ZZHYDJ ZZHYDJ");
            sql.append("  LEFT JOIN BU_SP_YWLZ YWLZ");
            sql.append("    ON ZZHYDJ.YWLZ_UID = YWLZ.YWLZ_UID");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON YWLZ.PROJECTS_UID = G.PROJECTS_UID");
            sql.append(" WHERE G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String jsdw3 = bs.getJson();         
            
            sql.setLength(0);
            /*
             * 如果V_GC_ZRZT中添加了安全生产许可证字段的话可以直接用下面SQL
            sql.append("SELECT V.DUIXIANG_NAME COMPANY_NAME,");
            sql.append("       V.SCORE ,");
            sql.append("       V.ZHENGSHU_CODE ,");
            sql.append("       V.ZHENGSHU_NAME ZHENGSHU_DENGJI,");
            sql.append("       V.LIANXIREN,");
            sql.append("       V.LIANXIREN_MOBILE");
            sql.append("  FROM V_GC_ZRZT V");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON V.GONGCHENG_UID = G.GONGCHENG_UID");
            sql.append(" WHERE V.DUIXIANG_TYPE = 'SGDW'");
            sql.append("   AND G.GONGCHENG_UID = "+GongCheng_UID);
            */
            sql.append("SELECT V.DUIXIANG_NAME COMPANY_NAME,");
            sql.append("       V.SCORE,V.DUIXIANG_UID SG_COMPANY_UID,");
            sql.append("       V.ZHENGSHU_CODE,");
            sql.append("       V.ZHENGSHU_NAME ZHENGSHU_DENGJI,");
            sql.append("       V.LIANXIREN,");
            sql.append("       V.LIANXIREN_MOBILE,");
            sql.append("       SG.ANQUAN_CODE");
            sql.append("  FROM PROJECTS_GONGCHENG G");
            sql.append("  LEFT JOIN V_GC_ZRZT V");
            sql.append("    ON V.GONGCHENG_UID = G.GONGCHENG_UID");
            sql.append("  LEFT JOIN DT_GC_SGBB DT");
            sql.append("    ON DT.GONGCHENG_UID = G.GONGCHENG_UID");
            sql.append("  LEFT JOIN SGBB BB");
            sql.append("    ON BB.SGBB_UID = DT.SGBB_UID");
            sql.append("  LEFT JOIN SG_ENTERPRISE_LIBRARY SG");
            sql.append("    ON SG.SG_COMPANY_UID = BB.SG_COMPANY_UID");
            sql.append(" WHERE V.DUIXIANG_TYPE = 'SGDW'");
            sql.append("   AND SG.STATUS = 1");
            sql.append("   AND G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String sgdw1 = bs.getJson();
            
            
            sql.setLength(0);
            sql.append("SELECT V.DUIXIANG_UID XMJL_UID,V.DUIXIANG_NAME PERSON_NAME, V.SCORE, V.ZHENGSHU_CODE ZHICHENG_CODE, V.LIANXIREN_MOBILE PHONE");
            sql.append("  FROM V_GC_ZRZT V");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON V.GONGCHENG_UID = G.GONGCHENG_UID");
            sql.append(" WHERE V.DUIXIANG_TYPE = 'XMJL'");
            sql.append("   AND G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String sgdw2 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT V.DUIXIANG_UID JLDW_UID,V.DUIXIANG_NAME JLDW_NAME, V.SCORE");
            sql.append("  FROM V_GC_ZRZT V");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON V.GONGCHENG_UID = G.GONGCHENG_UID");
            sql.append(" WHERE V.DUIXIANG_TYPE = 'JLDW'");
            sql.append("   AND G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String jldw1 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT V.DUIXIANG_UID ZJ_UID,V.DUIXIANG_NAME ZJ_NAME,");
            sql.append("       V.SCORE,");
            sql.append("       V.ZHENGSHU_CODE,");
            sql.append("       V.LIANXIREN_MOBILE PHONE");
            sql.append("  FROM V_GC_ZRZT V");
            sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
            sql.append("    ON V.GONGCHENG_UID = G.GONGCHENG_UID");
            sql.append(" WHERE V.DUIXIANG_TYPE = 'ZJ'");
            sql.append("   AND G.GONGCHENG_UID = "+GongCheng_UID);
            bs = DBUtil.query(conn, sql.toString(), null);
            String jldw2 = bs.getJson();
            
            sql.setLength(0);
            sql.append("SELECT S.SCORE");
            sql.append("  FROM GC_SCORE S");
            sql.append(" WHERE S.GONGCHENG_UID = "+GongCheng_UID);
            sql.append("   AND S.RIQI = (SELECT MAX(GC.RIQI) FROM GC_SCORE GC WHERE GC.GONGCHENG_UID = 69)");
            bs = DBUtil.query(conn, sql.toString(), null);
            String SCORE = bs.getJson();
            domresult = dqxmxx1+"&&&&"+dqxmxx2+"&&&&"+sxsp1+"&&&&"+sxsp2+"&&&&"+sxsp3+"&&&&"+jsdw1+"&&&&"+jsdw2+"&&&&"+jsdw3+"&&&&"+sgdw1+"&&&&"+sgdw2+"&&&&"+jldw1+"&&&&"+jldw2+"&&&&"+SCORE;
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryGC_SCORE(String json){
    	
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
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT S.GC_SCORE_UID, TO_CHAR(S.RIQI,'yyyy\"年\"mm\"月\"') RIQI, S.SCORE");
    		sql.append("  FROM GC_SCORE S");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    public String queryGC_SCORE_detail(String json){  	
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
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT DECODE(D.TARGET_TYPE,");
    		sql.append("              'JS',");
    		sql.append("              '建设单位',");
    		sql.append("              'SG',");
    		sql.append("              '施工单位',");
    		sql.append("              'JL',");
    		sql.append("              '监理单位',");
    		sql.append("              'XMJL',");
    		sql.append("              '项目经理',");
    		sql.append("              'ZJ',");
    		sql.append("              '总监') DUIXIANG,");
    		sql.append("       V.DUIXIANG_NAME,");
    		sql.append("			 TO_CHAR(D.RIQI,'YYYY-MM-DD') RIQI,");
    		sql.append("       D.SCORE");
    		sql.append("  FROM GC_SCORE_DETAIL D");
    		sql.append("  LEFT JOIN GC_SCORE S");
    		sql.append("    ON D.GC_SCORE_UID = S.GC_SCORE_UID");
    		sql.append("  LEFT JOIN V_GC_ZRZT V");
    		sql.append("    ON V.GONGCHENG_UID = S.GONGCHENG_UID");
    		sql.append("   AND V.DUIXIANG_UID = D.TARGET_UID");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    
    public String querySGRY(String json){  	
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
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT V.PERSON_UID,V.PERSON_NAME, V.SHENFENZHENG, V.ZHENGSHU_CODE, V.ZHUANYE, V.NAMES FROM V_GC_RY V");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    public String queryJLRY(String json){  	
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
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT V.PERSON_UID, V.PERSON_NAME, V.SHENFENZHENG, V.ZHENGSHU_CODE, V.ZHUANYE, V.NAMES FROM V_GC_RY V");    		
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    
    public String queryRYXX_detail(String json,String personType){  	
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
    		// 组织查询条件
    		PageManager page = RequestUtil.getPageManager(json);
    		String condition = RequestUtil.getConditionList(json).getConditionWhere();
    		if("SGRY".equals(personType)){
    			condition +=" AND V.COM_TYPE = 'SG'";
    		}else if("JLRY".equals(personType)){
    			condition +=" AND V.COM_TYPE = 'JL'";
    		}
    		String orderFilter = RequestUtil.getOrderFilter(json);
    		condition += orderFilter;
    		if (page == null)
    			page = new PageManager();
    		page.setFilter(condition);
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT V.PERSON_NAME, V.SHENFENZHENG, V.ZHENGSHU_CODE, V.ZHUANYE,V.PHONE,PL.SCORE");
    		sql.append("  FROM V_GC_RY V  ");
    		if("SGRY".equals(personType)){
    			sql.append(" LEFT JOIN SG_PERSON_LIBRARY PL ON V.PERSON_UID = PL.SG_PERSON_UID AND PL.STATUS = 1");
    		}else if("JLRY".equals(personType)){
    			sql.append(" LEFT JOIN PERSON_LIBRARY PL ON V.PERSON_UID = PL.JL_PERSON_UID AND PL.STATUS = 1");
    		}
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    
    public String queryRYXX_Fileid(String json,String PERSON_UID,String File_type){  	
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT F.FS_FILE_UID");
    		sql.append("  FROM FS_FILE F");
    		sql.append(" WHERE F.TARGET_UID = "+PERSON_UID);
    		sql.append("   AND F.FILE_TYPE = "+File_type);
    		sql.append("   AND F.ENABLED = 1");
    		sql.append("   AND F.CREATE_DATE = (SELECT MAX(T.CREATE_DATE)");
    		sql.append("                          FROM FS_FILE T");
    		sql.append("                         WHERE F.TARGET_UID = "+PERSON_UID);
    		sql.append("                           AND F.FILE_TYPE = "+File_type);
    		sql.append("                           AND F.ENABLED = 1)");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    
    
    public String querygc(String json){  	
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
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT DISTINCT (PG.GONGCHENG_UID),");
    		sql.append("                PG.GONGCHENG_NAME GCMC,");
    		sql.append("                P.PROJECTS_NAME XMMC,");
    		sql.append("                J.COMPANY_NAME JSDW,");
    		sql.append("                SG.COMPANY_NAME SGDW,");
    		sql.append("                SGPL.PERSON_NAME XMJL,");
    		sql.append("                SGPL.PHONE XMJLPHONE,");
    		sql.append("                PL.PERSON_NAME ZJ,");
    		sql.append("                PL.PHONE ZJPHONE");
    		sql.append("  FROM PROJECTS_GONGCHENG PG");
    		sql.append("  LEFT JOIN PROJECTS P");
    		sql.append("    ON PG.PROJECTS_UID = P.PROJECTS_UID");
    		sql.append("  LEFT JOIN JS_COMPANY J");
    		sql.append("    ON P.JS_COMPANY_UID = J.JS_COMPANY_UID");
    		sql.append("  LEFT JOIN DT_GC_SGBB DTSG");
    		sql.append("    ON PG.GONGCHENG_UID = DTSG.GONGCHENG_UID");
    		sql.append("  LEFT JOIN SGBB S");
    		sql.append("    ON DTSG.SGBB_UID = S.SGBB_UID");
    		sql.append("  LEFT JOIN SG_ENTERPRISE_LIBRARY SG");
    		sql.append("    ON S.SG_COMPANY_UID = SG.SG_COMPANY_UID");
    		sql.append("  LEFT JOIN (SELECT JLRYS.GONGCHENG_UID, JLYS.JL_PERSON_UID");
    		sql.append("               FROM DT_GC_JLRY JLRYS, JLBB_JLY JLYS");
    		sql.append("              WHERE JLRYS.JLBB_JLY_UID = JLYS.JLBB_JLY_UID");
    		sql.append("                AND JLYS.GANGWEI_UID = '10') ZJS");
    		sql.append("    ON ZJS.GONGCHENG_UID = PG.GONGCHENG_UID");
    		sql.append("  LEFT JOIN PERSON_LIBRARY PL");
    		sql.append("    ON ZJS.JL_PERSON_UID = PL.JL_PERSON_UID");
    		sql.append("   AND PL.STATUS = '1'");
    		sql.append("  LEFT JOIN (SELECT DTSGRY.GONGCHENG_UID, DTSGRY.SG_PERSON_UID");
    		sql.append("               FROM DT_GC_SGRY DTSGRY, SGBB_RY SGRY");
    		sql.append("              WHERE DTSGRY.SGBB_RY_UID = SGRY.SGBB_RY_UID");
    		sql.append("                AND SGRY.GANGWEI_UID = '19') XMJL");
    		sql.append("    ON XMJL.GONGCHENG_UID = PG.GONGCHENG_UID");
    		sql.append("  LEFT JOIN SG_PERSON_LIBRARY SGPL");
    		sql.append("    ON XMJL.SG_PERSON_UID = SGPL.SG_PERSON_UID");
    		sql.append("   AND SGPL.STATUS = '1'");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
    		domresult  = bs.getJson();
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;
    	
    }
    
    
    
    // 在此可加入其它方法
    public StringBuffer getShenpiStates(StringBuffer sql,String SPYW_UID,String GongchengUid){
    	sql.setLength(0);
    	
    	if("119".equals(SPYW_UID)){
    		sql.append("SELECT YWLZ.STATUS, YWLZ.ACT_END_DATE FROM BU_SP_YWLZ YWLZ WHERE YWLZ.PROJECTS_UID="+GongchengUid+" AND YWLZ.SPYW_UID="+SPYW_UID);
    	}else{   	
    	sql.append("SELECT YWLZ.STATUS, YWLZ.ACT_END_DATE");
    	sql.append("  FROM BU_SP_YWLZ YWLZ");
    	sql.append("  LEFT JOIN PROJECTS_GONGCHENG G");
    	sql.append("    ON YWLZ.PROJECTS_UID = G.PROJECTS_UID");
    	sql.append(" WHERE YWLZ.SPYW_UID ="+SPYW_UID);
    	sql.append("   AND YWLZ.CISHU = (SELECT MAX(Y.CISHU)");
    	sql.append("                       FROM BU_SP_YWLZ Y");
    	sql.append("                      WHERE Y.PROJECTS_UID = YWLZ.PROJECTS_UID");
    	sql.append("                        AND Y.SPYW_UID = "+SPYW_UID+")");
    	sql.append("   AND G.GONGCHENG_UID ="+GongchengUid);
    	}
    	return sql;
    }
}
