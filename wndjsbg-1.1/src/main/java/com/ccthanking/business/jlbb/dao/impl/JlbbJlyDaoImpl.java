/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jlbb.JlbbJlyDao.java
 * 创建日期： 2015-01-28 上午 09:21:37
 * 功能：   监理项目的人员报备信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:21:37  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jlbb.dao.impl;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.jl.vo.JlbbJlyVO;
import com.ccthanking.business.jlbb.dao.JlbbJlyDao;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> JlbbJlyDao.java </p>
 * <p> 功能：监理项目的人员报备信息 </p>
 *
 * <p><a href="JlbbJlyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Component
public class JlbbJlyDaoImpl  extends BsBaseDaoTJdbc implements JlbbJlyDao {

    public String queryCondition(String json, JlbbJlyVO vo, Map map){
    
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

            StringBuffer sql = new StringBuffer();
            sql.append("  SELECT taba.JLBB_JLY_UID,taba.status, GW.NAMES GWNAME,taba.JL_NAME,taba.ZHENGSHU_NAME,  ");
            sql.append("  taba.ZHENGSHU_CODE,taba.ZHUANYE,taba.AGE,taba.ZHICHENG_NAME,taba.SHENFENZHENG ");
            sql.append("   FROM JLBB_JLY taba ");
            sql.append("  LEFT JOIN GANGWEI GW  ");
            sql.append("  ON taba.GANGWEI_UID = GW.GANGWEI_UID ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);


            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String queryBbxx(String json) {
		// TODO Auto-generated method stub
		return null;
	}
    
	
/*	public JlyStatusInfoVO insertJlbbStatus(Connection conn,JlbbJlyVO vo,String reason){
		
		JlyStatusInfoVO stVO = new JlyStatusInfoVO();
		stVO.setChange_date(new Date());

		stVO.setOld_status("0");
		stVO.setNew_status("1");
		stVO.setReason(reason);
	
		stVO.setJl_person_uid(vo.getJl_person_uid());
		stVO.setJlbb_uid(vo.getJlbb_uid());
		stVO.setJlbb_jly_uid(vo.getJlbb_jly_uid());
		
		try {
			if(conn==null)
			conn = DBUtil.getConnection();
			
			BaseDAO.insert(conn, stVO);
			
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		}
		return stVO;
	}*/
    // 在此可加入其它方法

	public String checkPerson(String pid,String gwid, String gcdj) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();

		try {
			String sql = "  select  p.zhuce_type,p.valid_date from person_library p where p.status = '1' and p.jl_person_uid = '"+pid+"' ";
			List<?> list = DBUtil.queryReturnList(conn, sql);
			if(list.size()>0){
				Map map = (Map) list.get(0);
				
				String vdate = map.get("VALID_DATE")==null?"":map.get("VALID_DATE").toString();
				
				if("10".equals(gwid)){
					//一级工程
					if("22".equals(gcdj)){
						if(isOverdue(vdate)||!"G".equals(map.get("ZHUCE_TYPE"))||isExist(conn,pid,gwid)){
							obj.put("STATUS", 1);
							obj.put("REASON", "不可用");
						}else{
							obj.put("STATUS", 2);
							obj.put("REASON", "可用");
						}
					  
					}else{
						if(isOverdue(vdate)||isExist(conn,pid)){
							obj.put("STATUS", 1);
							obj.put("REASON", "不可用");
						}else{
							obj.put("STATUS", 2);
							obj.put("REASON", "可用");
						}
					}
					
				}// 安全监理
				else if("13".equals(gwid)){
					if(isOverdue(vdate)||isExist1(conn,pid)){
						obj.put("STATUS", 1);
						obj.put("REASON", "不可用");
					}else{
						obj.put("STATUS", 2);
						obj.put("REASON", "可用");
					}
					
				}//其他人员
				else{
					
					if(isOverdue(vdate)){
						obj.put("STATUS", 1);
						obj.put("REASON", "不可用");
					}else{
						obj.put("STATUS", 2);
						obj.put("REASON", "可用");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return obj.toString();
	}

    /**
     * 判断总监有几个一级工程
     * @param conn
     * @param pid
     * @param gwid
     * @param gcdj
     * @return 
     * @throws Exception
     */
	private boolean isExist(Connection conn,String pid,String gcdj) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from jlbb  bb left join jlbb_jly  ry on bb.jlbb_uid = ry.jlbb_uid where ry.jl_person_uid = '");
		sql.append(pid);
		sql.append("' and ry.gangwei_uid = '10' and ry.status = '1'");
		sql.append(" and bb.gc_dengji_uid = '");
		sql.append(gcdj);
		sql.append("'");
		List<?> list = DBUtil.queryReturnList(conn, sql.toString());
		return list.size()>1;
	}
	
	/**
	 * 判断总监有几个其他级别的工程
	 * @param conn
	 * @param pid
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	private boolean isExist(Connection conn,String pid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from jlbb  bb left join jlbb_jly  ry on bb.jlbb_uid = ry.jlbb_uid where ry.jl_person_uid = '");
		sql.append(pid);
		sql.append("' and ry.gangwei_uid = '10' and ry.status = '1' ");
		sql.append(" and bb.gc_dengji_uid ！= '22'");

		List<?> list = DBUtil.queryReturnList(conn, sql.toString());
		return list.size()>2;
	}
	/**
	 * 判断专业监理有几个工程
	 * @param conn
	 * @param pid
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	private boolean isExist1(Connection conn,String pid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from jlbb  bb left join jlbb_jly  ry on bb.jlbb_uid = ry.jlbb_uid where ry.jl_person_uid = '");
		sql.append(pid);
		sql.append("' and ry.gangwei_uid = '13' and ry.status = '1' ");
		
		List<?> list = DBUtil.queryReturnList(conn, sql.toString());
		return list.size()>2;
	}
	
	
	
	/**
	 * 判断证书是否过期
	 * @param odate
	 * @return 过期返回ture  
	 */
	private static boolean isOverdue(String odate) {
		long quot = 0;
		// 获取当前时间，转换传过来的时间
		if (odate != null && !odate.equals("")) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date time1 = df.parse(odate);
				Date time2 = df.parse(df.format(new Date()));
				Date date1 = time1;
				Date date2 = time2;
				quot = date1.getTime() - date2.getTime();
				//quot = (quot / 1000 / 60 / 60 / 24)+1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return quot < 0;
	}
	

}
