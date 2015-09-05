/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.WuGongService.java
 * 创建日期： 2015-03-24 上午 11:44:51
 * 功能：    接口：务工信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-03-24 上午 11:44:51  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rygl.service.impl;


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

import com.ccthanking.business.dtgl.rygl.vo.WuGongVO;
import com.ccthanking.business.rygl.dao.WuGongDao;
import com.ccthanking.business.rygl.service.WuGongService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> WuGongService.java </p>
 * <p> 功能：务工信息 </p>
 *
 * <p><a href="WuGongService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-03-24
 * 
 */

@Service
public class WuGongServiceImpl extends Base1ServiceImpl<WuGongVO, String> implements WuGongService {

	private static Logger logger = LoggerFactory.getLogger(WuGongServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.WUGONG;
    
    private WuGongDao wuGongDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = wuGongDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("务工信息{}", e.getMessage());
			SystemException.handleMessageException("务工信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WuGongVO vo = new WuGongVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            //BusinessUtil.setInsertCommonFields(vo, user);
           // vo.setYwlx(ywlx);

            //EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		   // vo.setSjbh(eventVO.getSjbh());

            // 插入
			wuGongDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("务工信息{}", e.getMessage());
            SystemException.handleMessageException("务工信息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WuGongVO vo = new WuGongVO();

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
            wuGongDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("务工信息{}", e.getMessage());
            SystemException.handleMessageException("务工信息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		WuGongVO vo = new WuGongVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//wuGongDao.delete(WuGongVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("务工信息{}", e.getMessage());
            SystemException.handleMessageException("务工信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("wuGongDaoImpl")
	public void setWuGongDao(WuGongDao wuGongDao) {
		this.wuGongDao = wuGongDao;
	}

	public String wugongtj(String msg) {
		
		return this.wuGongDao.wugongtj(msg);
	}

	public String queryById(String msg) {
		// TODO Auto-generated method stub
		return this.wuGongDao.queryById(msg);
	}

	public String queryGzqk(String msg) {
		// TODO Auto-generated method stub
		return this.wuGongDao.queryGzqk(msg);
	}

	public String queryJineng(String msg) {
		// TODO Auto-generated method stub
		return this.wuGongDao.queryJineng(msg);
	}

	public String queryTijianInfo(String msg) {
		// TODO Auto-generated method stub
		return this.wuGongDao.queryTijianInfo(msg);
	}
    
}
