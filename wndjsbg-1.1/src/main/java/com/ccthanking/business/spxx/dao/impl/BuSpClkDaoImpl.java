/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpClkDao.java
 * 创建日期： 2014-06-13 上午 11:34:41
 * 功能：   审批业务材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 上午 11:34:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpClkDao;
import com.ccthanking.business.spxx.vo.BuSpClkVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuSpClkDao.java </p>
 * <p> 功能：审批业务材料库 </p>
 *
 * <p><a href="BuSpClkDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Component
public class BuSpClkDaoImpl  extends BsBaseDaoTJdbc implements BuSpClkDao {

    public String queryCondition(String json, BuSpClkVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUti;
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "BU_SP_CLK t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("CL_LEVEL", "SPYWCLJB");
            bs.setFieldDic("SFYFJ", "SF");
            bs.setFieldDic("ENABLED", "SF");
//            bs.setFieldDic("SPYWCLJB", "CL_LEVEL");
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

	public List<?> getSpClkListByType(Map temmap) {
//    	User user = ActionContext.getCurrentUserInThread();
    	List<?>  tempList=null;
        Connection conn = DBUtil.getConnection();

        try {
        	String type=(String)temmap.get("TYPE");
        	String sql = "";  	
        	if(Constants.YWCLKJB_YW.equals(type)){
        	/** 2014-11-13修改
        		String ywlzuid=(String)temmap.get("YWLZUID");
        		sql=" select clk.clk_uid,clk.clmc,clk.cl_level,clk.cllx,lz.projects_uid,lz.cishu, xx.spywjc,xx.multi_y "+
					" from bu_sp_clk clk "+
					" left join bu_sp_ywcl cl on clk.clk_uid=cl.clk_uid " +
					" left join bu_sp_ywxx xx on xx.spyw_uid=cl.spyw_uid "+
					" left join bu_sp_ywlz lz on lz.spyw_uid=xx.spyw_uid "+
					" where clk.cl_level='"+type+"' and clk.enabled='1' and cl.sfysc='1' and lz.ywlz_uid='"+ywlzuid+"' ";
        		**/
        		sql=" select distinct clk.clk_uid,clk.clmc,clk.cl_level,clk.cllx,cl.clsx "+
				" from bu_sp_clk clk "+
				" left join bu_sp_ywcl cl on clk.clk_uid=cl.clk_uid " +
				" left join bu_sp_ywxx xx on xx.spyw_uid=cl.spyw_uid "+			
				" where clk.cl_level='"+type+"' and clk.enabled='1' and cl.sfysc='1' and xx.spywlx in ('HB','JS') ";
        		//可用的、需要上传的、属于建设和环保类型材料
        		
        	}else if(Constants.YWCLKJB_QY.equals(type)){
        		
        		sql="select clk.clk_uid,clk.clmc,clk.cl_level,clk.cllx  "+
				"	 from bu_sp_clk clk  "+
				"	 where clk.cl_level='"+type+"' and clk.enabled='1'  ";
        		
        	}else if(Constants.YWCLKJB_LX.equals(type)){//立项的所有可用的材料都在企业材料库中新建节点
        		
        		sql="select clk.clk_uid,clk.clmc,clk.cl_level,clk.cllx  "+
				"	 from bu_sp_clk clk  "+
				"	 where clk.cl_level='"+type+"' and clk.enabled='1'  ";
        		
        	}else {//待修正，非重复申请的项目是否会有一次流转实例？？？？？？？？
        		sql=" select distinct clk_uid ,clmc,cl_level from ( "+
					"	select clk.clk_uid,clk.clmc,clk.cl_level,clk.cllx  "+
					"	 from bu_sp_clk clk  "+
					"	 left join bu_sp_ywcl cl on clk.clk_uid=cl.clk_uid  "+
					"	 where clk.cl_level='"+type+"' and clk.enabled='1' and cl.sfysc='1' "+
					"	) ";
        	}
            tempList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********根据类型查询业务材料出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return tempList;
	}

	public String getYwid() {
		String ywid=null;
        Connection conn = DBUtil.getConnection();
        try {
        	String sql = "select CLK_UID.nextval as ywid from dual";  	

        	String[][] temp=DBUtil.querySql(conn, sql);
        	
        	if(temp!=null){
         	   if(StringUtils.isNotBlank(temp[0][0])){
         		  ywid = temp[0][0];
         	   }
            }
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询业务ID出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return ywid;
	}
    
    // 在此可加入其它方法

}
