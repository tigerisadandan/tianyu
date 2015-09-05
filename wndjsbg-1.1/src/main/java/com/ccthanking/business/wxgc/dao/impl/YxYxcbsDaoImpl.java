/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.wxgc.YxYxcbsDao.java
 * 创建日期： 2014-12-23 下午 01:31:30
 * 功能：   预选承包商
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:31:30  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxYxcbsDao;
import com.ccthanking.business.wxgc.vo.YxYxcbsVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxYxcbsDao.java </p>
 * <p> 功能：预选承包商 </p>
 *
 * <p><a href="YxYxcbsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Component
public class YxYxcbsDaoImpl  extends BsBaseDaoTJdbc implements YxYxcbsDao {

    public String queryCondition(String json, YxYxcbsVO vo, Map map){
    
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

            String sql = " SELECT * FROM (SELECT yy.*,vya.username as username FROM YX_YXCBS yy "+
            			" left join  v_yx_allcbsxx vya on yy.company_uid=vya.useruid and yy.cbs_type=vya.xtly ) t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("CBS_TYPE", "WXGC_YXCBSLX");
            bs.setFieldDic("ENABLED", "SF");
            bs.setFieldDic("ZT", "STATUS");
            // 设置查询条件
            bs.setFieldDateFormat("GSKSRQ", "yyyy-MM-dd HH:mm");// 计量月份
            bs.setFieldDateFormat("GSJZRQ", "yyyy-MM-dd HH:mm");
            
            bs.setFieldDateFormat("XZCZRQ", "yyyy-MM-dd");
            bs.setFieldDateFormat("XZDQRQ", "yyyy-MM-dd");
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    
    public String queryspjlCondition(String json, YxYxcbsVO vo, Map map){
//    	User user = ActionContext.getCurrentUserInThread();
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

            
            String sql = "SELECT * FROM (select s.* from yx_yxcbs_shjl s ) t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
          
            bs.setFieldDic("SHJG", "STATUS");
            
            bs.setFieldDateFormat("SHRQ", "yyyy-MM-dd");// 计量月份

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return domresult;
    }
    
    

    public String queryCondition(String json){
//    	User user = ActionContext.getCurrentUserInThread();
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

            
            String sql = " select * from ( "+
            " select distinct(ys.yxcbs_shjl_uid),ys.yxcbs_uid,ys.shr,ya.username,ys.shjg,ys.shrq,ys.shyj, "+
            " yy.cbs_type,yy.company_uid,yy.zt as yxcbszt,yall.username as companyname, "+
            " (select WM_CONCAT(yt.gc_type_code) from yx_cbs_gc_type yt where yt.yxcbs_uid=ys.yxcbs_uid )  as typecode, "+
            " (select WM_CONCAT(yg.gc_type_name) from yx_cbs_gc_type yt left join yx_gc_type yg on yg.gc_type_code=yt.gc_type_code  where yt.yxcbs_uid=ys.yxcbs_uid ) as typename "+
            " from yx_yxcbs_shjl ys "+
            " left join yx_yxcbs yy on yy.yxcbs_uid=ys.yxcbs_uid "+
            " left join v_yx_allcbsxx ya on ya.dengluname=ys.shr and ya.xtly='BG' " +
            " left join v_yx_allcbsxx yall on yall.useruid=yy.company_uid and yall.xtly=yy.cbs_type "+
            " left join yx_cbs_gc_type yt on yt.yxcbs_uid=ys.yxcbs_uid "+
            " ) t ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
          
            bs.setFieldDic("SHJG", "STATUS");
            bs.setFieldDic("YXCBSZT", "STATUS");
            bs.setFieldDic("CBS_TYPE", "WXGC_YXCBSLX");//
            bs.setFieldDateFormat("SHRQ", "yyyy-MM-dd");// 计量月份

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return domresult;
    }
    
    // 在此可加入其它方法

	public List<Map<String, String>> cbsgctypeList(Map map) {
		User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        List<Map<String,String>>  domresult =null;
        
        String YXCBS_UID=(String)map.get("YXCBS_UID");
        try {

            String sql = "SELECT * FROM YX_CBS_GC_TYPE t where t.YXCBS_UID='"+YXCBS_UID+"'" ;
            		
            domresult= DBUtil.queryReturnList(conn, sql);
           
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}


}
