/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywJgysbaService.java
 * 创建日期： 2014-06-09 下午 02:35:53
 * 功能：    接口：竣工验收备案
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 02:35:53  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.text.SimpleDateFormat;
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

import com.ccthanking.business.sp.dao.BuSpywJgysbaDao;
import com.ccthanking.business.sp.service.BuSpywJgysbaService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywJgysbaVO;
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
 * <p> BuSpywJgysbaService.java </p>
 * <p> 功能：竣工验收备案 </p>
 *
 * <p><a href="BuSpywJgysbaService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Service
public class BuSpywJgysbaServiceImpl extends Base1ServiceImpl<BuSpywJgysbaVO, String> implements BuSpywJgysbaService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJgysbaServiceImpl.class);
	 @Autowired
	 private BuSpLzhfService buSpLzhfService;
	// 业务类型
    private String ywlx = YwlxManager.Bu_Spyw_Jgysba;
    
    private BuSpywJgysbaDao buSpywJgysbaDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywJgysbaDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<竣工验收备案>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("竣工验收备案{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, 
					 user.getName() + "竣工验收备案查询失败", user, "", "");
			SystemException.handleMessageException("竣工验收备案查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			BuSpywJgysbaVO  vo = this.findById(id);

			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName=Constants.getString("template_word_js_06","");
			String workName=""+Constants.getString("template_word_js_06","")+""+vo.getJgysba_uid()+".doc";
			
			SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		    String jg=rqgs.format(vo.getBarq_date());
		    String kg=rqgs.format(vo.getKgrq_date());
		    String da=rqgs.format(vo.getJgysrq());
		    
		    vo.remove("BARQ_DATE");
		    vo.remove("KGRQ_DATE");
		    vo.remove("JGYSRQ");
            vo.put("BARQ_DATE",jg);
            vo.put("KGRQ_DATE",jg);
            vo.put("JGYSRQ",kg);
			
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
			
			
			
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
        BuSpywJgysbaVO vo = new BuSpywJgysbaVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            vo.setJgysba_uid(DBUtil.getSequenceValue("JGYSBA_UID"));
            

		    vo.setEvent_uid(new RandomGUID().toString());
		    
		    vo.setCreated_uid(user.getUserSN());
            vo.setCreated_name(user.getName());
            vo.setCreated_date(new Date());

            // 插入
			buSpywJgysbaDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, 
                     user.getName() + "竣工验收备案新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("竣工验收备案{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, 
                     user.getName() + "竣工验收备案新增失败", user, "", "");
            SystemException.handleMessageException("竣工验收备案新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywJgysbaVO vo = new BuSpywJgysbaVO();

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
            buSpywJgysbaDao.update(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getName() + "竣工验收备案修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("竣工验收备案{}", e.getMessage());
            LogManager.writeUserLog(vo.getEvent_uid(), ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE,  user.getName() + "竣工验收备案修改失败", user, "", "");
            SystemException.handleMessageException("竣工验收备案修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywJgysbaVO vo = new BuSpywJgysbaVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			 vo.setEvent_uid(new RandomGUID().toString());

			//删除   根据据主键
			buSpywJgysbaDao.delete(BuSpywJgysbaVO.class, vo.getJgysba_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "竣工验收备案删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("竣工验收备案{}", e.getMessage());
            LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,  user.getName() + "竣工验收备案删除失败", user, "", "");
            SystemException.handleMessageException("竣工验收备案删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpywJgysbaDaoImpl")
	public void setBuSpywJgysbaDao(BuSpywJgysbaDao buSpywJgysbaDao) {
		this.buSpywJgysbaDao = buSpywJgysbaDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJgysbaDao);
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
            String id= buSpywJgysbaDao.getIdByYwlzuid(YWLZ_UID);
            BuSpywJgysbaVO mapFtl=new BuSpywJgysbaVO();
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
