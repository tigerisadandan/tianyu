/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.yhzg.ZgContentDao.java
 * 创建日期： 2015-04-21 下午 01:24:35
 * 功能：   需整改的安全隐患
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:24:35  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Map;

import net.sf.json.JSONObject;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.yhzg.vo.ZgContentVO;
import com.ccthanking.business.yhzg.dao.ZgContentDao;
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
 * <p> ZgContentDao.java </p>
 * <p> 功能：需整改的安全隐患 </p>
 *
 * <p><a href="ZgContentDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Component
public class ZgContentDaoImpl  extends BsBaseDaoTJdbc implements ZgContentDao {

    public String queryCondition(String json, ZgContentVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
           // condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            //String sql = "SELECT * FROM " + "ZG_CONTENT t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT T.Zg_Content_Uid, T.DESCRIPTION,decode(WS.WG_DENGJI,'1','一般','2','较严重','3','严重') as WG_DENGJI,");
            sql.append(" WS.XMJL_SCORE,WS.ZJ_SCORE,WS.JSDW_SCORE,WS.SGDW_SCORE,WS.JLDW_SCORE ,");
            sql.append(" Y.NAME,WS.WEIGUI_CONTENT AS WG_MIAOSHU, TI.CODE AS TIAOLI, DC.DAFU_CONTENT FROM ZG_CONTENT T ");
            sql.append(" LEFT JOIN ZG_WEIGUI_SJ WS ");
            sql.append(" ON T.ZG_WEIGUI_SJ_UID = WS.ZG_WEIGUI_SJ_UID ");
            sql.append(" LEFT JOIN ZG_YINHUAN Y ");
            sql.append(" ON WS.ZG_YINHUAN_UID = Y.ZG_YINHUAN_UID ");
            sql.append(" LEFT JOIN ZG_TIAOLI TI ");
            sql.append(" ON WS.ZG_TIAOLI_UID = TI.ZG_TIAOLI_UID ");
            sql.append(" LEFT JOIN ZG_DAFU_CONTENT DC ");
            sql.append(" ON DC.ZG_CONTENT_UID = T.ZG_CONTENT_UID ");
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

	public String queryByTzdUid(String tzdUid) {
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件

            //String sql = "SELECT * FROM " + "ZG_CONTENT t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT T.*");
            //sql.append(" ,decode(WS.WG_DENGJI,'1','一般','2','较严重','3','严重') as WG_DENGJI,");
           // sql.append(" WS.XMJL_SCORE,WS.ZJ_SCORE,WS.JSDW_SCORE,WS.SGDW_SCORE,WS.JLDW_SCORE ,");
            //sql.append(" Y.NAME,WS.WEIGUI_CONTENT ");
            sql.append(" FROM ZG_CONTENT T ");
           // sql.append(" LEFT JOIN ZG_WEIGUI_SJ WS ");
           // sql.append(" ON T.ZG_WEIGUI_SJ_UID = WS.ZG_WEIGUI_SJ_UID ");
            //sql.append(" LEFT JOIN ZG_YINHUAN Y ");
            //sql.append(" ON WS.ZG_YINHUAN_UID = Y.ZG_YINHUAN_UID ");
            sql.append("  WHERE T.ZG_TZD_UID = '"+tzdUid+"'");
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
	 * 调用存储过程 计算扣分情况
	 */
	public String queryScore(String tzdUid) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{ call set_zg_score(?)}";
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

	public String getContent(String tzdUid) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			String sqlCall = "{?=call ret_zg_content(?)}";
			CallableStatement cstmt = conn.prepareCall(sqlCall);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, StringUtils.isNotBlank(tzdUid)?Integer.parseInt(tzdUid):0);
			cstmt.execute();
			String content =  cstmt.getString(1);
			//int status = cstmt.getInt(4);
			//String reason = cstmt.getString(5);
			//obj.put("STATUS", status);
			//obj.put("REASON", reason);
			obj.put("CONTENT", content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}

	public String getPicNum(String tzdUid) {
        Connection conn = DBUtil.getConnection();
        String domresult = "0";
        try {
            // 组织查询条件
            String sql = "select count(0) from at_fileupload f where f.business_sub_type = 'ZG_TZD' and f.target_uid = '' ";
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

}
