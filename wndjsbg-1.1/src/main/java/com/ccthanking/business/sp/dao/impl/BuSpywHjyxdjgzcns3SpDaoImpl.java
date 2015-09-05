/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywHjyxdjgzcns3SpDao.java
 * 创建日期： 2014-06-17 下午 02:07:15
 * 功能：   建设项目环境影响评价报告表（书）审批--附件四
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-17 下午 02:07:15  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.common.vo.AtFileuploadVO;
import com.ccthanking.business.sp.dao.BuSpywHjyxdjgzcns3SpDao;
import com.ccthanking.business.spyw.vo.BuSpywHjyxdjgzcns3SpVO;
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
 * <p> BuSpywHjyxdjgzcns3SpDao.java </p>
 * <p> 功能：建设项目环境影响评价报告表（书）审批--附件四 </p>
 *
 * <p><a href="BuSpywHjyxdjgzcns3SpDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-17
 * 
 */

@Component
public class BuSpywHjyxdjgzcns3SpDaoImpl  extends BsBaseDaoTJdbc implements BuSpywHjyxdjgzcns3SpDao {
	private static Logger logger = LoggerFactory.getLogger(BuSpywHjyxdjgzcns3SpDaoImpl.class);
	private  String SQL_QUERY="select hjyxdjgzcns_uid, ywlz_uid, xmmc, jsdw, tbrq_date, frdb," +
			" lxr, lxdh, cz, yzbm, txl, jsdd, jsxz, hylbjdm, zdmj, rhmj, ztz, hbtz, tzbl, yqtcrq," +
			" yjgzr, syt, rdjzmj, kfs, cws, xyfmj, sxj_x, sxj_t, gxj_x, gxj_t, ctmj, qfmj, lzs, rlzl," +
			" rlyl, ct_klokyxs, ylcsmj, discowtmj, gwt_klokyxs, qsmj, qds, qzs, ysmj, glxh, glxhsl, zysl," +
			" fspfl, cfysl, pfqx, ktsb_xh, ktsb_sl, fdj_xh, fdj_sl, gl_xh, gl_xl, rlqk_zl, rlqk_sl, qtsbss," +
			" psqk, fzwrcsjs from bu_spyw_hjyxdjgzcns3_sp";

	 public String queryCondition(String json){
		    
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

	            conn.setAutoCommit(false);

	            BaseResultSet bs = DBUtil.query(conn, SQL_QUERY, page);//连接数据库，进行查询，结果集给bs
	           
	            domresult = bs.getJson();
	            
	        } catch (Exception e) {
	        	DBUtil.rollbackConnetion(conn);
	            logger.error("环境影响登记告知承诺书审批———附件四信息查询失败!");
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
	        String ListMX="select file_save_name,at_fileupload_uid from at_fileupload where target_uid="+id+" and file_type=39 and business_sub_type='BU_SPYW_HJYXDJGZCNS3_SP'";
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
	        BuSpywHjyxdjgzcns3SpVO vo = new BuSpywHjyxdjgzcns3SpVO();
	        try{
	        conn.setAutoCommit(false);
	        JSONArray list = vo.doInitJson(json);
	        JSONObject obj = (JSONObject) list.get(0);
	        
	      
	        vo.setValueFromJson(obj);//把页面的值给属性
	       
	        String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");

	        vo.setHjyxdjgzcns_uid(DBUtil.getSequenceValue("HJYXDJGZCNS3_SP_UID"));
	        vo.setCreated_date(new Date());
	        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
	        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
	        
	        vo.setSerial_no(vo.getHjyxdjgzcns_uid());
	        BaseDAO.insert(conn, vo);
	        
	        insertFiles(files, fileLinks, vo.getHjyxdjgzcns_uid(), conn);

	        resultVO = vo.getRowJson();
	        
	        conn.commit();
	        }catch(Exception e){
	        	
	        	DBUtil.rollbackConnetion(conn);
	            e.printStackTrace(System.out);
	            logger.error("环境影响登记告知承诺书审批———附件四新增失败!");
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
	        BuSpywHjyxdjgzcns3SpVO vo = new BuSpywHjyxdjgzcns3SpVO();
	        User user = ActionContext.getCurrentUserInThread();


	        try {
	            conn.setAutoCommit(false);
	            JSONArray list = vo.doInitJson(json);
	            JSONObject obj = (JSONObject) list.get(0);
	            vo.setValueFromJson(obj);//把页面的值给属性
	            
	            String fileLinks = obj.getString(vo.getVOTableName()+"_FILEUPLOAD");
	        	
	        	insertFiles(files, fileLinks, vo.getHjyxdjgzcns_uid(), conn);
	        	
	            vo.setUpdate_date(new Date());
	            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
	            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            BaseDAO.update(conn, vo);

	            resultVO = vo.getRowJson();
	            
	            conn.commit();
	        } catch (Exception e) {
	            DBUtil.rollbackConnetion(conn);
	            e.printStackTrace(System.out);
	            logger.error("环境影响登记告知承诺书审批———附件四更改失败!");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
			return null;
		}
	    
}
