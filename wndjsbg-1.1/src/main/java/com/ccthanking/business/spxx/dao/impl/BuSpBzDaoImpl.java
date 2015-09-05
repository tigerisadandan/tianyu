/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpBzDao.java
 * 创建日期： 2014-06-13 下午 04:41:17
 * 功能：   业务信息步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 04:41:17  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpBzDao;
import com.ccthanking.business.spxx.vo.BuSpBzVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.visural.common.StringUtil;


/**
 * <p> BuSpBzDao.java </p>
 * <p> 功能：业务信息步骤 </p>
 *
 * <p><a href="BuSpBzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Component
public class BuSpBzDaoImpl  extends BsBaseDaoTJdbc implements BuSpBzDao {

    public String queryCondition(String json, BuSpBzVO vo, Map map){
    
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

            String sql = "SELECT * FROM (select bz.*,(select yw.spywmc from bu_sp_ywxx yw where yw.spyw_uid=bz.spyw_uid) as spywmc from BU_SP_BZ bz) t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            bs.setFieldDic("TSLX", "TSLX");
            bs.setFieldDic("BZLX", "BZLX");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    
    public String getPxh(String spyw_uid){
        
        Connection conn = DBUtil.getConnection();
        String domresult = "1";
        try {

          
            String sql = "SELECT max(BZXH) FROM " + "BU_SP_BZ t where t.spyw_uid = "+spyw_uid;
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

           String[][] res = DBUtil.query(conn, sql);
           if(res!=null){
        	   if(StringUtils.isNotBlank(res[0][0])){
        	   domresult = (Integer.parseInt(res[0][0])+1)+"";
        	   }else{
        		   domresult = "1";
        	   }
           }
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
    }
    // 在此可加入其它方法

	/**
	 * 查询审批步骤和审批参与者信息,按审批步骤序号降序排列
	 * spywid  审批业务ID
	 * bzlx    步骤类型
	 * add by long 20140619
	 * */
	public List<?> getSpBzList(String spywid, String bzlx) {
		User user = ActionContext.getCurrentUserInThread();    
        Connection conn = DBUtil.getConnection();
        List<?> tempList = null;
        try {
//            String sql = "select * from BU_SP_BZ bz where 1=1 ";
 /**           String sql=" select bz.spbz_uid as spbzuid,bz.spyw_uid as spywuid,bz.bzmc as bzmc,"+
						" bz.bzxh as bzxh,bz.bzlx as bzlx,bz.clts as clts,bz.tslx as tslx,bz.description as description,"+
						" cyz.bzcyz_uid as bzcyzuid,cyz.cyzuid as cyzuid "+
						" from BU_SP_BZ bz "+
						" left join BU_SP_BZ_CYZ cyz on bz.spbz_uid=cyz.spbz_uid where 1=1";
 **/           
			 String sql=" select bz.spbz_uid as spbzuid,bz.spyw_uid as spywuid,bz.bzmc as bzmc,"+
				" bz.bzxh as bzxh,bz.bzlx as bzlx,bz.clts as clts,bz.tslx as tslx,bz.description as description "+
				" from BU_SP_BZ bz  where 1=1";
 
            if(StringUtil.isNotBlankStr(spywid)){
            	sql+=" and bz.spyw_uid='"+spywid+"' ";
            }
            
            if(StringUtil.isNotBlankStr(bzlx)){
            	sql+=" and bz.bzlx='"+bzlx+"' ";
            }
            
            sql+=" order by bz.bzxh asc ";
            
            tempList= DBUtil.queryReturnList(conn, sql);

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return tempList;
	}
	

}
