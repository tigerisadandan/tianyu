/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywZzhydjService.java
 * 创建日期： 2014-05-27 下午 04:27:16
 * 功能：    接口：资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 04:27:16  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywZzhydjDao;
import com.ccthanking.business.sp.service.BuSpywZzhydjService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywZzhydjVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.ibm.icu.text.SimpleDateFormat;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywZzhydjService.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywZzhydjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

@Service
public class BuSpywZzhydjServiceImpl extends Base1ServiceImpl<BuSpywZzhydjVO, String> implements BuSpywZzhydjService {
	

	private static Logger logger = LoggerFactory.getLogger(BuSpywZzhydjServiceImpl.class);
	 @Autowired
	 private BuSpLzhfService buSpLzhfService;
	// 业务类型
    private String ywlx = YwlxManager.bu_spyw_zzhydj;
    
    private BuSpywZzhydjDao buSpywZzhydjDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();

    
        String domresult = "";
        try {

			domresult = buSpywZzhydjDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<资质>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("资质{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "资质查询失败", user, "", "");
			SystemException.handleMessageException("资质查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();

    
        try {

			BuSpywZzhydjVO  vo = this.findById(id);

		    SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		    String jg=rqgs.format(vo.getDate_jg());
		    String kg=rqgs.format(vo.getDate_kg());
		    
		    vo.remove("DATE_JG");
		    vo.remove("DATE_KG");
            vo.put("DATE_JG",jg);
            vo.put("DATE_KG",kg);
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_js_01","");
			String workName=Constants.getString("template_word_js_01","")+""+vo.getZzhydj_uid()+"";
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
    }
   
    //download
    public void download(String path,HttpServletResponse response){
    	  FileUploadService ser = new FileUploadService();
    	   File file = new File(path+".xml.pdf");
           try {
			if (file.exists()) {
			       int bytes = 0;
			       ServletOutputStream op = response.getOutputStream();
			       
			       response.setContentType(ser.getMimeType(file));
			       response.setContentLength((int) file.length());
					// response.setCharacterEncoding("UTF-8");
			       response.setHeader( "Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord(file.getName()) + "\"" );
			       byte[] bbuf = new byte[1024];
			       DataInputStream in = new DataInputStream(new FileInputStream(file));
			       while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
			           op.write(bbuf, 0, bytes);
			       }
			       in.close();
			       op.flush();
			       op.close();
			       
			       
			   }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();

		
        String resultVO = null;
        BuSpywZzhydjVO vo = new BuSpywZzhydjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setZzhydj_uid(DBUtil.getSequenceValue("ZZHYDJ_UID"));

            vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
            vo.setCreated_date(new Date());
            
            // 插入
			buSpywZzhydjDao.save(vo);
            resultVO = vo.getRowJson();

           

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("资质{}", e.getMessage());
          
            SystemException.handleMessageException("资质新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();


        
        String resultVO = null;
        BuSpywZzhydjVO vo = new BuSpywZzhydjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
            vo.setCreated_date(new Date());
            // 修改
            buSpywZzhydjDao.update(vo);
            resultVO = vo.getRowJson();

          

        } catch (DaoException e) {
            logger.error("资质{}", e.getMessage());

            SystemException.handleMessageException("资质修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();


		String resultVo = null;
		BuSpywZzhydjVO vo = new BuSpywZzhydjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		

			//删除   根据据主键
			buSpywZzhydjDao.delete(BuSpywZzhydjVO.class, vo.getZzhydj_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "资质删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("资质{}", e.getMessage());
            LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "资质删除失败", user, "", "");
            SystemException.handleMessageException("资质删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywZzhydjDaoImpl")
	public void setBuSpywZzhydjDao(BuSpywZzhydjDao buSpywZzhydjDao) {
		this.buSpywZzhydjDao = buSpywZzhydjDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywZzhydjDao);
	}
    
	/**
     * 材料核发调用获取MAP数据
     * */
	public String ywlzclhf(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();

        
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();
//        obj.YWLZ_UID = data.YWLZ_UID;
//		obj.YWCL_UID = data.YWCL_UID;
//		obj.PIJIAN_CODE = codeid;
//		obj.PIJIAN_NAME = nameid;
//		obj.LINGJIAN_REN =ljr;
//		obj.LINGJIAN_PHONE=ljrdh;
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            String YWLZ_UID=(String)object.get("YWLZ_UID");
            
            
            //通过业务流转UID查询企业填报的数据，map类型
            String id= buSpywZzhydjDao.getIdByYwlzuid(YWLZ_UID);
            BuSpywZzhydjVO mapFtl=new BuSpywZzhydjVO();
            if(StringUtil.isNotBlankStr(id)){
            	 mapFtl =this.findById(id);      
            }
    	 	
//
//        	//复选框的判断
//        	String Wszx_gyws=mapFtl.getWszx_gyws();
//        	String Wsxz_shws=mapFtl.getWsxz_shws();
//        	String Sfclml=mapFtl.getSfclml();
//        	if(StringUtils.isBlank(Wszx_gyws)){
//        		mapFtl.put("WSZX_GYWS", "");
//        	}
//        	if(StringUtils.isBlank(Wsxz_shws)){
//        		mapFtl.put("WSXZ_SHWS", "");
//        	}
//        	if(StringUtils.isBlank(Sfclml)){
//        		mapFtl.put("SFCLML", "");
//        	}
//            
            
            
          //组装业务流转核发数据
        	Map mapVo = new HashMap();
        	mapVo.put("YWLZ_UID", YWLZ_UID);
        	mapVo.put("YWCL_UID",object.get("YWCL_UID") );
        	mapVo.put("PIJIAN_CODE",object.get("PIJIAN_CODE") );
        	mapVo.put("PIJIAN_NAME",object.get("PIJIAN_NAME") );
        	mapVo.put("LINGJIAN_PHONE", object.get("LINGJIAN_PHONE"));
        	mapVo.put("LINGJIAN_REN", object.get("LINGJIAN_REN"));
        	mapVo.put("CLK_UID", object.getString("CLK_UID"));
        	mapVo.put("FZ_DATE", object.getString("FZ_DATE"));
        	mapVo.put("YXQ_DATE", object.getString("YXQ_DATE"));
        	
        	if(mapFtl!=null){
        		buSpLzhfService.saveBuSpLzhfVO(mapFtl, mapVo);
        	}
            
            resultVO = vo.getRowJson();    
        } catch (DaoException e) {
            logger.error("审批业务材料核发{}", e);
            SystemException.handleMessageException("审批业务材料核发调用失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

}
