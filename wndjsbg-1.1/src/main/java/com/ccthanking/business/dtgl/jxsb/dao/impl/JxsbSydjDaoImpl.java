/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.jxsb.JxsbSydjDao.java
 * 创建日期： 2014-12-26 上午 10:44:21
 * 功能：   机械设备使用登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-26 上午 10:44:21  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.jxsb.dao.impl;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ccthanking.business.bzwj.GongCheng;
import com.ccthanking.business.dtgl.azqy.vo.JxsbSydjVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbSydjDao;
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
 * <p> JxsbSydjDao.java </p>
 * <p> 功能：机械设备使用登记 </p>
 *
 * <p><a href="JxsbSydjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-26
 * 
 */

@Component
public class JxsbSydjDaoImpl  extends BsBaseDaoTJdbc implements JxsbSydjDao {

    public String queryCondition(String json, JxsbSydjVO vo, Map map,HttpServletRequest request){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        String start=request.getParameter("start");
        String end=request.getParameter("end");
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            if(!"".equals(start)&&start!=null){
            	condition += " and DJDATE >= to_date('"+start+"','yyyy-mm-dd') ";
            }
            if(!"".equals(end)&&end!=null){
            	condition += " and DJDATE <= to_date('"+end+"','yyyy-mm-dd') ";
            }
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM (select a.*,a.CREATED_DATE as DJDATE from JXSB_SYDJ a )  d " +
            		"left join JXSB_SYGL g on d.jxsb_sygl_uid=g.jxsb_sygl_uid " +
            		"left join JXSB s on g.jxsb_uid=s.jxsb_uid " +
            		"left join projects_gongcheng c on c.GONGCHENG_UID=g.GONGCHENG_UID " +
            		"left join projects f on c.PROJECTS_UID=f.PROJECTS_UID " +
            		"left join js_company j on f.js_company_uid=j.js_company_uid ";
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
            bs.setFieldDic("JXSB_TYPE_UID","AZ_JXSB_TYPE");
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryList(String json, JxsbSydjVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += " and s.SHENHE_STATUS in (30,40,41,50)"; 
            condition += orderFilter;
            if (page == null)
                page = new PageManager(); 
            page.setFilter(condition);

            String sql = " select g.GGXH,s.*,g.DONGSHU,g.GC_ADDRESS,g.XMJL,g.LXDH,g.GC_NAME,g.SHEBEI_NAME,g.CQ_BH,g.CQ_ATTRIBUTE " +
            		",(select u.user_name from users u where u.users_uid=s.XZSH_REN) XZSH_NAME " +
            		"from JXSB_SYDJ s left join JXSB_SYGL g on g.JXSB_SYGL_UID=s.JXSB_SYGL_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("CQ_ATTRIBUTE", "CQSX");

            bs.setFieldDateFormat("AZGZ_SG_DATE", "yyyy年MM日dd");// 计量月份
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
    
    public String queryJxsb(String json, JxsbSydjVO vo, Map map) {
        
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

            String sql = "SELECT j.JXSB_TYPE_UID,j.ZZXKZ,j.SB_XH,j.CC_CODE,j.ZZDW,j.CQDW FROM JXSB_SYGL s " +
            		"left join jxsb j on s.jxsb_uid=j.jxsb_uid";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
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
    
    public String queryRy(String json, JxsbSydjVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition +=" and JXSB_STEP = 'SYDJ' ";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = " select * from JXSB_CZRY";
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
    
    // 在此可加入其它方法

}
