package com.ccthanking.business.jl.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.jl.dao.JlEnterpriseDao;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
@Component
public class JlEnterpriseDaoImpl extends BsBaseDaoTJdbc implements JlEnterpriseDao {

	public String queryCondition(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        String status = "";
		try {
			JSONObject querycondition = JSONObject.fromObject(json);
			String querycondition_txt = querycondition.getString("querycondition");
	        JSONObject subcondition = JSONObject.fromObject(querycondition_txt);
			querycondition_txt = subcondition.getString("conditions");
	        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(querycondition_txt);
	        for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				if("STATUS".equals(obj.get("fieldname"))){
					status = obj.getString("value");
				}
			}
		     PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
	         String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	         if("30".equals(status)){
	        	 condition += " and t.SHENHE_JIEGUO is null ";
	         }
	            
	         String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	         condition += orderFilter;//把查询出来的数据进行排序
	         if (page == null)page = new PageManager();
	         page.setFilter(condition);
	         StringBuffer sql = new StringBuffer();
					
	         sql.append("SELECT T.*,z.zizhi_name,(select yy.yxcbs_uid from yx_yxcbs yy where yy.cbs_type='"+Constants.YXCBS_LX_JL+"' and yy.company_uid= T.JL_COMPANY_UID and T.status='1' ) as yxcbs_uid FROM ENTERPRISE_LIBRARY  T  ");
	         sql.append("  left join ZIZHI z on t.ZHU_ZIZHI = z.zizhi_uid ");
	         //sql.append(" SELECT T.ENTERPRISE_LIBRARY_UID,T.COMPANY_NAME, T.COMPANY_CODE, ");
	         //sql.append(" T.DENGLU_CODE,T.WAIDI_Y,T.ZHIZHAO,T.ZHIZHAO_VALID,T.SHUIWU,T.STATUS ");
	         //sql.append(" FROM ENTERPRISE_LIBRARY  T ");
	         
	         BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
	         
	         //设置字典
	         bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");
	         bs.setFieldDic("WAIDI_Y", "WAIDI_Y");
	         bs.setFieldDic("STATUS", "STATUS");
	            
	         domresult = bs.getJson();//把转换好的数据给domresult
		} catch (Exception e) {
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return domresult;
	}
	
	
    // 获取登录代码
    
