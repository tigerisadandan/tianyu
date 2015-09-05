package com.ccthanking.business.jl.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.common.vo.JldwUserVO;
import com.ccthanking.business.dtgl.jl.vo.EnterpriseLibraryShjlVO;
import com.ccthanking.business.dtgl.jl.vo.EnterpriseLibraryVO;
import com.ccthanking.business.jl.dao.JlEnterpriseDao;
import com.ccthanking.business.jl.service.JlEnterpriseService;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.FjlbManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

@Service
public class JlEnterpriseServiceImpl extends Base1ServiceImpl<EnterpriseLibraryVO, String> implements JlEnterpriseService {
	private static Logger logger = LoggerFactory.getLogger(JlEnterpriseServiceImpl.class);

	
	private JlEnterpriseDao jlEnterpriseDao;
	
	@Autowired
    private FsMessageInfoService fsMessageInfoService;
	
	@Autowired
	@Qualifier("jlEnterpriseDaoImpl")
	public void setJlEnterpriseDao(JlEnterpriseDao jlEnterpriseDao) {
		this.jlEnterpriseDao = jlEnterpriseDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) jlEnterpriseDao);
	}

	public String delete(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String insert(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String queryCondition(String json) throws Exception {
		// TODO Auto-generated method stub
		return this.jlEnterpriseDao.queryCondition(json);
	}

	public String queryOldCondition(String json) throws Exception {
		// TODO Auto-generated method stub
		return this.jlEnterpriseDao.queryOldCondition(json);
	}

	public String queryspyjCondition(String json) throws Exception {
		// TODO Auto-generated method stub
		return this.jlEnterpriseDao.queryspyjCondition(json);
	}

	public String update(String json,String jg) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        EnterpriseLibraryVO vo = new EnterpriseLibraryVO();
        Connection conn = DBUtil.getConnection();
        try {
        	
        	conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj= (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setCompany_type((String)obj.get("COMPANY_TYPE_SV"));
           // String ENTERPRISE_LIBRARY_UID=(String)obj.get("ENTERPRISE_LIBRARY_UID");
//            vo=this.findById(LH_ENTERPRISE_UID);

           // String SPXZZT=(String)obj.get("SPXZZT");
            //String SHENHE_YIJIAN=(String)obj.get("SHENHE_YIJIAN");
            //保存审核结果及意见
          
            	EnterpriseLibraryShjlVO spjlvo=new EnterpriseLibraryShjlVO();
            	if("10".equals(jg)){
            		
        			//vo.setStatus("10");//审核通过
        			/*if(vo.getDenglu_code()==null||"".equals(vo.getDenglu_code())){
        				vo.setDenglu_code(this.jlEnterpriseDao.getDengluCode());
        			}*/
        			
        			//this.jlEnterpriseDao.update(vo);
        			//vo.setStatus("1");
            		String eluid = isStatusOne(vo.getJl_company_uid());
            		if(!"0".equals(eluid)){
            			vo.setStatus("10");
            			vo.setShenhe_date(new Date());
            			vo.setShenhe_jieguo(jg);
            			vo.setShenhe_ren(user.getUserSN());
            			//vo.setShenhe_yijian(shenhe_yijian)
        				this.jlEnterpriseDao.update(vo);
        				//String new_id2 = DBUtil.getSequenceValue("ENTERPRISE_LIBRARY_UID");       				
        				StringBuffer sql2 = new StringBuffer();
        				sql2.append(" update ENTERPRISE_LIBRARY set ( ");
        				sql2.append(" COMPANY_CODE,COMPANY_NAME, ");
        				sql2.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
        				sql2.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
        				sql2.append(" PHONE,FAX,URL,FAREN, ");
        				sql2.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION, ");
        				sql2.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
        				sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE) ");
        				sql2.append(" =(SELECT COMPANY_CODE,COMPANY_NAME, ");
        				sql2.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
        				sql2.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
        				sql2.append(" PHONE,FAX,URL,FAREN, ");
        				sql2.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION, ");
        				sql2.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
        				sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE FROM ENTERPRISE_LIBRARY ");
        				sql2.append(" WHERE ENTERPRISE_LIBRARY_UID ="+vo.getEnterprise_library_uid()+"  AND status='10' )");
        				sql2.append(" where status = '1' and jl_company_uid ='"+vo.getJl_company_uid()+"'");
       				
            			//String sql_update = " update ENTERPRISE_LIBRARY set (company_code, company_name, company_type, waidi_y, zhizhao, shuiwu, zhengshu_code, bank, bank_account, zhu_zizhi, zhu_dengji, zen_zizhi, zen_dengji, zizhi_date, address, postcode, phone, fax, url, faren, faren_zhicheng, faren_mobile, lianxiren, lianxiren_mobile, lianxiren_mail, description, status, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, valid_date, jz_y, jz_yuanyin, jz_sjfw, zhizhao_valid, yuyue_date, tags, score)  "
            			//	 +" = (select company_code, company_name, company_type, waidi_y, zhizhao, shuiwu, zhengshu_code, bank, bank_account, zhu_zizhi, zhu_dengji, zen_zizhi, zen_dengji, zizhi_date, address, postcode, phone, fax, url, faren, faren_zhicheng, faren_mobile, lianxiren, lianxiren_mobile, lianxiren_mail, description, 10, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, valid_date, jz_y, jz_yuanyin, jz_sjfw, zhizhao_valid, yuyue_date, tags, score from enterprise_library where ENTERPRISE_LIBRARY_UID = '"+vo.getEnterprise_library_uid()+"' AND status='15' )  "
            			//	 +" where ENTERPRISE_LIBRARY_UID = '"+vo.getEnterprise_library_uid()+"' AND status='10' ";
            			
        				DBUtil.exec(sql2.toString());
        				
        				String del_file = "delete from fs_fileupload where ywid = to_char((select e.enterprise_library_uid from enterprise_library e where jl_company_uid = '"+vo.getJl_company_uid()+"' and status = 1)) and glid4 = 'jl' and ywlx = '999002'";
        				String del_zeng = "delete from enterprise_zen_zizhi where enterprise_library_uid = (select e.enterprise_library_uid from enterprise_library e where jl_company_uid = '"+vo.getJl_company_uid()+"' and status = 1)";
        				DBUtil.exec(del_file);
        				DBUtil.exec(del_zeng);
        				//复制附件信息
        				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
        						+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, (select e.enterprise_library_uid from enterprise_library e where jl_company_uid = '"+vo.getJl_company_uid()+"' and status = 1), fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
        						+" where ywid = '"+vo.getEnterprise_library_uid()+"' and glid4 = 'jl' and ywlx = '999002'";
        				DBUtil.exec(conn ,copy_file);
        				
        				//复制增项资质
        				String copy_zeng = "insert into enterprise_zen_zizhi (enterprise_zen_zizhi_uid, enterprise_library_uid, jl_company_uid, zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no)"
        								+ " select enterprise_zen_zizhi_uid.nextval, (select e.enterprise_library_uid from enterprise_library e where jl_company_uid = '"+vo.getJl_company_uid()+"' and status = 1), '', zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no from enterprise_zen_zizhi"
        								+ " where enterprise_library_uid = '"+vo.getEnterprise_library_uid()+"'  ";
        				DBUtil.exec(conn ,copy_zeng);
            			

            		}else{
            			if(vo.getDenglu_code()==null||"".equals(vo.getDenglu_code())){
            				vo.setDenglu_code(this.jlEnterpriseDao.getDengluCode());
            			}
            			vo.setStatus("10");
            			vo.setShenhe_date(new Date());
            			vo.setShenhe_jieguo(jg);
            			vo.setShenhe_ren(user.getUserSN());
            			this.jlEnterpriseDao.update(vo);

        				String new_id2 = DBUtil.getSequenceValue("ENTERPRISE_LIBRARY_UID");
        				
        				StringBuffer sql2 = new StringBuffer();
        				sql2.append(" INSERT INTO ENTERPRISE_LIBRARY (ENTERPRISE_LIBRARY_UID, ");
        				sql2.append(" JL_COMPANY_UID,DENGLU_CODE,PWD,MIMA,COMPANY_CODE,COMPANY_NAME, ");
        				sql2.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
        				sql2.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
        				sql2.append(" PHONE,FAX,URL,FAREN, ");
        				sql2.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION,STATUS, ");
        				sql2.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
        				sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE) ");
        				sql2.append(" SELECT "+new_id2+" ,JL_COMPANY_UID,DENGLU_CODE,PWD,MIMA,COMPANY_CODE,COMPANY_NAME, ");
        				sql2.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
        				sql2.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
        				sql2.append(" PHONE,FAX,URL,FAREN, ");
        				sql2.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION,1, ");
        				sql2.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
        				sql2.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE FROM ENTERPRISE_LIBRARY ");
        				sql2.append(" WHERE ENTERPRISE_LIBRARY_UID ="+vo.getEnterprise_library_uid()+"  AND status='10' ");
        				
        				DBUtil.exec(sql2.toString());

        				
        				//复制附件信息
        				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
        						+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+new_id2+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
        						+" where ywid = '"+vo.getEnterprise_library_uid()+"' and glid4 = 'jl' and ywlx = '999002'";
        				DBUtil.exec(conn ,copy_file);
        				
        				//复制增项资质
        				String copy_zeng = "insert into enterprise_zen_zizhi (enterprise_zen_zizhi_uid, enterprise_library_uid, jl_company_uid, zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no)"
        								+ " select enterprise_zen_zizhi_uid.nextval, '"+new_id2+"', '', zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no from enterprise_zen_zizhi"
        								+ " where enterprise_library_uid = '"+vo.getEnterprise_library_uid()+"'  ";
        				DBUtil.exec(conn ,copy_zeng);
        				
        				//审核通过 自动添加默认用户至 JLDW_USER
        				EnterpriseLibraryVO jlqyvo=this.findById(new_id2); 
        				String sql = "select * from jldw_User where jl_company_uid ="+jlqyvo.getJl_company_uid()+" and admin_y=1 ";
        			    String [][]res = DBUtil.query(sql);
        			       if(res==null){ //如有管理员 无需再默认添加
        			    		JldwUserVO jlvo=new JldwUserVO();
                			    jlvo.setJldw_user_uid(DBUtil.getSequenceValue("JLDW_USER_UID"));
                			    jlvo.setJl_company_uid(jlqyvo.getJl_company_uid());
                			    jlvo.setUser_code(jlqyvo.getDenglu_code());
                			    jlvo.setUser_name("管理员");
                			    jlvo.setPwd(jlqyvo.getPwd());
                			    jlvo.setMima(jlqyvo.getMima());
                			    jlvo.setAdmin_y("1");
                			    jlvo.setCreated_date(new Date());
                			    jlEnterpriseDao.save(jlvo);
        			       }
            		}

        			//vo.setShenhe_jieguo("10");
        			spjlvo.setShjg("10");
            	}else{
            		//vo.setStatus("20");
/*            		String new_id3 = DBUtil.getSequenceValue("ENTERPRISE_LIBRARY_UID");
    				StringBuffer sql = new StringBuffer();
    				sql.append(" INSERT INTO ENTERPRISE_LIBRARY (ENTERPRISE_LIBRARY_UID, ");
    				sql.append(" JL_COMPANY_UID,DENGLU_CODE,PWD,MIMA,COMPANY_CODE,COMPANY_NAME, ");
    				sql.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
    				sql.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
    				sql.append(" PHONE,FAX,URL,FAREN, ");
    				sql.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION,STATUS, ");
    				sql.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
    				sql.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE) ");
    				sql.append(" SELECT "+new_id3+" ,JL_COMPANY_UID,DENGLU_CODE,PWD,MIMA,COMPANY_CODE,COMPANY_NAME, ");
    				sql.append(" COMPANY_TYPE,WAIDI_Y,ZHIZHAO,SHUIWU,ZHENGSHU_CODE,BANK,BANK_ACCOUNT, ");
    				sql.append(" ZHU_ZIZHI,ZHU_DENGJI,ZEN_ZIZHI,ZEN_DENGJI,ZIZHI_DATE,ADDRESS,POSTCODE, ");
    				sql.append(" PHONE,FAX,URL,FAREN, ");
    				sql.append(" FAREN_ZHICHENG,FAREN_MOBILE,LIANXIREN,LIANXIREN_MOBILE,LIANXIREN_MAIL,DESCRIPTION,20, ");
    				sql.append(" UPDATED_DATE,TIJIAO_DATE,SHENHE_REN,SHENHE_JIEGUO,SHENHE_YIJIAN,SHENHE_DATE,VALID_DATE, ");
    				sql.append(" JZ_Y,JZ_YUANYIN,JZ_SJFW,ZHIZHAO_VALID,YUYUE_DATE,TAGS,SCORE FROM ENTERPRISE_LIBRARY ");
    				sql.append(" WHERE ENTERPRISE_LIBRARY_UID ="+new_id3+"  AND status='30' ");
    				
    				DBUtil.exec(sql.toString());*/
        			vo.setStatus("20");
        			vo.setShenhe_date(new Date());
        			vo.setShenhe_jieguo(jg);
        			vo.setShenhe_ren(user.getUserSN());
        			this.jlEnterpriseDao.update(vo);
/*    				//复制附件信息
    				String copy_file = "insert into fs_fileupload (fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, ywid, fjzt, glid1, glid2, glid3, glid4, fjlb ) "
    						+" select fileid, filename, url, zhux, bz, lrr, lrbm, lrsj, gxr, gxbm, gxsj, sjbh, ywlx, fjlx, filesize, '"+new_id3+"', fjzt, glid1, glid2, glid3, glid4, fjlb from fs_fileupload  "
    						+" where ywid = '"+vo.getEnterprise_library_uid()+"' and glid4 = 'jl' and ywlx = '999002'";
    				DBUtil.exec(conn ,copy_file);
    				
    				//复制增项资质
    				String copy_zeng = "insert into enterprise_zen_zizhi (enterprise_zen_zizhi_uid, enterprise_library_uid, jl_company_uid, zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no)"
    								+ " select enterprise_zen_zizhi_uid.nextval, '"+new_id3+"', '', zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no from enterprise_zen_zizhi"
    								+ " where enterprise_library_uid = '"+vo.getEnterprise_library_uid()+"'  ";
    				DBUtil.exec(conn ,copy_zeng);*/
    	
            		//vo.setShenhe_jieguo("20");
            		spjlvo.setShjg("20");
            	}
            	spjlvo.setEnterprise_library_uid(vo.getEnterprise_library_uid());
            	spjlvo.setShrq(new Date());
            	spjlvo.setShyj(vo.getShenhe_yijian());
            	spjlvo.setShr(user.getAccount());
            	spjlvo.setCreated_date(new Date());
            	spjlvo.setCreated_name(user.getName());
            	spjlvo.setCreated_uid(user.getUserSN());
            	this.jlEnterpriseDao.save(spjlvo);
            
            // 修改
            
            resultVO = vo.getRowJson();

            conn.commit();
        } catch (DaoException e) {
        	e.printStackTrace();
        	conn.rollback();
            logger.error("监理企业信息库{}", e.getMessage());
            SystemException.handleMessageException("监理企业信息库修改失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;
	}
	
	
	public static String isStatusOne(String jl_company_uid){
		String eluid = "0";
		Connection conn = DBUtil.getConnection();
		String sql = " select * from enterprise_library where jl_company_uid = '"+jl_company_uid+"' and status = '1'";
		try {
			List<?> list = DBUtil.queryReturnList(conn, sql);
			if(list.size()>0){
				Map map = (Map) list.get(0);
				eluid = (String) map.get("ENTERPRISE_LIBRARY_UID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            DBUtil.closeConnetion(conn);
        }
		return eluid;
		
	}

	public String tuihui(String ids, String yijian) throws Exception {
		
		return this.jlEnterpriseDao.tuihui(ids,yijian);
	}
	
	public void updateDshxx(Map map) throws Exception {
		
		this.jlEnterpriseDao.updateDshxx(map);
		
		 String jg = (String) map.get("jg");
		 String qyId =(String) map.get("qyId");
		 String qyName =(String) map.get("qyName");
		 String qyCode =(String) map.get("qyCode");
		 
		 if("10".equals(jg)){//人员审核通过
	    	 //****************************消息添加
		         Map messageMap= new HashMap();
		         messageMap.put("TITLE", "企业信息审核");
		         messageMap.put("CONTENT", "<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"qyglView()\">企业信息</a><span&nbsp;style='color:blue;'>审核通过</span>");
		         messageMap.put("USERTO", qyCode);//企业的登录Code
		         messageMap.put("USERTONAME", qyName);//企业的名称
		         messageMap.put("SYSTEM_TYPE","JL");//系统类型
		         messageMap.put("COMPANY_UID", qyId);//对应的施工企业编号
		         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_JLQYXX);//消息类型
		         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_JL_QYGL);//涉及的权限点
		         
		         fsMessageInfoService.insertVo(messageMap);
		}else if("20".equals(jg)){//人员审核不通过
			 //****************************消息添加
			Map messageMap= new HashMap();
	         messageMap.put("TITLE", "企业信息审核");
	         messageMap.put("CONTENT", "<a&nbsp;href='javascript:void(0)'&nbsp;onclick=\"qyglView()\">企业信息</a><span&nbsp;style='color:red;'>审核不通过</span>");
	         messageMap.put("USERTO", qyCode);//企业的登录Code
	         messageMap.put("USERTONAME", qyName);//企业的名称
	         messageMap.put("SYSTEM_TYPE","JL");//系统类型
	         messageMap.put("COMPANY_UID", qyId);//对应的施工企业编号
	         messageMap.put("MSG_TYPE",Constants.FS_MESSAGE_INFO_MSG_TYPE_JLQYXX);//消息类型
	         messageMap.put("QUANXIAN_UID",Constants.QUANXIAN_JL_QYGL);//涉及的权限点
	         
	         fsMessageInfoService.insertVo(messageMap);
		}
	}

	public String updateEnterprise(String msg) {
		EnterpriseLibraryVO vo = new EnterpriseLibraryVO();
		String resultVO = null;
		try {
			
			JSONArray list = vo.doInitJson(msg);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            String ywid=(String)obj.get("ywid");
            this.jlEnterpriseDao.update(vo);
            if(vo!=null&&vo.getEnterprise_library_uid()!=null&&!"".equals(vo.getEnterprise_library_uid())){
             	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getEnterprise_library_uid(),FjlbManager.JL_YYZZ_FJLB);
             	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getEnterprise_library_uid(),FjlbManager.JL_SWDJZ_FJLB);
             	 
             	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getEnterprise_library_uid(),FjlbManager.JL_KHXKZ_FJLB);
             	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getEnterprise_library_uid(),FjlbManager.JL_ZZZS_FJLB);
             	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getEnterprise_library_uid(),FjlbManager.JL_ZZJG_FJLB);
             }
            resultVO = vo.getRowJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	public String updateCopyByStatus(Map map) {
		// TODO Auto-generated method stub
		return this.jlEnterpriseDao.updateCopyByStatus(map);
	}

}
