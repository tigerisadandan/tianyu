/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgsx.BuSpywJsgcaqjcsqDao.java
 * 创建日期： 2015-04-03 下午 03:13:08
 * 功能：   sg_《建设工程安全监督申报表》
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-03 下午 03:13:08  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgcaqjcsqVO;
import com.ccthanking.business.sgsx.dao.BuSpywJsgcaqjcsqDao;
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
 * <p> BuSpywJsgcaqjcsqDao.java </p>
 * <p> 功能：sg_《建设工程安全监督申报表》 </p>
 *
 * <p><a href="BuSpywJsgcaqjcsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-03
 * 
 */

@Component
public class BuSpywJsgcaqjcsqDaoImpl  extends BsBaseDaoTJdbc implements BuSpywJsgcaqjcsqDao {

    public String queryCondition(String json, BuSpywJsgcaqjcsqVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_JSGCAQJCSQ t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("TFKWGC_Y", "YOUWU");
            bs.setFieldDic("ZHICHENGGC_Y", "YOUWU");
            bs.setFieldDic("GLGJ_Y", "YOUWU");
            bs.setFieldDic("DLJSJGC_Y", "YOUWU");
            bs.setFieldDic("ZZYCPT_Y", "YOUWU");
            bs.setFieldDic("JSJGC_Y", "YOUWU");
            bs.setFieldDic("CCGC_Y", "YOUWU");
            bs.setFieldDic("GJWCCGC_Y", "YOUWU");
            bs.setFieldDic("YXCCGC_Y", "YOUWU");
            bs.setFieldDic("BFJZCCGC_Y", "YOUWU");
            bs.setFieldDic("BPCCGC_Y", "YOUWU");
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
    
public String queryByGcId(String json, BuSpywJsgcaqjcsqVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            String sql = "select * from BU_SPYW_JSGCAQJCSQ j left join bu_sp_ywlz l on j.ywlz_uid=l.ywlz_uid where l.projects_uid ='"+json+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
               bs.setFieldDic("TFKWGC_Y", "YOUWU");
               bs.setFieldDic("ZHICHENGGC_Y", "YOUWU");
               bs.setFieldDic("GLGJ_Y", "YOUWU");
               bs.setFieldDic("DLJSJGC_Y", "YOUWU");
               bs.setFieldDic("ZZYCPT_Y", "YOUWU");
               bs.setFieldDic("JSJGC_Y", "YOUWU");
               bs.setFieldDic("CCGC_Y", "YOUWU");
               bs.setFieldDic("GJWCCGC_Y", "YOUWU");
               bs.setFieldDic("YXCCGC_Y", "YOUWU");
               bs.setFieldDic("BFJZCCGC_Y", "YOUWU");
               bs.setFieldDic("BPCCGC_Y", "YOUWU");

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
    
    public String queryDtgc(String gcid, BuSpywJsgcaqjcsqVO vo, Map map){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
           
            String sql = "select (select JG_DATE-PLAN_KG_DATE from projects fq where fq.projects_uid=p.projects_uid) GQ_DATE,u.* from projects_units u " +
            		"left join projects p on p.projects_uid=u.projects_uid " +
            		"left join projects_gongcheng gc on gc.Projects_Uid=p.Projects_Uid " +
            		"left join bu_sp_ywlz lz on lz.projects_uid= gc.GONGCHENG_UID " +
            		"where lz.ywlz_uid='"+gcid+"'";
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
               
               bs.setFieldDic("DSJG", "DT_DXJG");
               bs.setFieldDic("DXJG", "DT_DXJG");
               bs.setFieldDic("JS_XZ", "DT_JS_XZ");
               bs.setFieldDic("BPCCGC_Y", "YOUWU");
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
