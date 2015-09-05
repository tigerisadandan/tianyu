/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbSydjService.java
 * 创建日期： 2014-12-26 上午 10:44:21
 * 功能：    接口：机械设备使用登记
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-26 上午 10:44:21  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.ccthanking.business.dtgl.azqy.vo.JxsbSydjVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbSydjDao;
import com.ccthanking.business.dtgl.jxsb.service.JxsbSydjService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JxsbSydjService.java </p>
 * <p> 功能：机械设备使用登记 </p>
 *
 * <p><a href="JxsbSydjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-26
 * 
 */

@Service
public class JxsbSydjServiceImpl extends Base1ServiceImpl<JxsbSydjVO, String> implements JxsbSydjService {

	private static Logger logger = LoggerFactory.getLogger(JxsbSydjServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JXSB_SYDJ;
    
    private JxsbSydjDao jxsbSydjDao;

    // @Override
    public String queryCondition(String json,HttpServletRequest request) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbSydjDao.queryCondition(json, null, null,request);

           
        }catch (DaoException e) {
        	logger.error("机械设备使用登记{}", e.getMessage());
			SystemException.handleMessageException("机械设备使用登记查询失败,请联系相关人员处理");
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
        	
        	//SYDJ 信息
        	String sql=" SELECT (select a.CREATED_DATE from jxsb_azgz a where a.jxsb_sygl_uid=s.jxsb_sygl_uid) AZGZ_SG_DATE," +
        			"(select a.SG_SHENHE_YJ from jxsb_azgz a where a.jxsb_sygl_uid=s.jxsb_sygl_uid) SG_SHENHE_YJ," +
        			"(select a.AQGL_REN from jxsb_azgz a where a.jxsb_sygl_uid=s.jxsb_sygl_uid) AQGL_REN," +
        			"(select u.user_name from users u where u.users_uid=s.XZSH_REN) XZSH_NAME,s.*,g.dongshu " +
        			"FROM JXSB_SYDJ s left join jxsb_sygl g on s.jxsb_sygl_uid=g.jxsb_sygl_uid " +
        			" where s.jxsb_sydj_uid='"+id+"'";
			List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
			Map map= mapList.get(0);
			
			//JXSB
			String sqlsb= "SELECT s.CQ_ATTRIBUTE,s.shebei_name,s.JXSB_UID,d.SYDJ_BH,j.JXSB_TYPE_UID,j.ZZXKZ,j.SB_XH,j.CC_CODE,j.ZZDW,j.CQDW FROM JXSB_SYGL s " +
            		"left join jxsb j on s.jxsb_uid=j.jxsb_uid "+
                    "left join jxsb_sydj d on d.jxsb_sygl_uid=s.jxsb_sygl_uid where s.JXSB_SYGL_UID='"+map.get("JXSB_SYGL_UID")+"'";
			List<Map<String, String>> sqlsblist =DBUtil.queryReturnList(conn, sqlsb);
			Map mapsb= sqlsblist.get(0);
			if(mapsb.get("CQ_ATTRIBUTE").equals("U")){
				mapsb.put("CQSX", "租用");
			}else if(mapsb.get("CQ_ATTRIBUTE").equals("Z")){
				mapsb.put("CQSX", "自用");
			}
				
			map.putAll(mapsb);
			
			//xm
			String sqlxm=" select s.*, " +
		    		"(select j.JC_DW from JXSB_JCYS j where j.jxsb_sygl_uid=s.jxsb_sygl_uid ) JC_DW," +
		    		" (select j.BGQF_DATE from JXSB_JCYS j where j.jxsb_sygl_uid=s.jxsb_sygl_uid ) BGQF_DATE," +
		    		"(select g.SBAZ_DATE from JXSB_azgc g where g.jxsb_sygl_uid=s.jxsb_sygl_uid ) SBAZ_DATE," +
		    		"(select z.AZYS_DATE from JXSB_azgz z where z.jxsb_sygl_uid=s.jxsb_sygl_uid ) YS_DATE," +
		    		"(select count(*) from JXSB_SYGL where JXSB_uid = (select gl.jxsb_uid from JXSB_SYGL gl where gl.jxsb_sygl_uid=s.jxsb_sygl_uid)) as SY_COUNTS" +
		    		" from jxsb_sygl s  where s.JXSB_SYGL_UID='"+map.get("JXSB_SYGL_UID")+"'";
			List<Map<String, String>> xmlist =DBUtil.queryReturnList(conn, sqlxm);
			Map mapxm= xmlist.get(0);
			map.putAll(mapxm);
			
			//材料
			String sqlcl="select * from jxsb_sygl_cl t where t.JXSB_STEP ='SYDJ' and JXSB_STEP_UID = '"+id+"' ";
			List<Map<String, String>> mapListcl =DBUtil.queryReturnList(conn, sqlcl);
			map.put("CL_LIST",mapListcl);
			for (int i = 0; i < mapListcl.size(); i++) {
				int index=i+1;
				mapListcl.get(i).put("indexs",index+"");
			}
		    
			//AZ_PERSON 
			String sqlry="SELECT t.person_name,t.Job_type,t.zgbh,t.beizhu FROM JXSB_CZRY t where t.JXSB_STEP ='SYDJ' and JXSB_STEP_UID = '"+id+"' ";
			List<Map<String, String>> mapListry =DBUtil.queryReturnList(conn, sqlry);
			map.put("RY_LIST",mapListry);
			
			for (int i = 0; i < mapListry.size(); i++) {
				if(mapListry.get(i).get("JOB_TYPE").equals("安全员")){
					map.put("AQY",mapListry.get(i).get("PERSON_NAME"));		
				}else if(mapListry.get(i).get("JOB_TYPE").equals("驻锡技术负责人")){
					map.put("JSY",mapListry.get(i).get("PERSON_NAME"));		
				}
			}
		    
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_sydj","");
			String workName=Constants.getString("template_word_sydj","")+id+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
    }
   
