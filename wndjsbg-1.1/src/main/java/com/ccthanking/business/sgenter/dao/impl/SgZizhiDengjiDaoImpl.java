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

import com.ccthanking.business.sgenter.dao.SgZizhiDengjiDao;
import com.ccthanking.business.sgenter.vo.SgZizhiDengjiVO;
import com.ccthanking.common.BusinessUtil;
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
public class SgZizhiDengjiDaoImpl extends BsBaseDaoTJdbc implements SgZizhiDengjiDao {

	private static Logger logger = LoggerFactory.getLogger(SgZizhiDengjiDaoImpl.class);
	 private String ywlx = YwlxManager.SG_ZIZHI_DENGJI;
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
            condition += BusinessUtil.getSJYXCondition(null);
//            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);
            String sql = "SELECT * FROM " + "SG_ZIZHI_DENGJI t";
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

            // 插入
            BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "资质表新增成功", user, "", "");
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表新增失败!");
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "资质表新增失败", user, "", "");
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

            // 插入
            BaseDAO.update(conn, vo);
            resultVO = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "资质表修改成功", user, "", "");

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
            LogManager.writeUserLog(vo.getSg_zizhi_dengji_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "资质表删除成功", user, "", "");
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

	public String queryZizhiDengji(String zizhiUid) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from sg_zizhi_dengji where SG_ZIZHI_UID = '"+zizhiUid+"' order by DENGJI_NUMS";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_ZIZHI_DENGJI_UID",rsMap.get("SG_ZIZHI_DENGJI_UID"));
				jsonObj.put("DENGJI_NAME", rsMap.get("DENGJI_NAME"));
			    
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
