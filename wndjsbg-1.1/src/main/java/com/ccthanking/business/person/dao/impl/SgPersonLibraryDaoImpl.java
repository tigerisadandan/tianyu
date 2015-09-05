/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.SgPersonLibraryDao.java
 * 创建日期： 2014-04-28 上午 09:29:27
 * 功能：   人员信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-28 上午 09:29:27  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.person.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccthanking.business.person.dao.SgPersonLibraryDao;
import com.ccthanking.business.person.dao.SgPersonZhengshuDao;
import com.ccthanking.business.person.vo.SgPersonLibraryVO;
import com.ccthanking.business.person.vo.SgPersonZhengshuVO;
import com.ccthanking.business.sgenter.vo.AtFileuploadVO;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
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
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> SgPersonLibraryDao.java </p>
 * <p> 功能：人员信息 </p>
 *
 * <p><a href="SgPersonLibraryDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-28
 * 
 */

@Component
public class SgPersonLibraryDaoImpl  extends BsBaseDaoTJdbc implements SgPersonLibraryDao {

	private static Logger logger = LoggerFactory.getLogger(SgPersonLibraryDaoImpl.class);
	private static String SQL_QUERY_PERSON_ALL="select '' ZHENGSHU_LIST,a.status,a.shenhe_date,a.shenhe_jieguo,a.tijiao_date,a.shenhe_yijian,a.jz_y,a.jz_yuanyin,a.jz_sjfw,a.jz_date,b.user_name,a.sg_company_uid,a.sg_person_library_uid,e.company_name,e.denglu_code as company_denglu_code,a.sg_person_uid,a.person_name,a.phone,a.email,a.zhicheng_uid,a.zhicheng_code,d.zhicheng_name,a.begin_date,a.end_date,a.update_date,a.description,a.denglu_code,'' XINYONGFENSHU,'' YEJI,'' ZAIJIANXIANGMU,a.sex,a.shenfenzheng" +
	" from sg_person_library a left join zhicheng d on a.zhicheng_uid=d.zhicheng_uid left join users b on a.shenhe_ren=b.users_uid left join SG_ENTERPRISE_LIBRARY e on a.sg_company_uid=e.sg_company_uid and e.status = 1 ";
	
	private static String SQL_QUERY_ALL = "select a.sg_person_uid,a.status,a.sg_person_library_uid,a.person_name,a.phone,a.email,a.zhicheng_uid,a.zhicheng_code,d.zhicheng_name,a.begin_date,a.end_date,a.description,a.denglu_code,'' XINYONGFENSHU,'' YEJI,'' ZAIJIANXIANGMU,a.sex,a.shenfenzheng,"  
        +" (select WM_CONCAT(c.zhengshu_name) from sg_person_zhengshu b left join sg_zhengshu c on b.sg_zhengshu_uid = c.sg_zhengshu_uid where b.sg_person_uid = a.sg_person_library_uid) ZHENGSHU_LIST  from sg_person_library a left join zhicheng d on a.zhicheng_uid=d.zhicheng_uid   ";
	private String ywlx = YwlxManager.SGRY;

	@Autowired
	SgPersonZhengshuDao sgPersonZhengshuDao;
	
	@Autowired
    private FsMessageInfoService fsMessageInfoService;
	
