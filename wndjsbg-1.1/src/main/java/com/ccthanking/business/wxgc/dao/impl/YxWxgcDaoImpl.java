/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.wxgc.YxWxgcDao.java
 * 创建日期： 2014-12-23 下午 01:29:58
 * 功能：   微型工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:29:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxWxgcDao;
import com.ccthanking.business.wxgc.vo.YxWxgcVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxWxgcDao.java </p>
 * <p> 功能：微型工程 </p>
 *
 * <p><a href="YxWxgcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Component
public class YxWxgcDaoImpl  extends BsBaseDaoTJdbc implements YxWxgcDao {

    public String queryCondition(String json, YxWxgcVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUtil.getSJYXCondition(null);
//            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

          /**  
            String sql = "SELECT * FROM (SELECT yw.*, " +
            		"(select ygt.gc_type_name from yx_gc_type ygt where ygt.gc_type_code=yw.gc_type_code ) as gc_type_name, " +
            		" (select yu.qy_name from yx_user_qy yu where yu.qy_code=yw.qy_code) as qy_name," +
            		" decode(yw.zb_yxcbs_uid,null,0,1) as iscq , " +
            		" decode(sign((select count(ywc.cyz_uid) from yx_wxgc_cyz ywc where ywc.wxgc_uid=yw.wxgc_uid)-3),0,0,1,2,-1,1) as cyzs " +
            		" FROM YX_WXGC yw ) t";
          **/  
            String sql = "SELECT * FROM (SELECT yw.*, " +
    		" (select ygt.gc_type_name from yx_gc_type ygt where ygt.gc_type_code=yw.gc_type_code ) as gc_type_name, " +
    		" (select yu.qy_name from yx_user_qy yu where yu.qy_code=yw.qy_code) as qy_name," +
    		" (select pb.pbfs_name from yx_wxgc_pbfs pb where pb.pbfs_uid=yw.pbfs_uid) as pbfs_name,  "+
    		//"  decode(yw.zb_yxcbs_uid,null,0,1) as iscq ," +
    		"   nvl2(yw.zb_yxcbs_uid,1,0) as iscq ,  "+
    		"  decode(sign((select count(ywc.cyz_uid) from yx_wxgc_cyz ywc where ywc.wxgc_uid=yw.wxgc_uid)-3),0,0,1,2,-1,1) as cyzs," +
    		//" (select yyx.xypj_shzt from yx_yxcbs_xypj yyx where yyx.wxgc_uid=yw.wxgc_uid ) as xypj_shzt," +
    		" (select yyx.xypj_uid from yx_yxcbs_xypj yyx where yyx.wxgc_uid=yw.wxgc_uid ) as xypj_uid," +
    		//" (select count(yw1.wxgc_uid) from YX_WXGC yw1 where yw1.wxgc_uid=yw.wxgc_uid and yw1.jhjgrq<=sysdate ) as sfkpj,  " +
    		" (select count(yw1.wxgc_uid) from YX_WXGC yw1 where yw1.wxgc_uid=yw.wxgc_uid and yw1.gsjzrq<=sysdate ) as sfkdyzbtzs, " +
    		"  (select cyz.xcbj from yx_wxgc_cyz cyz where cyz.wxgc_uid=yw.wxgc_uid and cyz.sfzb='1' ) as xcbj "+
    		" FROM YX_WXGC yw ) t";
            
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
            bs.setFieldDic("ZT", "WXGC_GCZT");
            
            bs.setFieldDic("ISCQ", "SF");
            bs.setFieldDic("ZJLY", "WXGC_ZJLY");//资金来源
            bs.setFieldDic("CYZS", "WXGCCYZS");
            
            bs.setFieldDateFormat("JHKGRQ", "yyyy-MM-dd");
            bs.setFieldDateFormat("JHJGRQ", "yyyy-MM-dd");
            bs.setFieldDateFormat("KBRQ", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("GGKSRQ", "yyyy-MM-dd HH:mm");
            bs.setFieldDateFormat("GGJZRQ", "yyyy-MM-dd HH:mm");
            bs.setFieldThousand("ZBJ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    
    public String queryspjlCondition(String json, YxWxgcVO vo, Map map){
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

            
           // String sql = "SELECT * FROM (select s.*,(select y.username from v_yx_allcbsxx y where y.xtly in('BG','YX') and y.useruid=s.user_uid) as username  from yx_wxgc_spjl s ) t";
            StringBuffer sql = new StringBuffer();
            sql.append(" select  decode(y.step_code,'30',(select user_name from yx_user t where t.user_uid = y.user_uid ), ");
            sql.append(" '3',(select user_name from yx_user t where t.user_uid = y.user_uid),'4',(select user_name from  users u where  u.users_uid = y.user_uid)) as username  ");
            sql.append("  ,decode(y.step_code,'30','监管部门审核','3','分管领导审核','4','建设局审核') as shbz,y.sprq,y.spjg,y.spyj ");
            sql.append("  from yx_wxgc_spjl y  ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
          
            bs.setFieldDic("SPJG", "STATUS");
            
            bs.setFieldDateFormat("SPRQ", "yyyy-MM-dd HH:mm:ss");// 计量月份

            domresult = bs.getJson();
           
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    // 在此可加入其它方法

    
    /**微型工程代码
	 * XQWXGC 2015 01 10 – 01
	 * 年份+月份+日期+当日核准的微型工程数量加1
	 * 
	 * length 几位  01则两位 001 三位
	 * */
	public String getCode(int length) {
			String sccode=null;
			Connection conn = DBUtil.getConnection();
	        try {
	        	String sql="select count(t.wxgc_uid)+1 from yx_wxgc t " +
	        			"where t.gc_code is not null  " +
	        			"and  to_date(to_char(t.jsj_qrrq,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') ";
	        	/*if("J".equals(projectsType)){
	        		sql=sql+" and project_uid is not null";
	        	}*/
	        	String[][] temp=DBUtil.querySql(conn, sql); 
	        	String[][] nf=DBUtil.querySql(conn, "select to_char(sysdate,'yyyymmdd') from dual"); 
	        	if(temp!=null&&temp[0]!=null&&temp[0][0]!=null){
	        		Integer tem=Integer.valueOf(temp[0][0]);        		
	        		String f = "%0" + length + "d";	
	        		
	        		sccode="XQWXGC"+nf[0][0]+"-"+String.format(f, tem);
	        	}
	        
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********生成微型工程编号出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
		return sccode;
	}
	
	

	/**
	 * 通过类型代码获取微型工程类型数据
	 * 返回list
	 * 
	 * */
	public List<Map<String, String>> gcTypeList(Map map) {
		List<Map<String, String>> gcTypeList=null;
		Connection conn = DBUtil.getConnection();
        try {
        	String gctypecode=(String)map.get("GC_TYPE_CODE");
        	String sql="select * from yx_gc_type t where t.gc_type_code='"+gctypecode+"' " ;
        	gcTypeList=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询微型工程类型数据出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return gcTypeList;
	}
}
