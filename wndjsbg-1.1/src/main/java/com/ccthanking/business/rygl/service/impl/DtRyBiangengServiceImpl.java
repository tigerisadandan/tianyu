/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    rygl.service.DtRyBiangengService.java
 * 创建日期： 2015-04-12 下午 08:31:04
 * 功能：    接口：人员变更
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-12 下午 08:31:04  龚伟雄   创建文件，实现基本功能
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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.dtgl.rygl.vo.DtRyBiangengVO;
import com.ccthanking.business.rygl.dao.DtRyBiangengDao;
import com.ccthanking.business.rygl.service.DtRyBiangengService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> DtRyBiangengService.java </p>
 * <p> 功能：人员变更 </p>
 *
 * <p><a href="DtRyBiangengService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java1@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-12
 * 
 */

@Service
public class DtRyBiangengServiceImpl extends Base1ServiceImpl<DtRyBiangengVO, String> implements DtRyBiangengService {

	private static Logger logger = LoggerFactory.getLogger(DtRyBiangengServiceImpl.class);
	
	// 业务类型
    
    private DtRyBiangengDao dtRyBiangengDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = dtRyBiangengDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("人员变更{}", e.getMessage());
			SystemException.handleMessageException("人员变更查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        DtRyBiangengVO vo = new DtRyBiangengVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            //BusinessUtil.setInsertCommonFields(vo, user);
            
            vo.setStatus("0");//变更中
            vo.setShenpi_status("1");//审批状态：1－一级待审（安质监待审）

            // 插入
			dtRyBiangengDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("人员变更{}", e.getMessage());
            SystemException.handleMessageException("人员变更新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        DtRyBiangengVO vo = new DtRyBiangengVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	//BusinessUtil.setUpdateCommonFields(vo, user);

            // 修改
            dtRyBiangengDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("人员变更{}", e.getMessage());
            SystemException.handleMessageException("人员变更修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		DtRyBiangengVO vo = new DtRyBiangengVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			//dtRyBiangengDao.delete(DtRyBiangengVO.class, vo.getId());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("人员变更{}", e.getMessage());
            SystemException.handleMessageException("人员变更删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("dtRyBiangengDaoImpl")
	public void setDtRyBiangengDao(DtRyBiangengDao dtRyBiangengDao) {
		this.dtRyBiangengDao = dtRyBiangengDao;
	}

	public String getBgCount(String msg) {
		
		return this.dtRyBiangengDao.getBgCount(msg);
	}

	public String queryBg(String msg) {
		// TODO Auto-generated method stub
		return this.dtRyBiangengDao.queryBg(msg);
	}

	public String updateTg(String msg) {
		// TODO Auto-generated method stub
		return this.dtRyBiangengDao.updateTg(msg);
	}

	public String updateTh(String msg) {
		// TODO Auto-generated method stub
		return this.dtRyBiangengDao.updateTh(msg);
	}
    
}
