/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.spxx.BuSpLzhfDao.java
 * 创建日期： 2014-06-25 下午 03:35:39
 * 功能：   审批业务流转核发文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-25 下午 03:35:39  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.spxx.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.spxx.dao.BuSpLzhfDao;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuSpLzhfDao.java </p>
 * <p> 功能：审批业务流转核发文件 </p>
 *
 * <p><a href="BuSpLzhfDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-25
 * 
 */

@Component
public class BuSpLzhfDaoImpl  extends BsBaseDaoTJdbc implements BuSpLzhfDao {

    public String queryCondition(String json, BuSpLzhfVO vo, Map map){
    
//    	User user = ActionContext.getCurrentUserInThread();
    
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

//            String sql = "SELECT * FROM " + "BU_SP_LZHF t";
           
            String sql="select * from( select ff.fileid as fileid, ff.filename as filename,ff.url as fileurl,cl.ywcl_uid," +
            	"   cl.spyw_uid,cl.clnr,cl.clsx,cl.sfysc,cl.url,cl.enabled, "+
				"   k.clk_uid,k.clmc,k.sfyfj,lz.ywlz_uid,lz.projects_uid,lz.status,lz.cishu, "+
            	" 	hf.lzhf_uid,hf.pijian_code,hf.pijian_name,hf.lingjian_ren,hf.lingjian_phone, "+
            	" 	hf.lingjian_date,hf.fafang_ren,decode(hf.fz_date,'',to_char(sysdate,'yyyy-MM-dd'),to_char(hf.fz_date,'yyyy-MM-dd')) as fz_date," +
            	"   decode(hf.yxq_date,'',to_char(sysdate,'yyyy-MM-dd'),to_char(hf.yxq_date,'yyyy-MM-dd')) as yxq_date, "+
            	" 	(select s.user_name from users s where s.logon_name=hf.fafang_ren) as user_name   "+
            	" 	from bu_sp_ywcl cl "+
            	" 	left join bu_sp_clk k on k.clk_uid=cl.clk_uid "+
            	" 	left join bu_sp_ywlz lz on lz.spyw_uid=cl.spyw_uid "+
            	" 	left join bu_sp_lzhf hf on hf.ywcl_uid = cl.ywcl_uid and hf.ywlz_uid=lz.ywlz_uid " +// hf.ywcl_uid=cl.ywcl_uid
            	"   left join fs_fileupload ff on ff.ywid=to_char(hf.lzhf_uid) and ff.glid2 = k.clk_uid and ff.fjlb=k.clk_uid)"; //ff.ywid=to_char(hf.lzhf_uid)
//            	" 	where cl.clsx='H' and lz.ywlz_uid=''" ;
            
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

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<?> getTempletFile(String ywlzUid,String clk_uid) {
       List<?> resList=null;
		Connection conn = DBUtil.getConnection();
//        String domresult = "";
        try {
//审批业务流转状态为通过    材料属性为核发     业务UID？？？？？？
            String sql =" select ff.fileid as fileid, ff.filename as filename,ff.url as fileurl,spclk.clk_uid as clkuid," +
            		" spclk.clmc as clmc ,spclk.cl_level as cllevel ,ps.js_company_uid as comid,ps.projects_uid as proid "+
						" from fs_fileupload ff  "+
						" join bu_sp_clk spclk on ff.ywid=to_char(spclk.clk_uid) "+
						" join bu_sp_ywcl spcl on spclk.clk_uid=spcl.clk_uid  "+
						" join bu_sp_ywlz splz on splz.spyw_uid=spcl.spyw_uid " +
						" join projects ps on ps.projects_uid=splz.projects_uid "+
						" where splz.ywlz_uid='"+ywlzUid+"' and spcl.clsx='H' and splz.status='1' and spclk.clk_uid='"+clk_uid+"'";
           
            resList=DBUtil.queryReturnList(conn, sql);
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询模版文件出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resList;
	}

	public boolean insertFileUpload(FileUploadVO vo) {
		boolean isResult=false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String addSql = " insert into fs_fileupload (fileid,filename,url,zhux,lrr,lrbm,lrsj,ywlx,fjlx,ywid,fjzt,fjlb,glid1,glid2,glid4 ) "+
							" values('"+vo.getFileid()+"','"+vo.getFilename()+"','"+vo.getUrl()+"','1','"+vo.getLrr()+"'," +
							" '"+vo.getLrbm()+"',sysdate,'"+vo.getYwlx()+"','application/pdf','"+vo.getYwid()+"'," +
							"'"+vo.getFjzt()+"','"+vo.getFjlb()+"','"+vo.getGlid1()+"','"+vo.getGlid2()+"','"+vo.getGlid4()+"')";			
			isResult=DBUtil.execSql(conn, addSql);
			conn.commit();
			
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			DaoException.handleMessageException("*********查询模版文件出错!*********",e);
			
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return isResult;
	}
    
    // 在此可加入其它方法

}
