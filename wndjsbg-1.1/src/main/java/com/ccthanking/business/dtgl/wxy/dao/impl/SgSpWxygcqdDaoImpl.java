/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.wxy.SgSpWxygcqdDao.java
 * 创建日期： 2015-04-23 上午 11:44:17
 * 功能：   较大危险源工程清单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 上午 11:44:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.wxy.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.wxy.dao.SgSpWxygcqdDao;
import com.ccthanking.business.dtgl.wxy.vo.SgSpWxygcqdVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.ibm.icu.text.DecimalFormat;


/**
 * <p> SgSpWxygcqdDao.java </p>
 * <p> 功能：较大危险源工程清单 </p>
 *
 * <p><a href="SgSpWxygcqdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

@Component
public class SgSpWxygcqdDaoImpl  extends BsBaseDaoTJdbc implements SgSpWxygcqdDao {

    public String queryCondition(String json, SgSpWxygcqdVO vo, Map map){
    
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

            String sql = "select * from (SELECT qd.*,sj.status as JLSH_STATUS FROM SG_SP_WXYGCQD qd left join WXY_SJK sj on qd.gc_uid=sj.gongcheng_uid )";
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
    
    public String queryWxYBh(String gctype, SgSpWxygcqdVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	String sql="";
            if("SJK".equals(gctype)){
                sql="select count(*) from wxy_sjk where CREATE_DATE " +
            		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
            		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }else if("GDMB".equals(gctype)){
            	sql="select count(*) from wxy_GDMB where CREATE_DATE " +
                		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
                		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }else if("DZGC".equals(gctype)){
            	sql="select count(*) from wxy_DZGC where CREATE_DATE " +
                		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
                		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }else if("JSJ".equals(gctype)){
            	sql="select count(*) from wxy_JSJ where CREATE_DATE " +
                		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
                		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }else if("MQ".equals(gctype)){
            	sql="select count(*) from wxy_MQ where CREATE_DATE " +
                		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
                		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }else if("GJG".equals(gctype)){
            	sql="select count(*) from wxy_GJG where CREATE_DATE " +
                		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
                		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }else if("WJ".equals(gctype)){
            	sql="select count(*) from wxy_WJ where CREATE_DATE " +
                		"between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') " +
                		"and to_date((select Extract(year from sysdate)||'-12-31 24:59:59' from dual),'yyyy-mm-dd hh24:mi:ss')";	
            }
            String[][] res=DBUtil.query(sql);
            int year=new Date().getYear()+1900;
            String pattern="00";
            DecimalFormat df=new DecimalFormat(pattern);
            domresult=gctype+year+df.format(Integer.parseInt(res[0][0])+1);;
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryGcStatus(Map map, SgSpWxygcqdVO vo, Map map2){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	String sql="select STATUS from WXY_"+map.get("TYPE")+" where GONGCHENG_UID='"+map.get("id")+"'";
            String[][] res=DBUtil.query(sql);
            if(res!=null){
            	domresult=res[0][0];	
            }
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    // 在此可加入其它方法

}
