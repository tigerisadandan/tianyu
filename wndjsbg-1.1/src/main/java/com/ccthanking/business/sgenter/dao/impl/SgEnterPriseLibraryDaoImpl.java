/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgenter.service.SgEnterPriseLibraryService.java
 * 创建日期： 2014-04-09 上午 10:51:19
 * 功能：    接口：业务页面模版表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-09 上午 10:51:19  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgenter.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.commons.Utils;
import com.ccthanking.business.sgenter.dao.SgEnterPriseLibraryDao;
import com.ccthanking.business.sgenter.vo.AtFileuploadVO;
import com.ccthanking.business.sgenter.vo.SgECreditCommendRewardVO;
import com.ccthanking.business.sgenter.vo.SgECreditProjectsVO;
import com.ccthanking.business.sgenter.vo.SgEnterpriseLibraryVO;
import com.ccthanking.business.sgenter.vo.SgEnterpriseZenZizhiVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.QuerySet;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.util.DateTimeUtil;
import com.ccthanking.framework.util.Encipher;
import com.ccthanking.framework.util.RequestUtil;



/**
 * <p> SgEnterPriseLibraryService.java </p>
 * <p> 功能：业务页面模版表 </p>
 *
 * <p><a href="SgEnterPriseLibraryService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-09
 * 
 */

@Component
public class SgEnterPriseLibraryDaoImpl extends BsBaseDaoTJdbc implements SgEnterPriseLibraryDao {

	private static Logger logger = LoggerFactory.getLogger(SgEnterPriseLibraryDaoImpl.class);
	
	private static String SQL_QUERY_CONDITION = "select n.*,v.co,u.user_name,d.sg_zizhi_dengji_uid,d.dengji_name,d.dengji_nums,e.zizhi_name,e.zizhi_type,e.zhuanye_name,"
//				+" (select WM_CONCAT(v.zizhi_name||'-'||v.dengji_name) from V_sg_zen_zizhi_LIST v where v.sg_enterprise_library_uid = n.sg_enterprise_library_uid) zeng_zizhi,"
				+" (select yy.yxcbs_uid from yx_yxcbs yy where yy.cbs_type='"+Constants.YXCBS_LX_SG+"'" 
				+"  and yy.company_uid=n.SG_COMPANY_UID and  n.status='1' ) as yxcbs_uid "
				+" from sg_enterprise_library n "
				+" left join sg_zizhi_dengji d on n.zhu_dengji = d.sg_zizhi_dengji_uid left join sg_zizhi e on d.sg_zizhi_uid = e.sg_zizhi_uid  "
				+" left join users u on u.users_uid = n.shenhe_ren LEFT JOIN v_sg_e_xyfs v ON v.e_uid=n.sg_enterprise_library_uid  ";
	
	private static String SQL_QUERY_ONSP = "select n.*,d.sg_zizhi_dengji_uid,d.dengji_name,d.dengji_nums,e.zizhi_name,e.zizhi_type,e.zhuanye_name from sg_enterprise_library n "
		+" left join sg_zizhi_dengji d on n.zhu_dengji = d.sg_zizhi_dengji_uid left join sg_zizhi e on d.sg_zizhi_uid = e.sg_zizhi_uid  ";

	
	private static String SQL_QUERY_APPLIST = "select * from ( SELECT * from ( "
				+" SELECT  Rank() over(PARTITION BY t.Sg_Company_Uid ORDER BY t.Created_Date desc) rowno,  t.* FROM SG_ENTERPRISE_LIBRARY t where status = @@STATUS@@ ) WHERE rowno=1) n";
	
	// 业务类型
    private String ywlx = YwlxManager.SG_ENTERPRISE_LIBRARY;

