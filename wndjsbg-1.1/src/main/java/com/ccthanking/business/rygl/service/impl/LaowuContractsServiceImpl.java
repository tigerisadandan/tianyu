/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.LaowuContractsService.java
 * 创建日期： 2015-03-23 下午 05:19:19
 * 功能：    接口：劳务合同
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-23 下午 05:19:19  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rygl.service.impl;


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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.rygl.vo.LaowuContractsVO;
import com.ccthanking.business.rygl.dao.LaowuContractsDao;
import com.ccthanking.business.rygl.service.LaowuContractsService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> LaowuContractsService.java </p>
 * <p> 功能：劳务合同 </p>
 *
 * <p><a href="LaowuContractsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-23
 * 
 */

@Service
public class LaowuContractsServiceImpl extends Base1ServiceImpl<LaowuContractsVO, String> implements LaowuContractsService {

	private static Logger logger = LoggerFactory.getLogger(LaowuContractsServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.LAOWU_CONTRACTS;
    
    private LaowuContractsDao laowuContractsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = laowuContractsDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("劳务合同{}", e.getMessage());
			SystemException.handleMessageException("劳务合同查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        LaowuContractsVO vo = new LaowuContractsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           // BusinessUtil.setInsertCommonFields(vo, user);
           // vo.setYwlx(ywlx);

            //EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		    //vo.setSjbh(eventVO.getSjbh());
            vo.setCreate_date(new Date());
            vo.setCreate_by(user.getIdCard());
            // 插入
			laowuContractsDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("劳务合同{}", e.getMessage());
            SystemException.handleMessageException("劳务合同新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        LaowuContractsVO vo = new LaowuContractsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
           // vo.setYwlx(ywlx);
            //EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

            // 修改
            laowuContractsDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("劳务合同{}", e.getMessage());
            SystemException.handleMessageException("劳务合同修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		LaowuContractsVO vo = new LaowuContractsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//laowuContractsDao.delete(LaowuContractsVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("劳务合同{}", e.getMessage());
            SystemException.handleMessageException("劳务合同删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("laowuContractsDaoImpl")
	public void setLaowuContractsDao(LaowuContractsDao laowuContractsDao) {
		this.laowuContractsDao = laowuContractsDao;
	}

	public String queryById(String msg) {
		// TODO Auto-generated method stub
		return this.laowuContractsDao.queryById(msg);
	}
    
}
