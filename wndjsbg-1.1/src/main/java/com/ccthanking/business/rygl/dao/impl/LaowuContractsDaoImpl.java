/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.rygl.LaowuContractsDao.java
 * 创建日期： 2015-03-23 下午 05:19:19
 * 功能：   劳务合同
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-23 下午 05:19:19  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.rygl.vo.LaowuContractsVO;
import com.ccthanking.business.rygl.dao.LaowuContractsDao;
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
 * <p> LaowuContractsDao.java </p>
 * <p> 功能：劳务合同 </p>
 *
 * <p><a href="LaowuContractsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-23
 * 
 */

@Component
public class LaowuContractsDaoImpl  extends BsBaseDaoTJdbc implements LaowuContractsDao {

    public String queryCondition(String json, LaowuContractsVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
           // condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT T.LAOWU_CONTRACTS_UID,T.CONTRACTS_CODE,T.LAOWU_CONTRACTS_NAME, ");
            sql.append(" M.MINGONG_NAME AS YF, C.COMPANYS_NAME AS JF,T.WORK_PAY,T.NO_WORK_PAY ");
            sql.append("  FROM LAOWU_CONTRACTS T ");
            sql.append("  LEFT JOIN MINGONG M ");
            sql.append("  ON T.MINGONG_UID = M.MINGONG_UID ");
            sql.append("  LEFT JOIN COMPANYS C ");
            sql.append("  ON T.COMPANYS_UID = C.COMPANYS_UID ");
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

	public String queryById(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            String sql = "select * from laowu_contracts";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            domresult = bs.getJson();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	DBUtil.closeConnetion(conn);
        }
		return domresult;
	}
    
    // 在此可加入其它方法

}
