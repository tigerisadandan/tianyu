/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.weixin.FsMessageInfoDao.java
 * 创建日期： 2014-12-02 上午 11:04:41
 * 功能：   消息表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-02 上午 11:04:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.weixin.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.weixin.dao.FsMessageInfoDao;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.ccthanking.weixin.vo.FsMessageInfoVO;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> FsMessageInfoDao.java </p>
 * <p> 功能：消息表 </p>
 *
 * <p><a href="FsMessageInfoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-02
 * 
 */

@Component
public class FsMessageInfoDaoImpl  extends BsBaseDaoTJdbc implements FsMessageInfoDao {

    public String queryCondition(String json, FsMessageInfoVO vo, Map map){
    
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
            condition +=" and t.userto= '"+user.getAccount()+"'";
            if(orderFilter!=null&&!"".equals(orderFilter)){
            	condition += orderFilter;
            }else{
            	condition+=" order by t.optime desc ";
            }
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT t.* FROM fs_message_info t ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            // 设置字典
            //bs.setFieldDic("SJBH", "SJBH");//事件编号
            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");
            bs.setFieldDateFormat("OPTIME", "yyyy-MM-dd");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    
    // 在此可加入其它方法
	public List<?> countMessage(Map map) {
		User user = ActionContext.getCurrentUserInThread();
		List<?> reslutList=null;
	    Connection conn = DBUtil.getConnection();  
	    try {
			String sql="select count(opid) as ALLMES from fs_message_info t where t.userto='"+user.getAccount()+"'" ;	
			String STATE=(String)map.get("STATE");
			if(STATE!=null&&!"".equals(STATE)){
				sql+=" and t.state='"+STATE+"'";
			}
			sql+=" order by t.optime desc ";
			
	        reslutList=DBUtil.queryReturnList(conn, sql);
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询所有的待审数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return reslutList;
	}
    

}
