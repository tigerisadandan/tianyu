/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.project.JsProjectDao.java
 * 创建日期： 2014-09-02 下午 04:35:32
 * 功能：   项目管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-09-02 下午 04:35:32  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.dao.JsProjectDao;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> JsProjectDao.java </p>
 * <p> 功能：项目管理 </p>
 *
 * <p><a href="JsProjectDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-09-02
 * 
 */

@Component
public class JsProjectDaoImpl  extends BsBaseDaoTJdbc implements JsProjectDao {

	//private String sql="select project_uid, js_company_uid, shenhe_ren, shenhe_yijian, shenhe_date, shenhe_jieguo, status,  describe, created_uid, created_name, created_date, update_uid, update_name, update_date,  gongcheng_name, project_code, projects_level, projects_xinzhi, touzi_xingzhi, quyu, position, touzi, zdmj, jzmj, changdu, jianshe_dizhi, fzr, fzr_mobile, fzr_id, fzr_email, plan_kg_date, tc_date, jg_date from js_project";
	
	  //查询项目
    public String queryCondition(String json, ProjectsVO vo, Map map){
        
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

            String sql = "select * from (SELECT d.*,(select j.company_name from js_company j where j.js_company_uid=d.js_company_uid) as companyname, "+
						"(select u.user_name from users u where to_char(u.users_uid)=d.shenhe_ren) as shenhename "+
						" FROM JS_PROJECT d) t";
           
            BaseResultSet bs = DBUtil.query(conn, sql, page);
       
            bs.setFieldDic("PROJECTS_LEVEL", "PROJECTS_LEVEL");
            bs.setFieldDic("PROJECTS_XINZHI", "PROJECTS_XINZHI");
            bs.setFieldDic("TOUZI_XINGZHI", "PRO_TZXZ");
            bs.setFieldDic("QUYU", "QY");
            bs.setFieldDic("STATUS", "STATUS");
            bs.setFieldDic("SHENHE_JIEGUO", "SHENHEJIEGUO");
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("PLAN_KG_DATE", "yyyy-MM-dd");
            bs.setFieldDateFormat("JG_DATE", "yyyy-MM-dd");
            bs.setFieldDateFormat("TC_DATE", "yyyy-MM-dd");

            
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询企业申报项目信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryCondition2(String json, ProjectsVO vo, Map map) {
    	// TODO Auto-generated method stub
    	 Connection conn = DBUtil.getConnection();
         String domresult = "";
         try {

             // 组织查询条件
             PageManager page = RequestUtil.getPageManager(json);
             String condition = RequestUtil.getConditionList(json).getConditionWhere();
             String orderFilter = RequestUtil.getOrderFilter(json);
//             condition += BusinessUtil.getSJYXCondition(null);
//             condition += BusinessUtil.getCommonCondition(user, null);
             condition += orderFilter;
             if (page == null)
                 page = new PageManager();
             page.setFilter(condition);

             String sql = "SELECT * FROM JS_PROJECT t";
             BaseResultSet bs = DBUtil.query(conn, sql, page);
             // 合同表
             // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
             // 项目下达库
             // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
             // 标段表
             // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
             bs.setFieldDic("PROJECTS_LEVEL", "PROJECTS_LEVEL");
             bs.setFieldDic("PROJECTS_XINZHI", "PROJECTS_XINZHI");
             bs.setFieldDic("TOUZI_XINGZHI", "PRO_TZXZ");
             bs.setFieldDic("QUYU", "QY");
             bs.setFieldDic("STATUS", "STATUS");
             bs.setFieldDic("SHENHE_JIEGUO", "SHENHEJIEGUO");
             bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
             bs.setFieldDateFormat("PLAN_KG_DATE", "yyyy-MM-dd");
             bs.setFieldDateFormat("JG_DATE", "yyyy-MM-dd");
             bs.setFieldDateFormat("TC_DATE", "yyyy-MM-dd");
             // 设置字典

             // 设置查询条件
             domresult = bs.getJson();
             
         } catch (Exception e) {
             DaoException.handleMessageException("*********查询注册企业信息出错!*********");
         } finally {
             DBUtil.closeConnetion(conn);
         }
         return domresult;
    }
   
    public String queryLX(String json) throws Exception{
    	User user = ActionContext.getCurrentUserInThread();
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            //String orderFilter = RequestUtil.getOrderFilter(json);

           // condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql="select * from (select l.*, jp.project_uid as project_uid from lixiang l join js_project_lixiang jp on l.lixiang_UID=jp.lixiang_UID) t ";
           
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询项目的立项信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	
}
    public String queryLXdetail(String json) throws Exception{
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

            String sql="select * from lixiang ";
           
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询立项详细信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
}
  //获取项目总数
	public List<?> getProjectCount() throws Exception{
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
        	String sql="select "+
        	"(select count(p.project_uid) from js_project p where p.status='30') dsh, "+
        	"(select count(p.project_uid) from js_project p where p.status='20') wtg,"+
        	"(select count(p.project_uid) from js_project p where p.status='10')  ytg "+
        	"from dual";
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询企业申报项目审核结果统计信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
	
	
	
    // 在此可加入其它方法
	/**
	 * 根据项目类型并结合项目编号不为空、审批日期大于当前年份的第一天
	 * 来查询数据条目数，并加1，
	 * 然后根据length的长度要求，前面加0补齐
	 * 并返回项目编号
	 * */
	public String getProjectsCode(String jscompany,int length) throws Exception{
			String projectscode=null;
			Connection conn = DBUtil.getConnection();
			String s=null;
			String now=null;
	        try {
	        	String sqlCompany="select JIGUO_DAIMA from JS_COMPANY where JS_COMPANY_UID="+jscompany;
	        	String[][] res = DBUtil.query(conn, sqlCompany);
				if(res!=null){
					 s = res[0][0];
					 s = s.replaceAll("[^a-z^A-Z^0-9]", "");  
				}
	        	//MAX(SUBSTR(project_code,15))+1
	        	String sql="select count(t.project_uid)+1 from js_project t " +
	        			"where t.project_code is not null and t.shenhe_date >=trunc(sysdate,'yyyy') ";
	        	String[][] temp=DBUtil.querySql(conn, sql); 
	        	String[][] nf=DBUtil.querySql(conn, "select to_char(sysdate,'yyyy') from dual"); 
	        	if(temp!=null&&temp[0]!=null&&temp[0][0]!=null){
	        		Integer tem=Integer.valueOf(temp[0][0]);        		
	        		String f = "%0" + length + "d";	
	        		
	        		projectscode=s+nf[0][0]+"J"+String.format(f, tem);
	        	}
	        	
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询项目编号出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
		return projectscode;
	}

//当不通过时，把项目-立项关联表中的记录删除
	public void clean(String jsproject) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String clearzhengshu_SQL = "delete from JS_PROJECT_LIXIANG t where t.PROJECT_UID = "+ jsproject;
		DBUtil.exec(conn, clearzhengshu_SQL);
		
	}


	/**
	 * 通过项目UID查询关联的立项信息，返回list
	 * 
	 * */
	 public List<Map<String, String>> lxList(String jsprojectuid) throws Exception{
	    	User user = ActionContext.getCurrentUserInThread();
	        List<Map<String, String>> listmap=null;
	        Connection conn = DBUtil.getConnection();      
	        try {
	            String sql="select * from lixiang lx "+
					"left join js_project_lixiang jpl on jpl.lixiang_uid=lx.lixiang_uid "+
					"where jpl.project_uid='"+jsprojectuid+"'";
	           
	            listmap=DBUtil.queryReturnList(conn, sql); 
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询立项详细信息出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	      return listmap;
	}
    
}
