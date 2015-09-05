/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.BuSpywRqgyxkzSqDao.java
 * 创建日期： 2014-11-11 下午 04:54:18
 * 功能：   燃气供应许可证
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-11 下午 04:54:18  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywRqgyxkzSqDao;
import com.ccthanking.business.spyw.vo.BuSpywPssgtscsqVO;
import com.ccthanking.business.spyw.vo.BuSpywRqgyxkzSqVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywRqgyxkzSqDao.java </p>
 * <p> 功能：燃气供应许可证 </p>
 *
 * <p><a href="BuSpywRqgyxkzSqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-11
 * 
 */

@Component
public class BuSpywRqgyxkzSqDaoImpl  extends BsBaseDaoTJdbc implements BuSpywRqgyxkzSqDao {

	private static Logger logger = LoggerFactory.getLogger(BuSpywRqgyxkzSqDaoImpl.class);
	private String SQL_QUERY="select rqgyxkz_sq_uid, ywlz_uid, dwmc, gyzdmc, dz, jlsj, rqzl, " +
			"yb, fzr, lxdh, aqy, yyzzbh, sbgqgm, aqglzdqk, sdbmscyj, xqbmscyj, sdscrq, xqscrq, " +
			"hzgqgm, xkzbh, event_uid, enabled, describe, created_uid, created_name, created_date, " +
			"update_uid, update_name, update_date, serial_no from bu_spyw_rqgyxkz_sq";
	
	
	public String queryCondition(String json) throws Exception{
	    
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

            conn.setAutoCommit(false);

            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY, page);//连接数据库，进行查询，结果集给bs
     
            domresult = bs.getJson();
            
        } catch (Exception e) {
        	DBUtil.rollbackConnetion(conn);
            logger.error("燃气供应许可证申请信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String insert(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywRqgyxkzSqVO vo = new BuSpywRqgyxkzSqVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        vo.setRqgyxkz_sq_uid(DBUtil.getSequenceValue("RQGYXKZ_SQ_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getAccount());
        
        vo.setSerial_no(vo.getRqgyxkz_sq_uid());
        BaseDAO.insert(conn, vo);
        
      
        resultVO = vo.getRowJson();
        
        conn.commit();
        }catch(Exception e){
        	
        	DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("燃气供应许可证申请新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
		
	}

	public String update(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywRqgyxkzSqVO vo = new BuSpywRqgyxkzSqVO();
        User user = ActionContext.getCurrentUserInThread();


        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getAccount());
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("燃气供应许可证申请信息表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}

	public String findByZjId(String ywlz) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select RQGYXKZ_SQ_UID from BU_SPYW_RQGYXKZ_SQ where YWLZ_UID="+ywlz;
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
	         
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
    
    // 在此可加入其它方法


}
