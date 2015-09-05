/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.wxgc.YxLhEnterpriseDao.java
 * 创建日期： 2014-12-23 下午 01:46:44
 * 功能：   绿化企业信息库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:46:44  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxLhEnterpriseDao;
import com.ccthanking.business.wxgc.vo.YxLhEnterpriseVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxLhEnterpriseDao.java </p>
 * <p> 功能：绿化企业信息库 </p>
 *
 * <p><a href="YxLhEnterpriseDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Component
public class YxLhEnterpriseDaoImpl  extends BsBaseDaoTJdbc implements YxLhEnterpriseDao {

    public String queryCondition(String json, YxLhEnterpriseVO vo, Map map){
    
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

            String sql = "SELECT * FROM ( SELECT e.*,(select yy.yxcbs_uid from yx_yxcbs yy where yy.cbs_type='"+Constants.YXCBS_LX_LH+"'" +
            		"  and yy.company_uid=e.lh_enterprise_uid ) as yxcbs_uid FROM YX_LH_ENTERPRISE e ) t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");
            bs.setFieldDic("WAIDI_Y", "WAIDI_Y");
            bs.setFieldDic("STATUS", "STATUS");
            bs.setFieldDateFormat("ZHIZHAO_VALID", "yyyy-MM-dd");
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
    
    
    public String queryOldCondition(String json, YxLhEnterpriseVO vo, Map map){
        
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
            orderFilter+=" , createddate desc ";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM YX_LH_ENTERPRISE_HIS t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            // 设置字典
            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");
            bs.setFieldDic("WAIDI_Y", "WAIDI_Y");
            bs.setFieldDic("STATUS", "STATUS");
            bs.setFieldDateFormat("ZHIZHAO_VALID", "yyyy-MM-dd");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
 
    
    public String queryspjlCondition(String json, YxLhEnterpriseVO vo, Map map){
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

            
            String sql = "SELECT * FROM (select s.* from yx_lh_enterprise_shjl s ) t";
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
    // 在此可加入其它方法
    
    public String getDengluCode() throws Exception{
    	Connection conn = DBUtil.getConnection();
		String code = null;
		try {
			
			String sql = "select  max(to_number(substr(t.denglu_code,6,9))) from yx_lh_enterprise t where t.denglu_code like 'WNDLH%'";
			String[][] res = DBUtil.query(conn ,sql);
			if (res!=null&&StringUtils.isNotBlank(res[0][0])) {
				String code1 = Integer.parseInt(res[0][0]) + 1 + "";
				code = code1;
				for (int i = 0; i < 5-code1.length(); i++) {
					code = "0" +code;
				}
			}else{
				code="00001";
			}
			code = "WNDLH" + code;
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询绿化企业登录代码出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return code;
	}
    
    

	public JSONObject hisQyxxJSONObject(String uid) {
        Connection conn = DBUtil.getConnection();
        JSONObject obj=new JSONObject();
        List<Map<String, String>> domresult = null;
        try {
            String sql = "select * from yx_lh_enterprise_his t where t.lh_enterprise_uid ='"+uid+"' order by t.createddate desc ";
            domresult= DBUtil.queryReturnList(conn, sql);
            if(domresult!=null&&domresult.size()>0){
            	Map<String, String> map=domresult.get(0);
            	Iterator entries = map.entrySet().iterator();
            	while (entries.hasNext()) {
            	    Map.Entry entry = (Map.Entry) entries.next();
            	    obj.put(entry.getKey(), entry.getValue());
            	}
            }
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return obj;
	}
}
