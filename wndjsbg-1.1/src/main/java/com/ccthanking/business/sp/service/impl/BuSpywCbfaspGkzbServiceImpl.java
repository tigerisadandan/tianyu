/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sp.service.BuSpywCbfaspGkzbService.java
 * 创建日期： 2015-05-06 下午 02:07:52
 * 功能：    接口：公开（邀请）招标合同备案表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-06 下午 02:07:52  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.BuSpywCbfaspGkzbDao;
import com.ccthanking.business.sp.service.BuSpywCbfaspGkzbService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspGkzbVO;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywCbfaspGkzbService.java </p>
 * <p> 功能：公开（邀请）招标合同备案表 </p>
 *
 * <p><a href="BuSpywCbfaspGkzbService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-06
 * 
 */

@Service
public class BuSpywCbfaspGkzbServiceImpl extends Base1ServiceImpl<BuSpywCbfaspGkzbVO, String> implements BuSpywCbfaspGkzbService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspGkzbServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_CBFASP_GKZB;
    
    private BuSpywCbfaspGkzbDao buSpywCbfaspGkzbDao;
    
    @Autowired
    private BuSpLzhfService buSpLzhfService;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywCbfaspGkzbDao.queryCondition(json, null, null);
   
        }catch (DaoException e) {
        	logger.error("公开（邀请）招标合同备案表{}", e.getMessage());
			SystemException.handleMessageException("公开（邀请）招标合同备案表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
public String download(String id,String ywlz,String ty) throws Exception {
        
    	Connection conn = DBUtil.getConnection();
       	BuSpywCbfaspGkzbVO tmp=new BuSpywCbfaspGkzbVO();
     	if(id!=""&&id!=null){
	 		tmp =this.findById(id);
	 	}else{
	 		id=buSpywCbfaspGkzbDao.findByZjId(ywlz,ty);
	 		tmp =this.findById(id);
	 	}
     	/*
     	
            bs.setFieldDic("JS_LEIXING","JS_LEIXING");
            bs.setFieldDic("TZ_LEIXING","TZ_LEIXING");
     	*/
     	String []gcdic={"0","1","2","3","4","5","6","7"};
     	String []gcvalue={"工业建筑","经济适用房","安置房","商品住宅","公共建筑","工业建筑","保障房","市政基础设施"};
     	for (int i = 0; i < gcdic.length; i++) {
			if(gcdic[i].equals(tmp.get("GC_XINGZHI"))){
				tmp.put("GC_XINGZHI_SV", gcvalue[i]);
			}
		}
     	
     	String []tzdic={"0","1","2"};
     	String []tzvalue={"政府投资项目","非政府投资项目","外资项目"};
     	for (int i = 0; i < tzdic.length; i++) {
			if(tzdic[i].equals(tmp.get("TZ_LEIXING"))){
				tmp.put("TZ_LEIXING_SV", tzvalue[i]);
			}
		}
     	
     	String []jsdic={"0","1","2","3","4","5","6"};
     	String []jsvalue={"新建","扩建","改建","恢复","迁建","拆除","其他"};
     	for (int i = 0; i < jsdic.length; i++) {
			if(jsdic[i].equals(tmp.get("JS_LEIXING"))){
				tmp.put("JS_LEIXING_SV", jsvalue[i]);
			}
		}
     	
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
    		 fileName_pre = "无锡新区施工工程公开（邀请）招标初步方案审批表";
    		 templateName=fileName_pre;
    		 fileName="无锡新区施工工程公开（邀请）招标初步方案审批表"+id;
    		 filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	}
    	if("jl".equals(ty)){
    		 templatePath=Constants.getString(Constants.PATH_TEMPLATE_WORD, "");
    		 fileName_pre = "监理工程公开（邀请）招标初步方案审批表";
    		 templateName=fileName_pre;
    		 fileName="监理工程公开（邀请）招标初步方案审批表"+id;
    		 filePath=Constants.getString(Constants.PATH_TEMPLATE_XML, "");
    	}
    
    	Date jhkgrq=tmp.getKg_date();
    	Date jhjgrq=tmp.getJg_date();
    	Date wgrq=tmp.getWg_date();
    			
		SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		String kg=rqgs.format(jhkgrq);
		String jg=rqgs.format(jhjgrq);
		String wg=rqgs.format(wgrq);
		
		tmp.put("KG_DATE", kg);
		tmp.put("JG_DATE", jg);
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
        BuSpywCbfaspGkzbVO vo = new BuSpywCbfaspGkzbVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
        
            vo.setCbfasp_gkzb_uid(DBUtil.getSequenceValue("CBFASP_GKZB_UID")); // 主键
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            // 插入
			buSpywCbfaspGkzbDao.save(vo);
            resultVO = vo.getRowJson();

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("公开（邀请）招标合同备案表{}", e.getMessage());
            SystemException.handleMessageException("公开（邀请）招标合同备案表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywCbfaspGkzbVO vo = new BuSpywCbfaspGkzbVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
        
            // 修改
            buSpywCbfaspGkzbDao.update(vo);
            resultVO = vo.getRowJson();

      
        } catch (DaoException e) {
            logger.error("公开（邀请）招标合同备案表{}", e.getMessage());
            SystemException.handleMessageException("公开（邀请）招标合同备案表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpywCbfaspGkzbVO vo = new BuSpywCbfaspGkzbVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			buSpywCbfaspGkzbDao.delete(BuSpywCbfaspGkzbVO.class, vo.getCbfasp_gkzb_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("公开（邀请）招标合同备案表{}", e.getMessage());
           SystemException.handleMessageException("公开（邀请）招标合同备案表删除失败,请联系相关人员处理");
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
                id= buSpywCbfaspGkzbDao.findByZjId(YWLZ_UID,"sg");
                if("".equals(id)){
                	return "0";
                }
                BuSpywCbfaspGkzbVO sgvo = new BuSpywCbfaspGkzbVO();
                sgvo.setCbfasp_gkzb_uid(id);
                sgvo.setHt_bah((String) object.get("PIJIAN_CODE"));
                buSpywCbfaspGkzbDao.update(sgvo);
            }else if(object.get("CLK_UID").equals(Constants.JS_SPYW03_HFCL_JL_CLUID)){
                id= buSpywCbfaspGkzbDao.findByZjId(YWLZ_UID,"jl");
                if("".equals(id)){
                	return "0";
                }
                BuSpywCbfaspGkzbVO jlvo = new BuSpywCbfaspGkzbVO();
                jlvo.setCbfasp_gkzb_uid(id);
                jlvo.setHt_bah((String) object.get("PIJIAN_CODE"));
                buSpywCbfaspGkzbDao.update(jlvo);
            }
            //通过业务流转UID查询企业填报的数据，map类型
            BuSpywCbfaspGkzbVO mapFtl=new BuSpywCbfaspGkzbVO();
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

	@Autowired
	@Qualifier("buSpywCbfaspGkzbDaoImpl")
	public void setBuSpywCbfaspGkzbDao(BuSpywCbfaspGkzbDao buSpywCbfaspGkzbDao) {
		this.buSpywCbfaspGkzbDao = buSpywCbfaspGkzbDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywCbfaspGkzbDao);
	}
    
}
