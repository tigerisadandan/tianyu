/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bzwj.service.BuSpywQgjjzsjftspsxQgsftzdService.java
 * 创建日期： 2014-11-07 上午 09:41:47
 * 功能：    接口：八墙体材料专项基金预收款收费通知单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-07 上午 09:41:47  曹伟杰   创建文件，实现基本功能
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

import jsx3.gui.Alerts;

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

import com.ccthanking.business.bzwj.BuSpywQgjjzsjftspsxQgsftzdVO;
import com.ccthanking.business.bzwj.dao.BuSpywQgjjzsjftspsxQgsftzdDao;
import com.ccthanking.business.bzwj.service.BuSpywQgjjzsjftspsxQgsftzdService;
import com.ccthanking.business.spyw.vo.BuSpywScxmmjhsdVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywQgjjzsjftspsxQgsftzdService.java </p>
 * <p> 功能：八墙体材料专项基金预收款收费通知单 </p>
 *
 * <p><a href="BuSpywQgjjzsjftspsxQgsftzdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-11-07
 * 
 */

@Service
public class BuSpywQgjjzsjftspsxQgsftzdServiceImpl extends Base1ServiceImpl<BuSpywQgjjzsjftspsxQgsftzdVO, String> implements BuSpywQgjjzsjftspsxQgsftzdService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywQgjjzsjftspsxQgsftzdServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_QGJJZSJFTSPSX_QGSFTZD;
    
    private BuSpywQgjjzsjftspsxQgsftzdDao buSpywQgjjzsjftspsxQgsftzdDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywQgjjzsjftspsxQgsftzdDao.queryCondition(json, null, null);

          
            
        }catch (DaoException e) {
        	logger.error("八墙体材料专项基金预收款收费通知单{}", e.getMessage());
			
			SystemException.handleMessageException("八墙体材料专项基金预收款收费通知单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
 // @Override
 public String getCount() throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			Calendar a=Calendar.getInstance();
	        String year= Integer.toString(a.get(Calendar.YEAR));
	        String f = "%0" + 3 + "d";	
			String count = buSpywQgjjzsjftspsxQgsftzdDao.getCount();
            domresult=year+String.format(f, Integer.parseInt(count)+1);
            
        }catch (DaoException e) {
        	logger.error("八墙体材料专项基金预收款收费通知单{}", e.getMessage());
		
        } finally {
        }
        return domresult;

    }
    
    public String queryByLzbz(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywQgjjzsjftspsxQgsftzdDao.queryByLzbz(json);

            
        }catch (DaoException e) {
        	logger.error("三产项目面积核算单{}", e.getMessage());
            
            SystemException.handleMessageException("三产项目面积核算单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywQgjjzsjftspsxQgsftzdVO vo = new BuSpywQgjjzsjftspsxQgsftzdVO();

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
			buSpywQgjjzsjftspsxQgsftzdDao.save(vo);
            resultVO = vo.getRowJson();

         

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("八墙体材料专项基金预收款收费通知单{}", e.getMessage());
          
            SystemException.handleMessageException("八墙体材料专项基金预收款收费通知单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywQgjjzsjftspsxQgsftzdVO vo = new BuSpywQgjjzsjftspsxQgsftzdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
//            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

            // 修改
            buSpywQgjjzsjftspsxQgsftzdDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("八墙体材料专项基金预收款收费通知单{}", e.getMessage());
          
            SystemException.handleMessageException("八墙体材料专项基金预收款收费通知单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywQgjjzsjftspsxQgsftzdVO vo = new BuSpywQgjjzsjftspsxQgsftzdVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);
//
//			EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
//			vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			buSpywQgjjzsjftspsxQgsftzdDao.delete(BuSpywQgjjzsjftspsxQgsftzdVO.class, vo.getQgjjzsjftspsx_qgsftzd_uid());

			resultVo = vo.getRowJson();

			

		} catch (DaoException e) {
            logger.error("八墙体材料专项基金预收款收费通知单{}", e.getMessage());
          
            SystemException.handleMessageException("八墙体材料专项基金预收款收费通知单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
       public String querytoword(HttpServletResponse response,String id,String ptfName) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();

    
        try {

        	BuSpywQgjjzsjftspsxQgsftzdVO  vo = this.findById(id);

			String filePath=Constants.getString(Constants.PATH_TEMPLATE_BZWJ_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
//			String ftlName=Constants.getString("template_word_js_09","");   不需要初始化    直接步骤文件模板名
//			String workName=Constants.getString("template_word_js_09","")+""+vo.getScxmmjhsd_uid()+"";
			
			String workName="";
			if(ptfName==""){
				ptfName=null;
			}
			if("null".equals(ptfName)){
				ptfName=null;
			}
			if(ptfName==null){
				List<Map<String, String>> obj=buSpywQgjjzsjftspsxQgsftzdDao.queryTpfFileNameByScxmid(vo.getQgjjzsjftspsx_qgsftzd_uid());
		        workName=obj.get(0).get("TMPWJNAME")+vo.getQgjjzsjftspsx_qgsftzd_uid();
		        ptfName=obj.get(0).get("TMPWJNAME");
			}else{
			 workName=ptfName+vo.getQgjjzsjftspsx_qgsftzd_uid()+"";  
			}
			
			if(vo.getRq()==null){
				vo.setRq(new Date());
			}
			Date dd =vo.getRq();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String tbdate="";
			tbdate = sdf.format(dd);
			Map mapvo=vo;
			String date=mapvo.get("RQ")+"";
			mapvo.remove("RQ");
			mapvo.put("RQ",tbdate);
			
	
			FreemarkerHelper.createWord(vo, filePath, ptfName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+"/" +workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			String sql_add="update bu_sp_ywlz_bzwj set alprint=1 where lzbz_wj_uid='"+vo.getLzbz_wj_uid()+"'";
            DBUtil.exec(sql_add);
			return filename+".xml.pdf";
        }catch (DaoException e) {
        	return null;	
        }
		

    }

	@Autowired
	@Qualifier("buSpywQgjjzsjftspsxQgsftzdDaoImpl")
	public void setBuSpywQgjjzsjftspsxQgsftzdDao(BuSpywQgjjzsjftspsxQgsftzdDao buSpywQgjjzsjftspsxQgsftzdDao) {
		this.buSpywQgjjzsjftspsxQgsftzdDao = buSpywQgjjzsjftspsxQgsftzdDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywQgjjzsjftspsxQgsftzdDao);
	}
    
}
