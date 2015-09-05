/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.jxsb.AzPersonShenheDao.java
 * 创建日期： 2014-12-15 上午 10:48:26
 * 功能：   安装人员审核
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 上午 10:48:26  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.azqy.vo.AzPersonShenheVO;
import com.ccthanking.business.dtgl.jxsb.dao.AzPersonShenheDao;
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
 * <p> AzPersonShenheDao.java </p>
 * <p> 功能：安装人员审核 </p>
 *
 * <p><a href="AzPersonShenheDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

@Component
public class AzPersonShenheDaoImpl  extends BsBaseDaoTJdbc implements AzPersonShenheDao {

    public String queryCondition(String json, AzPersonShenheVO vo, Map map){
    
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

            String sql = "select * from (SELECT t.*,c.company_name as COMPANY_NAME,u.user_name as shenhename FROM AZ_PERSON_SHENHE t left join az_company c on t.AZ_COMPANY_UID=c.AZ_COMPANY_UID left join users u on u.users_uid=t.SHENHE_REN ) ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("GONGZHONG", "GONGZHONG");//工种
            bs.setFieldDic("SEX", "SEX");//工种

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
    
    public String updateLiZhiByShFz(String shenFz){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	conn.setAutoCommit(false);
        	  String sql = "delete AZ_person where shenfenzheng='"+shenFz+"'";
              DBUtil.exec(conn,sql);    
              String upsql = "update az_person_shenhe set status='40' where shenfenzheng='"+shenFz+"' and shenhe_jieguo='1' and status='10' and az_company_uid not in("+user.getUserSN()+")";
              DBUtil.exec(conn,upsql);
              conn.commit();
            domresult="ok";
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }  
    
    // 在此可加入其它方法

}
