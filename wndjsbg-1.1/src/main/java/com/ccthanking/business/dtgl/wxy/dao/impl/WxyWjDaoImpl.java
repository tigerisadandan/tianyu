/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.wxy.WxyWjDao.java
 * 创建日期： 2015-04-29 下午 03:06:53
 * 功能：   网架及索膜结构安装工程提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-29 下午 03:06:53  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.wxy.dao.WxyWjDao;
import com.ccthanking.business.dtgl.wxy.vo.WxyWjVO;
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
 * <p> WxyWjDao.java </p>
 * <p> 功能：网架及索膜结构安装工程提示单 </p>
 *
 * <p><a href="WxyWjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-29
 * 
 */

@Component
public class WxyWjDaoImpl  extends BsBaseDaoTJdbc implements WxyWjDao {

    public String queryCondition(String json, WxyWjVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "WXY_WJ t";
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

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public boolean tuiHui(String gcId) {
		User user = ActionContext.getCurrentUserInThread();
	    
        Connection conn = DBUtil.getConnection();
        boolean flag = false;
        try {

            String sql = "update wxy_wj set status = 32 where gongcheng_uid = "+gcId;
            flag = DBUtil.execUpdateSql(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********修改出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return flag;
	}
    // 在此可加入其它方法

}
