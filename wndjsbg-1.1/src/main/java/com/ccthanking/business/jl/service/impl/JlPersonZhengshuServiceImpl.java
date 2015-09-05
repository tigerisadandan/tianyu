/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jl.service.JlPersonZhengshuService.java
 * 创建日期： 2015-01-26 下午 02:23:22
 * 功能：    接口：监理人员证书
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-26 下午 02:23:22  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jl.service.impl;


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
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;


import com.ccthanking.business.dtgl.jl.vo.JlPersonZhengshuVO;
import com.ccthanking.business.jl.dao.JlPersonZhengshuDao;
import com.ccthanking.business.jl.service.JlPersonZhengshuService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JlPersonZhengshuService.java </p>
 * <p> 功能：监理人员证书 </p>
 *
 * <p><a href="JlPersonZhengshuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-01-26
 * 
 */

@Service
public class JlPersonZhengshuServiceImpl extends Base1ServiceImpl<JlPersonZhengshuVO, String> implements JlPersonZhengshuService {

	private static Logger logger = LoggerFactory.getLogger(JlPersonZhengshuServiceImpl.class);
	
	// 业务类型
   // private String ywlx = YwlxManager.JL_PERSON_ZHENGSHU;
    
    private JlPersonZhengshuDao jlPersonZhengshuDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jlPersonZhengshuDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("监理人员证书{}", e.getMessage());
			SystemException.handleMessageException("监理人员证书查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JlPersonZhengshuVO vo = new JlPersonZhengshuVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);


            // 插入
			jlPersonZhengshuDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("监理人员证书{}", e.getMessage());
            SystemException.handleMessageException("监理人员证书新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JlPersonZhengshuVO vo = new JlPersonZhengshuVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
           

            // 修改
            jlPersonZhengshuDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("监理人员证书{}", e.getMessage());
            SystemException.handleMessageException("监理人员证书修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JlPersonZhengshuVO vo = new JlPersonZhengshuVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			//jlPersonZhengshuDao.delete(JlPersonZhengshuVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("监理人员证书{}", e.getMessage());
            SystemException.handleMessageException("监理人员证书删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    
	public String queryListPersonZhengshu(String json) throws Exception {

		String domresult=this.jlPersonZhengshuDao.queryListPersonZhengshu(json);
		return domresult;
	} 

	@Autowired
	@Qualifier("jlPersonZhengshuDaoImpl")
	public void setJlPersonZhengshuDao(JlPersonZhengshuDao jlPersonZhengshuDao) {
		this.jlPersonZhengshuDao = jlPersonZhengshuDao;
	}
    
}
