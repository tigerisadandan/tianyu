/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpywJyfwfsfJsgcjktzdService.java
 * 创建日期： 2014-11-18 下午 03:02:34
 * 功能：    接口：十、交易服务费收费
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 03:02:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bzwj.service.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.bzwj.BuSpywJyfwfsfJsgcjktzdVO;
import com.ccthanking.business.bzwj.BuSpywQgjjzsjftspsxQgsftzdVO;
import com.ccthanking.business.bzwj.dao.BuSpywJyfwfsfJsgcjktzdDao;
import com.ccthanking.business.bzwj.service.BuSpywJyfwfsfJsgcjktzdService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywJyfwfsfJsgcjktzdService.java </p>
 * <p> 功能：十、交易服务费收费 </p>
 *
 * <p><a href="BuSpywJyfwfsfJsgcjktzdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

@Service
public class BuSpywJyfwfsfJsgcjktzdServiceImpl extends Base1ServiceImpl<BuSpywJyfwfsfJsgcjktzdVO, String> implements BuSpywJyfwfsfJsgcjktzdService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJyfwfsfJsgcjktzdServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_JYFWFSF_JSGCJKTZD;
    
    private BuSpywJyfwfsfJsgcjktzdDao buSpywJyfwfsfJsgcjktzdDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJyfwfsfJsgcjktzdDao.queryCondition(json, null, null);

        
            
        }catch (DaoException e) {
        	logger.error("十、交易服务费收费{}", e.getMessage());
		
        } finally {
        }
        return domresult;

    }
    
    public String getCount() throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			Calendar a=Calendar.getInstance();
	        String year= Integer.toString(a.get(Calendar.YEAR));
	        String f = "%0" + 3 + "d";	
			String count = buSpywJyfwfsfJsgcjktzdDao.getCount();
            domresult="XQ"+year+String.format(f, Integer.parseInt(count)+1);
            
        }catch (DaoException e) {
        	logger.error("十、交易服务费收费{}", e.getMessage());
		
        } finally {
        }
        return domresult;

    }
    
 public String queryByLzbz(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJyfwfsfJsgcjktzdDao.queryByLzbz(json);

            
        }catch (DaoException e) {
        	logger.error("建设工程交易服务费缴款通知单（直接发包）{}", e.getMessage());
            
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywJyfwfsfJsgcjktzdVO vo = new BuSpywJyfwfsfJsgcjktzdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//		    vo.setSjbh(eventVO.getSjbh());

            // 插入
			buSpywJyfwfsfJsgcjktzdDao.save(vo);
            resultVO = vo.getRowJson();

        

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("十、交易服务费收费{}", e.getMessage());
          
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywJyfwfsfJsgcjktzdVO vo = new BuSpywJyfwfsfJsgcjktzdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
//
//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            // 修改
            buSpywJyfwfsfJsgcjktzdDao.update(vo);
            resultVO = vo.getRowJson();

       

        } catch (DaoException e) {
            logger.error("十、交易服务费收费{}", e.getMessage());
          
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywJyfwfsfJsgcjktzdVO vo = new BuSpywJyfwfsfJsgcjktzdVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			buSpywJyfwfsfJsgcjktzdDao.delete(BuSpywJyfwfsfJsgcjktzdVO.class, vo.getJyfwfsf_jsgcjktzd_uid());

			resultVo = vo.getRowJson();

		

		} catch (DaoException e) {
            logger.error("十、交易服务费收费{}", e.getMessage());
        
		} finally {
		}
		return resultVo;

	}

 public String querytoword(HttpServletResponse response,String id,String ptfName) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();

    
        try {

        	BuSpywJyfwfsfJsgcjktzdVO  vo = this.findById(id);

			String filePath=Constants.getString(Constants.PATH_TEMPLATE_BZWJ_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
//			String ftlName=Constants.getString("template_word_js_09","");   不需要初始化    直接步骤文件模板名
//			String workName=Constants.getString("template_word_js_09","")+""+vo.getScxmmjhsd_uid()+"";
			
//			 SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
//			    String jg=rqgs.format(vo.getJsdwrq());
//			    String kg=rqgs.format(vo.getCbrrq());
//			    
//			    vo.remove("JSDWRQ");
//			    vo.remove("CBRRQ");
//	            vo.put("JSDWRQ",jg);
//	            vo.put("CBRRQ",kg);
			
			String workName="";
			if(ptfName==""){
				ptfName=null;
			}
			if("null".equals(ptfName)){
				ptfName=null;
			}
			if(ptfName==null){
				List<Map<String, String>> obj=buSpywJyfwfsfJsgcjktzdDao.queryTpfFileNameByScxmid(vo.getJyfwfsf_jsgcjktzd_uid());
		        workName=obj.get(0).get("TMPWJNAME")+vo.getJyfwfsf_jsgcjktzd_uid();
		        ptfName=obj.get(0).get("TMPWJNAME");
			}else{
			 workName=ptfName+vo.getJyfwfsf_jsgcjktzd_uid()+"";  
			}
			
//			if(vo.getRq()==null){
//				vo.setRq(new Date());
//			}
//			Date dd =vo.getRq();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
//			String tbdate="";
//			tbdate = sdf.format(dd);
			Map mapvo=vo;
//			String date=mapvo.get("RQ")+"";
//			mapvo.remove("RQ");
//			mapvo.put("RQ",tbdate);
//			
	
			FreemarkerHelper.createWord(vo, filePath, ptfName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"/" +workName;
				
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			String sql_add="update bu_sp_ywlz_bzwj set alprint=1 where lzbz_wj_uid='"+vo.getLzbz_wj_uid()+"'";
            DBUtil.exec(sql_add);
			return filename+".xml.pdf";
        }catch (Exception e) {
        	 logger.error("十、交易服务费打印{}", e.getMessage());
        }
		
        return null;
    }
    
    
	@Autowired
	@Qualifier("buSpywJyfwfsfJsgcjktzdDaoImpl")
	public void setBuSpywJyfwfsfJsgcjktzdDao(BuSpywJyfwfsfJsgcjktzdDao buSpywJyfwfsfJsgcjktzdDao) {
		this.buSpywJyfwfsfJsgcjktzdDao = buSpywJyfwfsfJsgcjktzdDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJyfwfsfJsgcjktzdDao);
	}
    
}
