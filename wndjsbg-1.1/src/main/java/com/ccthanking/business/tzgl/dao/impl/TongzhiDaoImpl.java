/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.tzgl.TongzhiDao.java
 * 创建日期： 2015-05-15 下午 03:34:18
 * 功能：   通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-15 下午 03:34:18  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.tzgl.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.tzgl.dao.TongzhiDao;
import com.ccthanking.business.tzgl.vo.TongzhiVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.ibm.icu.text.SimpleDateFormat;


/**
 * <p> TongzhiDao.java </p>
 * <p> 功能：通知管理 </p>
 *
 * <p><a href="TongzhiDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">喻敏</a>
 * @version 0.1
 * @since 2015-05-15
 * 
 */

@Component
public class TongzhiDaoImpl  extends BsBaseDaoTJdbc implements TongzhiDao {

    public String queryCondition(String json, TongzhiVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            Date data = new Date();
            String year = (new SimpleDateFormat("yyyy")).format(data);
            if(condition.indexOf("and CREATE_DATE = 'BN'")!=-1){
            	condition = condition.replace("and CREATE_DATE = 'BN'","and to_char(tz.CREATE_DATE,'yyyy') = '"+year+"'");
            }else if(condition.indexOf("and CREATE_DATE = 'LN'")!=-1){
            	condition = condition.replace("and CREATE_DATE = 'LN'","and to_char(tz.CREATE_DATE,'yyyy') < '"+year+"'");
            }
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            String sql = "SELECT decode(tz.TZ_LEVEL,'YB','一般通知','ZY','重要通知') as TZ_LEVEL,tz.TONGZHI_TITLE,u.user_name as CREATE_BY,tz.CREATE_DATE FROM " 
            	+ "TONGZHI tz left join TONGZHI_GONGCHENG gc on tz.TONGZHI_UID=gc.TONGZHI_UID left join USERS u on u.users_uid=tz.create_by";
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
