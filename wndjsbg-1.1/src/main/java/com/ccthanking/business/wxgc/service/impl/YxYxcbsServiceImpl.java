/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxYxcbsService.java
 * 创建日期： 2014-12-23 下午 01:31:30
 * 功能：    接口：预选承包商
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:31:30  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.wxgc.dao.YxYxcbsDao;
import com.ccthanking.business.wxgc.service.YxCbsGcTypeService;
import com.ccthanking.business.wxgc.service.YxYxcbsService;
import com.ccthanking.business.wxgc.vo.YxCbsGcTypeVO;
import com.ccthanking.business.wxgc.vo.YxYxcbsShjlVO;
import com.ccthanking.business.wxgc.vo.YxYxcbsVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxYxcbsService.java </p>
 * <p> 功能：预选承包商 </p>
 *
 * <p><a href="YxYxcbsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Service
public class YxYxcbsServiceImpl extends Base1ServiceImpl<YxYxcbsVO, String> implements YxYxcbsService {

	private static Logger logger = LoggerFactory.getLogger(YxYxcbsServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.YX_YXCBS;
    
    private YxYxcbsDao yxYxcbsDao;
    @Autowired
    private YxCbsGcTypeService yxCbsGcTypeService;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxYxcbsDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("预选承包商{}", e.getMessage());
			SystemException.handleMessageException("预选承包商查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String queryspyjCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxYxcbsDao.queryspjlCondition(json, null, null);
            
        }catch (DaoException e) {
        	logger.error("预选承包商信息库{}", e.getMessage());
			SystemException.handleMessageException("预选承包商信息库审批记录查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    
    // @Override
    public String querytjCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxYxcbsDao.queryCondition(json);
            
        }catch (DaoException e) {
        	logger.error("预选承包商信息库{}", e.getMessage());
			SystemException.handleMessageException("预选承包商信息库审批记录查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxYxcbsVO vo = new YxYxcbsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);


            // 插入
			yxYxcbsDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("预选承包商{}", e.getMessage());
            SystemException.handleMessageException("预选承包商新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxYxcbsVO vo = new YxYxcbsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
            String YXCBS_UID=(String)obj.get("YXCBS_UID");
            String SHJG=(String)obj.get("SHJG");
            String SHYJ=(String)obj.get("SHYJ");

            vo=this.findById(YXCBS_UID);
            if(SHJG!=null&&!"".equals(SHJG)){
            	YxYxcbsShjlVO shjlvo=new YxYxcbsShjlVO();
            	shjlvo.setYxcbs_uid(YXCBS_UID);
            	shjlvo.setShr(user.getAccount());
            	shjlvo.setShrq(new Date());
            	shjlvo.setShyj(SHYJ);
            	
            	if("1".equals(SHJG)){
            		shjlvo.setShjg("10");
        			vo.setZt("10");   
        			vo.setGsksrq(new Date());
        	        vo.setGsjzrq(DateUtils.addDays(new Date(), 5));//公示5天
            	}else{
            		shjlvo.setShjg("20");
            		vo.setZt("20");
            	}
            	yxYxcbsDao.save(shjlvo);
            	
            	Map map= new HashMap();
            	map.put("YXCBS_UID", YXCBS_UID);
            	
            	List<Map<String,String>> templist=this.yxYxcbsDao.cbsgctypeList(map);
                
                if(templist!=null&&templist.size()>0){
                	for(int i=0;i<templist.size();i++){
                		Map maptemp=templist.get(i);
                		YxCbsGcTypeVO typevo =yxCbsGcTypeService.findById((String)maptemp.get("YX_CBS_GC_TYPE_UID") );
                		if("1".equals(SHJG)){
                			typevo.setGsrq(new Date());
                			typevo.setGsjzrq(DateUtils.addDays(new Date(), 5));//公示两天
                			typevo.setZt("10");
                    	}else{
                    		typevo.setZt("10");
                    	}
                		
                		typevo.setUpdate_date(new Date());
                		typevo.setUpdate_name(user.getAccount());
                		typevo.setUpdate_uid(user.getUserSN());
                		yxYxcbsDao.update(typevo);
                	}
                }
            }
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getAccount());
            vo.setUpdate_uid(user.getUserSN());
            // 修改
            yxYxcbsDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("预选承包商{}", e.getMessage());
            SystemException.handleMessageException("预选承包商修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxYxcbsVO vo = new YxYxcbsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxYxcbsDao.delete(YxYxcbsVO.class, vo.getYxcbs_uid());
			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("预选承包商{}", e.getMessage());
            SystemException.handleMessageException("预选承包商删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;
	}

	@Autowired
	@Qualifier("yxYxcbsDaoImpl")
	public void setYxYxcbsDao(YxYxcbsDao yxYxcbsDao) {
		this.yxYxcbsDao = yxYxcbsDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxYxcbsDao);
	}
	
	
	public String querycbsgctype(Map map) throws Exception {
		JSONArray array = new JSONArray();
        
        List<Map<String,String>> templist=this.yxYxcbsDao.cbsgctypeList(map);
       
        if(templist!=null&&templist.size()>0){
        	for(int i=0;i<templist.size();i++){
        		Map maptemp=templist.get(i);
            	JSONObject obj = new JSONObject();
            	obj.put("YX_CBS_GC_TYPE_UID",maptemp.get("YX_CBS_GC_TYPE_UID") );
            	obj.put("YXCBS_UID",maptemp.get("YXCBS_UID") );
            	obj.put("ZT", maptemp.get("ZT"));
            	obj.put("GSRQ", maptemp.get("GSRQ"));
            	obj.put("GSJZRQ", maptemp.get("GSJZRQ"));
            	obj.put("GC_TYPE_CODE", maptemp.get("GC_TYPE_CODE"));
            	array.add(obj);
        	}
        }
        
		return array.toString();
	}

	public String updateXzYxcbs(String json) throws Exception {
		 User user = ActionContext.getCurrentUserInThread();
	        
	        String resultVO = null;
	        YxYxcbsVO vo1 = new YxYxcbsVO();

	        try {
	            JSONArray list = vo1.doInitJson(json);
	            JSONObject obj=(JSONObject) list.get(0);
	            String YXCBS_UID=(String)obj.get("YXCBS_UID");
	            String SFYZXZ=(String)obj.get("SFYZXZ");//是否永久限制
	            String XZYY=(String)obj.get("XZYY");//
	            String XZDQRQ=(String)obj.get("XZDQRQ");//

	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//  
	            java.util.Date dateXZDQRQ=sdf.parse(XZDQRQ); 
	            
	            YxYxcbsVO vo=this.findById(YXCBS_UID);
	            
	            vo.setSfyzxz(SFYZXZ);
	            vo.setXzyy(XZYY);
	            if(SFYZXZ!=null&&"0".equals(SFYZXZ)){
	            	vo.setXzdqrq(dateXZDQRQ);
	            }else{
	            	vo.setXzdqrq(null);
	            }
	            
	            vo.setXzczr(user.getAccount());
	            vo.setXzczrq(new Date());
	            vo.setEnabled("0");
	            vo.setUpdate_date(new Date());
	            vo.setUpdate_name(user.getAccount());
	            vo.setUpdate_uid(user.getUserSN());
	            // 修改
	            this.yxYxcbsDao.update(vo);
	            
	            resultVO = vo.getRowJson();

	        } catch (DaoException e) {
	            logger.error("预选承包商{}", e.getMessage());
	            SystemException.handleMessageException("预选承包商限制操作失败,请联系相关人员处理");
	        } finally {
	        }
	        return resultVO;
	}
}
