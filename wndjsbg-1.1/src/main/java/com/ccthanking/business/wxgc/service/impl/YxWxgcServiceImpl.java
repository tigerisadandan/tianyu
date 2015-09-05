/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxWxgcService.java
 * 创建日期： 2014-12-23 下午 01:29:58
 * 功能：    接口：微型工程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:29:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import java.util.Date;
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

import com.ccthanking.business.wxgc.dao.YxWxgcDao;
import com.ccthanking.business.wxgc.service.YxWxgcService;
import com.ccthanking.business.wxgc.vo.YxWxgcSpjlVO;
import com.ccthanking.business.wxgc.vo.YxWxgcVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxWxgcService.java </p>
 * <p> 功能：微型工程 </p>
 *
 * <p><a href="YxWxgcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Service
public class YxWxgcServiceImpl extends Base1ServiceImpl<YxWxgcVO, String> implements YxWxgcService {

	private static Logger logger = LoggerFactory.getLogger(YxWxgcServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.YX_WXGC;
    
    private YxWxgcDao yxWxgcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxWxgcDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("微型工程{}", e.getMessage());
			SystemException.handleMessageException("微型工程查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String queryspyjCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxWxgcDao.queryspjlCondition(json, null, null);
            
        }catch (DaoException e) {
        	logger.error("微型工程{}", e.getMessage());
			SystemException.handleMessageException("微型工程审批记录查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxWxgcVO vo = new YxWxgcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
//            BusinessUtil.setInsertCommonFields(vo, user);
//            vo.setYwlx(ywlx);

            // 插入
			yxWxgcDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("微型工程{}", e.getMessage());
            SystemException.handleMessageException("微型工程新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxWxgcVO vo = new YxWxgcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj= (JSONObject) list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

//          	BusinessUtil.setUpdateCommonFields(vo, user);
//            vo.setYwlx(ywlx);
            String WXGCSHJG=(String)obj.get("WXGCSHJG");
            String WXGCSHYJ=(String)obj.get("WXGCSHYJ");
            String zt = vo.getZt();
            //保存审核结果及意见
            if(WXGCSHJG!=null&&!"".equals(WXGCSHJG)){
            	
            	YxWxgcSpjlVO spjlvo=new YxWxgcSpjlVO();
            	if("1".equals(WXGCSHJG)){
            		
//            		vo.setGc_code(yxWxgcDao.getCode(2));
        			vo.setZt("10");   
        			spjlvo.setSpjg("10");
//        			vo.setGgksrq(new Date());
//        	        vo.setGgjzrq(DateUtils.addDays(new Date(), 2));//公示两天
        			spjlvo.setStep_code(zt);
            	}else{
            		vo.setZt("20");
            		spjlvo.setSpjg("20");
            	}
            	
            	spjlvo.setSprq(new Date());
            	spjlvo.setUser_uid(user.getUserSN());
            	spjlvo.setSpyj(WXGCSHYJ);
            	spjlvo.setWxgc_uid(vo.getWxgc_uid());
            	spjlvo.setCreated_date(new Date());
            	spjlvo.setCreated_name(user.getName());
            	spjlvo.setCreated_uid(user.getAccount());
            	
            	yxWxgcDao.save(spjlvo);
            	
            }
            
            vo.setJsj_qrr(user.getAccount());
            vo.setJsj_qrrq(new Date());
            
          
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(user.getAccount());
            // 修改
            yxWxgcDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("微型工程{}", e.getMessage());
            SystemException.handleMessageException("微型工程修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxWxgcVO vo = new YxWxgcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxWxgcDao.delete(YxWxgcVO.class, vo.getWxgc_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("微型工程{}", e.getMessage());
            SystemException.handleMessageException("微型工程删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxWxgcDaoImpl")
	public void setYxWxgcDao(YxWxgcDao yxWxgcDao) {
		this.yxWxgcDao = yxWxgcDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxWxgcDao);
	}
    
	

    //验证中标价是否符合规定
	public String validZbj(Map map) throws Exception {
		JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		String istrue="true";
		String mess="";
			List<Map<String,String>> gcTypeList=yxWxgcDao.gcTypeList(map);
			String ZBJ=(String)map.get("ZBJ");
			if(ZBJ!=null&&!"".equals(ZBJ)){
				if(gcTypeList!=null&&gcTypeList.size()>0){
					for(Map<String,String> temmap:gcTypeList){
						String GC_MBJGXE=temmap.get("GC_MBJGXE");
						String GC_TYPE_NAME=temmap.get("GC_TYPE_NAME");
						Integer xejg=Integer.valueOf(GC_MBJGXE);
						Integer zbj=Integer.valueOf(ZBJ);
						if(xejg>zbj){
							istrue="true";
							mess="明标价输入合理，通过！";
						}else{
							istrue="false";
							mess="明标价输入不合理，"+GC_TYPE_NAME+"的明标价格最高不能超过"+GC_MBJGXE+"元";
						}
					}
				}
			}else{
				istrue="true";
				mess="明标价为空，不需要验证！";
			}
			
		jsonObj.put("ISTRUE", istrue);
		jsonObj.put("MESS", mess);
		array.add(jsonObj);
		return array.toString();
	}
}
