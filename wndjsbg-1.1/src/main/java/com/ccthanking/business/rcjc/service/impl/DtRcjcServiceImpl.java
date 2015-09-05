/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rcjc.service.DtRcjcService.java
 * 创建日期： 2015-06-23 下午 05:51:24
 * 功能：    接口：日常检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-06-23 下午 05:51:24  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.rcjc.service.impl;


import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.dtgl.rcjc.vo.DtRcjcContentVO;
import com.ccthanking.business.dtgl.rcjc.vo.DtRcjcVO;
import com.ccthanking.business.dtgl.yhzg.vo.ZgContentVO;
import com.ccthanking.business.rcjc.dao.DtRcjcDao;
import com.ccthanking.business.rcjc.service.DtRcjcService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> DtRcjcService.java </p>
 * <p> 功能：日常检查 </p>
 *
 * <p><a href="DtRcjcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongweixiong@kzcpm.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-06-23
 * 
 */

@Service
public class DtRcjcServiceImpl extends Base1ServiceImpl<DtRcjcVO, String> implements DtRcjcService {

	private static Logger logger = LoggerFactory.getLogger(DtRcjcServiceImpl.class);
	
    
    private DtRcjcDao dtRcjcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = dtRcjcDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("日常检查{}", e.getMessage());
			SystemException.handleMessageException("日常检查查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        DtRcjcVO vo = new DtRcjcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            JSONArray arry = obj.getJSONArray("SJUID");
            String saveType = obj.getString("saveType");
            vo.setValueFromJson(obj);
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            // 插入
            vo.setCreated_by(user.getUserSN());
            vo.setCreated_date(new Date());
			dtRcjcDao.save(vo);
			
            if(null!=arry){
            	for (int i = 0; i < arry.size(); i++) {
            		DtRcjcContentVO cvo = new DtRcjcContentVO();
            		cvo.setDt_rcjc_uid(vo.getDt_rcjc_uid());
            		cvo.setZg_weigui_sj_uid(arry.getString(i));
            		cvo.setXuhao(i+1+"");
            		cvo.setDescription(obj.getJSONArray("DESCRIPTION").getString(i));
            		BaseDAO.insert(DBUtil.getConnection(), cvo);
				}
            }
            
            if("zgd".equals(saveType)){
            	
            	
            }


            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("日常检查{}", e.getMessage());
            SystemException.handleMessageException("日常检查新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        DtRcjcVO vo = new DtRcjcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            dtRcjcDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("日常检查{}", e.getMessage());
            SystemException.handleMessageException("日常检查修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		DtRcjcVO vo = new DtRcjcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			

			//删除   根据据主键
			dtRcjcDao.delete(DtRcjcVO.class, vo.getDt_rcjc_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("日常检查{}", e.getMessage());
            SystemException.handleMessageException("日常检查删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("dtRcjcDaoImpl")
	public void setDtRcjcDao(DtRcjcDao dtRcjcDao) {
		this.dtRcjcDao = dtRcjcDao;
	}
    
}
