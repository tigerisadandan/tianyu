/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.project.ProjectsDao.java
 * 创建日期： 2014-07-02 下午 12:00:58
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:00:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.dao.ProjectsDao;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.ExtInfoType;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> ProjectsDao.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="ProjectsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */

@Component
public class ProjectsDaoImpl  extends BsBaseDaoTJdbc implements ProjectsDao {
	//查询项目分期
    public String queryCondition(String json, ProjectsVO vo, Map map){
    
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

            /*String sql1 = "select * from (SELECT d.*,(select j.company_name from js_company j where j.js_company_uid=d.js_company_uid) as companyname, "+
						"(select p.GONGCHENG_NAME from js_project p where p.project_uid=d.project_uid) as project_name,(select u.user_name from users u where to_char(u.users_uid)=d.shenpi_ren) as shenpiname "+
						" FROM PROJECTS d) t";*/
            String sql="select d.*,j.company_name as companyname,p.GONGCHENG_NAME as PROJECT_NAME,u.user_name as shenpiname  from PROJECTS d left join js_company j on j.js_company_uid=d.js_company_uid" +
            		" left join js_project p  on p.project_uid=d.project_uid left join users u  on to_char(u.users_uid)=d.shenpi_ren";
            
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
            bs.setFieldDic("PROJECTS_LEVEL", "PROJECTS_LEVEL");
            bs.setFieldDic("PROJECTS_XINZHI", "PROJECTS_XINZHI");
            bs.setFieldDic("PROJECTS_TYPE", "PROJECTS_TYPE");
            bs.setFieldDic("JIANSHE_TYPE", "JIANSHE_TYPE");
            bs.setFieldDic("HYFL", "HYFL");
            bs.setFieldDic("QUYU", "QY");
            bs.setFieldDic("STATUS", "STATUS");
            bs.setFieldDic("SHENHE_JIEGUO", "SHENHEJIEGUO");
            bs.setFieldDateFormat("SHENPI_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("PLAN_KG_DATE", "yyyy-MM-dd");
            bs.setFieldDateFormat("JG_DATE", "yyyy-MM-dd");
            bs.setFieldDateFormat("TC_DATE", "yyyy-MM-dd");
            //bs.setFieldExtInfo("SHENPI_REN", ExtInfoType.USERIDNUM);
            
            
//            bs.setFieldThousand("ZHANDI_MIANJI");
//            bs.setFieldThousand("JIANZHU_MIANJI");
//            bs.setFieldThousand("CHANGDU");
//            bs.setFieldThousand("ZONG_TOUZI");
            
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询企业申报项目信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
  //查询环保项目
    public String queryHb(String json, ProjectsVO vo, Map map){
    
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

            String sql = "select * from (SELECT d.*,(select j.company_name from js_company j where j.js_company_uid=d.js_company_uid) as companyname, "+
						"(select u.user_name from users u where to_char(u.users_uid)=d.shenpi_ren) as shenpiname "+
						" FROM PROJECTS d) t";
           
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            bs.setFieldDic("PROJECTS_LEVEL", "PROJECTS_LEVEL");
            bs.setFieldDic("PROJECTS_XINZHI", "PROJECTS_XINZHI");
            bs.setFieldDic("PROJECTS_TYPE", "PROJECTS_TYPE");
            bs.setFieldDic("JIANSHE_TYPE", "JIANSHE_TYPE");
            bs.setFieldDic("HYFL", "HYFL");
            bs.setFieldDic("QUYU", "QUYU");
            bs.setFieldDic("STATUS", "STATUS");
            bs.setFieldDic("SHENHE_JIEGUO", "SHENHEJIEGUO");
            bs.setFieldDateFormat("SHENPI_DATE", "yyyy-MM-dd HH:mm:ss");
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
    //审核 修改 ProjectsUnits Units code 代码
    public void updateUnitsCode(String sql){
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		try {
			
			
			DBUtil.exec(conn, sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}
    
    //获取项目分期总数
	public List<?> getProjectCount(String type) {
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
        	String sql="select "+
        	"(select count(p.projects_uid) from projects p where p.status='30' and PROJECTS_TYPE='"+type+"') dsh, "+
        	"(select count(p.projects_uid) from projects p where p.status='20'and PROJECTS_TYPE='"+type+"') wtg,"+
        	"(select count(p.projects_uid) from projects p where p.status='10' and PROJECTS_TYPE='"+type+"')  ytg "+
        	"from dual";
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询企业申报项目分期审核结果统计信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
	
	/**项目分期
	 * 根据项目类型并结合项目编号不为空、审批日期大于当前年份的第一天
	 * 来查询数据条目数，并加1，
	 * 然后根据length的长度要求，前面加0补齐
	 * 并返回项目编号
	 * */
	public String getCode(String code,String jscompany,String projectsType,int length) {
			String projectscode=null;
			Connection conn = DBUtil.getConnection();
			String s=null;
	        try {
	        	String sqlCompany="select JIGUO_DAIMA from JS_COMPANY where JS_COMPANY_UID="+jscompany;
	        	String[][] res = DBUtil.query(conn, sqlCompany);
				if(res!=null){
					 s = res[0][0];
					 s = s.replaceAll("[^a-z^A-Z^0-9]", "");  
				}
	        	
	        	String sql="select count(t.projects_uid)+1 from projects t " +
	        			"where t.projects_code is not null and t.projects_type='"+projectsType+"' " +
	        			"and t.shenpi_date >=trunc(sysdate,'yyyy') ";
	        	/*if("J".equals(projectsType)){
	        		sql=sql+" and project_uid is not null";
	        	}*/
	        	String[][] temp=DBUtil.querySql(conn, sql); 
	        	String[][] nf=DBUtil.querySql(conn, "select to_char(sysdate,'yyyy') from dual"); 
	        	if("H".equals(projectsType)){
		        	if(temp!=null&&temp[0]!=null&&temp[0][0]!=null){
		        		Integer tem=Integer.valueOf(temp[0][0]);        		
		        		String f = "%0" + length + "d";	
		        		
		        		projectscode=s+nf[0][0]+projectsType+String.format(f, tem);
		        	}
	        	}
	        	if("J".equals(projectsType)){
	        		if(temp!=null&&temp[0]!=null&&temp[0][0]!=null){
		        		Integer tem=Integer.valueOf(temp[0][0]);        		
		        		String f = "%0" + 2 + "d";	
		        		
		        		projectscode=code+String.format(f, tem);
		        	}
	        		
	        	}
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询项目编号出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
		return projectscode;
	}
    
    // 在此可加入其它方法

}
