/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpBzThlyDao.java
 * 创建日期： 2014-06-29 上午 10:05:44
 * 功能：   步骤处理时的退回理由
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-29 上午 10:05:44  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpBzThlyDao;
import com.ccthanking.business.spxx.vo.BuSpBzThlyVO;
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
 * <p> BuSpBzThlyDao.java </p>
 * <p> 功能：步骤处理时的退回理由 </p>
 *
 * <p><a href="BuSpBzThlyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-29
 * 
 */

@Component
public class BuSpBzThlyDaoImpl  extends BsBaseDaoTJdbc implements BuSpBzThlyDao {

    public String queryCondition(String json, BuSpBzThlyVO vo, Map map){
    
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

//            String sql = "SELECT * FROM (select ly.*,(select bz.bzmc from bu_sp_bz bz where bz.spbz_uid=ly.spbz_uid ) as bzmc from BU_SP_BZ_THLY ly) t";
            String sql="select * from (select ly.*,bz.bzmc as bzmc,yw.spyw_uid as spyw_uid,yw.spywmc as spywmc "+
					"	 from bu_sp_bz_thly ly "+
					"	left join bu_sp_bz bz on ly.spbz_uid=bz.spbz_uid "+
					"	left join bu_sp_ywxx yw on yw.spyw_uid=bz.spyw_uid ) t ";
            
            
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
            bs.setFieldDic("ENABLED", "SF");
            bs.setFieldDateFormat("CREATED_DATE", "yyyy-MM-dd HH:mm:ss");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String getSpYwxx() {
		Connection conn = DBUtil.getConnection();	

		String querySql=" select * from bu_sp_ywxx y where y.enabled='1' order by y.serial_no ,y.spyw_uid ";
		JSONArray jsonArr = new JSONArray();
		try {
		List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, querySql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();

				json.put("id", rsMap.get("SPYW_UID"));
				json.put("pid", rsMap.get("P_SPYW_UID"));
				json.put("name", rsMap.get("SPYWMC"));
				json.put("isParent", true);
			    jsonArr.add(json);
			}
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询审批业务信息出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}
    
    // 在此可加入其它方法

}
