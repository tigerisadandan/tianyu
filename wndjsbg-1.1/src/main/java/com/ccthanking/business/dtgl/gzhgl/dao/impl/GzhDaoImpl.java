/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.gzhgl.GzhDao.java
 * 创建日期： 2015-05-27 上午 10:40:51
 * 功能：   告知会
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-27 上午 10:40:51  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.gzhgl.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.gzhgl.dao.GzhDao;
import com.ccthanking.business.dtgl.gzhgl.vo.GzhVO;
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
 * <p> GzhDao.java </p>
 * <p> 功能：告知会 </p>
 *
 * <p><a href="GzhDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-27
 * 
 */

@Component
public class GzhDaoImpl  extends BsBaseDaoTJdbc implements GzhDao {

    public String queryCondition(String json, GzhVO vo, Map map){
    
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
            
            String sql = "SELECT * FROM " + "GZH t	";
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
            bs.setFieldDateFormat("HUIYI_DATE", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String querySingleCondition(String json){
        
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
            
            StringBuilder sql = new StringBuilder("select distinct t.* from gzh t");
            sql.append(" inner join gzh_ry gr on t.gzh_uid = gr.gzh_uid");
            
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            
            bs.setFieldDateFormat("HUIYI_DATE", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd HH:mm");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String queryUndoneProject(String json) {
		
		User usre = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			 // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            condition += " and pg.gc_status = 0";
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
			String sql = "select distinct p.projects_uid,p.projects_name,jcu.company_name from projects p  inner join projects_gongcheng pg on p.projects_uid = pg.projects_uid  inner join js_company jcu on p.js_company_uid = jcu.js_company_uid" +
					" inner join dt_gc_sgbb dgs on dgs.gongcheng_uid = pg.gongcheng_uid" +
					" inner join sgbb bb on bb.sgbb_uid = dgs.sgbb_uid " +
					" inner join sg_enterprise_library sel on bb.sg_company_uid = sel.sg_company_uid";
			BaseResultSet bs = DBUtil.query(conn,sql,page);
			domresult = bs.getJson();
		} catch (Exception e) {
			 e.printStackTrace();
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally {
            DBUtil.closeConnetion(conn);
        }
		return domresult;
	}
	
	
	public String querySgPersonByGC(String json) {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			 // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            condition += " and pg.gc_status = 0 and gw.names in ('总监','安全监理','项目负责人','安全员') ";
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            String sql = "select distinct sr.sg_person_uid,p.projects_name,sr.sg_name as person_name,gw.names from projects p inner join projects_gongcheng pg on p.projects_uid = pg.projects_uid inner join dt_gc_sgry dgs on dgs.gongcheng_uid = pg.gongcheng_uid inner join sgbb_ry sr on sr.sg_person_uid = dgs.sg_person_uid inner join gangwei gw on gw.gangwei_uid  = sr.gangwei_uid";
            BaseResultSet bs = DBUtil.query(conn,sql,page);
            domresult = bs.getJson();
		} catch (Exception e) {
			e.printStackTrace();
			DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	
    
    // 在此可加入其它方法

}
