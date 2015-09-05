/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.rygl.WuGongDao.java
 * 创建日期： 2015-03-24 上午 11:44:51
 * 功能：   务工信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 上午 11:44:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.rygl.vo.WuGongVO;
import com.ccthanking.business.rygl.dao.WuGongDao;
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
 * <p> WuGongDao.java </p>
 * <p> 功能：务工信息 </p>
 *
 * <p><a href="WuGongDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */

@Component
public class WuGongDaoImpl  extends BsBaseDaoTJdbc implements WuGongDao {

    public String queryCondition(String json, WuGongVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT M.MINGONG_UID,M.MINGONG_NAME，M.SEX,Z.ZHENGJIAN_TYPE_NAME, ");
            sql.append(" DECODE(M.CARD_FLAG,NULL,'未发','Y','已发') AS XXK,M.ZHENGJIAN_NUMBER,M.LIANXI_PHONE,M.ORIGIN, ");
            sql.append(" DECODE(M.STATUS, 0,'空闲',1,'在工',-1,'注销') AS ZT , ");
            sql.append(" W.BEGIN_DATE,W.END_DATE ");
            sql.append(" FROM WUGONG W ");
            sql.append(" LEFT JOIN MINGONG M ");
            sql.append(" ON M.MINGONG_UID = W.MINGONG_UID ");
            sql.append(" LEFT JOIN ZHENGJIAN_TYPE Z ");
            sql.append(" ON M.ZHENGJIAN_TYPE_UID = Z.ZHENGJIAN_TYPE_UID ");
            
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
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

	public String wugongtj(String msg) {
        Connection conn = DBUtil.getConnection();
        String sdate = "";
        String edate = "";
        String tjlx = "";
        String domresult = "";

        try {
	    		JSONObject querycondition = JSONObject.fromObject(msg);
	    		String querycondition_txt = querycondition.getString("querycondition");
	            JSONObject subcondition = JSONObject.fromObject(querycondition_txt);
	    		querycondition_txt = subcondition.getString("conditions");
	            JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(querycondition_txt);
	            PageManager page = RequestUtil.getPageManager(msg);
	            if (page == null)
	                page = new PageManager();
	            for (int i = 0; i < jsonArray.size(); i++) {
	    			JSONObject obj = (JSONObject) jsonArray.get(i);
	    			if("tjlx".equals(obj.get("fieldname"))){
	    				tjlx = obj.getString("value");
	    			}
	    			if("B_DATE".equals(obj.get("fieldname"))){
	    				sdate = obj.getString("value");
	    			}
	    			if("E_DATE".equals(obj.get("fieldname"))){
	    				edate = obj.getString("value");
	    			}
	    		}
	            StringBuffer sql = new  StringBuffer();
	            if("gw".equals(tjlx)){
	            	sql.append(" select a.gangwei,decode(a.allnum, null, '0', a.allnum) as zs,decode(b.zg, null, '0', b.zg) as zg,decode(c.tg, null, '0', c.tg) as tg ");
	            	sql.append("   from (select gangwei, count(*) as allnum from wugong where 1 = 1 and BEGIN_DATE between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd')  group by gangwei) a ");
	            	sql.append("   left join  ");
	            	sql.append("   (select gangwei, count(*) as zg from wugong where status = '1' and BEGIN_DATE between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd')  group by gangwei) b ");
	            	sql.append("   on a.gangwei = b.gangwei ");
	            	sql.append("   left join  ");
	            	sql.append("   (select gangwei, count(*) as tg from wugong where status = '0' and BEGIN_DATE between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd')  group by gangwei) c ");
	            	sql.append("  on a.gangwei = c.gangwei ");
	            }else{
	            	sql.append(" select a.GONGZHONG,decode(a.allnum,null,'0',a.allnum) as zs,decode(b.zg,null,'0',b.zg) as zg,decode(c.tg,null,'0',c.tg) as tg from ");
	            	sql.append("  (select GONGZHONG,count(*) as allnum from wugong where 1=1 and BEGIN_DATE between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd')   group by GONGZHONG ) a ");
	            	sql.append(" left join  ");
	            	sql.append(" (select GONGZHONG,count(*) as zg from wugong where status = '1' and BEGIN_DATE between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd')  group by GONGZHONG ) b ");
	            	sql.append(" on a.GONGZHONG = b.GONGZHONG ");
	            	sql.append(" left join ");
	            	sql.append(" (select GONGZHONG,count(*) as tg from wugong where status = '0' and BEGIN_DATE between to_date('"+sdate+"', 'yyyy/mm/dd') and to_date('"+edate+"', 'yyyy/mm/dd')  group by GONGZHONG ) c ");
	            	sql.append(" on a.GONGZHONG = c.GONGZHONG ");
	
	            }
	            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
	            domresult = bs.getJson();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String queryById(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            StringBuffer sql = new StringBuffer();
            sql.append(" select m.mingong_uid,m.mingong_name,m.mingong_photo, ");
            sql.append(" m.zhengjian_number,m.sex,m.birth_date,m.ADDRESS, ");
            sql.append(" m.ORIGIN,m.lianxi_phone,m.lianxi_ren,m.lianxi_ren_phone, ");
            sql.append(" m.xueli,decode(m.huji_type,'0','农业','1','非农业') as hujilx, ");
            sql.append(" decode(m.person_type,'0','本省市','1','外省市') as personlx, ");
            sql.append(" decode(m.huiyuan,'N','非会员','Y','会员') as hy, ");
            sql.append(" decode(m.peixun,'0','参加过培训','1','没有') as px , ");
            sql.append(" m.peixun_date,c.companys_name,z.zhengjian_type_name ");
            sql.append(" from mingong m ");
            sql.append(" left join companys c ");
            sql.append(" on m.companys_uid = c.companys_uid ");
            sql.append(" left join ZHENGJIAN_TYPE z ");
            sql.append(" on m.zhengjian_type_uid = z.zhengjian_type_uid  ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            domresult = bs.getJson();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	DBUtil.closeConnetion(conn);
        }
		return domresult;
	}

	public String queryGzqk(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            StringBuffer sql = new StringBuffer();
            sql.append(" select pg.gongcheng_name,t.gangwei,t.gongzhong,t.begin_date,t.end_date from wugong t ");
            sql.append(" left join PROJECTS_GONGCHENG pg ");
            sql.append(" on t.zuzhi_guanxi_uid = pg.gongcheng_uid ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            domresult = bs.getJson();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	DBUtil.closeConnetion(conn);
        }
		return domresult;
	}

	public String queryJineng(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            StringBuffer sql = new StringBuffer();
            sql.append(" select * from jineng t ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            domresult = bs.getJson();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	DBUtil.closeConnetion(conn);
        }
		return domresult;
	}

	public String queryTijianInfo(String msg) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            StringBuffer sql = new StringBuffer();
            sql.append(" select * from tijian_info t ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            domresult = bs.getJson();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	DBUtil.closeConnetion(conn);
        }
		return domresult;
	}
    
    // 在此可加入其它方法

}
