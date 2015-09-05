/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.SgPersonZhengshuDao.java
 * 创建日期： 2014-04-27 下午 01:01:06
 * 功能：   人员证书记录信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-27 下午 01:01:06  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.person.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.person.dao.SgPersonZhengshuDao;
import com.ccthanking.business.person.service.impl.SgPersonZhengshuServiceImpl;
import com.ccthanking.business.person.vo.SgPersonLibraryVO;
import com.ccthanking.business.person.vo.SgPersonZhengshuVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> SgPersonZhengshuDao.java </p>
 * <p> 功能：人员证书记录信息 </p>
 *
 * <p><a href="SgPersonZhengshuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-27
 * 
 */

@Component
public class SgPersonZhengshuDaoImpl  extends BsBaseDaoTJdbc implements SgPersonZhengshuDao {
	private static Logger logger = LoggerFactory.getLogger(SgPersonZhengshuServiceImpl.class);
	private static String SQL_QUERY_ALL="select a.sg_person_zhengshu_uid,a.zhengshu_code,a.begin_date,a.end_date,b.zhengshu_name,c.zhuanye_name,( select count(*) from at_fileupload d where d.ENABLED=1 and d.target_uid=a.SG_PERSON_ZHENGSHU_UID) FJS from sg_person_zhengshu a left join sg_zhengshu b " +
			                             "on a.sg_zhengshu_uid=b.sg_zhengshu_uid " +
			                             "left join sg_zizhi c on a.sg_zizhi_uid=c.sg_zizhi_uid";
	private String ywlx = YwlxManager.SGZS;

