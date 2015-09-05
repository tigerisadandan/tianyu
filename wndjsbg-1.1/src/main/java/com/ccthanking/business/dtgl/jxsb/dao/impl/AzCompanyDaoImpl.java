/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.jxsb.AzCompanyDao.java
 * 创建日期： 2014-12-11 上午 11:06:42
 * 功能：   安装企业
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-11 上午 11:06:42  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.azqy.vo.AzCompanyVO;
import com.ccthanking.business.dtgl.jxsb.dao.AzCompanyDao;
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
 * <p> AzCompanyDao.java </p>
 * <p> 功能：安装企业 </p>
 *
 * <p><a href="AzCompanyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-11
 * 
 */

@Component
public class AzCompanyDaoImpl  extends BsBaseDaoTJdbc implements AzCompanyDao {

    public String queryCondition(String json, AzCompanyVO vo, Map map){
    
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

            String sql = "SELECT t.*,(select u.user_name from users u where u.users_uid=t.shenhe_ren) as shenhename FROM az_COMPANY t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            //bs.setFieldDic("COMPANYS_TYPE", "COMPANYS_TYPE");//企业类型
            bs.setFieldDic("ZZDJ", "AZ_ZZDJ");

            // 设置查询条件
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy-MM-dd HH:mm:ss");
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    
    public String queryDq(String json, AzCompanyVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String newdate=sdf.format(new Date());
            condition +=" and yxq_end < to_date('"+newdate+"','yyyy-mm-dd') ";
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            Date date=new Date();
           
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            
          
            String sql = "select * from az_company ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            //bs.setFieldDic("COMPANYS_TYPE", "COMPANYS_TYPE");//企业类型
            bs.setFieldDic("ZZDJ", "AZ_ZZDJ");

            // 设置查询条件
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy-MM-dd HH:mm:ss");
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
 public String queryCode(String json, AzCompanyVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            String sql = "SELECT COUNT(*) AS COUNT FROM  AZ_COMPANY T";
            List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
			int count=	Integer.parseInt(rsMap.get("COUNT"))+1;
			domresult=count+"";
			}
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
   public String queryCount(String type, AzCompanyVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String newdate=sdf.format(new Date());
            
            String sql = " SELECT COUNT(*) as count FROM AZ_COMPANY A where 1=1 ";
            if("yqsq".equals(type)){
            	sql +=" and a.yq_status='0' ";
            }else if("dq".equals(type)){
            	sql +=" and a.yxq_end < to_date('"+newdate+"','yyyy-mm-dd')";
            }
            BaseResultSet bs = DBUtil.query(conn, sql, null);
    
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
