/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.JxsbCxgzDao.java
 * 创建日期： 2015-01-26 下午 04:31:09
 * 功能：   机械设备拆卸告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 04:31:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.bzwj.GongCheng;
import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgzVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbSydjVO;
import com.ccthanking.business.dtgl.dao.JxsbCxgzDao;
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
 * <p> JxsbCxgzDao.java </p>
 * <p> 功能：机械设备拆卸告知 </p>
 *
 * <p><a href="JxsbCxgzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Component
public class JxsbCxgzDaoImpl  extends BsBaseDaoTJdbc implements JxsbCxgzDao {

    public String queryCondition(String json, JxsbCxgzVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件 
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += " and c.SHENHE_STATUS in (30,40,41,50) ";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "select (select u.user_name from users u where u.users_uid=c.XZSH_REN) XZSH_NAME,g.GC_NAME,g.GC_ADDRESS,g.XMJL,g.LXDH,g.SHEBEI_NAME,g.GGXH,c.* from JXSB_CXGZ c " +
            		"left join jxsb_sygl g on c.jxsb_sygl_uid=g.jxsb_sygl_uid ";
            		
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy年MM日dd");// 计量月份
            bs.setFieldDateFormat("SHOULI_DATE", "yyyy年MM日dd");// 计量月份 
            bs.setFieldDateFormat("XZSH_DATE", "yyyy年MM日dd");// 计量月份 
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy年MM日dd");// 计量月份 

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
    
    public String queryRy(String json, JxsbCxgzVO vo, Map map) {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition +=" and JXSB_STEP = 'CXGZ' ";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = " select * from JXSB_CZRY";
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

    // 在此可加入其它方法

}