	//删除数据
	public String delete(String json) throws Exception {
		    Connection conn = DBUtil.getConnection();
	        String resultVo = null;
	        SgPersonLibraryVO vo = new SgPersonLibraryVO();
	        User user = ActionContext.getCurrentUserInThread();

	        try {
	            conn.setAutoCommit(false);
	            JSONArray list = vo.doInitJson(json);
	            JSONObject jsonObj = (JSONObject) list.get(0);
	            
	            vo.setValueFromJson(jsonObj);
	            sgPersonZhengshuDao.deleteByPersonuid(vo);//删除sg_person_library_uid在sgPersonZhengshu表中的记录
	            BaseDAO.delete(conn, vo);
         		LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<人员信息>删除", user, "", "");

                resultVo = vo.getRowJson();
                conn.commit();
	        } catch (Exception e) {
	            DBUtil.rollbackConnetion(conn);
	            e.printStackTrace(System.out);
	            logger.error("人员信息删除失败!");
	          } finally {
	            DBUtil.closeConnetion(conn);
	        }
	    return resultVo;
	}

//新增数据
	public String insert(String json,String status,Map fileList ) throws Exception {
	
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgPersonLibraryVO vo = new SgPersonLibraryVO();
        SgPersonZhengshuVO voZhengshuVO=null;
        User user = ActionContext.getCurrentUserInThread();
        
     
        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            
          /*  vo.setBegin_date(formatToDate(obj.getString("BEGIN_DATE")));
            vo.setEnd_date(formatToDate(obj.getString("END_DATE")));*/
            vo.setValueFromJson(obj);//把页面的值给属性
            vo.setPerson_name(vo.getPerson_name().trim());
            vo.setShenfenzheng(StringUtils.upperCase(vo.getShenfenzheng()));//转大写
           
            String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
           
            vo.setSg_company_uid(ActionContext.getCurrentUserInThread().getIdCard());//设置公司ID
            if("".equals(status)||"1".equals(status)||"40".equals(status)){
            	 vo.setSg_person_library_uid(null);//置主键为空
                 vo.setStatus("40");
                 vo.setCreated_date(new Date());
                 vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
                 vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
            }
            else{
               vo.setSg_person_library_uid(null);
               vo.setStatus("30");//给该用户赋予未提交状态
               vo.setUpdate_date(new Date());
               vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
               vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());

            }
            vo.setSerial_no(vo.getSg_person_library_uid());
            BaseDAO.insert(conn, vo);
            insertFiles(fileList, fileLinks, vo.getSg_person_library_uid(), conn);
            LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<人员信息>新增", user, "", "");
            
