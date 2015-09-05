/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxYxcbsXypjService.java
 * 创建日期： 2015-01-21 上午 10:47:08
 * 功能：    接口：预选承包商信用评价
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-21 上午 10:47:08  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.wxgc.dao.YxWxgcCyzDao;
import com.ccthanking.business.wxgc.dao.YxXypjWgsjDao;
import com.ccthanking.business.wxgc.dao.YxYxcbsXypjDao;
import com.ccthanking.business.wxgc.service.YxWgsjService;
import com.ccthanking.business.wxgc.service.YxWxgcService;
import com.ccthanking.business.wxgc.service.YxXypjWgsjService;
import com.ccthanking.business.wxgc.service.YxYxcbsService;
import com.ccthanking.business.wxgc.service.YxYxcbsXypjService;
import com.ccthanking.business.wxgc.vo.YxWgsjVO;
import com.ccthanking.business.wxgc.vo.YxWxgcVO;
import com.ccthanking.business.wxgc.vo.YxXypjWgsjVO;
import com.ccthanking.business.wxgc.vo.YxYxcbsVO;
import com.ccthanking.business.wxgc.vo.YxYxcbsXypjVO;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.DateTimeUtil;
import com.ccthanking.framework.util.Pub;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxYxcbsXypjService.java </p>
 * <p> 功能：预选承包商信用评价 </p>
 *
 * <p><a href="YxYxcbsXypjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-21
 * 
 */

@Service
public class YxYxcbsXypjServiceImpl extends Base1ServiceImpl<YxYxcbsXypjVO, String> implements YxYxcbsXypjService {
	private static Logger logger = LoggerFactory.getLogger(YxYxcbsXypjServiceImpl.class);
	
    private YxYxcbsXypjDao yxYxcbsXypjDao;
    @Autowired
    private YxXypjWgsjDao yxXypjWgsjDao;
    @Autowired
    private YxWxgcCyzDao yxWxgcCyzDao;
    
