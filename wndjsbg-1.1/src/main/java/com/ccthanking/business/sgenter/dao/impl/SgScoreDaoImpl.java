/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgenter.SgScoreDao.java
 * 创建日期： 2014-06-09 上午 09:35:20
 * 功能：   分数
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 上午 09:35:20  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgenter.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sgenter.dao.SgScoreDao;
import com.ccthanking.business.sgenter.vo.SgScoreVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> SgScoreDao.java </p>
 * <p> 功能：分数 </p>
 *
 * <p><a href="SgScoreDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Component
public class SgScoreDaoImpl  extends BsBaseDaoTJdbc implements SgScoreDao {

    public String queryCondition(String json, SgScoreVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            condition += " and sysdate between t.begin_date and t.end_date ";
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT t.*,n.ZHIZHAO,n.SHUIWU,n.WAIDI_Y FROM " + "SG_SCORE t LEFT JOIN SG_ENTERPRISE_LIBRARY n on n.sg_company_uid = t.sg_company_uid and n.status = 1 ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            
            
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public JSONArray getJxInfo(String uid) {

        Connection conn = DBUtil.getConnection();
        JSONObject obj = null;
        JSONArray arr = new JSONArray();
        try {
        	
         	String sql = "select t.SCORE_TYPE,t.CR_LEVEL,t.SCORE_NAME,t.VALID_DATE,t.SCORE,t.SERIAL_NO,t.DESCRIPTION "
         		+" from SG_SCORE_DETAIL t where t.SCORE_TYPE='JL' AND t.SG_SCORE_UID = " +uid +" order by t.SERIAL_NO";
         	String[][] res = DBUtil.query(conn, sql);
         	
         	if (res!=null) {
         		for (int i = 0; i < res.length; i++) {
	             	obj = new JSONObject();
	             	obj.put("SCORE_TYPE", res[i][0]);
	             	obj.put("CR_LEVEL", Pub.getDictValueByCode("CR_LEVEL",res[i][1]));
	             	obj.put("SCORE_NAME", res[i][2]);
	             	obj.put("VALID_DATE", res[i][3].substring(0,10));
	             	obj.put("SCORE", res[i][4]);
	             	obj.put("DESCRIPTION", res[i][6]);
	             	arr.add(obj);
         		}
             }
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return arr;
	}

	public JSONArray getScoreInfo(String uid) {
	    
        Connection conn = DBUtil.getConnection();
        JSONObject obj = null;
        JSONArray arr = new JSONArray();
        try {
        	String sql = "select t.SG_SCORE_UID,t.SG_COMPANY_UID,t.COMPANY_NAME,t.DENGLU_CODE,t.SCORE,t.BEGIN_DATE,t.END_DATE,t.CREATED_DATE,t.DESCRIPTION,"
         		+" (select sum(n.score) from sg_score_detail n where SCORE_TYPE in ('JB','GC','JL') and n.sg_score_uid = t.sg_score_uid) JB_SCORE, "
         		+" (select sum(n.score) from sg_score_detail n where SCORE_TYPE = 'KH' and n.sg_score_uid = t.sg_score_uid) RC_SCORE "
        		+" from sg_score t where sysdate between t.begin_date and t.end_date ";
        		
        	if(StringUtils.isNotBlank(uid)){
        		sql+= " and t.sg_score_uid = " +uid;;
        	}
        	sql+=" order by t.created_date desc";
        	String[][] res = DBUtil.query(conn, sql);
        	
            if (res!=null) {
            	for (int i = 0; i < res.length; i++) {
            		obj = new JSONObject();
                	obj.put("COMPANY_NAME", res[i][2]);
                	obj.put("DENGLU_CODE", res[i][3]);
                	obj.put("SCORE", res[i][4]);
                	obj.put("BEGIN_DATE", res[i][5].substring(0,10));
                	obj.put("END_DATE", res[i][6].substring(0,10));
                	obj.put("CREATED_DATE", res[i][7].subSequence(0, 10));
                	obj.put("DESCRIPTION", res[i][8]);
                	obj.put("JB_SCORE", res[i][9]);
                	obj.put("RC_SCORE", res[i][10]);
                	
                	arr.add(obj);
				}
			}
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return arr;
	}

	public JSONArray getXmInfo(String uid) {
	    
        Connection conn = DBUtil.getConnection();
        JSONObject obj = null;
        JSONArray arr = new JSONArray();
        try {
        	
         	String sql = "select t.SCORE_TYPE,t.CR_LEVEL,t.SCORE_NAME,t.VALID_DATE,t.SCORE,t.SERIAL_NO,t.DESCRIPTION from SG_SCORE_DETAIL t where t.SCORE_TYPE='GC' AND t.SG_SCORE_UID = " +uid +" order by t.SERIAL_NO";
         	String[][] res = DBUtil.query(conn, sql);
         	
        	if (res!=null) {
            	 for (int i = 0; i < res.length; i++) {
	             	obj = new JSONObject();
	             	obj.put("SCORE_TYPE", res[i][0]);
	             	obj.put("CR_LEVEL", res[i][1]);
	             	obj.put("SCORE_NAME", res[i][2]);
	             	obj.put("VALID_DATE", res[i][3].substring(0,10));
	             	obj.put("SCORE", res[i][4]);
	             	obj.put("DESCRIPTION", res[i][6]);
	             	arr.add(obj);
            	 }
             }
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return arr;
	}
	
	public JSONArray getJCscore(String uid) {
	    
        Connection conn = DBUtil.getConnection();
        JSONObject obj = null;
        JSONArray arr = new JSONArray();
        try {
        	
         	String sql = "select t.SCORE_TYPE,t.CR_LEVEL,t.SCORE_NAME,t.VALID_DATE,t.SCORE,t.SERIAL_NO,t.DESCRIPTION from SG_SCORE_DETAIL t where t.SCORE_TYPE='JB' AND t.SG_SCORE_UID = " +uid +" order by t.SERIAL_NO";
         	String[][] res = DBUtil.query(conn, sql);
         	
         	if (res!=null) {
         		for (int i = 0; i < res.length; i++) {
            		obj = new JSONObject();
                  	obj.put("SCORE_TYPE", res[i][0]);
                  	obj.put("CR_LEVEL", res[i][1]);
                  	obj.put("SCORE_NAME", res[i][2]);
                  	obj.put("VALID_DATE", res[i][3].substring(0,10));
                  	obj.put("SCORE", res[i][4]);
                  	obj.put("DESCRIPTION", res[i][6]);
                  	arr.add(obj);
				}
        	}
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return arr;
	}
  

}
