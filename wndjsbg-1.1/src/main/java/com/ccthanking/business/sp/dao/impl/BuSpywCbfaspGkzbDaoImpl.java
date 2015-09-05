/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywCbfaspGkzbDao.java
 * 创建日期： 2015-05-06 下午 02:07:52
 * 功能：   公开（邀请）招标合同备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-06 下午 02:07:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywCbfaspGkzbDao;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspGkzbVO;
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
 * <p> BuSpywCbfaspGkzbDao.java </p>
 * <p> 功能：公开（邀请）招标合同备案表 </p>
 *
 * <p><a href="BuSpywCbfaspGkzbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-06
 * 
 */

@Component
public class BuSpywCbfaspGkzbDaoImpl  extends BsBaseDaoTJdbc implements BuSpywCbfaspGkzbDao {

    public String queryCondition(String json, BuSpywCbfaspGkzbVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_CBFASP_GKZB t";
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
            bs.setFieldDic("CB_XINGZHI","CB_XINGZHI");
            bs.setFieldDic("SFSZGC","SF");
            bs.setFieldDic("BID_TYPE","BID_TYPE");
            bs.setFieldDic("JS_LEIXING","JS_LEIXING");
            bs.setFieldDic("TZ_LEIXING","TZ_LEIXING");
            bs.setFieldDic("NEIRONG","SGNR");
            bs.setFieldDic("GC_XINGZHI","GC_XINGZHI");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String findByZjId(String ywlz,String ty) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select CBFASP_GKZB_UID from BU_SPYW_CBFASP_GKZB where YWLZ_UID="+ywlz+" and LX='"+ty+"'";
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
	         
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
    
    // 在此可加入其它方法

}
