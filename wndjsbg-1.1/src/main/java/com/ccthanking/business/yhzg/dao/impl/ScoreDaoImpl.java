/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.yhzg.ScoreDao.java
 * 创建日期： 2015-05-17 上午 09:23:51
 * 功能：   分数管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-17 上午 09:23:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.yhzg.vo.ScoreVO;
import com.ccthanking.business.yhzg.dao.ScoreDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> ScoreDao.java </p>
 * <p> 功能：分数管理 </p>
 *
 * <p><a href="ScoreDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-05-17
 * 
 */

@Component
public class ScoreDaoImpl  extends BsBaseDaoTJdbc implements ScoreDao {

    public String queryCondition(String json, ScoreVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String sdate = "";
        String edate = "";
        String domresult = "";
        try {


 
            // 组织查询条件
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
           // condition += BusinessUtil.getCommonCondition(user, null);
/*            if(condition.indexOf("and B_DATE")!=-1){
            	condition += " and CREATE_DATE  between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd') ";
            }*/
            
            condition += orderFilter;
            PageManager page = RequestUtil.getPageManager(json);
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            //String sql = "SELECT * FROM " + "SCORE t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT S.SCORE_UID,DECODE(S.CHULI_DUIXIANG,'SG',(SELECT SG.COMPANY_NAME FROM SG_ENTERPRISE_LIBRARY SG WHERE SG.SG_COMPANY_UID = S.CHULI_UID AND SG.STATUS = 1)||('(施工单位)'), ");
            sql.append(" 'JL',(SELECT JL.COMPANY_NAME FROM ENTERPRISE_LIBRARY JL WHERE JL.JL_COMPANY_UID = S.CHULI_UID AND JL.STATUS = 1)||('(监理单位)'), ");
            sql.append(" 'JS',(SELECT JS.COMPANY_NAME FROM JS_COMPANY JS WHERE JS.JS_COMPANY_UID = S.CHULI_UID AND JS.STATUS = 10)||('(建设单位)'), ");
            sql.append(" 'ZJ',(SELECT P.PERSON_NAME FROM PERSON_LIBRARY P WHERE P.JL_PERSON_UID = S.CHULI_UID AND P.STATUS = 1)||('(总监)'), ");
            sql.append(" 'XMJL',(SELECT SP.PERSON_NAME FROM SG_PERSON_LIBRARY SP  WHERE SP.SG_PERSON_UID = S.CHULI_UID AND SP.STATUS = 1)||('(项目经理)') ");
            sql.append(" ) AS CHULI_DUIXIANG, ");
            sql.append(" S.CHULI_DUIXIANG AS TYPE, ");
            sql.append(" PG.GONGCHENG_NAME,S.CHANGE_SCORE,S.YUANYIN,S.CREATE_DATE,TZD.ZG_CODE FROM SCORE S ");
            sql.append(" LEFT JOIN PROJECTS_GONGCHENG PG ");
            sql.append(" ON S.GONGCHENG_UID = PG.GONGCHENG_UID ");
            sql.append(" LEFT JOIN ZG_TZD TZD ");
            sql.append(" ON S.ZG_TZD_UID = TZD.ZG_TZD_UID ");
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

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryCondition1(String json, ScoreVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String sdate = "";
        String edate = "";
        String domresult = "";
        try {
            // 组织查询条件
        	PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += " and sh_status = 1";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            StringBuffer sql = new StringBuffer();
            sql.append("select t.score_uid,(select p.projects_name from projects_gongcheng pg,projects p where p.projects_uid = pg.projects_uid and pg.gongcheng_uid = t.gongcheng_uid) project_name,");
            sql.append("t.change_score, t.yuanyin,(case t.shijian_type when 'ZL' then '质量事件' ");
            sql.append(" when 'AQ' then '安全事件' when 'WM' then '文明事件' when 'GZ' then  '民工工资拖欠'  when 'ZG' then  '项目整改'   when 'WG' then '违规违章'  when 'BZ' then  '优秀表彰' when 'XC' then '现场自动扣分' when 'JX' then '机械设备自动扣分' end) as SHIJIAN_TYPE,");
            sql.append("t.create_date,t.chuli_type  from score t");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            // 设置查询条件
            bs.setFieldDateFormat("create_date", "yyyy/MM/dd");
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryFenshu(String json,String condition2){   	
    	User user = ActionContext.getCurrentUserInThread(); 
    	String userUid = user.getUserSN();
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {   		
    		// 组织查询条件
    		String condition = RequestUtil.getConditionList(json).getConditionWhere();
    		//根据condition添加条件
    		if("dshkf".equals(condition2)){
    			condition += " and KF.CHULI_TYPE = 'WG' and (KF.SH_STATUS = 0 and quanxian_package.get_bg_user_quanxian('3060001',"+userUid+") = 1)";
    		}else if("today".equals(condition2)||"week".equals(condition2)||"month".equals(condition2)){
    			condition +=" and KF.CHULI_TYPE = 'WG'";
    		}
    		String orderFilter = RequestUtil.getOrderFilter(json);    		
    		condition += orderFilter;
    		PageManager page = RequestUtil.getPageManager(json);
    		if (page == null)
    			page = new PageManager();
    		page.setFilter(condition);
    		StringBuffer sql = new StringBuffer();
    		sql.append(" select kf.score_uid, kf.chuli_duixiang,kf.type,kf.gongcheng_name,kf.change_score,");
    		sql.append(" kf.yuanyin, kf.create_date, kf.zg_code,kf.sh_status,kf.chuli_type from (");
    		sql.append(" SELECT S.SCORE_UID,DECODE(S.CHULI_DUIXIANG,'SG',(SELECT SG.COMPANY_NAME FROM SG_ENTERPRISE_LIBRARY SG WHERE SG.SG_COMPANY_UID = S.CHULI_UID AND SG.STATUS = 1)||('(施工单位)'), ");
    		sql.append(" 'JL',(SELECT JL.COMPANY_NAME FROM ENTERPRISE_LIBRARY JL WHERE JL.JL_COMPANY_UID = S.CHULI_UID AND JL.STATUS = 1)||('(监理单位)'), ");
    		sql.append(" 'JS',(SELECT JS.COMPANY_NAME FROM JS_COMPANY JS WHERE JS.JS_COMPANY_UID = S.CHULI_UID AND JS.STATUS = 10)||('(建设单位)'), ");
    		sql.append(" 'ZJ',(SELECT P.PERSON_NAME FROM PERSON_LIBRARY P WHERE P.JL_PERSON_UID = S.CHULI_UID AND P.STATUS = 1)||('(总监)'), ");
    		sql.append(" 'XMJL',(SELECT SP.PERSON_NAME FROM SG_PERSON_LIBRARY SP  WHERE SP.SG_PERSON_UID = S.CHULI_UID AND SP.STATUS = 1)||('(项目经理)') ");
    		sql.append(" ) AS CHULI_DUIXIANG, ");
    		sql.append(" S.CHULI_DUIXIANG AS TYPE, ");
    		sql.append(" PG.GONGCHENG_NAME,S.CHANGE_SCORE,S.YUANYIN,S.CREATE_DATE,TZD.ZG_CODE,S.SH_STATUS,S.CHULI_TYPE FROM SCORE S ");
    		sql.append(" LEFT JOIN PROJECTS_GONGCHENG PG ");
    		sql.append(" ON S.GONGCHENG_UID = PG.GONGCHENG_UID ");
    		sql.append(" LEFT JOIN ZG_TZD TZD ");
    		sql.append(" ON S.ZG_TZD_UID = TZD.ZG_TZD_UID ");
    		sql.append(" ) kf");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);    		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    
    
    public String queryJiafen(String json,String shenhe){   	
    	User user = ActionContext.getCurrentUserInThread(); 
    	String userUid = user.getUserSN();
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {      		
    		// 组织查询条件
    		String condition = RequestUtil.getConditionList(json).getConditionWhere();
    		//根据condition添加条件
    		condition +=" AND SGDT_PACKAGE.IS_MY_PROJECT(B.PROJECTS_UID, "+userUid+") > 0";
    		condition +=" AND ((T.STATUS = 30 AND";
    		condition +=" QUANXIAN_PACKAGE.GET_BG_USER_QUANXIAN('3060002', "+userUid+") = 1) OR";
    		condition +=" (T.STATUS = 50 AND";
    		condition +=" QUANXIAN_PACKAGE.GET_BG_USER_QUANXIAN('3060003', "+userUid+") = 1))";
    		if("1".equals(shenhe)){
    			condition +=" AND T.STATUS IN(10,20,30,40,50)";
    		}else if("2".equals(shenhe)){
    			condition +=" AND T.STATUS =1";
    		}
    		String orderFilter = RequestUtil.getOrderFilter(json);    		
    		condition += orderFilter;
    		PageManager page = RequestUtil.getPageManager(json);
    		if (page == null)
    			page = new PageManager();
    		page.setFilter(condition);
    		StringBuffer sql = new StringBuffer();
    		sql.append(" SELECT T.JFSQ_UID,");
    		sql.append(" DECODE(T.STATUS,-1,'不通过',0,'施工未提交',10,'监理待审',20,'建设方待审',40, '一级待审',50, '二级待审',1,'全部审核完成') ZT,");
    		sql.append(" T.SQ_DANWEI,");
    		sql.append(" B.GONGCHENG_NAME,");
    		sql.append(" ZG_PACKAGE.RET_JF_CONTENT(JFSQ_UID) SQ_CONTENT,");
    		sql.append(" ZG_PACKAGE.RET_JF_DUIXIANG(JFSQ_UID) JF_DUIXIANG,");
    		sql.append(" T.SQ_DATE,");
    		sql.append(" T.STATUS");
    		sql.append(" FROM JFSQ T LEFT JOIN PROJECTS_GONGCHENG B");
    		sql.append(" ON T.GONGCHENG_UID = B.GONGCHENG_UID");
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);   		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    
    
    public String queryjfxx(String json,String JFSQ_UID){   	

    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {   		
    		String sql = "select  j.sq_date,j.sq_danwei,j.description from jfsq j where j.jfsq_uid ="+JFSQ_UID;   		
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);   		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    
    
    public String queryspxx(String json,String JFSQ_UID){   	

    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {      		    		
    		String sql ="select j.JL_SH_YIJIAN,j.JL_SH_DATE,j.JS_SH_YIJIAN,j.JS_SH_DATE,j.XZ_SH_YIJIAN,j.XZ_SH_REN,j.XZ_SH_DATE,j.ERJI_SH_YIJIAN,j.ERJI_SH_REN,j.ERJI_SH_DATE from jfsq j where j.jfsq_uid = "+JFSQ_UID;        
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);   		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    
    
    public String queryjfdx(String json,String JFSQ_UID){   	
    	
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
    		String sql ="SELECT D.JFSQ_UID,DECODE(D.DUIXIANG_ROLE,'SG','施工单位','JL','监理单位','JS','建设单位','XMJL','项目经理','ZJ','总监','AQY','安全员','AQJL','安全监理') DUIXIANG_ROLE,D.DUIXIANG_NAME,D.SCORE FROM JF_DUIXIANG D";
    		sql +=" LEFT JOIN JFSQ J ON J.JFSQ_UID = D.JFSQ_UID WHERE J.JFSQ_UID = "+JFSQ_UID;
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);   		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    
    
    public String queryjfsj(String json,String JFSQ_UID){   	
    	
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
    		String sql ="SELECT  C.JF_CONTENT_UID,Z.WEIGUI_CONTENT,C.DESCRIPTION FROM JF_CONTENT C LEFT JOIN JFSQ J ON J.JFSQ_UID = C.JFSQ_UID ";
    		sql +=" LEFT JOIN ZG_WEIGUI_SJ Z ON C.ZG_WEIGUI_SJ_UID = Z.ZG_WEIGUI_SJ_UID WHERE J.JFSQ_UID = "+JFSQ_UID;
    		BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);   		
    		domresult = bs.getJson();
    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********查询出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    
    
    public String updatestatus(String agree,String JFSTATUS,String JFSQ_UID,String shyj){   	
    	User user = ActionContext.getCurrentUserInThread(); 
    	String username = user.getUsername();
    	Connection conn = DBUtil.getConnection();
    	String domresult = "";
    	try {
    		String sql = "";
    		if("-1".equals(agree)){
    			if("40".equals(JFSTATUS)){
    				sql = "UPDATE JFSQ J SET J.STATUS=-1 , YIJI_SH_DATE=SYSDATE, YIJI_SH_REN='"+username+"',YIJI_SH_YIJIAN='"+shyj+"' WHERE J.JFSQ_UID = "+JFSQ_UID;
    			}else if("50".equals(JFSTATUS)){
    				sql = "UPDATE JFSQ J SET J.STATUS=-1 , ERJI_SH_DATE=SYSDATE, ERJI_SH_REN='"+username+"',ERJI_SH_YIJIAN='"+shyj+"' WHERE J.JFSQ_UID = "+JFSQ_UID;
    			}
    			
    		}else if("1".equals(agree)){
    			if("40".equals(JFSTATUS)){
    				sql = "UPDATE JFSQ J SET J.STATUS=50 , YIJI_SH_DATE=SYSDATE, YIJI_SH_REN='"+username+"',YIJI_SH_YIJIAN='"+shyj+"' WHERE J.JFSQ_UID = "+JFSQ_UID;
        		}else if("50".equals(JFSTATUS)){
    				sql = "UPDATE JFSQ J SET J.STATUS=1 , ERJI_SH_DATE=SYSDATE, ERJI_SH_REN='"+username+"',ERJI_SH_YIJIAN='"+shyj+"' WHERE J.JFSQ_UID = "+JFSQ_UID;
        		}   
    		}   				
    		domresult =String.valueOf(DBUtil.execSql(conn, sql));    		
    	} catch (Exception e) {
    		DaoException.handleMessageException("*********更新出错!*********");
    	} finally {
    		DBUtil.closeConnetion(conn);
    	}
    	return domresult;    	
    }
    //查询待审核 扣分数量
	public String getDshkCount(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "0";
        try {
            // 组织查询条件
            String sql = "select count(0) from score where SH_STATUS = 0 and CHULI_TYPE = 'WG'  ";
            String[][] res = DBUtil.query(conn, sql);
            domresult = res[0][0];
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	//查询待审核 加分数量
	public String getDshjCount(String msg) {
		Connection conn = DBUtil.getConnection();
		String domresult = "0";
		try {
			// 组织查询条件
			String sql = "select count(0) from score where SH_STATUS = 0 and CHULI_TYPE = 'BZ' ";
			String[][] res = DBUtil.query(conn, sql);
			domresult = res[0][0];
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	
	//查询指定的 类型的 审核通过的 扣分总数
		public String getShtgkSum(String id,String type) {
	        Connection conn = DBUtil.getConnection();
	        String domresult = "0";
	        try {
	            // 组织查询条件
	            String sql = "select nvl(sum(CHANGE_SCORE),0) from score where SH_STATUS = 1 and CHULI_TYPE = 'WG' and CHULI_DUIXIANG = '"+type+"' and CHULI_UID = "+id;
	            String[][] res = DBUtil.query(conn, sql);
	            domresult = res[0][0];
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return domresult;
		}
		//查询指定的 类型的 审核通过的 加分总数
		public String getShtgjSum(String id,String type) {
			Connection conn = DBUtil.getConnection();
			String domresult = "0";
			try {
				// 组织查询条件
				String sql = "select nvl(sum(CHANGE_SCORE),0) from score where SH_STATUS = 1 and CHULI_TYPE = 'BZ' and CHULI_DUIXIANG = '"+type+"' and CHULI_UID = "+id;
				String[][] res = DBUtil.query(conn, sql);
				domresult = res[0][0];
			} catch (Exception e) {
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}
			return domresult;
		}
    // 在此可加入其它方法

	public String queryById(String sUid) {
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            //String sql = "SELECT * FROM " + "SCORE t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT S.SCORE_UID,DECODE(S.CHULI_DUIXIANG,'SG',(SELECT SG.COMPANY_NAME FROM SG_ENTERPRISE_LIBRARY SG WHERE SG.SG_COMPANY_UID = S.CHULI_UID AND SG.STATUS = 1)||('(施工单位)'), ");
            sql.append(" 'JL',(SELECT JL.COMPANY_NAME FROM ENTERPRISE_LIBRARY JL WHERE JL.JL_COMPANY_UID = S.CHULI_UID AND JL.STATUS = 1)||('(监理单位)'), ");
            sql.append(" 'JS',(SELECT JS.COMPANY_NAME FROM JS_COMPANY JS WHERE JS.JS_COMPANY_UID = S.CHULI_UID AND JS.STATUS = 10)||('(建设单位)'), ");
            sql.append(" 'ZJ',(SELECT P.PERSON_NAME FROM PERSON_LIBRARY P WHERE P.JL_PERSON_UID = S.CHULI_UID AND P.STATUS = 1)||('(总监)'), ");
            sql.append(" 'XMJL',(SELECT SP.PERSON_NAME FROM SG_PERSON_LIBRARY SP  WHERE SP.SG_PERSON_UID = S.CHULI_UID AND SP.STATUS = 1)||('(项目经理)') ");
            sql.append(" ) AS CHULI_DUIXIANG_SV, S.CHULI_DUIXIANG,");
            sql.append(" S.CHULI_UID,S.CHULI_TYPE, S.SH_STATUS,");
            sql.append(" S.CHANGE_SCORE,S.YUANYIN,S.YIJU,DECODE(S.CHULI_TYPE,'WG','扣分','BZ','加分') AS CHULI_TYPE_SV,S.JIEGUO  FROM SCORE S ");
            sql.append(" WHERE S.SCORE_UID = '"+sUid+"'");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
            domresult = bs.getJson();           
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	
	/**
	 * 人员扣分审核
	 */
	public String updatePersonSh(String msg) {
		User user = ActionContext.getCurrentUserInThread();
		ScoreVO vo = new ScoreVO();
		try {
	        JSONArray list = vo.doInitJson(msg);
	        JSONObject obj= (JSONObject) list.get(0);
	        String sUid = obj.getString("SCORE_UID");
	        String SH_STATUS = obj.getString("SH_STATUS");
	        String SH_YJ1 = obj.getString("SH_YJ1");
	        vo.setScore_uid(sUid);
	        vo.setSh_ren1(user.getUserSN());
	        vo.setSh_date1(new Date());
	        vo.setSh_yj1(SH_YJ1);
	        vo.setSh_status(SH_STATUS);
	        this.update(vo);
	        setPersonScore(vo.getChuli_uid(),vo.getChuli_duixiang(),vo.getChuli_type(),vo.getChange_score());
		} catch (Exception e) {
			e.printStackTrace();
		}        
		return null;
	}
	
	/**
	 * 企业扣分审核
	 */
	public String updateCompanySh(String msg) {
		User user = ActionContext.getCurrentUserInThread();
		ScoreVO vo = new ScoreVO();
		try {
			JSONArray list = vo.doInitJson(msg);
			JSONObject obj= (JSONObject) list.get(0);
			String sUid = obj.getString("SCORE_UID");
			String SH_STATUS = obj.getString("SH_STATUS");
			String SH_YJ1 = obj.getString("SH_YJ1");
			vo.setScore_uid(sUid);
			vo.setSh_ren1(user.getUserSN());
			vo.setSh_date1(new Date());
			vo.setSh_yj1(SH_YJ1);
			vo.setSh_status(SH_STATUS);
			this.update(vo);
			setCompanyScore(vo.getChuli_uid(),vo.getChuli_duixiang(),vo.getChuli_type(),vo.getChange_score());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 
	 * @param cluid    处理对象id
	 * @param com_type 人员类型 'XMJL'-项目经理,'ZJ'-总监
	 * @param type 	       扣分类型  'WG'-违规,'BZ'-表彰
	 * @param score    分值
	 * @return
	 */
	public String setPersonScore(String cluid,String p_type,String type,String score) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call sgdt_package.set_person_xy(?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.setInt(1, StringUtils.isNotBlank(cluid)?Integer.parseInt(cluid):0);
			cstmt.setString(2, p_type);
			cstmt.setString(3, type);
			cstmt.setInt(4, StringUtils.isNotBlank(score)?Integer.parseInt(score):0);
			
			cstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}
	
	/**
	 * 
	 * @param cluid    处理对象id
	 * @param com_type 企业类型 'SG'-施工,'JL'-监理,'JS'-建设
	 * @param type 	       扣分类型  'WG'-违规,'BZ'-表彰
	 * @param score    分值
	 * @return
	 */
	public String setCompanyScore(String cluid,String com_type,String type,String score) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call sgdt_package.set_company_xy(?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.setInt(1, StringUtils.isNotBlank(cluid)?Integer.parseInt(cluid):0);
			cstmt.setString(2,com_type);
			cstmt.setString(3, type);
			cstmt.setInt(4, StringUtils.isNotBlank(score)?Integer.parseInt(score):0);
			
			cstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}

}
