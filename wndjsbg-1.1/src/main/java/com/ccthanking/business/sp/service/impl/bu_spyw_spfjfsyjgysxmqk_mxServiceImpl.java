/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_spfjfsyjgysxmqk_mxService.java
 * 创建日期： 2014-11-06 下午 03:55:33
 * 功能：    接口：商品房交付使用竣工验收项目情况表_明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:55:33  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.bu_spyw_spfjfsyjgysxmqk_mxDao;
import com.ccthanking.business.sp.service.bu_spyw_spfjfsyjgysxmqk_mxService;
import com.ccthanking.business.spyw.vo.BuSpywCspsxkzsqVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysxmqkMxVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> bu_spyw_spfjfsyjgysxmqk_mxService.java </p>
 * <p> 功能：商品房交付使用竣工验收项目情况表_明细 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysxmqk_mxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Service
public class bu_spyw_spfjfsyjgysxmqk_mxServiceImpl extends Base1ServiceImpl<BuSpywSpfjfsyjgysxmqkMxVO, String> implements bu_spyw_spfjfsyjgysxmqk_mxService {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysxmqk_mxServiceImpl.class);

	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SPFJFSYJGYSXMQK_MX;
    
    private bu_spyw_spfjfsyjgysxmqk_mxDao bu_spyw_spfjfsyjgysxmqk_mxDao;
	@Autowired
	@Qualifier("bu_spyw_spfjfsyjgysxmqk_mxDaoImpl")
	public void setbu_spyw_spfjfsyjgysxmqk_mxDao(bu_spyw_spfjfsyjgysxmqk_mxDao bu_spyw_spfjfsyjgysxmqk_mxDao) {
		this.bu_spyw_spfjfsyjgysxmqk_mxDao = bu_spyw_spfjfsyjgysxmqk_mxDao;
	}
    
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = bu_spyw_spfjfsyjgysxmqk_mxDao.queryCondition(json, null, null);

         
        }catch (DaoException e) {
        	logger.error("商品房交付使用竣工验收项目情况表_明细{}", e.getMessage());
			SystemException.handleMessageException("商品房交付使用竣工验收项目情况表_明细查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    public String queryUpdate(String id) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        try {
        	resultVO=bu_spyw_spfjfsyjgysxmqk_mxDao.queryUpdate(id);
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表_明细查询{}", e.getMessage());
           
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表_明细查询失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    public String insert(String json) throws Exception {

		
        String resultVO = null;
        try {
           
			
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表_明细{}", e.getMessage());
            
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表_明细新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        
        String resultVO = null;

        try {
          
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表_明细{}", e.getMessage());
            
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表_明细修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {


		String resultVo = null;
		try {
			
		} catch (DaoException e) {
            logger.error("商品房交付使用竣工验收项目情况表_明细{}", e.getMessage());
           
            SystemException.handleMessageException("商品房交付使用竣工验收项目情况表_明细删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}


}
