/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yuxuan.service.YxClEnterpriseService.java
 * 创建日期： 2014-12-23 下午 01:25:43
 * 功能：    接口：材料设备企业信息库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-23 下午 01:25:43  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.wxgc.dao.YxClEnterpriseDao;
import com.ccthanking.business.wxgc.service.YxClEnterpriseService;
import com.ccthanking.business.wxgc.vo.YxClEnterpriseShjlVO;
import com.ccthanking.business.wxgc.vo.YxClEnterpriseVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxClEnterpriseService.java </p>
 * <p> 功能：材料设备企业信息库 </p>
 *
 * <p><a href="YxClEnterpriseService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-23
 * 
 */

@Service
public class YxClEnterpriseServiceImpl extends Base1ServiceImpl<YxClEnterpriseVO, String> implements YxClEnterpriseService {

	private static Logger logger = LoggerFactory.getLogger(YxClEnterpriseServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.YX_KC_ENTERPRISE;
    
    private YxClEnterpriseDao yxClEnterpriseDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxClEnterpriseDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("材料设备企业信息库{}", e.getMessage());
			SystemException.handleMessageException("材料设备企业信息库查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
    
    
    // @Override
    public String queryOldCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxClEnterpriseDao.queryOldCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("材料设备企业信息库{}", e.getMessage());
			SystemException.handleMessageException("材料设备企业信息库查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
    
    
    // @Override
    public String queryspyjCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = yxClEnterpriseDao.queryspjlCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("材料设备企业信息库{}", e.getMessage());
			SystemException.handleMessageException("材料设备企业信息库审批记录查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        YxClEnterpriseVO vo = new YxClEnterpriseVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			yxClEnterpriseDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("材料设备企业信息库{}", e.getMessage());
            SystemException.handleMessageException("材料设备企业信息库新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxClEnterpriseVO vo = new YxClEnterpriseVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj=(JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String CL_ENTERPRISE_UID=(String)obj.get("CL_ENTERPRISE_UID");
//            vo=this.findById(CL_ENTERPRISE_UID);

            String SHJG=(String)obj.get("SHJG");
            String SHYJ=(String)obj.get("SHYJ");
            //保存审核结果及意见
            if(SHJG!=null&&!"".equals(SHJG)){
            	YxClEnterpriseShjlVO spjlvo=new YxClEnterpriseShjlVO();
            	if("1".equals(SHJG)){
        			vo.setStatus("10");//审核通过
        			if(vo.getDenglu_code()==null||"".equals(vo.getDenglu_code())){
        				vo.setDenglu_code(yxClEnterpriseDao.getDengluCode());
        			}
        			
        			vo.setStatus("1");
        			spjlvo.setShjg("10");
            	}else{
            		//审核不通过，还原HIS最新的那条数据
            		YxClEnterpriseVO votemp=this.findById(CL_ENTERPRISE_UID);
            		
            		JSONObject jsonobj=yxClEnterpriseDao.hisQyxxJSONObject(CL_ENTERPRISE_UID);
            		vo.setValueFromJson(jsonobj);
            		vo.setDenglu_code(votemp.getDenglu_code());
            		vo.setMima(votemp.getMima());
            		vo.setPwd(votemp.getPwd());
            		vo.setStatus("20");
            		spjlvo.setShjg("20");
            	}
            	spjlvo.setCl_enterprise_uid(CL_ENTERPRISE_UID);
            	spjlvo.setShrq(new Date());
            	spjlvo.setShyj(SHYJ);
            	spjlvo.setShr(user.getAccount());
            	spjlvo.setCreated_date(new Date());
            	spjlvo.setCreated_name(user.getName());
            	spjlvo.setCreated_uid(user.getAccount());
            	yxClEnterpriseDao.save(spjlvo);
            }
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getAccount());
            vo.setUpdate_uid(user.getUserSN());

            // 修改
            yxClEnterpriseDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("材料设备企业信息库{}", e.getMessage());
            SystemException.handleMessageException("材料设备企业信息库修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxClEnterpriseVO vo = new YxClEnterpriseVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxClEnterpriseDao.delete(YxClEnterpriseVO.class, vo.getCl_enterprise_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("材料设备企业信息库{}", e.getMessage());
            SystemException.handleMessageException("材料设备企业信息库删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;
	}

	@Autowired
	@Qualifier("yxClEnterpriseDaoImpl")
	public void setyxClEnterpriseDao(YxClEnterpriseDao yxClEnterpriseDao) {
		this.yxClEnterpriseDao = yxClEnterpriseDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxClEnterpriseDao);
	}
    
}
