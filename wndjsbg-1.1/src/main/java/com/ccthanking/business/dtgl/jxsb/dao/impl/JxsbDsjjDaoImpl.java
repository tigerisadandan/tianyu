/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.jxsb.JxsbDsjjDao.java
 * 创建日期： 2014-12-25 下午 01:18:44
 * 功能：   机械设备顶升加节记录表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-25 下午 01:18:44  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.azqy.vo.JxsbDsjjVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbDsjjDao;
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
 * <p> JxsbDsjjDao.java </p>
 * <p> 功能：机械设备顶升加节记录表 </p>
 *
 * <p><a href="JxsbDsjjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-25
 * 
 */

@Component
public class JxsbDsjjDaoImpl  extends BsBaseDaoTJdbc implements JxsbDsjjDao {

    public String queryCondition(String json, JxsbDsjjVO vo, Map map){
    
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

            String sql = "select * from jxsb_dsjj d " +
            		"left join JXSB_SYGL s on d.jxsb_sygl_uid=s.jxsb_sygl_uid " +
            		"left join JXSB_SYDJ j on s.jxsb_sygl_uid=j.jxsb_sygl_uid " +
            		"left join JXSB_JCYS c on s.jxsb_sygl_uid=c.jxsb_sygl_uid " +
            		"left join JXSB_AZGC g on s.jxsb_sygl_uid=g.jxsb_sygl_uid " +
            		"left join az_company a on d.BY_COMPANY_DL_UID=a.AZ_COMPANY_UID " +
            		"left join jxsb b on b.jxsb_uid=s.jxsb_uid  ";
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
