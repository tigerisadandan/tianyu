/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.AzPersonService.java
 * 创建日期： 2014-12-15 上午 11:01:01
 * 功能：    接口：安装人员库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 上午 11:01:01  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service.impl;


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
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.azqy.vo.AzPersonVO;
import com.ccthanking.business.dtgl.jxsb.dao.AzPersonDao;
import com.ccthanking.business.dtgl.jxsb.service.AzPersonService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> AzPersonService.java </p>
 * <p> 功能：安装人员库 </p>
 *
 * <p><a href="AzPersonService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

@Service
public class AzPersonServiceImpl extends Base1ServiceImpl<AzPersonVO, String> implements AzPersonService {

	private static Logger logger = LoggerFactory.getLogger(AzPersonServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.AZ_PERSON;
    
    private AzPersonDao azPersonDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = azPersonDao.queryCondition(json, null, null);

          
        }catch (DaoException e) {
        	logger.error("安装人员库{}", e.getMessage());
			SystemException.handleMessageException("安装人员库查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        AzPersonVO vo = new AzPersonVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
          
            // 插入
			azPersonDao.save(vo);
            resultVO = vo.getRowJson();

        
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("安装人员库{}", e.getMessage());
            SystemException.handleMessageException("安装人员库新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null; 
        AzPersonVO vo = new AzPersonVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

         
            // 修改
            azPersonDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("安装人员库{}", e.getMessage());
            SystemException.handleMessageException("安装人员库修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		AzPersonVO vo = new AzPersonVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

				//删除   根据据主键
			azPersonDao.delete(AzPersonVO.class, vo.getAz_person_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "安装人员库删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("安装人员库{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "安装人员库删除失败", user, "", "");
            SystemException.handleMessageException("安装人员库删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("azPersonDaoImpl")
	public void setAzPersonDao(AzPersonDao azPersonDao) {
		this.azPersonDao = azPersonDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) azPersonDao);
	}
    
}
