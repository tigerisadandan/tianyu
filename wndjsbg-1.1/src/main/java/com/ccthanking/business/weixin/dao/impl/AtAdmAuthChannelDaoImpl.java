/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.weixin.AtAdmAuthChannelDao.java
 * 创建日期： 2014-11-27 上午 10:22:47
 * 功能：   栏目发布权限
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-27 上午 10:22:47  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.weixin.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.weixin.dao.AtAdmAuthChannelDao;
import com.ccthanking.weixin.vo.AtAdmAuthChannelVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RandomGUID;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> AtAdmAuthChannelDao.java </p>
 * <p> 功能：栏目发布权限 </p>
 *
 * <p><a href="AtAdmAuthChannelDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-27
 * 
 */

@Component
public class AtAdmAuthChannelDaoImpl  extends BsBaseDaoTJdbc implements AtAdmAuthChannelDao {

    public String queryCondition(String json, AtAdmAuthChannelVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
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

            String sql = "select * from (SELECT vc.fr as fr,vc.pfr as pfr,vc.account as account, vc.name as name,vc.user_sn as usersn, "+
            	" ac.channel_uid as channel_uid,ac.authid as authid, ac.adm_auth_channel_uid as adm_auth_channel_uid, "+
            	" (select wc.channel_name from WX_CHANNEL wc where wc.channel_uid=ac.channel_uid) as channel_name  "+
            	" FROM AT_ADM_AUTH_CHANNEL ac "+
            	" left join v_at_auth_channel vc on vc.account=ac.authid) t ";
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

	public void awardToUsers(String channelid, String[] userids,
			User user) throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteSql = "DELETE FROM AT_ADM_AUTH_CHANNEL F WHERE F.channel_uid ='" + channelid + "'";
			boolean bDel = DBUtil.exec(conn, deleteSql);

			if (bDel) {
				for (int i = 0; i < userids.length; i++) {
					String uuid=new RandomGUID().toString();
					String addSql = "INSERT INTO AT_ADM_AUTH_CHANNEL(ADM_AUTH_CHANNEL_UID,CHANNEL_UID,AUTHID,CREATED_UID,CREATED_NAME,CREATED_DATE)" +
						" VALUES ('"+uuid + "','"+channelid + "','" + userids[i] + "','"+user.getAccount()+"','"+user.getName()+"',sysdate)";
					DBUtil.exec(conn, addSql);
				}
			}
			conn.commit();

		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
			
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}
    
    // 在此可加入其它方法

}
