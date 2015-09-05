/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.ywlz.BuSpYwlzDao.java
 * 创建日期： 2014-06-19 下午 04:51:04
 * 功能：   审批业务流转实例
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:51:04  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.ywlz.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.ywlz.dao.BuSpYwlzDao;
import com.ccthanking.business.ywlz.vo.BuSpYwlzVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.visural.common.StringUtil;


/**
 * <p> BuSpYwlzDao.java </p>
 * <p> 功能：审批业务流转实例 </p>
 *
 * <p><a href="BuSpYwlzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

@Component
public class BuSpYwlzDaoImpl  extends BsBaseDaoTJdbc implements BuSpYwlzDao {

    public String queryCondition(String json, BuSpYwlzVO vo, Map map){
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	String spyw_uid_res=(String)map.get("spyw_uid_res");//是否待审 1是0否
        	String sfdssql=" ";
        	if(StringUtil.isNotBlankStr(spyw_uid_res)){
//        		sfdssql=" and ywlz_uid in (select lzbz.ywlz_uid from bu_sp_ywlz_bz lzbz " +
//        				"where  lzbz.chuli_jieguo='0' and lzbz.chuli_ren='"+user.getAccount()+"')";
        		//过滤出该登录人员所参与的所有业务流转的待审核数据
//        		sfdssql= " and spyw_uid in (select bz.spyw_uid from bu_sp_bz bz where bz.spbz_uid  in(" +
//        			    " select cyz.spbz_uid from bu_sp_bz_cyz cyz where cyz.cyzuid='"+user.getAccount()+"') )";
        		
        		sfdssql="and spyw_uid in (SELECT to_char(y.spyw_uid ) " +
        			" from  bu_sp_ywxx y " +
        			" connect by prior y.spyw_uid=y.p_spyw_uid start with y.p_spyw_uid ='"+spyw_uid_res+"' " +
        			" union " +
        			" select '"+spyw_uid_res+"' from dual)";
        	}
        	
        	
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();          
            condition+=sfdssql;
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUtil.getSJYXCondition(null);
//            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM (select gc.gongcheng_name,sg.company_name as SG_COMPANY_NAME,l.ywlz_uid,l.projects_uid,p.projects_code,p.projects_name,c.js_company_uid,c.company_name, "+
            	 " l.spyw_uid,y.spywmc,y.spywjc,y.spywlx,l.zlc_uid,decode(l.status,  1,'已通过',  -1,'退回', 0,'流转中',40,'未提交') as zstatus,l.status, "+
            	 "		l.cishu,l.begin_date,l.plan_end_date,l.act_end_date," +
            	 " (select count(cl.ywcl_uid) from bu_sp_ywcl cl where cl.spyw_uid=l.spyw_uid and cl.clsx='H' and cl.sfysc='1' ) as hfclcount  "+
            	 "		from bu_sp_ywlz l  " +
            	 "       left join js_company c on c.js_company_uid=l.js_company_uid"+
            	 "		left join bu_sp_ywxx y on y.spyw_uid=l.spyw_uid "+
            	 "		left join projects p on l.projects_uid=p.projects_uid " +
            	 "      left join sg_enterprise_library sg on sg.sg_enterprise_library_uid=l.sg_company_uid " +
            	 "       left join projects_gongcheng gc on gc.gongcheng_uid=l.projects_uid"+
            	 "		 )";
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
            
            bs.setFieldDateFormat("BEGIN_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("PLAN_END_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("ACT_END_DATE", "yyyy-MM-dd HH:mm:ss");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryClType(String json, BuSpYwlzVO vo, Map map){
        
//    	User user = ActionContext.getCurrentUserInThread();
    
    	User user = ActionContext.getCurrentUserInThread();
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
        	 PageManager page = RequestUtil.getPageManager(json);
             String condition = RequestUtil.getConditionList(json).getConditionWhere();
             String orderFilter = RequestUtil.getOrderFilter(json);
             condition += BusinessUtil.getCommonCondition(user, null);
             condition += orderFilter;
             if (page == null)
                 page = new PageManager();
             page.setFilter(condition);

             String sql="select * from bu_sp_ywlz ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryByProjectsid(String json){
        
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        
            String sql="select js.COMPANY_NAME,p.projects_name,xx.spywmc,lz.* " +
            		"from bu_sp_ywlz lz left join bu_sp_ywxx xx on lz.spyw_uid=xx.spyw_uid " +
            		"left join js_company js on lz.js_company_uid=js.js_company_uid " +
            		"left join projects p on lz.projects_uid=p.projects_uid " +
            		"where lz.projects_uid='"+json+"'  order by spywmc,ywlz_uid ";
            BaseResultSet bs = DBUtil.query(conn, sql, null);
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
            
            bs.setFieldDateFormat("BEGIN_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("PLAN_END_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("ACT_END_DATE", "yyyy-MM-dd HH:mm:ss");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }


	public List<?> getSpCount(String spywuid) {
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
//        	String sql="select "+
//        	"(select count(lz.ywlz_uid) from bu_sp_ywlz lz where lz.spyw_uid='"+spywuid+"' and lz.status ='0') as dsz, "+
//        	"(select count(lz.ywlz_uid) from bu_sp_ywlz lz where lz.spyw_uid='"+spywuid+"' and lz.status ='1') as ytg, "+
//        	"(select count(lz.ywlz_uid) from bu_sp_ywlz lz where lz.spyw_uid='"+spywuid+"' and lz.status ='-1') as yth "+
//        	"from dual";
        	
        	String sql="select * from (select a.root as spyw_uid, nvl(sum(b.num), 0) dsz, nvl(sum(c.num), 0) ytg, nvl(sum(d.num), 0) yth "+
						"  from (select id, fid, connect_by_root(id) root "+
						"         from (select y.spyw_uid as id, y.p_spyw_uid as fid "+
						"                from bu_sp_ywxx y "+
						"               start with y.p_spyw_uid is null "+
						"              connect by prior y.spyw_uid = y.p_spyw_uid) "+
						"      connect by prior id = fid) a "+
						" left join (select z.spyw_uid, count(z.ywlz_uid) num "+
						"             from bu_sp_ywlz z "+
						"            where z.status = '0' "+
						"            group by z.spyw_uid) b "+
						"  on a.id = b.spyw_uid "+
						" left join  (select z.spyw_uid, count(z.ywlz_uid) num "+
						"             from bu_sp_ywlz z "+
						"            where z.status = '1' "+
						"            group by z.spyw_uid) c "+
						"  on a.id = c.spyw_uid  "+
						"left join  (select z.spyw_uid, count(z.ywlz_uid) num "+
						"              from bu_sp_ywlz z "+
						"             where z.status = '-1' "+
						"            group by z.spyw_uid) d "+
						"  on a.id = d.spyw_uid     "+
						" group by root "+
						" order by root) where spyw_uid ="+spywuid;
        	
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询审批统计出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
    
	/**
	 * 查询核发材料数据
	 * 
	 * */
	public List<?> getHfclList(Map map){
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
        	String sql="select cl.ywcl_uid,cl.spyw_uid, cl.clk_uid,cl.clsx,cl.sl,cl.sfysc,cl.url,cl.enabled, "+
					"clk.clmc,clk.cl_level,clk.sfyfj,ff.fileid,ff.filename,ff.url,ff.ywlx,ff.fjlx, "+
					"ff.ywid,ff.glid1,ff.glid2,ff.fjlb "+
					"from bu_sp_ywcl cl "+
					"left join bu_sp_clk clk on clk.clk_uid=cl.clk_uid "+
					"left join fs_fileupload ff on clk.clk_uid=ff.ywid where ff.fjlb in ('"+Constants.FS_FILEUPLOAD_FJLB_YWCL+"'," +
					"'"+Constants.FS_FILEUPLOAD_FJLB_SPYWCL+"') ";
        	String spywuid=(String) map.get("SPYWUID");
        	
        	if(StringUtil.isNotBlankStr(spywuid)){
        		sql+=" and cl.spyw_uid='"+spywuid+"' ";
        	}
        	
        	String clsx=(String)map.get("CLSX");
        	if(StringUtil.isNotBlankStr(clsx)){
        		sql+=" and cl.clsx='"+clsx+"' ";
        	}
        	
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询核发材料数据出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
    // 在此可加入其它方法

public String queryYwcl(Map map) throws Exception {
		
    	User user = ActionContext.getCurrentUserInThread();
    	JSONArray arr = new JSONArray();
    	JSONObject obj = null;
        Connection conn = DBUtil.getConnection();
        try {
        	String sql = "select t.YWCL_UID,t.CLK_UID,t.SFYSC,t.URL, k.CLMC, k.SFYFJ, k.AT_FILEUPLOAD_UID, k.CL_LEVEL, g.describe,t.CLNR, "
	            	+" fs.FILEID, fs.FILENAME, fs.URL FILEPATH, fs.FJLX, fs.FILESIZE, fs.GLID1, fs.GLID2, fs.FJLB, '"+Constants.FS_FILEUPLOAD_FJLB_YWCL+"' uploadLb,g.SPYWMC,g.DESCRIBE ,t.clsx,t.SOURCE_LCUID"
	            	+" from bu_sp_ywxx g left join bu_sp_ywcl t on g.spyw_uid = t.SPYW_UID"
	            	+" left join bu_sp_clk k on t.CLK_UID = k.CLK_UID"
	            	+" left join fs_fileupload fs on fs.ywid = TO_CHAR(k.CLK_UID)  AND fs.FJLB= '1607'"
	            	+" where t.spyw_uid = "+(String)map.get("spyw_uid") 
	             	+" and t.CLSX = 'S'"
	            	+" ORDER BY t.SERIAL_NO";
			
            
            String[][] res = DBUtil.query(conn, sql);
            if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("YWCL_UID", res[i][0]);
					obj.put("CLK_UID", res[i][1]);
					obj.put("SFYSC", res[i][2]);
					obj.put("URL", res[i][3]);
					obj.put("CLMC", res[i][4]);
					obj.put("SFYFJ", res[i][5]);
					if (StringUtils.isNotBlank(res[i][5])&&("".equals(res[i][3])||"#".equals(res[i][3]))) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEUPLOADROOT");
						String fileRoot = syspara.PARAVALUE1;
						String url = "/UploadServlet?getfile="+res[i][11]+"&fileDir="+res[i][12].replace(res[i][11], "");
						obj.put("LOADPATH", url);
						obj.put("LOADFILENAME", res[i][11]);
						obj.put("FILEID", res[i][10]);
						
					}
					obj.put("UPLOAD_FJLB", res[i][18]);
					obj.put("CL_LEVEL", res[i][7]);
					obj.put("DESC", res[i][8]);
					obj.put("CLNR", res[i][9]);
					obj.put("YWMC", res[i][19]);
					obj.put("BZXX", res[i][20]);
					obj.put("CLSX", res[i][21]);
					obj.put("SOURCE_LCUID", res[i][22]);
					arr.add(obj);
				}
			}
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********业务所需材料查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return arr.toString();
	}
	
	public String queryYwclFromLz(Map map) throws Exception {
		
    	User user = ActionContext.getCurrentUserInThread();
    	JSONArray arr = new JSONArray();
    	JSONObject obj = null;
        Connection conn = DBUtil.getConnection();
        try {
        	String sql =  "select t.YWCL_UID,t.CLK_UID,t.SFYSC,t.URL, k.CLMC, k.SFYFJ, k.AT_FILEUPLOAD_UID, k.CL_LEVEL, g.describe,t.CLNR,"
	            	+" fs.FILEID, fs.FILENAME, fs.URL FILEPATH, fs.FJLX, fs.FILESIZE, fs.GLID1, fs.GLID2, fs.FJLB, '"+Constants.FS_FILEUPLOAD_FJLB_YWCL+"' uploadLb, "
	            	+" lz.ywlz_uid,lz.status,lz.cishu,lz.begin_date,lz.plan_end_date,lz.act_end_date,lz.zlc_uid,jc.JS_COM_CJK_UID,g.SPYWMC,g.DESCRIBE ,t.clsx,t.SOURCE_LCUID"
	            	+" from bu_sp_ywlz lz " 
	            	+" left join bu_sp_ywxx g on g.spyw_uid = lz.spyw_uid "
	            	+" left join bu_sp_ywcl t on g.spyw_uid = t.SPYW_UID"
	            	+" left join bu_sp_clk k on t.CLK_UID = k.CLK_UID"
	            	+" left join js_com_clk jc on jc.YWLZ_UID = lz.YWLZ_UID and jc.CLK_UID = t.CLK_UID "
	            	+" left join fs_fileupload fs on fs.ywid = TO_CHAR(k.CLK_UID)  AND fs.FJLB= '1607'"
	            	+" where lz.ywlz_uid = "+(String)map.get("ywlz_uid") 
	            	+" and t.CLSX = 'S'"
	            	+" ORDER BY t.SERIAL_NO";
			
            
            String[][] res = DBUtil.query(conn, sql);
            if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					obj = new JSONObject();
					obj.put("YWCL_UID", res[i][0]);
					obj.put("CLK_UID", res[i][1]);
					obj.put("SFYSC", res[i][2]);
					obj.put("URL", res[i][3]);
					obj.put("CLMC", res[i][4]);
					obj.put("SFYFJ", res[i][5]);
					if (StringUtils.isNotBlank(res[i][5])&&("".equals(res[i][3])||"#".equals(res[i][3]))) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEUPLOADROOT");
						String fileRoot = syspara.PARAVALUE1;
						String url = "/UploadServlet?getfile="+res[i][11]+"&fileDir="+res[i][12].replace(fileRoot, "");
						obj.put("LOADPATH", url);
						obj.put("LOADFILENAME", res[i][11]);
						obj.put("FILEID", res[i][10]);
					}
					obj.put("UPLOAD_FJLB", res[i][18]);
					obj.put("CL_LEVEL", res[i][7]);
					obj.put("DESC", res[i][8]);
					obj.put("CLNR", res[i][9]);
					obj.put("YWLZ_UID", res[i][19]);
					obj.put("STATUS", res[i][20]);
					obj.put("CISHU", res[i][21]);
					obj.put("BEGIN_DATE", res[i][22]);
					obj.put("PLAN_END_DATE", res[i][23]);
					obj.put("ACT_END_DATE", res[i][24]);
					obj.put("ZLC_UID", res[i][25]);
					obj.put("JS_COM_CJK_UID", res[i][26]);
					obj.put("YWMC", res[i][27]);
					obj.put("BZXX", res[i][28]);
					obj.put("CLSX", res[i][29]);
					obj.put("SOURCE_LCUID", res[i][30]);
					arr.add(obj);
				}
			}
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********业务流转所需材料查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return arr.toString();
	}

	public List<?> getAllDsCount() {
		List<?> reslutList=null;
        Connection conn = DBUtil.getConnection();
      
        try {
//        	String sql="select y.spyw_uid," +
//        			" (select count(lz.ywlz_uid) from bu_sp_ywlz lz where lz.status='0' and lz.spyw_uid=y.spyw_uid) as countds" +
//        			" from bu_sp_ywxx y";  
        	
        	String sql="select a.root as spyw_uid, nvl(sum(b.num), 0) countds  " + 
					 " from (select id, fid, connect_by_root(id) root   " +
					  "        from (select y.spyw_uid as id, y.p_spyw_uid as fid from bu_sp_ywxx y start with y.p_spyw_uid is null  " +
					 " connect by prior y.spyw_uid = y.p_spyw_uid)   " +
					 "       connect by prior id = fid) a   " +
					 " left join (select z.spyw_uid, count(z.ywlz_uid) num from bu_sp_ywlz z where z.status='0' group by z.spyw_uid) b on a.id = b.spyw_uid   " +
					 " group by root   " +
					 " order by root ";
            reslutList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询所有的待审数据出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return reslutList;
	}
	
	
	public List<?> ywlzList (String ywlzuid){
			
			List<?> reslutList=null;
	        Connection conn = DBUtil.getConnection();
	      
	        try {
	        	String sql="select l.* , (select count(cl.ywcl_uid) from bu_sp_ywcl cl where cl.spyw_uid=l.spyw_uid and cl.clsx='H' and cl.SFYSC='1' ) as hfclcount  " +
	        			" from bu_sp_ywlz l where 1=1 ";
	        	
	        	if(StringUtil.isNotBlankStr(ywlzuid)){
	        		sql+=" and l.ywlz_uid='"+ywlzuid+"' ";
	        	}
	        
	            reslutList=DBUtil.queryReturnList(conn, sql);
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询业务流转数据出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return reslutList;
	}
	
	public String queryGcPrint(String id, BuSpYwlzVO vo, Map map){
	    
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	String sql="  select distinct g.*,j.COMPANY_name,j.JS_COMPANY_UID,s.COMPANY_NAME as SG_COMPANY_NAME from projects_gongcheng g " +
        			"left join dt_gc_sgbb d on g.GONGCHENG_UID=d.GONGCHENG_UID " +
        			"left join sgbb b on d.SGBB_UID=b.SGBB_UID " +
        			"left join sgbb_ry r on b.sgbb_uid=r.sgbb_uid " +
        			"left join projects p on p.projects_uid=g.projects_uid " +
        			"left join js_company j on p.js_company_uid=j.js_company_uid " +
        			"left join SG_ENTERPRISE_LIBRARY s on s.SG_COMPANY_UID = b.SG_COMPANY_UID and s.status=1 " +
        			"where g.gongcheng_uid='"+id+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
	
    public String queryJzPrint(String id, BuSpYwlzVO vo, Map map){
	    
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	String sql="  select distinct g.gongcheng_name,jz.Gc_Zj,jz.jz_mj,jz.jg_leixing,jz.cc_kd,jz.begin_date,jz.end_date," +
        			"jz.contract_gq,jz.contract_zldj,jz.jzs_name,jz.zhengshu_code," +
        			"jz.ZCB_DW, p.lianxiren,p.lianxiren_phone,p.jg_date,p.plan_kg_date,p.zong_touzi," +
        			"p.jianzhu_mianji,g.cengshu,p.JIANSHE_DIZHI,p.PROJECTS_NAME,r.sg_name as XMJL_NAME," +
        			"r.mobile as XMJL_PHONE,sg.FAREN,sg.FAREN_MOBILE," +
        			"sg.ZHUXI_FZR,sg.ZHUXI_MOBILE,zz.zizhi_name,zd.dengji_name," +
        			"sg.company_type,sg.zhengshu_code as ZZ_ZHENGSHU_CODE,j.COMPANY_name,j.JS_COMPANY_UID," +
        			"sg.COMPANY_NAME as SG_COMPANY_NAME " +
        			"from projects_gongcheng g " +
        			"left join dt_gc_sgbb d on g.GONGCHENG_UID=d.GONGCHENG_UID " +
        			"left join sgbb b on d.SGBB_UID=b.SGBB_UID " +
        			"left join sgbb_ry r on b.sgbb_uid=r.sgbb_uid and r.gangwei_uid=19 " +
        			"left join projects p on p.projects_uid=g.projects_uid " +
        			"left join js_company j on p.js_company_uid=j.js_company_uid " +
        			"left join SG_ENTERPRISE_LIBRARY sg on sg.SG_COMPANY_UID = b.SG_COMPANY_UID and sg.status=1 " +
        			"left join sg_zizhi zz on zz.sg_zizhi_uid = sg.sg_zizhi_uid " +
        			"left join sg_zizhi_dengji zd on zd.sg_zizhi_dengji_uid=sg.zhu_dengji " +
        			"left join bu_sp_ywlz lz on lz.projects_uid=g.gongcheng_uid and spyw_uid='114' " +
        			"left join bu_spyw_jzgcqyrqjdxgczsb jz on lz.ywlz_uid=jz.ywlz_uid " +
        			"where g.gongcheng_uid='"+id+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            
            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
public String querySzPrint(String id, BuSpYwlzVO vo, Map map){
	    
//    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	String sql=" select distinct jz.CONTRACT_NR,jz.JISHU_FZR,jz.ZL_YUAN,jz.AQ_YUAN,jz.SG_YUAN,g.gongcheng_name,jz.QY_ZX_WTR,jz.zx_phone,jz.Gc_Zj,jz.begin_date,jz.end_date," +
        			"jz.contract_DATE,jz.contract_zldj,jz.MANAGER,jz.zhengshu_code," +
        			"jz.zb_fb_name, p.lianxiren,p.lianxiren_phone,p.jg_date,p.plan_kg_date,p.zong_touzi," +
        			"p.jianzhu_mianji,g.cengshu,p.JIANSHE_DIZHI,p.PROJECTS_NAME,r.sg_name as XMJL_NAME," +
        			"r.mobile as XMJL_PHONE,sg.FAREN,sg.FAREN_MOBILE," +
        			"sg.ZHUXI_FZR,sg.ZHUXI_MOBILE,zz.zizhi_name,zd.dengji_name," +
        			"sg.company_type,sg.zhengshu_code as ZZ_ZHENGSHU_CODE,j.COMPANY_name ,j.JS_COMPANY_UID,sg.COMPANY_NAME as SG_COMPANY_NAME from projects_gongcheng g " +
        			"left join dt_gc_sgbb d on g.GONGCHENG_UID=d.GONGCHENG_UID " +
        			"left join sgbb b on d.SGBB_UID=b.SGBB_UID  " +
        			"left join sgbb_ry r on b.sgbb_uid=r.sgbb_uid and r.gangwei_uid=19 " +
        			"left join projects p on p.projects_uid=g.projects_uid " +
        			"left join js_company j on p.js_company_uid=j.js_company_uid " +
        			"left join SG_ENTERPRISE_LIBRARY sg on sg.SG_COMPANY_UID = b.SG_COMPANY_UID and sg.status=1 " +
        			"left join sg_zizhi zz on zz.sg_zizhi_uid = sg.sg_zizhi_uid " +
        			"left join sg_zizhi_dengji zd on zd.sg_zizhi_dengji_uid=sg.zhu_dengji " +
        			"left join bu_sp_ywlz lz on lz.projects_uid=g.gongcheng_uid and spyw_uid='115' " +
        			"left join bu_spyw_szgcqyrqjdxgczsb jz on lz.ywlz_uid=jz.ywlz_uid "+
        			"where g.gongcheng_uid='"+id+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    

}
