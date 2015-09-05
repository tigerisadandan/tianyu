/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件： 
 * 创建日期： 2015-07-07 下午 12:00:14
 * 功能：   内部通知管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-07-07 下午 12:00:14  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.nbgl.jhgl.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Component;

import com.ccthanking.business.nbgl.jhgl.dao.WorkPlanDao;
import com.ccthanking.business.nbgl.jhgl.vo.WorkPlanVO;
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
 * <p> WorkPlanDao.java </p>
 * <p> 功能：计划管理 </p>
 *
 * <p><a href="WorkPlanDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:lhtarena@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-07-07
 * 
 */
//
@Component
public class WorkPlanDaoImpl  extends BsBaseDaoTJdbc implements WorkPlanDao {

	
	/**
	 * 查询页面初始数据
	 */
	  public String queryCondition(String json, WorkPlanVO vo, Map map){
	    	User user = ActionContext.getCurrentUserInThread();
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {
	           //获取 查询条件 页面 传递时间段
	        	JSONObject querycondition = JSONObject.fromObject(json);
				String querycondition_txt = querycondition.getString("querycondition");
		        JSONObject subcondition = JSONObject.fromObject(querycondition_txt);
				querycondition_txt = subcondition.getString("conditions");
		        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(querycondition_txt);
	            JSONObject obj = (JSONObject) jsonArray.get(0);
	            //获取到前端隐藏域传递的字符串(第一个)
	            String str = obj.getString("value");
	            
	            
	            //7(2015/02/09,2015/02/15)
	            //25(2015/06/15,2015/06/21)
	            String  begin = null;
	            String  end = null;
	            if(str.length()==23){
	            	begin = str.substring(2,12);
	                end = str.substring(13,23);
	            }else {
	            	begin = str.substring(3,13);
	                end = str.substring(14,24);
	            }
	             
	            // 组织查询条件
	            PageManager page = RequestUtil.getPageManager(json);
	            String condition = RequestUtil.getConditionList(json).getConditionWhere();
	            //condition = condition.replace("/", "");
	            String orderFilter = RequestUtil.getOrderFilter(json);
	            
	            //condition += BusinessUtil.getSJYXCondition(null);
	            condition += BusinessUtil.getCommonCondition(user, null);
	            condition += orderFilter;
	            
	           /************/
	            if(condition.indexOf("PLAN_DATE = ")!=-1){
	            	String str1 = "PLAN_DATE BETWEEN to_date('"+begin+"','yyyy/MM/DD') AND to_date('"+end+"','yyyy/MM/DD')";
	            	String str2 = condition.substring(condition.indexOf("PLAN_DATE = "),condition.indexOf("and"));
	            	condition = condition.replace(str2 , str1);
	            }
	           
	            if (page == null)
	                page = new PageManager();
	            page.setFilter(condition);
	           
	          String sql ="SELECT t.PLAN_CONTENT_AM,"+
					      "t.PLAN_CONTENT_PM,t.PLAN_DATE,t.UPDATE_BY,t.UPDATE_DATE,o.org_name "+
						  "FROM organize o "+
						  "left join work_plan t "+
						  "on o.user_uid = t.update_by "+
						  "left join organize g "+
						  "on o.p_organize_uid = g.organize_uid ";
	          
	            BaseResultSet bs = DBUtil.query(conn, sql,page);//null
	         
	            bs.setFieldDateFormat("PLAN_DATE","yyyy-MM-dd EE");
	    		bs.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd");
	            bs.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm");
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
	        } catch (Exception e){
	            DaoException.handleMessageException("*********查询首页数据出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return domresult;

	    }
  
    /**
     * 查询出 年所对应的周
     */
	public String getByTime(String time) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			
			String sql = "select level the_week ,'第' || level || '周(' || to_char(common_package.ret_week_date2("+time+", level),'yyyy/mm/dd') || '~' ||"
                +"to_char(common_package.ret_week_date2("+time+", level) + 6, 'yyyy/mm/dd') || ')' week from dual where level <= 53 "
                +"connect by level <= 53";

			BaseResultSet bs = DBUtil.query(conn,sql,null);
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询周出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	/**
	 * 查询 员工的部门编号
	 */
	public String getByOrgId(String user_uid) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			
			String sql = "select p_organize_uid from organize where user_uid="+user_uid;
               
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询员工部门编号出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	/**
	 * 查询当前登录用户所在部门的下周计划
	 */
	public String getDataByTime(String timeStr) {
		User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        String begin = null;
        String end = null;
        try {
            //25(2015/06/15,2015/06/21)
            if(timeStr.length()==23){
            	begin = timeStr.substring(2,12);
                end = timeStr.substring(13,23);
            }else {
            	begin = timeStr.substring(3,13);
                end = timeStr.substring(14,24);
            }
            String sql="SELECT t.WORK_PLAN_UID,t.PLAN_CONTENT_AM, t.PLAN_CONTENT_PM FROM organize o "+
					   "left join work_plan t on t.create_by = o.user_uid "+
					   "left join organize g on o.p_organize_uid = g.organize_uid "+
					   "where PLAN_DATE BETWEEN to_date('"+begin+"','yyyy/MM/DD') AND to_date('"+end+"','yyyy/MM/DD') "+
					   "and g.ORGANIZE_UID = (select o.p_organize_uid  from organize o "+   
					   "left join users u  on o.user_uid = u.users_uid  where users_uid = "+user.getUserSN()+") "+ 
					   "and rownum <= '1000' order by UPDATE_DATE desc, WORK_PLAN_UID asc";
						 
            BaseResultSet bs = DBUtil.query(conn, sql,null);
          
            domresult = bs.getJson();
        } catch (Exception e){
            DaoException.handleMessageException("*********查询下周计划出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    /**
     * 获取 各个部门的数据
     */
	public String getDataForDept(String str) {
		User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        String  week = null;
        String  year = null;
        try {
            if(str.length()==23){
            	week= str.substring(0,1);
                year = str.substring(2,6);
            }else {
            	week = str.substring(0,2);
                year = str.substring(3,7);
            }	
            String sql="select return_work_plan_content('"+week+"','"+year+"', '综合管理科', l.sn, 'ALL') days,"+
				       "return_work_plan_content('"+week+"','"+year+"', '监督一组', l.sn, 'AM') jdyz_am,"+
				       "return_work_plan_content('"+week+"','"+year+"', '监督一组', l.sn, 'PM') jdyz_pm,"+
				       "return_work_plan_content('"+week+"','"+year+"', '监督二组', l.sn, 'AM') jdez_am,"+
				       "return_work_plan_content('"+week+"','"+year+"', '监督二组', l.sn, 'PM') jdez_pm,"+
				       "return_work_plan_content('"+week+"','"+year+"', '监督管理科', l.sn, 'AM') jdk_am,"+
				       "return_work_plan_content('"+week+"','"+year+"', '监督管理科', l.sn, 'PM') jdk_pm,"+
				       "return_work_plan_content('"+week+"','"+year+"', '综合管理科', l.sn, 'AM') zhk_am,"+
				       "return_work_plan_content('"+week+"','"+year+"', '综合管理科', l.sn, 'PM') zhk_pm, l.sn, 'A' ampm "+
				       "from (select level sn from dual where level >= 2 "+
		    		   "connect by level >= 2 "+
		    		   "and level <= 6) l ";
        
            BaseResultSet bs = DBUtil.query(conn, sql,null);
         
            domresult = bs.getJson();
        } catch (Exception e){
            DaoException.handleMessageException("*********查询部门数据出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
 
	public List<?> findHeaderPrint(String timeStr){
		Connection conn = DBUtil.getConnection();
		List<?> map = null;
	    String  week = null;
	    String  year = null;
	    try {
	            if(timeStr.length()==23){
	            	week= timeStr.substring(0,1);
	                year = timeStr.substring(2,6);
	            }else {
	            	week = timeStr.substring(0,2);
	                year = timeStr.substring(3,7);
	            }	
	            String sql="select return_work_plan_content('"+week+"','"+year+"', '综合管理科', l.sn, 'ALL') days,"+
					       "return_work_plan_content('"+week+"','"+year+"', '监督一组', l.sn, 'AM') jdyz_am,"+
					       "return_work_plan_content('"+week+"','"+year+"', '监督一组', l.sn, 'PM') jdyz_pm,"+
					       "return_work_plan_content('"+week+"','"+year+"', '监督二组', l.sn, 'AM') jdez_am,"+
					       "return_work_plan_content('"+week+"','"+year+"', '监督二组', l.sn, 'PM') jdez_pm,"+
					       "return_work_plan_content('"+week+"','"+year+"', '监督管理科', l.sn, 'AM') jdk_am,"+
					       "return_work_plan_content('"+week+"','"+year+"', '监督管理科', l.sn, 'PM') jdk_pm,"+
					       "return_work_plan_content('"+week+"','"+year+"', '综合管理科', l.sn, 'AM') zhk_am,"+
					       "return_work_plan_content('"+week+"','"+year+"', '综合管理科', l.sn, 'PM') zhk_pm, l.sn, 'A' ampm "+
					       "from (select level sn from dual where level >= 2 "+
			    		   "connect by level >= 2 "+
			    		   "and level <= 6) l ";
			
			map =  DBUtil.queryReturnList(conn, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	
	}

	public String getDeptName(String deptId) {
		User user = ActionContext.getCurrentUserInThread();//当前登陆用户
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			
			String sql = "SELECT org_name from organize  WHERE  organize_uid ="+deptId;
               
			BaseResultSet bs = DBUtil.query(conn,sql,null);
			domresult = bs.getJson();
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询部门名出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	 /**  在此可加入其它方法 **/
	
	
	
	
	
	
	
	
	
	}
  
   

