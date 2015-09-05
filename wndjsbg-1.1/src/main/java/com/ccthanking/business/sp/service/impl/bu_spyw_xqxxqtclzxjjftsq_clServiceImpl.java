/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_xqxxqtclzxjjftsq_clService.java
 * 创建日期： 2014-10-28 下午 04:49:05
 * 功能：    接口：无锡新区新型墙体材料专项基金返退申请_材料
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-28 下午 04:49:05  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.bu_spyw_xqxxqtclzxjjftsq_clDao;
import com.ccthanking.business.sp.service.bu_spyw_xqxxqtclzxjjftsq_clService;
import com.ccthanking.business.spyw.vo.BuSpywXqxxqtclzxjjftsqClVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> bu_spyw_xqxxqtclzxjjftsq_clService.java </p>
 * <p> 功能：无锡新区新型墙体材料专项基金返退申请_材料 </p>
 *
 * <p><a href="bu_spyw_xqxxqtclzxjjftsq_clService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-10-28
 * 
 */

@Service
public class bu_spyw_xqxxqtclzxjjftsq_clServiceImpl extends Base1ServiceImpl<BuSpywXqxxqtclzxjjftsqClVO, String> implements bu_spyw_xqxxqtclzxjjftsq_clService {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_xqxxqtclzxjjftsq_clServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_XQXXQTCLZXJJFTSQ_CL;
    
    private bu_spyw_xqxxqtclzxjjftsq_clDao bu_spyw_xqxxqtclzxjjftsq_clDao;

	@Autowired
	@Qualifier("bu_spyw_xqxxqtclzxjjftsq_clDaoImpl")
	public void setbu_spyw_xqxxqtclzxjjftsq_clDao(bu_spyw_xqxxqtclzxjjftsq_clDao bu_spyw_xqxxqtclzxjjftsq_clDao) {
		this.bu_spyw_xqxxqtclzxjjftsq_clDao = bu_spyw_xqxxqtclzxjjftsq_clDao;
	}
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = bu_spyw_xqxxqtclzxjjftsq_clDao.queryCondition(json);

     
        }catch (DaoException e) {
        	logger.error("无锡新区新型墙体材料专项基金返退申请_材料{}", e.getMessage());
			
			SystemException.handleMessageException("无锡新区新型墙体材料专项基金返退申请_材料查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;

        try {
        	resultVO=bu_spyw_xqxxqtclzxjjftsq_clDao.insert(json);
        } catch (DaoException e) {
            logger.error("无锡新区新型墙体材料专项基金返退申请_材料{}", e.getMessage());
           
            SystemException.handleMessageException("无锡新区新型墙体材料专项基金返退申请_材料新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String queryUpdate(String id) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        try {
        	resultVO=bu_spyw_xqxxqtclzxjjftsq_clDao.queryUpdate(id);
        } catch (DaoException e) {
            logger.error("无锡新区新型墙体材料专项基金返退申请_材料{}", e.getMessage());
           
            SystemException.handleMessageException("无锡新区新型墙体材料专项基金返退申请_材料修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		try {
			
		} catch (DaoException e) {
            logger.error("无锡新区新型墙体材料专项基金返退申请_材料{}", e.getMessage());
            SystemException.handleMessageException("无锡新区新型墙体材料专项基金返退申请_材料删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

    
}
