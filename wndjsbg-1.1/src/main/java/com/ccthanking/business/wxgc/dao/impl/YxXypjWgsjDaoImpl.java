/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.wxgc.YxXypjWgsjDao.java
 * 创建日期： 2015-01-22 上午 09:41:08
 * 功能：   微型工程违规事件和信用评价关联
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-22 上午 09:41:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxXypjWgsjDao;
import com.ccthanking.business.wxgc.vo.YxXypjWgsjVO;
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
 * <p> YxXypjWgsjDao.java </p>
 * <p> 功能：微型工程违规事件和信用评价关联 </p>
 *
 * <p><a href="YxXypjWgsjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-22
 * 
 */

@Component
public class YxXypjWgsjDaoImpl  extends BsBaseDaoTJdbc implements YxXypjWgsjDao {

    public String queryCondition(String json, YxXypjWgsjVO vo, Map map){
    
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

            String sql = "select * from ( SELECT yxw.* ,yw.wgsjfl,yw.wgsjnr,yw.wgsjxxnr "+
		            " FROM YX_XYPJ_WGSJ yxw "+
		            " left join yx_wgsj yw on yw.wgsj_uid=yxw.wgsj_uid) ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");
            bs.setFieldDic("WGSJFL", "WXGCXYPJLX");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<Map<String, String>> getXypjWgsjList(Map map) {
		User user = ActionContext.getCurrentUserInThread();
		List<Map<String, String>> templist=null;
        Connection conn = DBUtil.getConnection();
        try {
        	String xypj_uid=(String)map.get("XYPJ_UID");
            String sql = " select yxw.*,yw.*,fd.dic_value "+
            " from yx_xypj_wgsj yxw "+
            " left join yx_wgsj yw on yw.wgsj_uid=yxw.wgsj_uid "+
            " left join fs_dic_tree fd on fd.dic_code=yw.wgsjfl "+
            " where yxw.xypj_uid ='"+xypj_uid+"'";
            
            templist=DBUtil.queryReturnList(conn, sql);
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return templist;
	}
    
    // 在此可加入其它方法

}