            JSONArray zhengshuArray = null;
            try{
            	zhengshuArray = obj.getJSONArray("ZHUANYE_NAME");
            }catch(JSONException e){
            	System.out.println("没有添加增项资质!");
            	logger.error("人员信息表新增失败!");
            }
            //处理资格证书
            if(zhengshuArray!=null){
            	for (int i = 0; i < zhengshuArray.size(); i++) {//判断添加到子表的记录的数量
					if(StringUtils.isBlank((String) zhengshuArray.get(i))){
						continue;
					}
					//获取索引
					voZhengshuVO = new SgPersonZhengshuVO();
			        fileLinks = obj.getJSONArray(voZhengshuVO.getVOTableName()+"_FILEUPLOAD").getString(i);
					voZhengshuVO.setSg_person_zhengshu_uid(DBUtil.getSequenceValue("SG_PERSON_ZHENGSHU_UID"));
					voZhengshuVO.setSg_person_uid(vo.getSg_person_library_uid());
					voZhengshuVO.setSg_zhengshu_uid(obj.getJSONArray("ZHENGSHU_NAME").getString(i));
					voZhengshuVO.setSg_zizhi_uid(obj.getJSONArray("ZHUANYE_NAME").getString(i));
					voZhengshuVO.setZhengshu_code(obj.getJSONArray("ZHENGSHU_CODE").getString(i));
					voZhengshuVO.setBegin_date(formatToDate(obj.getJSONArray("ZSBEGIN_DATE").getString(i)));
					voZhengshuVO.setEnd_date(formatToDate(obj.getJSONArray("ZSEND_DATE").getString(i)));
					//DBUtil.getSequenceValue(seqname)
		            
		            //插入用户/企业
					voZhengshuVO.setCreated_date(new Date());
					voZhengshuVO.setCreated_name(ActionContext.getCurrentUserInThread().getName());
					voZhengshuVO.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
					
		            //排序号
					//voZhengshuVO.setSerial_no(voZhengshuVO.getSg_person_zhengshu_uid());
		            //插入证书信息
		            BaseDAO.insert(conn, voZhengshuVO);
	        		LogManager.writeUserLog(voZhengshuVO.getSg_person_zhengshu_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<证书信息>新增", user, "", "");

		            insertFiles(fileList, fileLinks, voZhengshuVO.getSg_person_zhengshu_uid(), conn);

				}
            }
            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("人员信息表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}
	
	
     private Date formatToDate(String dateStr){
    		if (!Pub.empty(dateStr)) {
    			String val = dateStr.replaceAll("-", "")
    					.replaceAll("_", "").replaceAll(":", "")
    					.replaceAll("/", "").replaceAll(" ", "").trim()
    					+ "00000000000000";
    			return Pub.toDate("yyyyMMddHHmmss", val.substring(0, 14));
    		}
    		return null;
    	}
	public String queryCondition(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
            String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
            
           // condition += " and a.sg_company_uid = "+user.getIdCard()+" ";//加上公司ID来进行查询

            String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
            condition += orderFilter;//把查询出来的数据进行排序
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);

            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY_PERSON_ALL, page);//连接数据库，进行查询，结果集给bs
            bs.setFieldDic("SHENHE_JIEGUO", "SHZT");//把状态绑定到审核结果的字段上
            bs.setFieldDic("JZ_SJFW", "SUODING");
          //时间格式的修改
            bs.setFieldDateFormat("TIJIAO_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("JZ_DATE", "yyyy-MM-dd HH:mm:ss");
            
            domresult = bs.getJson();//把转换好的数据给domresult

        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("人员信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	public String queryConditionNotNull(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
            String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
            
            condition += " and a.shenhe_jieguo is null ";
            
            String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
            condition += orderFilter;//把查询出来的数据进行排序
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            conn.setAutoCommit(false);

           
            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY_PERSON_ALL, page);//连接数据库，进行查询，结果集给bs
           //时间格式的修改
            bs.setFieldDateFormat("TIJIAO_DATE", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
            
            domresult = bs.getJson();//把转换好的数据给domresult
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            logger.error("人员信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	public String update(String json,Map fileList) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgPersonLibraryVO vo = new SgPersonLibraryVO();
        User user = ActionContext.getCurrentUserInThread();


        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            vo.setPerson_name(vo.getPerson_name().trim());
            vo.setShenfenzheng(StringUtils.upperCase(vo.getShenfenzheng()));
            
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
            
           
            //增加资格证书
            String zhengshuList = getZhengshu(conn, obj, vo,fileList);
            if(!"".equals(zhengshuList)){
            	zhengshuList = zhengshuList.substring(0,zhengshuList.length()-1);
        		//清楚多余页面被删掉的资格证书
        		String clearzhengshu_SQL = "delete from sg_person_zhengshu t where t.sg_person_uid = "+vo.getSg_person_library_uid()+" and t.sg_person_zhengshu_uid not in ("+zhengshuList+")";
        		DBUtil.exec(conn, clearzhengshu_SQL);
        		//LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<证书信息>新增", user, "", "");

        	}else{
        		//删除所有资格证书
            	String clearzhengshu_SQL = "delete from sg_person_zhengshu t where t.sg_person_uid = "+ vo.getSg_person_library_uid();
        		DBUtil.exec(conn, clearzhengshu_SQL);
        		//LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<证书信息>新增", user, "", "");

        	}
            
            
            BaseDAO.update(conn, vo);
    		LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<人员以及证书信息>更改", user, "", "");

            //insertFiles(fileList, fileLinks, vo.getSg_person_library_uid(), conn);
            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("人员信息表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}
	
	public String queryList(String json) throws Exception {
		  Connection conn = DBUtil.getConnection();
	        String domresult = "";
	        try {

	            // 组织查询条件
	            PageManager page = RequestUtil.getPageManager(json);//设置获取查询后得到的记录数通过Util中的方法进行分页
	            String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
	            String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
	            //condition += BusinessUtil.getSJYXCondition(null);
	            //condition += BusinessUtil.getCommonCondition(user, null);
	            condition += orderFilter;//把查询出来的数据进行排序
	            if (page == null)
	                page = new PageManager();
	            page.setFilter(condition);

	            conn.setAutoCommit(false);

	            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY_PERSON_ALL, page);//连接数据库，进行查询，结果集给bs
	          //时间格式的修改
	            bs.setFieldDateFormat("TIJIAO_DATE", "yyyy-MM-dd HH:mm:ss");
	            bs.setFieldDateFormat("SHENHE_DATE", "yyyy-MM-dd HH:mm:ss");
	            
	            domresult = bs.getJson();//把转换好的数据给domresult
	           
	        } catch (Exception e) {
	            DBUtil.rollbackConnetion(conn);
	            e.printStackTrace(System.out);
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return domresult;
	}

	
	public String insert(String json) throws Exception {
		
		return null;
	}
	/**
	 * insert资格证书
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getZhengshu( Connection conn , JSONObject obj, SgPersonLibraryVO vo,Map fileList) throws Exception{
		JSONArray zhengshuArray = null;
		String emptyZhengshu = "";
		SgPersonZhengshuVO zhengshuVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	zhengshuArray = obj.getJSONArray("ZHENGSHU_NAME");
        }catch(JSONException e){
        	System.out.println("没有添加资格证书!");
        }
        
        //处理增项资质
       
        if(zhengshuArray!=null){
        	for (int i = 0; i < zhengshuArray.size(); i++) {
				if(StringUtils.isBlank((String) zhengshuArray.get(i))){
					continue;
				}
				//获取索引
				zhengshuVO = new SgPersonZhengshuVO();
	            String zeng_uid = obj.getJSONArray("SG_PERSON_ZHENGSHU_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            }else{
	            	zhengshuVO.setSg_person_zhengshu_uid(zeng_uid);
	            }
	            
	            zhengshuVO.setSg_person_uid(vo.getSg_person_library_uid());
	            zhengshuVO.setSg_zhengshu_uid(obj.getJSONArray("ZHENGSHU_NAME").getString(i));
	            zhengshuVO.setSg_zizhi_uid(obj.getJSONArray("ZHUANYE_NAME").getString(i));
	            zhengshuVO.setZhengshu_code(obj.getJSONArray("ZHENGSHU_CODE").getString(i));
	            zhengshuVO.setBegin_date(formatToDate(obj.getJSONArray("ZSBEGIN_DATE").getString(i)));
	            zhengshuVO.setEnd_date(formatToDate(obj.getJSONArray("ZSEND_DATE").getString(i)));
	          
	            //User  user = ActionContext.getCurrentUserInThread();

	           
	            //插入增项资质信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	//插入用户/企业
	            	zhengshuVO.setUpdate_date(new Date());
		            zhengshuVO.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
		            zhengshuVO.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            	BaseDAO.update(conn, zhengshuVO);
	        		LogManager.writeUserLog(zhengshuVO.getSg_person_zhengshu_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<证书信息>更改", user, "", "");

	            }else{
	            	//更新用户/企业
	            	zhengshuVO.setCreated_date(new Date());
		            zhengshuVO.setCreated_name(ActionContext.getCurrentUserInThread().getName());
		            zhengshuVO.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
		             
	            	BaseDAO.insert(conn, zhengshuVO);
	        		LogManager.writeUserLog(zhengshuVO.getSg_person_zhengshu_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<证书信息>新增", user, "", "");

	            	String fileLinks = obj.getJSONArray(zhengshuVO.getVOTableName()+"_FILEUPLOAD").getString(i);
		            
	            	insertFiles(fileList, fileLinks, zhengshuVO.getSg_person_zhengshu_uid(), conn);
	            }
	      
	            
	            emptyZhengshu += "'"+zhengshuVO.getSg_person_zhengshu_uid()+"',";
			}
        }
        return emptyZhengshu;
	}
	
//添加文件	
	private void insertFiles(Map files,String fileLinks, String target_uid ,Connection conn){
        User user = ActionContext.getCurrentUserInThread();

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
								vo.setCreated_date(new Date());
								vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
								vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
								BaseDAO.insert(conn, vo);
				        		LogManager.writeUserLog(vo.getAt_fileupload_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<附件信息>新增", user, "", "");

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

//查询数据库中是否有该记录
	public String queryCodeIsEmpty(String json) throws Exception {
        Connection conn = DBUtil.getConnection();
        JSONArray array = new JSONArray();
        try {
        	
			String sql = " select count(*) rs,sg_person_uid from SG_PERSON_LIBRARY where STATUS <> 1 AND SHENFENZHENG = '"+json+"' group by Sg_Person_Uid";
	
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
            logger.error("人员信息查询失败!");
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
        	 sql = " select count(*) rs,sg_person_uid from SG_PERSON_LIBRARY where STATUS=1 AND SG_COMPANY_UID= '"+user.getIdCard()+"' AND SHENFENZHENG = '"+json+"' AND SG_PERSON_UID <> "+personUID+" group by Sg_Person_Uid";
        	}
        	else{
			 sql = " select count(*) rs,sg_person_uid from SG_PERSON_LIBRARY where STATUS=1 AND SG_COMPANY_UID= '"+user.getIdCard()+"' AND SHENFENZHENG = '"+json+"' group by Sg_Person_Uid";
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
            logger.error("库中人员信息查询失败!");
            e.printStackTrace(System.out);	 
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return array.toString();
	
	
	}
//提交审核更新状态
	public String updateShenhe(String json,Map fileList,String u_id,String status,String t_id) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgPersonLibraryVO vo = new SgPersonLibraryVO();
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
            String sql_find = " select sg_person_library_uid from SG_PERSON_LIBRARY where SG_PERSON_UID = '"+u_id+"' and STATUS=15";
			String[][] res = DBUtil.query(conn, sql_find);
			if(res!=null){
				 s = res[0][0];
		         
			}
			//给30状态的该数据添加数据
	        String t="";
            String sql_find_thirty = " select sg_person_library_uid from SG_PERSON_LIBRARY where SG_PERSON_LIBRARY_UID = "+t_id;
			String[][] res_th = DBUtil.query(conn, sql_find_thirty);
			if(res_th!=null){
				 t = res_th[0][0];
		         String sql_add="update SG_PERSON_LIBRARY set shenhe_jieguo='"+status+"' ,shenhe_ren='"+shenheRenId+"' where sg_person_library_uid="+t;
		         DBUtil.exec(sql_add);
			}
			
			//然后把页面的值存入
            vo.setValueFromJson(obj);//把页面的值给属性
            vo.setPerson_name(vo.getPerson_name().trim());
            vo.setShenfenzheng(StringUtils.upperCase(vo.getShenfenzheng()));
            String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
            
            vo.setStatus(status);
           // vo.setShenhe_yijian(yj);
            vo.setUpdate_date(new Date());
            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(ActionContext.getCurrentUserInThread().getUserSN());
            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
            //判断是否有该人员，并获取该公司id
            if(!updateYczxx(conn, vo.getSg_person_library_uid(),vo.getSg_company_uid())){
            	vo.setDenglu_code(getDengluCode(conn, vo.getSg_person_library_uid()));
            	vo.setMima("123456");
            	vo.setPwd(DigestUtils.md5Hex("123456"));
            }
            //增加资格证书
            String zhengshuList = getZhengshu(conn, obj, vo,fileList);
            if(!"".equals(zhengshuList)){
            	zhengshuList = zhengshuList.substring(0,zhengshuList.length()-1);
        		//清楚多余页面被删掉的资格证书
        		String clearzhengshu_SQL = "delete from sg_person_zhengshu t where t.sg_person_uid = "+vo.getSg_person_library_uid()+" and t.sg_person_zhengshu_uid not in ("+zhengshuList+")";
        		DBUtil.exec(conn, clearzhengshu_SQL);
        	}else{
        		//删除所有资格证书
            	String clearzhengshu_SQL = "delete from sg_person_zhengshu t where t.sg_person_uid = "+ vo.getSg_person_library_uid();
        		DBUtil.exec(conn, clearzhengshu_SQL);
        	}
            
            //****************************消息添加【fs_message_info】
            /*
            Map messageMap= new HashMap();
            messageMap.put("TITLE", "施工人员信息审核");
            messageMap.put("CONTENT", "人员<a&nbsp;href='javascript:void(0)'&nbsp;onclick='zxdtView("+vo.getSg_person_uid()+",'sgrysh')'>"+vo.getPerson_name()+"</a>信息<span&nbsp;style='color:blue;'>审核通过</span>");
            messageMap.put("USERTO", jsCompanyVO.getUser_name());
            messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
            messageMap.put("SYSTEM_TYPE", "JS");
            messageMap.put("COMPANY_UID", jscompany);
            messageMap.put("MSG_TYPE", Constants.FS_MESSAGE_INFO_MSG_TYPE_JSXM);
            messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_XMXXWH);
            messageMap.put("MSG_VIEW_TYPE", "1");
            messageMap.put("PRM1", jsproject);
            
            fsMessageInfoService.insertVo(messageMap);
            */
	
            BaseDAO.update(conn, vo);
    		LogManager.writeUserLog(vo.getSg_person_library_uid(), ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对<人员以及证书信息>提交审核", user, "", "");

            //resultVO = vo.getRowJson();

            conn.commit();
                
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("人员提交审核失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return s;
	}
	//生成登录code
	private String getDengluCode(Connection conn , String uid) throws Exception{
		String code = null;
		String s = null;
		try {
			String sql_company="select denglu_code from sg_enterprise_library where status=1 and  sg_company_uid=(select sg_company_uid from sg_person_library where sg_person_library_uid="+uid+")";
			String[][] ids = DBUtil.query(conn ,sql_company);
			if(ids!=null){
				 s = ids[0][0];
		         //企业登录code 如：WNDSG00001
			}
			//截取登录code 如：WNDSG00001R006从第12个开始去三个“006”
			String sql = "select  max(to_number(substr(t.denglu_code,12,3))) from sg_person_library t where t.denglu_code like '"+s+"R%'";//like WNDSG00001R%
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
			code = s +"R"+code;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return code;
	}
	private boolean updateYczxx(Connection conn , String uid , String conpanyuid) throws Exception{
		boolean flag = true;
		try {
			String sql = "select n.sg_person_library_uid from sg_person_library n where n.status = 1 and n.sg_company_uid="+conpanyuid+" and n.sg_person_uid = (select t.sg_person_uid from sg_person_library t where t.sg_person_library_uid = "+uid+")";
			
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
	public String updatePass(String u_id,String js) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        SgPersonLibraryVO vo = new SgPersonLibraryVO();
        User user = ActionContext.getCurrentUserInThread();
        int z_id=Integer.parseInt(js);
        try {
        	
        	conn.setAutoCommit(false);
            String new_id="";
            String s="";

            String sql_find = " select sg_person_library_uid from SG_PERSON_LIBRARY where SG_PERSON_UID = '"+u_id+"' and STATUS=1";
			String[][] res = DBUtil.query(conn, sql_find);
			if(res!=null){
				 s = res[0][0];
		         String sql_update="update SG_PERSON_LIBRARY set STATUS=5  where SG_PERSON_LIBRARY_UID="+s;
		         DBUtil.exec(sql_update);
			}
            new_id = DBUtil.getSequenceValue("SG_PERSON_LIBRARY_UID");
			//复制10状态的数据到1该人员的信息
			String copy_sql = " insert into SG_PERSON_LIBRARY (SG_PERSON_LIBRARY_UID, ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE) "
							+" select "+new_id+", ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION,1,UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE FROM SG_PERSON_LIBRARY" 
							+" where SG_PERSON_LIBRARY_UID ="+z_id;
					
			DBUtil.exec(copy_sql);

			//复制附件信息
			String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
							+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
							+" where TARGET_UID ="+z_id+" AND BUSINESS_SUB_TYPE='SG_PERSON_LIBRARY'";//(select sg_person_library_uid from sg_person_library where status = 1 and sg_person_uid = "+u_id+") ";
				
			DBUtil.exec(copy_file);
				    
			
            //对在10状态的子表的数据进行复制和新增
    		String sql = "select SG_PERSON_ZHENGSHU_UID from SG_PERSON_ZHENGSHU where SG_PERSON_UID = "+js;//(select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where SG_PERSON_UID='"+u_id+"' and status=10)";//(select SG_PERSON_LIBRARY_UID from SG_PERSON_LIBRARY where status = 3 and sg_person_uid = "+u_id+")";
			String[][] results = DBUtil.query(conn,sql);
			if(results==null){
				return null;
			};
			
			logger.info("<{}>执行操作【证书删除】",user.getName());

			for (int i = 0; i < results.length; i++) {
				
				
				String new_idZS = DBUtil.getSequenceValue("SG_PERSON_ZHENGSHU_UID");
				String copy_zhengshu_sql = "insert into SG_PERSON_ZHENGSHU (SG_PERSON_ZHENGSHU_UID, SG_PERSON_UID,SG_ZIZHI_UID,SG_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE) "
						+ "select "+new_idZS+", "+new_id+", SG_ZIZHI_UID,SG_ZHENGSHU_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, ZHENGSHU_CODE,BEGIN_DATE,END_DATE from SG_PERSON_ZHENGSHU where SG_PERSON_ZHENGSHU_UID = "+results[i][0];
				DBUtil.exec(conn, copy_zhengshu_sql);
				String copy_zhengshu_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
					+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_idZS+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
					+" where TARGET_UID = "+results[i][0]+" AND BUSINESS_SUB_TYPE='SG_PERSON_ZHENGSHU'";
				DBUtil.exec(copy_zhengshu_file);
				logger.info("<{}>执行入库操作【证书新增数据及附件】",user.getName());
			}
            resultVO = vo.getRowJson();

            conn.commit();
                
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("人员提交审核失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}



	//审核时，把30状态的数据复制一条状态为15的，然后返回15的id，对状态15的数据进行入库、退回的判断。t_id为30状态该数据的ID，u_id为该数据的person_uid
	public String updateCopyPerson(String t_id,String u_id) throws Exception {
		Connection conn = null;
		//String person_uid = "";
		String tid=t_id;
		
		String new_id="";
		
		try {
			conn = DBUtil.getConnection();
			User user = ActionContext.getCurrentUserInThread();
			//person_uid = user.getIdCard();//获取该人员的ID
			
			//查询15状态是否有记录
			
			String sql = " select sg_person_library_uid from SG_PERSON_LIBRARY where SG_PERSON_UID = '"+u_id+"' and STATUS=15";
			String s="";
	        String[][] res = DBUtil.query(conn, sql);
			if(res!=null){
				 s = res[0][0];
				
			}
			
			//if(StringUtils.isNotBlank(person_uid)){
				//删除主表中15状态的记录
				
				String SQL_DELETE="DELETE FROM SG_PERSON_LIBRARY WHERE SG_PERSON_UID = '"+u_id+"' and STATUS=15";
				
				DBUtil.exec(SQL_DELETE);
				if(!s.equals("")&&s!=null){
				//删除改记录附件
				String SQL_DELETE_FJ="DELETE FROM at_fileupload WHERE TARGET_UID="+s;
				DBUtil.exec(SQL_DELETE_FJ);
				
				}
				    new_id = DBUtil.getSequenceValue("SG_PERSON_LIBRARY_UID");
					//复制该人员的信息
					String copy_sql = " insert into SG_PERSON_LIBRARY (SG_PERSON_LIBRARY_UID, ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE) "
							+" select "+new_id+", ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION,15,UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE FROM SG_PERSON_LIBRARY" 
							+" where sg_person_library_uid ="+tid+"  and status=30";
					
					DBUtil.exec(copy_sql);

					//复制附件信息
					String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
							+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
							+" where TARGET_UID = (select sg_person_library_uid from sg_person_library where status = 30 and sg_person_library_uid = "+tid+")  AND BUSINESS_SUB_TYPE='SG_PERSON_LIBRARY'";
				
					DBUtil.exec(copy_file);
				

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
	public String updateCopyPerson2(String status,String IdCard,String shenfenID) throws Exception {
		Connection conn = null;
		String person_uid = "";
		String uid=IdCard;
		int sta=Integer.parseInt(status);
      
		String new_id="";
		try {
			conn = DBUtil.getConnection();
			User user = ActionContext.getCurrentUserInThread();
			person_uid = user.getIdCard();//获取该人员的ID
			
//查询是否有记录
			
			String sql = " select sg_person_library_uid from SG_PERSON_LIBRARY where SHENFENZHENG = '"+shenfenID+"' and STATUS='"+sta+"'";
			String s="";
	        String[][] res = DBUtil.query(conn, sql);
			if(res!=null){
				 s = res[0][0];
				
			}
			
			if(StringUtils.isNotBlank(person_uid)){
			
				//删除主表中1状态的记录
				//if(sta==1||sta==10){
				String SQL_DELETE="DELETE FROM SG_PERSON_LIBRARY WHERE SHENFENZHENG='"+shenfenID+"' AND STATUS='"+sta+"'";
				
				DBUtil.exec(SQL_DELETE);
				if(!s.equals("")&&s!=null){
				//删除改记录附件
				String SQL_DELETE_FJ="DELETE FROM at_fileupload WHERE TARGET_UID="+s;
				DBUtil.exec(SQL_DELETE_FJ);
				
				}
					 new_id = DBUtil.getSequenceValue("SG_PERSON_LIBRARY_UID");
						
						//复制该人员的信息
						String copy_sql = " insert into SG_PERSON_LIBRARY (SG_PERSON_LIBRARY_UID, ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE) "
								+" select "+new_id+", ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION,"+sta+",UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE FROM SG_PERSON_LIBRARY" 
								+" where sg_person_library_uid ="+uid+"  and status=30";
						
						DBUtil.exec(copy_sql);
						
						//复制附件信息
						String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
								+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
								+" where TARGET_UID = (select sg_person_library_uid from sg_person_library where status = 30 and sg_person_library_uid = "+uid+")  AND BUSINESS_SUB_TYPE='SG_PERSON_LIBRARY'";
					
						DBUtil.exec(copy_file);
			}
				conn.commit();
			
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return new_id;
	}
//退回
	public String tuihui(String ids, String yijian) throws Exception {
		Connection conn = DBUtil.getConnection();
		String new_id="";
		String[] arr = ids.split(",");
		
		String shenheRenId=ActionContext.getCurrentUserInThread().getUserSN();
		try {
			for (int i = 0; i < arr.length; i++) {
				String sql_update="update SG_PERSON_LIBRARY set SHENHE_JIEGUO=20, SHENHE_DATE=sysdate ,SHENHE_REN='"+shenheRenId+"' ,SHENHE_YIJIAN='"+yijian+"' where SG_PERSON_LIBRARY_UID="+arr[i];
				DBUtil.exec(sql_update);
				
				 new_id = DBUtil.getSequenceValue("SG_PERSON_LIBRARY_UID");
				//复制该人员的信息
				String copy_sql = " insert into SG_PERSON_LIBRARY (SG_PERSON_LIBRARY_UID, ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, STATUS, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE) "
						+" select "+new_id+", ZHUANYE1, ZHUANYE2, ZHICHENG_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, SYSDATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, SG_PERSON_UID, SG_COMPANY_UID, DENGLU_CODE, PWD, MIMA, PERSON_NAME, SEX, BIRTHDAY, SHENFENZHENG, PHOTO, NATION, PHONE, EMAIL, XUELI, COLLEGE, ZHICHENG_CODE, JISHU_Y, FZR_Y, BEGIN_DATE, END_DATE, DESCRIPTION, 20, UPDATED_DATE, TIJIAO_DATE, SHENHE_REN, SHENHE_JIEGUO, SHENHE_YIJIAN, SHENHE_DATE, JZ_Y, JZ_YUANYIN, JZ_SJFW, JZ_DATE,SCORE FROM SG_PERSON_LIBRARY" 
						+" where sg_person_library_uid ="+arr[i]+"  and status=30";
				
				DBUtil.exec(copy_sql);
	
				//复制附件信息
				String copy_file = "insert into at_fileupload (AT_FILEUPLOAD_UID, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, UPDATE_DATE, SERIAL_NO, TARGET_TYPE, TARGET_UID, FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME) "
						+" select AT_FILEUPLOAD_UID.Nextval, EVENT_UID, ENABLED, DESCRIBE, CREATED_UID, CREATED_NAME, CREATED_DATE, UPDATE_UID, UPDATE_NAME, SYSDATE, SERIAL_NO, TARGET_TYPE, "+new_id+", FILE_TYPE, FILE_NAME, EXT_NAME, DOC_SIZE, MIME_TYPE, BUSINESS_SUB_TYPE, FILE_SAVE_NAME from at_fileupload "
						+" where TARGET_UID = (select sg_person_library_uid from sg_person_library where status = 30 and sg_person_library_uid = "+arr[i]+")  AND BUSINESS_SUB_TYPE='SG_PERSON_LIBRARY'";
			
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
	
	public String suoding(String id, String yijian,String sjfw,String jz_y) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
		String sql_add="update SG_PERSON_LIBRARY set JZ_Y='"+jz_y+"' ,JZ_YUANYIN='"+yijian+"' ,JZ_SJFW='"+sjfw+"' ,JZ_DATE=sysdate where sg_person_library_uid="+id;
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
		String sql_add="update SG_PERSON_LIBRARY set JZ_Y='' where sg_person_library_uid="+id;
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


