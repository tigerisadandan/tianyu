/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywJsgcsgxkzsqDao.java
 * 创建日期： 2014-05-27 下午 03:04:23
 * 功能：   施工许可申请表-明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 03:04:23  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.common.vo.AtFileuploadVO;
import com.ccthanking.business.sp.dao.BuSpywJsgcsgxkzsqDao;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqMxVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywJsgcsgxkzsqDao.java </p>
 * <p> 功能：施工许可申请表-明细 </p>
 *
 * <p><a href="BuSpywJsgcsgxkzsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

@Component
public class BuSpywJsgcsgxkzsqDaoImpl  extends BsBaseDaoTJdbc implements BuSpywJsgcsgxkzsqDao {
	
	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcsgxkzsqDaoImpl.class);
	
	/*private static String SQL_QUERY="select DT_IDS ,sgxkzsq_uid, ywlz_uid, jsdwmc, syzxz, jsdwdz, frdb, zzjgdm, xm_jsdw, xb_jsdw, sfzh_jsdw, lxdh_jsdw, gcmc, jslb, xmjsdz,"
      + "jhkgrq_date, jhjgrq_date, xmhzwh, xmhzrq_date, tzze, jzmj, htzj, cjdwmc_kc, zzzsh_kc, fzr_kc, zgzsh_kc,"
      + "cjdwmc_sj, zzzsh_sj, fzr_sj, zgzsh_sj, cjdwmc_sg, zzzsh_sg, fzr_sg, zgzsh_sg, cjdwmc_jl, zzzsh_jl, fzr_jl,"
      + "zgzsh_jl, wjbh_lxpw, ffrq_lxpw, wjbh_tdsyz, ffrq_tdsyz, wjbh_ghxk, ffrq_ghxk, wjbh_zfcn, ffrq_zfcn from bu_spyw_jsgcsgxkzsq";*/
	
	private static String SQL_QUERY="select bu_sp_ywlz.JS_COMPANY_UID,DT_IDS ,sgxkzsq_uid, bu_spyw_jsgcsgxkzsq.ywlz_uid, jsdwmc, syzxz, jsdwdz, frdb, zzjgdm, xm_jsdw, xb_jsdw, sfzh_jsdw, lxdh_jsdw, gcmc, jslb, xmjsdz,"
		      + "jhkgrq_date, jhjgrq_date, xmhzwh, xmhzrq_date, tzze, jzmj, htzj, cjdwmc_kc, zzzsh_kc, fzr_kc, zgzsh_kc,"
		      + "cjdwmc_sj, zzzsh_sj, fzr_sj, zgzsh_sj, cjdwmc_sg, zzzsh_sg, fzr_sg, zgzsh_sg, cjdwmc_jl, zzzsh_jl, fzr_jl,"
		      + "zgzsh_jl, wjbh_lxpw, ffrq_lxpw, wjbh_tdsyz, ffrq_tdsyz, wjbh_ghxk, ffrq_ghxk, wjbh_zfcn, ffrq_zfcn from bu_spyw_jsgcsgxkzsq " +
		      "left join bu_sp_ywlz  on bu_sp_ywlz.ywlz_uid=bu_spyw_jsgcsgxkzsq.ywlz_uid ";
	
	private static String SQL_QUERY_File="select at_fileupload_uid,  target_uid, file_type, file_name,  doc_size,  business_sub_type," +
			" file_save_name from at_fileupload ";
	
	public List<Map<String, String>> getFileName(String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		String ListMX="select at_fileupload_uid,  target_uid, file_type, file_name,  doc_size,  business_sub_type, file_save_name from at_fileupload where target_uid="+id;
		List<Map<String, String>> bs = DBUtil.queryReturnList(conn, ListMX);
		
		return bs;
	}
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	  PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
              String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
              
              String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
              condition += orderFilter;//把查询出来的数据进行排序
              if (page == null)
                  page = new PageManager();
              page.setFilter(condition);

              conn.setAutoCommit(false);

             
              BaseResultSet bs = DBUtil.query(conn, SQL_QUERY, page);//连接数据库，进行查询，结果集给bs
             
              domresult = bs.getJson();//把转换好的数据给domresult
          } catch (Exception e) {
        	DBUtil.rollbackConnetion(conn);
            logger.error("建设工程施工许可证信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    //查询附件的方法
    public String queryReadFile(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	  PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
              String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句
              
              String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
              condition += orderFilter;//把查询出来的数据进行排序
              if (page == null)
                  page = new PageManager();
              page.setFilter(condition);

              conn.setAutoCommit(false);

             
              BaseResultSet bs = DBUtil.query(conn, SQL_QUERY_File,page);//连接数据库，进行查询，结果集给bs
             
              domresult = bs.getJson();//把转换好的数据给domresult
          } catch (Exception e) {
        	DBUtil.rollbackConnetion(conn);
            logger.error("附件信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
	public String insert(String json, Map files) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywJsgcsgxkzsqVO vo = new BuSpywJsgcsgxkzsqVO();
        BuSpywJsgcsgxkzsqMxVO mvo=new BuSpywJsgcsgxkzsqMxVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
        vo.setSgxkzsq_uid(DBUtil.getSequenceValue("SGXKZSQ_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
        
        vo.setSerial_no(vo.getSgxkzsq_uid());
        BaseDAO.insert(conn, vo);
        
        insertFiles(files, fileLinks, vo.getSgxkzsq_uid(), conn);

        JSONArray sqmxArray = null;
        try{
        	sqmxArray = obj.getJSONArray("GCMX");
        }catch(JSONException e){
        	logger.error("没有添加工程申请明细!");
        }
        //处理申请明细
        if(sqmxArray!=null){
        	for (int i = 0; i < sqmxArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) sqmxArray.get(i))){
					continue;
				}
				//获取索引
		        mvo.setSgxkzsqmx_uid(DBUtil.getSequenceValue("SGXKZSQMX_UID"));
		        mvo.setSgxkzsq_uid(vo.getSgxkzsq_uid());
		        mvo.setGcmx(obj.getJSONArray("GCMX").getString(i));
		        mvo.setGczj(obj.getJSONArray("GCZJ").getString(i));
				mvo.setJzmj(obj.getJSONArray("MX_JZMJ").getString(i));
				mvo.setCs(obj.getJSONArray("CS").getString(i));
				mvo.setCd(obj.getJSONArray("CD").getString(i));
				mvo.setYt(obj.getJSONArray("YT").getString(i));
				mvo.setJglx(obj.getJSONArray("JGLX").getString(i));
				//DBUtil.getSequenceValue(seqname)
	            
	            //插入用户/企业
				mvo.setCreated_date(new Date());
				mvo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
				mvo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
				
	           
	            BaseDAO.insert(conn, mvo);

			}
        }
        resultVO = vo.getRowJson();
        
        conn.commit();
        }catch(Exception e){
        	
        	DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("建设工程施工许可证信息表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
		
        }
        
	//添加文件	
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
								vo.setCreated_date(new Date());
								vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
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

	public String update(String json, Map files) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywJsgcsgxkzsqVO vo = new BuSpywJsgcsgxkzsqVO();
        User user = ActionContext.getCurrentUserInThread();


        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
            
        	//String fileLinks = obj.getJSONArray(vo.getVOTableName()+"_FILEUPLOAD").getString(0);
        	String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
        	
        	insertFiles(files, fileLinks, vo.getSgxkzsq_uid(), conn);
            //增加申请明细
            String MXList = getMX(conn, obj, vo,files);
            if(!"".equals(MXList)){
            	MXList = MXList.substring(0,MXList.length()-1);
        		//清楚多余页面被删掉的申请明细
        		String clearMX_SQL = "delete from bu_spyw_jsgcsgxkzsq_mx t where t.sgxkzsq_uid = "+vo.getSgxkzsq_uid()+" and t.sgxkzsqmx_uid not in ("+MXList+")";
        		DBUtil.exec(conn, clearMX_SQL);

        	}else{
        		//删除所有申请明细
            	String clearzhengshu_SQL = "delete from bu_spyw_jsgcsgxkzsq_mx t where t.sgxkzsq_uid = "+ vo.getSgxkzsq_uid();
        		DBUtil.exec(conn, clearzhengshu_SQL);

        	}
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("建设工程施工许可证信息表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}

	/**
	 * insert资格证书
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getMX( Connection conn , JSONObject obj, BuSpywJsgcsgxkzsqVO vo,Map fileList) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywJsgcsgxkzsqMxVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("GCMX");
        }catch(JSONException e){
        	logger.error("没有添加工程明细!");
        }
        
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywJsgcsgxkzsqMxVO();
	            String zeng_uid = obj.getJSONArray("SGXKZSQMX_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            }else{
	            	MXVO.setSgxkzsqmx_uid(zeng_uid);
	            }
	            
	            MXVO.setSgxkzsq_uid(vo.getSgxkzsq_uid());
	            MXVO.setGcmx(obj.getJSONArray("GCMX").getString(i));
	            MXVO.setGczj(obj.getJSONArray("GCZJ").getString(i));
	            MXVO.setJzmj(obj.getJSONArray("MX_JZMJ").getString(i));
	            MXVO.setCs(obj.getJSONArray("CS").getString(i));
	            MXVO.setCd(obj.getJSONArray("CD").getString(i));
	            MXVO.setYt(obj.getJSONArray("YT").getString(i));
	            MXVO.setJglx(obj.getJSONArray("JGLX").getString(i));

	            //User  user = ActionContext.getCurrentUserInThread();

	           
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	
	            	MXVO.setUpdate_date(new Date());
	            	MXVO.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	
	            	MXVO.setCreated_date(new Date());
	            	MXVO.setCreated_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
		             
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            
	            emptyMX += "'"+MXVO.getSgxkzsqmx_uid()+"',";
			}
        }
        return emptyMX;
	}
    

	/**
	 * 通过业务流转UID来查数据ID
	 * */
	public String getIdByYwlzuid(String ywlzuid) {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
       
        try {
            String sql="select SGXKZSQ_UID from  BU_SPYW_JSGCSGXKZSQ where YWLZ_UID='"+ywlzuid+"' "; 
        	
            String[][] tem=DBUtil.querySql(conn, sql);
            resultVO=tem[0][0];
         
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("建设工程施工许可证信息表查询ID失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return resultVO;
	}
    // 在此可加入其它方法


}
