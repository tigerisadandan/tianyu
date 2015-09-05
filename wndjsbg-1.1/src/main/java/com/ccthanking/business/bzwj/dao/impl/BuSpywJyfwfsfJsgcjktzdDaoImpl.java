/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.bzwj.BuSpywJyfwfsfJsgcjktzdDao.java
 * 创建日期： 2014-11-18 下午 03:02:34
 * 功能：   十、交易服务费收费
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 03:02:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bzwj.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.bzwj.BuSpywJyfwfsfJsgcjktzdVO;
import com.ccthanking.business.bzwj.dao.BuSpywJyfwfsfJsgcjktzdDao;
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
 * <p> BuSpywJyfwfsfJsgcjktzdDao.java </p>
 * <p> 功能：十、交易服务费收费 </p>
 *
 * <p><a href="BuSpywJyfwfsfJsgcjktzdDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

@Component
public class BuSpywJyfwfsfJsgcjktzdDaoImpl  extends BsBaseDaoTJdbc implements BuSpywJyfwfsfJsgcjktzdDao {

    public String queryCondition(String json, BuSpywJyfwfsfJsgcjktzdVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_JYFWFSF_JSGCJKTZD t";
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
    
    public String getCount() {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="";
       
        	sql="select count(*) from BU_SPYW_JYFWFSF_JSGCJKTZD ";
      
        String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
    
public String queryByLzbz(String lzbzId){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {


            String sql = "select a.*,(select c.opturl from bu_sp_bzwj c where c.bzwj_uid in(b.bzwj_uid)) as url" +
            		",(select c.wjname from bu_sp_bzwj c where c.bzwj_uid in(b.bzwj_uid)) as wjname " +
            		"from BU_SPYW_JYFWFSF_JSGCJKTZD a left join bu_sp_ywlz_bzwj b on  a.lzbz_wj_uid in b.LZBZ_WJ_UID where b.lzbz_uid='"+lzbzId+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null);
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

  public List<Map<String, String>> queryTpfFileNameByScxmid(String id){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        List<Map<String, String>> bs=null;
        try {

            String sql = "select * from bu_sp_bzwj a where a.bzwj_uid=(select b.bzwj_uid from bu_sp_ywlz_bzwj b where " +
            		"b.lzbz_wj_uid=(select c.lzbz_wj_uid from BU_SPYW_JYFWFSF_JSGCJKTZD c where c.JYFWFSF_JSGCJKTZD_UID='"+id+"'))";
             bs = DBUtil.queryReturnList(conn, sql);
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

            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return bs;

    }
}