    @Autowired
    private YxWgsjService yxWgsjService;
    @Autowired
    private YxXypjWgsjService yxXypjWgsjService;
    @Autowired
    private YxYxcbsService yxYxcbsService;
    @Autowired
    private YxWxgcService yxWxgcService;
    // @Override
    public String queryCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxYxcbsXypjDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("预选承包商信用评价{}", e.getMessage());
        } finally {
        }
        return domresult;
    }
    
    public String queryAllXypjXqCondition(Map map) throws Exception {
//    	User user = ActionContext.getCurrentUserInThread();
    	JSONArray array = new JSONArray();
        try {
        	List<Map<String,String>> domresult = yxYxcbsXypjDao.queryAllXypjXqCondition(map);
        	if(domresult!=null&&domresult.size()>0){
        		for(Map<String,String> maptemp:domresult){
        			JSONObject jsonObj = new JSONObject();
        			Iterator entries = maptemp.entrySet().iterator();
        			while (entries.hasNext()) {
        			    Map.Entry entry = (Map.Entry) entries.next();
        			    jsonObj.put(entry.getKey(), entry.getValue());
        			}
        			array.add(jsonObj);
        		}
        		
        	}
        	
        }catch (DaoException e) {
        	logger.error("预选承包商信用评价详情查询{}", e.getMessage());
        } finally {
        }
        return array.toString();
    }
    
    
    public String queryXypjLx(Map map) throws Exception {
//    	User user = ActionContext.getCurrentUserInThread();
    	JSONArray array = new JSONArray();
        try {
        	List<Map<String,String>> domresult = yxYxcbsXypjDao.queryXypjLx(map);
        	if(domresult!=null&&domresult.size()>0){
        		for(Map<String,String> maptemp:domresult){
        			JSONObject jsonObj = new JSONObject();
        			Iterator entries = maptemp.entrySet().iterator();
        			while (entries.hasNext()) {
        			    Map.Entry entry = (Map.Entry) entries.next();
        			    jsonObj.put(entry.getKey(), entry.getValue());
        			}
        			array.add(jsonObj);
        		}
        		
        	}
        	
        }catch (DaoException e) {
        	logger.error("预选承包商信用评价类型查询{}", e.getMessage());
        } finally {
        }
        return array.toString();
    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxYxcbsXypjVO vo = new YxYxcbsXypjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
            vo.setValueFromJson(obj);
           
            vo.setYxcbs_uid((String)obj.get("ZB_YXCBS_UID"));//中标企业
            vo.setWxgc_uid((String)obj.get("GC_WXGC_UID"));//微型工程UID
            
            vo.setXypj_shzt("30");
            vo.setUser1(user.getAccount());
            vo.setUser1_pjrq(new Date());
            
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            
//          String gctypecode=(String)obj.get("GC_TYPE_CODE");
//          List<String> typeList= new ArrayList<String>();
//			Collections.addAll(typeList, gctypecode.split(","));
            
			yxYxcbsXypjDao.save(vo);
		
			String xypj_uid=vo.getXypj_uid();
            Map map =new HashMap();
            map.put("xypj_uid", xypj_uid);
            List<Map<String,String>> domresult = yxYxcbsXypjDao.queryXypjLx(map);
        	if(domresult!=null&&domresult.size()>0){
        		for(Map<String,String> maptemp:domresult){
        			String dic_code=maptemp.get("DIC_CODE");
        		    String selectvalue=(String)obj.get("ST_"+dic_code);
        		    String textareavalue=(String)obj.get("TA_"+dic_code);
//        		    String idvalue=(String)obj.get("ID_"+dic_code);
//        			String inputvalue=(String)obj.get("IT_"+dic_code);
        		    YxWgsjVO yxWgsjVO=  yxWgsjService.findById(selectvalue);
        		    if(yxWgsjVO!=null&&yxWgsjVO.getWgsj_uid()!=null&&!"".equals(yxWgsjVO.getWgsj_uid())){
        		    	YxXypjWgsjVO yxXypjWgsjVO=new YxXypjWgsjVO();
             		    yxXypjWgsjVO.setBz(textareavalue);
             		    yxXypjWgsjVO.setWgsj_uid(yxWgsjVO.getWgsj_uid());
             		    yxXypjWgsjVO.setXypj_uid(xypj_uid);
             		    yxXypjWgsjVO.setKffz(yxWgsjVO.getWgsjkffz());//分值
             		    yxYxcbsXypjDao.save(yxXypjWgsjVO);
        		    }
        		}
        	}
			
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("预选承包商信用评价{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxYxcbsXypjVO vo = new YxYxcbsXypjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            vo.setYxcbs_uid((String)obj.get("ZB_YXCBS_UID"));//中标企业
            vo.setWxgc_uid((String)obj.get("GC_WXGC_UID"));//微型工程UID
            
            String shzt=(String)obj.get("XYPJ_SHZT");
            
            if(shzt!=null&&"30".equals(shzt)){//监管部门
            	vo.setXypj_shzt("3");
            	vo.setUser2(user.getAccount());
            	vo.setUser2_pjrq(new Date());
            }else if(shzt!=null&&"3".equals(shzt)){//分管领导
            	vo.setXypj_shzt("10");
            	vo.setSfyx("1");
            	vo.setUser3(user.getAccount());
            	vo.setUser3_pjrq2(new Date());
            }
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());

            int zfz=0;
            String xypj_uid=vo.getXypj_uid();
            Map map =new HashMap();
            map.put("xypj_uid", xypj_uid);
            List<Map<String,String>> domresult = yxYxcbsXypjDao.queryXypjLx(map);
        	if(domresult!=null&&domresult.size()>0){
        		for(Map<String,String> maptemp:domresult){
        			String dic_code=maptemp.get("DIC_CODE");
        		    String selectvalue=(String)obj.get("ST_"+dic_code);
        		    String textareavalue=(String)obj.get("TA_"+dic_code);
        		    String idvalue=(String)obj.get("ID_"+dic_code);
//        			String inputvalue=(String)obj.get("IT_"+dic_code);
        		    YxWgsjVO yxWgsjVO=  yxWgsjService.findById(selectvalue);
        		    if(yxWgsjVO!=null&&yxWgsjVO.getWgsj_uid()!=null&&!"".equals(yxWgsjVO.getWgsj_uid())){
        		    	//通过 idvalue  查询YxXypjWgsjVO 
        		    	YxXypjWgsjVO yxXypjWgsjVO=yxXypjWgsjService.findById(idvalue); //new YxXypjWgsjVO();
        		    	if(!textareavalue.equals(yxXypjWgsjVO.getBz())){
        		    		yxXypjWgsjVO.setBz(textareavalue);
        		    	}
        		    	yxXypjWgsjVO.setWgsj_uid(yxWgsjVO.getWgsj_uid());
    		    		yxXypjWgsjVO.setKffz(yxWgsjVO.getWgsjkffz());//分值
    		    		
    		    		if(shzt!=null&&!"3".equals(shzt)){//分管领导审核，计算总分值
    		    			zfz=zfz+Integer.valueOf(yxWgsjVO.getWgsjkffz());
    		            }
             		    yxXypjWgsjVO.setXypj_uid(xypj_uid);
             		    yxYxcbsXypjDao.update(yxXypjWgsjVO);
        		    }
        		}
        	}
        	
        	int zhzfz=100-zfz;
            if(zhzfz<=0){//总分值小于等0清除，修改预选承包商有效标志，后面不能再报名参与企业微型工程招标
            	vo.setKfzfz(String.valueOf(0));
            	YxYxcbsVO yxYxcbsVO=yxYxcbsService.findById((String)obj.get("ZB_YXCBS_UID"));
            	yxYxcbsVO.setEnabled("0");
            	yxYxcbsXypjDao.update(yxYxcbsVO);
            }else{
            	vo.setKfzfz(String.valueOf(zhzfz));
            }
            // 修改
            yxYxcbsXypjDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("预选承包商信用评价{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		YxYxcbsXypjVO vo = new YxYxcbsXypjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			vo.setValueFromJson(jsonObj);
			//删除   根据据主键
			yxYxcbsXypjDao.delete(YxYxcbsXypjVO.class, vo.getYxcbs_uid());
			resultVo = vo.getRowJson();
		} catch (DaoException e) {
            logger.error("预选承包商信用评价{}", e.getMessage());
		} finally {
		}
		return resultVo;
	}

	@Autowired
	@Qualifier("yxYxcbsXypjDaoImpl")
	public void setYxYxcbsXypjDao(YxYxcbsXypjDao yxYxcbsXypjDao) {
		this.yxYxcbsXypjDao = yxYxcbsXypjDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) yxYxcbsXypjDao);
	}

	public String queryfilePathXypj(Map map) throws Exception {
		String filepath="";
		String xypjuid=(String)map.get("XYPJ_UID");
		
		YxYxcbsXypjVO yxYxcbsXypjVO=this.findById(xypjuid);
		if(yxYxcbsXypjVO!=null&&yxYxcbsXypjVO.getXypj_uid()!=null&&"10".equals(yxYxcbsXypjVO.getXypj_shzt())){
			
			YxWxgcVO yxWxgcVO=yxWxgcService.findById(yxYxcbsXypjVO.getWxgc_uid());
			
			Map maptemp=new HashMap();
			
			maptemp.put("JSDW", yxWxgcVO.getJsdw());
			maptemp.put("GC_NAME", yxWxgcVO.getGc_name());
			maptemp.put("JHGQ", yxWxgcVO.getJhgq());
			
			
			String sv = yxWxgcVO.getZbj();
			
			if(sv!=null&&!"".equals(sv)){
				sv = Pub.NumberToThousand(sv);
				if(!Pub.empty(sv)){
					sv = Pub.NumberAddDec(sv);
				}
			}
			
			maptemp.put("ZBJ",sv );
			maptemp.put("ZB_NAME", yxWxgcVO.getZb_name());
			
			String XMFZR="";
			String ZYZGZH="";
			map.put("yxcbsuid", yxWxgcVO.getZb_yxcbs_uid());
			map.put("wxgcuid", yxWxgcVO.getWxgc_uid());
			List<Map<String, String>> cyzlist=yxWxgcCyzDao.wxgcCyzList(map);
			if(cyzlist!=null){
				Map<String, String> cyzmap=cyzlist.get(0);
				XMFZR=cyzmap.get("XMFZR");
				ZYZGZH=cyzmap.get("ZYZGZH");
			}
			
			maptemp.put("XMFZR", XMFZR);
			maptemp.put("ZYZGZH", ZYZGZH);
			
			String JHKGRQ="";
			if(yxWxgcVO.getJhkgrq()!=null){
				JHKGRQ = DateTimeUtil.getDateTime(yxWxgcVO.getJhkgrq(), "yyyy-MM-dd");
			}
			
			String JHJGRQ="";
			if(yxWxgcVO.getJhjgrq()!=null){
				JHJGRQ = DateTimeUtil.getDateTime(yxWxgcVO.getJhjgrq(), "yyyy-MM-dd");
			}
			
			maptemp.put("JHKGRQ",JHKGRQ );
			maptemp.put("JHJGRQ", JHJGRQ);
			
			maptemp.put("PDDF", yxYxcbsXypjVO.getKfzfz());//评定得分
			
			
//			1、90-100分，优秀；
//			2、80-89分，良好；
//			3、70-79分，合格；
//			4、60-69分，基本合格；
//			5、1-59分，不合格；
//			6、0分，清除。
			String kfzfz=yxYxcbsXypjVO.getKfzfz();
			int zfz=Integer.valueOf(kfzfz);
			
			String pddj="清除";
			if(zfz>=90){
				pddj="优秀";
			}else if(80<=zfz&&zfz<=89){
				pddj="良好";
			}else if(70<=zfz&&zfz<=79){
				pddj="合格";
			}else if(60<=zfz&&zfz<=69){
				pddj="基本合格";
			}else if(0<=zfz&&zfz<=59){
				pddj="不合格";
			}
			
			maptemp.put("PDDJ", pddj);//评定等级
			
			
			
			List<Map<String,String>> templist=new ArrayList();
			List<Map<String,String>> xypjWgsjList=yxXypjWgsjDao.getXypjWgsjList(map);
			
			if(xypjWgsjList!=null&&xypjWgsjList.size()>0){
				for(Map<String,String> xypjlistmap :xypjWgsjList){
					Map listmap=new HashMap();
					String dic_value =xypjlistmap.get("DIC_VALUE");
					String wgsjnr =xypjlistmap.get("WGSJNR");
					String bz =xypjlistmap.get("BZ");
					String kffz =xypjlistmap.get("KFFZ");
					
					String strhz=dic_value+":"+wgsjnr+";";
					if(bz!=null&&!"".equals(bz)){
						strhz+="备注："+bz+";";
					}
					if(kffz!=null&&!"".equals(kffz)){
						strhz+="扣分："+kffz+";";
					}
					listmap.put("ZHPJXX", strhz);
					templist.add(listmap);
				}
			}
			maptemp.put("templist", templist);
		
			//模版地址
			String fileUrl="新区微型工程中标单位评价表.ftl";
			String ftlName="新区微型工程中标单位评价表.ftl";
			
			filepath=this.ftlToPdf(maptemp, fileUrl, ftlName, yxYxcbsXypjVO.getXypj_uid());
			
		}

		return filepath;
	}
	
	
	/**
     * 通过加载的数据进行ftl模版转换成PDF
     * mapFtl      加载的数据
     * fileUrl  模版存放路径
     * ftlName  模版名称
     * */
    public String ftlToPdf(Map mapFtl,String fileUrl,String ftlName,String wxgcuid){
    	String pdfPath=null;
    	try{    
//	        SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSYX"));
//	        String fileRoot = syspara.getSysParaConfigureParavalue1();  
    		String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			
	    	String templatePath = filePath+File.separator+fileUrl.substring(0, fileUrl.indexOf(ftlName));
	    	pdfPath=pdfPath+File.separator+fileUrl.substring(0, fileUrl.indexOf(ftlName));
	    	
	    	ftlName=ftlName.substring(0, ftlName.indexOf(".ftl"));//去掉后缀

	    	if(FreemarkerHelper.createWord(mapFtl, templatePath, ftlName, pdfPath, ftlName+"_"+wxgcuid)){
	    		pdfPath=pdfPath+File.separator+ftlName+"_"+wxgcuid+"";
	    		Word2PDF.toPdf(pdfPath);
	    		pdfPath=pdfPath+".xml.pdf";//拼接出转换的PDF文件存放路径
	    	}
	    	
    	}catch(Exception e){
	   		 logger.error("微型工程招标公告模版文件转换PDF失败{}", e);
	         SystemException.handleMessageException("微型工程招标公告模版文件转换PDF失败,请联系相关人员处理");
    	}	
    	
    	return pdfPath;
    }
    
}
