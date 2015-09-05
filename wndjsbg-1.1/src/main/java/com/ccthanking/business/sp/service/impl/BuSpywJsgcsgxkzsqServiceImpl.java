/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywJsgcsgxkzsqService.java
 * 创建日期： 2014-05-27 下午 03:04:23
 * 功能：    接口：施工许可申请表-明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-27 下午 03:04:23  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywJsgcsgxkzsqDao;
import com.ccthanking.business.sp.service.BuSpywJsgcsgxkzsqService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqMxVO;
import com.ccthanking.business.spyw.vo.BuSpywJsgcsgxkzsqVO;
import com.ccthanking.business.spyw.vo.BuSpywPsfayssqVO;
import com.ccthanking.business.spyw.vo.BuSpywYwsrwsqVO;
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
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywJsgcsgxkzsqService.java </p>
 * <p> 功能：施工许可申请表-明细 </p>
 *
 * <p><a href="BuSpywJsgcsgxkzsqService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-27
 * 
 */

@Service
public class BuSpywJsgcsgxkzsqServiceImpl extends Base1ServiceImpl<BuSpywJsgcsgxkzsqVO, String> implements BuSpywJsgcsgxkzsqService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywJsgcsgxkzsqServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_JSGCSGXKZSQ;
    
    private BuSpywJsgcsgxkzsqDao buSpywJsgcsgxkzsqDao;
    @Autowired
    private BuSpLzhfService buSpLzhfService;
    @Autowired
    private BuSpywJsgcsgxkzsqMxServiceImpl buSpywJsgcsgxkzsqMxServiceImpl;
    @Autowired
	@Qualifier("buSpywJsgcsgxkzsqDaoImpl")
	public void setBuSpywJsgcsgxkzsqDao(BuSpywJsgcsgxkzsqDao buSpywJsgcsgxkzsqDao) {
		this.buSpywJsgcsgxkzsqDao = buSpywJsgcsgxkzsqDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywJsgcsgxkzsqDao);

	}
    
    // @Override
 public String download(String id) throws Exception {
        
	    Connection conn = DBUtil.getConnection();
    	BuSpywJsgcsgxkzsqVO tmp =this.findById(id);
    	List<Map<String, String>> getFileNameVO=buSpywJsgcsgxkzsqDao.getFileName(id);
    	String sqlString = "select * from projects_units where units_uid in ("+tmp.getDt_ids()+")";
    	List<?> tmpMxVO = DBUtil.queryReturnList(conn, sqlString);

    	
    	String templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    	String fileName_pre = Constants.getString("template_word_js_05", "新区建筑工程施工许可证申请表");
    	String templateName=fileName_pre;
    	String fileName="新区建筑工程施工许可证申请表"+id;//+tmp.getPsfayssq_uid();
    	String filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	
    	/*for(int i=0;i<getFileNameVO.size();i++){
    		Map<String,String> map=(Map<String,String>)getFileNameVO.get(i);
    		if(map.get("FILE_TYPE").equals("33")){
    			tmp.put("FJSC_LXPW", map.get("FILE_NAME"));
    		}
    		else if(map.get("FILE_TYPE").equals("34")){
    			tmp.put("FJSC_TDSYZ", map.get("FILE_NAME"));
    		}
    		else if(map.get("FILE_TYPE").equals("35")){
    			tmp.put("FJSC_GHXK", map.get("FILE_NAME"));
    		}
    		else if(map.get("FILE_TYPE").equals("36")){
    			tmp.put("FJSC_ZFCN", map.get("FILE_NAME"));
    		}
    		else{}
    	}*/
    	//System.out.print(getFileNameVO);
    	//System.out.print(tmp);
    	//System.out.print(tmpMxVO);
    	if(tmpMxVO!=null&&tmpMxVO.size()>0){
    		tmp.put("templist", tmpMxVO);
    		//BuSpywJsgcsgxkzsqVO ss= (BuSpywJsgcsgxkzsqVO)tmpMxVO.get(0);获取对象
    	}else{
    		tmp.put("templist", "");
    	}
    	tmp.put("templist", tmpMxVO);
		Date kgrq=tmp.getJhkgrq_date();
    	Date jgrq=tmp.getJhjgrq_date();
		Date ghxk=tmp.getFfrq_ghxk();
    	Date lxpw=tmp.getFfrq_lxpw();
    	Date tdsyz=tmp.getFfrq_tdsyz();
    	Date zfcn=tmp.getFfrq_zfcn();
		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		String kg=rqgs.format(kgrq);
		String jg=rqgs.format(jgrq);
		String gh=rqgs.format(ghxk);
		String lx=rqgs.format(lxpw);
		String td=rqgs.format(tdsyz);
		String zf=rqgs.format(zfcn);
		tmp.put("JHKGRQ_DATE", kg);
		tmp.put("JHJGRQ_DATE", jg);
		tmp.put("FFRQ_GHXK", gh);
		tmp.put("FFRQ_LXPW", lx);
		tmp.put("FFRQ_TDSYZ", td);
		tmp.put("FFRQ_ZFCN", zf);
       // FreemarkerHelper.createWord(tmp, filePath, templateName, filePath, fileName);
        
        if(FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName)){
        	//Word2PDF.toPdf(filePath+File.separator+fileName);
        	String pathName=filePath+fileName;
            Word2PDF.toPdf(pathName);
        }
        String path=filePath+fileName+".xml.pdf";
        DBUtil.closeConnetion(conn);
        return path;
    }
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqVO vo=new BuSpywJsgcsgxkzsqVO();
 		String domresult = null;
 		try {
 			domresult = buSpywJsgcsgxkzsqDao.queryCondition(json);
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请—数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请—数据查询失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请—数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	

    }
 
    public String queryReadFile(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqVO vo=new BuSpywJsgcsgxkzsqVO();
 		String domresult = null;
 		try {
 			domresult =buSpywJsgcsgxkzsqDao.queryReadFile(json);
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请—附件信息-数据查询成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请—附件信息-数据查询失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请—附件信息-数据查询失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;
    	

    }
  
    public String insert(String json,Map files) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqVO vo=new BuSpywJsgcsgxkzsqVO();
 		String domresult = null;
 		try {
 			domresult =buSpywJsgcsgxkzsqDao.insert(json, files);
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请—数据新增成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请—数据新增失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请—数据新增失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

    }

    public String update(String json,Map files) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqVO vo=new BuSpywJsgcsgxkzsqVO();
 		String domresult = null;
 		try {
 			domresult =buSpywJsgcsgxkzsqDao.update(json, files);
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请—数据更改成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请—数据更改失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请—数据更改失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

    	

    }

    public String delete(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
    	BuSpywJsgcsgxkzsqVO vo=new BuSpywJsgcsgxkzsqVO();
 		String domresult = null;
 		try {
 			domresult =null;
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,
				user.getName() + "建设工程施工许可证申请—数据删除成功", user, "", "");

 		} catch (DaoException e) {
 			logger.error("表单信息{}", e.getMessage());
 			LogManager.writeUserLog(vo.getSgxkzsq_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
				user.getName() + "建设工程施工许可证申请—数据删除失败", user, "", "");
 			SystemException.handleMessageException("建设工程施工许可证申请—数据删除失败,请联系相关人员处理");
 		} finally {
 		}
 			return domresult;

    	

	}
    /**
     * 材料核发调用获取MAP数据
     * */
    public String ywlzclhf(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();
//        obj.YWLZ_UID = data.YWLZ_UID;
//    	obj.YWCL_UID = data.YWCL_UID;
//    	obj.PIJIAN_CODE = codeid;
//    	obj.PIJIAN_NAME = nameid;
//    	obj.LINGJIAN_REN =ljr;
//    	obj.LINGJIAN_PHONE=ljrdh;
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            String YWLZ_UID=(String)object.get("YWLZ_UID");
            
            
            //通过业务流转UID查询企业填报的数据，map类型
            String id= buSpywJsgcsgxkzsqDao.getIdByYwlzuid(YWLZ_UID);
            BuSpywJsgcsgxkzsqVO mapFtl=new BuSpywJsgcsgxkzsqVO();
            if(StringUtil.isNotBlankStr(id)){
            	 mapFtl =this.findById(id);      	
            }
    	 	
            
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