    public String getDengluCode() throws Exception{
    	Connection conn = DBUtil.getConnection();
		String code = null;
		try {
			
			String sql = "SELECT  MAX(TO_NUMBER(SUBSTR(T.DENGLU_CODE,6,9))) FROM ENTERPRISE_LIBRARY T WHERE T.DENGLU_CODE LIKE 'WND%'";
			String[][] res = DBUtil.query(conn ,sql);
			if (res!=null&&StringUtils.isNotBlank(res[0][0])) {
				String code1 = Integer.parseInt(res[0][0]) + 1 + "";
				code = code1;
				for (int i = 0; i < 5-code1.length(); i++) {
					code = "0" +code;
				}
			}else{
				code="00001";
			}
			code = "WND" + code;
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询监理企业登录代码出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return code;
	}


	public String queryspyjCondition(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String domresult = "";
		try {
		     PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
	         String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	            
	            
	         String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	         condition += orderFilter;//把查询出来的数据进行排序
	         if (page == null)page = new PageManager();
	         page.setFilter(condition);
	         StringBuffer sql = new StringBuffer();
	         sql.append("SELECT * FROM ENTERPRISE_LIBRARY_SHJL T");
	         BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
	         
	         bs.setFieldDic("SHJG", "STATUS");
	         domresult = bs.getJson();//把转换好的数据给domresult
	         
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}


	public String queryOldCondition(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String domresult = "";
		try {
		     PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
	         String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	            
	            
	         String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	         condition += orderFilter;//把查询出来的数据进行排序
	         if (page == null)page = new PageManager();
	         page.setFilter(condition);
	         StringBuffer sql = new StringBuffer();
	         
	        // BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
	            
	         //domresult = bs.getJson();//把转换好的数据给domresult
	         
		} catch (Exception e) {
			 DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}


	public String tuihui(String ids, String yijian) throws Exception {
		
		Connection conn = DBUtil.getConnection();
		String new_id="";
		String[] arr = ids.split(",");
		
		String shenheRenId=ActionContext.getCurrentUserInThread().getUserSN();
		try {
			for (int i = 0; i < arr.length; i++) {
				String sql_update="update ENTERPRISE_LIBRARY set SHENHE_JIEGUO='20', SHENHE_DATE=sysdate ,SHENHE_REN='"+shenheRenId+"' ,SHENHE_YIJIAN='"+yijian+"' where ENTERPRISE_LIBRARY_UID="+arr[i];
				DBUtil.exec(sql_update);
				
				 new_id = DBUtil.getSequenceValue("ENTERPRISE_LIBRARY_UID");
				//复制该人员的信息
				/*String copy_sql = " insert into ENTERPRISE_LIBRARY (ENTERPRISE_LIBRARY_UID, ZHUANYE1, ZHUANYE2, ZHICHENG_UID,  JL_PERSON_UID, JL_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE) "
						+" select "+new_id+", ZHUANYE1, ZHUANYE2, ZHICHENG_UID,   JL_PERSON_UID, JL_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, 20, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE FROM ENTERPRISE_LIBRARY" 
						+" where ENTERPRISE_LIBRARY_UID ="+arr[i]+"  and status=30";
				*/
				StringBuffer sql = new StringBuffer();
				sql.append(" INSERT INTO ENTERPRISE_LIBRARY (ENTERPRISE_LIBRARY_UID, ");
				sql.append(" JL_COMPANY_UID,DENGLU_CODE,PWD,MIMA,COMPANY_CODE,COMPANY_NAME, ");
				sql.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
				sql.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
				sql.append(" PHONE,FAX,URL,FAREN, ");
				sql.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION,STATUS, ");
				sql.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
				sql.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE) ");
				sql.append(" SELECT "+new_id+" ,JL_COMPANY_UID,DENGLU_CODE,PWD,MIMA,COMPANY_CODE,COMPANY_NAME, ");
				sql.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
				sql.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
				sql.append(" PHONE,FAX,URL,FAREN, ");
				sql.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION,20, ");
				sql.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
				sql.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE FROM ENTERPRISE_LIBRARY ");
				sql.append(" WHERE ENTERPRISE_LIBRARY_UID ="+arr[i]+"  AND status='30' ");
				
				DBUtil.exec(sql.toString());
	
				//复制附件信息
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
						+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
						+" where TARGET_UID = (select ENTERPRISE_LIBRARY_UID from ENTERPRISE_LIBRARY where status = '30' and ENTERPRISE_LIBRARY_UID = "+arr[i]+")  AND BUSINESS_SUB_TYPE='ENTERPRISE_LIBRARY'";
			
				DBUtil.exec(copy_file);
			}		
			conn.commit();
		
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
	
	
	public void updateDshxx(Map map) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        try {

           String sql = "update enterprise_library t set t.shenhe_ren = ?,t.shenhe_jieguo=?,t.shenhe_yijian=?,t.shenhe_date=to_date(?,'yyyy-mm-dd') where t.enterprise_library_uid = ?";
            
           String jieguo = (String) map.get("jg");
           String yijian = (String) map.get("yj");
           String uid = (String) map.get("uid");
           
           DBUtil.executeUpdate(conn, sql, new Object[]{user.getUserSN(),jieguo,yijian,(new SimpleDateFormat("yyyy-MM-dd")).format(new Date()),uid});
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
	}

	/**
	 * 复制临时状态15
	 */
	public String updateCopyByStatus(Map map) {
		Connection conn = null;
		String uid = "";
		try {
			conn = DBUtil.getConnection();
			String id = (String) map.get("uid"); 
			String status = (String) map.get("status");
			
			if("15".equals(status)){
				String delete_sql = "delete from enterprise_library t where t.status = 15 and t.jl_company_uid = (select n.jl_company_uid from enterprise_library n where n.enterprise_library_uid ="+id+")";
				DBUtil.exec(conn ,delete_sql);
				conn.commit();
			}
			
			uid = DBUtil.getSequenceValue("ENTERPRISE_LIBRARY_UID", conn);
			//复制企业信息
			String copy_sql = " insert into ENTERPRISE_LIBRARY (enterprise_library_uid, jl_company_uid, denglu_code, pwd, mima, company_code, company_name, company_type, waidi_y, zhizhao, shuiwu, zhengshu_code, bank, bank_account, zhu_zizhi, zhu_dengji, zen_zizhi, zen_dengji, zizhi_date, address, postcode, phone, fax, url, faren, faren_zhicheng, faren_mobile, lianxiren, lianxiren_mobile, lianxiren_mail, description, status, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, valid_date, jz_y, jz_yuanyin, jz_sjfw, zhizhao_valid, yuyue_date, tags, score) "
					+" select '"+uid+"', jl_company_uid, denglu_code, pwd, mima, company_code, company_name, company_type, waidi_y, zhizhao, shuiwu, zhengshu_code, bank, bank_account, zhu_zizhi, zhu_dengji, zen_zizhi, zen_dengji, zizhi_date, address, postcode, phone, fax, url, faren, faren_zhicheng, faren_mobile, lianxiren, lianxiren_mobile, lianxiren_mail, description, 15, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, valid_date, jz_y, jz_yuanyin, jz_sjfw, zhizhao_valid, yuyue_date, tags, score from ENTERPRISE_LIBRARY " 
					+" where ENTERPRISE_LIBRARY_uid = '"+id+"' ";
			System.out.println(copy_sql);
			DBUtil.exec(conn ,copy_sql);
			//复制附件信息
			String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
					+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+uid+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
					+" where ywid = '"+id+"' and glid4 = 'jl' and ywlx = '999002'";
			DBUtil.exec(conn ,copy_file);
			
			//复制增项资质
			String copy_zeng = "insert into enterprise_zen_zizhi (enterprise_zen_zizhi_uid, enterprise_library_uid, jl_company_uid, zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no)"
							+ " select enterprise_zen_zizhi_uid.nextval, '"+uid+"', '', zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no from enterprise_zen_zizhi"
							+ " where enterprise_library_uid = '"+id+"'  ";
			DBUtil.exec(conn ,copy_zeng);
		
			conn.commit();
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return uid;
	}

}
