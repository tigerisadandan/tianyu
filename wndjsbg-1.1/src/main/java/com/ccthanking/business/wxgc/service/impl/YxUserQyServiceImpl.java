/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxUserQyService.java
 * 创建日期： 2015-01-07 上午 09:22:01
 * 功能：    接口：招标人(实施方区域)维护
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-07 上午 09:22:01  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


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

import com.ccthanking.business.wxgc.vo.YxUserQyVO;
import com.ccthanking.business.wxgc.dao.YxUserQyDao;
import com.ccthanking.business.wxgc.service.YxUserQyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxUserQyService.java </p>
 * <p> 功能：招标人(实施方区域)维护 </p>
 *
 * <p><a href="YxUserQyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-07
 * 
 */

@Service
public class YxUserQyServiceImpl extends Base1ServiceImpl<YxUserQyVO, String> implements YxUserQyService {

	private static Logger logger = LoggerFactory.getLogger(YxUserQyServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.YX_USER_QY;
    
    private YxUserQyDao yxUserQyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxUserQyDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("招标人(实施方区域)维护{}", e.getMessage());
			SystemException.handleMessageException("招标人(实施方区域)维护查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxUserQyVO vo = new YxUserQyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            // 插入
			yxUserQyDao.save(vo);
            resultVO = vo.getRowJson();

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("招标人(实施方区域)维护{}", e.getMessage());
            SystemException.handleMessageException("招标人(实施方区域)维护新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxUserQyVO vo = new YxUserQyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            yxUserQyDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("招标人(实施方区域)维护{}", e.getMessage());
            SystemException.handleMessageException("招标人(实施方区域)维护修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxUserQyVO vo = new YxUserQyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxUserQyDao.delete(YxUserQyVO.class, vo.getUser_qy_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("招标人(实施方区域)维护{}", e.getMessage());
            SystemException.handleMessageException("招标人(实施方区域)维护删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxUserQyDaoImpl")
	public void setYxUserQyDao(YxUserQyDao yxUserQyDao) {
		this.yxUserQyDao = yxUserQyDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxUserQyDao);
	}
    
}