	public void deleteByPersonuid(SgPersonLibraryVO vo) {
		vo.getSg_person_library_uid();
	    String SQL_DELE="delete from sg_person_zhengshu where sg_person_uid="+vo.getSg_person_library_uid();
	    try {
			DBUtil.execSql(SQL_DELE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String queryCondition(String json, SgPersonZhengshuVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "SG_PERSON_ZHENGSHU t";
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
    public String queryCondition(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
            String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
            //condition += "  and sss='ddd' "
            String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
           
            condition += orderFilter;//把查询出来的数据进行排序
          
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);
            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY_ALL, page);//连接数据库，进行查询，结果集给bs
            domresult = bs.getJson();//把转换好的数据给domresult
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    //查询证书名字
    public String queryZS(String id){
        
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String s = "";
        try {

          String SQL_find="select WM_CONCAT(c.zhengshu_name) from sg_person_zhengshu b left join "+
           " sg_zhengshu c on b.sg_zhengshu_uid = c.sg_zhengshu_uid "+
           " where sg_person_zhengshu_uid in (select sg_person_zhengshu_uid from sg_person_zhengshu a where a.sg_person_uid="+id+")";
         
          String[][] res = DBUtil.query(conn, SQL_find);
			if(res!=null){
				 s = res[0][0];
		     }
         } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return s;

    }
	
    public String delete(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVo = null;
        SgPersonZhengshuVO voZhengshuVO=new SgPersonZhengshuVO();
    	User user = ActionContext.getCurrentUserInThread();

        try {
            conn.setAutoCommit(false);
            JSONArray list = voZhengshuVO.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);
            
            voZhengshuVO.setValueFromJson(jsonObj);
            
            BaseDAO.delete(conn, voZhengshuVO);
    		LogManager.writeUserLog(voZhengshuVO.getSg_zhengshu_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<证书信息>删除", user, "", "");
    	
            resultVo = voZhengshuVO.getRowJson();
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("合同信息删除失败!");
          } finally {
            DBUtil.closeConnetion(conn);
        }
    return resultVo;
		
	}
	

	public String queryListPersonZhengshu(String uid) throws Exception {
		
		Connection conn = DBUtil.getConnection();
		//String sql = "select a.*,b.zizhi_name,b.zizhi_type,b.zhuanye_name,c.sg_zizhi_dengji_uid,c.dengji_nums,c.dengji_name from SG_ENTERPRISE_ZEN_ZIZHI a "
		//	+" left join sg_zizhi b on a.sg_zizhi_uid = b.sg_zizhi_uid left join sg_zizhi_dengji c on a.zizhi_dengji = c.sg_zizhi_dengji_uid where a.SG_ENTERPRISE_LIBRARY_UID = "+uid+" order by a.SERIAL_NO desc";
		String ListZhengshu="select a.sg_person_zhengshu_uid,a.sg_person_uid,sgk_package.person_zhengshu_change(SG_PERSON_ZHENGSHU_UID) XX,a.sg_zhengshu_uid,a.sg_zizhi_uid,a.zhengshu_code,a.begin_date,a.end_date,b.zhengshu_name,c.zhuanye_name from sg_person_zhengshu a left join sg_zhengshu b on a.sg_zhengshu_uid=b.sg_zhengshu_uid"
            +" left join sg_zizhi c on a.sg_zizhi_uid=c.sg_zizhi_uid where a.sg_person_uid="+uid+"order by a.SERIAL_NO desc";
		JSONArray jsonArr = new JSONArray();
		
		try {
			//List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, ListZhengshu);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("SG_PERSON_ZHENGSHU_UID",rsMap.get("SG_PERSON_ZHENGSHU_UID"));
				jsonObj.put("SG_PERSON_UID", rsMap.get("SG_PERSON_UID"));
			    jsonObj.put("SG_ZHENGSHU_UID", rsMap.get("SG_ZHENGSHU_UID"));
			    jsonObj.put("SG_ZIZHI_UID", rsMap.get("SG_ZIZHI_UID"));
			    jsonObj.put("ZHENGSHU_CODE", rsMap.get("ZHENGSHU_CODE"));
			    jsonObj.put("ZHENGSHU_NAME", rsMap.get("ZHENGSHU_NAME"));
			    jsonObj.put("ZHUANYE_NAME", rsMap.get("ZHUANYE_NAME"));
			    jsonObj.put("ZHENGSHU_UID", rsMap.get("SG_ZHENGSHU_UID"));
			    jsonObj.put("ZHUANYE_UID", rsMap.get("SG_ZHUANYE_UID"));
			    jsonObj.put("BEGIN_DATE", rsMap.get("BEGIN_DATE"));
			    jsonObj.put("END_DATE", rsMap.get("END_DATE"));
			    jsonObj.put("XX", rsMap.get("XX"));
			    jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
}

	
    public String update(String json) throws Exception {
		
		return null;
	}
    
    public String insert(String json) throws Exception {
	 	return null;
}
//审核通过，把副表数据，和附件都新增。
	
	public String updateCopyZhengshu(String t_id,String u_id,String new_id) throws Exception{
       //System.out.print(new_id);
        Connection conn = null;
		try {
			User user = ActionContext.getCurrentUserInThread();

			conn = DBUtil.getConnection();
			/*String sql = "select SG_PERSON_ZHENGSHU_UID from SG_PERSON_ZHENGSHU where SG_PERSON_UID = (select SG_PERSON_UID from SG_PERSON_LIBRARY where status = 1 and sg_person_uid = "+user.getIdCard()+")";*/
			String sql = "select SG_PERSON_ZHENGSHU_UID from SG_PERSON_ZHENGSHU where SG_PERSON_UID = "+t_id;//(select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where status = 3 and sg_person_uid = "+u_id+")";
			String[][] results = DBUtil.query(conn,sql);
			if(results==null){
				return null;
			};
			//String SQL_DELETE="DELETE FROM SG_PERSON_ZHENGSHU WHERE SG_PERSON_UID =(select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where STATUS = "+status+" and SHENFENZHENG='"+shenfenID+"')";
			//DBUtil.exec(SQL_DELETE);
			logger.info("<{}>执行操作【证书删除】",user.getName());

			for (int i = 0; i < results.length; i++) {
				
				
				String new_idZS = DBUtil.getSequenceValue("SG_PERSON_ZHENGSHU_UID");
				String copy_sql = "insert into SG_PERSON_ZHENGSHU (SG_PERSON_ZHENGSHU_UID, SG_PERSON_UID,SG_ZIZHI_UID,SG_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE) "
						+ "select "+new_idZS+", "+new_id+", SG_ZIZHI_UID,SG_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE from SG_PERSON_ZHENGSHU where SG_PERSON_ZHENGSHU_UID = "+results[i][0];
				DBUtil.exec(conn, copy_sql);
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_idZS+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+results[i][0]+" AND BUSINESS_SUB_TYPE='SG_PERSON_ZHENGSHU'";
				DBUtil.exec(copy_file);
				logger.info("<{}>执行入库操作【证书新增数据及附件】",user.getName());
			}

		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
            logger.error("证书新增失败!");
            e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return "";
	}
	public String updateCopyZhengshu2(String IdCard,String new_id) throws Exception{
        //System.out.print(new_id);
        Connection conn = null;
		try {
			User user = ActionContext.getCurrentUserInThread();

			conn = DBUtil.getConnection();
			/*String sql = "select SG_PERSON_ZHENGSHU_UID from SG_PERSON_ZHENGSHU where SG_PERSON_UID = (select SG_PERSON_UID from SG_PERSON_LIBRARY where status = 1 and sg_person_uid = "+user.getIdCard()+")";*/
			String sql = "select SG_PERSON_ZHENGSHU_UID from SG_PERSON_ZHENGSHU where SG_PERSON_UID = (select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where status = 1 and sg_person_library_uid = "+IdCard+")";
			String[][] results = DBUtil.query(conn,sql);
			if(results==null){
				return null;
			};
			for (int i = 0; i < results.length; i++) {
				
				
				String new_idZS = DBUtil.getSequenceValue("SG_PERSON_ZHENGSHU_UID");
				String copy_sql = "insert into SG_PERSON_ZHENGSHU (SG_PERSON_ZHENGSHU_UID, SG_PERSON_UID,SG_ZIZHI_UID,SG_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE) "
						+ "select "+new_idZS+", "+new_id+", SG_ZIZHI_UID,SG_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE from SG_PERSON_ZHENGSHU where SG_PERSON_ZHENGSHU_UID ="+results[i][0];
				DBUtil.exec(conn, copy_sql);
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_idZS+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+results[i][0]+" AND BUSINESS_SUB_TYPE='SG_PERSON_ZHENGSHU'";
				DBUtil.exec(copy_file);
				logger.info("<{}>执行入库更改操作【证书新增数据及附件】",user.getName());
			}

		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			logger.error("证书更改失败!");
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return "";
	}
	
	
}
