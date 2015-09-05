/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.project.JsCompanyDao.java
 * 创建日期： 2014-07-02 下午 12:05:19
 * 功能：   建设单位
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:05:19  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.dao.JsCompanyDao;
import com.ccthanking.business.project.vo.JsCompanyVO;
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
 * <p> JsCompanyDao.java </p>
 * <p> 功能：建设单位 </p>
 *
 * <p><a href="JsCompanyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */

@Component
public class JsCompanyDaoImpl  extends BsBaseDaoTJdbc implements JsCompanyDao {

    public String queryCondition(String json, JsCompanyVO vo, Map map){
    
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

            String sql = "SELECT t.*,(select u.user_name from users u where u.users_uid=t.shenhe_ren) as shenhename,(select u.user_name from users u where u.users_uid=t.ZYXX_SHENHE_REN) as ZYXX_shenhename FROM JS_COMPANY t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            // 设置查询条件
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy-MM-dd HH:mm:ss");
            // bs.setFieldThousand("DYJLSDZ");
            bs.setFieldDic("ZZDJ", "AZ_ZZDJ");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询注册企业信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<?> getCompanyCount() {
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
        	String sql="select "+
        	"(select count(j.js_company_uid) from js_company j where j.status='30') dsh, "+
        	"(select count(j.js_company_uid) from js_company j where j.status='20') wtg, "+
        	"(select count(j.js_company_uid) from js_company j where j.status='10')  ytg "+
        	"from dual";
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询注册企业审核结果统计信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
	
	public List<?> getCompanyZyxxCount() {//审核重要信息
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
        	String sql="select "+
        	"(select count(j.js_company_uid) from js_company j where j.ZYXX_STATUS='30') dsh, "+
        	"(select count(j.js_company_uid) from js_company j where j.ZYXX_STATUS='20') wtg, "+
        	"(select count(j.js_company_uid) from js_company j where j.ZYXX_STATUS='10')  ytg "+
        	"from dual";
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询注册企业审核结果统计信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
    
    // 在此可加入其它方法

}
