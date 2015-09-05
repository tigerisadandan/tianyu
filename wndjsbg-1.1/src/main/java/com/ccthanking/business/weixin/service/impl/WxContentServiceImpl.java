package com.ccthanking.business.weixin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.weixin.dao.WxContentDao;
import com.ccthanking.business.weixin.service.WxContentService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.weixin.vo.WxContentVO;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

@Service
public class WxContentServiceImpl   extends Base1ServiceImpl<WxContentVO, String> implements WxContentService{
	private static Logger logger = LoggerFactory.getLogger(WxContentServiceImpl.class);
	private WxContentDao wxContentDao;

	@Autowired
	@Qualifier("wxContentDaoImpl")
	public void setWxContentDao(WxContentDao wxContentDao) {
		this.wxContentDao = wxContentDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) wxContentDao);
	}

	public String delete(String id) throws Exception {
		String resultVo = null;
//		WxContentVO vo = new WxContentVO();
		try {
		
			if(id!=null&&!"".equals(id)){
				//删除   根据据主键
				wxContentDao.delete(WxContentVO.class, id);
				resultVo="删除成功！";
			}else{
				resultVo="没有选中，删除失败！";
			}
		} catch (DaoException e) {
            logger.error("内容删除{}", e.getMessage());
            SystemException.handleMessageException("内容删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;
	}

	public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVO = null;
		WxContentVO vo = new WxContentVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String ywid=(String)obj.get("ywid");//上传附件的临时使用的ywid
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            // 插入
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());
            wxContentDao.save(vo);
            resultVO = vo.getRowJson();
            
            
            //更新附件ywid和状态
            if(vo!=null&&vo.getContent_uid()!=null&&!"".equals(vo.getContent_uid())){
            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getContent_uid(),null);
            }
        } catch (DaoException e) {
            logger.error("内容新增{}", e.getMessage());
            SystemException.handleMessageException("内容新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}

	
	public String queryCondition(String json) throws Exception {
		 String domresult = "";
	        try {	        	
	        	//List<Article> temp=listMessage(null, "32",10);	        	
	        	
				domresult = wxContentDao.queryCondition(json, null);   
				
	        }catch (DaoException e) {
	        	logger.error("内容查询{}", e.getMessage());
				SystemException.handleMessageException("内容查询失败,请联系相关人员处理");
	        } finally {
	        }
		return domresult;
	}

	public String update(String json) throws Exception {
		 User user = ActionContext.getCurrentUserInThread();
	        
	        String resultVO = null;
	        WxContentVO vo = new WxContentVO();

	        try {
	            JSONArray list = vo.doInitJson(json);
	            JSONObject obj=(JSONObject) list.get(0);
	            vo.setValueFromJson(obj);
	            String ywid=(String)obj.get("ywid");//企业注册上传附件的临时使用的ywid
	            vo.setUpdate_uid(user.getAccount());
	            vo.setUpdate_name(user.getName());
	            vo.setUpdate_date(new Date());
	            // 设置主键
//	            vo.setId(new RandomGUID().toString()); // 主键
	            wxContentDao.update(vo);
	            resultVO = vo.getRowJson();
	            
	            //更新附件ywid和状态
	            if(vo!=null&&vo.getContent_uid()!=null&&!"".equals(vo.getContent_uid())){
	            	 FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getContent_uid(),null);
	            }
	            
	        } catch (DaoException e) {
	            logger.error("内容修改{}", e.getMessage());
	            SystemException.handleMessageException("内容修改失败,请联系相关人员处理");
	        } finally {
	        }
	        return resultVO;
	}
	

	public String getWxContentVO(String json, String contentid)
			throws Exception {
		WxContentVO temp=null;
		if(contentid!=null&&contentid!=""){
			 temp=this.findById(contentid);
		}	
		return temp.getRowJson();
	}
	
	//通过channelid查询，组织成arr数组字符串
	public String listContentS(WxContentVO vo, String channelid,int rownum){				
		JSONArray jsonArr = new JSONArray();		
		List<Map<String, String>> templist= wxContentDao.listContent(vo, channelid, rownum);
	 	 
		 if(templist!=null&&templist.size()>0){
			 for(int i=0;i<templist.size();i++){
				 Map<String, String> maptemp=templist.get(i);
				 JSONObject o = new JSONObject();
				 o.put("CONTENT_UID", maptemp.get("CONTENT_UID"));
				 String title=maptemp.get("CONTENT_TITLE");
				 String titles=null;
				 if(title.length()>10){
					 titles=title.substring(0, 10);
					 titles+="...";
				 }else{
					 titles=title;
				 }
				 o.put("CONTENT_TITLES", titles);
				 o.put("CONTENT_TITLE", maptemp.get("CONTENT_TITLE"));
				 o.put("CONTENT_STXT", maptemp.get("CONTENT_STXT"));
				 o.put("CONTENT_PIC", maptemp.get("CONTENT_PIC"));
				 o.put("CHANNEL_UID", maptemp.get("CHANNEL_UID"));
				 
				 String datestring=maptemp.get("PUBLISH_TIME");
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
				 String sdate = null;
				 try {
					Date date = sdf.parse(datestring);
					sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(date);
				 } catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				 o.put("PUBLISH_TIME", sdate);
				 o.put("CHANNELNAME",  maptemp.get("CHANNELNAME"));
				 jsonArr.add(o);
			 }				 
		 }
		return jsonArr.toString();
	}


	
	//通过eventKey 查询，返回arr数组字符串
	public String listContent(WxContentVO vo, String eventKey, int rownum) throws Exception {
		JSONArray jsonArr = new JSONArray(); 
		try {
			 List<Map<String, String>> templist= wxContentDao.listCondition(vo,eventKey, rownum);
			 if(templist!=null&&templist.size()>0){
				 for(int i=0;i<templist.size();i++){
					 Map<String, String> maptemp=templist.get(i);
					 JSONObject o = new JSONObject();
					 o.put("CONTENT_UID", maptemp.get("CONTENT_UID"));
					 o.put("CONTENT_TITLE", maptemp.get("CONTENT_TITLE"));
					 o.put("CONTENT_STXT", maptemp.get("CONTENT_STXT"));
					 o.put("CONTENT_PIC", maptemp.get("CONTENT_PIC"));
					 o.put("CHANNEL_UID", maptemp.get("CHANNEL_UID"));
					 o.put("PUBLISH_TIME", maptemp.get("PUBLISH_TIME"));
					 jsonArr.add(o);
				 }				 
			 }
	
	      } catch (DaoException e) {
	        logger.error("消息记录查询{}", e.getMessage());
	        SystemException.handleMessageException("消息记录查询失败,请联系相关人员处理");
	      } catch (Exception e) {
		        logger.error("消息记录查询{}", e.getMessage());
		        SystemException.handleMessageException("消息记录查询失败,请联系相关人员处理");
		  }finally {
		  	}
		  return jsonArr.toString();
	}


}
