/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.ZtbZbdlService.java
 * 创建日期： 2015-01-14 上午 11:44:05
 * 功能：    接口：招标代理机构维护
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-14 上午 11:44:05  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.dao.ZtbZbdlDao;
import com.ccthanking.business.wxgc.service.ZtbZbdlService;
import com.ccthanking.business.ztb.vo.ZtbZbdlVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ZtbZbdlService.java </p>
 * <p> 功能：招标代理机构维护 </p>
 *
 * <p><a href="ZtbZbdlService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-14
 * 
 */

@Service
public class ZtbZbdlServiceImpl extends Base1ServiceImpl<ZtbZbdlVO, String> implements ZtbZbdlService {

	private static Logger logger = LoggerFactory.getLogger(ZtbZbdlServiceImpl.class);
	
    private ZtbZbdlDao ztbZbdlDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = ztbZbdlDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("招标代理机构维护{}", e.getMessage());
			SystemException.handleMessageException("招标代理机构维护查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ZtbZbdlVO vo = new ZtbZbdlVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            vo.setCreated_date(new Date());
            vo.setCreated_name(user.getName());
            vo.setCreated_uid(user.getAccount());

            // 插入
			ztbZbdlDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("招标代理机构维护{}", e.getMessage());
            SystemException.handleMessageException("招标代理机构维护新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ZtbZbdlVO vo = new ZtbZbdlVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            vo.setUpdate_date(new Date());
            vo.setUpdate_uid(user.getAccount());
            vo.setUpdate_name(user.getUsername());
            
            // 修改
            ztbZbdlDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("招标代理机构维护{}", e.getMessage());
            SystemException.handleMessageException("招标代理机构维护修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ZtbZbdlVO vo = new ZtbZbdlVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			ztbZbdlDao.delete(ZtbZbdlVO.class, vo.getZtb_zbdl_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("招标代理机构维护{}", e.getMessage());
            SystemException.handleMessageException("招标代理机构维护删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("ztbZbdlDaoImpl")
	public void setZtbZbdlDao(ZtbZbdlDao ztbZbdlDao) {
		this.ztbZbdlDao = ztbZbdlDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)ztbZbdlDao);
	}
    
}
