/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.jxsb.JxsbBywxDao.java
 * 创建日期： 2014-12-24 下午 05:10:41
 * 功能：   机械设备保养维修记录表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-24 下午 05:10:41  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao.impl;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.azqy.vo.JxsbBywxVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbBywxDao;
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
 * <p> JxsbBywxDao.java </p>
 * <p> 功能：机械设备保养维修记录表 </p>
 *
 * <p><a href="JxsbBywxDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-24
 * 
 */

@Component
public class JxsbBywxDaoImpl  extends BsBaseDaoTJdbc implements JxsbBywxDao {

    public String queryCondition(String json, JxsbBywxVO vo, Map map,HttpServletRequest request){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";

        String start=request.getParameter("start");
        String end=request.getParameter("end");
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            if(!"".equals(start)&&start!=null){
            	condition += " and BY_DATE >= to_date('"+start+"','yyyy-mm-dd') ";
            }
            if(!"".equals(end)&&end!=null){
            	condition += " and BY_DATE <= to_date('"+end+"','yyyy-mm-dd') ";
            }
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM JXSB_BYWX b left join JXSB_SYGL s on b.JXSB_SYGL_UID=s.JXSB_SYGL_UID left join az_company c on b.by_company_dl_uid=c.az_company_uid ";
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
