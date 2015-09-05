/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgsx.BuSpywSggdsjrgwsbDao.java
 * 创建日期： 2015-03-31 上午 09:57:55
 * 功能：   sg_《新区施工工地污水、雨水临时接入管网及环卫设施申请表》
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-31 上午 09:57:55  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywSggdsjrgwsbVO;
import com.ccthanking.business.sgsx.dao.BuSpywSggdsjrgwsbDao;
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
 * <p> BuSpywSggdsjrgwsbDao.java </p>
 * <p> 功能：sg_《新区施工工地污水、雨水临时接入管网及环卫设施申请表》 </p>
 *
 * <p><a href="BuSpywSggdsjrgwsbDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-31
 * 
 */

@Component
public class BuSpywSggdsjrgwsbDaoImpl  extends BsBaseDaoTJdbc implements BuSpywSggdsjrgwsbDao {

    public String queryCondition(String json, BuSpywSggdsjrgwsbVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_SGGDSJRGWSB t";
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

}
