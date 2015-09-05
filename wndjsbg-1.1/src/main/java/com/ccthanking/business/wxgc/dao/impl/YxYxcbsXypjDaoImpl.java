/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.wxgc.YxYxcbsXypjDao.java
 * 创建日期： 2015-01-21 上午 10:47:08
 * 功能：   预选承包商信用评价
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-21 上午 10:47:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.wxgc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.wxgc.dao.YxYxcbsXypjDao;
import com.ccthanking.business.wxgc.vo.YxYxcbsXypjVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> YxYxcbsXypjDao.java </p>
 * <p> 功能：预选承包商信用评价 </p>
 *
 * <p><a href="YxYxcbsXypjDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-21
 * 
 */

@Component
public class YxYxcbsXypjDaoImpl  extends BsBaseDaoTJdbc implements YxYxcbsXypjDao {

    public String queryCondition(String json, YxYxcbsXypjVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition+=" and t.qy_code = '"+user.getDepartment()+"' ";
            
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "select * from ( SELECT yw.wxgc_uid as gc_wxgc_uid ,yw.gc_name,yw.qy_code,yw.jsdw,yw.zbr,yw.jhkgrq,yw.jhjgrq,yw.jhgq,yw.zbj," +
            	" yw.zb_name,yw.zb_yxcbs_uid, "+
            	" ywc.xcbj, ywc.zbjg,ywc.xmfzr,ywc.zyzgzh,yyx.*, " +
            	" ( case  when yyx.kfzfz = 0 then '清除' " +
            	"  when yyx.kfzfz >0 and yyx.kfzfz <60 then '不合格' " +
            	"  when yyx.kfzfz >=60 and yyx.kfzfz <70 then '基本合格' " +
            	"  when yyx.kfzfz >=70 and yyx.kfzfz <80 then '合格' " +
            	"  when yyx.kfzfz >=80 and yyx.kfzfz < 90 then '良好' " +
            	"  when yyx.kfzfz >=90 and yyx.kfzfz <= 100 then '优秀' end) as pddjname "+
            	" FROM yx_wxgc yw "+
            	" left join yx_wxgc_cyz ywc  on yw.wxgc_uid=ywc.wxgc_uid and yw.zb_yxcbs_uid=ywc.yxcbs_uid "+
            	" left join YX_YXCBS_XYPJ yyx on yyx.wxgc_uid=ywc.wxgc_uid and yyx.yxcbs_uid=yw.zb_yxcbs_uid "+
            	" where yw.zt='10' and yw.zb_yxcbs_uid is not null ) t";
            
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            // 设置字典

            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");
            bs.setFieldDateFormat("JHKGRQ", "yyyy-MM-dd");
            bs.setFieldDateFormat("JHJGRQ", "yyyy-MM-dd");
            bs.setFieldThousand("ZBJ");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public List<Map<String,String>> queryAllXypjXqCondition( Map map){
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        List<Map<String,String>> domresult=null;
        try {
           
            String xypj_uid=(String)map.get("xypj_uid");
            String sql = "select * from ( select yw.wgsj_uid,yw.wgsjfl,yw.wgsjdj,yw.wgsjnr,yw.wgsjxxnr,yw.wgsjkffz, "+ 
            		" yxw.xypjwgsj_uid,yxw.xypj_uid,yxw.bz,yxw.kffz,fdt.dic_value "+
	            	" from yx_wgsj yw " +
	            	" left join fs_dic_tree fdt on fdt.dic_code=yw.wgsjfl and fdt.dic_name_code='WXGCXYPJLX' "+
	            	" left join yx_xypj_wgsj yxw on yw.wgsj_uid=yxw.wgsj_uid ";
            
            if(xypj_uid!=null&&!"".equals(xypj_uid)){
            	sql+="and yxw.xypj_uid ='"+xypj_uid+"' ";
            }

            sql+=")t";
            
            domresult = DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<Map<String, String>> queryXypjLx(Map map) {
		User user = ActionContext.getCurrentUserInThread();
	    
        Connection conn = DBUtil.getConnection();
        List<Map<String,String>> domresult=null;
        try {
        	String xypj_uid=(String)map.get("xypj_uid");
/**            String sql =" select distinct fdt.dic_code,fdt.dic_value ,yxw.bz,yxw.wgsj_uid,yxw.xypj_uid,yxw.kffz,yxw.xypjwgsj_uid "+
            " from fs_dic_tree fdt  "+
            " left join yx_wgsj yw on yw.wgsjfl=fdt.dic_code "+
            " left join yx_xypj_wgsj yxw on yxw.wgsj_uid=yw.wgsj_uid";
            if(xypj_uid!=null&&!"".equals(xypj_uid)){
            	sql+=" and yxw.xypj_uid is not null ";
            }
      **/      
            String sql =" select distinct fdt.dic_code, fdt.dic_value,z.*  "+         
            	"  from fs_dic_tree fdt  "+
            	" left join   "+
            	" (select yw.wgsjfl,yxw.bz,yxw.wgsj_uid,yxw.xypj_uid,  "+
            	"  yxw.kffz, yxw.xypjwgsj_uid  "+
            	"  from yx_wgsj yw  "+
            	"  left join yx_xypj_wgsj yxw  "+
            	"  on yxw.wgsj_uid = yw.wgsj_uid   "+
            	" ) z  on z.wgsjfl= fdt.dic_code";
          
            if(xypj_uid!=null&&!"".equals(xypj_uid)){
            	sql+=" and z.xypj_uid='"+xypj_uid+"' ";
            }
            
            sql+=" where  fdt.dic_name_code='WXGCXYPJLX'  and fdt.sfyx='1' and fdt.dic_code !='WXGCXYPJLX' order by fdt.dic_code  ";
            domresult = DBUtil.queryReturnList(conn, sql);
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

}
