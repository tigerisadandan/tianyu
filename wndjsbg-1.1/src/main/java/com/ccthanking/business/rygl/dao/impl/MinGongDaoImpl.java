/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.rygl.MinGongDao.java
 * 创建日期： 2015-03-23 下午 12:08:31
 * 功能：   农民工信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-23 下午 12:08:31  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.ccthanking.business.dtgl.rygl.vo.*;
import com.ccthanking.business.rygl.dao.MinGongDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> MinGongDao.java </p>
 * <p> 功能：农民工信息 </p>
 *
 * <p><a href="MinGongDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-23
 * 
 */

@Component
public class MinGongDaoImpl  extends BsBaseDaoTJdbc implements MinGongDao {

    public String queryCondition(String json, MinGongVO vo, Map map){
    
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
            
            if(condition.indexOf("'not null'")!=-1){
            	condition = condition.replace("'not null'", "not null");
            }
            if(condition.indexOf("'null'")!=-1){
            	condition = condition.replace("'null'", "null");
            }
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT M.MINGONG_UID,M.MINGONG_NAME，DECODE(M.MINGONG_PHOTO,NULL,'无', M.MINGONG_PHOTO,'有') ZP, ");
            sql.append(" DECODE(M.CARD_FLAG,NULL,'未发','Y','已发') AS XXK,M.ZHENGJIAN_NUMBER,C.COMPANYS_NAME,M.LIANXI_PHONE,M.ORIGIN, ");
            sql.append(" DECODE(M.STATUS, 0,'空闲',1,'在工',-1,'注销') AS ZT , ");
            sql.append(" DECODE(L.LAOWU_CONTRACTS_UID,NULL,'无',L.LAOWU_CONTRACTS_UID,'有') AS HT ");
            sql.append(" FROM MINGONG M ");
            sql.append(" LEFT JOIN  ");
            sql.append(" LAOWU_CONTRACTS L ");
            sql.append(" ON L.MINGONG_UID = M.MINGONG_UID ");
            sql.append(" LEFT JOIN COMPANYS C ");
            sql.append(" ON C.COMPANYS_UID = M.COMPANYS_UID ");
            

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

	public String queryByZjNumber(String json) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
		try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
			StringBuffer sql = new StringBuffer();
			sql.append(" select m.mingong_uid,m.mingong_name,m.mingong_photo  ");
			sql.append(" ,m.sex,m.age,m.origin,c.companys_name,z.zhengjian_type_name,m.zhengjian_number ");
			sql.append(" ,m.address,m.lianxi_phone,m.ZUZHI_GUANXI_UID,m.status ");
			sql.append(" from mingong m ");
			sql.append(" left join companys c ");
			sql.append(" on m.companys_uid = c.companys_uid ");
			sql.append(" left join zhengjian_type z ");
			sql.append(" on m.zhengjian_type_uid = z.zhengjian_type_uid ");
			
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			 DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String queryByMinGongUID(String MINGONG_UID) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
            // 组织查询条件         
			StringBuffer sql = new StringBuffer();
			sql.append(" select m.*,z.zhengjian_type_name,c.COMPANY_NAME");
			sql.append(" from mingong m ");
			sql.append(" left join sg_enterprise_library c ");
			sql.append(" on m.companys_uid = c.SG_COMPANY_UID and c.STATUS='1'");
			sql.append(" left join zhengjian_type z ");
			sql.append(" on m.zhengjian_type_uid = z.zhengjian_type_uid ");
			sql.append(" where m.mingong_uid ="+MINGONG_UID);
			BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			 DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String queryCondition2(String json, MinGongVO vo, Map map) {
		User user = ActionContext.getCurrentUserInThread();
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" select m.mingong_uid,m.mingong_name");
			sql.append(" ,z.zhengjian_type_name,m.zhengjian_number,c.COMPANY_NAME,m.lianxi_phone,m.origin,m.status");
			sql.append(" from mingong m ");
			sql.append(" left join sg_enterprise_library c ");
			sql.append(" on m.companys_uid = c.SG_COMPANY_UID and c.STATUS='1'");
			sql.append(" left join zhengjian_type z ");
			sql.append(" on m.zhengjian_type_uid = z.zhengjian_type_uid ");			

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
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
