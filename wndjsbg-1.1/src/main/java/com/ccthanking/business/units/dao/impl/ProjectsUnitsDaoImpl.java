/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.units.ProjectsUnitsDao.java
 * 创建日期： 2014-10-17 下午 01:43:10
 * 功能：   单位工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-17 下午 01:43:10  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.units.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.business.units.dao.ProjectsUnitsDao;
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
 * <p> ProjectsUnitsDao.java </p>
 * <p> 功能：单位工程 </p>
 *
 * <p><a href="ProjectsUnitsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-10-17
 * 
 */

@Component
public class ProjectsUnitsDaoImpl  extends BsBaseDaoTJdbc implements ProjectsUnitsDao {

    public String queryCondition(String json, ProjectsUnitsVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "PROJECTS_UNITS t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            bs.setFieldDic("UNITS_TYPE", "DT_UNITS_TYPE");
            bs.setFieldDic("ZXBZ", "DT_ZXBZ");
            bs.setFieldDic("DJLX", "DT_DJLX");
            bs.setFieldDic("JCLX", "DT_JCLX");
            bs.setFieldDic("DSJG", "DT_DSJG");
            bs.setFieldDic("DXJG", "DT_DXJG");
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
    
    public String querybyIds(String json, ProjectsUnitsVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {


            String sql = "SELECT * FROM " + "PROJECTS_UNITS t where t.UNITS_UID in ("+json+")";
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            bs.setFieldDic("UNITS_TYPE", "DT_UNITS_TYPE");
            bs.setFieldDic("ZXBZ", "DT_ZXBZ");
            bs.setFieldDic("DJLX", "DT_DJLX");
            bs.setFieldDic("JCLX", "DT_JCLX");
            bs.setFieldDic("DSJG", "DT_DSJG");
            bs.setFieldDic("DXJG", "DT_DXJG");
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
    public String querybygcid(String id,String type,String cUid){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	String sql=null;
            // 组织查询条件
        	if("d".equals(type)){
                 sql = "select * from projects_units u where u.units_uid in(select p.units_uid from projects_units p where p.PROJECTS_UID='"+id+"' and CREATED_UID='"+cUid+"')";
        	}else if("n".equals(type)){
        		sql = "select * from projects_units u where u.units_uid in(select p.units_uid from projects_gongcheng_units p where p.gongcheng_uid='"+id+"')";  
        	}else if("XMQUERY".equals(type)){
        		sql = "select * from projects_units u " +
        				"left join projects p on u.projects_uid=p.projects_uid " +
        				"where p.project_uid='"+id+"'";  
        	}
        	
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            bs.setFieldDic("UNITS_TYPE", "DT_UNITS_TYPE");
            bs.setFieldDic("ZXBZ", "DT_ZXBZ");
            bs.setFieldDic("DJLX", "DT_DJLX");
            bs.setFieldDic("JCLX", "DT_JCLX");
            bs.setFieldDic("DSJG", "DT_DSJG");
            bs.setFieldDic("DXJG", "DT_DXJG");
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

}
