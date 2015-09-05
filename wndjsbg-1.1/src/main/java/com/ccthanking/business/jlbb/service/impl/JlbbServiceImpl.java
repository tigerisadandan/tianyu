/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jlbb.service.JlbbService.java
 * 创建日期： 2015-01-28 上午 09:10:22
 * 功能：    接口：监理报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 上午 09:10:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jlbb.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.dtgl.jl.vo.JlbbJlyVO;
import com.ccthanking.business.dtgl.jl.vo.JlbbVO;
import com.ccthanking.business.jlbb.dao.JlbbDao;
import com.ccthanking.business.jlbb.service.JlbbService;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JlbbService.java </p>
 * <p> 功能：监理报备 </p>
 *
 * <p><a href="JlbbService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Service
public class JlbbServiceImpl extends Base1ServiceImpl<JlbbVO, String> implements JlbbService {

	private static Logger logger = LoggerFactory.getLogger(JlbbServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.JLBB;
    
    private JlbbDao jlbbDao;
    
	@Autowired
	private FsMessageInfoService fsMessageInfoService;

    // @Override
    public String queryCondition(String json,Map<String,String> map) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jlbbDao.queryCondition(json, map);

            
        }catch (DaoException e) {
			SystemException.handleMessageException("监理报备查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    


    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JlbbVO vo = new JlbbVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            jlbbDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("监理报备{}", e.getMessage());
            SystemException.handleMessageException("监理报备修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JlbbVO vo = new JlbbVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			//jlbbDao.delete(JlbbVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("监理报备{}", e.getMessage());
            SystemException.handleMessageException("监理报备删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    
    
    public String queryZbgg(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jlbbDao.queryZbgg(json);

            
        }catch (DaoException e) {
			SystemException.handleMessageException("监理报备查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

		return format.format(new Date());
	}

	@Autowired
	@Qualifier("jlbbDaoImpl")
	public void setJlbbDao(JlbbDao jlbbDao) {
		this.jlbbDao = jlbbDao;
	}


	public String queryBbxx(String json) throws Exception {
		
		return this.jlbbDao.queryBbxx(json);
	}


	public String queryPersonList(String msg) throws Exception {
		// TODO Auto-generated method stub
		return this.jlbbDao.queryPersonList(msg);
	}



	public String insert(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public void updateBbzt(String jlbb_uid,String status,String code,String name) throws Exception {
		
		 this.jlbbDao.updateBbzt(jlbb_uid,status);
			if("20".equals(status)){
				
				//****************************消息添加
		         Map messageMap= new HashMap();
		         messageMap.put("TITLE", "监理人员审核");
		         messageMap.put("CONTENT", "人员审核通过");
		         ///messageMap.put("USERTO", code);//企业的登录Code
		         messageMap.put("USERTONAME", name);//企业的名称
		
		         fsMessageInfoService.insertVo(messageMap);
			}
	}



	public void updateBbToUnlock(String bbid, String optype) {
		try {
			if ("doBb".equals(optype)) {
				this.jlbbDao.unLockBb(bbid);
			}else if("doBbry".equals(optype)){
				this.jlbbDao.unLockBbry(bbid);
			}
		} catch (DaoException e) {
			logger.error("监理报备解锁{}", e.getMessage());
		}
		
	}
	
	public void personLock(String bbuid) throws Exception {
		
		this.jlbbDao.personLock(bbuid);

	}



	public JasperPrint query4Print(String bbid, String parpentPath,
			String childPath) {
		// TODO Auto-generated method stub
		return this.jlbbDao.query4Print(bbid,parpentPath,childPath);
	}



	public String toword(HttpServletResponse response, String bbid) {
		Map<String, Object> map =  null;
		try {
			List<?> list = jlbbDao.findHeaderPrint(bbid);
			String path = System.getProperty(Constants.getString(Constants.webAppRootKey, "wndjsbg.root"));
			String connpath = path.replace("\\", "/") + "template/";
			//System.out.println("connpath=="+connpath);
			//String filePath=connpath;	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_jl_01","");
			String workName=""+System.nanoTime();
			String filename =connpath+workName;
			//System.out.println("filename=="+filename);
			if(list.size()!=0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map = (Map<String, Object>) list.get(0);
				map.put("tblist", jlbbDao.findTablePrint(bbid));
				map.put("dydate", sdf.format(new Date()));
				FreemarkerHelper.createWord(map, connpath, ftlName, connpath, workName);
				Word2PDF word2pdf = new Word2PDF();
				word2pdf.toPdf(filename);
				
			}
			return filename+".xml.pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
    
}
