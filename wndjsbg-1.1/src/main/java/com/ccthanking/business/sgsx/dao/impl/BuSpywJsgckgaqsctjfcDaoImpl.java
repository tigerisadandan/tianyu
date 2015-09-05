/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sgsx.BuSpywJsgckgaqsctjfcDao.java
 * 创建日期： 2015-03-31 上午 09:51:41
 * 功能：   sg_建设工程施工开工前安全条件备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-31 上午 09:51:41  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sgsx.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgckgaqsctjfcVO;
import com.ccthanking.business.sgsx.dao.BuSpywJsgckgaqsctjfcDao;
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
 * <p> BuSpywJsgckgaqsctjfcDao.java </p>
 * <p> 功能：sg_建设工程施工开工前安全条件备案表 </p>
 *
 * <p><a href="BuSpywJsgckgaqsctjfcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-03-31
 * 
 */

@Component
public class BuSpywJsgckgaqsctjfcDaoImpl  extends BsBaseDaoTJdbc implements BuSpywJsgckgaqsctjfcDao {

    public String queryCondition(String json, BuSpywJsgckgaqsctjfcVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "BU_SPYW_JSGCKGAQSCTJFC t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");
               bs.setFieldDic("XMJL_KHZ_Y", "YOUWU");
               bs.setFieldDic("AQY_COUNT_Y", "FUHE");
               bs.setFieldDic("AQY_FH_Y", "SHIFOU");
               bs.setFieldDic("AQGLZD_Y", "SHIFOU");
               bs.setFieldDic("AQSGFA_Y", "SHIFOU");
               bs.setFieldDic("AQSGSX_Y", "SHIFOU");
               bs.setFieldDic("LSSS_Y", "SHIFOU");
               bs.setFieldDic("WL_Y", "SHIFOU");
               bs.setFieldDic("CXT_Y", "SHIFOU");
               bs.setFieldDic("STYP_Y", "FUHE");
               bs.setFieldDic("SGBP_Y", "FUHE");
               bs.setFieldDic("PMT_Y", "SHIFOU");
               bs.setFieldDic("PMT_BZ_Y", "SHIFOU");
               bs.setFieldDic("PMT_FH_Y", "FUHE");

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

