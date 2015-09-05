package com.ccthanking.business.jl.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.dtgl.jl.vo.PersonLibraryVO;
import com.ccthanking.business.jl.dao.JlPersonLibraryDao;
import com.ccthanking.common.FjlbManager;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;

@Component
public class JlPersonLibraryDaoImpl extends BsBaseDaoTJdbc implements JlPersonLibraryDao {

	public String queryCondition(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
		     PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
	         String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	          condition += " and e.status = '1' ";   
	         String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	         condition += orderFilter;//把查询出来的数据进行排序
	         if (page == null)page = new PageManager();
	         page.setFilter(condition);
	         StringBuffer sql = new StringBuffer();
	            sql.append(" SELECT '' ZHENGSHU_LIST,A.STATUS, ");
	            sql.append("   A.SHENHE_DATE,A.SHENHE_JIEGUO, ");
	            sql.append("   A.TIJIAO_DATE,A.SHENHE_YIJIAN, ");
	            sql.append("   A.JZ_Y,A.JZ_YUANYIN,A.JZ_SJFW, ");
	            sql.append("   A.JZ_DATE,B.USER_NAME,A.JL_COMPANY_UID, A.PERSON_LIBRARY_UID, ");
	            sql.append("   E.COMPANY_NAME,E.DENGLU_CODE AS COMPANY_DENGLU_CODE, ");
	            sql.append("   A.JL_PERSON_UID,A.PERSON_NAME,A.PHONE, ");
	            sql.append("   A.EMAIL,A.ZHICHENG_UID,A.ZHICHENG_CODE, ");
	            sql.append("   D.ZHICHENG_NAME,A.BEGIN_DATE,A.END_DATE, ");
	            sql.append("   A.UPDATED_DATE,A.DESCRIPTION,A.DENGLU_CODE, ");
	            sql.append("   '' XINYONGFENSHU,'' YEJI,'' ZAIJIANXIANGMU, ");
	            sql.append("   A.SEX,A.SHENFENZHENG,a.zhuce_type,a.zhuce_code, ");
	            sql.append("  (select names from zhuanye where zhuanye_uid = a.zhuanye1) as zhuanye1 , ");
	            sql.append("  (select names from zhuanye where zhuanye_uid = a.zhuanye2) as zhuanye2 ");
	            sql.append("  ,a.VALID_DATE,a.FAZHENG_DATE");
	            sql.append("   FROM PERSON_LIBRARY A ");
	            sql.append("   LEFT JOIN ZHICHENG D ");
	            sql.append("     ON A.ZHICHENG_UID = D.ZHICHENG_UID ");
	            sql.append("   LEFT JOIN USERS B ");
	            sql.append("     ON A.SHENHE_REN = B.USERS_UID ");
	            sql.append("   LEFT JOIN ENTERPRISE_LIBRARY E ");
	            sql.append("     ON A.JL_COMPANY_UID = E.JL_COMPANY_UID ");
	         BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
	            
	         domresult = bs.getJson();//把转换好的数据给domresult
	         bs.setFieldDic("SEX", "SEX");
	         bs.setFieldDic("ZHUCE_TYPE", "JLRY_CYZG");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}
	

	public String queryNotNull(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
            String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
            
            //condition += " and a.shenhe_jieguo is null ";
            condition +="    AND E.STATUS = '1' ";
            condition +="    AND A.SHENHE_JIEGUO IS NULL ";
            
            String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
            condition += orderFilter;//把查询出来的数据进行排序
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);
            
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT '' ZHENGSHU_LIST,A.STATUS, ");
            sql.append("   A.SHENHE_DATE,A.SHENHE_JIEGUO, ");
            sql.append("   A.TIJIAO_DATE,A.SHENHE_YIJIAN, ");
            sql.append("   A.JZ_Y,A.JZ_YUANYIN,A.JZ_SJFW, ");
            sql.append("   A.JZ_DATE,B.USER_NAME,A.JL_COMPANY_UID, A.PERSON_LIBRARY_UID, ");
            sql.append("   E.COMPANY_NAME,E.DENGLU_CODE AS COMPANY_DENGLU_CODE, ");
            sql.append("   A.JL_PERSON_UID,A.PERSON_NAME,A.PHONE, ");
            sql.append("   A.EMAIL,A.ZHICHENG_UID,A.ZHICHENG_CODE, ");
            sql.append("   D.ZHICHENG_NAME,A.BEGIN_DATE,A.END_DATE, ");
            sql.append("   A.UPDATED_DATE,A.DESCRIPTION,A.DENGLU_CODE, ");
            sql.append("   '' XINYONGFENSHU,'' YEJI,'' ZAIJIANXIANGMU, ");
            sql.append("   A.SEX,A.SHENFENZHENG ");
            sql.append("   FROM PERSON_LIBRARY A ");
            sql.append("   LEFT JOIN ZHICHENG D ");
            sql.append("     ON A.ZHICHENG_UID = D.ZHICHENG_UID ");
            sql.append("   LEFT JOIN USERS B ");
            sql.append("     ON A.SHENHE_REN = B.USERS_UID ");
            sql.append("   LEFT JOIN ENTERPRISE_LIBRARY E ");
            sql.append("     ON A.JL_COMPANY_UID = E.JL_COMPANY_UID ");
           
           
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);//连接数据库，进行查询，结果集给bs
           //时间格式的修改
            
            domresult = bs.getJson();//把转换好的数据给domresult
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	
	
