/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.wxy.WxyGjgDao.java
 * 创建日期： 2015-04-28 下午 06:32:59
 * 功能：   钢结构工程提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-28 下午 06:32:59  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.wxy.dao.WxyGjgDao;
import com.ccthanking.business.dtgl.wxy.vo.WxyGjgVO;
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
 * <p> WxyGjgDao.java </p>
 * <p> 功能：钢结构工程提示单 </p>
 *
 * <p><a href="WxyGjgDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-28
 * 
 */

@Component
public class WxyGjgDaoImpl  extends BsBaseDaoTJdbc implements WxyGjgDao {

    public String queryCondition(String json, WxyGjgVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "WXY_GJG t";
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

            String sql = "update wxy_gjg set status = 32 where gongcheng_uid = "+gcId;
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
