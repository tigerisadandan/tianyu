/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgZiZhiDengjiService.java
 * 创建日期： 2014-04-10 上午 11:01:40
 * 功能：    接口：资质表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-10 上午 11:01:40  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sgenter.dao.SgEnterpriseZenZizhiDao;
import com.ccthanking.business.sgenter.vo.SgZizhiDengjiVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.util.RequestUtil;



/**
 * <p> SgZiZhiDengjiService.java </p>
 * <p> 功能：资质表 </p>
 *
 * <p><a href="SgZiZhiDengjiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-10
 * 
 */

@Component
public class SgEnterpriseZenZizhiDaoImpl extends BsBaseDaoTJdbc implements SgEnterpriseZenZizhiDao {

	private static Logger logger = LoggerFactory.getLogger(SgEnterpriseZenZizhiDaoImpl.class);
	
	private String ywlx = YwlxManager.SG_ENTERPRISE_ZEN_ZIZHI;
	// 业务类型

    // @Override
    public String queryCondition(String json) throws Exception {
    
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

            conn.setAutoCommit(false);
            String sql = "select a.*,b.zizhi_name,b.zizhi_type,b.zhuanye_name,c.sg_zizhi_dengji_uid,c.dengji_nums,c.dengji_name from SG_ENTERPRISE_ZEN_ZIZHI a "
    			+" left join sg_zizhi b on a.sg_zizhi_uid = b.sg_zizhi_uid left join sg_zizhi_dengji c on a.zizhi_dengji = c.sg_zizhi_dengji_uid ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("资质表查询失败!");
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    // @Override
    public String insert(String json) throws Exception {

        Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgZizhiDengjiVO vo = new SgZizhiDengjiVO();
        User user = ActionContext.getCurrentUserInThread(); 
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
//            vo.setId(new RandomGUID().toString()); // 主键
//            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//
//            EventVO eventVO = EventManager.createEvent(conn, vo.getYwlx(), user);// 生成事件
//            vo.setSjbh(eventVO.getSjbh());

            // 插入
            BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), "700001", Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "资质表新增成功", user, "", "");

            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表新增失败!");
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(),ywlx , Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE,user.getName() + "资质表新增失败", user, "", "");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    // @Override
    public String update(String json) throws Exception {

        Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgZizhiDengjiVO vo = new SgZizhiDengjiVO();
        User user = ActionContext.getCurrentUserInThread();
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(conn, vo.getYwlx(), user);// 生成事件
//            vo.setSjbh(eventVO.getSjbh());

            // 插入
            BaseDAO.update(conn, vo);
            resultVO = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "资质表修改成功", user, "", "");

        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表修改失败!");
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "资质表修改失败", user, "", "");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    // @Override
    public String delete(String json) throws Exception {

        Connection conn = DBUtil.getConnection();
        String resultVo = null;
        SgZizhiDengjiVO vo = new SgZizhiDengjiVO();
        User user = ActionContext.getCurrentUserInThread();
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);

            vo.setValueFromJson(jsonObj);
            BaseDAO.delete(conn, vo);

            resultVo = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,user.getName() + "资质表删除成功", user, "", "");
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表删除失败!");
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "资质表删除失败", user, "", "");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVo;

    }

	public String queryListZeng(String uid) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select a.*,b.zizhi_name,b.zizhi_type,b.zhuanye_name,c.sg_zizhi_dengji_uid,c.dengji_nums,c.dengji_name,sgk_package.company_zizhi_change(a.SG_ENTERPRISE_ZEN_ZIZHI_uid) CZ  from SG_ENTERPRISE_ZEN_ZIZHI a "
			+" left join sg_zizhi b on a.sg_zizhi_uid = b.sg_zizhi_uid left join sg_zizhi_dengji c on a.zizhi_dengji = c.sg_zizhi_dengji_uid where a.SG_ENTERPRISE_LIBRARY_UID = "+uid+" order by a.created_date";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_ENTERPRISE_ZEN_ZIZHI_UID",rsMap.get("SG_ENTERPRISE_ZEN_ZIZHI_UID"));
				jsonObj.put("SG_ZIZHI_UID", rsMap.get("SG_ZIZHI_UID"));
			    jsonObj.put("ZIZHI_CODE", rsMap.get("ZIZHI_CODE"));
			    jsonObj.put("ZENG_VALID_DATE", rsMap.get("VALID_DATE"));
			    jsonObj.put("ZIZHI_NAME", rsMap.get("ZIZHI_NAME"));
			    jsonObj.put("ZIZHI_TYPE", rsMap.get("ZIZHI_TYPE"));
			    jsonObj.put("ZHUANYE_NAME", rsMap.get("ZHUANYE_NAME"));
			    jsonObj.put("SG_ZIZHI_DENGJI_UID", rsMap.get("SG_ZIZHI_DENGJI_UID"));
			    jsonObj.put("DENGJI_NAME", rsMap.get("DENGJI_NAME"));
			    jsonObj.put("CZ", rsMap.get("CZ"));
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	public void updateCopyZizhi(String uid) throws Exception {
		Connection conn = null;
		try {
			User user = ActionContext.getCurrentUserInThread();
			conn = DBUtil.getConnection();
			String copy_zizhi = "insert into sg_enterprise_zen_zizhi (SG_ENTERPRISE_ZEN_ZIZHI_UID, SG_ZIZHI_UID, SG_ENTERPRISE_LIBRARY_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZIZHI_DENGJI, ZIZHI_CODE, VALID_DATE) "
					+" select SG_ENTERPRISE_ZEN_ZIZHI_UID.Nextval, SG_ZIZHI_UID, "+uid+", EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZIZHI_DENGJI, ZIZHI_CODE, VALID_DATE from sg_enterprise_zen_zizhi "
					+" where sg_enterprise_library_uid = ( select sg_enterprise_library_uid from sg_enterprise_library where status = 1 and sg_company_uid = "+user.getIdCard()+" )";
			DBUtil.exec(copy_zizhi);
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
		} 
	}

	public String querySfcz(Map map) throws Exception {
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			String sql = "select t.sg_zizhi_uid,t.zizhi_dengji,t.zizhi_code,t.valid_date from sg_enterprise_zen_zizhi t where t.sg_zizhi_uid = "+map.get("zizhi")+" and t.sg_enterprise_library_uid =" + map.get("uid");
			String[][] res = DBUtil.query(conn, sql);
			if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("SG_ZIZHI_UID", res[i][0]);
					obj.put("ZIZHI_DENGJI", res[i][1]);
					obj.put("ZIZHI_CODE", res[i][2]);
					obj.put("VALID_DATE", res[i][3]);
					arr.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return arr.toString();
	}

}
