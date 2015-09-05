/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.wxgc.YxWxgcCyzDao.java
 * 创建日期： 2014-12-22 下午 02:58:58
 * 功能：   微型工程参与者
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-22 下午 02:58:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxWxgcCyzDao;
import com.ccthanking.business.wxgc.vo.YxWxgcCyzVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxWxgcCyzDao.java </p>
 * <p> 功能：微型工程参与者 </p>
 *
 * <p><a href="YxWxgcCyzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-22
 * 
 */

@Component
public class YxWxgcCyzDaoImpl  extends BsBaseDaoTJdbc implements YxWxgcCyzDao {

    public String queryCondition(String json, YxWxgcCyzVO vo, Map map){
    
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

//            String sql = "SELECT * from ( " +
//    		"select c.* , " +
//			"(select t.username from v_yx_allcbsxx t where t.useruid=( " +
//			"select yx.company_uid from  yx_yxcbs yx where yx.yxcbs_uid=c.yxcbs_uid ) ) as companyname " +
//			"from yx_wxgc_cyz c " +
//    		") t";
            String sql = "select * from ( select cyz.*,vy.username as companyname from yx_wxgc_cyz cyz "+
    		" left join yx_yxcbs yy on yy.yxcbs_uid=cyz.yxcbs_uid "+
    		" left join v_yx_allcbsxx vy on vy.useruid=yy.company_uid and vy.xtly=yy.cbs_type  ) t ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            
            
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典
//            bs.setFieldDic("ZBJG", "WXGC_ZBJG");
            
            // 设置查询条件
            bs.setFieldDateFormat("BMRQ", "yyyy-MM-dd HH:mm");
            // bs.setFieldThousand("DYJLSDZ");
            bs.setFieldDic("ZBJG", "WXGC_ZBJG");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    /**
	 * 根据参与承包商uid 和微型工程uid 获取参与者信息
	 * */
	public List<Map<String, String>> wxgcCyzList(Map map) {
		String wxgcuid=(String)map.get("wxgcuid");
		String yxcbsuid=(String)map.get("yxcbsuid");
		String sjhm=(String)map.get("sjhm");
		String useruid=(String)map.get("useruid");
	
		Connection conn = DBUtil.getConnection();
		List<Map<String ,String>>  domresult=null;
        try {
//        	String sql="select * from (SELECT yw.*,(select yy.company_uid from yx_yxcbs yy where yy.yxcbs_uid=yw.yxcbs_uid) as userid" +
//        			" FROM YX_WXGC_CYZ yw) t where 1=1 ";
        	
        	String sql ="select * from(" +
        			" SELECT yw.*,yy.company_uid as userid,vy.username as companyname "+
        			" FROM YX_WXGC_CYZ yw "+
        			" left join yx_yxcbs yy on yy.yxcbs_uid=yw.yxcbs_uid and yy.zt='10' "+
        			" left join v_yx_allcbsxx vy on vy.xtly=yy.cbs_type and vy.useruid=yy.company_uid " +
        			") t where 1=1 ";
        	
        	if(useruid!=null&&!"".equals(useruid)){
        		sql+=" and t.userid='"+useruid+"' ";
        	}
        	
        	if(yxcbsuid!=null&&!"".equals(yxcbsuid)){
        		sql+=" and t.YXCBS_UID='"+yxcbsuid+"' ";
        	}
        	if(wxgcuid!=null&&!"".equals(wxgcuid)){
        		sql+=" and t.WXGC_UID='"+wxgcuid+"'";
        	}
        	if(sjhm!=null&&!"".equals(sjhm)){
        		sql+=" and t.SJHM ='"+sjhm+"'";
        	}
        	
        	domresult=DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return domresult;
	}
}
