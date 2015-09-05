/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.rygl.DtRyBiangengDao.java
 * 创建日期： 2015-04-12 下午 08:31:04
 * 功能：   人员变更
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-12 下午 08:31:04  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.rygl.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.rygl.vo.DtRyBiangengVO;
import com.ccthanking.business.rygl.dao.DtRyBiangengDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;


/**
 * <p> DtRyBiangengDao.java </p>
 * <p> 功能：人员变更 </p>
 *
 * <p><a href="DtRyBiangengDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java1@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-12
 * 
 */

@Component
public class DtRyBiangengDaoImpl  extends BsBaseDaoTJdbc implements DtRyBiangengDao {

    public String queryCondition(String json, DtRyBiangengVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
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

            //String sql = "select * from v_xcrygl_rybgsh ";
            StringBuffer sql = new StringBuffer();
            sql.append(" select * from v_xcrygl_rybgsh db ");
            sql.append(" where ");
            sql.append(" ((db.shenpi_status = 1 and ");
            sql.append("        quanxian_package.get_bg_user_quanxian('bg_rybgyjsh', 191) = 1) or ");
            sql.append("        (db.shenpi_status = 2 and ");
            sql.append("        quanxian_package.get_bg_user_quanxian('bg_rybgejsh', 191) = 1) or ");
            sql.append("        (db.shenpi_status = 3 and ");
            sql.append("        quanxian_package.get_bg_user_quanxian('bg_rybgsjsh', 191) = 1)) ");

            
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

    /**
     * 查询待审核变更
     */
	public String getBgCount(String msg) {
		User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "0";
        try {
            // 组织查询条件
            //String sql = "select count(0) from dt_ry_biangeng t where status = '0'";
            StringBuffer sql = new StringBuffer();
            sql.append(" select count(*) ");
            sql.append("   from dt_ry_biangeng db ");
            sql.append("  where db.status = 0 ");
            sql.append("    and ((db.shenpi_status = 1 and ");
            sql.append("        quanxian_package.get_bg_user_quanxian('bg_rybgyjsh', "+user.getUserSN()+") = 1) or ");
            sql.append("        (db.shenpi_status = 2 and ");
            sql.append("        quanxian_package.get_bg_user_quanxian('bg_rybgejsh', "+user.getUserSN()+") = 1) or ");
            sql.append("        (db.shenpi_status = 3 and ");
            sql.append("        quanxian_package.get_bg_user_quanxian('bg_rybgsjsh', "+user.getUserSN()+") = 1)) ");

            String[][] res = DBUtil.query(conn, sql.toString());
            domresult = res[0][0];
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public String queryBg(String msg) {
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

            String sql = "select * from dt_ry_biangeng";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	/***
	 * 审核通过
	 */
	public String updateTg(String msg) {
		User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        DtRyBiangengVO vo = new DtRyBiangengVO();

        try {
            JSONArray list = vo.doInitJson(msg);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String spstatus = vo.getShenpi_status();
            String gwuid = obj.get("GANGWEI_UID").toString();
            String sj = vo.getNew_zhengshu_code2()==null?"":vo.getNew_zhengshu_code2().toString();
            
            //审批状态：1－一级待审（安质监待审）；2－二级待审（招标办待审）；3－三级待审（主管部门待审）
            if("1".equals(spstatus)){
            	vo.setShenhe_user1(user.getUserSN());
            	vo.setShenhe_date1(new Date());
            	vo.setShenpi_status("2");
            }else if("2".equals(spstatus)){
            	vo.setShenhe_user2(user.getUserSN());
            	vo.setShenhe_date2(new Date());
            	//10总监   15安全监理   19项目负责人      22	安全员
            	if(!"10".equals(gwuid)&&!"15".equals(gwuid)&&!"19".equals(gwuid)&&!"22".equals(gwuid)){
            		vo.setStatus("1");
            	}else{
            		vo.setShenpi_status("3");
            	}
            }else if("3".equals(spstatus)){
            	vo.setFinish_user(user.getUserSN());
            	vo.setFinish_date(new Date());
            	vo.setStatus("1");
            }
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getUserSN());
            update(vo);
            resultVO = vo.getRowJson();
            //审核通过,给报备添加一条新数据 修改原报备数据
            if("1".equals(vo.getStatus())){
            	String new_id = "";
            	String buid = "";
            	String olduid = vo.getBb_ry_uid();
            	StringBuffer copy_sql = new StringBuffer();
            	StringBuffer update_sql = new StringBuffer();
            	StringBuffer insert_sql = new StringBuffer();
            	if("SG".equals(vo.getSg_jl())){
            	   new_id = DBUtil.getSequenceValue("SGBB_RY_UID");
            	   copy_sql.append(" insert into sgbb_ry   ");
            	   copy_sql.append(" (  SGBB_RY_UID,SGBB_UID,EVENT_UID,ENABLED,DESCRIBE, ");
            	   copy_sql.append("   CREATED_UID,CREATED_NAME,CREATED_DATE,UPDATE_UID, ");
            	   copy_sql.append("   UPDATE_NAME,UPDATE_DATE,SERIAL_NO,XUHAO,MUST_Y, ");
            	   copy_sql.append("   SG_PERSON_UID,SG_NAME,ZHENGSHU_NAME,ZHUANYE, ");
            	   copy_sql.append("   ZHENGSHU_CODE,ZHENGSHU_DATE,AQKH_CODE,AGE, ");
            	   copy_sql.append("   ZHICHENG_NAME,MOBILE,SHENFENZHENG,UPDATED_DATE, ");
            	   copy_sql.append("   STATUS,LOCK_END_DATE,CHANGED_DATE,CHANGED_REASON, ");
            	   copy_sql.append("   YOUXIAO_Y,OLD_UID,GANGWEI_UID) ");
            	   copy_sql.append(" select  '"+new_id+"',SGBB_UID,EVENT_UID,ENABLED,DESCRIBE, ");
            	   copy_sql.append("   CREATED_UID,CREATED_NAME,CREATED_DATE,UPDATE_UID, ");
            	   copy_sql.append("   UPDATE_NAME,sysdate,SERIAL_NO,XUHAO,MUST_Y, ");
            	   copy_sql.append("   '"+vo.getNew_person_uid()+"','"+vo.getNew_person_name()+"','"+vo.getNew_zhengshu_name()+"','"+vo.getNew_zhuanye()+"', ");
            	   copy_sql.append("   '"+vo.getNew_zhengshu_code()+"','"+sj+"','"+vo.getNew_aqkh_code()+"','"+vo.getNew_age()+"', ");
            	   copy_sql.append("   '"+vo.getNew_zhicheng_name()+"','"+vo.getNew_mobile()+"','"+vo.getNew_shenfenzheng()+"',sysdate, ");
            	   copy_sql.append("   STATUS,LOCK_END_DATE,sysdate,'"+vo.getReason()+"', ");
            	   copy_sql.append("   'Y',sgbb_ry_uid,GANGWEI_UID ");
            	   copy_sql.append(" from sgbb_ry where sgbb_ry_uid =  '"+olduid+"'");
            	   
            	   //项目经理变更后锁定半年,安全员变更后锁定3个月,其他人员自动解锁
            	   if("19".equals(gwuid)&&!"15".equals(gwuid)){
            		   update_sql.append(" update sgbb_ry t set t.youxiao_y = 'N', t.lock_end_date = add_months(t.lock_end_date,6) ");
            		   update_sql.append(" where t.sgbb_ry_uid = '"+olduid+"' "); 
            	   }else if("22".equals(gwuid)){
            		   update_sql.append(" update sgbb_ry t set t.youxiao_y = 'N', t.lock_end_date = add_months(t.lock_end_date,3)");
            		   update_sql.append(" where t.sgbb_ry_uid = '"+olduid+"' "); 
            	   }else{
            		   update_sql.append(" update sgbb_ry t set t.youxiao_y = 'N',t.status = '0'  ");
            		   update_sql.append(" where t.sgbb_ry_uid = '"+olduid+"' "); 
            	   }
            	   
            	   buid = DBUtil.getSequenceValue("DT_GC_SGRY_UID"); 
            	   insert_sql.append("insert into dt_gc_sgry  (dt_gc_sgry_uid, gongcheng_uid, sgbb_ry_uid, sg_person_uid, kq_y, kq_begin_date)");
            	   insert_sql.append("values ('"+buid+"','"+vo.getGongcheng_uid()+"','"+new_id+"','"+vo.getNew_person_uid()+"','Y',sysdate+1)");
            	   

            	}else if("JL".equals(vo.getSg_jl())){
            	   new_id = DBUtil.getSequenceValue("JLBB_JLY_UID"); 
            	   
            	   copy_sql.append(" insert into jlbb_jly   ");
            	   copy_sql.append(" (jlbb_jly_uid,jlbb_uid,xuhao,gangwei_uid,must_y,jl_person_uid, ");
            	   copy_sql.append(" jl_name,zhengshu_name,zhengshu_code,zhuanye,age,zhicheng_name, ");
            	   copy_sql.append(" shenfenzheng,updated_date,status,lock_end_date,changed_date, ");
            	   copy_sql.append(" changed_reason,youxiao_y,old_uid) ");
            	   copy_sql.append(" select '"+new_id+"', jlbb_uid,xuhao,gangwei_uid,must_y, ");
            	   copy_sql.append(" '"+vo.getNew_person_uid()+"','"+vo.getNew_person_name()+"','"+vo.getNew_zhengshu_name()+"','"+vo.getNew_zhengshu_code()+"', ");
            	   copy_sql.append(" '"+vo.getNew_zhuanye()+"','"+vo.getNew_age()+"','"+vo.getNew_zhicheng_name()+"','"+vo.getNew_shenfenzheng()+"',sysdate, ");
            	   copy_sql.append(" status,lock_end_date,sysdate,'"+vo.getReason()+"', ");
            	   copy_sql.append(" 'Y',jlbb_jly_uid from jlbb_jly  ");
            	   copy_sql.append(" where jlbb_jly_uid = '"+olduid+"'");
            	   
            	   //总监变更后锁定半年,安全监理变更后锁定3个月,其他人员自动解锁
            	   if("10".equals(gwuid)&&!"15".equals(gwuid)){
            		   update_sql.append(" update jlbb_jly t set t.youxiao_y = 'N', t.lock_end_date = add_months(t.lock_end_date,6) ");
            		   update_sql.append(" where t.jlbb_jly_uid = '"+olduid+"' "); 
            	   }else if("15".equals(gwuid)){
            		   update_sql.append(" update jlbb_jly t set t.youxiao_y = 'N', t.lock_end_date = add_months(t.lock_end_date,3)");
            		   update_sql.append(" where t.jlbb_jly_uid = '"+olduid+"' "); 
            	   }else{
            		   update_sql.append(" update jlbb_jly t set t.youxiao_y = 'N',t.status = '0' ");
            		   update_sql.append(" where t.jlbb_jly_uid = '"+olduid+"' "); 
            	   }
            	   
            	   buid = DBUtil.getSequenceValue("DT_GC_SGRY_UID"); 
            	   
            	   insert_sql.append("insert into dt_gc_jlry  (dt_gc_jlry_uid, gongcheng_uid, jlbb_jly_uid, jl_person_uid, kq_y, kq_begin_date)");
            	   insert_sql.append("values ('"+buid+"','"+vo.getGongcheng_uid()+"','"+new_id+"','"+vo.getNew_person_uid()+"','Y',sysdate+1)");
            	   
            	}
            	//System.out.println(copy_sql);
            	System.out.println(insert_sql);
    			DBUtil.exec(copy_sql.toString());
    			DBUtil.exec(update_sql.toString());
    			DBUtil.exec(insert_sql.toString());
            }

        } catch (Exception e) {

            SystemException.handleMessageException("人员变更修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

	/**
	 * 审核退回
	 */
	public String updateTh(String msg) {
		User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        DtRyBiangengVO vo = new DtRyBiangengVO();

        try {
            JSONArray list = vo.doInitJson(msg);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 修改
            String  spstatus = vo.getShenpi_status();
            
            if("1".equals(spstatus)){
            	vo.setShenhe_user1(user.getUserSN());
            	vo.setShenhe_date1(new Date());
            }else if("2".equals(spstatus)){
            	vo.setShenhe_user2(user.getUserSN());
            	vo.setShenhe_date2(new Date());
            	vo.setShenpi_status("2");
            }else if("3".equals(spstatus)){
            	vo.setFinish_user(user.getUserSN());
            	vo.setFinish_date(new Date());
            	vo.setShenpi_status("3");
            }
            
            vo.setStatus("-1");
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getUserSN());
            
            update(vo);
            resultVO = vo.getRowJson();


        } catch (Exception e) {
            e.printStackTrace();
            SystemException.handleMessageException("人员变更修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}
    
    // 在此可加入其它方法
    
    //审批  审批状态：1－一级待审（安质监待审）；2－二级待审（招标办待审）；3－三级待审（主管部门待审）
    
    //复制数据、更新数据
    

}
