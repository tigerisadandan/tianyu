/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.AzPersonShenheService.java
 * 创建日期： 2014-12-15 上午 10:48:26
 * 功能：    接口：安装人员审核
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-15 上午 10:48:26  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service.impl;


import java.sql.Connection;
import java.util.Date;

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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.azqy.vo.AzPersonShenheVO;
import com.ccthanking.business.dtgl.azqy.vo.AzPersonVO;
import com.ccthanking.business.dtgl.jxsb.dao.AzPersonShenheDao;
import com.ccthanking.business.dtgl.jxsb.service.AzPersonService;
import com.ccthanking.business.dtgl.jxsb.service.AzPersonShenheService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> AzPersonShenheService.java </p>
 * <p> 功能：安装人员审核 </p>
 *
 * <p><a href="AzPersonShenheService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-15
 * 
 */

@Service
public class AzPersonShenheServiceImpl extends Base1ServiceImpl<AzPersonShenheVO, String> implements AzPersonShenheService {

	private static Logger logger = LoggerFactory.getLogger(AzPersonShenheServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.AZ_PERSON_SHENHE;
    
    private AzPersonShenheDao azPersonShenheDao;
    
    @Autowired
    private AzPersonService azPersonService;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = azPersonShenheDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("安装人员审核{}", e.getMessage());
			SystemException.handleMessageException("安装人员审核查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        AzPersonShenheVO vo = new AzPersonShenheVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
     
            // 插入
			azPersonShenheDao.save(vo);
            resultVO = vo.getRowJson();

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("安装人员审核{}", e.getMessage());
             SystemException.handleMessageException("安装人员审核新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AzPersonShenheVO vo = new AzPersonShenheVO();
        Connection conn = DBUtil.getConnection();
        try {
        	JSONArray list = vo.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
            vo.setValueFromJson(object);
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");
            String SHENHE_YIJIAN=object.getString("SHENHE_YIJIAN");

            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(user.getUserSN());
            vo.setShenhe_yijian(SHENHE_YIJIAN);
            vo.setShenhe_jieguo(SHENHE_JIEGUO);
            
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	vo.setStatus("10");
            	azPersonShenheDao.updateLiZhiByShFz(vo.getShenfenzheng());  //  同身份证人员 更新离职
             	   
                  
            }else{
            	vo.setStatus("20");
            }
            // 修改
            azPersonShenheDao.update(vo);
            resultVO = vo.getRowJson();
            if("10".equals(vo.getStatus())){
                AzPersonShenheVO shvo = findById(vo.getAz_person_shenhe_uid());
                shvo.setStatus("1"); //最终入库
               
            	azPersonService.insert( shvo.getRowJson());
            }
            

        } catch (DaoException e) {
            logger.error("安装人员审核{}", e.getMessage());
               SystemException.handleMessageException("安装人员审核修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		AzPersonShenheVO vo = new AzPersonShenheVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			azPersonShenheDao.delete(AzPersonShenheVO.class, vo.getAz_person_shenhe_uid());

			resultVo = vo.getRowJson();

	
		} catch (DaoException e) {
            logger.error("安装人员审核{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "安装人员审核删除失败", user, "", "");
            SystemException.handleMessageException("安装人员审核删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("azPersonShenheDaoImpl")
	public void setAzPersonShenheDao(AzPersonShenheDao azPersonShenheDao) {
		this.azPersonShenheDao = azPersonShenheDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) azPersonShenheDao);
	}
    
}
