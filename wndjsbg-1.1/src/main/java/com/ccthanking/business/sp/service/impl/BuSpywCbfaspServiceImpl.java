/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCbfaspService.java
 * 创建日期： 2014-05-28 上午 10:34:34
 * 功能：    接口：资质
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-05-28 上午 10:34:34  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.bzwj.BuSpywCbfaspGcjsxmzjfbhtbabVO;
import com.ccthanking.business.commons.Utils;
import com.ccthanking.business.sp.dao.BuSpywCbfaspDao;
import com.ccthanking.business.sp.dao.BuSpywCbfaspFbfaDao;
import com.ccthanking.business.sp.service.BuSpywCbfaspService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspFbfaVO;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;
import com.ccthanking.business.spyw.vo.BuSpywCspsxkzsqVO;
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
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywCbfaspService.java </p>
 * <p> 功能：资质 </p>
 *
 * <p><a href="BuSpywCbfaspService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-05-28
 * 
 */

@Service
public class BuSpywCbfaspServiceImpl extends Base1ServiceImpl<BuSpywCbfaspVO, String> implements BuSpywCbfaspService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_CBFASP;
    
    private BuSpywCbfaspDao buSpywCbfaspDao;
    
    private BuSpywCbfaspFbfaDao buSpywCbfaspFbfaDao;
    @Autowired
    private BuSpLzhfService buSpLzhfService;
    @Autowired
	@Qualifier("buSpywCbfaspDaoImpl")
	public void setBuSpywCbfaspDao(BuSpywCbfaspDao buSpywCbfaspDao) {
		this.buSpywCbfaspDao = buSpywCbfaspDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywCbfaspDao);
	}
	
	@Autowired
	@Qualifier("buSpywCbfaspFbfaDaoImpl")
	public void setBuSpywCbfaspDao(BuSpywCbfaspFbfaDao buSpywCbfaspFbfaDao) {
		this.buSpywCbfaspFbfaDao = buSpywCbfaspFbfaDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywCbfaspFbfaDao);
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywCbfaspDao.queryCondition(json, null, null);

           //LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<资质>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表数据查询失败", e.getMessage());
        	SystemException.handleMessageException("无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表数据查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }

    // @Override
    public String download(String id,String ywlz,String ty) throws Exception {
           
    	Connection conn = DBUtil.getConnection();
       	BuSpywCbfaspVO tmp=new BuSpywCbfaspVO();
     	if(id!=""&&id!=null){
	 		tmp =this.findById(id);
	 	}else{
	 		id=buSpywCbfaspDao.findByZjId(ywlz,ty);
	 		tmp =this.findById(id);
	 	}
     	/*
     	tmp.bindFieldToDic("NEIRONG", "SGNR");
     	tmp.bindFieldToDic("CB_XINGZHI", "CB_XINGZHI");
     	tmp.bindFieldToDic("SFSZGC", "SF");
     	tmp.bindFieldToDic("BID_TYPE", "BID_TYPE");*/
     	String []dic={"GJG","JZZS","MQMC","QTGC","SBAZ","SZGC","TFZJ","TJGC","XF"};
     	String []value={"钢结构","建筑装饰","幕墙门窗","其他工程","设备安装","市政工程","土方桩基","土建工程","消防"};
     	for (int i = 0; i < dic.length; i++) {
			if(dic[i].equals(tmp.get("NEIRONG"))){
				tmp.put("NEIRONG_SV", value[i]);
			}
		}
     	
     	String []cbdic={"0","1"};
     	String []cbvalue={"施工总承包","专业承包"};
     	for (int i = 0; i < cbdic.length; i++) {
			if(cbdic[i].equals(tmp.get("CB_XINGZHI"))){
				tmp.put("CB_XINGZHI_SV", cbvalue[i]);
			}
		}
     	
     	String []sfdic={"0","1"};
     	String []sfvalue={"否","是"};
     	for (int i = 0; i < sfdic.length; i++) {
			if(sfdic[i].equals(tmp.get("SFSZGC"))){
				tmp.put("SFSZGC_SV", sfvalue[i]);
			}
		}
     	
     	String []bdic={"1","2","3"};
     	String []bvalue={"公开招标","建筑装饰","直接发包"};
     	for (int i = 0; i < bdic.length; i++) {
			if(bdic[i].equals(tmp.get("BID_TYPE"))){
				tmp.put("BID_TYPE_SV", bvalue[i]);
			}
		}
     	
     	
     	String sql="select * from projects_units where UNITs_UID in("+tmp.getDt_ids()+")";
     	List<?> tmpPtmxVO=DBUtil.queryReturnList(conn, sql);
    	//List<?> tmpPtmxVO=buSpywCbfaspFbfaDao.find(id); old子表
    	String templatePath="";
    	String fileName_pre="";
    	String templateName="";
    	String fileName="";
    	String filePath="";
    	
    	if("sg".equals(ty)){
    		 templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    		 fileName_pre = Constants.getString("template_word_js_03", "无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表");
    		 templateName=fileName_pre;
    		 fileName="无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表"+id;
    		 filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	}
    	if("jl".equals(ty)){
    		 templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    		 fileName_pre = Constants.getString("template_word_js_032", "无锡新区监理工程直接发包初步方案审批表");
    		 templateName=fileName_pre;
    		 fileName="无锡新区监理工程直接发包初步方案审批表"+id;
    		 filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	}
    
    	Date bjrq=tmp.getDate_bjrq();
    	Date jhkgrq=tmp.getDate_jhkg();
    	Date jhjgrq=tmp.getDate_jhjg();
    	Date wgrq=tmp.getWg_date();
    			
		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		String bj=rqgs.format(bjrq);
		String kg=rqgs.format(jhkgrq);
		String jg=rqgs.format(jhjgrq);
		String wg=rqgs.format(wgrq);
		
		tmp.put("DATE_BJRQ", bj);
		tmp.put("DATE_JHKG", kg);
		tmp.put("DATE_JHJG", jg);
		tmp.put("WG_DATE", wg);
    	
       	if(tmpPtmxVO!=null&&tmpPtmxVO.size()>0){
       		tmp.put("templist", tmpPtmxVO);
       	}else{
       		tmp.put("templist", "");
       	}
       	
           
           if(FreemarkerHelper.createWord(tmp, templatePath, templateName, filePath, fileName)){
           	String pathName=filePath+fileName;
               Word2PDF.toPdf(pathName);
           }
           String path=filePath+fileName+".xml.pdf";
           DBUtil.closeConnetion(conn);
           return path;
       }
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywCbfaspVO vo = new BuSpywCbfaspVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            vo.setCbfasp_uid(DBUtil.getSequenceValue("CBFASP_UID")); // 主键
            vo.setCreated_date(new Date());
            vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
            vo.setCreated_uid(ActionContext.getCurrentUserInThread().getAccount());

            // 插入
			buSpywCbfaspDao.save(vo);
			
			doInsertCbfa((JSONObject) list.get(0),vo.getCbfasp_uid());
			
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "资质新增成功", user, "", "");

            
        } catch (DaoException e) {
            logger.error("发包方案{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "资质新增失败", user, "", "");
            SystemException.handleMessageException("资质新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    public String insertHF(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpywCbfaspGcjsxmzjfbhtbabVO vo = new BuSpywCbfaspGcjsxmzjfbhtbabVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            vo.setCbfasp_gcjsxmzjfbhtbab_uid(DBUtil.getSequenceValue("CBFASP_GCJSXMZJFBHTBAB_UID")); // 主键
           
            // 插入
			buSpywCbfaspDao.save(vo);
			
			doInsertCbfa((JSONObject) list.get(0),vo.getCbfasp_uid());
			
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "资质新增成功", user, "", "");

            
        } catch (DaoException e) {
            logger.error("发包方案{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "资质新增失败", user, "", "");
            SystemException.handleMessageException("建设项目直接发包合同备案表《核发文件》保存失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    public String doInsertCbfa(JSONObject obj,String fk_uid){
    	JSONArray zizhiArray = null;
		BuSpywCbfaspFbfaVO fpfaVO = null;
        try{
        	zizhiArray = obj.getJSONArray("C_FBNR");
        }catch(JSONException e){
        	logger.error("发包方案为空!");
        }
        
        //处理增项资质
       
        if(zizhiArray!=null){
        	for (int i = 0; i < zizhiArray.size(); i++) {
				if(StringUtils.isBlank((String) zizhiArray.get(i))){
					continue;
				}
				//获取索引
				fpfaVO = new BuSpywCbfaspFbfaVO();
	           
				try {
					fpfaVO.setFbfa_uid(DBUtil.getSequenceValue("FBFA_UID"));
				} catch (Exception e) {
					 logger.error("发包方案{}", e.getMessage());
					 SystemException.handleMessageException("资质新增失败,请联系相关人员处理");
				}
	            fpfaVO.setSerial_no((i+1)+"");
	            fpfaVO.setCbfasp_uid(fk_uid);
	            fpfaVO.setBdhfbh(obj.getJSONArray("BDHFBH").getString(i));
	            fpfaVO.setFbnr(obj.getJSONArray("C_FBNR").getString(i));
	            fpfaVO.setFbfs(obj.getJSONArray("FBFS").getString(i));
	            fpfaVO.setDate_jhfbsj(Utils.formatToDate(obj.getJSONArray("DATE_JHFBSJ").getString(i)));
	            //zengVO.setSerial_no(zengVO.getSg_enterprise_zen_zizhi_uid());
	            
	            User  user = ActionContext.getCurrentUserInThread();
	            
	            
	            fpfaVO.setCreated_date(new Date());
	            fpfaVO.setCreated_name(user.getName());
	            fpfaVO.setCreated_uid(user.getAccount());
	            
	            buSpywCbfaspFbfaDao.save(fpfaVO);
	            
			}
        }
    	return null;
    }
    
    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywCbfaspVO vo = new BuSpywCbfaspVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getAccount());
            // 修改
            
            buSpywCbfaspFbfaDao.deleteFasps(vo.getCbfasp_uid());
            
            buSpywCbfaspDao.update(vo);
            
            doInsertCbfa((JSONObject) list.get(0), vo.getCbfasp_uid());
            
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,  user.getName() + "资质修改成功", user, "", "");

        } catch (DaoException e) {
            logger.error("资质{}", e.getMessage());
            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE,  user.getName() + "资质修改失败", user, "", "");
            SystemException.handleMessageException("资质修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String findByPerson(String lx,String uid,String bb_code) throws Exception{
    	
    	User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		resultVo=buSpywCbfaspDao.findByPerson(lx,uid,bb_code);
		
		return resultVo;
    }
    public String findByCompany(String lx,String bb_code) throws Exception{
    	
    	User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		resultVo=buSpywCbfaspDao.findByCompany(lx,bb_code);
		
		return resultVo;
    }
   
    public String getNum(String lx) throws Exception{
    	
    	User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		
		Calendar a=Calendar.getInstance();
        String year= Integer.toString(a.get(Calendar.YEAR));
        String f = "%0" + 3 + "d";	
		int tem=Integer.parseInt(buSpywCbfaspDao.getNum(31));//获取通过的数量  31为该直接发包的ywlz_uid      		
   	 	int count=Integer.parseInt(buSpywCbfaspDao.getCount(31,lx));//获取通过的施工的表单记录
   	 	if("sg".equals(lx)){
   	 		resultVo=year+String.format(f, tem)+"S"+String.format(f, count);
   	 	}else if("jl".equals(lx)){
   	 		resultVo=year+String.format(f, tem)+"J"+String.format(f, count);
   	 	}else{}
		return resultVo;
    }
    
    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywCbfaspVO vo = new BuSpywCbfaspVO();
		try {
			
		} catch (DaoException e) {
            logger.error("资质{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,  user.getName() + "资质删除失败", user, "", "");
            SystemException.handleMessageException("资质删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    /**
     * 材料核发调用获取MAP数据
     * */
	public String ywlzclhf(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();
//      obj.YWLZ_UID = data.YWLZ_UID;
//		obj.YWCL_UID = data.YWCL_UID;
//		obj.PIJIAN_CODE = codeid;
//		obj.PIJIAN_NAME = nameid;
//		obj.LINGJIAN_REN =ljr;
//		obj.LINGJIAN_PHONE=ljrdh;
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            String YWLZ_UID=(String)object.get("YWLZ_UID");
            
            String id="";  //业务填报uid
            if(object.get("CLK_UID").equals(Constants.JS_SPYW03_HFCL_SG_CLUID)){
                id= buSpywCbfaspDao.getIdByYwlzuid(YWLZ_UID,"sg");
                if("".equals(id)){
                	return "0";
                }
                BuSpywCbfaspVO sgvo = new BuSpywCbfaspVO();
                sgvo.setCbfasp_uid(id);
                sgvo.setHt_bah((String) object.get("PIJIAN_CODE"));
                buSpywCbfaspDao.update(sgvo);
            }else if(object.get("CLK_UID").equals(Constants.JS_SPYW03_HFCL_JL_CLUID)){
                id= buSpywCbfaspDao.getIdByYwlzuid(YWLZ_UID,"jl");
                if("".equals(id)){
                	return "0";
                }
                BuSpywCbfaspVO jlvo = new BuSpywCbfaspVO();
                jlvo.setCbfasp_uid(id);
                jlvo.setHt_bah((String) object.get("PIJIAN_CODE"));
                buSpywCbfaspDao.update(jlvo);
            }
            
            //通过业务流转UID查询企业填报的数据，map类型
            BuSpywCbfaspVO mapFtl=new BuSpywCbfaspVO();
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
