/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jl.JlPersonZhengshuDao.java
 * 创建日期： 2015-01-26 下午 02:23:22
 * 功能：   监理人员证书
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:23:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jl.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.jl.vo.JlPersonZhengshuVO;
import com.ccthanking.business.jl.dao.JlPersonZhengshuDao;
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
 * <p> JlPersonZhengshuDao.java </p>
 * <p> 功能：监理人员证书 </p>
 *
 * <p><a href="JlPersonZhengshuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Component
public class JlPersonZhengshuDaoImpl  extends BsBaseDaoTJdbc implements JlPersonZhengshuDao {

    public String queryCondition(String json, JlPersonZhengshuVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "JL_PERSON_ZHENGSHU t";
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
    
    
public String queryListPersonZhengshu(String uid) throws Exception {
		
		Connection conn = DBUtil.getConnection();
		/*String ListZhengshu="select a.sg_person_zhengshu_uid,a.sg_person_uid,sgk_package.person_zhengshu_change(SG_PERSON_ZHENGSHU_UID) XX,a.sg_zhengshu_uid,a.sg_zizhi_uid,a.zhengshu_code,a.begin_date,a.end_date,b.zhengshu_name,c.zhuanye_name from sg_person_zhengshu a left join sg_zhengshu b on a.sg_zhengshu_uid=b.sg_zhengshu_uid"
            +" left join sg_zizhi c on a.sg_zizhi_uid=c.sg_zizhi_uid where a.sg_person_uid='"+uid+"' order by a.SERIAL_NO desc";
		*/
		JSONArray jsonArr = new JSONArray();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM JL_PERSON_ZHENGSHU P where person_library_uid = '");
		sql.append(uid);
		sql.append("'");
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("JL_PERSON_ZHENGSHU_UID",rsMap.get("JL_PERSON_ZHENGSHU_UID"));
				jsonObj.put("JL_PERSON_UID", rsMap.get("JL_PERSON_UID"));
			    jsonObj.put("JL_ZHENGSHU_UID", rsMap.get("JL_ZHENGSHU_UID"));
			    jsonObj.put("ZHUCE_NAME", rsMap.get("ZHUCE_NAME"));
			    jsonObj.put("ZHUCE_CODE", rsMap.get("ZHUCE_CODE"));
			    jsonObj.put("FAZHENG_DATE", rsMap.get("FAZHENG_DATE"));
			    jsonObj.put("VALID_DATE", rsMap.get("VALID_DATE"));
			
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
}

}
