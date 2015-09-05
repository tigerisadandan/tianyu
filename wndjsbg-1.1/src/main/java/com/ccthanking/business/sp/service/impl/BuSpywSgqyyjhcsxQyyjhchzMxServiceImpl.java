/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywSgqyyjhcsxQyyjhchzMxService.java
 * 创建日期： 2014-11-12 上午 11:32:59
 * 功能：    接口：施工企业业绩核查汇总表_明细
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-12 上午 11:32:59  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.BuSpywSgqyyjhcsxQyyjhchzMxDao;
import com.ccthanking.business.sp.service.BuSpywSgqyyjhcsxQyyjhchzMxService;
import com.ccthanking.business.spyw.vo.BuSpywSgqyyjhcsxQyyjhchzMxVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuSpywSgqyyjhcsxQyyjhchzMxService.java </p>
 * <p> 功能：施工企业业绩核查汇总表_明细 </p>
 *
 * <p><a href="BuSpywSgqyyjhcsxQyyjhchzMxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-12
 * 
 */

@Service
public class BuSpywSgqyyjhcsxQyyjhchzMxServiceImpl extends Base1ServiceImpl<BuSpywSgqyyjhcsxQyyjhchzMxVO, String> implements BuSpywSgqyyjhcsxQyyjhchzMxService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSgqyyjhcsxQyyjhchzMxServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SGQYYJHCSX_QYYJHCHZ_MX;
    
    private BuSpywSgqyyjhcsxQyyjhchzMxDao buSpywSgqyyjhcsxQyyjhchzMxDao;

    @Autowired
	@Qualifier("buSpywSgqyyjhcsxQyyjhchzMxDaoImpl")
	public void setBuSpywSgqyyjhcsxQyyjhchzMxDao(BuSpywSgqyyjhcsxQyyjhchzMxDao buSpywSgqyyjhcsxQyyjhchzMxDao) {
		this.buSpywSgqyyjhcsxQyyjhchzMxDao = buSpywSgqyyjhcsxQyyjhchzMxDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywSgqyyjhcsxQyyjhchzMxDao);
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buSpywSgqyyjhcsxQyyjhchzMxDao.queryCondition(json);

         
        }catch (DaoException e) {
        	logger.error("施工企业业绩核查汇总表_明细{}", e.getMessage());
			SystemException.handleMessageException("施工企业业绩核查汇总表_明细查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

        String resultVO = null;

        try {
			buSpywSgqyyjhcsxQyyjhchzMxDao.insert(json);
			
        } catch (DaoException e) {
            logger.error("施工企业业绩核查汇总表_明细{}", e.getMessage());
            SystemException.handleMessageException("施工企业业绩核查汇总表_明细新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        String resultVO = null;

        try {
            buSpywSgqyyjhcsxQyyjhchzMxDao.update(json);

        } catch (DaoException e) {
            logger.error("施工企业业绩核查汇总表_明细{}", e.getMessage());
            SystemException.handleMessageException("施工企业业绩核查汇总表_明细修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    public String queryUpdate(String id) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        try {
        	resultVO=buSpywSgqyyjhcsxQyyjhchzMxDao.queryUpdate(id);
        } catch (DaoException e) {
            logger.error("无锡新区新型墙体材料专项基金返退申请_材料{}", e.getMessage());
           
            SystemException.handleMessageException("无锡新区新型墙体材料专项基金返退申请_材料修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    public String delete(String json) throws Exception {

		String resultVo = null;
		try {

			
		} catch (DaoException e) {
            logger.error("施工企业业绩核查汇总表_明细{}", e.getMessage());
            SystemException.handleMessageException("施工企业业绩核查汇总表_明细删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	
    
}