    // @Override
    public String queryCondition(String json,Map map) throws Exception {
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try { 

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            if(map!=null&&"30".equals((String) map.get("ZT"))){
            	condition += " and n.SHENHE_JIEGUO is null ";
            }
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            if(condition.indexOf("n.QUEREN_STATUS != '10'")!=-1){
            	condition = condition.replace("n.QUEREN_STATUS != '10'", "n.QUEREN_STATUS is null AND v.co IS NOT NULL  ");
            }
            
            page.setFilter(condition);

            conn.setAutoCommit(false);
            String sql = SQL_QUERY_CONDITION;
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            
            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");
            bs.setFieldDic("WAIDI_Y", "WAIDI_Y");
            bs.setFieldDic("STATUS", "RYSHZT");
            bs.setFieldDic("SHENHE_JIEGUO", "SPXZZT");
            bs.setFieldDateFormat("TIJIAO_DATE", DateTimeUtil.DEFAULT_DATETIME_FORMAT);
            bs.setFieldDateFormat("SHENHE_DATE", DateTimeUtil.DEFAULT_DATETIME_FORMAT);
            
            
            domresult = bs.getJson();//把转换好的数据给domresult
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("业务页面模版表查询失败!");
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    public String queryAppList(String json,Map map) throws Exception {
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);
            String sql = SQL_QUERY_APPLIST;
            sql = sql.replaceAll("@@STATUS@@",(String) map.get("status"));
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            
            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");
            bs.setFieldDic("WAIDI_Y", "SF");
            
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("业务页面模版表查询失败!");
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
 // @Override
    public String queryApproval(String json) throws Exception {
    
    	Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            condition += " and a.status not in ( 40,30,1) ";
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);
            String sql = " select a.*,p.user_name from sg_enterprise_library a LEFT JOIN USERS p on a.SHENHE_REN = p.users_uid";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("审批记录查询失败!");
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
    }
    
    // @Override
    public String insert(String json,Map fileList) throws Exception {

    	Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgEnterpriseLibraryVO vo = new SgEnterpriseLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
           
            //组织结构代码
            vo.setCompany_code(obj.getString("DAIMA_Z")+"-"+obj.getString("DAIMA_F"));
            
            //若company_UID从页面获取到了值 则不再从sequence获取(说明当前数据操作为修改操作)
            if(StringUtils.isBlank(vo.getSg_company_uid())){
	            vo.setSg_company_uid(DBUtil.getSequenceValue("SG_COMPANY_UID"));
            }
            //操作人员
            user = ActionContext.getCurrentUserInThread();
            
            //未加密密码
            vo.setMima(obj.getString("PWD"));
            //密码加密
            vo.setPwd(DigestUtils.md5Hex(obj.getString("PWD")));
            
            String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
            
            
            //插入用户/企业
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getUserSN());
            vo.setCreated_uid(user.getUserSN());
          	//排序号
            vo.setSerial_no(vo.getSg_enterprise_library_uid());
            
            if("30".equals(vo.getStatus())){
            	vo.setTijiao_date(new Date());
            }
            
            BaseDAO.insert(conn, vo);
            
            insertFiles(fileList, fileLinks, vo.getSg_enterprise_library_uid(), conn);
            
            //增加增项资质
            getZizhis(conn, obj, vo);
            
            //增加奖项
            getJiangxs(conn, obj, vo, fileList);
            
            //增加项目信息
            getXiangms(conn, obj, vo, fileList);
            
            
           
            resultVO = vo.getRowJson();
            
            //附件
//            insertFiles(fileList,vo.getVOTableName(),vo.getSg_enterprise_library_uid());
            
            conn.commit();
            LogManager.writeUserLog(vo.getSg_enterprise_library_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表新增成功", user, "", "");
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            LogManager.writeUserLog(vo.getSg_enterprise_library_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表新增失败", user, "", "");
            e.printStackTrace(System.out);
            logger.error("施工单位表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }
    // @Override
    public String update(String json,Map files) throws Exception {

    	Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgEnterpriseLibraryVO vo = new SgEnterpriseLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            
            if ("10".equals(vo.getStatus())||"20".equals(vo.getStatus())) {
				vo.setShenhe_ren(user.getUserSN());
				vo.setShenhe_date(new Date());
			}
            
            //组织结构代码
            vo.setCompany_code(obj.getString("DAIMA_Z")+"-"+obj.getString("DAIMA_F"));
            
            
            //插入用户/企业
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getUserSN());
            vo.setUpdate_uid(user.getUserSN());
            if("10".equals(vo.getStatus())){
            if(!updateYczxx(conn, vo.getSg_enterprise_library_uid())){
            	vo.setDenglu_code(getDengluCode(conn, vo.getSg_enterprise_library_uid()));
            }
            }
            //增加增项资质
            String zizhiList = getZizhis(conn, obj, vo);
            if(!"".equals(zizhiList)){
            	zizhiList = zizhiList.substring(0,zizhiList.length()-1);
        		//清楚多余页面被删掉的增项资质
        		String clearzizhi_SQL = "delete from sg_enterprise_zen_zizhi t where t.sg_enterprise_library_uid = "+vo.getSg_enterprise_library_uid()+" and t.sg_enterprise_zen_zizhi_uid not in ("+zizhiList+")";
        		DBUtil.exec(conn, clearzizhi_SQL);
        	}else{
        		//删除所有资质
            	String clearzizhi_SQL = "delete from sg_enterprise_zen_zizhi t where t.sg_enterprise_library_uid = "+ vo.getSg_enterprise_library_uid();
        		DBUtil.exec(conn, clearzizhi_SQL);
        	}
            //增加奖项
            String jiangxList = getJiangxs(conn, obj, vo, files);
            if(!"".equals(jiangxList)){
            	jiangxList = jiangxList.substring(0,jiangxList.length()-1);
        		//清楚多余页面被删掉的增项资质
        		String clearzizhi_SQL = "delete from SG_E_CREDIT_COMMEND_REWARD t where t.sg_enterprise_library_uid = "+vo.getSg_enterprise_library_uid()+" and t.CREDIT_COMMEND_REWARD_UID not in ("+jiangxList+")";
        		DBUtil.exec(conn, clearzizhi_SQL);
        	}else{
        		//删除所有资质
            	String clearzizhi_SQL = "delete from SG_E_CREDIT_COMMEND_REWARD t where t.sg_enterprise_library_uid = "+ vo.getSg_enterprise_library_uid();
        		DBUtil.exec(conn, clearzizhi_SQL);
        	}
            
            //增加项目信息
            String xiangmList = getXiangms(conn, obj, vo, files);
            if(!"".equals(xiangmList)){
            	xiangmList = xiangmList.substring(0,xiangmList.length()-1);
        		//清楚多余页面被删掉的增项资质
        		String clearzizhi_SQL = "delete from SG_E_CREDIT_PROJECTS t where t.sg_enterprise_library_uid = "+vo.getSg_enterprise_library_uid()+" and t.CREDIT_PROJECTS_UID not in ("+xiangmList+")";
        		DBUtil.exec(conn, clearzizhi_SQL);
        	}else{
        		//删除所有资质
            	String clearzizhi_SQL = "delete from SG_E_CREDIT_PROJECTS t where t.sg_enterprise_library_uid = "+ vo.getSg_enterprise_library_uid();
        		DBUtil.exec(conn, clearzizhi_SQL);
        	}
            
            
            BaseDAO.update(conn, vo);
            
            resultVO = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_enterprise_library_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表修改成功", user, "", "");
        } catch (Exception e) {
        	LogManager.writeUserLog(vo.getSg_enterprise_library_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表修改失败", user, "", "");
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("施工单位表修改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVO;
    }

    // @Override
    public String delete(String json) throws Exception {

        Connection conn = DBUtil.getConnection();
        String resultVo = null;
        SgEnterpriseLibraryVO vo = new SgEnterpriseLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject jsonObj = (JSONObject) list.get(0);

            vo.setValueFromJson(jsonObj);
            BaseDAO.delete(conn, vo);

            resultVo = vo.getRowJson();
            conn.commit();
            LogManager.writeUserLog(vo.getSg_enterprise_library_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表删除成功", user, "", "");
        } catch (Exception e) {
        	LogManager.writeUserLog(vo.getSg_enterprise_library_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表删除失败", user, "", "");
            DBUtil.rollbackConnetion(conn);
            //e.printStackTrace(System.out);
            logger.error("业务页面模版表删除失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return resultVo;

    }

	public String queryAll() throws Exception {
		Connection conn = DBUtil.getConnection();
		String queryDictSql = "SELECT SG_ZIZHI_UID,ZIZHI_NAME,ZIZHI_TYPE,ZHUANYE_NAME FROM SG_ZIZHI";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, queryDictSql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("SG_ZIZHI_UID",rsMap.get("SG_ZIZHI_UID"));
			    json.put("ZIZHI_NAME", rsMap.get("ZIZHI_NAME"));
			    json.put("ZIZHI_TYPE", rsMap.get("ZIZHI_TYPE"));
			    json.put("ZHUANYE_NAME", rsMap.get("ZHUANYE_NAME"));
			    jsonArr.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	

	public SgEnterpriseLibraryVO getUserByUsernameAndPassword(String dengluCode) throws Exception {
		Connection conn = null;
		SgEnterpriseLibraryVO vo = null;
		
		try {
			//查询当前登陆代码的用户
			String querySql = "select SG_ENTERPRISE_LIBRARY_UID,SG_COMPANY_UID,DENGLU_CODE,PWD,COMPANY_CODE,COMPANY_NAME from sg_enterprise_library where DENGLU_CODE = '"+dengluCode+"' ";
			conn = DBUtil.getConnection();
			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					vo = new SgEnterpriseLibraryVO();
					vo.setSg_enterprise_library_uid(list[i][0]);
					vo.setSg_company_uid(list[i][1]);
					vo.setDenglu_code(list[i][2]);
					vo.setPwd(Encipher.DecodePasswd(list[i][3]));
					vo.setCompany_code(list[i][4]);
					vo.setCompany_name(list[i][5]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return vo;

	}

	public String queryCodeIsEmpty(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		try {
			JSONArray array = JSONArray.fromObject("["+json+"]");
			JSONObject o=(JSONObject) array.get(0);
			String sql = " select count(0) from SG_ENTERPRISE_LIBRARY where status = 1 and COMPANY_CODE = '"+o.getString("code")+"' ";
			if(StringUtils.isNotBlank(o.getString("id"))){
				sql += " and SG_COMPANY_UID!="+o.getString("id")+"";
			}
			String[][] res = DBUtil.query(conn, sql);
			if(Integer.parseInt(res[0][0])>0){
				return "empty";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return "notempty";
	}
	
	private void insertFiles(Map files,String fileLinks, String target_uid ,Connection conn){
		try {
				conn.setAutoCommit(false);
				if (files!=null&&StringUtils.isNotBlank(fileLinks)) {
					String[] links = fileLinks.split(","); 
					for (int i = 0; i < links.length; i++) {
						if(!"".equals(links[i])){
							if(files.get(links[i])!=null){
								AtFileuploadVO vo = (AtFileuploadVO) files.get(links[i]);
								vo.setTarget_uid(target_uid);		//设置文件关联
								vo.setEnabled("1");					//设置文件已生效
//								vo.setCreated_date(new Date());
								vo.setCreated_name(ActionContext.getCurrentUserInThread().getUserSN());
								vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
								BaseDAO.insert(conn, vo);
								files.remove(links[i]);
							}
						}
					}
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public String loadFiles(Map map) throws Exception {
		Connection conn = DBUtil.getConnection();
		String querySql = "SELECT * FROM AT_FILEUPLOAD WHERE ENABLED = 1 ";
		if(map.get("target_id")!=null&&!"".equals(map.get("target_id"))){
			querySql += " AND TARGET_UID = "+map.get("target_id");
		}
		if(map.get("file_type")!=null&&!"".equals(map.get("file_type"))){
			querySql += " AND FILE_TYPE = "+map.get("file_type");
		}
		if(map.get("business_type")!=null&&!"".equals(map.get("business_type"))){
			querySql += " AND BUSINESS_SUB_TYPE = "+map.get("business_type");
		}
		querySql += " ORDER BY SERIAL_NO ";
		JSONArray jsonArr = new JSONArray();
		
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, querySql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("AT_FILEUPLOAD_UID",rsMap.get("AT_FILEUPLOAD_UID"));
				json.put("TARGET_TYPE",rsMap.get("TARGET_TYPE"));
				json.put("TARGET_UID",rsMap.get("TARGET_UID"));
				json.put("FILE_TYPE",rsMap.get("FILE_TYPE"));
				json.put("FILE_NAME",rsMap.get("FILE_NAME"));
				json.put("EXT_NAME",rsMap.get("EXT_NAME"));
				json.put("DOC_SIZE",rsMap.get("DOC_SIZE"));
				json.put("MIME_TYPE",rsMap.get("MIME_TYPE"));
				json.put("BUSINESS_SUB_TYPE",rsMap.get("BUSINESS_SUB_TYPE"));
				json.put("FILE_SAVE_NAME",rsMap.get("FILE_SAVE_NAME"));
			    jsonArr.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}
	
	/**
	 * insert奖项
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getJiangxs( Connection conn , JSONObject obj, SgEnterpriseLibraryVO vo, Map files) throws Exception{
		 JSONArray jiangxArray = null;
		 SgECreditCommendRewardVO jiangxVO = new SgECreditCommendRewardVO();
         try{
        	 jiangxArray = obj.getJSONArray("CR_NAME");
         }catch(JSONException e){
         	System.out.println("没有添加奖项!");
         }
         //处理增项资质
        String enptyJiangx = "";
         if(jiangxArray!=null){
         	for (int i = 0; i < jiangxArray.size(); i++) {
					if(StringUtils.isBlank((String) jiangxArray.get(i))){
						continue;
					}
					jiangxVO = new SgECreditCommendRewardVO();
					String uid = obj.getJSONArray("CREDIT_COMMEND_REWARD_UID").getString(i);
					boolean flag = true;
		            if(uid==null||"".equals(uid)){
		            	//若不存在uid则执行新增,反之进行修改
		            	flag = false;
		            }else{
		            	jiangxVO.setCredit_commend_reward_uid(uid);
		            }
					//获取索引
					jiangxVO.setSg_enterprise_library_uid(vo.getSg_enterprise_library_uid());
					jiangxVO.setCr_name((String) jiangxArray.get(i));
					jiangxVO.setCr_level(obj.getJSONArray("CR_LEVEL").getString(i));
					jiangxVO.setBg_validity_date(Utils.formatToDate(obj.getJSONArray("BG_VALIDITY_DATE").getString(i)));
					User  user = ActionContext.getCurrentUserInThread();
					
		           
					if(!flag){
						 //插入用户/企业
						jiangxVO.setCreated_date(new Date());
						jiangxVO.setCreated_name(user.getUserSN());
						jiangxVO.setCreated_uid(user.getUserSN());
			            //排序号
						//jiangxVO.setSerial_no(jiangxVO.getCredit_commend_reward_uid());
			            //插入奖项信息
						BaseDAO.insert(conn, jiangxVO);
						String fileLinks = obj.getJSONArray(jiangxVO.getVOTableName()+"_FILEUPLOAD").getString(i);
						insertFiles(files, fileLinks, jiangxVO.getCredit_commend_reward_uid(), conn);
					}else{
						 //插入用户/企业
						jiangxVO.setUpdate_date(new Date());
						jiangxVO.setUpdate_name(user.getUserSN());
						jiangxVO.setUpdate_uid(user.getUserSN());
			            //插入奖项信息
						BaseDAO.update(conn, jiangxVO);
					}
					enptyJiangx += "'"+jiangxVO.getCredit_commend_reward_uid()+"',";
				}
         }
		return enptyJiangx;
	}
	
	/**
	 * insert项目
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getXiangms( Connection conn , JSONObject obj, SgEnterpriseLibraryVO vo, Map files) throws Exception{
		 JSONArray jiangxArray = null;
		 SgECreditProjectsVO xiangmVO = new SgECreditProjectsVO();
         try{
        	 jiangxArray = obj.getJSONArray("PROJECT_NAME");
         }catch(JSONException e){
         	System.out.println("没有添加项目!");
         }
         //处理增项资质
        String enptyJiangx = "";
         if(jiangxArray!=null){
         	for (int i = 0; i < jiangxArray.size(); i++) {
					if(StringUtils.isBlank((String) jiangxArray.get(i))){
						continue;
					}
					xiangmVO = new SgECreditProjectsVO();
					String uid = obj.getJSONArray("CREDIT_PROJECTS_UID").getString(i);
					boolean flag = true;
		            if(uid==null||"".equals(uid)){
		            	//若不存在uid则执行新增,反之进行修改
		            	flag = false;
		            }else{
		            	xiangmVO.setCredit_projects_uid(uid);
		            }
					//获取索引
		            xiangmVO.setSg_enterprise_library_uid(vo.getSg_enterprise_library_uid());
		            xiangmVO.setProject_name((String) jiangxArray.get(i));
		            xiangmVO.setYxbegin_date(Utils.formatToDate((obj.getJSONArray("YXBEGIN_DATE").getString(i))));
		            
					User  user = ActionContext.getCurrentUserInThread();
		           
					if(!flag){
						 //插入用户/企业
						xiangmVO.setCreated_date(new Date());
						xiangmVO.setCreated_name(user.getUserSN());
						xiangmVO.setCreated_uid(user.getUserSN());
			            //排序号
						//xiangmVO.setSerial_no(xiangmVO.getCredit_projects_uid());
			            //插入增项资质信息
						BaseDAO.insert(conn, xiangmVO);
						String fileLinks = obj.getJSONArray(xiangmVO.getVOTableName()+"_FILEUPLOAD").getString(i);
						insertFiles(files, fileLinks, xiangmVO.getCredit_projects_uid(), conn);
					}else{
						 //插入用户/企业
						xiangmVO.setUpdate_date(new Date());
						xiangmVO.setUpdate_name(user.getUserSN());
						xiangmVO.setUpdate_uid(user.getUserSN());
			            //插入项目信息
						BaseDAO.update(conn, xiangmVO);
					}
					enptyJiangx += "'"+xiangmVO.getCredit_projects_uid()+"',";
				}
         }
		return enptyJiangx;
	}
	
	/**
	 * insert增项资质
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getZizhis( Connection conn , JSONObject obj, SgEnterpriseLibraryVO vo) throws Exception{
		JSONArray zizhiArray = null;
		String emptyZizhi = "";
		SgEnterpriseZenZizhiVO zengVO = null;
        try{
        	zizhiArray = obj.getJSONArray("ZENG_SG_ZIZHI_UID");
        }catch(JSONException e){
        	System.out.println("没有添加增项资质!");
        }
        
        //处理增项资质
       
        if(zizhiArray!=null){
        	for (int i = 0; i < zizhiArray.size(); i++) {
				if(StringUtils.isBlank((String) zizhiArray.get(i))){
					continue;
				}
				//获取索引
	            zengVO = new SgEnterpriseZenZizhiVO();
	            String zeng_uid = obj.getJSONArray("SG_ENTERPRISE_ZEN_ZIZHI_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            }else{
	            	zengVO.setSg_enterprise_zen_zizhi_uid(zeng_uid);
	            }
	            zengVO.setSerial_no((i+1)+"");
	            zengVO.setSg_enterprise_library_uid(vo.getSg_enterprise_library_uid());
	            zengVO.setSg_zizhi_uid(obj.getJSONArray("ZENG_SG_ZIZHI_UID").getString(i));
	            zengVO.setZizhi_code(obj.getJSONArray("ZIZHI_CODE").getString(i));
	            zengVO.setZizhi_dengji(obj.getJSONArray("ZIZHI_DENGJI").getString(i));
	            zengVO.setValid_date(Utils.formatToDate(obj.getJSONArray("ZENG_VALID_DATE").getString(i)));
	            //zengVO.setSerial_no(zengVO.getSg_enterprise_zen_zizhi_uid());
	            
	            User  user = ActionContext.getCurrentUserInThread();
	            
	            //插入增项资质信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	//插入用户/企业
		            zengVO.setUpdate_date(new Date());
		            zengVO.setUpdate_name(user.getUserSN());
		            zengVO.setUpdate_uid(user.getUserSN());
	            	BaseDAO.update(conn, zengVO);
	            }else{
	            	//更新用户/企业
		            zengVO.setCreated_date(new Date());
		            zengVO.setCreated_name(user.getUserSN());
		            zengVO.setCreated_uid(user.getUserSN());
	            	BaseDAO.insert(conn, zengVO);
	            }
	            
	            emptyZizhi += "'"+zengVO.getSg_enterprise_zen_zizhi_uid()+"',";
			}
        }
        return emptyZizhi;
	}

	public String updateCopyCompany() throws Exception {
		Connection conn = null;
		String company_uid = "";
		String uid = "";
		try {
			conn = DBUtil.getConnection();
			User user = ActionContext.getCurrentUserInThread();
			company_uid = user.getIdCard();
			if(StringUtils.isNotBlank(company_uid)){
				uid = DBUtil.getSequenceValue("SG_ENTERPRISE_LIBRARY_UID");
				//复制企业信息
				String copy_sql = " insert into SG_ENTERPRISE_LIBRARY (SG_ENTERPRISE_LIBRARY_UID, SG_COMPANY_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, DENGLU_CODE, PWD, MIMA, COMPANY_CODE, COMPANY_NAME, COMPANY_TYPE, WAIDI_Y, ZHIZHAO, ZHIZHAO_VALID, SHUIWU, BANK, BANK_ACCOUNT, ZHUCE_ZIJIN, ANQUAN_CODE, ANQUAN_VALID, XINYONG_CODE, XINYONG_VALID, SG_ZIZHI_UID, ZHU_DENGJI, ZHENGSHU_CODE, ZIZHI_DATE, ADDRESS, POSTCODE, PHONE, FAX, URL, FAREN, FAREN_ZHICHENG, FAREN_MOBILE, ZHUXI_ADDRESS, ZHUXI_FZR, ZHUXI_MOBILE, LIANXIREN, LIANXIREN_MOBILE, LIANXIREN_MAIL, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, VALID_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW,SCORE) "
						+" select "+uid+", SG_COMPANY_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, DENGLU_CODE, PWD, MIMA, COMPANY_CODE, COMPANY_NAME, COMPANY_TYPE, WAIDI_Y, ZHIZHAO, ZHIZHAO_VALID, SHUIWU, BANK, BANK_ACCOUNT, ZHUCE_ZIJIN, ANQUAN_CODE, ANQUAN_VALID, XINYONG_CODE, XINYONG_VALID, SG_ZIZHI_UID, ZHU_DENGJI, ZHENGSHU_CODE, ZIZHI_DATE, ADDRESS, POSTCODE, PHONE, FAX, URL, FAREN, FAREN_ZHICHENG, FAREN_MOBILE, ZHUXI_ADDRESS, ZHUXI_FZR, ZHUXI_MOBILE, LIANXIREN, LIANXIREN_MOBILE, LIANXIREN_MAIL, DESCRIPTION, 40, UPDATED_DATE, TIJIAO_DATE, NULL, NULL, NULL, NULL, VALID_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW,SCORE from SG_ENTERPRISE_LIBRARY " 
						+" where sg_company_uid ="+user.getIdCard()+"  and status=1";
				DBUtil.exec(copy_sql);
				//复制附件信息
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
						+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, "+uid+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
						+" where TARGET_UID = (select sg_enterprise_library_uid from sg_enterprise_library where status = 1 and sg_company_uid = "+user.getIdCard()+") ";
				DBUtil.exec(copy_file);
				
				conn.commit();
			}
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return uid;
	}
	
	public boolean queryCompanyForEdit() throws Exception{
		boolean count = false;
		try {
			User user = ActionContext.getCurrentUserInThread();
			String sql = "select count(*) from SG_ENTERPRISE_LIBRARY where status = 40 and sg_company_uid = "+user.getIdCard();
			String[][] results = DBUtil.query(sql);
			if (results == null) {
				count = true;
			}else{
				if(Integer.parseInt(results[0][0])>0){
					count = false;
				}else{
					count = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public boolean queryHasEmptyZizhi(String dengjis) throws Exception{
		boolean count = false;
		try {
			User user = ActionContext.getCurrentUserInThread();
			String sql = "select count(*) from v_sg_company_zizhi t where t.sg_company_uid = "+user.getIdCard()+" and t.sg_zizhi_dengji_uid in ("+dengjis+")";
			String[][] results = DBUtil.query(sql);
			if (results == null) {
				count = false;
			}else{
				if(Integer.parseInt(results[0][0])>0){
					count = true;
				}else{
					count = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public String insertToOld(String json) throws Exception{
		
		Connection conn = null;
		String flag = null;
		List<String> zengs = new ArrayList<String>();
		String company_uid = null;
		try {
			conn = DBUtil.getConnection();
			JSONObject data = JSONObject.fromObject(json);
			JSONObject data2 = JSONObject.fromObject(JSONObject.fromObject(data.getString("response")).getJSONArray("data").getString(0));
			if(StringUtils.isNotBlank(data2.getString("STATUS"))&&"30".equals(data2.getString("STATUS"))){
			
				String uid = data2.getString("SG_ENTERPRISE_LIBRARY_UID");
				//复制企业信息
				String sql_copy_sgenter  = "insert into wndjs.sg_enterprise_library "
					  	+" (sg_enterprise_library_uid, sg_company_uid, denglu_code, pwd, mima, company_code, company_name, company_type, waidi_y, zhizhao, zhizhao_valid, shuiwu, bank, bank_account, zhengshu_code, zhu_zizhi, zhu_dengji, zizhi_date, address, postcode, phone, fax, url, faren, faren_zhicheng, faren_mobile, lianxiren, lianxiren_mobile, lianxiren_mail, description, status, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, valid_date, jz_y, jz_yuanyin, jz_sjfw, zhuxi_address, zhuxi_fzr, zhuxi_mobile, zhuce_zijin, anquan_code, anquan_valid, xinyong_code, xinyong_valid)"
					  	+" select sg_enterprise_library_uid, sg_company_uid, denglu_code, pwd, mima, company_code, company_name, (case company_type when '有限责任公司' then 0  when '股份有限公司' then 1  when '全民所有制' then 2 end), waidi_y, zhizhao, zhizhao_valid, shuiwu, bank, bank_account, zhengshu_code, SG_ZIZHI_UID, zhu_dengji, zizhi_date, address, postcode, phone, fax, url, faren, faren_zhicheng, faren_mobile, lianxiren, lianxiren_mobile, lianxiren_mail, description, status, updated_date, tijiao_date, shenhe_ren, shenhe_jieguo, shenhe_yijian, shenhe_date, valid_date, jz_y, jz_yuanyin, jz_sjfw, zhuxi_address, zhuxi_fzr, zhuxi_mobile, zhuce_zijin, anquan_code, anquan_valid, xinyong_code, xinyong_valid from sg_enterprise_library where  sg_enterprise_library_uid = "+uid;
				//复制企业附件信息
				String sql_copy_sgenter_file = "insert into wndjs.sgk_file "
						+" (sgk_file_uid, target_type, target_uid, file_type, file_name, ext_name, doc_size, mime_type, upload_date) "
						+" select at_fileupload_uid, target_type, target_uid, file_type, file_name, ext_name, doc_size, mime_type, created_date from at_fileupload where BUSINESS_SUB_TYPE = 'SG_ENTERPRISE_LIBRARY' and  target_uid = " + uid;
				
				//复制施工信息
				DBUtil.exec(conn, sql_copy_sgenter);
				//复制附件信息
				DBUtil.exec(conn, sql_copy_sgenter_file);
				
				//复制增项资质信息
				String copy_zizhiXX = "select t.*,(select a.sg_company_uid from SG_ENTERPRISE_LIBRARY a where a.SG_ENTERPRISE_LIBRARY_UID = t.SG_ENTERPRISE_LIBRARY_UID) cid from sg_enterprise_zen_zizhi t where t.SG_ENTERPRISE_LIBRARY_UID = " + uid;
				QuerySet qs = DBUtil.executeQuery(copy_zizhiXX,null,conn);
				if (qs.getRowCount() > 0) {
					for (int i = 0; i < qs.getRowCount(); i++) {
						String zizhi_uid = qs.getString(i + 1, "SG_ZIZHI_UID");
						company_uid = qs.getString(i + 1, "CID");
						String zeng_uid = qs.getString(i + 1, "SG_ENTERPRISE_ZEN_ZIZHI_UID");
						
						String sql = "select SG_ENTERPRISE_ZEN_ZIZHI_UID,SG_COMPANY_UID,ZIZHI_UID,ZIZHI_DENGJI,ZIZHI_CODE,VALID_DATE from wndjs.sg_enterprise_zen_zizhi where sg_company_uid = "+company_uid+" and ZIZHI_UID = "+zizhi_uid;
						String[][] res = DBUtil.query(conn, sql);
						if(res!=null){
							String sql_copy_zeng_zizhi = "";
							if(res.length!=0){
								String dengji = qs.getString(i + 1, "ZIZHI_DENGJI");
								String code = qs.getString(i + 1, "ZIZHI_CODE");
								String date = qs.getString(i + 1, "VALID_DATE");
								//原表存在该条目信息
								for (int j = 0; j < res.length; j++) {
									sql_copy_zeng_zizhi = "update wndjs.sg_enterprise_zen_zizhi "
										+" set zizhi_dengji = "+dengji+",zizhi_code = '"+code+"',valid_date = to_date('"+date+"','yyyy-MM-dd HH24:mi:ss')"
										+" where sg_enterprise_zen_zizhi_uid = "+res[j][0];
									zengs.add(res[j][0]);
									DBUtil.exec(conn, sql_copy_zeng_zizhi);
								}
							}
							
							
						}else{
							//原表不存在该条目信息
							//复制增项资质信息
							String sql_copy_zeng_zizhi = "insert into wndjs.sg_enterprise_zen_zizhi "
									+" (sg_enterprise_zen_zizhi_uid, sg_company_uid, zizhi_uid, zizhi_dengji, zizhi_code, valid_date, serial_no) "
									+" select sg_enterprise_zen_zizhi_uid, (select t.sg_company_uid from sg_enterprise_library t where t.sg_enterprise_library_uid = "+uid+"), SG_ZIZHI_UID, zizhi_dengji, zizhi_code, valid_date, serial_no from sg_enterprise_zen_zizhi where sg_enterprise_zen_zizhi_uid = " +zeng_uid+ " ";
							
							zengs.add(zeng_uid);
							DBUtil.exec(conn, sql_copy_zeng_zizhi);
						}
					}
				}
				
				if(zengs.size()>0){
					String ids = zengs.toString().substring(1,zengs.toString().length()-1);
					String sql_delete = "delete from wndjs.sg_enterprise_zen_zizhi where sg_company_uid = "+company_uid+" and sg_enterprise_zen_zizhi_uid not in (" +ids +" )";
					DBUtil.exec(conn, sql_delete);
				}
				
				flag = json;
			}
			
			
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;

	}
	
	
	 public String queryOnSp(String json) throws Exception {
		    
	        Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	            PageManager page = RequestUtil.getPageManager(json);
	            String condition = RequestUtil.getConditionList(json).getConditionWhere();
	            condition += " and SHENHE_JIEGUO is null ";
	            String orderFilter = " order by n.SG_ENTERPRISE_LIBRARY_UID desc ";
	            //condition += BusinessUtil.getCommonCondition(user, null);
	            condition += orderFilter;
	            if (page == null)
	                page = new PageManager();
	            page.setFilter(condition);

	            conn.setAutoCommit(false);
	            String sql = SQL_QUERY_ONSP;
	            BaseResultSet bs = DBUtil.query(conn, sql, page);
	            
	            bs.setFieldDic("COMPANY_TYPE", "COMPANY_TYPE");
	            bs.setFieldDic("WAIDI_Y", "WAIDI_Y");
	            
	            domresult = bs.getJson();
	            
	        } catch (Exception e) {
	            DBUtil.rollbackConnetion(conn);
	            logger.error("业务页面模版表查询失败!");
	            e.printStackTrace(System.out);
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return domresult;

	    }

	public String updateCopyByStatus(Map map) throws Exception {
		Connection conn = null;
		String uid = "";
		
		try {
			conn = DBUtil.getConnection();
			String id = (String) map.get("uid"); 
			String status = (String) map.get("status");
			
			if("15".equals(status)){
				String delete_sql = "delete from sg_enterprise_library t where t.status = 15 and t.sg_company_uid = (select n.sg_company_uid from sg_enterprise_library n where n.sg_enterprise_library_uid ="+id+")";
				DBUtil.exec(conn ,delete_sql);
				conn.commit();
			}
			
			uid = DBUtil.getSequenceValue("SG_ENTERPRISE_LIBRARY_UID", conn);
			//复制企业信息
			String copy_sql = " insert into SG_ENTERPRISE_LIBRARY (SG_ENTERPRISE_LIBRARY_UID, SG_COMPANY_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, DENGLU_CODE, PWD, MIMA, COMPANY_CODE, COMPANY_NAME, COMPANY_TYPE, WAIDI_Y, ZHIZHAO, ZHIZHAO_VALID, SHUIWU, BANK, BANK_ACCOUNT, ZHUCE_ZIJIN, ANQUAN_CODE, ANQUAN_VALID, XINYONG_CODE, XINYONG_VALID, SG_ZIZHI_UID, ZHU_DENGJI, ZHENGSHU_CODE, ZIZHI_DATE, ADDRESS, POSTCODE, PHONE, FAX, URL, FAREN, FAREN_ZHICHENG, FAREN_MOBILE, ZHUXI_ADDRESS, ZHUXI_FZR, ZHUXI_MOBILE, LIANXIREN, LIANXIREN_MOBILE, LIANXIREN_MAIL, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, VALID_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, QUEREN_STATUS, QUEREN_REN, QUEREN_DATE,SCORE) "
					+" select "+uid+", SG_COMPANY_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, DENGLU_CODE, PWD, MIMA, COMPANY_CODE, COMPANY_NAME, COMPANY_TYPE, WAIDI_Y, ZHIZHAO, ZHIZHAO_VALID, SHUIWU, BANK, BANK_ACCOUNT, ZHUCE_ZIJIN, ANQUAN_CODE, ANQUAN_VALID, XINYONG_CODE, XINYONG_VALID, SG_ZIZHI_UID, ZHU_DENGJI, ZHENGSHU_CODE, ZIZHI_DATE, ADDRESS, POSTCODE, PHONE, FAX, URL, FAREN, FAREN_ZHICHENG, FAREN_MOBILE, ZHUXI_ADDRESS, ZHUXI_FZR, ZHUXI_MOBILE, LIANXIREN, LIANXIREN_MOBILE, LIANXIREN_MAIL, DESCRIPTION, "+status+", UPDATED_DATE, TIJIAO_DATE, SHENHE_REN,  SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, VALID_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, null, null, null,SCORE from SG_ENTERPRISE_LIBRARY " 
					+" where SG_ENTERPRISE_LIBRARY_uid ="+id+" ";
			DBUtil.exec(conn ,copy_sql);
			//复制附件信息
			String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+uid+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+id+" AND BUSINESS_SUB_TYPE = 'SG_ENTERPRISE_LIBRARY'";
			DBUtil.exec(conn ,copy_file);
			
			//施工企业增项资质
			String copy_zeng_zizhi = "insert into sg_enterprise_zen_zizhi "
				+" (sg_enterprise_zen_zizhi_uid, sg_zizhi_uid, sg_enterprise_library_uid, event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, zizhi_dengji, zizhi_code, valid_date) "
				+" select sg_enterprise_zen_zizhi_uid.nextval, sg_zizhi_uid, "+uid+", event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, zizhi_dengji, zizhi_code, valid_date from sg_enterprise_zen_zizhi "
				+" where sg_enterprise_library_uid = " + id;
			DBUtil.exec(conn ,copy_zeng_zizhi);
			
			//施工企业信用-表彰奖励分
			String[][] res = DBUtil.query(conn, "select t.credit_commend_reward_uid from sg_e_credit_commend_reward t where t.sg_enterprise_library_uid = "+id);
			if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					String newUid = DBUtil.getSequenceValue(StringUtils.upperCase("credit_commend_reward_uid"), conn);
					String copy_jx = "insert into sg_e_credit_commend_reward "
						+" (credit_commend_reward_uid, sg_enterprise_library_uid, event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, cr_name, cr_level, cr_type, cr_score, sf_checked, bg_validity_date, yxqx) "
						+" select "+newUid+", "+uid+", event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, cr_name, cr_level, cr_type, cr_score, sf_checked, bg_validity_date, yxqx from sg_e_credit_commend_reward "
						+" where credit_commend_reward_uid = " + res[i][0];
					copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
						+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+newUid+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
						+" where TARGET_UID = "+res[i][0]+" AND BUSINESS_SUB_TYPE='SG_E_CREDIT_COMMEND_REWARD'";
					
					DBUtil.exec(conn ,copy_jx);
					DBUtil.exec(conn ,copy_file);
				}
			}
			
			//施工企业信用-承接工程分
			res = DBUtil.query(conn, "select t.credit_projects_uid from sg_e_credit_projects t where t.sg_enterprise_library_uid = "+id);
			if (res!=null) {
				for (int i = 0; i < res.length; i++) {
					String newUid = DBUtil.getSequenceValue(StringUtils.upperCase("credit_projects_uid"), conn);
					String copy_jx = "insert into sg_e_credit_projects "
						+" (credit_projects_uid, sg_enterprise_library_uid, event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, project_name, yxbegin_date, sf_checked, project_score) "
						+" select "+newUid+", "+uid+", event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, project_name, yxbegin_date, sf_checked, project_score from sg_e_credit_projects "
						+" where credit_projects_uid = " + res[i][0];
					copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
						+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+newUid+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
						+" where TARGET_UID = "+res[i][0] +" AND BUSINESS_SUB_TYPE='SG_E_CREDIT_PROJECTS'";
					
					DBUtil.exec(conn ,copy_jx);
					DBUtil.exec(conn ,copy_file);
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return uid;
	}
	public void updateDshxx(Map map) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        try {

           String sql = "update sg_enterprise_library t set t.shenhe_ren = ?,t.shenhe_jieguo=?,t.shenhe_yijian=?,t.shenhe_date=to_date(?,'yyyy-mm-dd') where t.sg_enterprise_library_uid = ?";
            
           String jieguo = (String) map.get("jg");
           String yijian = (String) map.get("yj");
           String uid = (String) map.get("uid");
           
           DBUtil.executeUpdate(conn, sql, new Object[]{user.getUserSN(),jieguo,yijian,(new SimpleDateFormat("yyyy-MM-dd")).format(new Date()),uid});
            
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("业务页面模版表查询失败!");
            e.printStackTrace(System.out);
        } finally {
            DBUtil.closeConnetion(conn);
        }
	}
	
	private boolean updateYczxx(Connection conn , String uid) throws Exception{
		boolean flag = true;
		try {
			String sql = "select n.sg_enterprise_library_uid from sg_enterprise_library n where n.status = 1 and n.sg_company_uid = (select t.sg_company_uid from sg_enterprise_library t where t.sg_enterprise_library_uid = "+uid+")";
			
			String[][] res = DBUtil.query(conn ,sql);
			if(res==null||res.length==0){
				flag = false;
			}else{
				DBUtil.exec("update sg_enterprise_library n set n.status = 5 where n.sg_enterprise_library_uid = " +res[0][0]);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}
	
	private String getDengluCode(Connection conn , String uid) throws Exception{
		String code = null;
		try {
			
			String sql = "select  max(to_number(substr(t.denglu_code,6,9))) from sg_enterprise_library t where t.denglu_code like 'WNDSG%'";
			String[][] res = DBUtil.query(conn ,sql);
			if (res!=null&&StringUtils.isNotBlank(res[0][0])) {
				String code1 = Integer.parseInt(res[0][0]) + 1 + "";
				code = code1;
				for (int i = 0; i < 5-code1.length(); i++) {
					code = "0" +code;
				}
			}
			code = "WNDSG" + code;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return code;
	}
	
	public String insertRole(String id){
		Connection conn = DBUtil.getConnection();
		//添加权限
		String sql1 = "select count(0) from fs_org_role_psn_map t where t.person_account = (select n.denglu_code from sg_enterprise_library n where n.sg_enterprise_library_uid = "+id+" ) and role_id = '4dfb25ff-e5a5-4443-87fd-b5d081fb3bcf'";
		String[][] res = DBUtil.query(conn, sql1);
		if (res==null||Integer.parseInt(res[0][0])==0) {
			String doRole = "insert into FS_ORG_ROLE_PSN_MAP (ROLE_NAME,PERSON_ACCOUNT,ROLE_ID) values ('施工单位角色',(select n.denglu_code from sg_enterprise_library n where n.sg_enterprise_library_uid = "+id+") ,'4dfb25ff-e5a5-4443-87fd-b5d081fb3bcf')";
			try {
				DBUtil.exec(conn, doRole);
				conn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.closeConnetion(conn);
			}
		}
		return null;
	}
	
	//确认获奖信息
	public void updateQueren(String uid) throws Exception{
		
		Connection conn = DBUtil.getConnection();
		User user = ActionContext.getCurrentUserInThread();
		//添加权限
		String updateSql = "update sg_enterprise_library t set t.queren_status = 10,t.queren_ren= "+user.getUserSN()+",t.queren_date = sysdate where t.sg_enterprise_library_uid = "+uid;
		try {
			DBUtil.exec(conn, updateSql);
		} catch (Exception e) {
			LogManager.writeUserLog(uid, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,user.getName() + "企业表删除失败", user, "", "");
            DBUtil.rollbackConnetion(conn);
            //e.printStackTrace(System.out);
            logger.error("确认奖项信息失败!");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
	}
}
	
