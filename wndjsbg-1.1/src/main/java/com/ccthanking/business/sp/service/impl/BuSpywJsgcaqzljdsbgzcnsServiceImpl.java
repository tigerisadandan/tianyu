/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywJsgcaqzljdsbgzcnsService.java
 * 创建日期： 2014-06-10 下午 02:52:43
 * 功能：    接口：安全、质量监督申报告知承诺书
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-10 下午 02:52:43  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywJsgcaqzljdsbgzcnsDao;
import com.ccthanking.business.sp.service.BuSpywJsgcaqzljdsbgzcnsService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcaqzljdsbgzcnsVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywJsgcaqzljdsbgzcnsService.java </p>
 * <p> 功能：安全、质量监督申报告知承诺书 </p>
 *
 * <p><a href="BuSpywJsgcaqzljdsbgzcnsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-10
 * 
 */

@Service
public class BuSpywJsgcaqzljdsbgzcnsServiceImpl extends Base1ServiceImpl<BuSpywJsgcaqzljdsbgzcnsVO, String> implements BuSpywJsgcaqzljdsbgzcnsService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcaqzljdsbgzcnsServiceImpl.class);
	 @Autowired
	 private BuSpLzhfService buSpLzhfService;
	// 业务类型
    private String ywlx = YwlxManager.Bu_Spyw_Jsgcaqzljdsbgzcns;
    
    private BuSpywJsgcaqzljdsbgzcnsDao buSpywJsgcaqzljdsbgzcnsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJsgcaqzljdsbgzcnsDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS,  user.getName() + "查询<安全、质量监督申报告知承诺书>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("安全、质量监督申报告知承诺书{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE,  user.getName() + "安全、质量监督申报告知承诺书查询失败", user, "", "");
			SystemException.handleMessageException("安全、质量监督申报告知承诺书查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
 // toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			BuSpywJsgcaqzljdsbgzcnsVO  vo = this.findById(id);

			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_js_04","");
			String workName=""+Constants.getString("template_word_js_04","")+""+vo.getAqzljdsbgzcns_uid()+".doc";
			Date dd =vo.getCreated_date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String tbdate="";
			tbdate = sdf.format(dd);
			Map mapvo=vo;
			String date=mapvo.get("TBRE_DATE")+"";
			mapvo.remove("TBRE_DATE");
			mapvo.put("TBRE_DATE",tbdate);
			
			//复选框的判断
	    	String Wszx_gyws=vo.getGcgk_gclx_xz();
	    	if(org.apache.commons.lang3.StringUtils.isBlank(Wszx_gyws)){
	    		vo.put("GCGK_GCLX_XZ", "");
	    	}
	    	//复选框的判断
	    	String Wszx_gyws2=vo.getGcgk_gclx_xkg();
	    	if(org.apache.commons.lang3.StringUtils.isBlank(Wszx_gyws2)){
	    		vo.put("GCGK_GCLX_XKG", "");
	    	}
	    	//复选框的判断
	    	String GCGK_GCLX_TZLX=vo.getGcgk_gclx_tzlx();
	    	if(org.apache.commons.lang3.StringUtils.isBlank(GCGK_GCLX_TZLX)){
	    		vo.put("GCGK_GCLX_TZLX", "");
	    	}
	    	
	    	 SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
			    String jg=rqgs.format(vo.getGcgk_htjgrq_date());
			    String kg=rqgs.format(vo.getGcgk_htkgrq_date());
			    
			    vo.remove("GCGK_HTKGRQ_DATE");
			    vo.remove("GCGK_HTJGRQ_DATE");
	            vo.put("GCGK_HTJGRQ_DATE",jg);
	            vo.put("GCGK_HTKGRQ_DATE",kg);
	    	
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename = Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;

			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
		

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywJsgcaqzljdsbgzcnsVO vo = new BuSpywJsgcaqzljdsbgzcnsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setAqzljdsbgzcns_uid(DBUtil.getSequenceValue("AQZLJDSBGZCNS_UID"));
            vo.setEvent_uid(new RandomGUID().toString());
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
            vo.setTbre_date(sdf1.parse(sdf1.format(new Date())));
            
            vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
            vo.setCreated_date(new Date());

            vo.setEvent_uid(new RandomGUID().toString());

            // 插入
			buSpywJsgcaqzljdsbgzcnsDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS,  user.getName() + "安全、质量监督申报告知承诺书新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("安全、质量监督申报告知承诺书{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "安全、质量监督申报告知承诺书新增失败", user, "", "");
            SystemException.handleMessageException("安全、质量监督申报告知承诺书新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywJsgcaqzljdsbgzcnsVO vo = new BuSpywJsgcaqzljdsbgzcnsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	
          	 vo.setEvent_uid(new RandomGUID().toString());

          	vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
            vo.setCreated_date(new Date());
            
            // 修改
            buSpywJsgcaqzljdsbgzcnsDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,  user.getName() + "安全、质量监督申报告知承诺书修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("安全、质量监督申报告知承诺书{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getName() + "安全、质量监督申报告知承诺书修改失败", user, "", "");
            SystemException.handleMessageException("安全、质量监督申报告知承诺书修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywJsgcaqzljdsbgzcnsVO vo = new BuSpywJsgcaqzljdsbgzcnsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			 vo.setEvent_uid(new RandomGUID().toString());

			//删除   根据据主键
			buSpywJsgcaqzljdsbgzcnsDao.delete(BuSpywJsgcaqzljdsbgzcnsVO.class, vo.getAqzljdsbgzcns_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,  user.getName() + "安全、质量监督申报告知承诺书删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("安全、质量监督申报告知承诺书{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,  user.getName() + "安全、质量监督申报告知承诺书删除失败", user, "", "");
            SystemException.handleMessageException("安全、质量监督申报告知承诺书删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywJsgcaqzljdsbgzcnsDaoImpl")
	public void setBuSpywJsgcaqzljdsbgzcnsDao(BuSpywJsgcaqzljdsbgzcnsDao buSpywJsgcaqzljdsbgzcnsDao) {
		this.buSpywJsgcaqzljdsbgzcnsDao = buSpywJsgcaqzljdsbgzcnsDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJsgcaqzljdsbgzcnsDao);
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
            String id= buSpywJsgcaqzljdsbgzcnsDao.getIdByYwlzuid(YWLZ_UID);
            BuSpywJsgcaqzljdsbgzcnsVO mapFtl=new BuSpywJsgcaqzljdsbgzcnsVO();
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

        	
        	Calendar now = Calendar.getInstance(); 
/**     **/ 	
//        	System.out.println("年: " + now.get(Calendar.YEAR));  
//            System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
//            System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));

        	Map hfmap=new HashMap();
        	//安全监督通知书
        	hfmap.put("pijian_code",object.get("PIJIAN_CODE"));
        	hfmap.put("companysname", mapFtl.getJs_mc());
        	hfmap.put("projectsname", mapFtl.getXmmc());
        	hfmap.put("username", object.get("LINGJIAN_PHONE"));
        	hfmap.put("phone",object.get("LINGJIAN_REN"));
        	hfmap.put("year", now.get(Calendar.YEAR));
        	hfmap.put("month", (now.get(Calendar.MONTH) + 1));
        	hfmap.put("day", now.get(Calendar.DAY_OF_MONTH));
        	       	
        	//质量监督通知书
        	hfmap.put("bh", object.get("PIJIAN_CODE"));

//        	hfmap.put("yzdw", "上海科瑞信息技术有限公司");
//        	hfmap.put("xmmc", "无锡新区建设管理系统");
//        	hfmap.put("zjname", "李四");
//        	hfmap.put("zjphone", "18701973011");

        	hfmap.put("yzdw", mapFtl.getJs_mc());
        	hfmap.put("xmmc",  mapFtl.getXmmc());
        	hfmap.put("zjname", object.get("LINGJIAN_REN"));
        	hfmap.put("zjphone",object.get("LINGJIAN_PHONE"));
        	      	
        	if(hfmap!=null){     		
        		buSpLzhfService.saveBuSpLzhfVO(hfmap, mapVo);
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
