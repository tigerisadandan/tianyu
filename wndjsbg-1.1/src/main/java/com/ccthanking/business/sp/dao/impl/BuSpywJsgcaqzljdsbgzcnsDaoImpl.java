/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywJsgcaqzljdsbgzcnsDao.java
 * 创建日期： 2014-06-10 下午 02:52:43
 * 功能：   安全、质量监督申报告知承诺书
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-10 下午 02:52:43  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywJsgcaqzljdsbgzcnsDao;
import com.ccthanking.business.spyw.vo.BuSpywJsgcaqzljdsbgzcnsVO;
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
 * <p> BuSpywJsgcaqzljdsbgzcnsDao.java </p>
 * <p> 功能：安全、质量监督申报告知承诺书 </p>
 *
 * <p><a href="BuSpywJsgcaqzljdsbgzcnsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-10
 * 
 */

@Component
public class BuSpywJsgcaqzljdsbgzcnsDaoImpl  extends BsBaseDaoTJdbc implements BuSpywJsgcaqzljdsbgzcnsDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcaqzljdsbgzcnsDaoImpl.class);
    public String queryCondition(String json, BuSpywJsgcaqzljdsbgzcnsVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "Bu_Spyw_Jsgcaqzljdsbgzcns t";
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
    
    // 在此可加入其它方法
    /**
	 * 通过业务流转UID来查数据ID
	 * */
	public String getIdByYwlzuid(String ywlzuid) {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
       
        try {
            String sql="select AQZLJDSBGZCNS_UID from  Bu_Spyw_Jsgcaqzljdsbgzcns where YWLZ_UID='"+ywlzuid+"' "; 
        	
            String[][] tem=DBUtil.querySql(conn, sql);
            resultVO=tem[0][0];
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
//            e.printStackTrace(System.out);
            logger.error("安全、质量监督申报告知承诺书查询失败!");

        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return resultVO;
	}
}
