/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcnsDao.java
 * 创建日期： 2014-06-13 下午 05:31:52
 * 功能：   环境影响登记告知承诺
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-13 下午 05:31:52  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.common.vo.AtFileuploadVO;
import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcnsDao;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsNrgmVO;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsVO;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcnsZyssVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywHjyxdjgzcnsDao.java </p>
 * <p> 功能：环境影响登记告知承诺 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcnsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-13
 * 
 */

@Component
public class BuSpywHjyxdjgzcnsDaoImpl  extends BsBaseDaoTJdbc implements BuSpywHjyxdjgzcnsDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcnsDaoImpl.class);
	private static String SQL_QUERY="select hjyxdjgzcns_uid, ywlz_uid, xmmc, jsdw, tbrq_date," +
			" frdb, lxr, lxdh, cz, yzbm, txl, jsdd, jsxz, hylbjdm, zdmj, rhmj, ztz, hbtz, yqtcrq," +
			" yjgzr, syt, s_xhl, ry_xhl, d_xhl, rq_xhl, rm_xhl, qt, qtqksm, scgylcjs, ncydwrfzcs from bu_spyw_hjyxdjgzcns";
	private static String SQL_QUERY_File="select at_fileupload_uid,  target_uid, file_type, file_name,  doc_size,  business_sub_type," +
	" file_save_name from at_fileupload ";

    public String queryCondition(String json){
    
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
            logger.error("环境影响登记告知承诺书信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    //查询附件的方法
    public String queryReadFile(String id) throws Exception {
        
    	Connection conn = DBUtil.getConnection();
    	JSONArray array = new JSONArray();
        String ListMX="select file_save_name,at_fileupload_uid from at_fileupload where target_uid="+id+" and file_type=39 and business_sub_type='BU_SPYW_HJYXDJGZCNS'";
    	String filePath="";
    	String fileName="";
    	String uid="";
    	try {
    		
	        String[][] res = DBUtil.query(conn, ListMX);
			if(res!=null){
				//fileName=res[0][0];
				SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEUPLOADROOT");
	    		String fileRoot = syspara.PARAVALUE1;
	    		//filePath=fileRoot+"/"+fileName;
	    		
				for (int i = 0; i < res.length; i++) {
					JSONObject object = new JSONObject(); 
					
					//object.put("FILEPATH", filePath);
					object.put("FILENAME", res[i][0]);
					object.put("FILEROOT", fileRoot);
					object.put("AT_FILEUPLOAD_UID", res[i][1]);
					array.add(object);
				}
			}else{
				return null;
			}
			
          } catch (Exception e) {
        	DBUtil.rollbackConnetion(conn);
            logger.error("附件信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return array.toString();

    }
    public String insert(String json,Map files) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywHjyxdjgzcnsVO vo = new BuSpywHjyxdjgzcnsVO();
        BuSpywHjyxdjgzcnsNrgmVO nvo=new BuSpywHjyxdjgzcnsNrgmVO();
        BuSpywHjyxdjgzcnsZyssVO svo=new BuSpywHjyxdjgzcnsZyssVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
        String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
        vo.setHjyxdjgzcns_uid(DBUtil.getSequenceValue("HJYXDJGZCNS_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
        
        vo.setSerial_no(vo.getHjyxdjgzcns_uid());
        BaseDAO.insert(conn, vo);
        
        insertFiles(files, fileLinks, vo.getHjyxdjgzcns_uid(), conn);

        JSONArray sqmxArray = null;
        try{
        	sqmxArray = obj.getJSONArray("MC");
        }catch(JSONException e){
        	logger.error("没有添加主要设施!");
        }
        //处理主要设施
        if(sqmxArray!=null){
        	for (int i = 0; i < sqmxArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) sqmxArray.get(i))){
					continue;
				}
				//获取索引
		        svo.setHjyxdjgzcnszyss_uid(DBUtil.getSequenceValue("HJYXDJGZCNSZYSS_UID"));
		        svo.setHjyxdjgzcns_uid(vo.getHjyxdjgzcns_uid());
		        svo.setMc(obj.getJSONArray("MC").getString(i));
		        svo.setGg(obj.getJSONArray("GG").getString(i));
				svo.setSl(obj.getJSONArray("SL").getString(i));
				svo.setDw(obj.getJSONArray("DW").getString(i));
				
				//DBUtil.getSequenceValue(seqname)
	            
	          
				svo.setCreated_date(new Date());
				svo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
				svo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
				
	          
	            BaseDAO.insert(conn, svo);

			}
        }
        JSONArray nrgmArray = null;
        try{
        	nrgmArray = obj.getJSONArray("ZYCP_MC");
        }catch(JSONException e){
        	logger.error("没有添加内容规模!");
        }
        //处理内容规模
        if(nrgmArray!=null){
        	for (int i = 0; i < nrgmArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) nrgmArray.get(i))&&StringUtils.isBlank(obj.getJSONArray("ZYCP_MC").getString(i))&&StringUtils.isBlank(obj.getJSONArray("ZYYFCL_MC").getString(i))){
					continue;
				}
				//获取索引
		        nvo.setHjyxdjgzcnsnrgm_uid(DBUtil.getSequenceValue("HJYXDJGZCNSNRGM_UID"));
		        nvo.setHjyxdjgzcns_uid(vo.getHjyxdjgzcns_uid());
		        nvo.setZycp_mc(obj.getJSONArray("ZYCP_MC").getString(i));
		        nvo.setZycp_sl(obj.getJSONArray("ZYCP_SL").getString(i));
				nvo.setZyyfcl_mc(obj.getJSONArray("ZYYFCL_MC").getString(i));
				nvo.setZyyfcl_sl(obj.getJSONArray("ZYYFCL_SL").getString(i));
				
				
				nvo.setCreated_date(new Date());
				nvo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
				nvo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
				
	            
	            BaseDAO.insert(conn, nvo);

			}
        }
        resultVO = vo.getHjyxdjgzcns_uid();
        
        conn.commit();
        }catch(Exception e){
        	
        	DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("环境影响登记告知承诺书信息表新增失败!");
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

	public String update(String json,Map files) throws Exception {
		    Connection conn = DBUtil.getConnection();
	        String resultVO = null;
	        BuSpywHjyxdjgzcnsVO vo = new BuSpywHjyxdjgzcnsVO();
	        User user = ActionContext.getCurrentUserInThread();


	        try {
	            conn.setAutoCommit(false);
	            JSONArray list = vo.doInitJson(json);
	            JSONObject obj = (JSONObject) list.get(0);
	            vo.setValueFromJson(obj);//把页面的值给属性
	            
	            
	            vo.setUpdate_date(new Date());
	            vo.setUpdate_name(user.getName());
	            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            
	            String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
	        	
	        	insertFiles(files, fileLinks, vo.getHjyxdjgzcns_uid(), conn);
	            //增加项目内容规模信息
	            String MXList = getMX(conn, obj, vo);
	            if(!"".equals(MXList)){
	            	MXList = MXList.substring(0,MXList.length()-1);
	        		//清楚多余页面被删掉的内容规模信息
	        		String clear_nrgm_SQL = "delete from bu_spyw_hjyxdjgzcns_nrgm t where t.hjyxdjgzcns_uid = "+vo.getHjyxdjgzcns_uid()+" and t.hjyxdjgzcnsnrgm_uid not in ("+MXList+")";
	        		DBUtil.exec(conn, clear_nrgm_SQL);

	        	}else{
	        		//删除所有内容规模信息
	            	String clear_nrgm_SQL = "delete from bu_spyw_hjyxdjgzcns_nrgm t where t.hjyxdjgzcns_uid = "+ vo.getHjyxdjgzcns_uid();
	        		DBUtil.exec(conn, clear_nrgm_SQL);

	        	}
	            //增加项目主要设施信息
	            String zyssList = getZYSS(conn, obj, vo);
	            if(!"".equals(zyssList)){
	            	zyssList = zyssList.substring(0,zyssList.length()-1);
	        		//清楚多余页面被删掉的内容规模信息
	        		String clear_zyss_SQL = "delete from bu_spyw_hjyxdjgzcns_zyss t where t.hjyxdjgzcns_uid = "+vo.getHjyxdjgzcns_uid()+" and t.hjyxdjgzcnszyss_uid not in ("+zyssList+")";
	        		DBUtil.exec(conn, clear_zyss_SQL);

	        	}else{
	        		//删除所有内容规模信息
	            	String clear_zyss_SQL = "delete from bu_spyw_hjyxdjgzcns_zyss t where t.hjyxdjgzcns_uid = "+ vo.getHjyxdjgzcns_uid();
	        		DBUtil.exec(conn, clear_zyss_SQL);

	        	}
	            
	            BaseDAO.update(conn, vo);

	            resultVO = vo.getRowJson();
	            
	            conn.commit();
	        } catch (Exception e) {
	            DBUtil.rollbackConnetion(conn);
	            e.printStackTrace(System.out);
	            logger.error("环境影响登记告知承诺书信息表更改失败!");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
			return null;
		}
	/**
	 * insert明细
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getMX( Connection conn , JSONObject obj, BuSpywHjyxdjgzcnsVO vo) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywHjyxdjgzcnsNrgmVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("ZYCP_MC");
        }catch(JSONException e){
        	logger.error("没有添加内容规模信息!");
        }
        
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))&&StringUtils.isBlank(obj.getJSONArray("ZYCP_MC").getString(i))&&StringUtils.isBlank(obj.getJSONArray("ZYYFCL_MC").getString(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywHjyxdjgzcnsNrgmVO();
	            String zeng_uid = obj.getJSONArray("HJYXDJGZCNSNRGM_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            	MXVO.setHjyxdjgzcnsnrgm_uid(DBUtil.getSequenceValue("HJYXDJGZCNSNRGM_UID"));
	            }else{
	            	MXVO.setHjyxdjgzcnsnrgm_uid(zeng_uid);
	            }
	            
	          
	            
	            MXVO.setHjyxdjgzcns_uid(vo.getHjyxdjgzcns_uid());
	            MXVO.setZycp_mc(obj.getJSONArray("ZYCP_MC").getString(i));
	            MXVO.setZycp_sl(obj.getJSONArray("ZYCP_SL").getString(i));
	            MXVO.setZyyfcl_mc(obj.getJSONArray("ZYYFCL_MC").getString(i));
	            MXVO.setZyyfcl_sl(obj.getJSONArray("ZYYFCL_SL").getString(i));
	            

	           
	            //插入增项资质信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	//插入用户/企业
	            	MXVO.setUpdate_date(new Date());
	            	MXVO.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	//更新用户/企业
	            	MXVO.setCreated_date(new Date());
	            	MXVO.setCreated_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
		             
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            
	            emptyMX += "'"+MXVO.getHjyxdjgzcnsnrgm_uid()+"',";
			}
        }
        return emptyMX;
	}
	public String getZYSS( Connection conn , JSONObject obj, BuSpywHjyxdjgzcnsVO vo) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywHjyxdjgzcnsZyssVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("MC");
        }catch(JSONException e){
        	logger.error("没有添加主要设施信息!");
        }
        
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywHjyxdjgzcnsZyssVO();
	            String zeng_uid = obj.getJSONArray("HJYXDJGZCNSZYSS_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            	MXVO.setHjyxdjgzcnszyss_uid(DBUtil.getSequenceValue("HJYXDJGZCNSZYSS_UID"));
	            }else{
	            	MXVO.setHjyxdjgzcnszyss_uid(zeng_uid);
	            }
	            
	          
	            
	            MXVO.setHjyxdjgzcns_uid(vo.getHjyxdjgzcns_uid());
	            MXVO.setMc(obj.getJSONArray("MC").getString(i));
	            MXVO.setGg(obj.getJSONArray("GG").getString(i));
	            MXVO.setSl(obj.getJSONArray("SL").getString(i));
	            MXVO.setDw(obj.getJSONArray("DW").getString(i));
	            

	           
	            //插入增项资质信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	//插入用户/企业
	            	MXVO.setUpdate_date(new Date());
	            	MXVO.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	//更新用户/企业
	            	MXVO.setCreated_date(new Date());
	            	MXVO.setCreated_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
		             
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            
	            emptyMX += "'"+MXVO.getHjyxdjgzcnszyss_uid()+"',";
			}
        }
        return emptyMX;
	}

}
