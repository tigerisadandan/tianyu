/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dtgl.JxsbCxgzDao.java
 * 创建日期： 2015-01-26 下午 04:31:09
 * 功能：   机械设备拆卸告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 04:31:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dtgl.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.bzwj.GongCheng;
import com.ccthanking.business.dtgl.dao.XyFzDao;
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
 * <p> JxsbCxgzDao.java </p>
 * <p> 功能：信用分值管理 </p>
 *
 * <p><a href="XyFzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-08-04
 * 
 */

@Component
public class XyFzDaoImpl  extends BsBaseDaoTJdbc implements XyFzDao {

    public String queryCondition(String json,String qiyeType){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件 
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += " and t.status = 1";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            String sql = "";
            	if(qiyeType.equals("JS")){//建设
            		sql = "select t.js_company_uid company_uid,t.company_name ,t.jiguo_daima company_code,t.address,t.fzr,t.fzr_mobile,t.score from js_company t";
            	}else if(qiyeType.equals("SG")){//施工
            		sql = "select t.sg_company_uid company_uid,t.company_name,t.company_code   company_code,t.address,t.lianxiren fzr,t.lianxiren_mobile fzr_mobile,t.score from sg_enterprise_library t";
            	}else if(qiyeType.equals("JL")){//监理
            		sql = "select t.jl_company_uid company_uid,t.company_name,t.company_code,t.address,t.lianxiren fzr ,t.lianxiren_mobile fzr_mobile,t.score from enterprise_library t ";
            	}else if(qiyeType.equals("JLRY")){//监理人员
            		sql= "select t.jl_person_uid person_uid,t.person_name,(select el.company_name from enterprise_library el where el.jl_company_uid = t.jl_company_uid and el.status = 1) company_name, t.shenfenzheng,t.phone,t.score from person_library t";
            	}else if(qiyeType.equals("SGRY")){//施工人员
            		sql= "select t.sg_person_uid person_uid ,t.person_name,( select el.company_name from sg_enterprise_library el where el.sg_company_uid = t.sg_company_uid and el.status = 1 ) company_name,t.shenfenzheng,t.phone,t.score from sg_person_library t ";
            	}
           
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy年MM日dd");// 计量月份
            bs.setFieldDateFormat("SHOULI_DATE", "yyyy年MM日dd");// 计量月份 
            bs.setFieldDateFormat("XZSH_DATE", "yyyy年MM日dd");// 计量月份 
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy年MM日dd");// 计量月份 

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
    
    public boolean updateScore(String qiyeType,String id,String score){
    	User user = ActionContext.getCurrentUserInThread();
        
        Connection conn = DBUtil.getConnection();
        boolean  domresult = false;
        try {
            String sql = "";
            	if(qiyeType.equals("JS")){//建设
            		sql = "update js_company t set t.score = "+score+" where t.status = 1 and  t.js_company_uid = "+id;
            	}else if(qiyeType.equals("SG")){//施工
            		sql = "update sg_enterprise_library t set t.score = "+score+" where t.status = 1 and t.sg_company_uid = "+id;
            	}else if(qiyeType.equals("JL")){//监理
            		sql = "update enterprise_library t set t.score = "+score+" where t.status and t.jl_company_uid = "+id;
            	}else if(qiyeType.equals("JLRY")){//监理人员
            		sql= "update person_library t set t.score = "+score+" where t.status = 1 and t.jl_person_uid = "+id;
            	}else if(qiyeType.equals("SGRY")){//施工人员
            		sql= "update sg_person_library t set t.score = "+score+" where t.status = 1 and t.sg_person_uid = " + id;
            	}
            	domresult = DBUtil.execSql(sql);

        } catch (Exception e) {
        	DBUtil.rollbackConnetion(conn);
            DaoException.handleMessageException("*********更新出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
    }

    // 在此可加入其它方法

}
