/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.resources.JsComClkDao.java
 * 创建日期： 2014-06-14 下午 05:05:25
 * 功能：   企业材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-14 下午 05:05:25  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.resources.dao.impl;

import java.sql.Connection;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.resources.dao.JsComClkDao;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.copj.modules.utils.exception.DaoException;
import com.visural.common.StringUtil;


/**
 * <p> JsComClkDao.java </p>
 * <p> 功能：企业材料库 </p>
 *
 * <p><a href="JsComClkDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-14
 * 
 */

@Component
public class JsComClkDaoImpl  extends BsBaseDaoTJdbc implements JsComClkDao {

	 //查询注册公司list  add by longchuxiong 20140617
	public List<?> getAllCompanyList(String json) {
		List<?> tempList=null;
//		String sql="select c.js_company_uid as comid,c.user_name as comaccount,c.company_name as comname," +
//				" c.jiguo_daima as jgdm " +
//				" from js_company c where c.enabled='1'";		

		String sql="select distinct(cc.js_company_uid) as comid from js_com_clk cc where cc.enabled='1'";
		Connection conn = null;
		 try {
	            conn = DBUtil.getConnection();
	            tempList=  DBUtil.queryReturnList(conn, sql);
	      
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }	
		return tempList;
	}

	
	public List<?> getAllCompanyClkList(JSONObject object) {
		List<?> tempList=null;
		String sql="select c.js_com_cjk_uid as id,c.p_com_cjk_uid as pid,c.js_company_uid as comid,  " +
				 " c.node_name as name,c.node_type as type,c.lixiang_uid as lxid,c.projects_uid as proid, " +
				 " c.clk_uid as clkid  " +
				 " from js_com_clk c where 1=1" ;
		String companyid= (String)object.get("JS_COMPANY_UID");
		
		if(StringUtil.isNotBlankStr(companyid)){
			sql+=" and c.js_company_uid='"+companyid+"' ";
		}
		String p_com_cjk_uid=(String)object.get("P_COM_CJK_UID");
		if(StringUtil.isNotBlankStr(p_com_cjk_uid)){
			sql+=" and c.p_com_cjk_uid='"+p_com_cjk_uid+"' ";
		}
		//节点类型：QY－企业；LXG－立项根节点；LX－立项；XMG－项目根节点；XM－项目；CL－材料
		String nodetype=(String)object.get("NODE_TYPE");
		if(StringUtil.isNotBlankStr(nodetype)){
			sql+=" and c.node_type='"+nodetype+"' ";
			
		}
		
		String lixiangid=(String)object.get("LIXIANG_UID");
		if(StringUtil.isNotBlankStr(lixiangid)){
			sql+=" and c.lixiang_uid='"+lixiangid+"' ";
		}
		
		String projectid=(String)object.get("PROJECTS_UID");
		if(StringUtil.isNotBlankStr(projectid)){
			sql+=" and c.projects_uid='"+projectid+"' ";
		}
		
		String clkid=(String)object.get("CLK_UID");
		if(StringUtil.isNotBlankStr(clkid)){
			sql+=" and c.clk_uid='"+clkid+"' ";
		}
		
		String ywlzid=(String)object.get("YWLZ_UID");
		if(StringUtil.isNotBlankStr(ywlzid)){
			sql+=" and c.ywlz_uid='"+ywlzid+"' ";
		}

		Connection conn = null;
		 try {
	            conn = DBUtil.getConnection();
	            tempList=  DBUtil.queryReturnList(conn, sql);
	      
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }	
		return tempList;
	}
	
}
