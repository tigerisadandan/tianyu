/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgZiZhiService.java
 * 创建日期： 2014-04-10 上午 10:27:57
 * 功能：    接口：资质表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-10 上午 10:27:57  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sgenter.dao.SgZizhiDao;
import com.ccthanking.business.sgenter.vo.SgZizhiVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;



/**
 * <p> SgZiZhiService.java </p>
 * <p> 功能：资质表 </p>
 *
 * <p><a href="SgZiZhiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-10
 * 
 */

@Component
public class SgZizhiDaoImpl extends BsBaseDaoTJdbc implements SgZizhiDao {

	private static Logger logger = LoggerFactory.getLogger(SgZizhiDaoImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.SG_ZIZHI;

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
            String sql = "SELECT * FROM " + "SG_ZIZHI t";
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
        SgZizhiVO vo = new SgZizhiVO();

        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
            BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            conn.commit();
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    // @Override
    public String update(String json) throws Exception {

        Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgZizhiVO vo = new SgZizhiVO();

        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
            BaseDAO.update(conn, vo);
            resultVO = vo.getRowJson();
            conn.commit();

        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表修改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    // @Override
    public String delete(String json) throws Exception {

        Connection conn = DBUtil.getConnection();
        String resultVo = null;
        SgZizhiVO vo = new SgZizhiVO();
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);

            vo.setValueFromJson(jsonObj);
            BaseDAO.delete(conn, vo);

            resultVo = vo.getRowJson();
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("资质表删除失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVo;

    }

	public String queryAllZizhi() throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from sg_zizhi order by SERIAL_NO";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_ZIZHI_UID",rsMap.get("SG_ZIZHI_UID"));
				jsonObj.put("ZIZHI_NAME", rsMap.get("ZIZHI_NAME"));
			    
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}
    
	
public String queryAllZhuanye(String json) throws Exception {
		
		Connection conn = DBUtil.getConnection();
		String sql = "select * from sg_zizhi order by SERIAL_NO";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_ZIZHI_UID",rsMap.get("SG_ZIZHI_UID"));
				jsonObj.put("ZHUANYE_NAME", rsMap.get("ZHUANYE_NAME"));
			    
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
