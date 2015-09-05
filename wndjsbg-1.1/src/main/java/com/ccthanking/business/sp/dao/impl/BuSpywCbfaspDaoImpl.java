/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspDao.java
 * 创建日期： 2014-05-28 上午 10:34:34
 * 功能：   资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 上午 10:34:34  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Map;

import jsx3.gui.Alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywCbfaspDao;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;
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
 * <p> BuSpywCbfaspDao.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywCbfaspDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

@Component
public class BuSpywCbfaspDaoImpl  extends BsBaseDaoTJdbc implements BuSpywCbfaspDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspDaoImpl.class);

    public String queryCondition(String json, BuSpywCbfaspVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_CBFASP t";
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
            bs.setFieldDic("NEIRONG", "SGNR");
            bs.setFieldDic("CB_XINGZHI", "CB_XINGZHI");
            bs.setFieldDic("SFSZGC", "SF");
            bs.setFieldDic("BID_TYPE", "BID_TYPE");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
   
    // 在此可加入其它方法
	public String findByZjId(String ywlz,String ty) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select CBFASP_UID from BU_SPYW_CBFASP where YWLZ_UID="+ywlz+" and LX='"+ty+"'";
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
	         
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
	
	 // 在此可加入其它方法
	public String findByPerson(String lx,String uid, String bb_code) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
		String sql="";
		if("sg".equals(lx)){
			sql="select a.SG_NAME,a.ZHENGSHU_CODE from SGBB_RY a where a.GANGWEI_UID='"+uid+"' and a.SGBB_UID=( select b.SGBB_UID from SGBB b where b.bb_code='"+bb_code+"')";
		}else if("jl".equals(lx)){
			sql="select a.JL_NAME,a.ZHENGSHU_CODE from JLBB_JLY a where a.GANGWEI_UID='"+uid+"' and a.JLBB_UID=( select b.JLBB_UID from JLBB b where b.bb_code='"+bb_code+"')";
   	 	}else{}
         String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0]+","+res[0][1];
			
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
	public String findByCompany(String lx,String bb_code) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
		String sql="";
		if("sg".equals(lx)){
			sql="select a.COMPANY_NAME,a.ZHENGSHU_CODE from SG_ENTERPRISE_LIBRARY a where a.SG_COMPANY_UID=( select b.SG_COMPANY_UID from SGBB b where b.bb_code='"+bb_code+"')";
		}else if("jl".equals(lx)){
        	sql="select a.COMPANY_NAME,a.ZHENGSHU_CODE from ENTERPRISE_LIBRARY a where a.JL_COMPANY_UID=( select b.JL_COMPANY_UID from JLBB b where b.bb_code='"+bb_code+"')";
   	 	}else{}
         
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0]+","+res[0][1];
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
	public String getNum(int uid) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select count(*)+1 from bu_sp_ywlz where spyw_uid="+uid+" and status=1";
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
	public String getCount(int uid,String lx) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="";
        if("sg".equals(lx)){
        	sql="select count(*)+1 from bu_sp_ywlz where spyw_uid="+uid+" and status=1 and ywlz_uid in (select ywlz_uid from bu_spyw_cbfasp where lx='"+lx+"')";
        }else if("jl".equals(lx)){
        	sql="select count(*)+1 from bu_sp_ywlz where spyw_uid="+uid+" and status=1 and ywlz_uid in (select ywlz_uid from bu_spyw_cbfasp where lx='"+lx+"')";
   	 	}else{}
        String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
	
	/**
	 * 通过业务流转UID来查数据ID
	 * */
	public String getIdByYwlzuid(String ywlzuid,String lx) {
		Connection conn = DBUtil.getConnection();
        String resultVO = "";
       
        try {
            String sql="select CBFASP_UID from  BU_SPYW_CBFASP where YWLZ_UID='"+ywlzuid+"' and lx = '"+lx+"' "; 
        	
            String[][] tem=DBUtil.querySql(conn, sql);
            if(tem!=null){
            resultVO=tem[0][0];
            }
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("BuSpywCbfasp登记表查询失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return resultVO;
	}
}