   public String downloadDjz(HttpServletResponse response,String id) throws Exception {
       
   	User user = ActionContext.getCurrentUserInThread();
   	Connection conn = null;
   	//SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
       try {
/**/
       	conn = DBUtil.getConnection();
       	
       	//SYDJ 信息
       	String sql="select d.JXSB_SYDJ_UID,d.SHOULI_DATE,z.SBAZ_DATE,d.SYDJ_BH,a.ZZDJ,b.CC_CODE,g.SHEBEI_NAME,g.XKZHAO,g.GGXH,g.GC_NAME,g.GCSG_DANWEI,g.XMJL,g.LXDH,d.AZ_DANWEI,d.YS_DATE,d.BGQF_DATE,d.JC_DW " +
       			"from jxsb_sygl g " +
       			"left join jxsb_sydj d on g.jxsb_sygl_uid=d.jxsb_sygl_uid " +
       			"left join jxsb_azgz z on g.jxsb_sygl_uid=z.jxsb_sygl_uid " +
       			"left join jxsb b on g.jxsb_uid=b.jxsb_uid " +
       			"left join az_company a on d.AZ_COMPANY_UID=a.AZ_COMPANY_UID where g.jxsb_sygl_uid='"+id+"'";
			List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
			Map map= mapList.get(0);
			if(map.get("ZZDJ").equals("1")){
				map.put("ZZDJ_SV", "起重设备安装工程专业承包一级");
			}else if(map.get("ZZDJ").equals("2")){
				map.put("ZZDJ_SV", "起重设备安装工程专业承包二级");
			}else if(map.get("ZZDJ").equals("3")){
				map.put("ZZDJ_SV", "起重设备安装工程专业承包三级");
			}else if(map.get("ZZDJ").equals("4")){
				map.put("ZZDJ_SV", "起重设备安装工程专业承包暂定三级");
			} 
			map.put("DJ_DW", user.getAccount());
		    
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_djz","");
			String workName=Constants.getString("template_word_djz","")+map.get("JXSB_SYDJ_UID")+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
       }catch (DaoException e) {
       	return null;	
       }
   }
    
    public String queryList(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbSydjDao.queryList(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("机械设备使用登记{}", e.getMessage());
			SystemException.handleMessageException("机械设备使用登记查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryJxsb(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbSydjDao.queryJxsb(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("机械设备使用登记{}", e.getMessage());
			SystemException.handleMessageException("机械设备使用登记查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String queryRy(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbSydjDao.queryRy(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("机械设备使用登记{}", e.getMessage());
			SystemException.handleMessageException("机械设备使用登记查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JxsbSydjVO vo = new JxsbSydjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
        
            // 插入
			jxsbSydjDao.save(vo);
            resultVO = vo.getRowJson();

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("机械设备使用登记{}", e.getMessage());
            SystemException.handleMessageException("机械设备使用登记新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json,String type) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbSydjVO vo = new JxsbSydjVO();

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
        	if("50".equals(vo.getShenhe_status())){
        		JSONObject object= (JSONObject) list.get(0);
        		vo.setSydj_bh(object.getString("DJCODE"));
        	}
        	
        
            // 修改
            jxsbSydjDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("机械设备使用登记{}", e.getMessage());
            SystemException.handleMessageException("机械设备使用登记修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JxsbSydjVO vo = new JxsbSydjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			jxsbSydjDao.delete(JxsbSydjVO.class, vo.getJxsb_sydj_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("机械设备使用登记{}", e.getMessage());
           SystemException.handleMessageException("机械设备使用登记删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jxsbSydjDaoImpl")
	public void setJxsbSydjDao(JxsbSydjDao jxsbSydjDao) {
		this.jxsbSydjDao = jxsbSydjDao;
	}
    
}
