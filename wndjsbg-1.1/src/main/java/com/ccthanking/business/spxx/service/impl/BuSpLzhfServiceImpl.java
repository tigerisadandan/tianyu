/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    spxx.service.BuSpLzhfService.java
 * 创建日期： 2014-06-25 下午 03:35:39
 * 功能：    接口：审批业务流转核发文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-25 下午 03:35:39  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.spxx.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.project.vo.JsComClkVO;
import com.ccthanking.business.resources.service.JsComClkService;
import com.ccthanking.business.spxx.dao.BuSpLzhfDao;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpLzhfService.java </p>
 * <p> 功能：审批业务流转核发文件 </p>
 *
 * <p><a href="BuSpLzhfService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-25
 * 
 */

@Service
public class BuSpLzhfServiceImpl extends Base1ServiceImpl<BuSpLzhfVO, String> implements BuSpLzhfService {

	private static Logger logger = LoggerFactory.getLogger(BuSpLzhfServiceImpl.class);
	
    @Autowired
    private JsComClkService jsComClkService;
    
    private BuSpLzhfDao buSpLzhfDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpLzhfDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("审批业务流转核发文件{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转核发文件查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            vo.setValueFromJson(object);
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
          
            // 插入
			buSpLzhfDao.save(vo);
            resultVO = vo.getRowJson();

            String ywlzUid=object.getString("ywlzUid");
            String xmUid=object.getString("xmUid");
            String comUid=object.getString("comUid");
            //转移模版文件
//-------------------------------------------------------待修正，需要传入map值
            
            Map mapFtl=new HashMap();
//            templetFileToCom(mapFtl, ywlzUid,vo.getLzhf_uid(),null);
            
        } catch (DaoException e) {
            logger.error("审批业务流转核发文件{}", e);
             SystemException.handleMessageException("审批业务流转核发文件新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    /**
     * 流转核发调用接口
     * */
    public  boolean saveBuSpLzhfVO(Map mapFtl,Map mapVo){
    	boolean isResult=false;
		User user = ActionContext.getCurrentUserInThread();

        BuSpLzhfVO vo = new BuSpLzhfVO();

        try {
        	
        	vo.setFafang_ren(user.getAccount());//发放人       	 
        	vo.setLingjian_date(new Date());
        	vo.setLingjian_phone((String)mapVo.get("LINGJIAN_PHONE"));
        	vo.setLingjian_ren((String)mapVo.get("LINGJIAN_REN"));
//        	vo.setLzhf_uid((String)mapVo.get("LZHF_UID"));
        	vo.setPijian_code((String)mapVo.get("PIJIAN_CODE"));
        	vo.setPijian_name((String)mapVo.get("PIJIAN_NAME"));
        	vo.setYwcl_uid((String)mapVo.get("YWCL_UID"));
        	vo.setYwlz_uid((String)mapVo.get("YWLZ_UID"));
        	
        	String fzdate=(String)mapVo.get("FZ_DATE");
        	String yxqdate=(String)mapVo.get("YXQ_DATE");
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
//        	java.util.Date date=new java.util.Date();       	
			try {
				Date datefz = sdf.parse(fzdate);
				Date dateyxq = sdf.parse(yxqdate);
	        	vo.setFz_date(datefz);
	        	vo.setYxq_date(dateyxq);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
        	
            // 插入
			buSpLzhfDao.save(vo);

            String ywlzUid=(String)mapVo.get("YWLZ_UID");
            String CLK_UID=(String)mapVo.get("CLK_UID");
//          String xmUid=(String)mapVo.get("XMUID");
//          String comUid=(String)mapVo.get("COMUID");
            //转移模版文件

            templetFileToCom(mapFtl, ywlzUid,vo.getLzhf_uid(),CLK_UID);
            isResult=true;
        } catch (DaoException e) {
            logger.error("审批业务流转核发文件{}", e);
             SystemException.handleMessageException("审批业务流转核发文件新增失败,请联系相关人员处理");
        } finally {
        }
        return isResult;
    }
    
    
    
    
    /**
     * 查找模版文件并调用转换方法 进行模版转换转移至企业材料库
     * 
     * */
    public boolean templetFileToCom(Map mapFtl, String ywlzUid,String lzhfuid,String CLK_UID){
    	boolean isResult=false;
    	try{
    		
    		List<?> temList=buSpLzhfDao.getTempletFile(ywlzUid,CLK_UID);
    		
    		if(temList!=null&&temList.size()>0){
    			for(int i=0;i<temList.size();i++){
    				Map tem=(Map)temList.get(i);
    				String ftlId=(String)tem.get("FILEID");
    				String ftlName=(String)tem.get("FILENAME");
    				String fileUrl=(String)tem.get("FILEURL");
    				String clkuid=(String)tem.get("CLKUID");
    				String clmc=(String)tem.get("CLMC");
    				String cllevel=(String)tem.get("CLLEVEL");
    				String comUid=(String)tem.get("COMID");
    				String proid =(String)tem.get("PROID");
    				
        			if(StringUtil.isNotBlankStr(cllevel)&&cllevel.equals("QY")){//企业       				
        				JSONObject objecttemp= new JSONObject();
            			objecttemp.put("NODE_TYPE", "QY");
            			objecttemp.put("JS_COMPANY_UID", comUid);
            			objecttemp.put("CLK_UID", clkuid);
    
//---------------------------------------------是否需要加判断 重名之类的判断

            			List<?> comclkList=jsComClkService.getAllCompanyClkList(objecttemp);
            			Map map=(Map)comclkList.get(0);
            			
            			String qyclkid=(String)map.get("ID");

        				JsComClkVO clkvo= new JsComClkVO();
            			clkvo.setP_com_cjk_uid(qyclkid);
            			clkvo.setJs_company_uid(comUid);
            			clkvo.setNode_name(clmc+"(核发)");
            			clkvo.setNode_type("CL");
            			clkvo.setClk_uid(clkuid);
            			clkvo.setEnabled("1");        				
            			JsComClkVO comClkVo=jsComClkService.saveJsComClkVO(clkvo);
        				
        				//文件移到企业材料上传的路径上，并保存记录进入fs_fileupload表
            			copyFileToCom(mapFtl,fileUrl,ftlName,ftlId,comClkVo, lzhfuid);
        				
        	    	} else if(StringUtil.isNotBlankStr(cllevel)&&cllevel.equals("LX")){//立项

        				JSONObject objecttemp= new JSONObject();
            			objecttemp.put("NODE_TYPE", "LX");
            			objecttemp.put("JS_COMPANY_UID", comUid);
            			objecttemp.put("CLK_UID", clkuid);
            			List<?> comclkList=jsComClkService.getAllCompanyClkList(objecttemp);
            			
            			Map map=(Map)comclkList.get(0);           			
            			String qyclkid=(String)map.get("ID");

        				JsComClkVO clkvo= new JsComClkVO();
            			clkvo.setP_com_cjk_uid(qyclkid);
            			clkvo.setJs_company_uid(comUid);
            			clkvo.setNode_name(clmc+"(核发)");
            			clkvo.setNode_type("CL");
            			clkvo.setClk_uid(clkuid);
            			clkvo.setEnabled("1");        				
            			JsComClkVO comClkVo=jsComClkService.saveJsComClkVO(clkvo);
        				
        				//文件移到企业材料上传的路径上，并保存记录进入fs_fileupload表
            			copyFileToCom(mapFtl,fileUrl,ftlName,ftlId,comClkVo, lzhfuid);
            			
    	
        	    	}else if(StringUtil.isNotBlankStr(cllevel)&&(cllevel.equals("XM")||cllevel.equals("YW"))){//项目        				
        				//先通过  PROJECTS_UID   CLK_UID  JS_COMPANY_UID  NODE_TYPE 为CL 的 查询是否存在节点 不存在则新建
        	    		
        	    		JSONObject objectcl= new JSONObject();
        	    		objectcl.put("NODE_TYPE", "CL");
        	    		objectcl.put("JS_COMPANY_UID", comUid);
        	    		objectcl.put("CLK_UID", clkuid);
        	    		objectcl.put("PROJECTS_UID", proid);
            		
            			
            			List<?> comclkclList=jsComClkService.getAllCompanyClkList(objectcl);
            			JsComClkVO comClkVo=null;
        	    		if(comclkclList!=null&&comclkclList.size()>0){
        	    			comClkVo=new JsComClkVO();
        	    			Map clmap=(Map)comclkclList.get(0);
        	    			
        	    			String qyclkid=(String)clmap.get("ID");
//        	    			String comid=(String)clmap.get("COMID");
//        	    			String clkuid=(String)clmap.get("CLKID");
        	    			comClkVo=jsComClkService.findById(qyclkid);
//        	    			comClkVo.setJs_com_cjk_uid(qyclkid);
//        	    			comClkVo.setClk_uid(clkuid);
//        	    			comClkVo.setJs_company_uid(comUid);
       	   
        	    		}else{
        	    			JSONObject objecttemp= new JSONObject();
                			objecttemp.put("NODE_TYPE", "XM");
                			objecttemp.put("JS_COMPANY_UID", comUid);
//                			objecttemp.put("CLK_UID", clkuid);
                			objecttemp.put("PROJECTS_UID", proid);
                		
                			
                			List<?> comclkList=jsComClkService.getAllCompanyClkList(objecttemp);
                			Map map=(Map)comclkList.get(0);           			
                			String qyclkid=(String)map.get("ID");

            				JsComClkVO clkvo= new JsComClkVO();
                			clkvo.setP_com_cjk_uid(qyclkid);
                			clkvo.setJs_company_uid(comUid);
                			clkvo.setNode_name(clmc+"(核发)");
                			clkvo.setNode_type("CL");
                			clkvo.setProjects_uid(proid);
                			clkvo.setClk_uid(clkuid);
                			clkvo.setEnabled("1");        				
                			comClkVo=jsComClkService.saveJsComClkVO(clkvo);
        	    		}
        				
        				//文件移到企业材料上传的路径上，并保存记录进入fs_fileupload表
            			copyFileToCom(mapFtl,fileUrl,ftlName,ftlId,comClkVo, lzhfuid);
        	    	}
    			}
    		}

    	}catch(Exception e){
    		 logger.error("模版文件数据转移失败{}", e);
             SystemException.handleMessageException("审批业务流转核发的模版文件数据转移失败,请联系相关人员处理");
    	}
    	return isResult;
    }
    
    
    /**
     * 通过加载的数据进行ftl模版转换成PDF
     * mapFtl      加载的数据
     * fileUrl  模版存放路径
     * ftlName  模版名称
     * */
    public String ftlToPdf(Map mapFtl,String fileUrl,String ftlName,String ftlId,JsComClkVO comClkVo){
    	String pdfPath=null;
    	try{
//	    	SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT, "FILEUPLOADOLD_ROOT"));
//	        String fileRoot = syspara.PARAVALUE1;
	        
	        SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
	        String fileRoot = syspara.getSysParaConfigureParavalue1();
	          
	    	String templatePath = fileRoot+File.separator+fileUrl.substring(0, fileUrl.indexOf(ftlName));
	    	
	    	ftlName=ftlName.substring(0, ftlName.indexOf(".ftl"));//去掉后缀
	    	/**    	if(FreemarkerHelper.createWord(mapFtl, templatePath, ftlName, templatePath, ftlName+"_"+comClkVo.getJs_com_cjk_uid())){
	    		pdfPath=templatePath+File.separator+ftlName+"_"+comClkVo.getJs_com_cjk_uid()+"";
//	    		Word2PDF.toPdf(pdfPath);
	    		pdfPath=pdfPath+".xml";//拼接出转换的PDF文件存放路径
	    	}
**/
	    	if(FreemarkerHelper.createWord(mapFtl, templatePath, ftlName, templatePath, ftlName+"_"+comClkVo.getJs_com_cjk_uid())){
	    		pdfPath=templatePath+File.separator+ftlName+"_"+comClkVo.getJs_com_cjk_uid()+"";
	    		Word2PDF.toPdf(pdfPath);
	    		pdfPath=pdfPath+".xml.pdf";//拼接出转换的PDF文件存放路径
	    	}
	    	
    	}catch(Exception e){
   		 logger.error("模版文件转换PDF失败{}", e);
         SystemException.handleMessageException("审批业务流转核发的模版文件转换成PDF失败,请联系相关人员处理");
	}	
    	
    	return pdfPath;
    }
    
    
   
    /** 
     * 复制文件并保存记录到上传的记录表fs_fileupload
     * map   模版填充数据
     * ftlPath 模版存放路径
     * ftlName  模版名称
     * ftlId    模版ID
     * comVo   企业材料库vo
     * lzhfuid 流转核发UID
     * @return 
     */  
    //待修正：模版文件需要通过企业ID 拿到对应的数据，填充到模版文件中，并生成对应的属于企业的数据模版文件    并转换成PDF?????
    public void copyFileToCom(Map mapFtl,String fileUrl,String ftlName,String ftlId,JsComClkVO comClkVo,String lzhfuid) {  
	
        try {  
        	//模版转填充数据并转换成PDF 返回PDF的存放路径。
        	String oldPathFile =ftlToPdf(mapFtl,fileUrl,ftlName, ftlId,comClkVo);
//            int bytesum = 0;  
//            int byteread = 0;  
            //获取文件上传保存的根路径
//            SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT, "FILEUPLOADOLD_ROOT"));
//            String fileRoot = syspara.PARAVALUE1;
            SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
	        String fileRoot = syspara.getSysParaConfigureParavalue1();
            
//          oldPathFile=fileRoot+"/"+oldPathFile;
            File oldfile = new File(oldPathFile);  
 
            if (oldfile.exists()) { //文件存在时  
//              insert into fs_fileupload (fileid,filename,url,zhux,lrr,lrbm,lrsj,ywlx,fjlx,ywid,fjzt,fjlb )
                User user = ActionContext.getCurrentUserInThread();
                FileUploadVO vo = new FileUploadVO();
                String fileid=new RandomGUID().toString();
                vo.setFileid(fileid);
                vo.setFilename(oldfile.getName());
                
                String url=lzhfuid+File.separator+fileid+File.separator+oldfile.getName();
                String targetfile=fileRoot+File.separator+url;
//                String newPathFile=fileRoot+File.separator+comClkVo.getJs_com_cjk_uid()+File.separator+fileid;
                
                vo.setUrl(url);
                vo.setLrr(user.getAccount());
                vo.setLrbm(user.getDepartment());
//                vo.setYwlx("500001");
                vo.setYwid(lzhfuid);
                vo.setGlid1(comClkVo.getJs_com_cjk_uid());
                vo.setGlid2(comClkVo.getClk_uid());
                vo.setGlid4("js");
                vo.setFjzt("1");
                vo.setFjlb(comClkVo.getClk_uid());
//                vo.setFjlb(Constants.FS_FILEUPLOAD_FJLB_HFCL);
                
                if(buSpLzhfDao.insertFileUpload(vo)){  
                	//新的文件夹是否存在，不存在则创建
                	File moveDir = new File(targetfile);
//                	if (!moveDir.exists()) {  
//                        moveDir.mkdirs();  
//                    } 
                	FileUtils.copyFile(oldfile, moveDir);
                /**	
	                InputStream inStream = new FileInputStream(oldPathFile); //读入原文件  
	                FileOutputStream fs = new FileOutputStream(targetfile);  
	                byte[] buffer = new byte[1444];  
	                while ((byteread = inStream.read(buffer)) != -1) {  
	                    bytesum += byteread; //字节数 文件大小  
	                    //System.out.println(bytesum);  
	                    fs.write(buffer, 0, byteread);  
	                }  
	                inStream.close();  **/
                }
            } 

        } catch (Exception e) {  
        	 logger.error("模版文件转移指企业材料失败{}", e);
             SystemException.handleMessageException("审批业务流转核发的模版文件转移企业材料失败,请联系相关人员处理");
        }  
    }  


    
    
    public String update(String json) throws Exception {

//        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          
            // 修改
            buSpLzhfDao.update(vo);
            resultVO = vo.getRowJson();

           
        } catch (DaoException e) {
            logger.error("审批业务流转核发文件修改{}", e);
            SystemException.handleMessageException("审批业务流转核发文件修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuSpLzhfVO vo = new BuSpLzhfVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			
			//删除   根据据主键
			buSpLzhfDao.delete(BuSpLzhfVO.class, vo.getLzhf_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("审批业务流转核发文件删除{}", e.getMessage());
            SystemException.handleMessageException("审批业务流转核发文件删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buSpLzhfDaoImpl")
	public void setBuSpLzhfDao(BuSpLzhfDao buSpLzhfDao) {
		this.buSpLzhfDao = buSpLzhfDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)buSpLzhfDao);
		
	}

	public String insertBuSpLzhfVO(String json, Map temmap) throws Exception {
//		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            vo.setValueFromJson(object);
           
            // 插入
			buSpLzhfDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("审批业务流转核发文件{}", e);
             SystemException.handleMessageException("审批业务流转核发文件新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}
    
}
