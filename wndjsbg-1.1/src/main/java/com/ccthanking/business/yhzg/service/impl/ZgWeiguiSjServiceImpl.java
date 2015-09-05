/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgWeiguiSjService.java
 * 创建日期： 2015-04-21 下午 01:21:34
 * 功能：    接口：违规事件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:21:34  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.dtgl.yhzg.vo.ZgWeiguiSjVO;
import com.ccthanking.business.yhzg.dao.ZgWeiguiSjDao;
import com.ccthanking.business.yhzg.service.ZgWeiguiSjService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ZgWeiguiSjService.java </p>
 * <p> 功能：违规事件 </p>
 *
 * <p><a href="ZgWeiguiSjService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Service
public class ZgWeiguiSjServiceImpl extends Base1ServiceImpl<ZgWeiguiSjVO, String> implements ZgWeiguiSjService {

	private static Logger logger = LoggerFactory.getLogger(ZgWeiguiSjServiceImpl.class);
	
    
    private ZgWeiguiSjDao zgWeiguiSjDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = zgWeiguiSjDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("违规事件{}", e.getMessage());
			SystemException.handleMessageException("违规事件查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ZgWeiguiSjVO vo = new ZgWeiguiSjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
            //vo.setYwlx(ywlx);

           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		    //vo.setSjbh(eventVO.getSjbh());

            // 插入
			zgWeiguiSjDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("违规事件{}", e.getMessage());
            SystemException.handleMessageException("违规事件新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ZgWeiguiSjVO vo = new ZgWeiguiSjVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
           // vo.setYwlx(ywlx);
           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

            // 修改
            zgWeiguiSjDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("违规事件{}", e.getMessage());
            SystemException.handleMessageException("违规事件修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ZgWeiguiSjVO vo = new ZgWeiguiSjVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//zgWeiguiSjDao.delete(ZgWeiguiSjVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("违规事件{}", e.getMessage());
            SystemException.handleMessageException("违规事件删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("zgWeiguiSjDaoImpl")
	public void setZgWeiguiSjDao(ZgWeiguiSjDao zgWeiguiSjDao) {
		this.zgWeiguiSjDao = zgWeiguiSjDao;
	}

	public String getTree() {
		// TODO Auto-generated method stub
		return this.zgWeiguiSjDao.getTree();
	}

	public String queryZgsj(String msg) {
		// TODO Auto-generated method stub
		return this.zgWeiguiSjDao.queryZgsj(msg);
	}
    
}
