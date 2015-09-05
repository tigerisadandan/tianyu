/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.JxsbAzgzService.java
 * 创建日期： 2015-01-16 下午 12:03:25
 * 功能：    接口：机械设备安装告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-16 下午 12:03:25  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.Role;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.bzwj.dao.JxsbAzgzDao;
import com.ccthanking.business.bzwj.service.JxsbAzgzService;
import com.ccthanking.business.dtgl.azqy.vo.JxsbAzgzVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgzVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbSydjVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JxsbAzgzService.java </p>
 * <p> 功能：机械设备安装告知 </p>
 *
 * <p><a href="JxsbAzgzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-16
 * 
 */

@Service
public class JxsbAzgzServiceImpl extends Base1ServiceImpl<JxsbAzgzVO, String> implements JxsbAzgzService {

	private static Logger logger = LoggerFactory.getLogger(JxsbAzgzServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JXSB_AZGZ;
    
    private JxsbAzgzDao jxsbAzgzDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String toword(HttpServletResponse response,String id) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    	Connection conn = null;
    	//SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
        try {
/**/
        	conn = DBUtil.getConnection();
        	
        	//AZGZ GC 信息
        	String sql="select (select u.user_name from users u where u.users_uid=g.XZSH_REN) XZSH_NAME,g.CREATED_DATE,g.SHOULI_DATE,g.XZSH_DATE,g.SHENHE_DATE,g.SG_SHENHE_YJ,g.AQGL_REN,g.JL_SHENHE_YJ,g.XZSH_YIJIAN,g.JBRSH_YIJIAN,g.GZ_SHOULI_YJ,s.step,s.JXSB_SYGL_UID,s.JXSB_UID,g.BY_COMPANY_UID,g.AZ_COMPANY_UID,j.SY_STATUS,j.SB_XH,s.SHEBEI_NAME,s.XKZHAO,s.ZZCJ,s.GGXH,s.HGZH,s.CHUCHANG_DATE,s.CQ_DANWEI,s.CQ_BH,s.SBZLQRS_CODE," +
            		"g.ZCBY_DANWEI,g.XYSC_CODE,g.JXSB_AZGZ_UID,g.AZ_DANWEI,g.ZZBH,g.AQSCBH,g.SBAZ_DATE,g.SBAZ_E_DATE,g.AZZJ_DATE,g.AZYS_DATE," +
            		"s.GC_NAME,s.GC_ADDRESS,s.GCSG_DANWEI,s.XMJL,g.SHENHE_STATUS,g.STATUS from jxsb_azgz g " +
            		"left join JXSB_SYGL s on g.jxsb_sygl_uid=s.jxsb_sygl_uid " +
            		"left join jxsb j on s.jxsb_uid=j.jxsb_uid  where g.JXSB_AZGZ_UID='"+id+"'";
			List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
			Map map= mapList.get(0);
			
			//材料
			String sqlcl="select * from jxsb_sygl_cl t where t.JXSB_STEP ='AZGZ' and JXSB_STEP_UID = '"+id+"' ";
			List<Map<String, String>> mapListcl =DBUtil.queryReturnList(conn, sqlcl);
			map.put("CL_LIST",mapListcl);
			for (int i = 0; i < mapListcl.size(); i++) {
				int index=i+1;
				mapListcl.get(i).put("indexs",index+"");
			}
		    
			//AZ_PERSON 
			String sqlry="SELECT t.person_name,t.Job_type,t.zgbh,t.beizhu FROM JXSB_CZRY t where t.JXSB_STEP ='AZGZ' and JXSB_STEP_UID = '"+id+"' ";
			List<Map<String, String>> mapListry =DBUtil.queryReturnList(conn, sqlry);
			map.put("RY_LIST",mapListry);
			
			for (int i = 0; i < mapListry.size(); i++) {
				if(mapListry.get(i).get("JOB_TYPE").equals("安全员")){
					map.put("AQY",mapListry.get(i).get("PERSON_NAME"));		
				}else if(mapListry.get(i).get("JOB_TYPE").equals("驻锡技术负责人")){
					map.put("JSY",mapListry.get(i).get("PERSON_NAME"));		
				}
			}
			
			
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName=Constants.getString("template_word_azgz","");
			String workName=Constants.getString("template_word_azgz","")+id+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	e.printStackTrace();
        	return "";	
        }
    }
    
public String queryByView(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.queryByView(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    
  public String queryAzyr(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.queryAzyr(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JxsbAzgzVO vo = new JxsbAzgzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
        
            // 插入
			jxsbAzgzDao.save(vo);
            resultVO = vo.getRowJson();

          
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("机械设备安装告知{}", e.getMessage());
           SystemException.handleMessageException("机械设备安装告知新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json,String type) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbAzgzVO vo = new JxsbAzgzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            if("xz".equals(type)){
        		vo.setXzsh_date(new Date());
        		vo.setXzsh_ren(user.getUserSN());
        	}else if("jb".equals(type)){
        		vo.setJbrsh_date(new Date());
        		vo.setJbrsh_ren(user.getUserSN());
        	}else if("sl".equals(type)){
        	    vo.setShouli_by(user.getUserSN());
        	    vo.setShouli_date(new Date());
        	}

         
            // 修改
            jxsbAzgzDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("机械设备安装告知{}", e.getMessage());
           SystemException.handleMessageException("机械设备安装告知修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String UpdateSH(String ids,String type) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
    	Role[] roles = user.getRoles();
    	String xzjs = "";
    	String jbjs = "";
    	String sljs = "";
    	for (int i = 0; i < roles.length; i++) {
    		if ("内网小组审核人".equals(roles[i].getName())) {
    			xzjs = "内网小组审核人";
    		}
    		if ("内网经办审核人".equals(roles[i].getName())) {
    			jbjs = "内网经办审核人";
    		}
    		if ("内网受理人".equals(roles[i].getName())) {
    			sljs = "内网受理人";
    		}
    	}
        
        String resultVO = null;
        JxsbAzgzVO vo = new JxsbAzgzVO();

        try {
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            String [] id=ids.split(","); 
            for (int i = 0; i < id.length; i++) {
            	String [] val=id[i].split("/");
            	String aa=val[0];
				if("az".equals(val[0])){
						JxsbAzgzVO az=new JxsbAzgzVO();
						az.setJxsb_azgz_uid(val[1]);
						if("-1".equals(type)){
							az.setStatus("-1");
						}else if("1".equals(type)){
							    if("30".equals(val[2])){
							    az.setShenhe_status("41");	
								}else if("41".equals(val[2])){
								az.setShenhe_status("50");	
								}
						}
						jxsbAzgzDao.update(az);
				}else if("sy".equals(val[0])){
					JxsbSydjVO sy=new JxsbSydjVO();
					sy.setJxsb_sydj_uid(val[1]);
					if("-1".equals(type)){
						sy.setStatus("-1");
					}else if("1".equals(type)){
						    if("30".equals(val[2])){
						    	sy.setShenhe_status("41");	
							}else if("41".equals(val[2])){
								sy.setShenhe_status("50");
								sy.setSydj_bh(val[3]);
							}
					}
					jxsbAzgzDao.update(sy);
			    }else if("cz".equals(val[0])){
					JxsbCxgzVO cz=new JxsbCxgzVO();
					cz.setJxsb_cxgz_uid(val[1]);
					if("-1".equals(type)){
						cz.setStatus("-1");
					}else if("1".equals(type)){
						    if("30".equals(val[2])){
						    	cz.setShenhe_status("41");	
							}else if("41".equals(val[2])){
								cz.setShenhe_status("50");	
							}
					}
					jxsbAzgzDao.update(cz);
			    }
				
			}
         
            // 修改
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("机械设备安装告知{}", e.getMessage());
           SystemException.handleMessageException("机械设备安装告知修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JxsbAzgzVO vo = new JxsbAzgzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			jxsbAzgzDao.delete(JxsbAzgzVO.class, vo.getJxsb_azgz_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("机械设备安装告知{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "机械设备安装告知删除失败", user, "", "");
            SystemException.handleMessageException("机械设备安装告知删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    public String queryAzgc(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.queryAzgc(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryJcys(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.queryJcys(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryCxgc(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.queryCxgc(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
  public String querySbzl(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbAzgzDao.querySbzl(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
  
  public String querySyglCl(String json) throws Exception {
      
  	User user = ActionContext.getCurrentUserInThread();
  
      String domresult = "";
      try {

			domresult = jxsbAzgzDao.querySyglCl(json, null, null);

          
      }catch (DaoException e) {
      	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
      } finally {
      }
      return domresult;

  }
  
  public String queryDslSb(String json,String type) throws Exception {
      
  	User user = ActionContext.getCurrentUserInThread();
  
      String domresult = "";
      try {
            if("0".equals(type)){
            	domresult = jxsbAzgzDao.queryDslSb(json, null, null);
            }else if("1".equals(type)){
            	domresult = jxsbAzgzDao.queryDslSbWsl(json, null, null);
            }else if("2".equals(type)){
            	domresult = jxsbAzgzDao.queryDslSbDzx(json, null, null);
            }else{
            	domresult = jxsbAzgzDao.queryDslSb(json, null, null);
            }
			

          
      }catch (DaoException e) {
      	logger.error("机械设备安装告知{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "机械设备安装告知查询失败", user, "", "");
			SystemException.handleMessageException("机械设备安装告知查询失败,请联系相关人员处理");
      } finally {
      }
      return domresult;

  }
  
   
  public String towordAzgc(HttpServletResponse response,String id) throws Exception {
      
  	User user = ActionContext.getCurrentUserInThread();
  	Connection conn = null;
  	//SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
      try {
/**/
      	conn = DBUtil.getConnection();
      	
      	String sql = " SELECT G.JXSB_UID,G.SHEBEI_NAME,G.CQ_BH,T.* FROM JXSB_SYGL G " +
        		"LEFT JOIN JXSB_AZGC T ON T.JXSB_SYGL_UID=G.JXSB_SYGL_UID  where T.JXSB_AZGC_UID='"+id+"'";
		List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
		Map map= mapList.get(0);
		if(map.get("BJJC").equals("0")){
			map.put("BJJC_SV", "无");
		}else if(map.get("BJJC").equals("1")){
			map.put("BJJC_SV", "有");
		}
		if(map.get("GZQQ").equals("N")){
			map.put("GZQQ_SV", "否");
		}else if(map.get("GZQQ").equals("Y")){
			map.put("GZQQ_SV", "是");
		} 
		    
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_azgc","");
			String workName=Constants.getString("template_word_azgc","")+id+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
      }catch (DaoException e) {
      	return null;	
      }
  }
  
  public String towordJcys(HttpServletResponse response,String id) throws Exception {
      
  	User user = ActionContext.getCurrentUserInThread();
  	Connection conn = null;
  	//SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
      try {
/**/
      	conn = DBUtil.getConnection();
      	
      	//AZGZ GC 信息
      	 String sql = " SELECT G.JXSB_UID,G.SHEBEI_NAME,G.CQ_BH,T.* FROM JXSB_SYGL G " +
           		"LEFT JOIN JXSB_JCYS T ON T.JXSB_SYGL_UID=G.JXSB_SYGL_UID where T.JXSB_JCYS_HIS_UID='"+id+"'";
			List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
			Map map= mapList.get(0);
		    
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_jcys","");
			String workName=Constants.getString("template_word_jcys","")+id+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
      }catch (DaoException e) {
      	return null;	
      }
  }
  
  public String towordCxgc(HttpServletResponse response,String id) throws Exception {
      
  	User user = ActionContext.getCurrentUserInThread();
  	Connection conn = null;
  	//SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
      try {
/**/
      	conn = DBUtil.getConnection();
      	
      	//AZGZ GC 信息
      	 String sql = "select g.JXSB_UID,g.SHEBEI_NAME,g.CQ_BH,c.* from jxsb_sygl g left join jxsb_cxgc c on g.jxsb_sygl_uid=c.jxsb_sygl_uid " +
      	 		"where c.JXSB_CXGC_UID='"+id+"'";
			List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
			Map map= mapList.get(0);
			
			if(map.get("GZQQ").equals("N")){
				map.put("GZQQ_SV", "否");
			}else if(map.get("GZQQ").equals("Y")){
				map.put("GZQQ_SV", "是");
			}
		    
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_cxgc","");
			String workName=Constants.getString("template_word_cxgc","")+id+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
      }catch (DaoException e) {
      	return null;	
      }
  }
  
    

	@Autowired
	@Qualifier("jxsbAzgzDaoImpl")
	public void setJxsbAzgzDao(JxsbAzgzDao jxsbAzgzDao) {
		this.jxsbAzgzDao = jxsbAzgzDao;
	}
    
}
