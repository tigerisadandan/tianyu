/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.bzwj.JxsbAzgzDao.java
 * 创建日期： 2015-01-16 下午 12:03:25
 * 功能：   机械设备安装告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-16 下午 12:03:25  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.bzwj.GongCheng;
import com.ccthanking.business.bzwj.dao.JxsbAzgzDao;
import com.ccthanking.business.dtgl.azqy.vo.JxsbAzgcVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbAzgzVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgcVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbCzryVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbJcysVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbSyglVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.Role;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> JxsbAzgzDao.java </p>
 * <p> 功能：机械设备安装告知 </p>
 *
 * <p><a href="JxsbAzgzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-16
 * 
 */

@Component
public class JxsbAzgzDaoImpl  extends BsBaseDaoTJdbc implements JxsbAzgzDao {

	 public String queryCondition(String json, JxsbAzgzVO vo, Map map){
		    
	    	User user = ActionContext.getCurrentUserInThread();
	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	        	 PageManager page = RequestUtil.getPageManager(json);
	             String condition = RequestUtil.getConditionList(json).getConditionWhere();
	             String orderFilter = RequestUtil.getOrderFilter(json);
	             condition += BusinessUtil.getCommonCondition(user, null);
	             condition += " and g.SHENHE_STATUS in (30,40,41,50) ";
	             condition += orderFilter;
	             if (page == null)
	                 page = new PageManager();
	             page.setFilter(condition);

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             String sql="select d.SYDJ_BH,s.GGXH,s.DONGSHU,g.CREATED_DATE,g.JXSB_AZGZ_UID,g.JXSB_SYGL_UID,g.SHENHE_STATUS,g.STATUS,g.SBAZ_DATE,j.JXSB_TYPE_UID,j.CQBH,j.ZZXKZ,g.SHENHE_DATE,g.SHOULI_DATE from jxsb_azgz g " +
	              		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	              		"left join JXSB_SYDJ d on g.jxsb_sygl_uid=d.jxsb_sygl_uid " +
	              		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("SY_STATUS", "SB_SY_STATUS");
	            bs.setFieldDic("JXSB_TYPE_UID", "AZ_JXSB_TYPE");
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
	 
	 public String queryByView(String json, JxsbAzgzVO vo, Map map){
	        
	    	User user = ActionContext.getCurrentUserInThread();
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	            PageManager page = RequestUtil.getPageManager(json);
	            String condition = RequestUtil.getConditionList(json).getConditionWhere();
	            String orderFilter = RequestUtil.getOrderFilter(json);
	            condition += BusinessUtil.getCommonCondition(user, null);
	         //   condition += "and g.AZ_COMPANY_UID = '"+user.getIdCard()+"' ";
	            condition += orderFilter;
	            if (page == null)
	                page = new PageManager();
	            page.setFilter(condition);

	            String sql = "select  " +
	            		" (select u.user_name from users u where u.users_uid=g.XZSH_REN) XZSH_NAME," +
	            		"g.CREATED_DATE,g.SHOULI_DATE,g.XZSH_DATE,g.SHENHE_DATE, " +
	            		"g.SG_SHENHE_YJ,g.XZSH_YIJIAN,g.JBRSH_YIJIAN,g.GZ_SHOULI_YJ,g.JL_SHENHE_YJ,g.AQGL_REN,s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	            		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	            		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	            		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	            		"left join jxsb j on s.jxsb_uid=j.jxsb_uid ";
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("SY_STATUS", "SB_SY_STATUS");
	            bs.setFieldDic("JXSB_TYPE_UID", "AZ_JXSB_TYPE");
	            
	            bs.setFieldDateFormat("CREATED_DATE", "yyyy年MM日dd");// 计量月份
	               bs.setFieldDateFormat("SHOULI_DATE", "yyyy年MM日dd");// 计量月份 
	               bs.setFieldDateFormat("XZSH_DATE", "yyyy年MM日dd");// 计量月份 
	               bs.setFieldDateFormat("SHENHE_DATE", "yyyy年MM日dd");// 计量月份 
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
	   
	   public String queryAzyr(String json, JxsbCzryVO vo, Map map){  //安装人员 
		    
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

	           String sql = "SELECT * FROM " + "JXSB_CZRY t";
	           BaseResultSet bs = DBUtil.query(conn, sql, page);
	           // 合同表
	           // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	           // 项目下达库
	           // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	           // 标段表
	           // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	           // 设置字典
	              bs.setFieldDic("JOB_TYPE", "GONGZHONG");
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
	   
	   public String queryAzgc(String json, JxsbAzgcVO vo, Map map){
		    
	    	User user = ActionContext.getCurrentUserInThread();
	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	        	 PageManager page = RequestUtil.getPageManager(json);
	             String condition = RequestUtil.getConditionList(json).getConditionWhere();
	             String orderFilter = RequestUtil.getOrderFilter(json);
	             condition += BusinessUtil.getCommonCondition(user, null);
	             condition += " and t.SHENHE_STATUS in (20,50) ";
	             condition += orderFilter;
	             if (page == null)
	                 page = new PageManager();
	             page.setFilter(condition);

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             /*
	             String sql="   select g.SHEBEI_NAME,g.CQ_BH,t.*  from jxsb_azgc t " +
	             		"left join jxsb_sygl g on t.jxsb_sygl_uid=g.jxsb_sygl_uid ";*/
	             String sql= " SELECT G.JXSB_UID,G.SHEBEI_NAME,G.CQ_BH,T.* FROM JXSB_SYGL G " +
         		"LEFT JOIN JXSB_AZGC T ON T.JXSB_SYGL_UID=G.JXSB_SYGL_UID ";
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("GZQQ", "WAIDI_Y");
	            bs.setFieldDic("BJJC", "YOUWU");
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
	   
	   public String queryJcys(String json, JxsbJcysVO vo, Map map){
		    
	    	User user = ActionContext.getCurrentUserInThread();
	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	        	 PageManager page = RequestUtil.getPageManager(json);
	             String condition = RequestUtil.getConditionList(json).getConditionWhere();
	             String orderFilter = RequestUtil.getOrderFilter(json);
	             condition += BusinessUtil.getCommonCondition(user, null);
	             condition += " and t.SHENHE_STATUS in (20,50) ";
	             condition += orderFilter;
	             if (page == null)
	                 page = new PageManager();
	             page.setFilter(condition);

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             String sql="   select g.SHEBEI_NAME,g.CQ_BH,t.*  from jxsb_jcys t " +
		             		"left join jxsb_sygl g on t.jxsb_sygl_uid=g.jxsb_sygl_uid ";
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
	   
	   public String queryCxgc(String json, JxsbCxgcVO vo, Map map){
		    
	    	User user = ActionContext.getCurrentUserInThread();
	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	        	 PageManager page = RequestUtil.getPageManager(json);
	             String condition = RequestUtil.getConditionList(json).getConditionWhere();
	             String orderFilter = RequestUtil.getOrderFilter(json);
	             condition += BusinessUtil.getCommonCondition(user, null);
	             condition += " and t.SHENHE_STATUS in (30,40,41,50) ";
	             condition += orderFilter;
	             if (page == null)
	                 page = new PageManager();
	             page.setFilter(condition);

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             
	             String sql="   select g.SHEBEI_NAME,g.CQ_BH,t.*  from jxsb_cxgc t " +
		             		"left join jxsb_sygl g on t.jxsb_sygl_uid=g.jxsb_sygl_uid ";
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("GZQQ", "WAIDI_Y");
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
	   
	   public String querySbzl(String json, JxsbSyglVO vo, Map map){
		    
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

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             
	             String sql="  SELECT g.*,d.SYDJ_BH FROM JXSB_SYGL g " +
	             		"left join JXSB_SYDJ d on g.JXSB_SYGL_UID=d.JXSB_SYGL_UID " ;
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("GZQQ", "WAIDI_Y");
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
	   
	   public String queryDslSb(String json, JxsbSyglVO vo, Map map){
		    
	    	User user = ActionContext.getCurrentUserInThread();
	    	
	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

		    	Role[] roles = user.getRoles();
		    	String xzjs = "";
		    	String jbjs = "";
		    	String sljs = "";
		    	for (int i = 0; i < roles.length; i++) {
		    		if ("内网小组审核人".equals(roles[i].getName())) {
		    			xzjs = "内网小组审核人";
		    		}
		    		if ("内网经办审核人".equals(roles[i].getName())) {
		    			jbjs = "内网经办审核人";
		    		}
		    		if ("内网受理人".equals(roles[i].getName())) {
		    			sljs = "内网受理人";
		    		}
		    	}
		    	String tj="";
		    	if(xzjs!=""&&sljs!=""){
		    		tj="30,41";
		    	}
		    	if(xzjs!=""&&sljs==""){
		    		tj="30";
		    	}
		    	if(xzjs==""&&sljs==""){
		    		tj="41";
		    	}
	            // 组织查询条件
	        	 PageManager page = RequestUtil.getPageManager(json);
	             String condition = RequestUtil.getConditionList(json).getConditionWhere();
	             String orderFilter = RequestUtil.getOrderFilter(json);
	             condition += BusinessUtil.getCommonCondition(user, null);
	             condition += " and bz.status!='-1' ";
	             condition += orderFilter;
	             if (page == null)
	                 page = new PageManager();
	             page.setFilter(condition);

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             
	             String sql=" select bz.*,gl.CQ_ATTRIBUTE,gl.GGXH,gl.CQ_BH,gl.SHEBEI_NAME,gl.STEP,gl.BEGIN_DATE,(select a.sbaz_date from jxsb_azgz a where bz.jxsb_sygl_uid=a.jxsb_sygl_uid ) sbaz_date,(select a.SBAZ_E_DATE from jxsb_azgz a where bz.jxsb_sygl_uid=a.jxsb_sygl_uid) SBAZ_E_DATE" +
	             		" ,(select dj.sydj_bh from jxsb_sydj dj where dj.jxsb_sygl_uid=bz.jxsb_sygl_uid) SYDJ_BH,(select dj.SY_COUNTS from jxsb_sydj dj where dj.jxsb_sygl_uid=bz.jxsb_sygl_uid) SY_COUNTS  from ( " +
	             		"select az.jxsb_azgz_uid as bz_uid,'az' as bz_type,az.jxsb_sygl_uid,az.SHENHE_STATUS,az.STATUS,az.SHENHE_DATE  from jxsb_azgz az " +
	             		"where az.SHENHE_STATUS in ("+tj+") " +
	             		"UNION " +
	             		"select sy.JXSB_SYDJ_UID as bz_uid,'sy' as bz_type,sy.jxsb_sygl_uid,sy.SHENHE_STATUS,sy.STATUS,sy.SHENHE_DATE from jxsb_sydj sy " +
	             		"where sy.SHENHE_STATUS in ("+tj+") " +
	             		"UNION " +
	             		"select cz.JXSB_CXGZ_UID as bz_uid,'cz' as bz_type,cz.jxsb_sygl_uid,cz.SHENHE_STATUS,cz.STATUS,cz.SHENHE_DATE from jxsb_cxgz cz " +
	             		"where cz.SHENHE_STATUS in  ("+tj+") " +
	             		") bz left join jxsb_sygl gl on gl.jxsb_sygl_uid=bz.jxsb_sygl_uid " ;
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("GZQQ", "WAIDI_Y");
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
	   
	   public String queryDslSbWsl(String json, JxsbSyglVO vo, Map map) {
		    
	    	User user = ActionContext.getCurrentUserInThread();
	    	
	    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

		    	Role[] roles = user.getRoles();
		    	String xzjs = "";
		    	String jbjs = "";
		    	String sljs = "";
		    	for (int i = 0; i < roles.length; i++) {
		    		if ("内网小组审核人".equals(roles[i].getName())) {
		    			xzjs = "内网小组审核人";
		    		}
		    		if ("内网经办审核人".equals(roles[i].getName())) {
		    			jbjs = "内网经办审核人";
		    		}
		    		if ("内网受理人".equals(roles[i].getName())) {
		    			sljs = "内网受理人";
		    		}
		    	}
		    	String tj="";
		    	if(xzjs!=""&&sljs!=""){
		    		tj="30,41";
		    	}
		    	if(xzjs!=""&&sljs==""){
		    		tj="30";
		    	}
		    	if(xzjs==""&&sljs==""){
		    		tj="41";
		    	}
	            // 组织查询条件
	        	 PageManager page = RequestUtil.getPageManager(json);
	             String condition = RequestUtil.getConditionList(json).getConditionWhere();
	             String orderFilter = RequestUtil.getOrderFilter(json);
	             condition += BusinessUtil.getCommonCondition(user, null);
	             condition += " and bz.status='-1' ";
	             condition += orderFilter;
	             if (page == null)
	                 page = new PageManager();
	             page.setFilter(condition);

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             
	             String sql=" select bz.*,gl.GGXH,gl.CQ_BH,gl.SHEBEI_NAME,gl.STEP,gl.BEGIN_DATE,(select a.sbaz_date from jxsb_azgz a where bz.jxsb_sygl_uid=a.jxsb_sygl_uid ) sbaz_date,(select a.SBAZ_E_DATE from jxsb_azgz a where bz.jxsb_sygl_uid=a.jxsb_sygl_uid) SBAZ_E_DATE" +
	             		" ,(select dj.sydj_bh from jxsb_sydj dj where dj.jxsb_sygl_uid=bz.jxsb_sygl_uid) SYDJ_BH  from ( " +
	             		"select az.jxsb_azgz_uid as bz_uid,'az' as bz_type,az.jxsb_sygl_uid,az.SHENHE_STATUS,az.STATUS,az.SHENHE_DATE  from jxsb_azgz az " +
	             		"where az.SHENHE_STATUS in ("+tj+") " +
	             		"UNION " +
	             		"select sy.JXSB_SYDJ_UID as bz_uid,'sy' as bz_type,sy.jxsb_sygl_uid,sy.SHENHE_STATUS,sy.STATUS,sy.SHENHE_DATE from jxsb_sydj sy " +
	             		"where sy.SHENHE_STATUS in ("+tj+") " +
	             		"UNION " +
	             		"select cz.JXSB_CXGZ_UID as bz_uid,'cz' as bz_type,cz.jxsb_sygl_uid,cz.SHENHE_STATUS,cz.STATUS,cz.SHENHE_DATE from jxsb_cxgz cz " +
	             		"where cz.SHENHE_STATUS in  ("+tj+") " +
	             		") bz left join jxsb_sygl gl on gl.jxsb_sygl_uid=bz.jxsb_sygl_uid " ;
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("GZQQ", "WAIDI_Y");
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
	   
	   public String queryDslSbDzx(String json, JxsbSyglVO vo, Map map) {
		    
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

	             /*String sql = "select  s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
	             		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
	             		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
	             		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
	             		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  ";*/
	             
	             
	             String sql=" select * from (select sygl.*,(select dj.sydj_bh from jxsb_sydj dj where dj.jxsb_sygl_uid=sygl.jxsb_sygl_uid) SYDJ_BH from jxsb_sygl sygl where sygl.jxsb_sygl_uid=(select cc.jxsb_sygl_uid  from jxsb_cxgc cc where cc.shenhe_status='50') and sygl.status='0' ) gl" ;
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            // 合同表
	            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
	            // 项目下达库
	            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
	            // 标段表
	            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

	            // 设置字典
	            bs.setFieldDic("GZQQ", "WAIDI_Y");
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
	   
	   public String querySyglCl(String json, JxsbSyglVO vo, Map map){  //安装人员 
		    
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

		           String sql = "SELECT * FROM " + "jxsb_sygl_cl t";
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
	   
	   

}
