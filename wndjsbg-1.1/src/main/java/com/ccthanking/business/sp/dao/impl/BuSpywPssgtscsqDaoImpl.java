/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywPssgtscsqDao.java
 * 创建日期： 2014-06-04 上午 10:13:09
 * 功能：   排水方案施工图审查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-04 上午 10:13:09  蒋根亮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywPssgtscsqDao;
import com.ccthanking.business.spyw.vo.BuSpywPsfayssqVO;
import com.ccthanking.business.spyw.vo.BuSpywPssgtscsqVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywPssgtscsqDao.java </p>
 * <p> 功能：排水方案施工图审查 </p>
 *
 * <p><a href="BuSpywPssgtscsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-04
 * 
 */

@Component
public class BuSpywPssgtscsqDaoImpl  extends BsBaseDaoTJdbc implements BuSpywPssgtscsqDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywPssgtscsqDaoImpl.class);
	private String SQL_QUERY="select pssgtscsq_uid, ywlz_uid, dwmc, dwdz, fddbr, fddbr_lxdh, sqblr," +
			" sqblr_lxdh, xmmc, jsdd, ydmj, hyfl, wsxz_shws, wszx_gyws, shws_l, gyws_l, ysl," +
			" zywrw, sfclml from bu_spyw_pssgtscsq";
	
	
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
            logger.error("排水施工图审查申请信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String insert(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywPssgtscsqVO vo = new BuSpywPssgtscsqVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        vo.setPssgtscsq_uid(DBUtil.getSequenceValue("PSSGTSCSQ_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
        
        vo.setSerial_no(vo.getPssgtscsq_uid());
        BaseDAO.insert(conn, vo);
        
      
        resultVO = vo.getRowJson();
        
        conn.commit();
        }catch(Exception e){
        	
        	DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("排水施工图审查申请表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
		
	}

	public String update(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywPssgtscsqVO vo = new BuSpywPssgtscsqVO();
        User user = ActionContext.getCurrentUserInThread();


        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("排水施工图审查申请信息表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}

	
	/**
	 * 通过业务流转UID来查数据ID
	 * */
	public String getIdByYwlzuid(String ywlzuid) {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
       
        try {
            String sql="select PSSGTSCSQ_UID from  BU_SPYW_PSSGTSCSQ where YWLZ_UID='"+ywlzuid+"' "; 
        	
            String[][] tem=DBUtil.querySql(conn, sql);
            resultVO=tem[0][0];
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("排水施工图审查申请信息表查询ID失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return resultVO;
	}
    
    // 在此可加入其它方法

}
