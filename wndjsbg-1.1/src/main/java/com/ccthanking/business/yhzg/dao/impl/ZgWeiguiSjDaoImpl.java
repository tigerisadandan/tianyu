/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.yhzg.ZgWeiguiSjDao.java
 * 创建日期： 2015-04-21 下午 01:21:34
 * 功能：   违规事件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:21:34  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.yhzg.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.yhzg.vo.ZgWeiguiSjVO;
import com.ccthanking.business.yhzg.dao.ZgWeiguiSjDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> ZgWeiguiSjDao.java </p>
 * <p> 功能：违规事件 </p>
 *
 * <p><a href="ZgWeiguiSjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Component
public class ZgWeiguiSjDaoImpl  extends BsBaseDaoTJdbc implements ZgWeiguiSjDao {

    public String queryCondition(String json, ZgWeiguiSjVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            if(condition.indexOf("sj.ZG_WEIGUI_SJ_UID")!=-1){
            	condition = condition.replaceAll("\'", "");
            }
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
           
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT SJ.ZG_WEIGUI_SJ_UID,YH.NAME,SJ.WG_DENGJI,SJ.WEIGUI_CONTENT, ");
            sql.append(" YH.DESCRIPTION,SJ.XMJL_SCORE,SJ.ZJ_SCORE,SJ.JSDW_SCORE,SJ.SGDW_SCORE, ");
            sql.append(" SJ.JLDW_SCORE,t.content ");
            sql.append("  FROM ZG_WEIGUI_SJ SJ ");
            sql.append(" LEFT JOIN ZG_YINHUAN YH ");
            sql.append(" ON  SJ.ZG_YINHUAN_UID = YH.ZG_YINHUAN_UID ");
            sql.append(" left join zg_tiaoli t ");
            sql.append(" on  t.zg_tiaoli_uid = sj.zg_tiaoli_uid ");
            
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            
            // 设置字典
            bs.setFieldDic("WG_DENGJI", "YHZGWGDJ");
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
    
    /**
     * 获取
     * 
     * @return
     */
    public  String getTree(){
    	Connection conn = DBUtil.getConnection();
    	String sql = "select zg_yinhuan_uid as id,name,p_uid,levelno from ZG_YINHUAN where  code is not null order by levelno ";
		JSONArray jsonArr = new JSONArray();
		
    	try {
    		List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("ID"));
				json.put("parentId", rsMap.get("P_UID")==null?"":rsMap.get("P_UID"));
				json.put("name", rsMap.get("NAME"));
				json.put("levelno", rsMap.get("LEVELNO"));
				jsonArr.add(json);
			}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
    	
		return jsonArr.toString();
    }

	public String queryZgsj(String msg) {
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            if(condition.indexOf("ws.zg_weigui_sj_uid")!=-1){
            	condition = condition.replaceAll("\'", "");
            }
            String orderFilter = RequestUtil.getOrderFilter(msg);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
           
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" select ws.zg_weigui_sj_uid as sjuid,decode(ws.wg_dengji,'1','一般','2','较严重','3','严重') as wg_dengji,    ");
            sql.append("  y.name,ws.weigui_content as wg_miaoshu, ti.code as tiaoli ,'' description from  ");
            sql.append("  zg_weigui_sj ws  ");
            sql.append("  left join zg_yinhuan y  ");
            sql.append("  on ws.zg_yinhuan_uid = y.zg_yinhuan_uid  ");
            sql.append("  left join zg_tiaoli ti  ");
            sql.append("  on ws.zg_tiaoli_uid = ti.zg_tiaoli_uid  ");
            
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
            
            // 设置字典
            bs.setFieldDic("WG_DENGJI", "YHZGWGDJ");
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
