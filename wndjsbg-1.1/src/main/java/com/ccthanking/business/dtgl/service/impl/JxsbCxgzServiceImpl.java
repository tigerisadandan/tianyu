/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    dtgl.service.JxsbCxgzService.java
 * 创建日期： 2015-01-26 下午 04:31:09
 * 功能：    接口：机械设备拆卸告知
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 04:31:09  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.service.impl;


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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgzVO;
import com.ccthanking.business.dtgl.dao.JxsbCxgzDao;
import com.ccthanking.business.dtgl.service.JxsbCxgzService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JxsbCxgzService.java </p>
 * <p> 功能：机械设备拆卸告知 </p>
 *
 * <p><a href="JxsbCxgzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Service
public class JxsbCxgzServiceImpl extends Base1ServiceImpl<JxsbCxgzVO, String> implements JxsbCxgzService {

	private static Logger logger = LoggerFactory.getLogger(JxsbCxgzServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JXSB_AZGZ;
    
    private JxsbCxgzDao jxsbCxgzDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbCxgzDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("机械设备拆卸告知{}", e.getMessage());
			SystemException.handleMessageException("机械设备拆卸告知查询失败,请联系相关人员处理");
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
        	
        	//cxgz 信息
        	String sql="select" +
            		" (select u.user_name from users u where u.users_uid=c.XZSH_REN) XZSH_NAME, " +
            		" g.SHEBEI_NAME,g.GGXH,y.SYDJ_BH SYDJBH,c.* from jxsb_sygl g " +
            		"left join JXSB_CXGZ c on c.jxsb_sygl_uid=g.jxsb_sygl_uid " +
            		"left join JXSB_SYDJ y on y.jxsb_sygl_uid=g.jxsb_sygl_uid where c.JXSB_CXGZ_UID='"+id+"'";
			List<Map<String, String>> mapList =DBUtil.queryReturnList(conn, sql);
			Map map= mapList.get(0);
			
			//JXSB
			String sqlsb= "SELECT s.CQ_ATTRIBUTE,s.shebei_name,s.JXSB_UID,d.SYDJ_BH,j.JXSB_TYPE_UID,j.ZZXKZ,j.SB_XH,j.CC_CODE,j.ZZDW,j.CQDW FROM JXSB_SYGL s " +
            		"left join jxsb j on s.jxsb_uid=j.jxsb_uid "+
                    "left join jxsb_sydj d on d.jxsb_sygl_uid=s.jxsb_sygl_uid where s.JXSB_SYGL_UID='"+map.get("JXSB_SYGL_UID")+"'";
			List<Map<String, String>> sqlsblist =DBUtil.queryReturnList(conn, sqlsb);
			Map mapsb= sqlsblist.get(0);
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
			if(mapsb.get("CQ_ATTRIBUTE").equals("U")){
				mapsb.put("CQSX", "租用");
			}else if(mapsb.get("CQ_ATTRIBUTE").equals("Z")){
				mapsb.put("CQSX", "自用");
			}
			map.putAll(mapxm);
			
			
			
			//材料
			String sqlcl="select * from jxsb_sygl_cl t where t.JXSB_STEP ='CXGZ' and JXSB_STEP_UID = '"+id+"' ";
			List<Map<String, String>> mapListcl =DBUtil.queryReturnList(conn, sqlcl);
			map.put("CL_LIST",mapListcl);
			for (int i = 0; i < mapListcl.size(); i++) {
				int index=i+1;
				mapListcl.get(i).put("indexs",index+"");
			}
		    
			//AZ_PERSON 
			String sqlry="SELECT t.person_name,t.Job_type,t.zgbh,t.beizhu FROM JXSB_CZRY t where t.JXSB_STEP ='CXGZ' and JXSB_STEP_UID = '"+id+"' ";
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
			String ftlName=Constants.getString("template_word_cxgz","");
			String workName=Constants.getString("template_word_cxgz","")+id+".doc";;
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
    }
    
    public String queryRy(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbCxgzDao.queryRy(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("机械设备拆卸告知{}", e.getMessage());
			SystemException.handleMessageException("机械设备拆卸告知查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JxsbCxgzVO vo = new JxsbCxgzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
          
            // 插入
			jxsbCxgzDao.save(vo);
            resultVO = vo.getRowJson();

        
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("机械设备拆卸告知{}", e.getMessage());
           SystemException.handleMessageException("机械设备拆卸告知新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json,String type) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbCxgzVO vo = new JxsbCxgzVO();

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
            jxsbCxgzDao.update(vo);
            resultVO = vo.getRowJson();

          
        } catch (DaoException e) {
            logger.error("机械设备拆卸告知{}", e.getMessage());
            SystemException.handleMessageException("机械设备拆卸告知修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JxsbCxgzVO vo = new JxsbCxgzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		//删除   根据据主键
			jxsbCxgzDao.delete(JxsbCxgzVO.class, vo.getJxsb_cxgz_uid());

			resultVo = vo.getRowJson();

	
		} catch (DaoException e) {
            logger.error("机械设备拆卸告知{}", e.getMessage());
            SystemException.handleMessageException("机械设备拆卸告知删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jxsbCxgzDaoImpl")
	public void setJxsbCxgzDao(JxsbCxgzDao jxsbCxgzDao) {
		this.jxsbCxgzDao = jxsbCxgzDao;
	}
    
}
