/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.yhzg.ZgDafuDao.java
 * 创建日期： 2015-04-21 下午 01:27:03
 * 功能：   整改答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:27:03  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.yhzg.vo.ZgDafuVO;
import com.ccthanking.business.yhzg.dao.ZgDafuDao;
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
 * <p> ZgDafuDao.java </p>
 * <p> 功能：整改答复 </p>
 *
 * <p><a href="ZgDafuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Component
public class ZgDafuDaoImpl  extends BsBaseDaoTJdbc implements ZgDafuDao {

    public String queryCondition(String json, ZgDafuVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
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

            //String sql = "SELECT * FROM " + "ZG_DAFU t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT T.ZG_DAFU_UID,T.ZG_TZD_UID,  T.DAFU_DATE, T.DAFU_END_DATE, T.DAFU_CONTENT,   ");
            sql.append(" DECODE(T.HOUXU_CL,'1','未处理','2','重新整改','3','闭合') AS HOUXU_CL , ");
            sql.append(" T.HOUXU_CL AS TYPE,Z.ZG_XINGZHI_UID ");
            sql.append("  FROM ZG_DAFU T LEFT JOIN ZG_TZD Z ");
            sql.append("  ON T.ZG_TZD_UID = Z.ZG_TZD_UID ");
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

	public List<?> queryPrint(String tzdfuid) {
		Connection conn = DBUtil.getConnection();
		List<?> map = null;
        User user = ActionContext.getCurrentUserInThread();
        StringBuffer sql = new StringBuffer();
		try {
            sql.append(" select * from zg_dafu d where d.zg_dafu_uid = '"+tzdfuid+"'");
			map =  DBUtil.queryReturnList(conn, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}

	public List<?> queryPrint2(String tzduid) {
		Connection conn = DBUtil.getConnection();
		List<?> map = null;
        User user = ActionContext.getCurrentUserInThread();
        StringBuffer sql = new StringBuffer();
		try {
            sql.append(" select * from zg_tzd where zg_tzd_uid = '"+tzduid+"'");
			map =  DBUtil.queryReturnList(conn, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}
    
    // 在此可加入其它方法

}
