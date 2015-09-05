/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.SgbbRyService.java
 * 创建日期： 2014-04-24 下午 07:04:31
 * 功能：    接口：施工报备人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-24 下午 07:04:31  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.commons.Utils;
import com.ccthanking.business.sgbb.dao.SgbbRyDao;
import com.ccthanking.business.sgbb.service.SgbbRyService;
import com.ccthanking.business.sgbb.vo.SgbbRyVO;
import com.ccthanking.business.sgbb.vo.SgbbVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SgbbRyService.java </p>
 * <p> 功能：施工报备人员 </p>
 *
 * <p><a href="SgbbRyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-24
 * 
 */

@Service
public class SgbbRyServiceImpl extends Base1ServiceImpl<SgbbRyVO, String> implements SgbbRyService {

	private static Logger logger = LoggerFactory.getLogger(SgbbRyServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.SGBB_RY;
    
    private SgbbRyDao sgbbRyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sgbbRyDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工报备人员>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("施工报备人员{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "施工报备人员查询失败", user, "", "");
			SystemException.handleMessageException("施工报备人员查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SgbbRyVO vo = new SgbbRyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);

            // 插入
			sgbbRyDao.save(vo);
            resultVO = vo.getRowJson();

            LogManager.writeUserLog(vo.getSgbb_ry_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS,  user.getName() + "施工报备人员新增成功", user, "", "");

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("施工报备人员{}", e.getMessage());
            LogManager.writeUserLog(vo.getSgbb_ry_uid(), ywlx, Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "施工报备人员新增失败", user, "", "");
            SystemException.handleMessageException("施工报备人员新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();

		String resultVO = null;
		SgbbVO vo = new SgbbVO();
		SgbbRyVO ryVO = new SgbbRyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject obj = (JSONObject) list.get(0);
			vo.setValueFromJson(obj);
			
			resultVO = vo.getSgbb_uid();
//			BusinessUtil.setInsertCommonFields(vo, user);
			
			
			JSONArray ryArray = null;
			try{
				ryArray = obj.getJSONArray("SG_PERSON_UID");
	        }catch(JSONException e){
	        	logger.warn("施工报备人员{}", e.getMessage());	
	        }
	        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	        String reason = format.format(new Date())+"在"+vo.getProject_name()+"("+vo.getProject_code()+")报备中加锁";
	        String emptyRy = "";
	        if(ryArray!=null){
	        	for (int index = 0; index < ryArray.size(); index++) {
	        		if(StringUtils.isBlank((String) ryArray.get(index))){
						continue;
					}
	        		ryVO = new SgbbRyVO();
	        		ryVO.setSgbb_uid(vo.getSgbb_uid());
	        		
	        		
	        		ryVO.setSerial_no(index+"");
	        		ryVO.setGangwei_uid(obj.getJSONArray("GANGWEI_UID").getString(index));
	        		ryVO.setMust_y(obj.getJSONArray("MUST_Y").getString(index));
	        		ryVO.setSg_person_uid(ryArray.getString(index));
	        		ryVO.setSg_name(obj.getJSONArray("SG_NAME").getString(index));
	        		ryVO.setZhengshu_name(obj.getJSONArray("ZHENGSHU_NAME").getString(index));
	        		ryVO.setZhuanye(obj.getJSONArray("ZHUANYE").getString(index));
	        		ryVO.setZhengshu_code(obj.getJSONArray("ZHENGSHU_CODE").getString(index));
	        		ryVO.setZhengshu_date(Utils.formatToDate(obj.getJSONArray("ZHENGSHU_DATE").getString(index)));
	        		ryVO.setAge(obj.getJSONArray("AGE").getString(index));
	        		ryVO.setZhicheng_name(obj.getJSONArray("ZHICHENG_NAME").getString(index));
	        		ryVO.setMobile(obj.getJSONArray("MOBILE").getString(index));
	        		ryVO.setShenfenzheng(obj.getJSONArray("SHENFENZHENG").getString(index));
	        		if(StringUtils.isNotBlank(obj.getJSONArray("SGBB_RY_UID").getString(index))){
	        			
	        			ryVO.setSgbb_ry_uid(obj.getJSONArray("SGBB_RY_UID").getString(index));
	        			ryVO.setUpdate_date(new Date());
		        		ryVO.setUpdate_name(user.getName());
		        		ryVO.setUpdate_uid(user.getUserSN());
	        			sgbbRyDao.update(ryVO);
	        			
	        			if (sgbbRyDao.queryPersonUid(obj.getJSONArray("SGBB_RY_UID").getString(index), obj.getJSONArray("GANGWEI_UID").getString(index), ryArray.getString(index))) {
	        				sgbbRyDao.insertSgbbStatus(null, ryVO, reason);
						}
	        			
	        		}else{
	        			ryVO.setStatus("1");
	        			ryVO.setXuhao(index+"");
	        			ryVO.setCreated_date(new Date());
		        		ryVO.setCreated_name(user.getName());
		        		ryVO.setCreated_uid(user.getUserSN());
	        			sgbbRyDao.save(ryVO);
	        			sgbbRyDao.insertSgbbStatus(null, ryVO, reason);
	        			
	        			
	        		}
	        		emptyRy += ryVO.getSgbb_ry_uid()+",";
				}
	        }
	        if(StringUtils.isNotBlank(emptyRy)){
	        	sgbbRyDao.deleteByUids(emptyRy.substring(0,emptyRy.length()-1),vo.getSgbb_uid());
	        }
			 LogManager.writeUserLog(ryVO.getSgbb_ry_uid(), ywlx,Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "施工报备新增成功",user, "", "");

			// String
			// jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
			// return queryCondition(jsona, user);

		} catch (DaoException e) {
			logger.error("施工报备{}", e.getMessage());
			 LogManager.writeUserLog(ryVO.getSgbb_ry_uid(), ywlx,
			 Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "施工报备新增失败",user, "", "");
			SystemException.handleMessageException("施工报备新增失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;
    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SgbbRyVO vo = new SgbbRyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			sgbbRyDao.delete(SgbbRyVO.class, vo.getSgbb_ry_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(vo.getSgbb_ry_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "施工报备人员删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("施工报备人员{}", e.getMessage());
            LogManager.writeUserLog(vo.getSgbb_ry_uid(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getName() + "施工报备人员删除失败", user, "", "");
            SystemException.handleMessageException("施工报备人员删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sgbbRyDaoImpl")
	public void setSgbbRyDao(SgbbRyDao sgbbRyDao) {
		this.sgbbRyDao = sgbbRyDao;
	}

	public String queryBbry(String bbUid) throws Exception {
		return sgbbRyDao.queryBbry(bbUid);
	}
	
	public String queryLockUser(String sg_person_uid,String gangwei_uid,String sgbb_uid) {
		return sgbbRyDao.queryLockUser(sg_person_uid, gangwei_uid, sgbb_uid);

	}

	public void syncUpdateRyxx(String bb_uid) throws Exception {
		sgbbRyDao.syncUpdateRyxx(bb_uid);
	}
	
    
}