//审核通过，把副表数据，和附件都新增。
	
	public String updateCopyZhengshu(String t_id,String u_id,String new_id) throws Exception{
       //System.out.print(new_id);
        Connection conn = null;
		try {
			User user = ActionContext.getCurrentUserInThread();

			conn = DBUtil.getConnection();
			
			String sql = "SELECT PERSON_ZHENGSHU_UID FROM JL_PERSON_ZHENGSHU ZS WHERE ZS.PERSON_LIBRARY_UID = '"+t_id+"'";
			String[][] results = DBUtil.query(conn,sql);
			if(results==null){
				return null;
			};
			String SQL_DELETE="DELETE FROM jl_person_zhengshu WHERE PERSON_LIBRARY_UID = (select person_library_uid from PERSON_LIBRARY where JL_PERSON_UID = '"+u_id+"' and STATUS='15')";
			DBUtil.exec(SQL_DELETE);
			//logger.info("<{}>执行操作【证书删除】",user.getName());

			for (int i = 0; i < results.length; i++) {
				
				
				String new_idZS = DBUtil.getSequenceValue("JL_PERSON_ZHENGSHU_UID");
				String copy_sql = "insert into JL_PERSON_ZHENGSHU (JL_PERSON_ZHENGSHU_UID, JL_PERSON_UID,JL_ZIZHI_UID,JL_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE) "
						+ "select "+new_idZS+", "+new_id+", JL_ZIZHI_UID,JL_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE from JL_PERSON_ZHENGSHU where JL_PERSON_ZHENGSHU_UID = "+results[i][0];
				//DBUtil.exec(conn, copy_sql);
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_idZS+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+results[i][0]+" AND BUSINESS_SUB_TYPE='JL_PERSON_ZHENGSHU'";
				//DBUtil.exec(copy_file);
				//logger.info("<{}>执行入库操作【证书新增数据及附件】",user.getName());
			}

		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
            //logger.error("证书新增失败!");
            e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return "";
	}
	
	//审核时，把30状态的数据复制一条状态为15的，然后返回15的id，对状态15的数据进行入库、退回的判断。t_id为30状态该数据的ID，u_id为该数据的person_uid
	public String updateCopyPerson(String t_id,String u_id) throws Exception {
		Connection conn = null;
		//String person_uid = "";
		//String tid=t_id;
		
		String new_id="";
		
		try {
			conn = DBUtil.getConnection();
			User user = ActionContext.getCurrentUserInThread();
			//person_uid = user.getIdCard();//获取该人员的ID
			
			//查询15状态是否有记录
			
			String sql = " select person_library_uid from PERSON_LIBRARY where JL_PERSON_UID = '"+u_id+"' and STATUS='15'";
			String s="";
	        String[][] res = DBUtil.query(conn, sql);
			if(res!=null){
				 s = res[0][0];
				
			}
			
			//if(StringUtils.isNotBlank(person_uid)){
				//删除主表中15状态的记录
				
				String SQL_DELETE="DELETE FROM PERSON_LIBRARY WHERE JL_PERSON_UID = '"+u_id+"' and STATUS='15'";
				
				DBUtil.exec(SQL_DELETE);
				if(!s.equals("")&&s!=null){
				//删除改记录附件
				//String SQL_DELETE_FJ="DELETE FROM at_fileupload WHERE TARGET_UID="+s;
				//DBUtil.exec(SQL_DELETE_FJ);
				
				}
				    new_id = DBUtil.getSequenceValue("PERSON_LIBRARY_UID");
					//复制该人员的信息
					String copy_sql = " insert into PERSON_LIBRARY (person_library_uid, jl_person_uid, jl_company_uid, denglu_code, pwd, mima, person_name, sex, birthday, shenfenzheng, photo, nation, phone, email, xueli, college, zhuanye1, zhuanye2, zhicheng_uid, zhicheng_code, zhuce_type, zhuce_code, valid_date, jishu_y, description, status, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, jz_y, jz_yuanyin, jz_sjfw, fzr_y, begin_date, end_date, fazheng_date, jz_date, score ) "
						+" select '"+new_id+"', jl_person_uid, jl_company_uid, denglu_code, pwd, mima, person_name, sex, birthday, shenfenzheng, photo, nation, phone, email, xueli, college, zhuanye1, zhuanye2, zhicheng_uid, zhicheng_code, zhuce_type, zhuce_code, valid_date, jishu_y, description, 15, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, jz_y, jz_yuanyin, jz_sjfw, fzr_y, begin_date, end_date, fazheng_date, jz_date, score  FROM PERSON_LIBRARY" 
						+" where PERSON_LIBRARY_UID ="+t_id;
					System.out.println("copy_sql==="+copy_sql);
					DBUtil.exec(copy_sql);

					//复制附件信息
					String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
						+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+new_id+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
						+" where ywid = '"+t_id+"'  and glid4 = 'jl' and ywlx = '999003' ";
					System.out.println("copy_file==="+copy_file);
					DBUtil.exec(copy_file);
					//复制证书信息
					String copy_zs = "insert into jl_person_zhengshu (jl_person_zhengshu_uid, person_library_uid, zhuce_code, valid_date, fazheng_date, zhuce_name) "
								 +" select jl_person_zhengshu_uid.nextval, '"+new_id+"', zhuce_code, valid_date, fazheng_date, zhuce_name from jl_person_zhengshu "
								 +" where person_library_uid = '"+t_id+"'";
					System.out.println("copy_zs==="+copy_zs);
					DBUtil.exec(copy_zs);
				

				conn.commit();
			//}
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return new_id;
	}
	
	
	//提交审核更新状态
	public String updateShenhe(String json,String u_id,String status,String t_id) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        PersonLibraryVO vo = new PersonLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
       // int sta=Integer.parseInt(status);
        String s="";
        try {
        	
        	conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            
            
            //把15状态转换到10状态的主ID返回
            String shenheRenId=ActionContext.getCurrentUserInThread().getUserSN();
            System.out.println(shenheRenId);
            String sql_find = " select person_library_uid from PERSON_LIBRARY where JL_PERSON_UID = '"+u_id+"' and STATUS=15";
			String[][] res = DBUtil.query(conn, sql_find);
			if(res!=null){
				 s = res[0][0];
		         
			}
			//给30状态的该数据添加数据
	        String t="";
            String sql_find_thirty = " select person_library_uid from PERSON_LIBRARY where PERSON_LIBRARY_UID = "+t_id;
			String[][] res_th = DBUtil.query(conn, sql_find_thirty);
			if(res_th!=null){
				 t = res_th[0][0];
		         String sql_add="update PERSON_LIBRARY set shenhe_jieguo='"+status+"' ,shenhe_ren='"+shenheRenId+"' where person_library_uid="+t;
		         DBUtil.exec(sql_add);
			}
			
			//然后把页面的值存入
            vo.setValueFromJson(obj);//把页面的值给属性
            vo.setPerson_name(vo.getPerson_name().trim());
            vo.setShenfenzheng(StringUtils.upperCase(vo.getShenfenzheng()));
            String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
            
            vo.setStatus(status);
           // vo.setShenhe_yijian(yj);
            vo.setUpdated_date(new Date());
            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(ActionContext.getCurrentUserInThread().getUserSN());
           // vo.setUpdated_name(ActionContext.getCurrentUserInThread().getName());
            //vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
            //判断是否有该人员，并获取该公司id
            if(!updateYczxx(conn, vo.getPerson_library_uid(),vo.getJl_company_uid())){
            	vo.setDenglu_code(getDengluCode(conn, vo.getPerson_library_uid(),vo.getZhuce_type()));
            	vo.setMima("123456");
            	vo.setPwd(DigestUtils.md5Hex("123456"));
            }
            //增加资格证书
/*           String zhengshuList = getZhengshu(conn, obj, vo,fileList);
            if(!"".equals(zhengshuList)){
            	zhengshuList = zhengshuList.substring(0,zhengshuList.length()-1);
        		//清楚多余页面被删掉的资格证书
        		String clearzhengshu_SQL = "delete from sg_person_zhengshu t where t.sg_person_uid = "+vo.getSg_person_library_uid()+" and t.sg_person_zhengshu_uid not in ("+zhengshuList+")";
        		DBUtil.exec(conn, clearzhengshu_SQL);
        	}else{
        		//删除所有资格证书
            	String clearzhengshu_SQL = "delete from sg_person_zhengshu t where t.sg_person_uid = "+ vo.getSg_person_library_uid();
        		DBUtil.exec(conn, clearzhengshu_SQL);
        	}*/
           
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();

            conn.commit();
                
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return s;
	}
	
	
	private boolean updateYczxx(Connection conn , String uid , String conpanyuid) throws Exception{
		boolean flag = true;
		try {
			String sql = "select n.person_library_uid from person_library n where n.status = 1 and n.jl_company_uid= '"+conpanyuid+"' and n.jl_person_uid = (select t.jl_person_uid from person_library t where t.person_library_uid = '"+uid+"')";
			
			String[][] res = DBUtil.query(conn ,sql);
			if(res==null||res.length==0){
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}
	
	
	//生成登录code
	private String getDengluCode(Connection conn , String uid,String zhuce_type) throws Exception{
		String code = null;
		String s = null;
		try {
			String sql_company="select denglu_code from enterprise_library where status=1 and  jl_company_uid=(select jl_company_uid from person_library where person_library_uid="+uid+")";
			String[][] ids = DBUtil.query(conn ,sql_company);
			if(ids!=null){
				 s = ids[0][0];
		         //企业登录code 如：WNDSG00001
			}
			//截取登录code 如：WND00025S002从第10个开始去三个“002”
			String sql = "select max(to_number(substr(t.denglu_code,10,3))) as code from person_library t where t.denglu_code like '"+s+"%' order by code desc";//like WNDSG00001R%
			//List list = DBUtil.queryReturnList(conn, sql);
			String[][] res = DBUtil.query(conn ,sql);
			if (res!=null&&StringUtils.isNotBlank(res[0][0])) {
				String code1 = Integer.parseInt(res[0][0]) + 1 + "";
				code = code1;
				for (int i = 0; i < 3-code1.length(); i++) {
					code = "0" +code;
				}
				
			}else{
					code="001";
			}
			code = s +zhuce_type+code;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return code;
	}
	
	
	public String updatePass(String u_id,String js) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        PersonLibraryVO vo = new PersonLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        int z_id=Integer.parseInt(js);
        try {
        	
        	conn.setAutoCommit(false);
            String new_id="";
            String s="";

            String sql_find = " select person_library_uid from PERSON_LIBRARY where jl_person_uid = '"+u_id+"' and STATUS=1";
			String[][] res = DBUtil.query(conn, sql_find);
			if(res!=null){
				 s = res[0][0];
		         String sql_update="update PERSON_LIBRARY set STATUS=5  where person_library_uid="+s;
		         DBUtil.exec(sql_update);
			}
            new_id = DBUtil.getSequenceValue("PERSON_LIBRARY_UID");
			//复制10状态的数据到1该人员的信息
			String copy_sql = " insert into PERSON_LIBRARY (PERSON_LIBRARY_UID, ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE) "
							+" select "+new_id+", ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, JL_PERSON_UID, JL_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION,1,UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE FROM PERSON_LIBRARY" 
							+" where PERSON_LIBRARY_UID ="+z_id;
					
			DBUtil.exec(copy_sql);

			//复制附件信息
			String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
							+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
							+" where TARGET_UID ="+z_id+" AND BUSINESS_SUB_TYPE='PERSON_LIBRARY'";//(select sg_person_library_uid from sg_person_library where status = 1 and sg_person_uid = "+u_id+") ";
				
			DBUtil.exec(copy_file);
				    
			
            //对在10状态的子表的数据进行复制和新增
    		String sql = "select JL_PERSON_ZHENGSHU_UID from JL_PERSON_ZHENGSHU where JL_PERSON_UID = "+js;//(select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where SG_PERSON_UID='"+u_id+"' and status=10)";//(select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where status = 3 and sg_person_uid = "+u_id+")";
			String[][] results = DBUtil.query(conn,sql);
			if(results==null){
				return null;
			};
			

			for (int i = 0; i < results.length; i++) {
				
				
				String new_idZS = DBUtil.getSequenceValue("JL_PERSON_ZHENGSHU_UID");
				String copy_zhengshu_sql = "insert into JL_PERSON_ZHENGSHU (JL_PERSON_ZHENGSHU_UID, JL_PERSON_UID,JL_ZIZHI_UID,JL_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE) "
						+ "select "+new_idZS+", "+new_id+", JL_ZIZHI_UID,JL_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE from JL_PERSON_ZHENGSHU where JL_PERSON_ZHENGSHU_UID = "+results[i][0];
				DBUtil.exec(conn, copy_zhengshu_sql);
				String copy_zhengshu_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_idZS+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+results[i][0]+" AND BUSINESS_SUB_TYPE='JL_PERSON_ZHENGSHU'";
				DBUtil.exec(copy_zhengshu_file);
			}
            resultVO = vo.getRowJson();

            conn.commit();
                
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}
	
	
	public String queryCodeIsEmpty(String json) throws Exception {
        Connection conn = DBUtil.getConnection();
        JSONArray array = new JSONArray();
        try {
        	
			String sql = " select count(*) rs,jl_person_uid from PERSON_LIBRARY where STATUS <> 1 AND SHENFENZHENG = '"+json+"' group by jl_Person_Uid";
	
	        String[][] res = DBUtil.query(conn, sql);
			if(res!=null){
				for (int i = 0; i < res.length; i++) {
					JSONObject object = new JSONObject();
					object.put("rs", res[i][0]);
					object.put("SG_PERSON_UID", res[i][1]);
					array.add(object);
				}
			}
		 } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return array.toString();
	}
	
	//查询数据库中1状态是否有该记录
	public String queryStatusIsEmpty(String json,String bz,String personUID) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        JSONArray array = new JSONArray();
        String sql="";
        try {
        	if(StringUtils.isNotBlank(personUID)){
        	 sql = " select count(*) rs,jl_person_uid from PERSON_LIBRARY where STATUS=1 AND JL_COMPANY_UID= '"+user.getIdCard()+"' AND SHENFENZHENG = '"+json+"' AND JL_PERSON_UID <> "+personUID+" group by JL_Person_Uid";
        	}
        	else{
			 sql = " select count(*) rs,jl_person_uid from PERSON_LIBRARY where STATUS=1 AND JL_COMPANY_UID= '"+user.getIdCard()+"' AND SHENFENZHENG = '"+json+"' group by JL_Person_Uid";
        	}
	        String[][] res = DBUtil.query(conn, sql);
			if(res!=null){
				if(Integer.parseInt(res[0][0])>0){
					return "yes";
				}
			}
			else{
				return "";
			}
			
		 } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);	 
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return array.toString();
	
	
	}


	public String tuihui(String ids, String yijian) throws Exception {
		Connection conn = DBUtil.getConnection();
		String new_id="";
		String[] arr = ids.split(",");
		
		String shenheRenId=ActionContext.getCurrentUserInThread().getUserSN();
		try {
			for (int i = 0; i < arr.length; i++) {
				String sql_update="update PERSON_LIBRARY set SHENHE_JIEGUO=20, SHENHE_DATE=sysdate ,SHENHE_REN='"+shenheRenId+"' ,SHENHE_YIJIAN='"+yijian+"' where PERSON_LIBRARY_UID="+arr[i];
				DBUtil.exec(sql_update);
				
				 new_id = DBUtil.getSequenceValue("PERSON_LIBRARY_UID");
				//复制该人员的信息
   				 StringBuffer sql = new StringBuffer();
				 sql.append(" insert into PERSON_LIBRARY (PERSON_LIBRARY_UID, JL_PERSON_UID,JL_COMPANY_UID,DENGLU_CODE, ");
				 sql.append(" PWD,MIMA,PERSON_NAME,SEX,BIRTHDAY, ");
				 sql.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
				 sql.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
				 sql.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
				 sql.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
				 sql.append(" DESCRIPTION,STATUS,UPDATED_DATE, ");
				 sql.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
				 sql.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
				 sql.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
				 sql.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE) ");
				 sql.append(" select "+new_id+",");
				 sql.append(" JL_PERSON_UID,JL_COMPANY_UID,DENGLU_CODE, ");
				 sql.append(" PWD,MIMA,PERSON_NAME,SEX,BIRTHDAY, ");
				 sql.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
				 sql.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
				 sql.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
				 sql.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
				 sql.append(" DESCRIPTION,20,UPDATED_DATE, ");
				 sql.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
				 sql.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
				 sql.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
				 sql.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE FROM PERSON_LIBRARY ");
				 sql.append(" where person_library_uid ="+arr[i]+"  and status=30");
				DBUtil.exec(sql.toString());
	
				//复制附件信息
				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
					+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+new_id+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
					+" where ywid = '"+arr[i]+"'  and glid4 = 'jl' and ywlx = '999003' ";
				System.out.println("copy_file==="+copy_file);
				DBUtil.exec(copy_file);
				//复制证书信息
				String copy_zs = "insert into jl_person_zhengshu (jl_person_zhengshu_uid, person_library_uid, zhuce_code, valid_date, fazheng_date, zhuce_name) "
							 +" select jl_person_zhengshu_uid.nextval, '"+new_id+"', zhuce_code, valid_date, fazheng_date, zhuce_name from jl_person_zhengshu "
							 +" where person_library_uid = '"+arr[i]+"'";
				System.out.println("copy_zs==="+copy_zs);
				DBUtil.exec(copy_zs);
			
				
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


	public String update(String msg, String jg) throws Exception {
        String resultVO = null;
        PersonLibraryVO vo = new PersonLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        try {
            JSONArray list = vo.doInitJson(msg);
            JSONObject obj= (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            
            //保存审核结果及意见
            	if("10".equals(jg)){
            		
        			//if(vo.getDenglu_code()==null||"".equals(vo.getDenglu_code())){
        			//	vo.setDenglu_code(this.getDengluCode(DBUtil.getConnection(), vo.getPerson_library_uid(), vo.getZhuce_type()));
        			//}
        			//this.update(vo);
            		String pluid = isStatusOne(vo.getJl_person_uid());
            		//如果存在 状态为1的则说明是修改 没有则说明是申请
            		if(!"0".equals(pluid)){

            			vo.setStatus("10");
            			vo.setShenhe_date(new Date());
            			vo.setShenhe_jieguo(jg);
            			vo.setShenhe_ren(user.getUserSN());
            			this.update(vo);
	    				//String new_id2 = DBUtil.getSequenceValue("PERSON_LIBRARY_UID");
	    				//复制该人员的信息
		   				 StringBuffer sql2 = new StringBuffer();
						 sql2.append(" update PERSON_LIBRARY set(");
						 sql2.append(" PERSON_NAME,SEX,BIRTHDAY, ");
						 sql2.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
						 sql2.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
						 sql2.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
						 sql2.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
						 sql2.append(" DESCRIPTION,UPDATED_DATE, ");
						 sql2.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
						 sql2.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
						 sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
						 sql2.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE) ");
						 sql2.append(" =(select ");
						 sql2.append(" PERSON_NAME,SEX,BIRTHDAY, ");
						 sql2.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
						 sql2.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
						 sql2.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
						 sql2.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
						 sql2.append(" DESCRIPTION,UPDATED_DATE, ");
						 sql2.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
						 sql2.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
						 sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
						 sql2.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE FROM PERSON_LIBRARY ");
						 sql2.append(" where person_library_uid ="+vo.getPerson_library_uid()+"  and status='10')");
						 sql2.append(" where status = '1' and JL_PERSON_UID ='"+vo.getJl_person_uid()+"'");
	    				DBUtil.exec(sql2.toString());
	    				
	    				
	    				String del_file = "delete from fs_fileupload where ywid = (select e.person_library_uid from person_library e where jl_person_uid = '"+vo.getJl_person_uid()+"' and status = 1) and glid4 = 'jl' and ywlx = '999003'";
        				String del_zeng = "delete from jl_person_zhengshu where enterprise_library_uid = (select e.person_library_uid from person_library e where jl_person_uid = '"+vo.getJl_person_uid()+"' and status = 1)";
        				DBUtil.exec(del_file);
        				DBUtil.exec(del_zeng);
        				//复制附件信息
        				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
        						+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, (select e.enterprise_library_uid from enterprise_library e where jl_company_uid = '"+vo.getJl_company_uid()+"' and status = 1), fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
        						+" where ywid = '"+vo.getPerson_library_uid()+"' and glid4 = 'jl' and ywlx = '999003'";
        				DBUtil.exec(copy_file);
        				
        				//复制证书信息
        				String copy_zs = "insert into jl_person_zhengshu (jl_person_zhengshu_uid, person_library_uid, zhuce_code, valid_date, fazheng_date, zhuce_name) "
        							 +" select jl_person_zhengshu_uid.nextval, (select e.enterprise_library_uid from enterprise_library e where jl_company_uid = '"+vo.getJl_company_uid()+"' and status = 1), zhuce_code, valid_date, fazheng_date, zhuce_name from jl_person_zhengshu "
        							 +" where person_library_uid = '"+vo.getPerson_library_uid()+"'";
        				System.out.println("copy_zs==="+copy_zs);
        				DBUtil.exec(copy_zs);
	    				
            		}else{
            			if(vo.getDenglu_code()==null||"".equals(vo.getDenglu_code())){
            				vo.setDenglu_code(this.getDengluCode(DBUtil.getConnection(), vo.getPerson_library_uid(), vo.getZhuce_type()));
            			}
            			vo.setStatus("10");
            			vo.setShenhe_date(new Date());
            			vo.setShenhe_jieguo(jg);
            			vo.setShenhe_ren(user.getUserSN());
            			this.update(vo);


            			
        				String new_id2 = DBUtil.getSequenceValue("PERSON_LIBRARY_UID");
        				//复制该人员的信息
    	   				 StringBuffer sql2 = new StringBuffer();
    					 sql2.append(" insert into PERSON_LIBRARY (PERSON_LIBRARY_UID, JL_PERSON_UID,JL_COMPANY_UID,DENGLU_CODE, ");
    					 sql2.append(" PWD,MIMA,PERSON_NAME,SEX,BIRTHDAY, ");
    					 sql2.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
    					 sql2.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
    					 sql2.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
    					 sql2.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
    					 sql2.append(" DESCRIPTION,STATUS,UPDATED_DATE, ");
    					 sql2.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
    					 sql2.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
    					 sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
    					 sql2.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE) ");
    					 sql2.append(" select "+new_id2+",");
    					 sql2.append(" JL_PERSON_UID,JL_COMPANY_UID,DENGLU_CODE, ");
    					 sql2.append(" PWD,MIMA,PERSON_NAME,SEX,BIRTHDAY, ");
    					 sql2.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
    					 sql2.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
    					 sql2.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
    					 sql2.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
    					 sql2.append(" DESCRIPTION,1,UPDATED_DATE, ");
    					 sql2.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
    					 sql2.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
    					 sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
    					 sql2.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE FROM PERSON_LIBRARY ");
    					 sql2.append(" where person_library_uid ="+vo.getPerson_library_uid()+"  and status=10");
        				DBUtil.exec(sql2.toString());
        				//复制附件信息
        				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
        					+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+new_id2+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
        					+" where ywid = '"+vo.getPerson_library_uid()+"'  and glid4 = 'jl' and ywlx = '999003' ";
        				System.out.println("copy_file==="+copy_file);
        				DBUtil.exec(copy_file);
        				//复制证书信息
        				String copy_zs = "insert into jl_person_zhengshu (jl_person_zhengshu_uid, person_library_uid, zhuce_code, valid_date, fazheng_date, zhuce_name) "
        							 +" select jl_person_zhengshu_uid.nextval, '"+new_id2+"', zhuce_code, valid_date, fazheng_date, zhuce_name from jl_person_zhengshu "
        							 +" where person_library_uid = '"+vo.getPerson_library_uid()+"'";
        				System.out.println("copy_zs==="+copy_zs);
        				DBUtil.exec(copy_zs);
        			
            		}
            	}else{
/*    				String new_id3 = DBUtil.getSequenceValue("PERSON_LIBRARY_UID");
	   				 StringBuffer sql3 = new StringBuffer();
					 sql3.append(" insert into PERSON_LIBRARY (PERSON_LIBRARY_UID, JL_PERSON_UID,JL_COMPANY_UID,DENGLU_CODE, ");
					 sql3.append(" PWD,MIMA,PERSON_NAME,SEX,BIRTHDAY, ");
					 sql3.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
					 sql3.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
					 sql3.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
					 sql3.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
					 sql3.append(" DESCRIPTION,STATUS,UPDATED_DATE, ");
					 sql3.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
					 sql3.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
					 sql3.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
					 sql3.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE) ");
					 sql3.append(" select "+new_id3+",");
					 sql3.append(" JL_PERSON_UID,JL_COMPANY_UID,DENGLU_CODE, ");
					 sql3.append(" PWD,MIMA,PERSON_NAME,SEX,BIRTHDAY, ");
					 sql3.append(" SHENFENZHENG,PHOTO,NATION,PHONE, ");
					 sql3.append(" EMAIL,XUELI,COLLEGE,ZHUANYE1, ");
					 sql3.append(" ZHUANYE2,ZHICHENG_UID,ZHICHENG_CODE, ");
					 sql3.append(" ZHUCE_TYPE,ZHUCE_CODE,VALID_DATE,JISHU_Y, ");
					 sql3.append(" DESCRIPTION,20,UPDATED_DATE, ");
					 sql3.append(" TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO, ");
					 sql3.append(" SHENHE_YIJIAN,SHENHE_DATE, ");
					 sql3.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,FZR_Y, ");
					 sql3.append(" BEGIN_DATE,END_DATE,FAZHENG_DATE,JZ_DATE,SCORE FROM PERSON_LIBRARY ");
					 sql3.append(" where person_library_uid ="+vo.getPerson_library_uid()+"  and status='30'");
    				DBUtil.exec(sql3.toString());
    				//复制附件信息
    				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
    					+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+new_id3+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
    					+" where ywid = '"+vo.getPerson_library_uid()+"'  and glid4 = 'jl' and ywlx = '999003' ";
    				System.out.println("copy_file==="+copy_file);
    				DBUtil.exec(copy_file);
    				//复制证书信息
    				String copy_zs = "insert into jl_person_zhengshu (jl_person_zhengshu_uid, person_library_uid, zhuce_code, valid_date, fazheng_date, zhuce_name) "
    							 +" select jl_person_zhengshu_uid.nextval, '"+new_id3+"', zhuce_code, valid_date, fazheng_date, zhuce_name from jl_person_zhengshu "
    							 +" where person_library_uid = '"+vo.getPerson_library_uid()+"'";
    				System.out.println("copy_zs==="+copy_zs);
    				DBUtil.exec(copy_zs);*/
        			vo.setStatus("20");
        			vo.setShenhe_date(new Date());
        			vo.setShenhe_jieguo(jg);
        			vo.setShenhe_ren(user.getUserSN());
        			this.update(vo);
            	}
            
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
        	e.printStackTrace();
        } finally {
        	
        }
        return resultVO;
	}


	public void updateDshxx(Map<String, String> map) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        try {

           String sql = "update person_library t set t.shenhe_ren = ?,t.shenhe_jieguo=?,t.shenhe_yijian=?,t.shenhe_date=to_date(?,'yyyy-mm-dd') where t.person_library_uid = ?";
            
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
	
	
	public static String isStatusOne(String jl_person_uid){
		//boolean flag = false;
		String pluid = "0";
		Connection conn = DBUtil.getConnection();
		String sql = " select * from person_library where jl_person_uid ='"+jl_person_uid+"' and status = '1'";
		try {
			List<?> list = DBUtil.queryReturnList(conn, sql);
			if(list.size()>0){
				Map map = (Map) list.get(0);
				pluid = (String) map.get("PERSON_LIBRARY_UID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            DBUtil.closeConnetion(conn);
        }
		return pluid;
		
	}
	
	
	public String suoding(String id, String yijian,String sjfw,String jz_y) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
		String sql_add="update PERSON_LIBRARY set JZ_Y='"+jz_y+"' ,JZ_YUANYIN='"+yijian+"' ,JZ_SJFW='"+sjfw+"' ,JZ_DATE=sysdate where person_library_uid="+id;
		DBUtil.exec(sql_add);
		conn.commit();
		
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
	public String jiesuo(String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
		String sql_add="update PERSON_LIBRARY set JZ_Y='' where person_library_uid="+id;
		DBUtil.exec(sql_add);
		conn.commit();
		
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

}
